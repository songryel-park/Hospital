package org.example.animalhospital.controller

import org.hibernate.annotations.processing.SQL
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class JobLauncherController {

    @Qualifier("recordJobLauncher")
    @Autowired
    lateinit var recordJobLauncher: JobLauncher

    @Qualifier("statusJobLauncher")
    @Autowired
    lateinit var statusJobLauncher: JobLauncher

    @Qualifier("ReserveRetentionJob")
    @Autowired
    lateinit var recordJob: Job

    @Qualifier("StatusTransferJob")
    @Autowired
    lateinit var statusJob: Job

    @RequestMapping("/recordJobLauncher.html")
//    @Throws(java.lang.Exception::class)
    fun recordJob() {
        recordJobLauncher.run(recordJob, JobParameters())
    }

    @RequestMapping("/statusJobLauncher.html")
//    @Throws(java.lang.Exception::class)
    fun statusJob() {
        statusJobLauncher.run(statusJob, JobParameters())
    }
}