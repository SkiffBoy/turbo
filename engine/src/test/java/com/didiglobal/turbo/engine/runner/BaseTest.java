package com.didiglobal.turbo.engine.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestEngineApplication.class)
public class BaseTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
}
