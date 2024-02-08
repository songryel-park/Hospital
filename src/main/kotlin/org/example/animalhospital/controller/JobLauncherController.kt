package org.example.animalhospital.controller

import org.hibernate.annotations.processing.SQL
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class JobLauncherController {
    @Autowired
    lateinit var jobLauncher: JobLauncher

    @Autowired
    lateinit var job: Job

    @RequestMapping("/jobLauncher.html")
//    @Throws(java.lang.Exception::class)
    fun handle() {
        jobLauncher.run(job, JobParameters())
    }
}