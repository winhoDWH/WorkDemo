package com.suntek.exportdemo.service;

import com.suntek.exportdemo.entity.ImgEntity;

/**
 * 导出类
 * @author: dwh
 * @DATE: 2020/9/25
 */
public interface ExportService {
    /**
     * 导出照片类
     * @param data
     */
    void exportImg(ImgEntity data);
}
