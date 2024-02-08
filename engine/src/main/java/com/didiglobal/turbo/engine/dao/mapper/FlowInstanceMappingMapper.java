package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowInstanceMappingPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlowInstanceMappingMapper extends BaseMapper<FlowInstanceMappingPO> {

    List<FlowInstanceMappingPO> selectFlowInstanceMappingPOList(@Param("flowInstanceId") String flowInstanceId, @Param("nodeInstanceId") String nodeInstanceId);

    FlowInstanceMappingPO selectFlowInstanceMappingPO(@Param("flowInstanceId") String flowInstanceId, @Param("nodeInstanceId") String nodeInstanceId);

    void updateType(FlowInstanceMappingPO entity);
}
