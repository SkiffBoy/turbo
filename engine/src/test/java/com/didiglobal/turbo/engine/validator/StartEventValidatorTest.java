package com.didiglobal.turbo.engine.validator;

import com.didiglobal.turbo.engine.exception.DefinitionException;
import com.didiglobal.turbo.engine.model.FlowElement;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StartEventValidatorTest extends BaseTest {

    @Resource StartEventValidator startEventValidator;

    /**
     * Test startEvent's incoming, whlile normal.
     *
     */
    @Test
    public void checkIncomingAccess() {
        FlowElement startEvent = EntityBuilder.buildStartEvent();
        Map<String, FlowElement> map = new HashMap<>();
        map.put(startEvent.getKey(), startEvent);
        startEventValidator.checkIncoming(map, startEvent);
    }
    /**
     * Test startEvent's incoming, whlile incoming is too much.
     *
     */
    @Test
    public void checkTooManyIncoming() {
        FlowElement startEventVaild = EntityBuilder.buildStartEvent();
        List<String> incomings = new ArrayList<>();
        incomings.add("sequence");
        startEventVaild.setIncoming(incomings);
        Map<String, FlowElement> map = new HashMap<>();
        map.put("startEvent", startEventVaild);
        startEventValidator.checkIncoming(map, startEventVaild);
    }

    /**
     * Test startEvent's incoming, whlile normal.
     *
     */
    @Test
    public void checkOutgoingAccess() {
        FlowElement startEvent = EntityBuilder.buildStartEvent();
        Map<String, FlowElement> map = new HashMap<>();
        map.put(startEvent.getKey(), startEvent);
        boolean access = false;
        try {
            startEventValidator.checkOutgoing(map, startEvent);
            access = true;
            Assertions.assertTrue(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        }
    }
    /**
     * Test startEvent's incoming, whlile incoming is too much.
     *
     */
    @Test
    public void checkTooMuchOutgoing() {
        FlowElement startEventVaild = EntityBuilder.buildStartEvent();
        List<String> outgoings = new ArrayList<>();
        startEventVaild.setOutgoing(outgoings);
        Map<String, FlowElement> map = new HashMap<>();
        map.put("startEvent", startEventVaild);
        boolean access = false;
        try {
            startEventValidator.checkOutgoing(map, startEventVaild);
            access = true;
            Assertions.assertFalse(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        }

    }
}