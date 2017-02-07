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


