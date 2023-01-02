INSERT INTO `tbl_role` (`id`, `name`, `state`, `data_auth`, `create_time`, `update_time`)
VALUES
    (1, '管理员', 1, 1, '2022-11-16 16:00:46', '2022-11-16 16:00:46');

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

