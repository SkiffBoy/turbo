set search_path = "t_engine";

DROP TABLE IF EXISTS em_flow_definition;
CREATE TABLE IF NOT EXISTS em_flow_definition
(
    id             bigserial                                  not null
        constraint pk_flow_definition primary key,
    flow_module_id varchar(128),
    flow_name      varchar(64),
    flow_key       varchar(32),
    tenant_id      varchar(16),
    flow_model     text,
    status         smallint     default 0                     not null,
    create_time    timestamp    default CURRENT_TIMESTAMP     not null,
    modify_time    timestamp    default '1970-01-01 00:00:00' not null,
    operator       varchar(32),
    remark         varchar(512),
    archive        smallint     default 0                     not null,
    tenant         varchar(100),
    caller         varchar(100)
);

create unique index uniq_flow_module_id on em_flow_definition (flow_module_id);

comment on table em_flow_definition is '流程定义表';
comment on column em_flow_definition.id             is '自增主键';
comment on column em_flow_definition.flow_module_id is '流程模型id';
comment on column em_flow_definition.flow_name      is '流程名称';
comment on column em_flow_definition.flow_key       is '流程业务标识';
comment on column em_flow_definition.tenant_id      is '业务方标识';
comment on column em_flow_definition.flow_model     is '表单定义';
comment on column em_flow_definition.status         is '状态(1.初始态 2.编辑中 3.已下线)';
comment on column em_flow_definition.create_time    is '流程创建时间';
comment on column em_flow_definition.modify_time    is '流程修改时间';
comment on column em_flow_definition.operator       is '操作人';
comment on column em_flow_definition.remark         is '备注';
comment on column em_flow_definition.archive        is '归档状态(0未删除，1删除)';
comment on column em_flow_definition.tenant         is '租户';
comment on column em_flow_definition.caller         is '调用方';


DROP TABLE IF EXISTS em_flow_deployment;
CREATE TABLE IF NOT EXISTS em_flow_deployment
(
    id             bigserial                                  not null
        constraint pk_flow_deployment primary key,
    flow_deploy_id varchar(128),
    flow_module_id varchar(128),
    flow_name      varchar(64),
    flow_key       varchar(32),
    tenant_id      varchar(16),
    flow_model     text,
    status         smallint     default 0                     not null,
    create_time    timestamp    default CURRENT_TIMESTAMP     not null,
    modify_time    timestamp    default '1970-01-01 00:00:00' not null,
    operator       varchar(32),
    remark         varchar(512),
    archive        smallint     default 0                     not null,
    tenant         varchar(100),
    caller         varchar(100)
);

create unique index uniq_flow_deploy_id on em_flow_deployment (flow_deploy_id);
create index idx_flow_module_id on em_flow_deployment (flow_module_id);

comment on table em_flow_deployment is '流程部署表';
comment on column em_flow_deployment.id             is '自增主键';
comment on column em_flow_deployment.flow_deploy_id is '流程模型部署id';
comment on column em_flow_deployment.flow_module_id is '流程模型id';
comment on column em_flow_deployment.flow_name      is '流程名称';
comment on column em_flow_deployment.flow_key       is '流程业务标识';
comment on column em_flow_deployment.tenant_id      is '业务方标识';
comment on column em_flow_deployment.flow_model     is '表单定义';
comment on column em_flow_deployment.status         is '状态(1.已部署 3.已下线)';
comment on column em_flow_deployment.create_time    is '流程创建时间';
comment on column em_flow_deployment.modify_time    is '流程修改时间';
comment on column em_flow_deployment.operator       is '操作人';
comment on column em_flow_deployment.remark         is '备注';
comment on column em_flow_deployment.archive        is '归档状态(0未删除，1删除)';
comment on column em_flow_deployment.tenant         is '租户';
comment on column em_flow_deployment.caller         is '调用方';


DROP TABLE IF EXISTS ei_flow_instance;
CREATE TABLE IF NOT EXISTS ei_flow_instance
(
    id                      bigserial                                  not null
        constraint pk_flow_instance primary key,
    flow_instance_id        varchar(128),
    parent_flow_instance_id varchar(128),
    flow_deploy_id          varchar(128),
    flow_module_id          varchar(128),
    tenant_id               varchar(16),
    status                  smallint     default 0                     not null,
    create_time             timestamp    default CURRENT_TIMESTAMP     not null,
    modify_time             timestamp    default '1970-01-01 00:00:00' not null,
    archive                 smallint     default 0                     not null,
    tenant                  varchar(100),
    caller                  varchar(100)
);

