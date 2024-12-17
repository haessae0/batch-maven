package com.haessae0.batch.reader;

import com.haessae0.batch.dto.MrctDto;
import com.haessae0.batch.mapper.MailRtlWeeklyMapper;
import org.springframework.batch.item.ItemReader;

import java.util.List;

public class MrctItemReader implements ItemReader<String> {

    private final MailRtlWeeklyMapper mailRtlWeeklyMapper;
    private final String curDate;
    private int currentIndex = 0;
    private List<MrctDto> mrctList;

    public MrctItemReader(MailRtlWeeklyMapper mailRtlWeeklyMapper, String curDate) {
        this.mailRtlWeeklyMapper = mailRtlWeeklyMapper;
        this.curDate = curDate;
    }

    @Override
    public String read() throws Exception {
        if (mrctList == null) {
            mrctList = mailRtlWeeklyMapper.selectMrctList(curDate);
        }

        if (currentIndex < mrctList.size()) {
            String mrctId = mrctList.get(currentIndex).getMrctId();
            currentIndex++;
            return mrctId;
        } else {
            return null;
        }
    }

}
