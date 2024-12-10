package com.haessae0.batch.rtl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class RtlDto {

	private String mrctId;
	private String mrctNm;
	private String billDt;
	private String cntrPrdtNm;
	private String billCnt;
	private String billAmt;
	private String feeRt;
	private String splAmt;
	private String vat;
	private String fee;
	private String rcptSchAmt;
	private String rtlcntrNo;
}
