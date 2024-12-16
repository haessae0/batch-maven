package com.haessae0.batch.rtl.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@EnableScheduling
@AllArgsConstructor
public class BatchScheduler {

	private final JobLauncher jobLauncher;
	private final Job processJob;

	@Scheduled(cron = "0 0/24 * * * *")
	public void runJob() {
		try {
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			JobParameters jobParameters = new JobParametersBuilder().addString("timestamp", timestamp)
					.toJobParameters();

			jobLauncher.run(processJob, jobParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}