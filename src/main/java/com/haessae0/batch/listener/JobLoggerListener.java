package com.haessae0.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobLoggerListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job Started: {}", jobExecution.getJobInstance().getJobName());
        log.info("Job Instance ID: {}", jobExecution.getJobInstance().getInstanceId());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job Completed: {}", jobExecution.getJobInstance().getJobName());
        log.info("Job Instance ID: {}", jobExecution.getJobInstance().getInstanceId());
        log.info("Job Status: {}", jobExecution.getStatus());
    }
}
