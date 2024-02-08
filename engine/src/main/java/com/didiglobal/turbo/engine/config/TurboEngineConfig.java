package com.didiglobal.turbo.engine.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.didiglobal.turbo.engine")
@MapperScan("com.didiglobal.turbo.engine.dao.mapper")
public class TurboEngineConfig {

}
