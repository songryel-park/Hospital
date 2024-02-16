package org.example.animalhospital.batch.record

import org.example.animalhospital.entity.Reserve
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.web.bind.annotation.RequestMapping


@Configuration
@EnableBatchProcessing
class RecordJobApp(
    private val recordReader: RecordReader,
//    private val recordProcessor: RecordProcessor,
    private val recordWriter: RecordWriter,
): DefaultBatchConfiguration() {
    private val log = LoggerFactory.getLogger(RecordJobApp::class.java)
//    private lateinit var recordJobLauncher: TaskExecutorJobLauncher

    @Bean("ReserveRetentionJob")
    fun reserveRetentionJob(jobRepository: JobRepository): Job {
        return JobBuilder("reserveRetentionJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(dataRetentionStep(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun dataRetentionStep(jobRepository: JobRepository,
                          transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("dataRetentionStep", jobRepository)
            .allowStartIfComplete(true)
            .chunk<Reserve?, Reserve>(100, transactionManager)
            .reader(recordReader)
//            .processor(recordProcessor)
            .writer(recordWriter)
            .build()
    }

    @Bean
    fun recordJobLauncher(): JobLauncher {
        val jobLauncher = TaskExecutorJobLauncher()
        jobLauncher.setJobRepository(jobRepository())
        jobLauncher.setTaskExecutor(SimpleAsyncTaskExecutor())
        jobLauncher.afterPropertiesSet()
        return jobLauncher
    }

    @Scheduled(cron = "0 0 4 * * *")  // 매일 오전 4시 작업
//    @RequestMapping("/recordJobLauncher.html")
    fun recordJob() {
        recordJobLauncher().run(reserveRetentionJob(jobRepository()), JobParameters())
    }
}