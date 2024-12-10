package com.haessae0.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.haessae0.batch.listener.JobLoggerListener;
import com.haessae0.batch.tasklet.HelloWorldTasklet;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final JobLoggerListener jobLoggerListener;

	@Bean
	public Job helloWorldJob(Step helloWorldStep) {
		return jobBuilderFactory.get("helloWorldJob").listener(jobLoggerListener).start(helloWorldStep).build();
	}

	@Bean
	public Step helloWorldStep(HelloWorldTasklet tasklet) {
		return stepBuilderFactory.get("helloWorldStep").tasklet(tasklet).build();
	}

}
