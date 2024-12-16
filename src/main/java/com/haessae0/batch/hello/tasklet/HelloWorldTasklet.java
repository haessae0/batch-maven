package com.haessae0.batch.hello.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
//@AllArgsConstructor
public class HelloWorldTasklet implements Tasklet {

//	private RtlMapper rtlMapper;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

//		List<RtlDto> test = rtlMapper.selectRtlList();

//		log.info(test.toString());

		log.info("Hello, World ! Spring Batch is Running!!!");
		return RepeatStatus.FINISHED;
	}
}
