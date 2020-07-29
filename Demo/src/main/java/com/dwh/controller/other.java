package com.dwh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class other {

    private static final Logger logger = LoggerFactory.getLogger(other.class);

    //插入排序

     /**key是流水号
     *value是{通行单类型：通行单内容}*/

    public List<String> sortList(Map<String,Object>map) throws Exception {
        if (map.size()==0){
            throw new Exception("输入排序的数据有误");
        }
        int i,j;
        String timetemp,idtemp;
        List<String> tollntervalid = new ArrayList<>();
        List<String> time = new ArrayList<>();
        for (String keys:map.keySet()){
            Map<String,String> value = (Map<String, String>) map.get(keys);
            tollntervalid.add(keys);
            time.add(value.get("time"));
        }
        for (i = 1;i<time.size();i++){
            timetemp = time.get(i);
            idtemp = tollntervalid.get(i);
            for (j = i-1;j>=0 && comparedata(time.get(j),timetemp);j--){
                time.set(j+1,time.get(j));
                tollntervalid.set(j+1,tollntervalid.get(j));
            }
            time.set(j+1,timetemp);
            tollntervalid.set(j+1,idtemp);
        }
        return tollntervalid;
    }


   /*  * 解析redis数据,数据是乱的
     * 获取车牌识别流水号,计费单元，费率版本，流水号，时间,TAC*/

    public Map<String,Object> Resolve(Map<String,String> data) throws Exception {
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
                //根据type来获取时间,费率，(车牌识别流水号,计费单元)
                //车牌识别
                if(type.equals("vehIdinfo")){
                    String[] tollid = content.getString("tollintervalid").split("\\|");
                    if(!map.containsKey(tollid[0])){
                        value = new HashMap<>();
                        //通行单流水号
                        value.put("id",key);
                        //时间
                        value.put("time",content.getString("passtime"));
                        //车牌识别流水号
                        value.put("vehIdinfotd",content.getString("id"));
                        //车牌识别（车尾）
                        value.put("tailVehicleId",content.getString("tailvehicleid"));
                        for (String id :tollid){
                            logger.info("车牌识别编码："+id);
                            map.put(id,value);
                        }
                    }
                    else {
                        for (String id :tollid){
                            logger.info("车牌识别编码："+id);
                            value = (Map<String, String>)map.get(id);
                            value.put("tailVehicleId",content.getString("tailvehicleid"));
                        }
                    }
                }
                //门架
                else if(type.equals("gantryPassData")){
                    String[] tollid = content.getString("tollintervalid").split("\\|");
                    for (String id :tollid){
                        logger.info("门架编码："+id);
                        if (map.containsKey(id)){
                            value = (Map<String, String>)map.get(id);
                            value.put("TAC",content.getString("tac"));
                            //添加计费交易编号
                            value.put("tollintervalid",content.getString("id"));
                        }else {
                            value = new HashMap<>();
                            //通行单流水号
                            value.put("id",key);
                            //时间
                            value.put("time",content.getString("transtime"));
                            //车牌识别流水号
                            value.put("vehIdinfoId",content.getString("vehiclesignid"));
                            //TAC
                            value.put("TAC",content.getString("tac"));
                            //添加计费交易编号
                            value.put("tollintervalid",content.getString("id"));
                        }
                        map.remove(id);
                        map.put(id,value);
                    }
                }
                //入口站数据
                else if (type.equals("enPass")){
                    value = new HashMap<>();
                    logger.info("入口站编码："+ content.getString("enstaitonid"));
                    map.put( content.getString("enstaitonid"),value);
                    //通行单流水号
                    value.put("id",key);
                    //时间
                    value.put("time",content.getString("entime"));
                    //车牌识别流水号
                    value.put("vehIdinfoId",content.getString("vehiclesignid"));
                    //TAC
                    value.put("TAC",content.getString("tac"));
                }
                //出口站数据
                else if(type.equals("exPass")||type.equals("exETCTrans")||type.equals("OtherTrans")){
                    value = new HashMap<>();
                    logger.info("出口站编码："+content.getString("exstaitonid"));
                    map.put(content.getString("exstaitonid"),value);
                    //通行单流水号
                    value.put("id",key);
                    //时间
                    value.put("time",content.getString("extime"));
                    //车牌识别流水号
                    value.put("vehIdinfoId",content.getString("vehiclesignid"));
                    //用于校验接口传入的是否正确
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
    public boolean comparedata(String firstdata,String seconddata) throws ParseException {
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
