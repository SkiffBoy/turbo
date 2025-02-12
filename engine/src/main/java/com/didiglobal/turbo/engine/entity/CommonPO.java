package com.didiglobal.turbo.engine.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;

import java.util.Date;

public class CommonPO {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String tenant;
    private String caller;
    private Date createTime;
    private Integer archive = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getArchive() {
        return archive;
    }

    public void setArchive(Integer archive) {
        this.archive = archive;
    }
}