create unique index uniq_flow_instance_id on ei_flow_instance (flow_instance_id);

comment on table ei_flow_instance is '流程执行实例表';
comment on column ei_flow_instance.id                      is '自增主键';
comment on column ei_flow_instance.flow_instance_id        is '流程执行实例id';
comment on column ei_flow_instance.parent_flow_instance_id is '父流程执行实例id';
comment on column ei_flow_instance.flow_deploy_id          is '流程模型部署id';
comment on column ei_flow_instance.flow_module_id          is '流程模型id';
comment on column ei_flow_instance.tenant_id               is '业务方标识';
comment on column ei_flow_instance.status                  is '状态(1.执行完成 2.执行中 3.执行终止(强制终止))';
comment on column ei_flow_instance.create_time             is '流程创建时间';
comment on column ei_flow_instance.modify_time             is '流程修改时间';
comment on column ei_flow_instance.archive                 is '归档状态(0未删除，1删除)';
comment on column ei_flow_instance.tenant                  is '租户';
comment on column ei_flow_instance.caller                  is '调用方';


DROP TABLE IF EXISTS ei_flow_instance_mapping;
CREATE TABLE ei_flow_instance_mapping
(
    id                   bigserial                                  not null
        constraint pk_flow_instance_mapping primary key,
    flow_instance_id     varchar(128),
    node_instance_id     varchar(128),
    node_key             varchar(64),
    sub_flow_instance_id varchar(128),
    type                 smallint     default 0                     not null,
    create_time          timestamp    default CURRENT_TIMESTAMP     not null,
    modify_time          timestamp    default '1970-01-01 00:00:00' not null,
    archive              smallint     default 0                     not null,
    tenant               varchar(100) default 'didi'                not null,
    caller               varchar(100) default 'optimus-prime'       not null
);

create index idx_fii on ei_flow_instance_mapping (flow_instance_id);

comment on table ei_flow_instance is '父子流程实例映射表';
comment on column ei_flow_instance_mapping.id                   is '自增主键';
comment on column ei_flow_instance_mapping.flow_instance_id     is '流程执行实例id';
comment on column ei_flow_instance_mapping.node_instance_id     is '节点执行实例id';
comment on column ei_flow_instance_mapping.node_key             is '节点唯一标识';
comment on column ei_flow_instance_mapping.sub_flow_instance_id is '子流程执行实例id';
comment on column ei_flow_instance_mapping.type                 is '状态(1.执行 2.回滚)';
comment on column ei_flow_instance_mapping.create_time          is '流程创建时间';
comment on column ei_flow_instance_mapping.modify_time          is '流程修改时间';
comment on column ei_flow_instance_mapping.archive              is '归档状态(0未删除，1删除)';
comment on column ei_flow_instance_mapping.tenant               is '租户';
comment on column ei_flow_instance_mapping.caller               is '调用方';


DROP TABLE IF EXISTS ei_node_instance;
CREATE TABLE IF NOT EXISTS ei_node_instance
(
    id                      bigserial                                  not null
        constraint pk_node_instance primary key,
    node_instance_id        varchar(128),
    flow_instance_id        varchar(128),
    source_node_instance_id varchar(128),
    instance_data_id        varchar(128),
    flow_deploy_id          varchar(128),
    node_key                varchar(64),
    source_node_key         varchar(64),
    tenant_id               varchar(16),
    status                  smallint     default 0                     not null,
    create_time             timestamp    default CURRENT_TIMESTAMP     not null,
    modify_time             timestamp    default '1970-01-01 00:00:00' not null,
    archive                 smallint     default 0                     not null,
    tenant                  varchar(100),
    caller                  varchar(100)
);

create unique index uniq_node_instance_id on ei_node_instance (node_instance_id);
create index idx_fiid_sniid_nk on ei_node_instance (flow_instance_id, source_node_instance_id, node_key);

