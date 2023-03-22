package com.nms.action;
import org.json.simple.JSONObject;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.FileWriter;

import java.io.File;
import java.util.Arrays;


class QuartzScheduler
{
    public static void main(String[] args)
    {
        try
        {
            //Set job details.
            JobDetail job = JobBuilder.newJob(PingEvery5Minutes.class)

                    .withIdentity("PingEvery5Minutes").build();


            //Set the scheduler timings.
            Trigger trigger = TriggerBuilder.newTrigger()

                    .withIdentity("cronTrigger")

                    .withSchedule(CronScheduleBuilder

                            .cronSchedule("0 */5 * ? * *")).build();



            //Execute the job.
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();

            scheduler.start();

            scheduler.scheduleJob(job, trigger);
//            String maxRTT = DataBaseConnection.TOP5MaxRTT();
//
//            String minRTT = DataBaseConnection.TOP5MinRTT();
//
//            int[] upDownCount = DataBaseConnection.UpDownCount();
//
//            String ipStatus = DataBaseConnection.IPStatus();
//
//            JSONObject jsonObject = new JSONObject();
//
//            jsonObject.put("MaxRtt", maxRTT);
//
//            jsonObject.put("MinRtt", minRTT);
//
//            jsonObject.put("Ipstatus", ipStatus);
//
//            jsonObject.put("UpCount", upDownCount[0]);
//
//            jsonObject.put("DownCount", upDownCount[1]);
//
//            try
//            {
//                FileWriter file = new FileWriter("/root/IdeaProjects/MainProject/src/main/resources/data.json");
//
//                file.write(jsonObject.toJSONString());
//
//                file.close();
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//            System.out.println(maxRTT);
//
//            System.out.println(minRTT);
//
//            System.out.println(Arrays.toString(upDownCount));
//
//            System.out.println(ipStatus);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}