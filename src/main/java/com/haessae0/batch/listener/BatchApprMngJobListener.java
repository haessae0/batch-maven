package com.haessae0.batch.listener;

import com.haessae0.batch.Util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class BatchApprMngJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(">>> Job 시작 전 처리: insertBatchApprMng 대신 로그만 출력합니다.");
        String seqNo = "TEST_SEQ_NO";
        log.info(">>> 생성된 SEQ_NO: {}", seqNo);
        jobExecution.getExecutionContext().put("seqNo", seqNo);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String seqNo = (String) jobExecution.getExecutionContext().get("seqNo");
        if (jobExecution.getStatus().isUnsuccessful()) {
            log.error(">>> Job 실패. SEQ_NO: {}", seqNo);
            log.error(">>> 유통사별 주정산 메일 발송 오류 로그만 남기기. 실제 DB 업데이트 없음.");
            log.error("[모인스] {} 유통사별 주정산 메일 발송 실패 로그", DateUtil.getCurDate("yyyy.MM.dd"));
        } else {
            log.info(">>> Job 정상 종료. SEQ_NO: {}", seqNo);
            log.info(">>> 정상 처리 되었습니다. (DB 업데이트 없이 로그만 남김)");
        }
    }
}
