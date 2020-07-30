package com.dwh.payweb.Request;


import com.dwh.model.GoodsDetail;
import lombok.Data;

import java.util.List;

@Data
public class tradeprecreate {
    private String outTradeNo; // (必填) 商户网站订单系统中唯一订单号
    private String totalAmount; // (必填) 订单总金额
    private String goods;   //商品信息
}
