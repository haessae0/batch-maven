package com.haessae0.batch.rtl.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.haessae0.batch.rtl.dto.RtlDto;
import com.haessae0.batch.rtl.job.processor.RtlItemProcessor;
import com.haessae0.batch.rtl.job.reader.RtlItemReader;
import com.haessae0.batch.rtl.job.tasklet.FinalizeTasklet;
import com.haessae0.batch.rtl.job.writer.RtlItemWriter;
import com.haessae0.batch.rtl.listener.JobLoggerListener;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final JobLoggerListener jobLoggerListener;

	@Bean
	public Job processJob(Step processStep, Step finalizeStep) {
		return jobBuilderFactory.get("processJob").listener(jobLoggerListener).start(processStep).next(finalizeStep)
				.build();
	}

	@Bean
	public Step processStep(RtlItemReader itemReader, RtlItemProcessor itemProcessor, RtlItemWriter itemWriter) {
		return stepBuilderFactory.get("processStep").<RtlDto, RtlDto>chunk(2).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();
	}

	@Bean
	public Step finalizeStep() {
		return stepBuilderFactory.get("finalizeStep").tasklet(new FinalizeTasklet()).build();

	}

}