comment on table ei_flow_instance is '节点执行实例表';
comment on column ei_node_instance.id                      is '自增主键';
comment on column ei_node_instance.node_instance_id        is '节点执行实例id';
comment on column ei_node_instance.flow_instance_id        is '流程执行实例id';
comment on column ei_node_instance.source_node_instance_id is '上一个节点执行实例id';
comment on column ei_node_instance.instance_data_id        is '实例数据id';
comment on column ei_node_instance.flow_deploy_id          is '流程模型部署id';
comment on column ei_node_instance.node_key                is '节点唯一标识';
comment on column ei_node_instance.source_node_key         is '上一个流程节点唯一标识';
comment on column ei_node_instance.tenant_id               is '业务方标识';
comment on column ei_node_instance.status                  is '状态(1.处理成功 2.处理中 3.处理失败 4.处理已撤销)';
comment on column ei_node_instance.create_time             is '流程创建时间';
comment on column ei_node_instance.modify_time             is '流程修改时间';
comment on column ei_node_instance.archive                 is '归档状态(0未删除，1删除)';
comment on column ei_node_instance.tenant                  is '租户';
comment on column ei_node_instance.caller                  is '调用方';


DROP TABLE IF EXISTS ei_node_instance_log;
CREATE TABLE IF NOT EXISTS ei_node_instance_log
(
    id               bigserial                              not null
        constraint pk_node_instance_log primary key,
    node_instance_id varchar(128),
    flow_instance_id varchar(128),
    instance_data_id varchar(128),
    node_key         varchar(64),
    tenant_id        varchar(16),
    type             smallint     default 0                 not null,
    status           smallint     default 0                 not null,
    create_time      timestamp    default CURRENT_TIMESTAMP not null,
    archive          smallint     default 0                 not null,
    tenant           varchar(100),
    caller           varchar(100)
);

comment on table ei_node_instance_log is '节点执行记录表';
comment on column ei_node_instance_log.id                is '自增主键';
comment on column ei_node_instance_log.node_instance_id  is '节点执行实例id';
comment on column ei_node_instance_log.flow_instance_id  is '流程执行实例id';
comment on column ei_node_instance_log.instance_data_id  is '实例数据id';
comment on column ei_node_instance_log.node_key          is '节点唯一标识';
comment on column ei_node_instance_log.tenant_id         is '业务方标识';
comment on column ei_node_instance_log.type              is '操作类型(1.系统执行 2.任务提交 3.任务撤销)';
comment on column ei_node_instance_log.status            is '状态(1.处理成功 2.处理中 3.处理失败 4.处理已撤销)';
comment on column ei_node_instance_log.create_time       is '流程创建时间';
comment on column ei_node_instance_log.archive           is '归档状态(0未删除，1删除)';
comment on column ei_node_instance_log.tenant            is '租户';
comment on column ei_node_instance_log.caller            is '调用方';


DROP TABLE IF EXISTS ei_instance_data;
CREATE TABLE IF NOT EXISTS ei_instance_data
(
    id               bigserial                              not null
        constraint pk_instance_data primary key,
    node_instance_id varchar(128),
    flow_instance_id varchar(128),
    instance_data_id varchar(128),
    flow_deploy_id   varchar(128),
    flow_module_id   varchar(128),
    node_key         varchar(64),
    tenant_id        varchar(16),
    instance_data    text,
    type             smallint     default 0                 not null,
    create_time      timestamp    default CURRENT_TIMESTAMP not null,
    archive          smallint     default 0                 not null,
    tenant           varchar(100),
    caller           varchar(100)
);

create unique index uniq_instance_data_id on ei_instance_data (instance_data_id);
create index idx_flow_instance_id on ei_instance_data (flow_instance_id);

comment on table ei_instance_data is '实例数据表';
comment on column ei_instance_data.id               is '自增主键';
comment on column ei_instance_data.node_instance_id is '节点执行实例id';
comment on column ei_instance_data.flow_instance_id is '流程执行实例id';
comment on column ei_instance_data.instance_data_id is '实例数据id';
comment on column ei_instance_data.flow_deploy_id   is '流程模型部署id';
comment on column ei_instance_data.flow_module_id   is '流程模型id';
comment on column ei_instance_data.node_key         is '节点唯一标识';
comment on column ei_instance_data.tenant_id        is '业务方标识';
comment on column ei_instance_data.instance_data    is '数据列表json';
comment on column ei_instance_data.type             is '操作类型(1.实例初始化 2.系统执行 3.系统主动获取 4.上游更新 5.任务提交 6.任务撤回)';
comment on column ei_instance_data.create_time      is '流程创建时间';
comment on column ei_instance_data.archive          is '归档状态(0未删除，1删除)';
comment on column ei_instance_data.tenant           is '租户';
comment on column ei_instance_data.caller           is '调用方';
