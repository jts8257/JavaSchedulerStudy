package quartztutorial.Job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DumbJob implements Job {

    private final Date now = new Date();
    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.sss");

    public DumbJob() {
        System.err.println(format.format(now) +  " DumbJob created");
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloat("myFloatValue");
        // Class myClass = dataMap.getClass()
        // Map myMap = dataMap.getWrappedMap();

        System.err.println(format.format(now)
                + " Instance " + key
                + " of DumbJob says: " + jobSays
                + ", and val is: " + myFloatValue);
    }
}
