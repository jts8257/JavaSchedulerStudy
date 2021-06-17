package quartztutorial.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

    private final Date now = new Date();
    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.sss");

    public HelloJob() {
        System.err.println(format.format(now) +  " HelloJob created");
    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println(format.format(now) + " HelloJob excuted");
    }
}
