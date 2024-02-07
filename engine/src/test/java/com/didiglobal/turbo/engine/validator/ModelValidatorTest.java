package com.didiglobal.turbo.engine.validator;

import com.didiglobal.turbo.engine.exception.DefinitionException;
import com.didiglobal.turbo.engine.exception.ProcessException;
import com.didiglobal.turbo.engine.runner.BaseTest;
import com.didiglobal.turbo.engine.util.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class ModelValidatorTest extends BaseTest {

    @Resource ModelValidator modelValidator;

    /**
     * Test modelValidator, while model is normal.
     *
     */
    @Test
    public void validateAccess() {
        String modelStr = EntityBuilder.buildModelStringAccess();
        boolean access = false;
        try {
            modelValidator.validate(modelStr);
            access = true;
            Assertions.assertTrue(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        } catch (ProcessException e) {
            LOGGER.error("", e);
            Assertions.assertTrue(access);
        }


    }
    /**
     * Test modelValidator, while model is empty.
     *
     */
    @Test
    public void validateEmptyModel() {
        String modelStr = null;
        boolean access = false;
        try {
            modelValidator.validate(modelStr);
            access = true;
            Assertions.assertFalse(access);
        } catch (DefinitionException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        } catch (ProcessException e) {
            LOGGER.error("", e);
            Assertions.assertFalse(access);
        }
    }

}