//package com.haessae0.batch.scheduler;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//
////@Component
////@Slf4j
//public class BatchScheduler {
//
//	private final JobLauncher jobLauncher;
//	private final Job helloWorldJob;
//
//	@Autowired
//	public BatchScheduler(JobLauncher jobLauncher, Job helloWorldJob) {
//		this.jobLauncher = jobLauncher;
//		this.helloWorldJob = helloWorldJob;
//	}
//
////	@Scheduled(fixedRate = 60000)
////	@Scheduled(cron = "0 0/10 * * * *")
//	public void runBatchJob() {
//		try {
//			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
//					.toJobParameters();
//
//			jobLauncher.run(helloWorldJob, jobParameters);
//
//		} catch (Exception e) {
//			log.error("Error while executing batch job: {}", e.getMessage());
//		}
//	}
//}
