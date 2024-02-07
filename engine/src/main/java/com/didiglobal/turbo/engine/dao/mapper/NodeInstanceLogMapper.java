package com.didiglobal.turbo.engine.dao.mapper;

import com.didiglobal.turbo.engine.dao.provider.NodeInstanceLogProvider;
import com.didiglobal.turbo.engine.entity.NodeInstanceLogPO;
import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NodeInstanceLogMapper extends BaseMapper<NodeInstanceLogPO> {

    @InsertProvider(type = NodeInstanceLogProvider.class, method = "batchInsert")
    boolean batchInsert(@Param("flowInstanceId") String flowInstanceId,
                        @Param("nodeInstanceLogList") List<NodeInstanceLogPO> nodeInstanceLogList);

}
