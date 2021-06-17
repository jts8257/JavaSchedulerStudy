package quartztutorial;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import quartztutorial.Job.HelloJob;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzMain {

    public static void main(String[] args) {

        try {
            // Grab the Scheduler instance from the Factory
            // StdSchedulerFactory 는 인스턴스화 하지 않아도 사용 가능.
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            System.out.println(scheduler);

            // and start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            // Job의 detail을 정하는 메서드들은 모두 JobBuilder class 에서 import된 메서드들임.

            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 5 seconds
            // 마찬가지로 TriggerBuilder가 staic하게 import되어 있으므로 그냥 메서드 사용 가능.
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // ShutDown 해버리면 스케줄러가 돌아가는걸 볼 수 없음.
            // delay 시키는 작업이 필요함.
            Thread.sleep(10100 );
            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }
}
