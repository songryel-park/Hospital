package org.example.animalhospital.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.stereotype.Component

@Component
class BatchScheduler(private val jobLauncher: JobLauncher, private val myJob: Job) {
    fun runJob() {
        try {
            val jobExecution = jobLauncher.run(myJob, JobParameters())
            println("Batch Job completed with status: ${jobExecution.status}")
        } catch (e: Exception) {
            println("Batch Job failed: ${e.message}")
        }
    }
}