package com.didiglobal.turbo.engine.executor;

import com.alibaba.fastjson2.JSONObject;
import com.didiglobal.turbo.engine.common.RuntimeContext;
import com.didiglobal.turbo.engine.model.FlowElement;
import com.didiglobal.turbo.engine.model.FlowModel;
import com.didiglobal.turbo.engine.model.InstanceData;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import com.didiglobal.turbo.engine.util.FlowModelUtil;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Transactional
@Rollback
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExclusiveGatewayExecutorTest extends BaseTest {

    @Resource
    private ExecutorFactory executorFactory;

    private ExclusiveGatewayExecutor exclusiveGatewayExecutor;

    private RuntimeContext runtimeContext;

    @BeforeEach
    public void initExclusiveGatewayExecutor() {
        List<FlowElement> flowElementList = EntityBuilder.buildFlowElementList();

        FlowModel flowModel = new FlowModel();
        flowModel.setFlowElementList(flowElementList);
        Map<String, FlowElement> flowElementMap = FlowModelUtil.getFlowElementMap(JSONObject.toJSONString(flowModel));

        FlowElement exclusiveGateway = FlowModelUtil.getFlowElement(flowElementMap, "exclusiveGateway1");

        runtimeContext = EntityBuilder.buildRuntimeContext();
        Map<String, InstanceData> instanceDataMap = Maps.newHashMap();
        InstanceData instanceDataA = new InstanceData("a", 2);
        InstanceData instanceDataB = new InstanceData("b", 1);
        instanceDataMap.put(instanceDataA.getKey(), instanceDataA);
        instanceDataMap.put(instanceDataB.getKey(), instanceDataB);
        runtimeContext.setInstanceDataMap(instanceDataMap);
        runtimeContext.setCurrentNodeModel(exclusiveGateway);

        try {
            exclusiveGatewayExecutor = (ExclusiveGatewayExecutor) executorFactory
                    .getElementExecutor(exclusiveGateway);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @Test
    @Order(1)
    public void testDoExecute() {
        try {
            exclusiveGatewayExecutor.doExecute(runtimeContext);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    @Test
    @Order(2)
    public void testGetExecuteExecutor() {
        try {
            exclusiveGatewayExecutor.getExecuteExecutor(runtimeContext);
            String modelKey = runtimeContext.getCurrentNodeModel().getKey();
            Assertions.assertTrue("userTask2".equals(modelKey));
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}
