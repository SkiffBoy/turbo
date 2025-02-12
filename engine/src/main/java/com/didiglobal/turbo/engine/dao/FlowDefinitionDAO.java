package com.didiglobal.turbo.engine.dao;

import com.didiglobal.turbo.engine.dao.mapper.FlowDefinitionMapper;
import com.didiglobal.turbo.engine.entity.FlowDefinitionPO;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;


@Repository
public class FlowDefinitionDAO extends BaseDAO<FlowDefinitionMapper, FlowDefinitionPO> {

    /**
     * Insert: insert flowDefinitionPO, return -1 while insert failed.
     *
     * @param flowDefinitionPO
     * @return int
     */
    public int insert(FlowDefinitionPO flowDefinitionPO) {
        try {
            return mapper.insertSelective(flowDefinitionPO);
        } catch (Exception e) {
            LOGGER.error("insert exception.||flowDefinitionPO={}", flowDefinitionPO, e);
        }
        return -1;
    }

    /**
     * UpdateByModuleId: update flowDefinitionPO by flowModuleId, return -1 while updateByModuleId failed.
     *
     * @param flowDefinitionPO
     * @return int
     */
    public int updateByModuleId(FlowDefinitionPO flowDefinitionPO) {
        if (null == flowDefinitionPO) {
            LOGGER.warn("updateByModuleId failed: flowDefinitionPO is null.");
            return -1;
        }
        try {
            String flowModuleId = flowDefinitionPO.getFlowModuleId();
            if (StringUtils.isBlank(flowModuleId)) {
                LOGGER.warn("updateByModuleId failed: flowModuleId is empty.||flowDefinitionPO={}", flowDefinitionPO);
                return -1;
            }
            QueryWrapper updateWrapper = QueryWrapper.create();
            updateWrapper.eq("flow_module_id", flowModuleId);
            return mapper.updateByQuery(flowDefinitionPO, updateWrapper);
        } catch (Exception e) {
            LOGGER.error("update exception.||flowDefinitionPO={}", flowDefinitionPO, e);
        }
        return -1;
    }

    /**
     * SelectByModuleId: query flowDefinitionPO by flowModuleId, return null while flowDefinitionPO can't be found.
     *
     * @param flowModuleId
     * @return flowDefinitionPO
     */
    public FlowDefinitionPO selectByModuleId(String flowModuleId) {
        if (StringUtils.isBlank(flowModuleId)) {
            LOGGER.warn("getById failed: flowModuleId is empty.");
            return null;
        }
        try {
            return mapper.selectByFlowModuleId(flowModuleId);
        } catch (Exception e) {
            LOGGER.error("getById exception.||flowModuleId={}", flowModuleId, e);
        }
        return null;
    }
}
