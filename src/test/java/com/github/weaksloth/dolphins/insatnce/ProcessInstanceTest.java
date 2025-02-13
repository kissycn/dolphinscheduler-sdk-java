package com.github.weaksloth.dolphins.insatnce;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.common.PageInfo;
import com.github.weaksloth.dolphins.instance.ProcessInstanceCreateParam;
import com.github.weaksloth.dolphins.instance.ProcessInstanceQueryResp;
import org.junit.Assert;
import org.junit.Test;

public class ProcessInstanceTest extends BaseTest {

  public static final Long PROCESS_DEFINITION_CODE = 113392636785632L;

  /** the workflow must in online state,otherwise will cause error */
  @Test
  public void testStartInstance() {

    ProcessInstanceCreateParam startParam = new ProcessInstanceCreateParam();
    startParam
        .setProcessDefinitionCode(PROCESS_DEFINITION_CODE)
        .setScheduleTime("")
        .setFailureStrategy("CONTINUE")
        .setWarningType("NONE")
        .setWarningGroupId(0L)
        .setExecType("")
        .setStartNodeList("")
        .setTaskDependType("TASK_POST")
        .setRunMode("RUN_MODE_SERIAL")
        .setProcessInstancePriority("MEDIUM")
        .setWorkerGroup("default")
        .setEnvironmentCode("")
        .setStartParams("")
        .setExpectedParallelismNumber("")
        .setDryRun(0);
    Assert.assertTrue(getClient().opsForProcessInst().start(projectCode, startParam));
  }

  @Test
  public void testReRun() {
    Long instanceId = 31L;
    Assert.assertTrue(getClient().opsForProcessInst().reRun(projectCode, instanceId));
  }

  @Test
  public void testPage() {
    PageInfo<ProcessInstanceQueryResp> page =
        getClient().opsForProcessInst().page(null, null, projectCode, PROCESS_DEFINITION_CODE);

    System.out.println(page.toString());
  }

  @Test
  public void testDelete() {
    Long instanceId = 31L;
    Assert.assertTrue(getClient().opsForProcessInst().delete(projectCode, instanceId));
  }
}
