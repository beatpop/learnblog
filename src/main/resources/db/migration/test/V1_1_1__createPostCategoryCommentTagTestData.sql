# 新增博客相关测试数据
# 测试用户（testBlog / admin）
INSERT INTO user (`id`, `username`, `password`, `salt`)
    VALUES ('100', 'testBlog', '13ae90938f0762980ff6f44705d41d4732e1dff3c2a6a4ed543b8a81b6f220f1', 'estfxNJh0HSEt1MM');
# 测试用户（testBlog2 / admin）
INSERT INTO user (`id`, `username`, `password`, `salt`)
    VALUES ('101', 'testBlog2', '13ae90938f0762980ff6f44705d41d4732e1dff3c2a6a4ed543b8a81b6f220f1', 'estfxNJh0HSEt1MM');

# 博客数据
INSERT INTO `post` (`id`, `title`, `content`, `user_id`) VALUES ('1', 'Java学习', '学习java啦啦', '100');

# 类别
INSERT INTO `category` (`id`, `name`, `description`, `user_id`) VALUES ('1', 'Java', '关于java', '100');

# 标签
INSERT INTO `tag` (`id`, `name`, `user_id`) VALUES ('1', 'Java', '100');

# 评论
INSERT INTO `comment` (`id`, `content`, `user_id`, `post_id`) VALUES ('1', '这java说的可以', '101', '1');

# 博客关联类别以及关联标签
INSERT INTO `post_category` (`post_id`, `category_id`) VALUES ('1', '1');
INSERT INTO `post_tag` (`post_id`, `tag_id`) VALUES ('1', '1');
