package com.didiglobal.turbo.engine.dao;

import com.didiglobal.turbo.engine.common.NodeInstanceStatus;
import com.didiglobal.turbo.engine.entity.NodeInstancePO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Rollback
public class NodeInstanceDAOTest extends BaseTest {

    @Resource
    private NodeInstanceDAO nodeInstanceDAO;

    @Test
    public void insert(){
        NodeInstancePO nodeInstancePO = EntityBuilder.buildNodeInstancePO();
        int result = nodeInstanceDAO.insert(nodeInstancePO);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void insertOrUpdateList(){
        NodeInstancePO nodeInstancePO = EntityBuilder.buildNodeInstancePO();
        nodeInstanceDAO.insert(nodeInstancePO);
        nodeInstancePO = nodeInstanceDAO.selectByNodeInstanceId(nodeInstancePO.getFlowInstanceId(), nodeInstancePO.getNodeInstanceId());
        List<NodeInstancePO> nodeInstancePOList = new ArrayList<>();
        nodeInstancePO.setStatus(NodeInstanceStatus.COMPLETED); // change something
        nodeInstancePOList.add(nodeInstancePO); // update
        nodeInstancePOList.add(EntityBuilder.buildDynamicNodeInstancePO()); // batch insert
        nodeInstancePOList.add(EntityBuilder.buildDynamicNodeInstancePO()); // batch insert
        nodeInstanceDAO.insertOrUpdateList(nodeInstancePOList);
    }

    @Test
    public void updateStatus() {
        NodeInstancePO nodeInstancePO = EntityBuilder.buildNodeInstancePO();
        nodeInstanceDAO.insert(nodeInstancePO);

        nodeInstanceDAO.updateStatus(nodeInstancePO, NodeInstanceStatus.COMPLETED);
        NodeInstancePO result = nodeInstanceDAO.selectByNodeInstanceId(nodeInstancePO.getFlowInstanceId(), nodeInstancePO.getNodeInstanceId());
        Assertions.assertTrue(result.getStatus() == NodeInstanceStatus.COMPLETED);
    }
}
