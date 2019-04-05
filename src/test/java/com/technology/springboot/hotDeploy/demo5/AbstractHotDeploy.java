package com.technology.springboot.hotDeploy.demo5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 通用实现类  实现监控
 */
public abstract class AbstractHotDeploy implements HotDeploy {
    private Logger LOG = LoggerFactory.getLogger(AbstractHotDeploy.class.getName());
    //存放监控文件 和其最后的更新时间的映射组合  用于目录操作
    protected List<Entry> marks = new ArrayList<>();
    private int scheduledThreadCount = 1;  //调度线程数
    private int delaySeconds = 0;//延时数
    private int period = 1; //每隔多少秒回调一次
    private TimeUnit unit = TimeUnit.SECONDS;//单位

    @Override
    public void addMonitor(File destFile) {
        exec();  //先执行一次 代码
        if (destFile == null) {
            LOG.debug("{}  methods:{},file is null", LocalDateTime.now(), "AbstractHotDeploy.addMonitor");
            return;
        }
        if (!destFile.exists()) {
            LOG.debug("{}  methods:{},file not exists", LocalDateTime.now(), "AbstractHotDeploy.addMonitor");
            return;
        }
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(scheduledThreadCount);
        if (destFile.isFile()) {  //监听文件
            marks.add(new Entry() {{
                setFile(destFile);
                setLastTime(destFile.lastModified());
            }});
            monitorFile(destFile, scheduledExecutorService);
        }
        if (destFile.isDirectory()) {  //监听目录下所有的文件
            listFile(destFile, marks);
            monitorDirectory(scheduledExecutorService);
        }

    }

    private void monitorDirectory(ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            marks.stream().forEach(item -> {
                Long startLastTime = item.getLastTime();
                File file = item.getFile();
                if (file.lastModified() > startLastTime) {
                    item.setLastTime(file.lastModified()); //设置为最新的更新时间
                    item.setChange(true);
                    reCompiler();  //重新编译
                    LOG.debug("{} {} is update", LocalDateTime.now(), file.getName());
                }
            });
        }, delaySeconds, period, unit);
    }

    private void monitorFile(File destFile, ScheduledExecutorService scheduledExecutorService) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            marks.stream().forEach(item -> {
                Long startLastTime = item.getLastTime();
                if (destFile.lastModified() > startLastTime) {
                    item.setLastTime(destFile.lastModified()); //设置为最新的更新时间
                    item.setChange(true);
                    reCompiler();  //重新编译
                    LOG.debug("{} {} is update", LocalDateTime.now(), destFile.getName());
                }
            });
        }, delaySeconds, period, unit);
    }

    @Override
    public void reCompiler() {
        try {
            List<Entry> changeFile = marks.stream().filter(item -> item.isChange()).collect(Collectors.toList());
            //监听操作
            StringBuilder execCompiler = new StringBuilder(100);
            //获取文件的编译目录 getClass().getResource("").getPath() 获取当前文件的编译路径 /F:/idea/target/test-classes/com/technology/springboot/hotDeploy/demo5/
            //getClass().getResource("/").getPath() 获取当前项目的编译路径  /F:/idea/target/test-classes/
            execCompiler.append("javac -d ")
                    .append(getClass().getResource("/").getPath().substring(1))
                    .append(" ");  //拼接编译字节码放入的目录
            changeFile.forEach(item -> {
                item.setChange(false);
                execCompiler.append(item.getFile().getAbsolutePath()).append(" ");
            });
            //执行编译处理
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(execCompiler.toString());
            LOG.debug("{},compiler success",LocalDateTime.now());
            exec();
        } catch (IOException e) {
            LOG.debug("{},error msg:{}", LocalDateTime.now(),e.getMessage());
        }
    }

    private List<Entry> listFile(File directoryFile, List<Entry> data) {
        File[] files = directoryFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                listFile(file, data);
            } else if (file.isFile()) {
                data.add(new Entry() {{
                    setFile(file);
                    setLastTime(file.lastModified());
                }});//设置元素
            }
        }
        return data;
    }


    class Entry {
        private File file;
        private Long lastTime;
        private boolean isChange = false;

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public Long getLastTime() {
            return lastTime;
        }

        public void setLastTime(Long lastTime) {
            this.lastTime = lastTime;
        }

        public boolean isChange() {
            return isChange;
        }

        public void setChange(boolean change) {
            isChange = change;
        }
    }


}
