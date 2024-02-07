package com.didiglobal.turbo.engine.dao;

import com.didiglobal.turbo.engine.entity.FlowDeploymentPO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Rollback
public class FlowDeploymentDAOTest extends BaseTest {

    @Resource
    FlowDeploymentDAO flowDeploymentDAO;

    @Test
    public void insert() {
        FlowDeploymentPO flowDeploymentPO = EntityBuilder.buildFlowDeploymentPO();
        flowDeploymentPO.setFlowDeployId("testFlowDeployId_" + System.currentTimeMillis());
        int result = flowDeploymentDAO.insert(flowDeploymentPO);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void selectByDeployId() {
        FlowDeploymentPO flowDeploymentPO = EntityBuilder.buildFlowDeploymentPO();
        flowDeploymentPO.setFlowDeployId("testFlowDeployId_" + System.currentTimeMillis());
        flowDeploymentDAO.insert(flowDeploymentPO);
        String flowDeployId = flowDeploymentPO.getFlowDeployId();
        flowDeploymentPO = flowDeploymentDAO.selectByDeployId(flowDeployId);
        Assertions.assertTrue(flowDeployId.equals(flowDeploymentPO.getFlowDeployId()));


    }

    @Test
    public void selectRecentByFlowModuleId() {
        FlowDeploymentPO flowDeploymentPO = EntityBuilder.buildFlowDeploymentPO();
        flowDeploymentPO.setFlowDeployId("testFlowDeployId_" + System.currentTimeMillis());
        flowDeploymentDAO.insert(flowDeploymentPO);
        FlowDeploymentPO flowDeploymentPONew = EntityBuilder.buildFlowDeploymentPO();
        String flowModuleId1 = flowDeploymentPO.getFlowModuleId();
        flowDeploymentPO.setFlowDeployId("testFlowDeployId_" + System.currentTimeMillis());
        flowDeploymentPONew.setFlowModuleId(flowModuleId1);
        flowDeploymentPONew.setFlowDeployId(flowDeploymentPONew.getFlowDeployId()+2);
        flowDeploymentDAO.insert(flowDeploymentPONew);
        FlowDeploymentPO flowDeploymentPORes = flowDeploymentDAO.selectRecentByFlowModuleId(flowModuleId1);
        Assertions.assertTrue(flowDeploymentPONew.getFlowDeployId().equals(flowDeploymentPORes.getFlowDeployId()));



    }
}