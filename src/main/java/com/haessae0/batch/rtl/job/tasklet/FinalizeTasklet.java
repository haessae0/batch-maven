package com.haessae0.batch.rtl.job.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class FinalizeTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// MyBatis INSERT 작업 수행
		finalizeDatabaseInsert();
		return RepeatStatus.FINISHED;
	}

	private void finalizeDatabaseInsert() {
		// DB INSERT 작업 구현
		System.out.println("Finalizing batch operation with DB insert...");
	}
}
