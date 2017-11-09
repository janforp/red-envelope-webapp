DROP TABLE IF EXISTS wx_share_mission;
CREATE TABLE `wx_share_mission` (
  `mission_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id，自增长',
  `mission_title` varchar(128) DEFAULT NULL COMMENT '任务名字',
  `mission_icon` varchar(128) DEFAULT NULL COMMENT '任务图标',
  `mission_desc` varchar(256) DEFAULT NULL COMMENT '任务描述',
  `prize_rate` tinyint(3) DEFAULT '50' COMMENT '中奖概率 0-100',
  `min_money` int(6) DEFAULT '100' COMMENT '最小金额(分)',
  `max_money` int(6) DEFAULT '100' COMMENT '最大金额(分)',
  `prize_times` tinyint(3) DEFAULT '1' COMMENT '允许中奖次数',
  `lottery_times` tinyint(3) DEFAULT '1' COMMENT '每个用户允许抽奖次数',
  `total_red_num` int(10) DEFAULT '0' COMMENT '红包总数量',
  `left_red_num` int(10) DEFAULT '0' COMMENT '剩余红包数量',
  `verify_ip` tinyint(1) DEFAULT '0' COMMENT '是否验证IP地址,0:不需要,1:需要',
  `is_encrypted` tinyint(1) DEFAULT '0' COMMENT '是否加密，0:不需要加密，1:需要加密',
  `merchant_secret` varchar(32) DEFAULT NULL COMMENT '商户加密密钥',
  `mission_province` varchar(20) DEFAULT NULL COMMENT '省,如:浙江。则只有IP是浙江的用户才能参加此活动',
  `mission_city` varchar(20) DEFAULT NULL COMMENT '市/县,如:杭州。则只有IP是杭州的用户才能参加此活动',
  `merchant_name` varchar(128) DEFAULT NULL COMMENT '商家名字',
  `share_url` varchar(500) DEFAULT NULL COMMENT '分享链接(按客户要求)',
  `share_title` varchar(100) DEFAULT NULL COMMENT '分享标题(按客户要求)',
  `share_img` varchar(500) DEFAULT NULL COMMENT '分享图片(按客户要求)',
  `share_desc` varchar(500) DEFAULT NULL COMMENT '分享描述(按客户要求)',
  `share_type` varchar(20) DEFAULT NULL COMMENT '分享类型:music、video或link，不填默认为link',
  `share_dataUrl` varchar(500) DEFAULT NULL COMMENT '分享数据链接,如果type是music或video，则要提供数据链接，默认为空',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  `success_img` varchar(500) DEFAULT NULL COMMENT '领取红包成功页面图片',
  `fail_img` varchar(500) DEFAULT NULL COMMENT '没有获得红包页面图片',
  `open_img` varchar(500) DEFAULT NULL COMMENT '点击打开红包页面图片',
  `is_end` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '活动是否已经结束，0:已结束，1:进行中',
  PRIMARY KEY (`mission_id`)
) ENGINE=InnoDB COMMENT='分享任务';


DROP TABLE IF EXISTS wx_share_red_detail;
CREATE TABLE `wx_share_red_detail` (
  `red_id`            BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  `flag`              TINYINT(1) DEFAULT 0 COMMENT '0:未发送,1:成功,2:发送失败',
  `mission_id`        BIGINT UNSIGNED  COMMENT '任务ID',
  `user_id`           BIGINT UNSIGNED  COMMENT '领取人ID',
  `money`             int(6) DEFAULT 1000 COMMENT '金额(分)',
  `red_status`        TINYINT(1) DEFAULT 0 COMMENT '是否被领取,0:未领取,1:领取,2:领取失败',
  `receive_time`      varchar(20) NULL COMMENT '领取时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`red_id`)
) ENGINE=InnoDB COMMENT='分享红包详情';


DROP TABLE IF EXISTS wx_share_user;
CREATE TABLE wx_share_user(
  `user_id`         BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id，自增长',
  `open_id`         VARCHAR(40) NOT NULL COMMENT '微信openid',
  `user_ip`         VARCHAR(40) NULL COMMENT '用户ip',
  `update_time`     varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  `create_time`     varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB COMMENT='分享用户表';


DROP TABLE IF EXISTS wx_share_user_relation;
CREATE TABLE wx_share_user_relation(
  `user_id`         BIGINT UNSIGNED NOT NULL COMMENT '用户id',
  `mission_id`      BIGINT UNSIGNED NOT NULL COMMENT '任务id',
  `prize_times`     TINYINT(3) DEFAULT 1 COMMENT '中奖次数',
  `lottery_times`   TINYINT(3) DEFAULT 1 COMMENT '抽奖次数',
  `update_time`     varchar(20) NULL COMMENT '更新时间,如:2016-08-18 12:53:30',
  `create_time`     varchar(20) NULL COMMENT '创建时间,如:2016-08-18 12:53:30',
  PRIMARY KEY (`user_id`,`mission_id`)
) ENGINE=InnoDB COMMENT='用户任务关联表';




