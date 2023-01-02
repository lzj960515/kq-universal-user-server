INSERT INTO `tbl_user` (`id`, `org_id`, `dep_id`, `name`, `phone`, `account`, `password`, `state`, `create_time`, `update_time`)
VALUES
    (1, 1, 1, '张三', 'db519b651470140e0703849a5e1e90d5', 'zhangsan', '$2a$10$Xupby9OS8aqDuPhDwP.rcOGN8q/Fq0kWYfWTDC08RXUHGbWqCyhTW', 1, '2022-11-16 15:59:57', '2022-11-16 15:59:57');

INSERT INTO `tbl_role` (`id`, `name`, `state`, `data_auth`, `create_time`, `update_time`)
VALUES
    (1, '管理员', 1, 1, '2022-11-16 16:00:46', '2022-11-16 16:00:46');

INSERT INTO `tbl_user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`)
VALUES
    (1, 1, 1, '2023-01-02 17:48:49', '2023-01-02 17:48:49');


INSERT INTO `tbl_organization` (`id`, `name`, `state`, `main`, `summary`, `logo`, `picture`, `carousel`, `create_time`, `update_time`)
VALUES
    (1, '测试机构', 1, 1, '机构简介', '1', '1', '[\"1\", \"2\"]', '2023-01-02 17:37:34', '2023-01-02 17:37:34');

INSERT INTO `tbl_role_auth` (`id`, `role_id`, `auth_id`)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3);

INSERT INTO `tbl_auth` (`id`, `name`, `type`, `url`, `method`, `code`, `parent_id`, `weight`)
VALUES
    (1, 'A', '1', '', '', '', 0, 0),
    (2, 'B', '2', '', '', '', 1, 0),
    (3, 'C', '3', '/xx', 'GET', '', 2, 0);
