package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowDeploymentPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FlowDeploymentMapper extends BaseMapper<FlowDeploymentPO> {

    FlowDeploymentPO selectByDeployId(@Param("flowDeployId") String flowDeployId);

    FlowDeploymentPO selectByModuleId(@Param("flowModuleId") String flowModuleId);
}
