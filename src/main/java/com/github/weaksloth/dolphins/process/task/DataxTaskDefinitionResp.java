package com.github.weaksloth.dolphins.process.task;

import com.github.weaksloth.dolphins.task.AbstractTask;
import com.github.weaksloth.dolphins.task.DataxTask;
import lombok.Data;

/** DataxTaskDefinitionResp */
@Data
public class DataxTaskDefinitionResp {
  private Long code;

  private Integer version;

  /** the task node's name */
  private String name;

  /** the task node's description */
  private String description;

  /** get from {@link AbstractTask#getTaskType()} */
  private String taskType;

  /** NO:the node will not execute;YES:the node will execute,default is YES */
  private String flag;

  private String taskPriority;

  private String workerGroup;

  private String failRetryTimes;

  private String failRetryInterval;

  private String timeoutFlag;

  private String timeoutNotifyStrategy;

  private Integer timeout;

  private String delayTime;

  private Integer environmentCode;

  private String taskExecuteType;

  private Integer cpuQuota;

  private Long memoryMax;

  private DataxTask taskParams;
}
