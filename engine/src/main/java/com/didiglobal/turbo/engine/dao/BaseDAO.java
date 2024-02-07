package com.didiglobal.turbo.engine.dao;

import com.mybatisflex.annotation.UseDataSource;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UseDataSource("engine")
public class BaseDAO<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IService<T> {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);
}
