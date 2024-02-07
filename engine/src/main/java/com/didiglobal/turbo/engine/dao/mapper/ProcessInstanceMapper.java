package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.FlowInstancePO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProcessInstanceMapper extends BaseMapper<FlowInstancePO> {

    @Select("SELECT * FROM ei_flow_instance WHERE flow_instance_id=#{flowInstanceId}")
    FlowInstancePO selectByFlowInstanceId(@Param("flowInstanceId") String flowInstanceId);

    @Update("UPDATE ei_flow_instance SET status=#{status}, modify_time=#{modifyTime} " +
        "WHERE flow_instance_id=#{flowInstanceId}")
    void updateStatus(FlowInstancePO entity);
}
