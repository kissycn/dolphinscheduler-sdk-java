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

/**
 * @create: 2024-07-11 11:43
 **/
public class TaskInstanceOperator extends AbstractOperator {
    public TaskInstanceOperator(String dolphinAddress, String token, DolphinsRestTemplate dolphinsRestTemplate) {
        super(dolphinAddress, token, dolphinsRestTemplate);
    }

    /**
     * page query task's instance list
     *
     * @param page         page,default 1 while is null
     * @param size         size,default 10 while is null
     * @param projectCode  project code
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
                    restResult.getData().toString(),
                    new TypeReference<PageInfo<TaskInstanceQueryResp>>() {});
        } catch (Exception e) {
            throw new DolphinException("page dolphin scheduler process instance list fail", e);
        }
    }
}
