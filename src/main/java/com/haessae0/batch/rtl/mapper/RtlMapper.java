package com.haessae0.batch.rtl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.haessae0.batch.rtl.dto.RtlDto;

@Mapper
public interface RtlMapper {

	List<RtlDto> selectRtlList();
}
