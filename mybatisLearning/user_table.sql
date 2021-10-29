/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 04/08/2020 19:52:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER`  (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `USER_NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `USER_CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `CREATETIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

create table STUDENT(
  ID int(10) PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID或标识ID',
	STUDENT_NAME VARCHAR(10) NOT NULL COMMENT '学生名字',
	TEACHER_ID INT(10) NOT NULL COMMENT '教师ID'
)ENGINE = InnoDB;

CREATE TABLE TEACHER(
	ID INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID或标识ID',
	TEACHER_NAME VARCHAR(10) NOT NULL COMMENT '教师名字'
)ENGINE = InnoDB;

CREATE TABLE `BLOG`(
    `ID` VARCHAR(50) NOT NULL COMMENT '博客id',
    `TITLE` VARCHAR(100) NOT NULL COMMENT '博客标题',
    `AUTHOR` VARCHAR(30) NOT NULL COMMENT '博客作者',
    `CREATE_TIME` DATETIME NOT NULL COMMENT '创建时间',
    `VIEWS` INT(30) NOT NULL COMMENT '浏览量'
)ENGINE=INNODB DEFAULT CHARSET=utf8

SET FOREIGN_KEY_CHECKS = 1;
