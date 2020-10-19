package com.suntek.exportdemo.util;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.suntek.exportdemo.config.TaskStatusManager;
import com.suntek.exportdemo.entity.ImgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Date;

/**
 * 下载工具类
 * @author: dwh
 * @DATE: 2020/10/19
 */
@Slf4j
public class DownloadUtil {

    /**超时时间，应该取配置文件中，需进一步设计**/
    private static int outTime;

    public static void downImage(ImgEntity data){
        try {
            HttpUtil.downloadFile(data.getImageUrl(),
                    FileUtils.getFile(new File(data.getFilePath()), data.getFileName() + "." + data.getFileType()),
                    outTime);
            TaskStatusManager.getInstance().getTaskStatusModule().incrementSuccessNum();
        } catch (Exception e) {
            /**下载失败的情况处理**/
            log.error(data.getErrorMessage(), e);
            TaskStatusManager.getInstance().getTaskStatusModule().incrementFailedNum();
            TaskStatusManager.getInstance().getTaskStatusModule().putErrorMessage(data.getErrorKey(), data.getErrorMessage());
        } finally {
            long finished = TaskStatusManager.getInstance().getTaskStatusModule().incrementFinishedNum();
            if (finished == TaskStatusManager.getInstance().getTaskStatusModule().getTotalNum()) {
                TaskStatusManager.getInstance().getTaskStatusModule().setEndTime(new Date());
                TaskStatusManager.getInstance().getTaskStatusModule().finish();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                /**使用文件记录导入批量数据的状态**/
            }
        }
    }
}
