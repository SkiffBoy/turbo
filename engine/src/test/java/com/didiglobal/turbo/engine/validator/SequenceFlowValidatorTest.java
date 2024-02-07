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


public class SequenceFlowValidatorTest extends BaseTest {

    @Resource
    SequenceFlowValidator sequenceFlowValidator;

    /**
     * Test sequenceFlow's checkIncoming, while incoming is normal.
     *
     */
    @Test
    public void checkIncomingAccess() {
        FlowElement sequenceFlow = EntityBuilder.buildSequenceFlow();
        Map<String, FlowElement> flowElementMap = new HashMap<>();
        flowElementMap.put(sequenceFlow.getKey(), sequenceFlow);
        boolean access = false;
        try {
            sequenceFlowValidator.checkIncoming(flowElementMap, sequenceFlow);
            access = true;
            Assertions.assertTrue(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        }
    }


    /**
     * Test sequenceFlow's checkIncoming, while incoming  is too much.
     *
     */
    @Test
    public void checkTooMuchIncoming() {
        FlowElement sequenceFlow = EntityBuilder.buildSequenceFlow();
        List<String> sfIncomings = new ArrayList<>();
        sfIncomings.add("userTask2");
        sfIncomings.add("userTask1");
        sequenceFlow.setIncoming(sfIncomings);
        Map<String, FlowElement> flowElementMap = new HashMap<>();
        flowElementMap.put(sequenceFlow.getKey(), sequenceFlow);
        boolean access = false;
        try {
            sequenceFlowValidator.checkIncoming(flowElementMap, sequenceFlow);
            access = true;
            Assertions.assertFalse(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        }
    }

    /**
     * Test sequenceFlow's checkOutgoing, while outgoing is normal.
     *
     */
    @Test
    public void checkOutgoingAccess() {

        FlowElement sequenceFlow = EntityBuilder.buildSequenceFlow();
        Map<String, FlowElement> flowElementMap = new HashMap<>();
        flowElementMap.put(sequenceFlow.getKey(), sequenceFlow);
        boolean access = false;
        try {
            sequenceFlowValidator.checkOutgoing(flowElementMap, sequenceFlow);
            access = true;
            Assertions.assertTrue(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        }
    }

    /**
     * Test sequenceFlow's outgoing, while outgoing is lack.
     *
     */
    @Test
    public void checkWithoutOutgoing() {

        FlowElement sequenceFlow = EntityBuilder.buildSequenceFlow();
        sequenceFlow.setOutgoing(null);
        Map<String, FlowElement> flowElementMap = new HashMap<>();
        flowElementMap.put(sequenceFlow.getKey(), sequenceFlow);
        boolean access = false;
        try {
            sequenceFlowValidator.checkOutgoing(flowElementMap, sequenceFlow);
            access = true;
            Assertions.assertFalse(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        }
    }
}