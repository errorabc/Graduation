/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : graduation

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-03-04 14:02:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(255) NOT NULL,
  `username` varchar(64) NOT NULL,
  `operation` varchar(64) NOT NULL,
  `time` int(11) NOT NULL,
  `method` varchar(255) NOT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('0b880e1b-5990-4747-a7f7-30235edb3f4c', 'admin', '添加用户', '195', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@88a4c9description: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:50:00');
INSERT INTO `sys_log` VALUES ('1', 'root', '添加用户', '111', '11111', '11111', '11111', '2020-03-01 22:56:58');
INSERT INTO `sys_log` VALUES ('2', 'admin', '添加用户', '1111', '11111', '1111', '1111', '2020-03-27 22:57:54');
INSERT INTO `sys_log` VALUES ('333', 'admin', '添加用户', '111', '111', '111', '111', '2020-03-12 22:58:12');
INSERT INTO `sys_log` VALUES ('4444', 'admin', '添加用户', '1111', '1111', '111', '111', '2020-03-19 22:58:28');
INSERT INTO `sys_log` VALUES ('555', 'admin', '添加用户', '111', '1111', '1111', '111', '2020-03-06 22:58:50');
INSERT INTO `sys_log` VALUES ('6666', 'admin', '添加用户', '1111', '2222', '2222', '22', '2020-03-06 22:59:12');
INSERT INTO `sys_log` VALUES ('6dc1862c-e385-4c3a-9be0-b8f15d2297ef', 'admin', '添加用户', '142', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@4e5a8852description: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:48:34');
INSERT INTO `sys_log` VALUES ('777', 'admin', '添加用户', '111', '2222', '333', '444', '2020-03-07 22:59:25');
INSERT INTO `sys_log` VALUES ('7d3b9d7d-ecf2-4abd-8f5c-da9cb773b0d7', 'admin', '添加用户', '336', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@2d3b66fdescription: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:47:04');
INSERT INTO `sys_log` VALUES ('9d041d2f-ea49-46c7-9d1f-e140a1990cfe', 'admin', '添加用户', '248', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@712bc222description: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:48:19');
INSERT INTO `sys_log` VALUES ('aa7cf812-386d-4492-89f2-8296bfe47613', 'admin', '添加用户', '173', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@52857b7fdescription: 用户', '0:0:0:0:0:0:0:1', '2020-02-25 14:09:06');

-- ----------------------------
-- Table structure for `sys_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(10) NOT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources` VALUES ('01f464d6-32df-4fc2-93b8-29e5e115e594', '修改权限', '9b7d4d55-5ea0-5718-a378-cf5f56476500', '/roleinfo/updateroles', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('194f5614-68e9-4794-bdef-d3c3624a488c', '测试1菜单1', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034', '/aaaaa', '2', null, '1');
INSERT INTO `sys_resources` VALUES ('2936ac60-77f4-43a0-b350-ac108a1442a0', '测试2菜单2', '3a884955-44e7-4d08-82af-d9d728f4d67d', '/aaaaa', '2', null, '1');
INSERT INTO `sys_resources` VALUES ('2af29df4-fe76-474f-98e4-fecbfb4d1966', '测试1菜单2', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034', '/aaaa', '2', null, '1');
INSERT INTO `sys_resources` VALUES ('2ebcabbf-dc78-454f-a871-d753904e2a5d', '删除权限', '9b7d4d55-5ea0-5718-a378-cf5f56476500', '/roleinfo/deleteroles', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('3a884955-44e7-4d08-82af-d9d728f4d67d', '宠物管理', '0', '/ceshi2', '1', null, '1');
INSERT INTO `sys_resources` VALUES ('47158fd3-8b1b-517c-a317-5f41415986c3', '修改用户', '635b0533-9544-5b99-9268-23eb485ed2bb', '/userinfo/updateusers', '3', 'user:update', '4');
INSERT INTO `sys_resources` VALUES ('48965adf-766e-5b70-bf38-75e5ed74a191', '添加用户', '635b0533-9544-5b99-9268-23eb485ed2bb', '/userinfo/addusers', '3', 'user:add', '2');
INSERT INTO `sys_resources` VALUES ('5a65d264-046f-4b8a-a265-11b88e5768a3', '测试2菜单1', '3a884955-44e7-4d08-82af-d9d728f4d67d', '/aaaa', '2', null, '1');
INSERT INTO `sys_resources` VALUES ('5ab29e13-1370-42b7-b6cd-c71f28617cd0', '日志管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/syslog', '2', null, '1');
INSERT INTO `sys_resources` VALUES ('5c4fbc0f-ed78-4a20-b112-77cc02b3d034', '测试目录1', '0', '/ceshi', '1', null, '1');
INSERT INTO `sys_resources` VALUES ('5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '菜单管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/menuinfo', '2', null, '1');
INSERT INTO `sys_resources` VALUES ('635b0533-9544-5b99-9268-23eb485ed2bb', '用户管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/userinfo', '2', 'user:user', '1');
INSERT INTO `sys_resources` VALUES ('643b4957-7b52-4240-88a1-653d65e93d55', '角色管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/userroleinfo', '2', '', '1');
INSERT INTO `sys_resources` VALUES ('890d35fd-be5f-4c8c-ae31-a171eb32604d', '删除菜单', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '/menuinfo/deletemenus', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('89618ef6-955e-4a38-990b-a11ac9741156', '删除角色', '643b4957-7b52-4240-88a1-653d65e93d55', '/useroleinfo/deleteuserrole', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('8fe95670-2d71-45be-98c0-90c08901936c', '添加角色', '643b4957-7b52-4240-88a1-653d65e93d55', '/userroleinfo/adduserrole', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('918e4f69-ac25-48cb-9dd4-77298b76b3c7', '添加菜单', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '/menuinfo/addmenus', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('9b7d4d55-5ea0-5718-a378-cf5f56476500', '权限管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/roleinfo', '2', null, '2');
INSERT INTO `sys_resources` VALUES ('9dc89dc7-2a26-4cb5-9d65-5cef0bcb2617', '修改角色', '643b4957-7b52-4240-88a1-653d65e93d55', '/userroleinfo/updateuserrole', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('bb775c92-bba3-4ec9-b76e-6233af254f5e', '系统管理', '0', '/admininfo', '1', null, '1');
INSERT INTO `sys_resources` VALUES ('eb0a7a36-31c6-4137-aa46-58529a3a7371', '添加权限', '9b7d4d55-5ea0-5718-a378-cf5f56476500', '/roleinfo/addroles', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('f9dd036d-95b3-48f7-9d44-8071f4d10a62', '修改菜单', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '/menuinfo/updatemenus', '3', null, '1');
INSERT INTO `sys_resources` VALUES ('fb285405-c3a9-5f62-b412-dabe9aa84faf', '删除用户', '635b0533-9544-5b99-9268-23eb485ed2bb', '/userinfo/deleteusers', '3', 'user:deleteuser', '3');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(255) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('17ccc725-04f2-4cfe-af92-466a758bcbda', 'role:user', '用户', null, null);
INSERT INTO `sys_role` VALUES ('2424109d-def4-5069-8c3b-c15bab8eadc6', 'role:root', '超级管理员', null, null);
INSERT INTO `sys_role` VALUES ('380f5394-2448-4530-b99c-91712ffeed06', 'role:admin', '管理员', null, null);

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('41ed2987-1fb8-4973-98a0-369517802ea2', '666666', 'abd85efdb8c9eb3e9423e4c50c4e3806', '1');
INSERT INTO `sys_user` VALUES ('49986241-dcc7-4130-b12d-d47dd46eb35b', '222222', '4a25bdf742ee46f66745f4d64e483b48', '1');
INSERT INTO `sys_user` VALUES ('589ca9f1-ef4b-4bbd-8731-30f2bef12a44', '654321', '94921f613c1bef770b366a002f1dfc1d', '1');
INSERT INTO `sys_user` VALUES ('62d9d149-44fe-4577-8b4c-4b774fd85183', 'admin', '0bc5321f53e909fe1c2e4c8d951baac4', '1');
INSERT INTO `sys_user` VALUES ('687a8d8b-2f61-4a82-b243-3f7c44acfd46', '12345678', '89537471db0e3a41545dc6c06a9ac048', '1');
INSERT INTO `sys_user` VALUES ('8f27f2c9-db62-43c3-a22e-d8a15cf82cfd', '111111', '404d91136ce2be61c9865c0c5cfa7721', '1');
INSERT INTO `sys_user` VALUES ('b022a3a5-bf61-5958-bbc4-d49350384c07', 'root', 'c4ef4cd970de6843a3a30ced9099341c', '1');
INSERT INTO `sys_user` VALUES ('bb58ee29-263d-4830-a5c3-83666d1e4707', '333333', '757d28726894e6dd71a9a89827a961a0', '1');
INSERT INTO `sys_user` VALUES ('d6f9de15-6480-4e9a-860e-e15a6a801183', '444444', '0f6449a827b3f8d8ba283c6d350944b0', '1');
INSERT INTO `sys_user` VALUES ('dc47a85a-a367-4a35-8d2d-232a887fc8a0', '123456', '5fd12185d75080d1813cb3507bcc6091', '1');

-- ----------------------------
-- Table structure for `sys_user_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_resources`;
CREATE TABLE `sys_user_resources` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `resources` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_resources
-- ----------------------------
INSERT INTO `sys_user_resources` VALUES ('1', '62d9d149-44fe-4577-8b4c-4b774fd85183', '194f5614-68e9-4794-bdef-d3c3624a488c');
INSERT INTO `sys_user_resources` VALUES ('2', '62d9d149-44fe-4577-8b4c-4b774fd85183', '2936ac60-77f4-43a0-b350-ac108a1442a0');
INSERT INTO `sys_user_resources` VALUES ('3', '62d9d149-44fe-4577-8b4c-4b774fd85183', '2af29df4-fe76-474f-98e4-fecbfb4d1966');
INSERT INTO `sys_user_resources` VALUES ('4', '62d9d149-44fe-4577-8b4c-4b774fd85183', '3a884955-44e7-4d08-82af-d9d728f4d67d');
INSERT INTO `sys_user_resources` VALUES ('5', '62d9d149-44fe-4577-8b4c-4b774fd85183', '5a65d264-046f-4b8a-a265-11b88e5768a3');
INSERT INTO `sys_user_resources` VALUES ('6', '62d9d149-44fe-4577-8b4c-4b774fd85183', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034');
INSERT INTO `sys_user_resources` VALUES ('7', '62d9d149-44fe-4577-8b4c-4b774fd85183', 'bb775c92-bba3-4ec9-b76e-6233af254f5e');
INSERT INTO `sys_user_resources` VALUES ('8', '62d9d149-44fe-4577-8b4c-4b774fd85183', '635b0533-9544-5b99-9268-23eb485ed2bb');
INSERT INTO `sys_user_resources` VALUES ('9', '62d9d149-44fe-4577-8b4c-4b774fd85183', '48965adf-766e-5b70-bf38-75e5ed74a191');
INSERT INTO `sys_user_resources` VALUES ('10', '62d9d149-44fe-4577-8b4c-4b774fd85183', 'fb285405-c3a9-5f62-b412-dabe9aa84faf');
INSERT INTO `sys_user_resources` VALUES ('11', '62d9d149-44fe-4577-8b4c-4b774fd85183', '47158fd3-8b1b-517c-a317-5f41415986c3');
INSERT INTO `sys_user_resources` VALUES ('12', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '01f464d6-32df-4fc2-93b8-29e5e115e594');
INSERT INTO `sys_user_resources` VALUES ('13', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '194f5614-68e9-4794-bdef-d3c3624a488c');
INSERT INTO `sys_user_resources` VALUES ('14', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '2936ac60-77f4-43a0-b350-ac108a1442a0');
INSERT INTO `sys_user_resources` VALUES ('15', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '2af29df4-fe76-474f-98e4-fecbfb4d1966');
INSERT INTO `sys_user_resources` VALUES ('16', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '2ebcabbf-dc78-454f-a871-d753904e2a5d');
INSERT INTO `sys_user_resources` VALUES ('17', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '3a884955-44e7-4d08-82af-d9d728f4d67d');
INSERT INTO `sys_user_resources` VALUES ('18', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '47158fd3-8b1b-517c-a317-5f41415986c3');
INSERT INTO `sys_user_resources` VALUES ('19', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '48965adf-766e-5b70-bf38-75e5ed74a191');
INSERT INTO `sys_user_resources` VALUES ('20', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '5a65d264-046f-4b8a-a265-11b88e5768a3');
INSERT INTO `sys_user_resources` VALUES ('21', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034');
INSERT INTO `sys_user_resources` VALUES ('22', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a');
INSERT INTO `sys_user_resources` VALUES ('23', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '635b0533-9544-5b99-9268-23eb485ed2bb');
INSERT INTO `sys_user_resources` VALUES ('24', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '643b4957-7b52-4240-88a1-653d65e93d55');
INSERT INTO `sys_user_resources` VALUES ('25', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '890d35fd-be5f-4c8c-ae31-a171eb32604d');
INSERT INTO `sys_user_resources` VALUES ('26', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '89618ef6-955e-4a38-990b-a11ac9741156');
INSERT INTO `sys_user_resources` VALUES ('27', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '8fe95670-2d71-45be-98c0-90c08901936c');
INSERT INTO `sys_user_resources` VALUES ('28', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '918e4f69-ac25-48cb-9dd4-77298b76b3c7');
INSERT INTO `sys_user_resources` VALUES ('29', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '9b7d4d55-5ea0-5718-a378-cf5f56476500');
INSERT INTO `sys_user_resources` VALUES ('30', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '9dc89dc7-2a26-4cb5-9d65-5cef0bcb2617');
INSERT INTO `sys_user_resources` VALUES ('31', 'b022a3a5-bf61-5958-bbc4-d49350384c07', 'bb775c92-bba3-4ec9-b76e-6233af254f5e');
INSERT INTO `sys_user_resources` VALUES ('32', 'b022a3a5-bf61-5958-bbc4-d49350384c07', 'eb0a7a36-31c6-4137-aa46-58529a3a7371');
INSERT INTO `sys_user_resources` VALUES ('33', 'b022a3a5-bf61-5958-bbc4-d49350384c07', 'f9dd036d-95b3-48f7-9d44-8071f4d10a62');
INSERT INTO `sys_user_resources` VALUES ('34', 'b022a3a5-bf61-5958-bbc4-d49350384c07', 'fb285405-c3a9-5f62-b412-dabe9aa84faf');
INSERT INTO `sys_user_resources` VALUES ('35', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '5ab29e13-1370-42b7-b6cd-c71f28617cd0');
INSERT INTO `sys_user_resources` VALUES ('36', '62d9d149-44fe-4577-8b4c-4b774fd85183', '5ab29e13-1370-42b7-b6cd-c71f28617cd0');
INSERT INTO `sys_user_resources` VALUES ('37', '62d9d149-44fe-4577-8b4c-4b774fd85183', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `roleid` varchar(255) NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=575 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '2424109d-def4-5069-8c3b-c15bab8eadc6', null, null);
INSERT INTO `sys_user_role` VALUES ('2', '62d9d149-44fe-4577-8b4c-4b774fd85183', '380f5394-2448-4530-b99c-91712ffeed06', null, null);
INSERT INTO `sys_user_role` VALUES ('557', 'dc47a85a-a367-4a35-8d2d-232a887fc8a0', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('558', '589ca9f1-ef4b-4bbd-8731-30f2bef12a44', '380f5394-2448-4530-b99c-91712ffeed06', null, null);
INSERT INTO `sys_user_role` VALUES ('567', '687a8d8b-2f61-4a82-b243-3f7c44acfd46', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('570', '8f27f2c9-db62-43c3-a22e-d8a15cf82cfd', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('571', '49986241-dcc7-4130-b12d-d47dd46eb35b', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('572', 'bb58ee29-263d-4830-a5c3-83666d1e4707', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('573', 'd6f9de15-6480-4e9a-860e-e15a6a801183', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('574', '41ed2987-1fb8-4973-98a0-369517802ea2', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
