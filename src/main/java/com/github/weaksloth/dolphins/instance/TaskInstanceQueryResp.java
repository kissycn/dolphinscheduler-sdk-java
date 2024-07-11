package com.github.weaksloth.dolphins.instance;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.weaksloth.dolphins.process.type.TaskExeStatusType;
import com.github.weaksloth.dolphins.process.type.TaskExecuteType;
import java.util.Date;
import lombok.Data;

/** @create: 2024-07-11 11:3 * */
@Data
public class TaskInstanceQueryResp {
  private Integer id;

  /** task name */
  private String name;

  /** task type */
  private String taskType;

  private int processInstanceId;

  private String processInstanceName;

  private Long projectCode;

  private long taskCode;

  private int taskDefinitionVersion;

  private String processDefinitionName;

  /** process instance name */
  private int taskGroupPriority;

  /** state */
  private TaskExeStatusType state;

  /** task first submit time. */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date firstSubmitTime;

  /** task submit time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date submitTime;

  /** task start time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date startTime;

  /** task end time */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date endTime;

  /** task host */
  private String host;

  /**
   * task shell execute path and the resource down from hdfs default path:
   * $base_run_dir/processInstanceId/taskInstanceId/retryTimes
   */
  private String executePath;

  /** task log path default path: $base_run_dir/processInstanceId/taskInstanceId/retryTimes */
  private String logPath;

  /** retry times */
  private int retryTimes;

  /** process id */
  private int pid;

  /** appLink */
  private String appLink;

  /** flag */
  private String flag;

  /** task is cache: yes/no */
  private String isCache;

  /** cache_key */
  private String cacheKey;

  /** duration */
  private String duration;

  /** max retry times */
  private int maxRetryTimes;

  /** task retry interval, unit: minute */
  private int retryInterval;

  /** task intance priority */
  private String taskInstancePriority;

  /** process intance priority */
  private String processInstancePriority;

  /** dependent state */
  private String dependentResult;

  /** workerGroup */
  private String workerGroup;

  /** environment code */
  private Long environmentCode;

  /** environment config */
  private String environmentConfig;

  /** executor id */
  private int executorId;

  /** varPool string */
  private String varPool;

  private String executorName;

  /** delay execution time. */
  private int delayTime;

  /** task params */
  private String taskParams;

  /** dry run flag */
  private int dryRun;
  /** task group id */
  private int taskGroupId;

  /** cpu quota */
  private Integer cpuQuota;

  /** max memory */
  private Integer memoryMax;

  /** task execute type */
  private TaskExecuteType taskExecuteType;

  /** test flag */
  private int testFlag;
}
