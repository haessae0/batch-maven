package com.haessae0.batch.writer;

import com.haessae0.batch.dto.MailCompositeItem;
import com.haessae0.batch.dto.MailItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class MrctItemWriter implements ItemWriter<MailCompositeItem> {
    @Override
    public void write(List<? extends MailCompositeItem> items) throws Exception {
        for (MailCompositeItem composite : items) {
            for (MailItem mailItem : composite.getMails()) {
                log.info("Sending mail to: {}", mailItem.getMailAddr());
                log.info("Mail title: {}", mailItem.getMailTitle());
                log.info("Mail content: {}", mailItem.getHtmlContent());
                log.info("Mail ID: {}", mailItem.getMailId());
                System.out.println("--------------------------------------------------");
                System.out.println("To       : " + mailItem.getMailAddr());
                System.out.println("Title    : " + mailItem.getMailTitle());
                System.out.println("Content  : " + mailItem.getHtmlContent());
                System.out.println("Mail ID  : " + mailItem.getMailId());
                System.out.println("--------------------------------------------------");
            }
        }
    }
}
