package com.haessae0.batch.rtl.job.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.haessae0.batch.rtl.dto.RtlDto;

@Component
public class RtlItemWriter implements ItemWriter<RtlDto> {

	@Override
	public void write(List<? extends RtlDto> items) throws Exception {
		for (RtlDto item : items) {
			System.out.println("Writing DtlDto : " + item);
		}
	}

}
