
-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member`  (
  `member_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `open_id` char(28) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '对外开放id',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'https://fun-cloud.oss-cn-beijing.aliyuncs.com/2020-09-29/default-head.jpg' COMMENT '头像',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `invite_code` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邀请码',
  `integration` bigint NOT NULL DEFAULT 0 COMMENT '积分',
  `growth` bigint NOT NULL DEFAULT 0 COMMENT '成长值',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '2' COMMENT '性别:0->男；1->女；2->未知',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '启用状态:0->禁用；1->启用',
  `is_delete` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除状态 0:未删；1:删除',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `last_login_time` timestamp(0) NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '最后login时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member
-- ----------------------------
INSERT INTO `ums_member` VALUES (1, 'oB4nYjnoHhuWrPVi2pYLuPjnCaU0', 'mrdjun', '$2a$10$MYYfychYA9ARvlu6FCmf5uWA1KF/b5a6g217V/7bo3ZpHxCuhnrqG', '管理员', '一只管理员', 'https://fun-cloud.oss-cn-beijing.aliyuncs.com/2020-09-29/default-head.jpg', '18888888888', '88888888', 0, 0, '2', '1', '0', NULL, '1971-01-01 00:00:00', '2020-10-08 09:29:13', '2020-09-29 02:55:27', 'mrdjun', 'mrdjun', NULL, NULL);
INSERT INTO `ums_member` VALUES (2, 'oB4nYjnoHhuWrPVi2pYLuPjnCaU1', 'user', '$2a$10$x/9XtXO6KTVk09StYkKFu.11PZ6M7.qPJW1nF0W2J0c0r6oBR7nGm', '用户', '尊贵的用户', 'https://fun-cloud.oss-cn-beijing.aliyuncs.com/2020-09-29/default-head.jpg', '18666666666', '66666666', 0, 0, '2', '1', '0', NULL, '1971-01-01 00:00:00', '2020-10-01 10:48:22', '2020-09-29 02:55:27', 'mrdjun', 'mrdjun', NULL, NULL);

-- ----------------------------
-- Table structure for ums_member_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_role`;
CREATE TABLE `ums_member_role`  (
  `member_id` bigint NULL DEFAULT NULL,
  `role_id` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_member_role
-- ----------------------------
INSERT INTO `ums_member_role` VALUES (1, 2);
INSERT INTO `ums_member_role` VALUES (1, 1);

-- ----------------------------
-- Table structure for ums_perm
-- ----------------------------
DROP TABLE IF EXISTS `ums_perm`;
CREATE TABLE `ums_perm`  (
  `perm_id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `perm` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限字符串',
  `perm_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '启用状态:0->禁用；1->启用',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` timestamp(0) NULL DEFAULT NULL,
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`perm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_perm
-- ----------------------------
INSERT INTO `ums_perm` VALUES (1, 'user', '权限1', '1', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_perm` VALUES (2, 'vip', '权限2', '1', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_perm` VALUES (3, 'perm3', '权限3', '1', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_perm` VALUES (4, 'perm4', '权限4', '1', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_perm` VALUES (5, 'perm5', '权限5', '1', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识符',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '启用状态:0->禁用；1->启用',
  `is_delete` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除状态 0:未删；1:删除',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (1, 'USER', 'ROLE_USER', '1', '0', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_role` VALUES (2, '角色2', 'ROLE_2', '1', '0', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_role` VALUES (3, '角色3', 'ROLE_3', '1', '0', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_role` VALUES (4, '角色4', 'ROLE_4', '1', '0', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);
INSERT INTO `ums_role` VALUES (5, '角色5', 'ROLE_5', '1', '0', 'mrdjun', '2020-10-01 16:46:26', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ums_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_perm`;
CREATE TABLE `ums_role_perm`  (
  `role_id` bigint NULL DEFAULT NULL COMMENT '角色ID',
  `perm_id` bigint NULL DEFAULT NULL COMMENT '字符串ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_perm
-- ----------------------------
INSERT INTO `ums_role_perm` VALUES (1, 1);
INSERT INTO `ums_role_perm` VALUES (1, 2);
INSERT INTO `ums_role_perm` VALUES (1, 3);
INSERT INTO `ums_role_perm` VALUES (2, 1);
INSERT INTO `ums_role_perm` VALUES (2, 2);
INSERT INTO `ums_role_perm` VALUES (2, 3);
INSERT INTO `ums_role_perm` VALUES (2, 4);
INSERT INTO `ums_role_perm` VALUES (3, 1);
INSERT INTO `ums_role_perm` VALUES (3, 2);
INSERT INTO `ums_role_perm` VALUES (3, 3);
INSERT INTO `ums_role_perm` VALUES (3, 4);
INSERT INTO `ums_role_perm` VALUES (3, 5);

SET FOREIGN_KEY_CHECKS = 1;
