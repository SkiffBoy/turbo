package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.InstanceDataPO;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
public class InstanceDataMapperTest extends BaseTest {

    @Autowired
    private InstanceDataMapper instanceDataMapper;

    @Test
    public void insert() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        int result = instanceDataMapper.insertSelective(instanceDataPO);
        LOGGER.info("insert.result={}", result);
        Assertions.assertTrue(result == 1);
    }

    @Test
    public void select() {
        InstanceDataPO instanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataMapper.insertSelective(instanceDataPO);
        InstanceDataPO result = instanceDataMapper.select(instanceDataPO.getFlowInstanceId(), instanceDataPO.getInstanceDataId());
        Assertions.assertTrue(result.getInstanceDataId().equals(instanceDataPO.getInstanceDataId()));
    }

    @Test
    public void selectRecentOne() {
        InstanceDataPO oldInstanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataMapper.insertSelective(oldInstanceDataPO);
        InstanceDataPO newInstanceDataPO = EntityBuilder.buildDynamicInstanceDataPO();
        instanceDataMapper.insertSelective(newInstanceDataPO);
        InstanceDataPO result = instanceDataMapper.selectRecentOne(oldInstanceDataPO.getFlowInstanceId());
        Assertions.assertTrue(result.getInstanceDataId().equals(newInstanceDataPO.getInstanceDataId()));
    }
}
