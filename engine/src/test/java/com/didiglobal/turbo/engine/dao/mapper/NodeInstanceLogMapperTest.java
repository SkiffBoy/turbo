package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.NodeInstanceLogPO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import com.mybatisflex.core.query.QueryWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Rollback
public class NodeInstanceLogMapperTest extends BaseTest {

    @Resource
    private NodeInstanceLogMapper nodeInstanceLogMapper;

    @Test
    public void batchInsert() {
        String flowInstanceId = "flowInstanceId_" + UUID.randomUUID().toString();
        NodeInstanceLogPO nodeInstanceLogPO = EntityBuilder.buildNodeInstanceLogPO(flowInstanceId);
        List<NodeInstanceLogPO> nodeInstanceLogPOList = new ArrayList<>();
        nodeInstanceLogPOList.add(nodeInstanceLogPO);
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO(flowInstanceId));
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO(flowInstanceId));
        nodeInstanceLogMapper.batchInsert(nodeInstanceLogPO.getFlowInstanceId(), nodeInstanceLogPOList);

        QueryWrapper entityWrapper = QueryWrapper.create();
        entityWrapper.in("flow_instance_id", nodeInstanceLogPO.getFlowInstanceId());
        nodeInstanceLogPOList = nodeInstanceLogMapper.selectListByQuery(entityWrapper);
        Assertions.assertTrue(nodeInstanceLogPOList.size() == 3);
    }
}
