package com.didiglobal.turbo.engine.dao;

import com.didiglobal.turbo.engine.entity.InstanceDataPO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Rollback
public class InstanceDataDAOTest extends BaseTest {

    @Resource
    private InstanceDataDAO instanceDataDAO;

    @Test
    public void insertSuccess() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        int result = instanceDataDAO.insert(instanceDataPO);
        LOGGER.info("insertTest.result={}", result);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void insertFailed() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(instanceDataPO);
        // test DuplicateKeyException
        int result = instanceDataDAO.insert(instanceDataPO);
        LOGGER.info("insertTest.result={}", result);
        Assertions.assertTrue(result == -1);
    }

    @Test
    public void select() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(instanceDataPO);
        InstanceDataPO result = instanceDataDAO.select(instanceDataPO.getFlowInstanceId(), instanceDataPO.getInstanceDataId());
        Assertions.assertTrue(result.getInstanceDataId().equals(instanceDataPO.getInstanceDataId()));
    }

    @Test
    public void selectRecentOne() {
        InstanceDataPO oldInstanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(oldInstanceDataPO);
        InstanceDataPO newInstanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataDAO.insert(newInstanceDataPO);
        InstanceDataPO result = instanceDataDAO.selectRecentOne(oldInstanceDataPO.getFlowInstanceId());
        Assertions.assertTrue(result.getInstanceDataId().equals(newInstanceDataPO.getInstanceDataId()));
    }
}
