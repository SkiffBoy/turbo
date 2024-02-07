package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowDefinitionPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FlowDefinitionMapper extends BaseMapper<FlowDefinitionPO> {

    @Select("SELECT * FROM em_flow_definition WHERE flow_module_id=#{flowModuleId}")
    FlowDefinitionPO selectByFlowModuleId(@Param("flowModuleId") String flowModuleId);
}
