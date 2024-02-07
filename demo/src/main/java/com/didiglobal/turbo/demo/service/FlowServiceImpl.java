package com.didiglobal.turbo.demo.service;


import com.didiglobal.turbo.demo.enums.FlowModuleStatusEnum;
import com.didiglobal.turbo.demo.pojo.request.CreateFlowRequest;
import com.didiglobal.turbo.demo.pojo.request.DeployFlowRequest;
import com.didiglobal.turbo.demo.pojo.request.GetFlowModuleListRequest;
import com.didiglobal.turbo.demo.pojo.request.GetFlowModuleRequest;
import com.didiglobal.turbo.demo.pojo.request.UpdateFlowRequest;
import com.didiglobal.turbo.demo.pojo.response.FlowModuleListResponse;
import com.didiglobal.turbo.demo.pojo.response.FlowModuleResponse;
import com.didiglobal.turbo.engine.common.FlowDeploymentStatus;
import com.didiglobal.turbo.engine.dao.FlowDefinitionDAO;
import com.didiglobal.turbo.engine.dao.FlowDeploymentDAO;
import com.didiglobal.turbo.engine.engine.ProcessEngine;
import com.didiglobal.turbo.engine.entity.FlowDefinitionPO;
import com.didiglobal.turbo.engine.result.CreateFlowResult;
import com.didiglobal.turbo.engine.result.DeployFlowResult;
import com.didiglobal.turbo.engine.result.FlowModuleResult;
import com.didiglobal.turbo.engine.result.UpdateFlowResult;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: james zhangxiao
 * @Date: 4/11/22
 * @Description: 流程处理类
 */
@Service
public class FlowServiceImpl {

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private FlowDefinitionDAO flowDefinitionDAO;

    @Resource
    private FlowDeploymentDAO flowDeploymentDAO;

    /**
     * 创建流程
     *
     * @param createFlowParam 创建流程基础信息
     * @return 创建流程结果
     */
    public CreateFlowResult createFlow(CreateFlowRequest createFlowParam) {
        return processEngine.createFlow(createFlowParam);
    }

    /**
     * 保存流程模型数据
     *
     * @param updateFlowRequest 保存流程模型请求参数
     * @return 保存流程模型结果
     */
    public UpdateFlowResult updateFlow(UpdateFlowRequest updateFlowRequest) {
        return processEngine.updateFlow(updateFlowRequest);
    }

    /**
     * 发布流程
     *
     * @param deployFlowRequest 发布流程请求参数
     * @return 发布流程结果
     */
    public DeployFlowResult deployFlow(DeployFlowRequest deployFlowRequest) {
        return processEngine.deployFlow(deployFlowRequest);
    }

    /**
     * 获取单个流程
     *
     * @param getFlowModuleRequest 查询单个流程参数
     * @return 查询单个流程结果
     */
    public FlowModuleResult getFlowModule(GetFlowModuleRequest getFlowModuleRequest) {
        return processEngine.getFlowModule(getFlowModuleRequest);
    }

    /**
     * 查询流程列表
     *
     * @param getFlowModuleListRequest 查询流程列表参数
     * @return 查询流程结果
     */
    public FlowModuleListResponse getFlowModuleList(GetFlowModuleListRequest getFlowModuleListRequest) {
        Page<FlowDefinitionPO> page = buildGetFlowModuleListPage(getFlowModuleListRequest);
        QueryWrapper queryWrapper = buildGetFlowModuleListQueryWrapper(getFlowModuleListRequest);
        Page<FlowDefinitionPO> pageRes = flowDefinitionDAO.page(page, queryWrapper);
        List<FlowModuleResponse> flowModuleList = new ArrayList<>();
        for (FlowDefinitionPO flowDefinitionPO : pageRes.getRecords()) {
            FlowModuleResponse getFlowModuleResponse = new FlowModuleResponse();
            BeanUtils.copyProperties(flowDefinitionPO, getFlowModuleResponse);
            QueryWrapper flowDeployQuery = buildCountFlowDeployQueryWrapper(flowDefinitionPO.getFlowModuleId());
            long count = flowDeploymentDAO.count(flowDeployQuery);
            if (count >= 1) {
                //4 已发布
                getFlowModuleResponse.setStatus(FlowModuleStatusEnum.PUBLISHED.getValue());
            }
            flowModuleList.add(getFlowModuleResponse);
        }
        FlowModuleListResponse flowModuleListResponse = new FlowModuleListResponse();
        flowModuleListResponse.setFlowModuleList(flowModuleList);
        flowModuleListResponse.setCurrent(pageRes.getPageNumber());
        flowModuleListResponse.setSize(pageRes.getPageSize());
        flowModuleListResponse.setTotal(pageRes.getTotalRow());
        return flowModuleListResponse;
    }

    private Page<FlowDefinitionPO> buildGetFlowModuleListPage(GetFlowModuleListRequest getFlowModuleListRequest) {
        Page<FlowDefinitionPO> page = new Page<>();
        if (getFlowModuleListRequest.getSize() != null && getFlowModuleListRequest.getCurrent() != null) {
            page.setPageNumber(getFlowModuleListRequest.getCurrent());
            page.setPageSize(getFlowModuleListRequest.getSize());
        }
        return page;
    }

    private QueryWrapper buildGetFlowModuleListQueryWrapper(GetFlowModuleListRequest getFlowModuleListRequest) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowModuleId())) {
            queryWrapper.eq("flow_module_id", getFlowModuleListRequest.getFlowModuleId());
        }
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowName())) {
            queryWrapper.like("flow_name", getFlowModuleListRequest.getFlowName());
        }
        if (StringUtils.isNotBlank(getFlowModuleListRequest.getFlowDeployId())) {
            queryWrapper.eq("flow_module_id", getFlowModuleListRequest.getFlowDeployId());
        }
        queryWrapper.orderBy("modify_time", false);
        return queryWrapper;
    }

    private QueryWrapper buildCountFlowDeployQueryWrapper(String flowModuleId) {
        QueryWrapper flowDeployQuery = QueryWrapper.create();
        flowDeployQuery.eq("flow_module_id", flowModuleId);
        flowDeployQuery.eq("status", FlowDeploymentStatus.DEPLOYED);
        return flowDeployQuery;
    }

}
