CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `dob` date NOT NULL COMMENT 'date of birth',
  `gender` enum('m','f') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `country` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `countryname` varchar(255) DEFAULT NULL COMMENT '国家名称',
  `countrycode` varchar(255) DEFAULT NULL COMMENT '国家代码',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* freemarker 数据库读取模板测试  */

CREATE TABLE `t_template` (
  `id` int(11) NOT NULL,
  `status_key` varchar(20) NOT NULL,
  `template` text,
  `last_modified` datetime DEFAULT NULL,
  `lang` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `status_key` (`status_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

insert into t_template(id,status_key,template,last_modified,lang)
    values(1,'key','名称：${name},信息:${msg}',now(),'zh_CN');


CREATE TABLE `brand` (
	/* bigint 10亿亿 */
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` VARCHAR(40) NOT NULL COMMENT '名称',
  `description` VARCHAR(80) DEFAULT NULL COMMENT '描述',
  `img_url` VARCHAR(80) DEFAULT NULL COMMENT '图片Url',
  `web_site` VARCHAR(80) DEFAULT NULL COMMENT '品牌网址',
  /* 10亿 */
  `sort` INT(11) DEFAULT NULL COMMENT '排序:最大最排前',
  /* -128到127 */
  `is_display` TINYINT(1) DEFAULT NULL COMMENT '是否可见 1:可见 0:不可见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
