/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : graduation

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-03-20 02:32:16
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
INSERT INTO `sys_log` VALUES ('00c3d0a8-8da7-4c16-b650-6cb32602c031', 'admin', '删除菜单', '48', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 5eb374c0-7074-4ade-8b03-363214704534', '0:0:0:0:0:0:0:1', '2020-03-08 15:22:53');
INSERT INTO `sys_log` VALUES ('0137de49-3624-4bf5-b02a-e53c02d1795f', 'admin', '修改菜单', '13', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@2dcf947a', '0:0:0:0:0:0:0:1', '2020-03-10 16:37:27');
INSERT INTO `sys_log` VALUES ('0369f928-63a9-4aa7-a991-66ebd91849b8', 'admin', '修改菜单', '126', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@1493dc5f', '0:0:0:0:0:0:0:1', '2020-03-10 17:08:13');
INSERT INTO `sys_log` VALUES ('0b65bf50-c13c-451c-bf59-07f27351a39d', 'admin', '修改菜单', '11', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@25eafeed', '0:0:0:0:0:0:0:1', '2020-03-10 16:37:37');
INSERT INTO `sys_log` VALUES ('0b880e1b-5990-4747-a7f7-30235edb3f4c', 'admin', '添加用户', '195', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@88a4c9description: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:50:00');
INSERT INTO `sys_log` VALUES ('1', 'root', '添加用户', '111', '11111', '11111', '11111', '2020-03-01 22:56:58');
INSERT INTO `sys_log` VALUES ('15747e7b-a7ce-496e-bb2b-d7e4c74a955b', 'admin', '添加菜单', '12', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@164448a6', '0:0:0:0:0:0:0:1', '2020-03-06 11:15:07');
INSERT INTO `sys_log` VALUES ('16006a3d-c3c8-4c7c-911a-778528bdc91a', 'admin', '添加菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@75cc7062', '0:0:0:0:0:0:0:1', '2020-03-06 10:57:05');
INSERT INTO `sys_log` VALUES ('1906b351-5b16-4997-9b05-50ee7dc4bd16', 'admin', '删除菜单', '140', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '0:0:0:0:0:0:0:1', '2020-03-08 15:18:07');
INSERT INTO `sys_log` VALUES ('1959295a-2e98-4e86-97f6-fdf77db3b62c', 'admin', '修改菜单', '9', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@46015d10', '0:0:0:0:0:0:0:1', '2020-03-10 16:35:40');
INSERT INTO `sys_log` VALUES ('2', 'admin', '添加用户', '1111', '11111', '1111', '1111', '2020-03-27 22:57:54');
INSERT INTO `sys_log` VALUES ('2660c5d0-12a9-4033-acb3-300ee0eec7ee', 'admin', '添加菜单', '81', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@ed47ef3', '0:0:0:0:0:0:0:1', '2020-03-08 15:26:35');
INSERT INTO `sys_log` VALUES ('28736889-aa55-4f8c-8f77-e5b39d97260d', 'admin', '添加菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@292b9c5f', '0:0:0:0:0:0:0:1', '2020-03-06 10:53:21');
INSERT INTO `sys_log` VALUES ('2e746743-606d-4d6a-8435-efd035776da1', 'admin', '删除菜单', '87', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 4b3aa6f9-185d-4ff0-926d-73e7e94a6ed7', '0:0:0:0:0:0:0:1', '2020-03-08 15:28:38');
INSERT INTO `sys_log` VALUES ('333', 'admin', '添加用户', '111', '111', '111', '111', '2020-03-12 22:58:12');
INSERT INTO `sys_log` VALUES ('348fb19c-2b2b-4301-954f-2917b24d06ea', 'admin', '添加按钮', '61', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddButton()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@5a71bf13', '0:0:0:0:0:0:0:1', '2020-03-08 14:10:25');
INSERT INTO `sys_log` VALUES ('3800957b-603e-4775-80a7-c7bb49162ff1', 'admin', '添加菜单', '6', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@5d59e6ce', '0:0:0:0:0:0:0:1', '2020-03-06 11:06:18');
INSERT INTO `sys_log` VALUES ('3bb9fe9d-0b75-4409-83d6-fb3574d18719', 'admin', '删除菜单', '235', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 13a92a16-7891-442f-86a6-7ac261c4ae9e', '0:0:0:0:0:0:0:1', '2020-03-08 15:20:23');
INSERT INTO `sys_log` VALUES ('3d8e5c38-7bb0-4bcd-a2e7-1e26abd9c5a2', 'admin', '修改菜单', '11', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@22598850', '0:0:0:0:0:0:0:1', '2020-03-10 16:37:48');
INSERT INTO `sys_log` VALUES ('3f3050d5-c708-4287-8eba-f335554e27c6', 'admin', '删除菜单', '152', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: d5fb897b-cd4a-4b8c-9ee8-c697a7ae50a6', '0:0:0:0:0:0:0:1', '2020-03-11 03:33:15');
INSERT INTO `sys_log` VALUES ('4035ca77-f423-4511-b172-d5d819c5fec2', 'admin', '删除菜单', '55', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: d561716d-7433-4497-a046-432394638c84', '0:0:0:0:0:0:0:1', '2020-03-11 03:24:35');
INSERT INTO `sys_log` VALUES ('4444', 'admin', '添加用户', '1111', '1111', '111', '111', '2020-03-19 22:58:28');
INSERT INTO `sys_log` VALUES ('44c3361e-f6d8-446f-86be-a252aea43e65', 'admin', '添加用户', '147', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity@445fdf41name: 用户', '0:0:0:0:0:0:0:1', '2020-03-14 16:22:53');
INSERT INTO `sys_log` VALUES ('491aea69-7e62-42aa-bad7-c9f5a279e55e', 'admin', '修改菜单', '127', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@4a84cf71', '0:0:0:0:0:0:0:1', '2020-03-10 17:03:33');
INSERT INTO `sys_log` VALUES ('4b56a628-be53-4b70-bac2-ff4ae1aeeec4', 'admin', '删除菜单', '3', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '0:0:0:0:0:0:0:1', '2020-03-08 15:15:07');
INSERT INTO `sys_log` VALUES ('4d4c232c-3be7-4cb4-8d31-1537a6a9e367', 'admin', '修改菜单', '12', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@3af629c0', '0:0:0:0:0:0:0:1', '2020-03-10 16:36:13');
INSERT INTO `sys_log` VALUES ('51f135af-189b-4017-9b35-9a09159e734d', 'admin', '添加菜单', '84', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@4c4451ee', '0:0:0:0:0:0:0:1', '2020-03-08 15:28:32');
INSERT INTO `sys_log` VALUES ('555', 'admin', '添加用户', '111', '1111', '1111', '111', '2020-03-06 22:58:50');
INSERT INTO `sys_log` VALUES ('627c7d81-bec4-4537-b737-207099fa8236', 'admin', '修改菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@353e7363', '0:0:0:0:0:0:0:1', '2020-03-10 06:59:37');
INSERT INTO `sys_log` VALUES ('62ba80a5-6f37-437e-9225-976f03ab4fb4', 'admin', '修改菜单', '89', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@491bf0ef', '0:0:0:0:0:0:0:1', '2020-03-10 17:02:46');
INSERT INTO `sys_log` VALUES ('6336a870-7d69-4eae-9002-87d08c2c9c2b', 'admin', '删除菜单', '4', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '0:0:0:0:0:0:0:1', '2020-03-08 15:15:13');
INSERT INTO `sys_log` VALUES ('661cf2be-158b-4a64-9c0d-3efb409ff7ae', 'admin', '添加菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@2b5232de', '0:0:0:0:0:0:0:1', '2020-03-06 10:50:57');
INSERT INTO `sys_log` VALUES ('6666', 'admin', '添加用户', '1111', '2222', '2222', '22', '2020-03-06 22:59:12');
INSERT INTO `sys_log` VALUES ('6dc1862c-e385-4c3a-9be0-b8f15d2297ef', 'admin', '添加用户', '142', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@4e5a8852description: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:48:34');
INSERT INTO `sys_log` VALUES ('6f35e948-9a09-4f5b-98dc-1674c27e2880', 'admin', '修改菜单', '131', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@7a147b4a', '0:0:0:0:0:0:0:1', '2020-03-10 17:07:56');
INSERT INTO `sys_log` VALUES ('708917fa-3b35-433c-992a-a8f070e4d08d', 'admin', '添加菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@273e8358', '0:0:0:0:0:0:0:1', '2020-03-06 10:53:13');
INSERT INTO `sys_log` VALUES ('70dc1e29-fb8d-43d7-9b07-b3c722d69deb', 'admin', '修改菜单', '136', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@168cd073', '0:0:0:0:0:0:0:1', '2020-03-10 17:08:25');
INSERT INTO `sys_log` VALUES ('7151bcb8-8755-40ec-9fb0-dd574cd58c74', 'admin', '修改菜单', '14', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@30a6ff79', '0:0:0:0:0:0:0:1', '2020-03-10 16:40:30');
INSERT INTO `sys_log` VALUES ('777', 'admin', '添加用户', '111', '2222', '333', '444', '2020-03-07 22:59:25');
INSERT INTO `sys_log` VALUES ('78fc5911-ad5a-4f23-aa2d-46237f3fbca7', 'admin', '添加菜单', '67', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@25a2e34a', '0:0:0:0:0:0:0:1', '2020-03-08 15:22:45');
INSERT INTO `sys_log` VALUES ('7a09f58d-3d1a-413f-b966-e5c808e7c82f', 'admin', '修改菜单', '57', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@8a7180d', '0:0:0:0:0:0:0:1', '2020-03-10 17:01:33');
INSERT INTO `sys_log` VALUES ('7acd3650-cea9-4f22-9156-930c2dd04b7a', 'admin', '添加菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@49a74de6', '0:0:0:0:0:0:0:1', '2020-03-06 10:54:38');
INSERT INTO `sys_log` VALUES ('7bae44ea-5795-4297-a76c-1995c5b7c165', 'admin', '添加按钮', '106', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddButton()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@efcd8e2', '0:0:0:0:0:0:0:1', '2020-03-08 13:31:33');
INSERT INTO `sys_log` VALUES ('7d3b9d7d-ecf2-4abd-8f5c-da9cb773b0d7', 'admin', '添加用户', '336', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@2d3b66fdescription: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:47:04');
INSERT INTO `sys_log` VALUES ('7d624101-8f69-4e8f-a2f6-04075c999cb4', 'admin', '修改菜单', '134', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@5b2e577a', '0:0:0:0:0:0:0:1', '2020-03-10 16:52:55');
INSERT INTO `sys_log` VALUES ('837616e8-b486-4829-b774-7c4048066316', 'admin', '添加菜单', '8', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@58b0a75', '0:0:0:0:0:0:0:1', '2020-03-06 11:09:56');
INSERT INTO `sys_log` VALUES ('935922f2-0dbf-4f71-9f4f-8fda1a6665a5', 'admin', '删除菜单', '7', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '127.0.0.1', '2020-03-08 15:11:36');
INSERT INTO `sys_log` VALUES ('947b051c-d45f-4e13-a08a-68e3512b8f27', 'admin', '添加菜单', '71', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@6902c220', '0:0:0:0:0:0:0:1', '2020-03-11 03:21:45');
INSERT INTO `sys_log` VALUES ('99918bb0-24c7-41c7-89c3-6158d81eab0a', 'admin', '删除菜单', '3', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '127.0.0.1', '2020-03-08 15:12:44');
INSERT INTO `sys_log` VALUES ('9d041d2f-ea49-46c7-9d1f-e140a1990cfe', 'admin', '添加用户', '248', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@712bc222description: 用户', '0:0:0:0:0:0:0:1', '2020-02-28 16:48:19');
INSERT INTO `sys_log` VALUES ('a1af09cc-0c9f-4b58-b4ce-240e63595d20', 'admin', '修改菜单', '132', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@15bfeaa', '0:0:0:0:0:0:0:1', '2020-03-10 16:57:50');
INSERT INTO `sys_log` VALUES ('a2fa3e53-899d-4c9c-a2ce-5e6c65de7a30', 'admin', '修改菜单', '7', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@57de0c49', '0:0:0:0:0:0:0:1', '2020-03-11 05:21:23');
INSERT INTO `sys_log` VALUES ('aa7cf812-386d-4492-89f2-8296bfe47613', 'admin', '添加用户', '173', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity.UserEntity@52857b7fdescription: 用户', '0:0:0:0:0:0:0:1', '2020-02-25 14:09:06');
INSERT INTO `sys_log` VALUES ('ac9adced-70d4-484a-898f-20468957b559', 'admin', '删除菜单', '106', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 1931ba4a-4582-49bd-b0cd-633d0d5e3b96', '0:0:0:0:0:0:0:1', '2020-03-08 15:26:43');
INSERT INTO `sys_log` VALUES ('acad7664-3afc-410f-a607-105f81176120', 'admin', '修改菜单', '10', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@7f0aa85c', '0:0:0:0:0:0:0:1', '2020-03-10 16:40:17');
INSERT INTO `sys_log` VALUES ('b3e08e5d-e1ef-401c-af33-082a98479c1a', 'admin', '删除菜单', '4', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '127.0.0.1', '2020-03-08 15:12:06');
INSERT INTO `sys_log` VALUES ('b777034c-6a01-421d-855c-f184d69aa616', 'admin', '添加按钮', '229', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddButton()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@3dc8b16e', '0:0:0:0:0:0:0:1', '2020-03-11 03:31:55');
INSERT INTO `sys_log` VALUES ('bdb53331-9b9c-49ee-913f-9d0978cd606e', 'admin', '添加菜单', '7', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@2e107d43', '0:0:0:0:0:0:0:1', '2020-03-06 11:04:16');
INSERT INTO `sys_log` VALUES ('c1b256b3-0e57-4b77-8b58-4f284a27906a', 'admin', '添加按钮', '152', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddButton()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@e65c665', '0:0:0:0:0:0:0:1', '2020-03-08 15:21:34');
INSERT INTO `sys_log` VALUES ('c69eca3f-9308-4395-be09-aab020c22848', 'admin', '删除菜单', '5', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '127.0.0.1', '2020-03-08 15:12:55');
INSERT INTO `sys_log` VALUES ('c9d59676-aa63-43a6-92ef-14ad743eec6d', 'admin', '添加菜单', '62', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@6a5e37dd', '0:0:0:0:0:0:0:1', '2020-03-08 13:30:15');
INSERT INTO `sys_log` VALUES ('cbc6288e-7db7-47ac-9833-6dcdd813e67d', 'admin', '添加菜单', '139', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@7766f086', '0:0:0:0:0:0:0:1', '2020-03-06 11:18:35');
INSERT INTO `sys_log` VALUES ('ccc80546-3069-45c3-a069-a5fa8112bdee', 'admin', '修改菜单', '113', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@1f8aef68', '0:0:0:0:0:0:0:1', '2020-03-10 17:03:13');
INSERT INTO `sys_log` VALUES ('d72d3d25-95cb-4840-a5a0-b5fea4579c6f', 'admin', '添加菜单', '9', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@242a67ab', '0:0:0:0:0:0:0:1', '2020-03-06 11:08:58');
INSERT INTO `sys_log` VALUES ('d7e27066-f3a8-44e0-a4ae-75328b8b52df', 'admin', '修改菜单', '54', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@d1d53c1', '0:0:0:0:0:0:0:1', '2020-03-11 05:20:31');
INSERT INTO `sys_log` VALUES ('d9bf4b12-a1cc-43ab-b9cd-4d3d88c0ecd6', 'admin', '添加菜单', '133', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@2b6487d0', '0:0:0:0:0:0:0:1', '2020-03-08 14:02:59');
INSERT INTO `sys_log` VALUES ('e29f93f7-aabe-42e7-8ef9-1efcb5458d3e', 'admin', '修改菜单', '87', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@ad6ff5d', '0:0:0:0:0:0:0:1', '2020-03-11 05:21:07');
INSERT INTO `sys_log` VALUES ('e5ae2a19-6b89-496b-ab53-6633a8066b3b', 'admin', '删除菜单', '140', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 2c0588bb-e73f-43b1-9e1a-ea8f446928b8', '0:0:0:0:0:0:0:1', '2020-03-08 15:21:46');
INSERT INTO `sys_log` VALUES ('e6fd3ed0-5935-42c5-91c7-fdb238ff2500', 'admin', '修改菜单', '191', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@14a2b9c4', '0:0:0:0:0:0:0:1', '2020-03-10 16:58:42');
INSERT INTO `sys_log` VALUES ('ebfab54e-c5b6-4acb-bfc3-8d9b909adeae', 'admin', '添加菜单', '0', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@26c44792', '0:0:0:0:0:0:0:1', '2020-03-06 10:52:03');
INSERT INTO `sys_log` VALUES ('edc702aa-60c7-4941-a1b3-5d5128c5f945', 'admin', '添加菜单', '453', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@5ef5d8bc', '0:0:0:0:0:0:0:1', '2020-03-06 11:17:30');
INSERT INTO `sys_log` VALUES ('eeae7434-acbd-465e-8d26-84c6ad121151', 'admin', '添加目录', '90', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddCatalog()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@32ac0e64', '0:0:0:0:0:0:0:1', '2020-03-10 17:28:52');
INSERT INTO `sys_log` VALUES ('ef627ad5-fcf3-4469-a592-71d057c151ae', 'admin', '添加用户', '114', 'com.example.demo.Graduation.controller.UserController.UserController.AddUserInfo()', 'userEntity: com.example.demo.Graduation.entity.UserEntity@2bbaename: 测试角色', '0:0:0:0:0:0:0:1', '2020-03-20 01:11:42');
INSERT INTO `sys_log` VALUES ('ef8a23d2-07af-408e-b542-d96f647ffeec', 'admin', '删除菜单', '3', 'com.example.demo.Graduation.controller.MenuController.MenuController.deletemenu()', 'id: 53eab053-2eed-4bd5-af2b-34c656170e70', '0:0:0:0:0:0:0:1', '2020-03-08 15:15:02');
INSERT INTO `sys_log` VALUES ('f153a95a-ac6e-4798-930f-d45a6fad2886', 'admin', '修改菜单', '12', 'com.example.demo.Graduation.controller.MenuController.MenuController.UpdateMenus()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@6cd1e1ea', '0:0:0:0:0:0:0:1', '2020-03-10 16:53:07');
INSERT INTO `sys_log` VALUES ('facc7468-5966-425d-980a-edd18237b62f', 'admin', '添加菜单', '403', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddMenu()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@3ff3fbff', '0:0:0:0:0:0:0:1', '2020-03-08 13:28:15');
INSERT INTO `sys_log` VALUES ('fdfd08c7-8393-4c90-afad-59c9cfdd8c31', 'admin', '添加按钮', '59', 'com.example.demo.Graduation.controller.MenuController.MenuController.AddButton()', 'menuEntity: com.example.demo.Graduation.entity.MenuEntity.MenuEntity@2d98b945', '0:0:0:0:0:0:0:1', '2020-03-11 03:23:47');

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
INSERT INTO `sys_resources` VALUES ('194f5614-68e9-4794-bdef-d3c3624a488c', '测试1菜单1', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034', '/7676', '2', '222', '1');
INSERT INTO `sys_resources` VALUES ('2936ac60-77f4-43a0-b350-ac108a1442a0', '测试2菜单2', '3a884955-44e7-4d08-82af-d9d728f4d67d', '/5568', '2', '333', '1');
INSERT INTO `sys_resources` VALUES ('2af29df4-fe76-474f-98e4-fecbfb4d1966', '测试1菜单2', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034', '/aaaa1', '2', '444', '1');
INSERT INTO `sys_resources` VALUES ('3a884955-44e7-4d08-82af-d9d728f4d67d', '宠物管理', '0', '/ceshi2', '1', '666', '5');
INSERT INTO `sys_resources` VALUES ('47158fd3-8b1b-517c-a317-5f41415986c3', '修改用户', '635b0533-9544-5b99-9268-23eb485ed2bb', '/userinfo/updateusers', '3', 'user:update', '4');
INSERT INTO `sys_resources` VALUES ('48965adf-766e-5b70-bf38-75e5ed74a191', '添加用户', '635b0533-9544-5b99-9268-23eb485ed2bb', '/userinfo/addusers', '3', 'user:add', '2');
INSERT INTO `sys_resources` VALUES ('5a65d264-046f-4b8a-a265-11b88e5768a3', '测试2菜单1', '3a884955-44e7-4d08-82af-d9d728f4d67d', '/aaaa', '2', '777', '1');
INSERT INTO `sys_resources` VALUES ('5ab29e13-1370-42b7-b6cd-c71f28617cd0', '日志管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/syslog', '2', '888', '1');
INSERT INTO `sys_resources` VALUES ('5c4fbc0f-ed78-4a20-b112-77cc02b3d034', '测试目录1', '0', '/ceshi', '1', '999', '3');
INSERT INTO `sys_resources` VALUES ('5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '菜单管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/menuinfo', '2', '1010', '1');
INSERT INTO `sys_resources` VALUES ('635b0533-9544-5b99-9268-23eb485ed2bb', '用户管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/userinfo', '2', 'user:user', '1');
INSERT INTO `sys_resources` VALUES ('643b4957-7b52-4240-88a1-653d65e93d55', '角色管理', 'bb775c92-bba3-4ec9-b76e-6233af254f5e', '/roleinfo', '2', '111111', '1');
INSERT INTO `sys_resources` VALUES ('70bfb979-aedc-4407-a5a5-f177788bd1da', '测试菜单3', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034', 'fdsfsd', '2', 'sdfsdf', '456456');
INSERT INTO `sys_resources` VALUES ('890d35fd-be5f-4c8c-ae31-a171eb32604d', '删除菜单', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '/menuinfo/deletemenus', '3', 'menu:delete', '11');
INSERT INTO `sys_resources` VALUES ('89618ef6-955e-4a38-990b-a11ac9741156', '删除角色', '643b4957-7b52-4240-88a1-653d65e93d55', '/useroleinfo/deleteuserrole', '3', 'aaa', '1');
INSERT INTO `sys_resources` VALUES ('8fe95670-2d71-45be-98c0-90c08901936c', '添加角色', '643b4957-7b52-4240-88a1-653d65e93d55', '/userroleinfo/adduserrole', '3', '141414', '1');
INSERT INTO `sys_resources` VALUES ('918e4f69-ac25-48cb-9dd4-77298b76b3c7', '添加菜单', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '/menuinfo/addmenus', '3', '151515', '1');
INSERT INTO `sys_resources` VALUES ('9dc89dc7-2a26-4cb5-9d65-5cef0bcb2617', '修改角色', '643b4957-7b52-4240-88a1-653d65e93d55', '/userroleinfo/updateuserrole', '3', '171717', '1');
INSERT INTO `sys_resources` VALUES ('bb775c92-bba3-4ec9-b76e-6233af254f5e', '系统管理', '0', '/system', '1', 'system:list', '111');
INSERT INTO `sys_resources` VALUES ('ebf3942b-1500-4f95-90de-deae503cd2a5', '测试目录2', '0', '/ceshi111', '1', 'fdsfdsf', '10');
INSERT INTO `sys_resources` VALUES ('f9dd036d-95b3-48f7-9d44-8071f4d10a62', '修改菜单', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a', '/menuinfo/updatemenus', '3', '202020', '12');
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
INSERT INTO `sys_role` VALUES ('17ccc725-04f2-4cfe-af92-466a758bcbda', '用户', '这是用户', '2020-02-05 18:58:25', '2020-03-11 18:58:29');
INSERT INTO `sys_role` VALUES ('2424109d-def4-5069-8c3b-c15bab8eadc6', '超级管理员', '这是超级管理员', '2020-02-05 18:58:33', '2020-03-13 00:32:59');
INSERT INTO `sys_role` VALUES ('380f5394-2448-4530-b99c-91712ffeed06', '管理员', '这是管理员', '2020-02-05 18:58:41', '2020-03-11 18:58:45');
INSERT INTO `sys_role` VALUES ('971e20a6-21fd-409a-a770-db2bcb98b6d2', '测试角色', '1111111', '2020-03-20 01:11:16', '2020-03-20 01:11:16');
INSERT INTO `sys_role` VALUES ('a6430a52-4dae-48eb-abbb-ca9fd55e4819', '测试2', '发送到地方', '2020-03-20 01:25:13', '2020-03-20 01:25:13');

-- ----------------------------
-- Table structure for `sys_role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `roleid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resourcesid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('40', '380f5394-2448-4530-b99c-91712ffeed06', 'bb775c92-bba3-4ec9-b76e-6233af254f5e');
INSERT INTO `sys_role_resources` VALUES ('41', '380f5394-2448-4530-b99c-91712ffeed06', '635b0533-9544-5b99-9268-23eb485ed2bb');
INSERT INTO `sys_role_resources` VALUES ('42', '380f5394-2448-4530-b99c-91712ffeed06', '48965adf-766e-5b70-bf38-75e5ed74a191');
INSERT INTO `sys_role_resources` VALUES ('43', '380f5394-2448-4530-b99c-91712ffeed06', '47158fd3-8b1b-517c-a317-5f41415986c3');
INSERT INTO `sys_role_resources` VALUES ('44', '380f5394-2448-4530-b99c-91712ffeed06', 'fb285405-c3a9-5f62-b412-dabe9aa84faf');
INSERT INTO `sys_role_resources` VALUES ('45', '380f5394-2448-4530-b99c-91712ffeed06', 'ebf3942b-1500-4f95-90de-deae503cd2a5');
INSERT INTO `sys_role_resources` VALUES ('46', '380f5394-2448-4530-b99c-91712ffeed06', '643b4957-7b52-4240-88a1-653d65e93d55');
INSERT INTO `sys_role_resources` VALUES ('47', '380f5394-2448-4530-b99c-91712ffeed06', '8fe95670-2d71-45be-98c0-90c08901936c');
INSERT INTO `sys_role_resources` VALUES ('48', '380f5394-2448-4530-b99c-91712ffeed06', '9dc89dc7-2a26-4cb5-9d65-5cef0bcb2617');
INSERT INTO `sys_role_resources` VALUES ('49', '380f5394-2448-4530-b99c-91712ffeed06', '89618ef6-955e-4a38-990b-a11ac9741156');
INSERT INTO `sys_role_resources` VALUES ('50', '380f5394-2448-4530-b99c-91712ffeed06', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a');
INSERT INTO `sys_role_resources` VALUES ('51', '380f5394-2448-4530-b99c-91712ffeed06', '918e4f69-ac25-48cb-9dd4-77298b76b3c7');
INSERT INTO `sys_role_resources` VALUES ('52', '380f5394-2448-4530-b99c-91712ffeed06', 'f9dd036d-95b3-48f7-9d44-8071f4d10a62');
INSERT INTO `sys_role_resources` VALUES ('53', '380f5394-2448-4530-b99c-91712ffeed06', '890d35fd-be5f-4c8c-ae31-a171eb32604d');
INSERT INTO `sys_role_resources` VALUES ('54', '380f5394-2448-4530-b99c-91712ffeed06', '5ab29e13-1370-42b7-b6cd-c71f28617cd0');
INSERT INTO `sys_role_resources` VALUES ('55', '971e20a6-21fd-409a-a770-db2bcb98b6d2', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034');
INSERT INTO `sys_role_resources` VALUES ('56', '971e20a6-21fd-409a-a770-db2bcb98b6d2', '70bfb979-aedc-4407-a5a5-f177788bd1da');
INSERT INTO `sys_role_resources` VALUES ('57', '971e20a6-21fd-409a-a770-db2bcb98b6d2', '194f5614-68e9-4794-bdef-d3c3624a488c');
INSERT INTO `sys_role_resources` VALUES ('58', '971e20a6-21fd-409a-a770-db2bcb98b6d2', '2af29df4-fe76-474f-98e4-fecbfb4d1966');
INSERT INTO `sys_role_resources` VALUES ('59', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', 'bb775c92-bba3-4ec9-b76e-6233af254f5e');
INSERT INTO `sys_role_resources` VALUES ('60', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '5ab29e13-1370-42b7-b6cd-c71f28617cd0');
INSERT INTO `sys_role_resources` VALUES ('61', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '5cb8f73d-c29f-4c20-bc52-4dc45a18dc9a');
INSERT INTO `sys_role_resources` VALUES ('62', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', 'f9dd036d-95b3-48f7-9d44-8071f4d10a62');
INSERT INTO `sys_role_resources` VALUES ('63', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '890d35fd-be5f-4c8c-ae31-a171eb32604d');
INSERT INTO `sys_role_resources` VALUES ('64', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '918e4f69-ac25-48cb-9dd4-77298b76b3c7');
INSERT INTO `sys_role_resources` VALUES ('65', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '635b0533-9544-5b99-9268-23eb485ed2bb');
INSERT INTO `sys_role_resources` VALUES ('66', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '47158fd3-8b1b-517c-a317-5f41415986c3');
INSERT INTO `sys_role_resources` VALUES ('67', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', 'fb285405-c3a9-5f62-b412-dabe9aa84faf');
INSERT INTO `sys_role_resources` VALUES ('68', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '48965adf-766e-5b70-bf38-75e5ed74a191');
INSERT INTO `sys_role_resources` VALUES ('69', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '643b4957-7b52-4240-88a1-653d65e93d55');
INSERT INTO `sys_role_resources` VALUES ('70', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '89618ef6-955e-4a38-990b-a11ac9741156');
INSERT INTO `sys_role_resources` VALUES ('71', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '8fe95670-2d71-45be-98c0-90c08901936c');
INSERT INTO `sys_role_resources` VALUES ('72', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '9dc89dc7-2a26-4cb5-9d65-5cef0bcb2617');
INSERT INTO `sys_role_resources` VALUES ('73', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', 'ebf3942b-1500-4f95-90de-deae503cd2a5');
INSERT INTO `sys_role_resources` VALUES ('74', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '3a884955-44e7-4d08-82af-d9d728f4d67d');
INSERT INTO `sys_role_resources` VALUES ('75', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '2936ac60-77f4-43a0-b350-ac108a1442a0');
INSERT INTO `sys_role_resources` VALUES ('76', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '5a65d264-046f-4b8a-a265-11b88e5768a3');
INSERT INTO `sys_role_resources` VALUES ('77', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '5c4fbc0f-ed78-4a20-b112-77cc02b3d034');
INSERT INTO `sys_role_resources` VALUES ('78', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '70bfb979-aedc-4407-a5a5-f177788bd1da');
INSERT INTO `sys_role_resources` VALUES ('79', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '194f5614-68e9-4794-bdef-d3c3624a488c');
INSERT INTO `sys_role_resources` VALUES ('80', 'a6430a52-4dae-48eb-abbb-ca9fd55e4819', '2af29df4-fe76-474f-98e4-fecbfb4d1966');

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
INSERT INTO `sys_user` VALUES ('05e5696e-fd64-4862-857b-d1d5a4574d40', 'wuwen123', 'a5445a70b369b416780dd609eeee4250', '1');
INSERT INTO `sys_user` VALUES ('589ca9f1-ef4b-4bbd-8731-30f2bef12a44', '654321', '94921f613c1bef770b366a002f1dfc1d', '1');
INSERT INTO `sys_user` VALUES ('62d9d149-44fe-4577-8b4c-4b774fd85183', 'admin', '0bc5321f53e909fe1c2e4c8d951baac4', '1');
INSERT INTO `sys_user` VALUES ('687a8d8b-2f61-4a82-b243-3f7c44acfd46', '12345678', '89537471db0e3a41545dc6c06a9ac048', '1');
INSERT INTO `sys_user` VALUES ('b022a3a5-bf61-5958-bbc4-d49350384c07', 'root', 'c4ef4cd970de6843a3a30ced9099341c', '1');
INSERT INTO `sys_user` VALUES ('c8180a02-767c-4381-9088-828c5277a45e', 'admin123', 'a47783606c6b6a6470b0c81d4f78801a', '1');
INSERT INTO `sys_user` VALUES ('dc47a85a-a367-4a35-8d2d-232a887fc8a0', '123456', '5fd12185d75080d1813cb3507bcc6091', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=577 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'b022a3a5-bf61-5958-bbc4-d49350384c07', '2424109d-def4-5069-8c3b-c15bab8eadc6', null, null);
INSERT INTO `sys_user_role` VALUES ('2', '62d9d149-44fe-4577-8b4c-4b774fd85183', '380f5394-2448-4530-b99c-91712ffeed06', null, null);
INSERT INTO `sys_user_role` VALUES ('557', 'dc47a85a-a367-4a35-8d2d-232a887fc8a0', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('558', '589ca9f1-ef4b-4bbd-8731-30f2bef12a44', '380f5394-2448-4530-b99c-91712ffeed06', null, null);
INSERT INTO `sys_user_role` VALUES ('567', '687a8d8b-2f61-4a82-b243-3f7c44acfd46', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('575', 'c8180a02-767c-4381-9088-828c5277a45e', '17ccc725-04f2-4cfe-af92-466a758bcbda', null, null);
INSERT INTO `sys_user_role` VALUES ('576', '05e5696e-fd64-4862-857b-d1d5a4574d40', '971e20a6-21fd-409a-a770-db2bcb98b6d2', null, null);
