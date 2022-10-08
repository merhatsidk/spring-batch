package com.firstProject.springBatch.controller;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class StudentController {
    private JobLauncher jobLauncher;
    private Job job;



    @PostMapping("/student")
    public void importStudentToDb() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters parameter = new JobParametersBuilder().addLong("start",System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(job, parameter);
    }
}
