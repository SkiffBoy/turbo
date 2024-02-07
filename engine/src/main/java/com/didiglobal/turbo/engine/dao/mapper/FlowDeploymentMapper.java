package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowDeploymentPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FlowDeploymentMapper extends BaseMapper<FlowDeploymentPO> {

    @Select("SELECT * FROM em_flow_deployment WHERE flow_deploy_id=#{flowDeployId}")
    FlowDeploymentPO selectByDeployId(@Param("flowDeployId") String flowDeployId);

    @Select("SELECT * FROM em_flow_deployment WHERE flow_module_id=#{flowModuleId} ORDER BY id DESC LIMIT 1")
    FlowDeploymentPO selectByModuleId(@Param("flowModuleId") String flowModuleId);
}
