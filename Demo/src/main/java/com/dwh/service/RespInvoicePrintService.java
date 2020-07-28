package com.dwh.service;

import com.alibaba.fastjson.JSONObject;
import com.dwh.UserDemo.JsonXmlUtils;
import com.dwh.entity.InvalidCommpara;
import com.dwh.mapper.InvalidCommparaMapper;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespInvoicePrintService {
    @Autowired
    private InvalidCommparaMapper invalidCommparaMapper;

    private static Logger log4j=Logger.getLogger(RespInvoicePrintService.class);

    public String resp(String xml) throws DocumentException {
        //解析报文
        JSONObject json = JsonXmlUtils.xmlToJson(xml);
        log4j.info(json.toString());
        JSONObject printv = json.getJSONObject("Envelope").getJSONObject("Body").getJSONObject("PRINT_VAT_INVOICE_INPUT")
                .getJSONObject("UNI_BSS_BODY").getJSONObject("PRINT_VAT_INVOICE_REQ");
        String TAXPAYER_CODE = printv.getString("TAXPAYER_CODE");
        String ORDER_ID = printv.getString("PROVINCE_ORDER_ID");
        log4j.info(TAXPAYER_CODE);
        InvalidCommpara invalidCommpara = invalidCommparaMapper.selectByPrimaryKey("ASM_TAXSIM_"+TAXPAYER_CODE);
        return (invalidCommpara == null)?"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns392:PRINT_VAT_INVOICE_OUTPUT xmlns:ns392=\"http://ws.chinaunicom.cn/InvoicePrintSer/unibssBody\"><ns1:UNI_BSS_HEAD xmlns:ns1=\"http://ws.chinaunicom.cn/unibssHead\"><ns1:ORIG_DOMAIN>GCAC</ns1:ORIG_DOMAIN><ns1:SERVICE_NAME>InvoicePrintSer</ns1:SERVICE_NAME><ns1:OPERATE_NAME>printVATInvoice</ns1:OPERATE_NAME><ns1:ACTION_CODE>1</ns1:ACTION_CODE><ns1:ACTION_RELATION>0</ns1:ACTION_RELATION><ns1:ROUTING><ns1:ROUTE_TYPE>00</ns1:ROUTE_TYPE><ns1:ROUTE_VALUE>52</ns1:ROUTE_VALUE></ns1:ROUTING><ns1:PROC_ID>20190506100151</ns1:PROC_ID><ns1:TRANS_IDO>20190506100151853</ns1:TRANS_IDO><ns1:TRANS_IDH></ns1:TRANS_IDH><ns1:PROCESS_TIME>20190506100151</ns1:PROCESS_TIME><ns1:RESPONSE><ns1:RSP_TYPE>0</ns1:RSP_TYPE><ns1:RSP_CODE>0000</ns1:RSP_CODE><ns1:RSP_DESC>接收成功</ns1:RSP_DESC></ns1:RESPONSE><ns1:COM_BUS_INFO><ns1:OPER_ID>HZHCRTX1</ns1:OPER_ID><ns1:PROVINCE_CODE>51</ns1:PROVINCE_CODE><ns1:EPARCHY_CODE>570</ns1:EPARCHY_CODE><ns1:CITY_CODE>512937</ns1:CITY_CODE><ns1:CHANNEL_ID>51b2cti</ns1:CHANNEL_ID><ns1:CHANNEL_TYPE>1010500</ns1:CHANNEL_TYPE><ns1:ACCESS_TYPE>01</ns1:ACCESS_TYPE><ns1:ORDER_TYPE>01</ns1:ORDER_TYPE></ns1:COM_BUS_INFO><ns1:SP_RESERVE><ns1:TRANS_IDC>201905061001521695381104608101</ns1:TRANS_IDC><ns1:CUTOFFDAY>20190506</ns1:CUTOFFDAY><ns1:OSNDUNS>F400</ns1:OSNDUNS><ns1:HSNDUNS>5200</ns1:HSNDUNS><ns1:CONV_ID>2019050610015185320190506100152532</ns1:CONV_ID></ns1:SP_RESERVE><ns1:TEST_FLAG>0</ns1:TEST_FLAG><ns1:MSG_SENDER>5200</ns1:MSG_SENDER><ns1:MSG_RECEIVER>F400</ns1:MSG_RECEIVER></ns1:UNI_BSS_HEAD><ns392:UNI_BSS_BODY><ns387:PRINT_VAT_INVOICE_RSP xmlns:ns387=\"http://ws.chinaunicom.cn/InvoicePrintSer/unibssBody/printVATInvoiceRsp\"><ns387:RESP_CODE>0000</ns387:RESP_CODE><ns387:RESP_DESC>RECEIVESUCCESS！</ns387:RESP_DESC><ns387:INVOICE_PRINT_RESULT><ns387:ORDER_ID>"+ORDER_ID+"</ns387:ORDER_ID></ns387:INVOICE_PRINT_RESULT></ns387:PRINT_VAT_INVOICE_RSP></ns392:UNI_BSS_BODY><ns2:UNI_BSS_ATTACHED xmlns:ns2=\"http://ws.chinaunicom.cn/unibssAttached\"><ns2:MEDIA_INFO></ns2:MEDIA_INFO></ns2:UNI_BSS_ATTACHED></ns392:PRINT_VAT_INVOICE_OUTPUT></soapenv:Body></soapenv:Envelope>":
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns392:PRINT_VAT_INVOICE_OUTPUT xmlns:ns392=\"http://ws.chinaunicom.cn/InvoicePrintSer/unibssBody\"><ns1:UNI_BSS_HEAD xmlns:ns1=\"http://ws.chinaunicom.cn/unibssHead\"><ns1:ORIG_DOMAIN>GCAC</ns1:ORIG_DOMAIN><ns1:SERVICE_NAME>InvoicePrintSer</ns1:SERVICE_NAME><ns1:OPERATE_NAME>printVATInvoice</ns1:OPERATE_NAME><ns1:ACTION_CODE>1</ns1:ACTION_CODE><ns1:ACTION_RELATION>0</ns1:ACTION_RELATION><ns1:ROUTING><ns1:ROUTE_TYPE>00</ns1:ROUTE_TYPE><ns1:ROUTE_VALUE>52</ns1:ROUTE_VALUE></ns1:ROUTING><ns1:PROC_ID>20190506100151</ns1:PROC_ID><ns1:TRANS_IDO>20190506100151853</ns1:TRANS_IDO><ns1:TRANS_IDH></ns1:TRANS_IDH><ns1:PROCESS_TIME>20190506100151</ns1:PROCESS_TIME><ns1:RESPONSE><ns1:RSP_TYPE>0</ns1:RSP_TYPE><ns1:RSP_CODE>0000</ns1:RSP_CODE><ns1:RSP_DESC>接收成功</ns1:RSP_DESC></ns1:RESPONSE><ns1:COM_BUS_INFO><ns1:OPER_ID>HZHCRTX1</ns1:OPER_ID><ns1:PROVINCE_CODE>51</ns1:PROVINCE_CODE><ns1:EPARCHY_CODE>570</ns1:EPARCHY_CODE><ns1:CITY_CODE>512937</ns1:CITY_CODE><ns1:CHANNEL_ID>51b2cti</ns1:CHANNEL_ID><ns1:CHANNEL_TYPE>1010500</ns1:CHANNEL_TYPE><ns1:ACCESS_TYPE>01</ns1:ACCESS_TYPE><ns1:ORDER_TYPE>01</ns1:ORDER_TYPE></ns1:COM_BUS_INFO><ns1:SP_RESERVE><ns1:TRANS_IDC>201905061001521695381104608101</ns1:TRANS_IDC><ns1:CUTOFFDAY>20190506</ns1:CUTOFFDAY><ns1:OSNDUNS>F400</ns1:OSNDUNS><ns1:HSNDUNS>5200</ns1:HSNDUNS><ns1:CONV_ID>2019050610015185320190506100152532</ns1:CONV_ID></ns1:SP_RESERVE><ns1:TEST_FLAG>0</ns1:TEST_FLAG><ns1:MSG_SENDER>5200</ns1:MSG_SENDER><ns1:MSG_RECEIVER>F400</ns1:MSG_RECEIVER></ns1:UNI_BSS_HEAD><ns392:UNI_BSS_BODY><ns387:PRINT_VAT_INVOICE_RSP xmlns:ns387=\"http://ws.chinaunicom.cn/InvoicePrintSer/unibssBody/printVATInvoiceRsp\"><ns387:RESP_CODE>8888</ns387:RESP_CODE><ns387:RESP_DESC>FAIL!</ns387:RESP_DESC><ns387:INVOICE_PRINT_RESULT><ns387:ORDER_ID>"+ORDER_ID+"</ns387:ORDER_ID></ns387:INVOICE_PRINT_RESULT></ns387:PRINT_VAT_INVOICE_RSP></ns392:UNI_BSS_BODY><ns2:UNI_BSS_ATTACHED xmlns:ns2=\"http://ws.chinaunicom.cn/unibssAttached\"><ns2:MEDIA_INFO></ns2:MEDIA_INFO></ns2:UNI_BSS_ATTACHED></ns392:PRINT_VAT_INVOICE_OUTPUT></soapenv:Body></soapenv:Envelope>";
    }
}
