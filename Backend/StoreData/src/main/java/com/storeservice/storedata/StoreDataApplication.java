package com.storeservice.storedata;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class StoreDataApplication {
	
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Value("${source.path}")
	private String sourceDir;
	
	@Value("${destination.path}")
	private String destinationDir;
	
	
	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(StoreDataApplication.class, args);
		
	}

	
	@Scheduled(cron = "0 */1 * * * ?")
	public void LoadStore() throws Exception{
		System.out.print("Welcome to schedule");
		JobParameters jobParameters=new JobParametersBuilder()
				.addString("SourceDir",sourceDir)
				.addString("DestDir", destinationDir)
				.addLong("startAt",System.currentTimeMillis()).toJobParameters();
		
		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}