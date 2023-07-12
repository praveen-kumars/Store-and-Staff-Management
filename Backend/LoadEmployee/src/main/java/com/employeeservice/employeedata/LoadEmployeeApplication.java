package com.employeeservice.employeedata;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class LoadEmployeeApplication {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Value("${source.path}")
	private String sourceDir;
	
	@Value("${destination.path}")
	private String destinationDir;
	
	
	@Autowired
	private Job job;

	public static void main(String[] args) {
		SpringApplication.run(LoadEmployeeApplication.class, args);
	}
	
	@Scheduled(cron = "0 */1 * * * ?")
	public void LoadEmployee() {
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
