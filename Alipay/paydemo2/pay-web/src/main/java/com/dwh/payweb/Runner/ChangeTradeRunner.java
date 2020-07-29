package com.dwh.payweb.Runner;

import com.dwh.payweb.entity.Goods_pro;
import com.dwh.payweb.entity.Goods_proKey;
import com.dwh.payweb.entity.Trade;
import com.dwh.payweb.mapper.GoodsMapper;
import com.dwh.payweb.mapper.Goods_proMapper;
import com.dwh.payweb.mapper.TradeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChangeTradeRunner implements Runnable{

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private Goods_proMapper goodsProMapper;

    private String tradeid;
    private String proid = "001";

    @Override
    public void run() {
        log.info("正在修改库存表");
        log.info(tradeid);
        //获取交易单中的商品信息
        Trade trade = tradeMapper.selectByPrimaryKey(tradeid);
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
        log.info(gidlist.toString());
        log.info(numberlist.toString());
        //修改库存表
        for (int i =0;i<gidlist.length;i++){
            log.info(gidlist[i]);
            log.info("number:"+Integer.valueOf(numberlist[i]));
            goodsProMapper.updatenumber(gidlist[i],proid,Integer.valueOf(numberlist[i]));
        }
    }

    public String getTradeid() {
        return tradeid;
    }

    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }
}
