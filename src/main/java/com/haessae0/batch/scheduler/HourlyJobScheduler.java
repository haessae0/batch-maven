package com.haessae0.batch.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HourlyJobScheduler {
    private final JobLauncher jobLauncher;
    private final Job mailRtlWeeklyJob;

    @Scheduled(cron = "0 12 * * * ?")
    public void runMailRtlWeeklyJob() {
        try {
            JobExecution execution = jobLauncher.run(
                    mailRtlWeeklyJob,
                    new JobParametersBuilder()
                            .addLong("timestamp", System.currentTimeMillis())
                            .toJobParameters()
            );
            System.out.println("Job Exit Status : " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
