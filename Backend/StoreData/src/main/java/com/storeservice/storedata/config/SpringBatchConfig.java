package com.storeservice.storedata.config;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.xml.StandaloneStepParser;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.skip.ExceptionClassifierSkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

import com.storeservice.storedata.model.Store;
import com.storeservice.storedata.repository.StoreRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
	
	private JobBuilderFactory jobBuilderFactory;
	
	private StepBuilderFactory stepBuilderFactory;
	
	private StoreRepo storeRepo;
	
	@Bean
	@StepScope
	public FlatFileItemReader<Store> reader(@Value("#{jobParameters['SourceDir']}") String sourceDir){
		log.info("BEGIN - [Item Reader]");
		FlatFileItemReader<Store> itemReader=new FlatFileItemReader<>();
		itemReader.setResource(new PathResource(sourceDir));
		itemReader.setName("csv-reader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		log.info("END -[Item Reader]");
		return itemReader;
	}
	
	@Bean
	public LineMapper<Store> lineMapper(){
		
		DefaultLineMapper<Store> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
	
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("store_id","address","phone_number","area_region_code","mon_hours","tue_hours",
				"wed_hours","thru_hours","fri_hours","sat_hours","sun_hours");
		
		BeanWrapperFieldSetMapper<Store> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Store.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	@Bean
	public StoreProcessor processor() {
		
		return new StoreProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Store> writer(){
		log.info("BEGIN - [Storing in database] ");
		RepositoryItemWriter<Store> writer=new RepositoryItemWriter<>();
		writer.setRepository(storeRepo);
		writer.setMethodName("save");
		log.info("END-[Storing in database]");
		return writer;
		
	}
	@Bean
	public Step step1() {
		log.info("BEGIN - [Step builder csv-db]");
		log.info("END-[Step builder csv-db]");
		return stepBuilderFactory.get("csv-db")
				.<Store,Store>chunk(10)
				.reader(reader(null))
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	@StepScope
	public Tasklet moveFilesTasklet(@Value("#{jobParameters['SourceDir']}") String sourceDirLocation,
			@Value("#{jobParameters['DestDir']}") String destinationDirLocation) {
		return (StepContribution,ChunkContext)->{
			
			log.info("BEGIN - Moving file from master to archieve");

			Date date=new Date();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MMddyyyy");
			String dateString=simpleDateFormat.format(date);
			
			String destinationLocation;
			destinationLocation=destinationDirLocation+"store_info"+dateString+".csv";
		
			Path sourcePath=Paths.get(sourceDirLocation);
			System.out.println(sourcePath);
			System.out.println(destinationDirLocation);
			Path destPath=Paths.get(destinationLocation);
			System.out.println(destPath);
			System.out.println(destinationLocation);
			try{
				Files.move(sourcePath,destPath,StandardCopyOption.REPLACE_EXISTING);
				log.info("END -[Files successfully moved]");
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return RepeatStatus.FINISHED;
				
			};
	
	}
	
	
	
	
	@Bean
	public Step step2() {
		log.info("BEGIN -[Step from process-csv]");
		log.info("END - [File move ended process-csv]");
		return stepBuilderFactory.get("process-csv")
				.tasklet(moveFilesTasklet(null,null))
				.build();
		}
	
	
	
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("importStore")
				.start(step1())
				.next(step2())
				.build();
	}
	
		
	
	
	
	
}
