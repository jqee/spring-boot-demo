
DROP TABLE IF EXISTS repo_resource;
CREATE TABLE `repo_resource` (
  `guid` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(32) DEFAULT NULL COMMENT '资源编码',
  `res_type` varchar(32) DEFAULT NULL COMMENT '资源类型',
  `name` varchar(128) DEFAULT NULL COMMENT '资源名称',
  `version` varchar(32) DEFAULT NULL COMMENT '版本',
  `provider` varchar(128) DEFAULT NULL COMMENT '资源提供者',
  `status` int(2) DEFAULT NULL COMMENT '状态:0草稿，1修改中，2审批中，3审批通过，4审批拒绝，5在线（上架），6已下线',
  `creator` varchar(128) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(128) DEFAULT NULL COMMENT '更新人',
  `publisher` varchar(128) DEFAULT NULL COMMENT '发布人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` int(1) DEFAULT NULL COMMENT '删除标识：0未删除，1删除',
  `updater_name` varchar(128) DEFAULT NULL COMMENT '修改人名称',
  `creator_name` varchar(128) DEFAULT NULL COMMENT '创建人名称',
  `publisher_name` varchar(128) DEFAULT NULL COMMENT '发布人名称',
  PRIMARY KEY (`guid`),
  UNIQUE KEY `res_type_code_version` (`res_type`,`code`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表,存储表单模型、流程引用';

DROP TABLE IF EXISTS repo_catalog;
CREATE TABLE repo_catalog
(
        guid BIGINT(20),
        name VARCHAR(64),
        parent_id BIGINT(20),
        root_id BIGINT(20),
        full_name_path VARCHAR(255),
        full_id_path VARCHAR(255),
        levels INT,
        sort_num INT,
        comment  VARCHAR(255),
        PRIMARY KEY (guid)
);

DROP TABLE IF EXISTS repo_catalog_res;
CREATE TABLE repo_catalog_res
(
        guid VARCHAR(32) comment '主键',
        catalog_id VARCHAR(32) comment '目录id',
        root_id VARCHAR(32) comment '根目录id',
        code VARCHAR(32) comment '资源编码',
        name VARCHAR(128) comment '资源名称',
        res_type varchar(32) DEFAULT NULL COMMENT '资源类型',
        PRIMARY KEY (guid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录资源关系表';

DROP TABLE IF EXISTS repo_res_content;
CREATE TABLE repo_res_content
(
        res_id VARCHAR(32) comment '主键',
        content_text TEXT comment '资源内容',
        PRIMARY KEY (res_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源内容表';

DROP TABLE IF EXISTS repo_res_tag;
CREATE TABLE repo_res_tag
(
        guid VARCHAR(32) NOT NULL comment '主键',
        res_id VARCHAR(32) comment '资源编号',
        tag_name VARCHAR(32) comment '标签名',
        tag_value VARCHAR(255) comment '标签值',
        is_deleted int(1) DEFAULT NULL COMMENT '删除标识：0未删除，1删除',
        PRIMARY KEY (guid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源标签表';

DROP TABLE IF EXISTS repo_res_relation;
CREATE TABLE repo_res_relation
(
        guid VARCHAR(32) comment '主键',
        source_id VARCHAR(32) comment '源模型，即表单资源id',
        source_key VARCHAR(255) comment '源模型键：由资源表中的res_type_code_version拼接',
        target_id VARCHAR(32) comment '目标模型',
        target_key VARCHAR(255) comment '目标键',
        PRIMARY KEY (guid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源关系表';
