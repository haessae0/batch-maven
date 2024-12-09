package com.haessae0.batch.helloWorldBatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@SpringBatchTest
class HelloWorldStepTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @DisplayName("헬로_월드_스텝_테스트")
    @Test
    void testHelloWorldStep() throws Exception {
        // when: Step 실행
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("helloWorldStep");
        StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();

        // then: Step 상태 검증
        assertThat(stepExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }
}
