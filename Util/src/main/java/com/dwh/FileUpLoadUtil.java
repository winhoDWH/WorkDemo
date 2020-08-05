package com.dwh;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传工具类
 * 作用是保存
 * @author: dwh
 * @DATE: 2020/8/4
 */
public class FileUpLoadUtil {
    /**
     * 解析请求中的信息，并将其转换为FileItem列表
     * @param request httpServletRequest请求
     * @throws FileUploadException
     */
    public static List<FileItem> initFileUpload(HttpServletRequest request) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取servlet的公共文件区域
        File repository = (File) request.getServletContext().getAttribute("javax.servlet.context.tempdir");
        //设置内存缓冲区大小
        factory.setSizeThreshold(102400);
        factory.setRepository(repository);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        return fileUpload.parseRequest(request);
    }

    /**
     * 保存上传文件并返回请求中的参数
     * @param request http请求
     * @param savePath 保存路径
     * @param saveFileName 保存文件名
     * @throws FileUploadException
     */
    public static Map<String,String> saveFile(HttpServletRequest request, String savePath, String saveFileName) throws Exception {
        Map<String,String> fieldValMap = new HashMap<String,String>(1);
        //解析请求
        List<FileItem> items = initFileUpload(request);
        for(FileItem item : items) {
            if (item.isFormField()) {
                //表单数据，前端附加在上传文件请求中的额外参数
                //fieldName为参数名，item.getString()即参数值
                fieldValMap.put(item.getFieldName(),item.getString());
            }else {
                //上传文件
                String fileUploadName = item.getName();
                File dir = new File(savePath);
                File f = new File(dir, saveFileName);

                if(f.exists()) {
                    f.delete();
                }
                f.createNewFile();

                //保存
                item.write(f);
                fieldValMap.put("fileUploadName", item.getName());
                fieldValMap.put("FILE_NAME", saveFileName);
            }
        }
        return fieldValMap;
    }

}
