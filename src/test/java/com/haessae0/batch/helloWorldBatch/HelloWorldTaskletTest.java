package com.haessae0.batch.helloWorldBatch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.haessae0.batch.hello.tasklet.HelloWorldTasklet;

class HelloWorldTaskletTest {

	@Test
	@DisplayName("헬로_월드_테스클랫_테스트")
	void testHelloWorldTasklet() throws Exception {
		// given: Tasklet 인스턴스 생성
		Tasklet tasklet = new HelloWorldTasklet();

		// when: Tasklet 실행
		RepeatStatus status = tasklet.execute(null, null);

		// then: 결과 검증
		assertThat(status).isEqualTo(RepeatStatus.FINISHED);
	}
}
