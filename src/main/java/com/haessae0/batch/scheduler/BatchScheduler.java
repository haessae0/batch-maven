package com.haessae0.batch.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job helloWorldJob;

    @Autowired
    public BatchScheduler(JobLauncher jobLauncher, Job helloWorldJob) {
        this.jobLauncher = jobLauncher;
        this.helloWorldJob = helloWorldJob;
    }

    @Scheduled(fixedRate = 60000)
    public void runBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(helloWorldJob, jobParameters);

        } catch (Exception e) {
            log.error("Error while executing batch job: {}", e.getMessage());
        }
    }
}