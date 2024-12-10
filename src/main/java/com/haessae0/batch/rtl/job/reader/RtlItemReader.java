package com.haessae0.batch.rtl.job.reader;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import com.haessae0.batch.rtl.dto.RtlDto;
import com.haessae0.batch.rtl.mapper.RtlMapper;

@Component
public class RtlItemReader implements ItemReader<RtlDto> {

	private RtlMapper rtlMapper;

	private List<RtlDto> selectRtlList;

	private int index = 0;

	public RtlItemReader(RtlMapper rtlMapper) {
		this.rtlMapper = rtlMapper;
	}

	@Override
	public RtlDto read() {
		if (selectRtlList == null) {
			selectRtlList = rtlMapper.selectRtlList();
		}

		if (index < selectRtlList.size()) {
			return selectRtlList.get(index++);
		}
		return null; // null 반환 시 종료
	}
}