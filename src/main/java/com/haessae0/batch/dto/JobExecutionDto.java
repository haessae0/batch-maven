package com.haessae0.batch.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JobExecutionDto {
    private Long jobExecutionId;
    private Date startTime;
    private Date endTime;
    private String status;
    private String exitStatus;
}
