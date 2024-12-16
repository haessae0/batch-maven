package com.haessae0.batch.rtl.job.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.haessae0.batch.rtl.dto.RtlDto;

@Component
public class RtlItemProcessor implements ItemProcessor<RtlDto, RtlDto> {

	@Override
	public RtlDto process(RtlDto item) throws Exception {
		System.out.println("Processing RtlDto : " + item);
		item.setMrctNm("배치에서 테스트로 바꾸는 거임");
		return item;
	}

}
