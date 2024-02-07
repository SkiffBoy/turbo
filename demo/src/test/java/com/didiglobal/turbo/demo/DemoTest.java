package com.didiglobal.turbo.demo;

import com.didiglobal.turbo.demo.runner.CallActivityDemoRunner;
import com.didiglobal.turbo.demo.runner.CommonDemoRunner;
import com.didiglobal.turbo.demo.service.AfterSaleServiceImpl;
import com.didiglobal.turbo.demo.service.LeaveServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author rick
 * @Date 2022/4/11 12:53
 */
@Rollback
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = DemoTestApplication.class)
public class DemoTest {
    @Resource
    private AfterSaleServiceImpl afterSaleService;
    @Resource
    private LeaveServiceImpl leaveService;
    @Resource
    private CallActivityDemoRunner activityDemoRunner;
    @Resource
    private CommonDemoRunner commonDemoRunner;

    @Test
    @Order(1)
    public void runAfterSaleDemo(){
        afterSaleService.run();
    }

    @Test
    @Order(2)
    public void runLeaveDemo(){
        leaveService.run();
    }

    @Test
    @Order(3)
    public void runActivityDemo(){
        activityDemoRunner.run();
    }

    @Test
    @Order(4)
    public void runCommonDemo(){
        commonDemoRunner.run();
    }
}
