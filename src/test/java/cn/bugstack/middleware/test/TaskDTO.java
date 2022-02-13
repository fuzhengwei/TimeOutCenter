package cn.bugstack.middleware.test;

import java.io.Serializable;

/**
 * @author xiang xiaocheng
 * @version 1.0
 * @site chsoul.cnblogs.com
 * @date 2021/2/8 9:25
 */
public class TaskDTO<T> implements Serializable {
    private String taskName;
    private T taskBody;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public T getTaskBody() {
        return taskBody;
    }

    public void setTaskBody(T taskBody) {
        this.taskBody = taskBody;
    }
}

