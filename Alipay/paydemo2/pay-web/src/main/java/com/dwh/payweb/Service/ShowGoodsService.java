package com.dwh.payweb.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dwh.payweb.entity.Goods;
import com.dwh.payweb.mapper.GoodsMapper;
import com.dwh.payweb.mapper.Goods_proMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShowGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private Goods_proMapper goods_proMapper;

    public Map showdata(){
        List<Goods> goods = goodsMapper.selectAll();
        Map map = new HashMap();
        if (goods.size()>0){
            JSONArray datas = new JSONArray();
            map.put("code","0");
            map.put("msg","success");
            map.put("count",goods.size());
            for (Goods g:goods){
                Map data = new HashMap();
                data.put("gid",g.getGid());
                data.put("gname",g.getGname());
                data.put("price",g.getPrice());
                data.put("pArea",goods_proMapper.selectParebyGid(g.getGid()).toString());
                data.put("version",g.getVersion());
                datas.add(JSON.toJSON(data));
                log.info(JSON.toJSONString(data));
            }
            map.put("data",datas);
            log.info(datas.toString());
        }
        else {
            map.put("code","-1");
            map.put("msg","库中暂无商品信息");
        }
        log.info(map.toString());
        return map;
    }
}
