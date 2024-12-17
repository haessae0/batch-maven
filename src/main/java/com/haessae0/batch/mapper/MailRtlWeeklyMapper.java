package com.haessae0.batch.mapper;

import com.haessae0.batch.dto.KeyManDto;
import com.haessae0.batch.dto.MailIdDto;
import com.haessae0.batch.dto.MrctDto;
import com.haessae0.batch.dto.RtlDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MailRtlWeeklyMapper {

    List<MrctDto> selectMrctList(@Param("curDate") String curDate);

    List<KeyManDto> selectKeyMan(@Param("mrctId") String mrctId);

    List<RtlDto> selectRtlList(@Param("curDate") String curDate,
                               @Param("mrctId") String mrctId);

    MailIdDto getMailId(@Param("curDate") String curDate,
                        @Param("mrctId") String mrctId);

    void insertBatchApprMng();

    String selectSeqNo();

    void updateBatchApprMng(@Param("seqNo") String seqNo,
                            @Param("procRslt") String procRslt,
                            @Param("rsltMsg") String rsltMsg);

}