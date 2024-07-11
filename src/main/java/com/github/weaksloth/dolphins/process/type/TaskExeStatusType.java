package com.github.weaksloth.dolphins.process.type;

/** @create: 2024-07-11 11:36 */
public enum TaskExeStatusType {
  /** 提交成功 */
  SUBMITTED_SUCCESS,

  /** 运行中 */
  RUNNING_EXECUTION,

  /** 暂停 */
  PAUSE,

  /** 停止 */
  STOP,

  /** 失败 */
  FAILURE,

  /** 成功 */
  SUCCESS,

  /** 需要容错 */
  NEED_FAULT_TOLERANCE,

  /** 杀死 */
  KILL,

  /** 延迟执行 */
  DELAY_EXECUTION,

  /** 强制成功 */
  FORCED_SUCCESS,

  /** 调度 */
  DISPATCH
}
