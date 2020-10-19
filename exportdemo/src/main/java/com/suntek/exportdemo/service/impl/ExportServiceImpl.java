package com.suntek.exportdemo.service.impl;

import com.suntek.exportdemo.entity.ImgEntity;
import com.suntek.exportdemo.service.ExportService;
import com.suntek.exportdemo.util.DownloadUtil;
import org.springframework.stereotype.Service;

/**
 * 导出实现类
 * @author: dwh
 * @DATE: 2020/9/25
 */
@Service
public class ExportServiceImpl implements ExportService {
    @Override
    public void exportImg(ImgEntity data) {
        DownloadUtil.downImage(data);
    }
}
