package com.employeeservice.employeedata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoadEmployeeApplicationTests {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Value("${source.path}")
	private String sourceDir;
	
	@Value("${destination.path}")
	private String destinationDir;
	
	@Autowired
	private Job job;
	
	@Autowired
	LoadEmployeeApplication loadEmployeeApplication;

	@Test
	void contextLoads() {
		LoadEmployeeApplication.main(new String[] {});
	}
	
	@Test
	void LoadEmployee() {
		JobParameters jobParameters=new JobParametersBuilder()
				.addString("SourceDir",sourceDir)
				.addString("DestDir", destinationDir)
				.addLong("startAt",System.currentTimeMillis()).toJobParameters();
		
		   JobExecution jobExecution;
			try {
				jobExecution=jobLauncher.run(job, jobParameters);
				assertEquals(BatchStatus.COMPLETED,jobExecution.getStatus());
				loadEmployeeApplication.LoadEmployee();
			} catch (JobExecutionAlreadyRunningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.springframework.batch.core.repository.JobRestartException e) {
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
