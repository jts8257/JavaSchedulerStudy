package quartztutorial;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartztutorial.Job.DumbJob;
import quartztutorial.Job.HelloJob;

import org.quartz.JobBuilder;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzMain2 extends JobBuilder{

    public static void main(String[] args) {

        try {
            // SchedulerFactory를 직접 인스턴스화 해서 사용하는 방법
            SchedulerFactory schedFact = new StdSchedulerFactory();

            Scheduler sched = schedFact.getScheduler();

            sched.start();


            JobDetail job = newJob(DumbJob.class)
                    .withIdentity("myJob", "group1") // name "myJob", group "group1"
                    .usingJobData("jobSays", "Hello World!")
                    .usingJobData("myFloatValue", 3.141f)
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);

            Thread.sleep(10100 );
            sched.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }
}
