package com.dwh.payweb.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.dwh.config.Constants;
import com.dwh.model.ExtendParams;
import com.dwh.model.GoodsDetail;
import com.dwh.model.TradeStatus;
import com.dwh.model.builder.AlipayTradeCancelRequestBuilder;
import com.dwh.model.builder.AlipayTradePrecreateRequestBuilder;
import com.dwh.model.builder.AlipayTradeQueryRequestBuilder;
import com.dwh.model.builder.AlipayTradeRefundRequestBuilder;
import com.dwh.model.result.AlipayF2FPrecreateResult;
import com.dwh.model.result.AlipayF2FQueryResult;
import com.dwh.model.result.AlipayF2FRefundResult;
import com.dwh.payweb.Config.PaydemoConfig;
import com.dwh.payweb.Request.tradeprecreate;
import com.dwh.payweb.Runner.ChangeTradeRunner;
import com.dwh.payweb.common.Constants_dwh;
import com.dwh.payweb.entity.Goods_pro;
import com.dwh.payweb.entity.Goods_proKey;
import com.dwh.payweb.entity.Trade;
import com.dwh.payweb.mapper.Goods_proMapper;
import com.dwh.payweb.mapper.TradeMapper;
import com.dwh.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class TradeService {
    @Autowired
    private PaydemoConfig paydemoConfig;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private Goods_proMapper goodsProMapper;

    /*@Autowired
    private ChangeTradeRunner changeTradeRunner;*/

    // 当面付2.0生成支付二维码
    public Map trade_precreate(tradeprecreate requst){
        Map map = new HashMap();

        log.info(requst.getOutTradeNo());
        log.info("amount:"+requst.getTotalAmount());
        log.info("goods:"+requst.getGoods());
        JSONArray jsonArray = JSONArray.parseArray(requst.getGoods());
        log.info("jsonsize:"+jsonArray.size());
        if (jsonArray.size()<1){
            map.put("code", Constants_dwh.FAILED);
            map.put("msg","购物车无数据");
            map.put("url","");
            return map;
        }

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = requst.getOutTradeNo();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "当面付扫码消费";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = requst.getTotalAmount();

        /*// (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";*/

       /* // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";*/

        /*// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "购买商品3件共20.00元";*/

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        /*// 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");*/

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

       /* // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxx小面包", 1000, 1);
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        goodsDetailList.add(goods2);*/

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                /*.setUndiscountableAmount(undiscountableAmount)*//*.setSellerId(sellerId)*//*.setBody(body)*/
                .setOperatorId(operatorId).setStoreId(storeId)/*.setExtendParams(extendParams)*/
                .setTimeoutExpress(timeoutExpress)
                //                .setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                /*.setGoodsDetailList(goodsDetailList)*/;
        AlipayF2FPrecreateResult result = paydemoConfig.tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功:");
                //把订单信息插入数据库中
                Trade trade = new Trade();
                trade.setTradeid(outTradeNo);
                trade.setmPrice(totalAmount);
                trade.setStatus("0");//未完成支付
                JSONObject jsonObject;
                //定义各种序列
                List<String> gids = new ArrayList<>();
                List<String> versions = new ArrayList<>();
                List<String> numbers = new ArrayList<>();
                List<String> dprices = new ArrayList<>();
                List<String> aprices = new ArrayList<>();
                //遍历json串,添加商品信息
                for (int i=0;i<jsonArray.size();i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    gids.add(jsonObject.getString("gid"));
                    versions.add(jsonObject.getString("version"));
                    numbers.add(jsonObject.getString("number"));
                    dprices.add(jsonObject.getString("dprice"));
                    aprices.add(jsonObject.getString("aprice"));
                }
                trade.setGids(String.join("|", gids));
                trade.setVersions(String.join("|", versions));
                trade.setDprices(String.join("|", dprices));
                trade.setAprices(String.join("|", aprices));
                trade.setNumbers(String.join("|", numbers));

                tradeMapper.insertSelective(trade);

                AlipayTradePrecreateResponse response = result.getResponse();

                // 需要修改为运行机器上的路径
                String urlPath = response.getQrCode();
                log.info("filePath:" + urlPath);
                map.put("code", Constants_dwh.SUCCESS);
                map.put("msg","生成二维码成功");
                map.put("url",urlPath);
                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                map.put("code", Constants_dwh.FAILED);
                map.put("msg","支付宝预下单失败!!!请重试");
                map.put("url","");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                map.put("code", Constants_dwh.ERROR);
                map.put("msg","系统异常,请联系商家");
                map.put("url","");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                map.put("code", Constants_dwh.ERROR);
                map.put("msg","系统异常，请联系商家");
                map.put("url","");
                break;
        }
        return  map;
    }

    //当面付2.0查询订单
    public Map trade_query(String tradeid) {

        Map map = new HashMap();

        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
        String outTradeNo = tradeid;

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(outTradeNo);

        AlipayTradeQueryResponse response = paydemoConfig.tradeService.loopQueryResult(builder);

        if(querySuccess(response)){
            //支付成功
            log.info("查询返回该订单支付成功: )");
            Trade trade = new Trade();
            trade.setTradeid(outTradeNo);
            trade.setStatus("1");
            trade.setPaytime(response.getSendPayDate());
            tradeMapper.updateByPrimaryKeySelective(trade);
            //更新库存库
            /*changeTradeRunner.setTradeid(outTradeNo);
            Thread thread =new Thread(changeTradeRunner);
            thread.start();*/
            log.info("正在修改库存表");
            trade = tradeMapper.selectByPrimaryKey(outTradeNo);
            if (trade==null){
                try {
                    throw new Exception("查询交易单失败，交易单id为："+tradeid);
                } catch (Exception e) {
                    log.info("查询交易单失败");
                    e.printStackTrace();
                }
            }
            String gids = trade.getGids();
            String numbers = trade.getNumbers();
            String[] gidlist = gids.split("\\|");
            String[] numberlist = numbers.split("\\|");
            Goods_proKey goodsProKey = new Goods_proKey();
            goodsProKey.setProid("001");
            Goods_pro goodsPro = new Goods_pro();
            for (int i =0;i<gidlist.length;i++){
               goodsProKey.setGid(gidlist[i]);
               goodsPro=goodsProMapper.selectByPrimaryKey(goodsProKey);
               goodsPro.setpArea(goodsPro.getpArea()-Integer.valueOf(numberlist[i]));
               goodsProMapper.updateByPrimaryKeySelective(goodsPro);
            }
            map.put("code", Constants_dwh.SUCCESS);
            map.put("msg","支付成功");
        }else {
            //支付失败
            log.error("查询订单失败，撤销订单");
            AlipayTradeCancelRequestBuilder builder2 = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
            AlipayTradeCancelResponse cancelResponse = paydemoConfig.tradeService.cancelPayResult(builder2);
            tradeMapper.deleteByPrimaryKey(outTradeNo);
            map.put("code", Constants_dwh.FAILED);
            map.put("msg","支付失败");
        }
        /*switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");

                AlipayTradeQueryResponse response = result.getResponse();

                log.info(response.getTradeStatus());
                if (Utils.isListNotEmpty(response.getFundBillList())) {
                    for (TradeFundBill bill : response.getFundBillList()) {
                        log.info(bill.getFundChannel() + ":" + bill.getAmount());
                    }
                }
                Trade trade = new Trade();
                trade.setTradeid(outTradeNo);
                trade.setStatus("1");
                trade.setPaytime(response.getSendPayDate());
                tradeMapper.updateByPrimaryKeySelective(trade);
                map.put("code", Constants_dwh.SUCCESS);
                map.put("msg","支付成功");
                break;

            case FAILED:
                log.error("查询返回该订单支付失败或被关闭!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                log.error("查询订单失败，撤销订单");
                AlipayTradeCancelRequestBuilder builder2 = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
                AlipayTradeCancelResponse cancelResponse = paydemoConfig.tradeService.cancelPayResult(builder2);
                tradeMapper.deleteByPrimaryKey(outTradeNo);
                map.put("code", Constants_dwh.FAILED);
                map.put("msg","支付失败");
                break;
        }*/
        return map;
    }

    //当面付2.0撤销
    public String trade_cancel(String outTradeNo) {
        AlipayTradeCancelRequestBuilder builder = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
        AlipayTradeCancelResponse cancelResponse = paydemoConfig.tradeService.cancelPayResult(builder);
        tradeMapper.deleteByPrimaryKey(outTradeNo);
        return "";
    }

    // 查询返回“支付成功”
    protected boolean querySuccess(AlipayTradeQueryResponse response) {
        return response != null &&
                Constants.SUCCESS.equals(response.getCode()) &&
                ("TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                        "TRADE_FINISHED".equals(response.getTradeStatus())
                );
    }

}
