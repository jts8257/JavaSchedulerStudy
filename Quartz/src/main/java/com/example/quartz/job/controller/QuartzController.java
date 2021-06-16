package com.example.quartz.job.controller;

import com.example.quartz.job.Job1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Slf4j
@Controller
public class QuartzController {
    //field injection 이니까 생성자 injection으로 바꾸자.

    private final Scheduler scheduler;

    // 생성자 주입
    private QuartzController(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void start() throws SchedulerException {
        JobDetail Job1Detail =
                buildJobDetail(Job1.class, "testJob", "test", new HashMap());

        scheduler.scheduleJob(Job1Detail, buildJobTrigger("0 * * * * ?"));
        /* manual
         Date scheduleJob(JobDetail jobDetail, Trigger trigger) throws SchedulerException;

         Add the given JobDetail to the Scheduler, and associate the given Trigger with it.
         If the given Trigger does not reference any Job,
         then it will be set to reference the Job passed with it into this method.
         */
    }

    public Trigger buildJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger().
                withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, String name, String group, Map params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).withIdentity(name, group)
                .usingJobData(jobDataMap)
                .build();
    }

    JobDetail
}

