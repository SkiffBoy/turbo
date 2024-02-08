package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowInstancePO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProcessInstanceMapper extends BaseMapper<FlowInstancePO> {

    FlowInstancePO selectByFlowInstanceId(@Param("flowInstanceId") String flowInstanceId);

    void updateStatus(FlowInstancePO entity);
}
