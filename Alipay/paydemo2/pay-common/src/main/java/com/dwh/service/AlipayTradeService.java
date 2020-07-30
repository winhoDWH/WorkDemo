package com.dwh.service;

import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.dwh.model.builder.*;
import com.dwh.model.result.AlipayF2FPayResult;
import com.dwh.model.result.AlipayF2FPrecreateResult;
import com.dwh.model.result.AlipayF2FQueryResult;
import com.dwh.model.result.AlipayF2FRefundResult;


/**
 * Created by liuyangkly on 15/7/29.
 */
public interface AlipayTradeService {

    // 当面付2.0流程支付
    AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder);

    // 当面付2.0消费查询
    AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder);

    // 当面付2.0消费退款
    AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder);

    // 当面付2.0预下单(生成二维码)
    AlipayF2FPrecreateResult tradePrecreate(AlipayTradePrecreateRequestBuilder builder);

    //撤销订单
    void asyncCancel(AlipayTradeCancelRequestBuilder builder);

    // 轮询查询订单支付结果
    AlipayTradeQueryResponse loopQueryResult(AlipayTradeQueryRequestBuilder builder);

    // 根据外部订单号outTradeNo撤销订单
    AlipayTradeCancelResponse cancelPayResult(AlipayTradeCancelRequestBuilder builder);
}
