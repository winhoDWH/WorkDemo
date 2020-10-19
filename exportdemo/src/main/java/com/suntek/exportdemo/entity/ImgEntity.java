package com.suntek.exportdemo.entity;

import lombok.Data;

/**
 * 图片导出实体类
 * @author: dwh
 * @DATE: 2020/9/25
 */
@Data
public class ImgEntity {
    /**图片下载地址url**/
    private String imageUrl;
    /**图片下载后名称**/
    private String fileName;
    /**图片下载类型**/
    private String fileType;
    /**图片下载路径**/
    private String filePath;
    /**图片下载错误时抛出给外部的错误信息**/
    private String errorMessage;
    /**图片下载错误时，查找对应错误信息的key**/
    private String errorKey;

}
