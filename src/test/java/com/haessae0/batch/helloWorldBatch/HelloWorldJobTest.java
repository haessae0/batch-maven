package com.haessae0.batch.helloWorldBatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringBootTest
class HelloWorldJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private Job helloWorldJob;

    @Test
    @DisplayName("헬로_월드_잡_테스트")
    void testHelloWorldJob() throws Exception {
        // given:

        // when: Job 실행
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // then: Job 상태 검증
        assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    }
}
