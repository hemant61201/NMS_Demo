package com.nms.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.HashMap;


public class TraceOrgLoadData extends ActionSupport
{
    private HashMap<String,Object> result = new HashMap<>();

    public HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    public String getUIData()
    {
        HashMap<String, Object> output = DataBaseConnection.getUIdatafromDatabase();


        result.put("result",output);

        return SUCCESS;
    }
}
