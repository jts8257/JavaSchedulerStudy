package com.example.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Component
public class Job1 extends QuartzJobBean implements InterruptableJob {

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        System.out.println("interrupt run");
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date now = new Date();
        //SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        SimpleDateFormat TimeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println("["+ TimeFormat.format(now) +"] Job1 Run");
    }
}
