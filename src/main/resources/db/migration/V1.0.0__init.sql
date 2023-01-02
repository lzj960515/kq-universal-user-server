CREATE TABLE `tbl_organization` (
                                    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                                    `name` varchar(20) NOT NULL COMMENT '机构名称',
                                    `state` tinyint(1) NOT NULL COMMENT '状态，1启用 0停用',
                                    `main` tinyint(1) NOT NULL COMMENT '主机构，1是 0否',
                                    `summary` varchar(128) NOT NULL COMMENT '机构介绍',
                                    `logo` varchar(256) NOT NULL COMMENT 'logo',
                                    `picture` varchar(256) NOT NULL COMMENT '机构大图',
                                    `carousel` json NOT NULL COMMENT '轮播图',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='机构管理';

CREATE TABLE `tbl_user` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(20) NOT NULL COMMENT '姓名',
                            `phone` varchar(50) NOT NULL COMMENT '联系电话',
                            `account` varchar(50) NOT NULL COMMENT '登录账号',
                            `password` varchar(256) NOT NULL COMMENT '登录密码',
                            `org_id` bigint(20) NOT NULL COMMENT '机构id',
                            `dep_id` bigint(20) NOT NULL COMMENT '部门id',
                            `state` tinyint(1) NOT NULL COMMENT '状态，1启用 0停用',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_phone` (`phone`),
                            UNIQUE KEY `uk_account` (`account`),
                            KEY `idx_oid` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号管理';

CREATE TABLE `tbl_role` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
                            `name` varchar(20) NOT NULL COMMENT '角色名称',
                            `state` tinyint(1) NOT NULL COMMENT '状态，1启用 0停用',
                            `data_auth` tinyint(3) NOT NULL COMMENT '数据权限，1全部权限 2本机构数据',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号管理';

CREATE TABLE `tbl_auth` (
                            `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '功能权限id',
                            `name` varchar(64) NOT NULL COMMENT '资源名称',
                            `type` varchar(64) NOT NULL COMMENT '资源类型 1.目录 2.菜单 3.按钮(绑定接口)',
                            `url` varchar(255) NOT NULL DEFAULT '' COMMENT '资源地址',
                            `method` varchar(10) NOT NULL DEFAULT '' COMMENT '请求方式',
                            `code` varchar(64) NOT NULL DEFAULT '' COMMENT '权限标识',
                            `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'parent_id',
                            `weight` int(11) NOT NULL DEFAULT '0' COMMENT '权重',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限管理';

CREATE TABLE `tbl_user_role` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色关联id',
                                 `user_id` bigint(20) NOT NULL COMMENT '用户id',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色id',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_uid` (`user_id`),
                                 KEY `idx_rid` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号-角色';

CREATE TABLE `tbl_role_auth` (
                                 `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色权限关联id',
                                 `role_id` bigint(20) NOT NULL COMMENT '角色id',
                                 `auth_id` bigint(20) NOT NULL COMMENT '权限id',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_rid` (`role_id`),
                                 KEY `idx_aid` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-权限';