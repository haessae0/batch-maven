package com.haessae0.batch.controller;

import com.haessae0.batch.dto.JobExecutionDto;
import com.haessae0.batch.dto.JobInstanceDto;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/monitor")
public class BatchMonitoringController {

    private final JobExplorer jobExplorer;

    public BatchMonitoringController(JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    @GetMapping("/job/{jobName}")
    public List<JobInstanceDto> getJobExecutions(@PathVariable String jobName) {
        // 최근 5개의 JobInstance 조회
        List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, 0, 5);

        List<JobInstanceDto> result = new ArrayList<>();
        for (JobInstance instance : jobInstances) {
            JobInstanceDto instanceDto = new JobInstanceDto();
            instanceDto.setJobInstanceId(instance.getId());
            instanceDto.setJobName(instance.getJobName());

            List<JobExecution> executions = jobExplorer.getJobExecutions(instance);
            List<JobExecutionDto> execDtos = new ArrayList<>();

            for (JobExecution exec : executions) {
                JobExecutionDto execDto = new JobExecutionDto();
                execDto.setJobExecutionId(exec.getId());
                execDto.setStartTime(exec.getStartTime());
                execDto.setEndTime(exec.getEndTime());
                execDto.setStatus(exec.getStatus().toString());
                execDto.setExitStatus(exec.getExitStatus().getExitCode());
                execDtos.add(execDto);
            }

            instanceDto.setExecutions(execDtos);
            result.add(instanceDto);
        }
        return result;
    }
}