package payweb.Runner;

import com.alipay.api.response.AlipayTradeQueryResponse;

import com.dwh.config.Constants;
import com.dwh.model.builder.AlipayTradeCancelRequestBuilder;
import com.dwh.model.builder.AlipayTradeQueryRequestBuilder;
import com.dwh.payweb.Config.PaydemoConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class LoopQueRunner implements Runnable {
    private String outTradeNo;

    @Autowired
    private PaydemoConfig paydemoConfig;

    @Override
    public void run() {
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder().setOutTradeNo(outTradeNo);
        AlipayTradeQueryResponse response = paydemoConfig.tradeService.loopQueryResult(builder);
        //判断轮询结果
        if (Constants.SUCCESS.equals(response.getCode())) {
            //查询成功
            if ("TRADE_FINISHED".equals(response.getTradeStatus()) ||
                    "TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                    "TRADE_CLOSED".equals(response.getTradeStatus())) {
                // 如果查询到交易成功、交易结束、交易关闭，则返回对应结果
                return;
            }
        }
        else {
            //查询失败,异步取消，网络问题怎么解决？
            AlipayTradeCancelRequestBuilder builder_1 = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
            paydemoConfig.tradeService.asyncCancel(builder_1);
        }
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
