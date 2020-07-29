package com.dwh.payweb.Service.Imp;

import com.dwh.payweb.Response.entity.PayList;
import com.dwh.payweb.Response.entity.PayResponse;
import com.dwh.payweb.Service.CheckPayService;
import com.dwh.payweb.mapper.PayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckPayServiceImpl implements CheckPayService {
    @Autowired
    PayMapper payMapper;

    @Override
    public PayList getPayList() {
        List<PayResponse> payResponses = payMapper.selectAllPay();
        for(PayResponse pay:payResponses){
            String[] goods = pay.getGoodslist().split("\\|");
            String[] numbers = pay.getNumlist().split("\\|");
            StringBuffer res = new StringBuffer();
            for(int i = 0;i<goods.length;i++){
                res.append(goods[i]+"x"+numbers[i]);
                if(i!=goods.length-1) res.append(",");
            }
            pay.setPaylist(res.toString());
        }
        PayList payList = new PayList();
        payList.setCode(0);
        payList.setCount(payResponses.size());
        payList.setMsg("");
        payList.setData(payResponses);
        return payList;
    }
}
