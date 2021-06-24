package com.lee.plcanalysis.core.sps.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * 解析json对象生成Observer对象，作为后续工作的基础
 */
public class Observer {
    private final String name;
    private final List<String> params;
    private final List<String> locations;
    private String code;
    private final int transNum;

    public Observer(String name, String code, int transNum) {
        this.name = name;
        this.params = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.code = code;
        this.transNum = transNum;
    }

    public String getName() {
        return name;
    }

    public List<String> getParam() {
        return params;
    }

    public List<String> getLocations() {
        return locations;
    }

    public String getCode() {
        return code;
    }

    public int getTransNum() {
        return transNum;
    }

    public void setCode(String target, String replaceStr) {
        this.code= this.code.replaceAll(target, replaceStr);
    }

    @Override
    protected Observer clone() throws CloneNotSupportedException {
        Observer newObj = new Observer(this.name, this.code, this.transNum);
        newObj.params.addAll(this.params);
        newObj.locations.addAll(this.locations);
        return newObj;
    }


    /**
     * @param obs 获取obs的json对象
     * @return 返回一个新的Observer对象，代表一个新的观察者模式
     */
    public static Observer generateObserver(JSONObject obs){
        String name = obs.getString("name");
        String code = obs.getString("code");
        int transNum = obs.getInt("transNum");
        Observer newObserver = new Observer(name, code, transNum);

        JSONArray paramsJsonArr = obs.getJSONArray("params");
        for(int i = 0; i < paramsJsonArr.length(); ++i){
            newObserver.addParam(paramsJsonArr.getString(i));
        }

        JSONArray locationsJsonArr = obs.getJSONArray("locations");
        for(int i = 0; i < locationsJsonArr.length(); ++i){
            newObserver.addLocations(locationsJsonArr.getString(i));
        }

        return newObserver;
    }

    public void addParam(String param){
        params.add(param);
    }

    public void addLocations(String loc){
        locations.add(loc);
    }

    @Override
    public String toString() {
        return "Observer{" +
                "name='" + name + '\'' +
                ", params=" + params +
                ", locations=" + locations +
                ", code='" + code + '\'' +
                ", transNum='" + transNum + '\'' +
                '}';
    }
}
