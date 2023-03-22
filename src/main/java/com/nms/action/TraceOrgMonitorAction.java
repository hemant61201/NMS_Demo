package com.nms.action;

import com.opensymphony.xwork2.ActionSupport;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Objects;


public class TraceOrgMonitorAction extends ActionSupport
{
    private String name;
    private String ip;
    private String type;

    public HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    private HashMap<String, Object> result = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String addMonitor()
    {
        System.out.println("Hello World");

        System.out.printf(getIp() + " "+ getName()+ " "+ getType());

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                DataBaseDump.DBDumpIP(getIp());
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                DataBaseConnection.DBDataFetch();
            }
        });
        thread1.start();

//        result.put("Top5MaxRTT", DataBaseConnection.TOP5MaxRTT());
//
//        result.put("Top5MinRTT", DataBaseConnection.TOP5MinRTT());
//
//        result.put("IpStatus",DataBaseConnection.IPStatus());
//
//        result.put("UpDownCount", DataBaseConnection.UpDownCount());

        return "SUCCESS";
    }

}
