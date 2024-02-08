package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowDefinitionPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FlowDefinitionMapper extends BaseMapper<FlowDefinitionPO> {

    FlowDefinitionPO selectByFlowModuleId(@Param("flowModuleId") String flowModuleId);
}
