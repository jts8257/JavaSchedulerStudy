package com.example.qaurtzwithjdbc_dao.configuration;

import com.example.qaurtzwithjdbc_dao.quartz.job.AlarmJob;
import com.example.qaurtzwithjdbc_dao.quartz.jobutil.JobUtil;

import org.quartz.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:/quartz.properties")
public class JobConfiguration {

    private final Scheduler scheduler;
    private final JobUtil jobUtil;

    @Autowired
    public JobConfiguration(Scheduler scheduler, JobUtil jobUtil) {
        System.out.println("JobConfiguration Constructed");
        this.scheduler = scheduler;
        this.jobUtil = jobUtil;
    }

    @PostConstruct
    public void start() {
        try {

            /* DAO를 스케줄러에 등록*/
            JobDetail AlarmJob = newJob(AlarmJob.class)
                    .withIdentity("Alarm", "DaoGroup")
                    .build();

            Trigger AlarmTrigger = newTrigger()
                    .withIdentity("AlarmTrigger", "DaoGroup")
                    .withSchedule(cronSchedule("1/10 * * * * ?"))
                    .forJob("Alarm", "DaoGroup")
                    .build();

            scheduler.scheduleJob(AlarmJob, AlarmTrigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
