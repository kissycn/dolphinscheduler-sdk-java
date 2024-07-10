package com.github.weaksloth.dolphins.process.task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.weaksloth.dolphins.core.AbstractOperator;
import com.github.weaksloth.dolphins.core.DolphinException;
import com.github.weaksloth.dolphins.process.TaskDefinition;
import com.github.weaksloth.dolphins.remote.DolphinsRestTemplate;
import com.github.weaksloth.dolphins.remote.HttpRestResult;
import com.github.weaksloth.dolphins.util.JacksonUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** */
@Slf4j
public class TaskOperator extends AbstractOperator {
  public TaskOperator(
      String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
    super(dolphinAddress, token, dolphinsRestTemplate);
  }

  public DataxTaskDefinitionResp detail(Long projectCode, Long taskId) {
    String url = dolphinAddress + "/projects/" + projectCode + "/task-definition/" + taskId;
    log.info("get process task definition detail, url:{}", url);

    HttpRestResult<JsonNode> restResult =
        dolphinsRestTemplate.get(url, getHeader(), null, JsonNode.class);

    if (restResult.getSuccess()) {
      return JacksonUtils.parseObject(
          restResult.getData().toString(), new TypeReference<DataxTaskDefinitionResp>() {});
    } else {
      log.error("dolphin scheduler response:{}", restResult);
      throw new DolphinException(restResult.getCode(), restResult.getMsg());
    }
  }

  public void update(Long projectCode, Long taskCode, TaskDefinition definition) {
    String url =
        dolphinAddress
            + "/projects/"
            + projectCode
            + "/task-definition/"
            + taskCode
            + "/with-upstream";

    log.info("update process definition, url:{}, param:{}", url, definition);

    Map<String, TaskDefinition> param = new HashMap<>();
    param.put("taskDefinitionJsonObj", definition);

    HttpRestResult<JsonNode> restResult =
        dolphinsRestTemplate.putForm(url, getHeader(), param, JsonNode.class);

    if (restResult.getFailed()) {
      log.error("dolphin scheduler response:{}", restResult);
      throw new DolphinException(restResult.getCode(), restResult.getMsg());
    }
  }
}
