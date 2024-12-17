package com.haessae0.batch.config;

import com.haessae0.batch.Util.DateUtil;
import com.haessae0.batch.dto.MailCompositeItem;
import com.haessae0.batch.listener.BatchApprMngJobListener;
import com.haessae0.batch.mapper.MailRtlWeeklyMapper;
import com.haessae0.batch.processor.MrctItemProcessor;
import com.haessae0.batch.reader.MrctItemReader;
import com.haessae0.batch.writer.MrctItemWriter;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MailJobConfiguration {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private MailRtlWeeklyMapper mailRtlWeeklyMapper;

    @Bean
    public Job mailRtlWeeklyJob() {
        return jobBuilderFactory.get("mailRtlWeeklyJob")
                .listener(new BatchApprMngJobListener())
                .start(sendMailStep())
                .build();

    }

    @Bean
    public Step sendMailStep() {
        return stepBuilderFactory.get("sendMailStep")
                .<String, MailCompositeItem>chunk(10)
                .reader(new MrctItemReader(mailRtlWeeklyMapper, DateUtil.getCurDate("yyyyMMdd")))
                .processor(new MrctItemProcessor(mailRtlWeeklyMapper))
                .writer(new MrctItemWriter())
                .faultTolerant()
                .build();
    }
}
