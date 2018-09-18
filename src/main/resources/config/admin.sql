create database if not exists `admin`
default character set utf8
default collate = utf8_general_ci;

create table if not exists admin.`user`
(
	`id` int(11) not null AUTO_INCREMENT comment '主键ID',
    `username` varchar(10) null comment '用户登录名',
    `password` varchar(20) null comment '密码',
    `realname` varchar(10) null comment '真实姓名',
    `identity_card` varchar(20) null default '' comment '身份证',
    `sex` int(1) null default '2' comment '性别(1=男，0=女，2=私密)',
    `email` varchar(20)	null comment '邮箱',
    `phone` varchar(20) null comment '手机',
    `status` int(1) null default '0' comment '状态(0=可用，1=禁用)',
    `is_delete` int(1) null default '0' comment '是否删除(0=否，1=是)',
    `gmt_create` datetime null  default current_timestamp comment '创建时间',
    `gmt_modified` datetime null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`id`)

) engine=InnoDB auto_increment=1 default character set utf8 default collate utf8_general_ci comment '用户表';
