package com.dwh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwh.UserDemo.HttpRequestUtils;
import com.dwh.service.Sort;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.*;

public class test {
   /* private Sort sort;
    public String billing(@RequestBody String req) {
        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");//设置日期格式
            String proRequestTime = df.format(new Date());// new Date()为获取当前系统时间
            //解析接口数据,主要获取passId
            JSONObject reqjson = JSONObject.parseObject(req);
            String passId = reqjson.getString("passId");
            String enStationId = reqjson.getString("enStationId");
            String enTime = reqjson.getString("enTime");
            String enVehicleId = reqjson.getString("enVehicleId");
            String enVehicleClass = reqjson.getString("enVehicleClass");
            String exStationId = reqjson.getString("exStationId");
            String exTime = reqjson.getString("exTime");
            String exVehicleId = reqjson.getString("exVehicleId");
            String exVehicleType = reqjson.getString("exVehicleType");
            String exVehicleClass = reqjson.getString("exVehicleClass");
            String requestTime = reqjson.getString("requestTime");
            String payType = reqjson.getString("paytype");
            String multiProvinceSign = reqjson.getString("multiProvinceSign");


            //取redis里面数据，redis数据格式：map<流水号，通行单JSON>
            Map<String, String> data =new HashMap<>();*//*= pathOperate.getPassIdPath(passId, "1");
            if (data.size() == 0 || data == null) {
                throw new Exception("无通行数据");
            }*//*
            data.put("00","11");


            //解析通行单，map：（计费单元，相关信息）
            Map<String, Object> map = new HashMap<>();
            map = sort.Resolve(data);

            //出口数据，判断redis里面有没有，没有就按接口写入的
            if (map.containsKey("exPass")){
                if (!map.containsKey(exStationId)){
                    //接口的出口站编号与redis中不一样
                    throw new Exception("接口传入的出口站数据有误，redis中存的出口站编号为："+(String)map.get("exPass"));
                }
            }
            else{
                Map<String, String> resultvalue = new HashMap<>();
                resultvalue.put("time", exTime);
                resultvalue.put("tollintervalid",exStationId);
                map.put(exVehicleId, resultvalue);
            }

            //按照时间排序，收费单元序列为List<String>
            //change by dengwh
            List<String> tolllist = new ArrayList<>();
            JSONArray value = new JSONArray();
            Map<String,Object> sortlist =  sort.sortList(map);
            tolllist = (List<String>) sortlist.get("tollintervalid");
            value = (JSONArray) sortlist.get("value");


            //调用路径拟合服务
            JSONArray inpath = new JSONArray();
            for (int i = 0; i < tolllist.size(); i++) {
                inpath.add(tolllist.get(i));
            }
            JSONObject param = new JSONObject();
            //miles=1000000000000;
            param.put("passId", passId);
            param.put("path", inpath);
            param.put("version", tolllist.size());

            JSONObject execute = HttpRequestUtils.execute("http://localhost:8830/fitting/passage", param);
            if (!"0000".equals(execute.getString("code"))) {
                throw new Exception("调用服务失败" + execute.getString("message"));
            }

            //获取返回路径，区分拟合路径新增节点，然后计费与总里程数
            //计费需要对费率表进行比对得相关费用然后相加
            //总里程数就是对拟合返回的结果中Mile值进行相加
            JSONArray outPaths = new JSONArray();
            Long outpathmiles = (long) 0;
            Long fees = (long) 0;
            List<String> chargefeeGroup = new ArrayList<>();//计费收费单元实际收费金额组合
            outPaths = execute.getJSONArray("outPath");
            List<String> outPath = new ArrayList<>();
            //把拟合后的数据，去掉出口站和入口站数据，所以从1到size-1
            for (int j = 1; j < outPaths.size()-1; j++) {
                outPath.add(outPaths.getJSONObject(j).getString("nodeId"));
                Long fee = PriovincePrice.noderelationfee.get(outPath.get(j));
                if (fee != null) {
                    chargefeeGroup.add(fee.toString());
                    fees += fee;
                } else {
                    chargefeeGroup.add("0");
                }
                if (outPaths.getJSONObject(j).containsKey("miles")) {
                    Long outPathMile = outPaths.getJSONObject(j).getLong("miles");
                    if (outPathMile != null)
                        outpathmiles += outPathMile;
                }
            }

            //判断是否需要请求外省计费接口
            if (multiProvinceSign!=null) {
                if (multiProvinceSign.equals("2")){}
                //调用外省计费接口
            }

            //根据接口返回与自己计费结果写返回报文,出口和入口的通行单的流水号不需要
            JSONObject resq = new JSONObject();
            JSONArray provinceFees = new JSONArray();
            JSONObject provinceFee = new JSONObject();
            JSONArray datasheeytIdGroups = new JSONArray();

            resq.put("code","0000");
            resq.put("message","成功");
            resq.put("passId", passId);
            resq.put("enStationId", enStationId);
            resq.put("enTim", enTime);
            resq.put("exStationId", exStationId);
            resq.put("exTime", exTime);
            resq.put("vehicleId", exVehicleId);
            resq.put("vehicleType", exVehicleType);
            resq.put("vehicleClass", exVehicleClass);
            resq.put("payType", payType);
            resq.put("chargingIN", "101");
            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            resq.put("chargingSN",uuid);//唯一流水号
            resq.put("proServerVersion", "1.0");
            resq.put("MultiPriceVersion", "1.0");
            resq.put("PriceVersionGroup", "1.0");
        *//*resq.put("minServerVersion",);
        resq.put("minRequestTime",);
        resq.put("minResponseTime",);*//*
            resq.put("proRequestTime", proRequestTime);
            String proResponseTime = df.format(new Date());
            resq.put("proResponseTime", proResponseTime);
            resq.put("tollfee", fees.toString());
            resq.put("tollmileage", outpathmiles.toString());
            resq.put("provinceFees", provinceFees);
            resq.put("Spare1", "");
            resq.put("Spare2", "");
            resq.put("Spare3", "");
            resq.put("Spare4", "");
            resq.put("Spare5", "");

            //暂时不考虑跨省
            provinceFees.add(provinceFee);
            provinceFee.put("provinceId","");
            provinceFee.put("provinceNo","");
            provinceFee.put("provinceTotal", outPath.size());
            provinceFee.put("tollSupport", "1");
            provinceFee.put("tollSupportDetails", "11");
            provinceFee.put("tollfee", fees.toString());
            provinceFee.put("tollIntervalsCount", String.valueOf(outPath.size()));
            provinceFee.put("oriintervalsGount", String.valueOf(outPath.size()));
            provinceFee.put("oriintervalsGroup", String.join("|", outPath));
            provinceFee.put("tollIntervalsGroup", String.join("|", outPath));

            provinceFee.put("datasheeytIdGroup", datasheeytIdGroups);

            String path;
            List<String> tollIntervalsTag = new ArrayList<>();//计费单元类型识别
            List<String> transTimeGroup = new ArrayList<>();//计费收费单元的时间组合
            List<String> tollIntervalsTypeGroup = new ArrayList<>();//收费单元计费种类组合串
            List<String> tollIntervaltoVehIdgroup = new ArrayList<>();//收费单元车牌识别流水号串
           // List<String> tollIntervaltoTailImagegroup = new ArrayList<>();//收费单元车牌识别图像（车尾）流水号串
            List<String> chargePriceVersionGroup = new ArrayList<>();//收费单元费率版本号组合
            List<String> tollIntervaltoGantrypassIDGroup = new ArrayList<>();//收费单元计费扣费编号串
            String enStationVehId = "";

            //change by dengwh
            //用原数据进行遍历，去掉出口与入口数据
            int j=0;
            for (int i=1;i<tolllist.size()-1;i++){
                //拟合数据
                while (!tolllist.get(i).equals(outPath.get(j))){
                    tollIntervalsTag.add("1");
                    transTimeGroup.add("0");
                    tollIntervalsTypeGroup.add("3");
                    tollIntervaltoVehIdgroup.add("0");
                    chargePriceVersionGroup.add("1.0");
                    tollIntervaltoGantrypassIDGroup.add("0");
                    JSONObject datasheeytIdGroup = new JSONObject();
                    datasheeytIdGroup.put("gantryPassDataId","");//门架通行单流水号
                    datasheeytIdGroup.put("tollintervalId",outPath.get(j));
                    datasheeytIdGroup.put("vehIdinfoId","");//车牌识别流水号
                    datasheeytIdGroups.add(datasheeytIdGroup);
                    j++;
                }
                //原数据
                JSONObject jsonObject = new JSONObject();
                jsonObject = value.getJSONObject(i);
                tollIntervalsTag.add(jsonObject.getString("TAC").equals("0")?"0":"2");
                transTimeGroup.add(jsonObject.getString("time"));
                tollIntervalsTypeGroup.add("1");
                tollIntervaltoVehIdgroup.add(jsonObject.getString("vehiclesignid"));
                chargePriceVersionGroup.add("1.0");
                tollIntervaltoGantrypassIDGroup.add(jsonObject.getString("ganid").equals("0")?"0":jsonObject.getString("ganid"));
                JSONObject datasheeytIdGroup = new JSONObject();
                datasheeytIdGroup.put("gantryPassDataId",jsonObject.getString("ganid").equals("0")?"":jsonObject.getString("ganid"));//门架通行单流水号
                datasheeytIdGroup.put("tollintervalId",outPath.get(j));
                datasheeytIdGroup.put("vehIdinfoId",jsonObject.getString("vehiclesignid"));//车牌识别流水号
                datasheeytIdGroups.add(datasheeytIdGroup);
            }

            provinceFee.put("tollIntervalsTag", String.join("|", tollIntervalsTag));
            provinceFee.put("transTimeGroup", String.join("|", transTimeGroup));
            provinceFee.put("chargefeeGroup", String.join("|", chargefeeGroup));
            provinceFee.put("chargeDiscountGroup", "");
            provinceFee.put("chargeDiscountDetailsGroup", "");

            provinceFee.put("tollIntervalsTypeGroup", String.join("|", tollIntervalsTypeGroup));
            provinceFee.put("chargePriceVersionGroup", "1.0");
            provinceFee.put("tollIntervaltoVehIdgroup", String.join("|", tollIntervaltoVehIdgroup));
            provinceFee.put("tollIntervaltoGantrypassIDGroup", String.join("|", tollIntervaltoGantrypassIDGroup));
            provinceFee.put("tollIntervaltoImagegroup","");
            provinceFee.put("tollIntervaltoTailImagegroup","");

            provinceFee.put("enStationVehId", enStationVehId);
            provinceFee.put("enStationImage", "");
            provinceFee.put("enStationTailImage", "");


            provinceFee.put("Spare1", "");
            provinceFee.put("Spare2", "");
            provinceFee.put("Spare3", "");
            provinceFee.put("Spare4", "");
            provinceFee.put("Spare5", "");


            return resq.toString();
        } catch (Exception e) {
            JSONObject resq = new JSONObject();
            resq.put("code", "9999");
            resq.put("fo", "失败");
            resq.put("exception",e.getMessage());

            return resq.toString();
        }

    }*/
}
