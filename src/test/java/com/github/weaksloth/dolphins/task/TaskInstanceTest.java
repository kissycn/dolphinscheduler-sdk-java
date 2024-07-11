package com.github.weaksloth.dolphins.task;

import com.github.weaksloth.dolphins.BaseTest;
import com.github.weaksloth.dolphins.common.PageInfo;
import com.github.weaksloth.dolphins.instance.LogDetailResp;
import com.github.weaksloth.dolphins.instance.TaskInstanceQueryResp;
import org.junit.Test;

/** @create: 2024-07-11 11:40 */
public class TaskInstanceTest extends BaseTest {
  @Test
  public void testPage() {
    PageInfo<TaskInstanceQueryResp> page =
        getClient()
            .opsForTaskInst()
            .page(1, 10, projectCode, "hello_world_process-1-20240711095300172");

    System.out.println(page.toString());
  }

  @Test
  public void testLogDetail() {
    LogDetailResp logDetailResp = getClient().opsForTaskInst().logDetail(51L, 10, 10);
    System.out.println(logDetailResp.toString());
  }
}
