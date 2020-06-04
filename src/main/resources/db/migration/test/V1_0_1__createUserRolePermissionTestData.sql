# 新增测试数据
# 新增权限
INSERT INTO permission (`id`, `name`) values ('1', 'user:index');
INSERT INTO permission (`id`, `name`) values ('2', 'user:store');
INSERT INTO permission (`id`, `name`) values ('3', 'user:delete');

# 新增角色
INSERT INTO role (`id`, `name`) values ('1', 'testAdminRole');

# 测试用户（test / admin）
INSERT INTO user (`id`, `username`, `password`, `salt`)
  values ('1', 'test', '13ae90938f0762980ff6f44705d41d4732e1dff3c2a6a4ed543b8a81b6f220f1', 'estfxNJh0HSEt1MM');

INSERT INTO role_permission (`role_id`, `permission_id`) values ('1', '1');
INSERT INTO role_permission (`role_id`, `permission_id`) values ('1', '2');
INSERT INTO role_permission (`role_id`, `permission_id`) values ('1', '3');

INSERT INTO user_role (`user_id`, `role_id`) values ('1', '1');
