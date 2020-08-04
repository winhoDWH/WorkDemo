package com.dwh;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.HttpRequest;

/**
 * 文件上传工具类
 * @author: dwh
 * @DATE: 2020/8/4
 */
public class FileUpLoadUtil {
    public static ServletFileUpload initFileUpload(HttpRequest request){
        DiskFileItemFactory factory = new DiskFileItemFactory();

    }

    public static void ex(HttpRequest request){

    }

}
