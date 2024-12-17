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
public class HelloWorldScheduler {

    private final JobLauncher jobLauncher;
    private final Job helloWorldJob;

    // 매 시간 20분마다 실행: 0초, 20분, 매시간
    @Scheduled(cron = "0 12 * * * ?")
    public void runHelloWorldJob() {
        try {
            JobExecution execution = jobLauncher.run(
                    helloWorldJob,
                    new JobParametersBuilder()
                            .addLong("timestamp", System.currentTimeMillis())
                            .toJobParameters()
            );
            System.out.println("HelloWorldJob Exit Status: " + execution.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}