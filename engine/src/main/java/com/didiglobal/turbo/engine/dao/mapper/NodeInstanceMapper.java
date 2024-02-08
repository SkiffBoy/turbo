package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.entity.NodeInstancePO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NodeInstanceMapper extends BaseMapper<NodeInstancePO> {

    NodeInstancePO selectByNodeInstanceId(@Param("flowInstanceId") String flowInstanceId,
                                          @Param("nodeInstanceId") String nodeInstanceId);

    NodeInstancePO selectRecentOne(@Param("flowInstanceId") String flowInstanceId);

    List<NodeInstancePO> selectByFlowInstanceId(@Param("flowInstanceId") String flowInstanceId);

    List<NodeInstancePO> selectDescByFlowInstanceId(@Param("flowInstanceId") String flowInstanceId);

    NodeInstancePO selectRecentOneByStatus(@Param("flowInstanceId") String flowInstanceId, @Param("status") int status);

    NodeInstancePO selectBySourceInstanceId(@Param("flowInstanceId") String flowInstanceId,
                                            @Param("sourceNodeInstanceId") String sourceNodeInstanceId,
                                            @Param("nodeKey") String nodeKey);

    void updateStatus(NodeInstancePO entity);

}
