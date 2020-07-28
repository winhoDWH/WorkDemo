package com.dwh.payweb.Controller;

import com.alibaba.fastjson.JSON;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.dwh.config.Constants;
import com.dwh.model.builder.AlipayTradeCancelRequestBuilder;
import com.dwh.model.builder.AlipayTradeQueryRequestBuilder;
import com.dwh.payweb.Request.tradeprecreate;
import com.dwh.payweb.Response.PayResponse;
import com.dwh.payweb.Service.TradeService;
import com.dwh.payweb.common.Constants_dwh;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 */
//@RestController
@Controller
@RequestMapping("/trade")
@Slf4j
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * 预订单，创建二维码
     *
     * @return url
     */
    @RequestMapping("/precreate")
    @ResponseBody
    public String precreate(tradeprecreate requst) {
        return JSON.toJSONString(tradeService.trade_precreate(requst));
    }

    /**
     * 异步撤销订单
     *
     * @param id
     * @return
     */
    @RequestMapping("/cancel")
    public String cancel(String id) {
        log.info("id:"+id);
        return tradeService.trade_cancel(id);
    }

    /**
     * 查询订单状态
     * @param id
     * @return
     */
    @RequestMapping("/queresult")
    @ResponseBody
    public Map queresult(String id) {
        log.info("id:"+id);
        return tradeService.trade_query(id);
    }
}
