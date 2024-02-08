package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.InstanceDataPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface InstanceDataMapper extends BaseMapper<InstanceDataPO> {

    InstanceDataPO select(@Param("flowInstanceId") String flowInstanceId,
                          @Param("instanceDataId") String instanceDataId);

    InstanceDataPO selectRecentOne(@Param("flowInstanceId") String flowInstanceId);
}
