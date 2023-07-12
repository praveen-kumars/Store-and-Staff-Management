package com.employeeservice.employeedata.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = {"*"})
@Slf4j
public class EmployeeController {
	
	
	
	@PostMapping(path = "/EmployeeData")
	public void  importcsvtodb(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		log.info("BEGIN - [Storing multipart file]");

		File convertFile=new File("C:\\Users\\2113310\\OneDrive - Cognizant\\Desktop\\Case Study 2\\Backend\\LoadEmployee\\src\\main\\resources\\MasterData\\"+multipartFile.getOriginalFilename());
		try(FileOutputStream foutFile=new FileOutputStream(convertFile)){
			foutFile.write(multipartFile.getBytes());
			log.info("END - [Stored multipart file]");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

}
}