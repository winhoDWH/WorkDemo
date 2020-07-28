package com.dwh.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sort {
    private static final Logger logger = LoggerFactory.getLogger(Sort.class);

    //插入排序
    /*
     *key是流水号
     *value是{通行单类型：通行单内容}
     */
        /*Map<String,String> value;
        List<String> times = new ArrayList<>();  //时间
        List<String> tollintervalid = new ArrayList<>();    //收费单元
        List<String> tacs = new ArrayList<>(); //TAC
        List<String> ids = new ArrayList<>(); //通行单流水号
        List<String> vehiclesignids = new ArrayList<>(); //车牌识别流水号
        List<String> ganids = new ArrayList<>(); //门架交易单编号
        for(String key:map.keySet()){
            if(key.equals("exPass"))continue;
            value = (Map<String, String>) map.get(key);
            String[] tolls = value.get("tollintervalid").split("\\|");
            for (String t:tolls){
                tollintervalid.add(t);
                times.add(value.get("time"));
                tacs.add(value.containsKey("TAC")?value.get("TAC"):"0");
                ids.add(value.containsKey("id")?value.get("id"):"0");
                vehiclesignids.add(key);
                ganids.add(value.containsKey("ganid")?value.get("ganid"):"0");
            }
        }
        String timetemp;
        String toll;
        String tac;
        String id;
        String vehiclesignid;
        String ganid;
        int i,j;
        for (i = 1;i<times.size();i++) {
            timetemp = times.get(i);
            toll = tollintervalid.get(i);
            tac = tacs.get(i);
            id = ids.get(i);
            vehiclesignid = vehiclesignids.get(i);
            ganid = ganids.get(i);
            for (j = i - 1; j >= 0 && comparedata(times.get(j), timetemp); j--) {
                times.set(j + 1, times.get(j));
                tollintervalid.set(j + 1, tollintervalid.get(j));
                tacs.set(j + 1, tacs.get(j));
                ids.set(j + 1, ids.get(j));
                vehiclesignids.set(j + 1, vehiclesignids.get(j));
                ganids.set(j + 1, ganids.get(j));
            }
            times.set(j + 1, timetemp);
            tollintervalid.set(j + 1, toll);
            tacs.set(j + 1, tac);
            ids.set(j + 1, id);
            vehiclesignids.set(j + 1, vehiclesignid);
            ganids.set(j + 1, ganid);
        }
        JSONArray resultvalue = new JSONArray();
        for(int k = 0;k<tollintervalid.size();k++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time",times.get(k));
            jsonObject.put("TAC",tacs.get(k));
            jsonObject.put("id",ids.get(k));
            jsonObject.put("vehiclesignid",vehiclesignids.get(k));
            jsonObject.put("ganid",ganids.get(k));
            resultvalue.add(jsonObject);
        }*/

       /* Map<String,Object> result = new HashMap<>();
        result.put("tollintervalid",tollintervalid);
        result.put("value",resultvalue);
        return result;*/


    /*
     * 解析redis数据,数据是乱的
     * 获取车牌识别流水号,计费单元，费率版本，流水号，时间,TAC
     */
    public static Map<String,Object> Resolve(Map<String,String> data) throws Exception {
        String type;
        Map<String,Object> map = new HashMap<>();
        Iterator<String> keys;
        JSONObject content = new JSONObject();

        if(data.size()==0||data==null){
            throw new Exception("无解析数据");
        }
        //遍历redis中的map
        for (String key : data.keySet()){
            Map<String,String> value;
            String s = data.get(key);
            JSONObject jsonObject = JSON.parseObject(s);
            keys = jsonObject.keySet().iterator();
            while(keys.hasNext()){
                //获取after的通行单类型
                type = keys.next();
                logger.info(type);
                //获取after的通行单内容json
                content= JSON.parseObject(jsonObject.getString(type));
                //根据type来获取时间,费率，(车牌识别流水号,计费单元等数据信息)
                //车牌识别
                if(type.equals("vehIdinfo")){
                    //没有相同的门架与车牌识别的情况 change by dengw
                    if (!map.containsKey(content.getString("id"))){
                        value = new HashMap<>();
                        map.put(content.getString("id"),value);
                        //通行单流水号
                        value.put("id",key);
                        //时间
                        value.put("time",content.getString("passtime"));
                        //收费单元
                        value.put("tollintervalid",content.getString("tollintervalid"));
                    }
                }
                //门架
                else if(type.equals("gantryPassData")){
                    //没有相同的门架与车牌识别的情况 change by dengw
                    if (!map.containsKey(content.getString("vehiclesignid"))){
                        value = new HashMap<>();
                        map.put(content.getString("vehiclesignid"),value);
                        //通行单流水号
                        value.put("id",key);
                        //时间
                        value.put("time",content.getString("transtime"));
                        //收费单元
                        value.put("tollintervalid",content.getString("tollintervalid"));
                        //TAC
                        value.put("TAC",content.getString("tac"));
                        //门架交易编号
                        value.put("ganid",content.getString("id"));
                    }
                    //有相同
                    else{
                        value = (Map<String, String>) map.get(content.getString("vehiclesignid"));
                        //通行单流水号
                        value.put("id",key);
                        //TAC
                        value.put("TAC",content.getString("tac"));
                        //门架交易编号
                        value.put("ganid",content.getString("id"));
                    }
                }
                //入口站数据
                else if (type.equals("enPass")){
                    //change by dengw
                    value = new HashMap<>();
                    map.put(content.getString("vehiclesignid"),value);
                    //通行单流水号
                    value.put("id",key);
                    //时间
                    value.put("time",content.getString("entime"));
                    //收费单元
                    value.put("tollintervalid",content.getString("enstaitonid"));
                }
                //出口站数据
                else if(type.equals("exPass")||type.equals("exETCTrans")||type.equals("OtherTrans")){
                    //change by dengw
                    value = new HashMap<>();
                    map.put(content.getString("vehiclesignid"),value);
                    //通行单流水号
                    value.put("id",key);
                    //时间
                    value.put("time",content.getString("extime"));
                    //收费单元
                    value.put("tollintervalid",content.getString("exstaitonid"));
                    //用作校验接口输入的出口站信息与redis是否一致
                    map.put("exPass",content.getString("exstaitonid"));
                }
                else throw new Exception("通行单类型错误，通行单流水号："+key+",通行单类型："+type);
            }
        }

        if(map.size()==0||map==null){
            throw new Exception("解析错误");
        }
        return map;
    }

    //比较日期大小,fistdata>seconddata(f比s晚)返回true
    public static boolean comparedata(String firstdata,String seconddata) throws ParseException {
        String DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(DATA_FORMAT);
        Date data1 = format.parse(firstdata);
        Date data2 = format.parse(seconddata);
        long digitaldata1 = data1.getTime();
        long digitaldata2 = data2.getTime();
        if (digitaldata1>digitaldata2){
            return true;
        }
        return false;
    }
}
