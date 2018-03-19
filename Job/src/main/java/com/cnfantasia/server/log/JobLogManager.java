package com.cnfantasia.server.log;

import com.cnfantasia.server.domainbase.taskLogger.entity.TaskLogger;
import com.cnfantasia.server.domainbase.taskLogger.service.ITaskLoggerBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TaskLogManager
 * @Date: 2016-12-16 14:19
 * @Auther: kangduo
 * @Description: (job执行日志管理类)
 */
public class JobLogManager {
    private ITaskLoggerBaseService taskLoggerBaseService;
    public void setTaskLoggerBaseService(ITaskLoggerBaseService taskLoggerBaseService) {
        this.taskLoggerBaseService = taskLoggerBaseService;
    }

    private List<TaskLogger> taskLoggerList = new ArrayList<TaskLogger>();
    private final Object lock = new Object();
    void insertTaskLoggerCache(TaskLogger logger) {
        synchronized (lock) {
            taskLoggerList.add(logger);
            if (taskLoggerList.size() >= 30) {
                taskLoggerBaseService.insertTaskLoggerBatch(taskLoggerList);
                taskLoggerList.clear();
            }
        }
    }

    public void destroy() {
        taskLoggerBaseService.insertTaskLoggerBatch(taskLoggerList);
    }
}
