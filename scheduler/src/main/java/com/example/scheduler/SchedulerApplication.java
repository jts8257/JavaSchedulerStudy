package com.example.scheduler;

import com.example.scheduler.schedulerclass.Scheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
        Scheduler scheduler = new Scheduler();
        scheduler.cronJobSch();
    }
}
