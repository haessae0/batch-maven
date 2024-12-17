package com.haessae0.batch.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobInstanceDto {
    private Long jobInstanceId;
    private String jobName;
    private List<JobExecutionDto> executions;
}
