package org.example.animalhospital.batch

import org.example.animalhospital.entity.Reserve
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
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

@Configuration
@EnableBatchProcessing
class StatusJobApp(
    private val statusReader: StatusReader,
    private val statusProcessor: RecordProcessor,
    private val statusWriter: RecordWriter
): DefaultBatchConfiguration() {
    private val log = LoggerFactory.getLogger(RecordJobApp::class.java)

    @Bean("StatusTransferJob")
    fun statusTransferJob(jobRepository: JobRepository): Job {
        return JobBuilder("statusTransferJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(dataTransferStep(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun dataTransferStep(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("statusTransferJob", jobRepository)
            .allowStartIfComplete(true)
            .chunk<Reserve?, Reserve>(100, transactionManager)
            .reader(statusReader)
            .processor(statusProcessor)
            .writer(statusWriter)
            .build()
    }

    @Bean
    @Scheduled(cron = "0 0 0 * * *")  // 매일 자정 작업
    fun statusJobLauncher(): JobLauncher {
        val jobLauncher = TaskExecutorJobLauncher()
        jobLauncher.setJobRepository(jobRepository())
        jobLauncher.setTaskExecutor(SimpleAsyncTaskExecutor())
        jobLauncher.afterPropertiesSet()
        return jobLauncher
    }
}