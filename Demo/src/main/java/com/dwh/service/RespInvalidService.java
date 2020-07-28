package com.dwh.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwh.UserDemo.HttpRequestUtils;
import com.dwh.UserDemo.JsonXmlUtils;
import com.dwh.entity.InvalidCommpara;
import com.dwh.mapper.InvalidCommparaMapper;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RespInvalidService {
    private static Logger logger=Logger.getLogger(RespInvalidService.class);

    @Autowired
    private InvalidCommparaMapper invalidCommparaMapper;

    public String changexml(String xml) throws DocumentException {
        logger.info("请求报文："+xml);
        //解析XML报文，并转化为json
        JSONObject json = new JSONObject();
        json = JsonXmlUtils.xmlToJson(xml);
        logger.info("JSON："+json);
        String params = json.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("runRule").getString("params");
        JSONObject param = JSONObject.parseObject(params);
        logger.info("param："+param.toString());
        JSONObject msg = param.getJSONObject("msg");
        String cust_id =param.getJSONObject("msg").getString("custId");
        InvalidCommpara commpara =invalidCommparaMapper.selectByPrimaryKey("ASM_OASIM_" + cust_id);
        if (commpara != null) {
            return "<?xml version=\"1.0\" ?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:runRuleResponse xmlns:ns2=\"http://server.ws.linkey.cn/\"><return>{&quot;result&quot;:{&quot;msg&quot;:&quot;工单发起成功&quot;,&quot;status&quot;:&quot;1&quot;}}</return></ns2:runRuleResponse></S:Body></S:Envelope>";
        } else {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JSONObject reqInvoiceInfo = msg.getJSONArray("invoiceInfo").getJSONObject(0);
            JSONObject reqInvoiceItem = reqInvoiceInfo.getJSONArray("invoiceItem").getJSONObject(0);
            //构建发起报文
            JSONObject reqbody = new JSONObject();
            JSONObject UNI_BSS_BODY = new JSONObject();
            JSONObject resultSynReq= new JSONObject();
            JSONObject invoiceInfo= new JSONObject();
            JSONObject invoiceItem= new JSONObject();
            JSONObject aoItem= new JSONObject();

            JSONArray invoiceInfo_Arry = new JSONArray();
            JSONArray invoiceItem_Arry= new JSONArray();
            JSONArray aoItem_Arry = new JSONArray();

            invoiceInfo_Arry.add(invoiceInfo);
            invoiceItem_Arry.add(invoiceItem);
            aoItem_Arry.add(aoItem);

            invoiceItem.put("invItemIdApp",reqInvoiceItem.getString("invItemId"));
            invoiceItem.put("invItemNameApp",reqInvoiceItem.getString("invItemName"));
            invoiceItem.put("invItemRate",reqInvoiceItem.getString("invItemRate"));
            invoiceItem.put("invItemIdNew",reqInvoiceItem.getString("invItemId"));
            invoiceItem.put("invItemNameNew",reqInvoiceItem.getString("invItemName"));//新生成怎么搞？
            //非必须
            invoiceItem.put("invItemVat",reqInvoiceItem.getString("invItemVat"));
            invoiceItem.put("invItemTax",reqInvoiceItem.getString("invItemTax"));

            invoiceInfo.put("invSeqNo",reqInvoiceInfo.getString("invSeqNo"));
            invoiceInfo.put("invRemark",reqInvoiceInfo.getString("invRemark"));//非必须
            invoiceInfo.put("invoiceItem",invoiceItem);
            //非必须
            Date data = new Date();
            aoItem.put("currentNodeName","审核完毕");
            aoItem.put("nextNodeName","回调");
            aoItem.put("currentPerson","zhangsan");
            aoItem.put("currentDept","renshi");
            aoItem.put("nextPerson","lisi");
            aoItem.put("nextDept","shichang");
            aoItem.put("approvalTime",data.toString());
            aoItem.put("approvalOpinion","无");
            aoItem.put("approvalOpinionDetail","无");

            resultSynReq.put("gcacTradeId",msg.getString("gcacTradeId"));
            resultSynReq.put("invoiceInfo",invoiceInfo_Arry);
            //非必须
            resultSynReq.put("subject","模拟OA");
            resultSynReq.put("flowCode","001");
            resultSynReq.put("appTime",data.toString());
            resultSynReq.put("appPerson",msg.getString("appPerson"));
            resultSynReq.put("appPersonEmail",msg.getString("appPersonEmail"));
            resultSynReq.put("creatorcomname","yaxin");
            resultSynReq.put("creatordept","renshi");
            resultSynReq.put("aoItem",aoItem_Arry);

            UNI_BSS_BODY.put("resultSynReq",resultSynReq);
            reqbody.put("UNI_BSS_BODY",UNI_BSS_BODY);
            JSONObject execute = HttpRequestUtils.execute("http://132.120.2.130:18091/gacClientService/ResultSynSer/resultSyn",reqbody);
            if (!"0000".equals(execute.getString("respCode"))) {
                logger.info("调用失败");
                logger.info(execute.toString());
                logger.info(execute.getString("respCode"));
            }
            return "<?xml version=\"1.0\" ?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:runRuleResponse xmlns:ns2=\"http://server.ws.linkey.cn/\"><return>{&quot;result&quot;:{&quot;msg&quot;:&quot;接口调用错误，有信息为空，请检查&quot;,&quot;status&quot;:&quot;0&quot;}}</return></ns2:runRuleResponse></S:Body></S:Envelope>";
        }
    }

}

