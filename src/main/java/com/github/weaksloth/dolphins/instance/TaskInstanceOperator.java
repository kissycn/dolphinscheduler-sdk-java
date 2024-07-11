package com.github.weaksloth.dolphins.instance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.weaksloth.dolphins.common.PageInfo;
import com.github.weaksloth.dolphins.core.AbstractOperator;
import com.github.weaksloth.dolphins.core.DolphinClientConstant;
import com.github.weaksloth.dolphins.core.DolphinException;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.remote.Query;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/** @create: 2024-07-11 11:43 */
@Slf4j
public class TaskInstanceOperator extends AbstractOperator {
  public TaskInstanceOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  /**
   * page query task's instance list
   *
   * @param page page,default 1 while is null
   * @param size size,default 10 while is null
   * @param projectCode project code
   * @param processInstanceName process instance name
   * @return PageInfo<TaskInstanceQueryResp>
   */
  public PageInfo<TaskInstanceQueryResp> page(
      Integer page, Integer size, Long projectCode, String processInstanceName) {
    page = Optional.ofNullable(page).orElse(DolphinClientConstant.Page.DEFAULT_PAGE);
    size = Optional.ofNullable(size).orElse(DolphinClientConstant.Page.DEFAULT_SIZE);

    String url = dolphinAddress + "/projects/" + projectCode + "/task-instances";

    Query query = new Query();
    query
        .addParam("pageNo", String.valueOf(page))
        .addParam("pageSize", String.valueOf(size))
        .addParam("processInstanceName", processInstanceName);

    try {
      HttpRestResult<JsonNode> restResult =
          dolphinsRestTemplate.get(url, getHeader(), query, JsonNode.class);
      return JacksonUtils.parseObject(
          restResult.getData().toString(), new TypeReference<PageInfo<TaskInstanceQueryResp>>() {});
    } catch (Exception e) {
      throw new DolphinException("page dolphin scheduler process instance list fail", e);
    }
  }

  public LogDetailResp logDetail(Long taskInstanceId, Integer limit, Integer skipLineNum) {
    String url = dolphinAddress + "/log/detail";
    log.info("get process task definition detail, url:{}", url);

    Query query =
        new Query()
            .addParam("taskInstanceId", String.valueOf(taskInstanceId))
            .addParam("limit", String.valueOf(limit))
            .addParam("skipLineNum", String.valueOf(skipLineNum));

    HttpRestResult<LogDetailResp> restResult =
        dolphinsRestTemplate.get(url, getHeader(), query, LogDetailResp.class);

    if (restResult.getFailed()) {
      log.error("dolphin scheduler response:{}", restResult);
      throw new DolphinException(restResult.getCode(), restResult.getMsg());
    }

    return restResult.getData();
  }
}
