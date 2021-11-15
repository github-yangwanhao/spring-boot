CREATE TABLE `baidu_top_news` (
    `id` varchar(64) NOT NULL COMMENT '主键',
    `batch_id` varchar(64) NOT NULL COMMENT '批次号',
    `order_num` int(3) NOT NULL COMMENT '序号',
    `title` varchar(255) NOT NULL COMMENT '标题',
    `image_url` varchar(1000) DEFAULT NULL COMMENT '缩略图url',
    `detail` varchar(1000) DEFAULT NULL COMMENT '详细描述',
    `query_url` varchar(1000) DEFAULT NULL COMMENT '查询详情url',
    `hot_score` bigint(20) DEFAULT NULL COMMENT '热度分数',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_title` (`title`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;