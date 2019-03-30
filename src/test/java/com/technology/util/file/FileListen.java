package com.technology.util.file;

import com.technology.util.string.Strings;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 监听文件的变化
 */
public class FileListen {

    private FileListen() {
    }

    private static long lastTime;  //在全局 接收这个参数 就不需要声明为final了

    /**
     *
     * @param fileName
     * @param fileListenCallback
     */
    public static void listenFile(String fileName, FileListenCallback fileListenCallback) {
        if (Strings.isNullorEmpty(fileName)) {
            System.err.println("文件名为空,错误");
            return;
        }
         listenFile(new File(fileName),fileListenCallback);
    }


    public static void listenFile(File file, FileListenCallback fileListenCallback) {
        lastTime = file.lastModified();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (file.lastModified() > lastTime) {
                    lastTime = file.lastModified();
                    fileListenCallback.callback();  //执行文件监听之后的 动作
                    System.out.printf("file update! time :%s\n " , LocalDateTime.now());
                }
            }
        }, 0, 1, TimeUnit.SECONDS);  //没隔一秒 调用该定时器

    }


}

