package com.example.practicalwork.service.Impl;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 *
 * @author lfy
 *
 */
public class Msg {
    //状态码   100-成功    200-失败
    private int code;
    //提示信息
    private String msg;

    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功！");
        return result;
    }

    public static Msg fail(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败！");
        return result;
    }

    public void besuccess(){
        this.setCode(100);
        this.setMsg("处理成功！");
    }

    public void befail(){
        this.setCode(200);
        this.setMsg("处理失败！");
    }

    public Msg add(String key,Object value){
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public String extendToString(){
        String s="{";
        int i=0;
        for (Map.Entry<String, Object> entry : this.extend.entrySet()) {
            String mapKey = entry.getKey();
            String mapValue = (String)entry.getValue();
            s+="\""+mapKey+ "\""+ ":" + mapValue;
            i++;
            if(i<this.extend.entrySet().size()){
                s+=",";
            }
        }
        s+="}";
        return s;
    }

    public String simpleToString(){
        return "{\"code\":"+this.code+"," +
                "\"msg\":\""+ this.msg +"\"," +
                "\"extend\":"+ extendToString() +"" +
                "}";
    }
}
