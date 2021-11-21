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

CREATE TABLE `baidu_top_news_all` (
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

CREATE TABLE `user_email_info` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `email` varchar(64) NOT NULL,
    `email_status` varchar(3) NOT NULL,
    `create_time` datetime NOT NULL,
    `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `news_mail_record` (
    `id` varchar(64) NOT NULL COMMENT '主键',
    `batch_id` varchar(64) NOT NULL COMMENT '批次号',
    `channel_type` varchar(3) NOT NULL COMMENT '消息渠道类型',
    `has_mail` varchar(3) NOT NULL DEFAULT '0' COMMENT '是否已经发送过邮件',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '最后更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;