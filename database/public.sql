/*
 Navicat Premium Data Transfer

 Source Server         : LocalPGSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 90520
 Source Host           : localhost:5432
 Source Catalog        : tradedoc
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90520
 File Encoding         : 65001

 Date: 06/12/2020 14:07:44
*/


-- ----------------------------
-- Sequence structure for category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."category_id_seq";
CREATE SEQUENCE "public"."category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for code_signup_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."code_signup_id_seq";
CREATE SEQUENCE "public"."code_signup_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for comments_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."comments_id_seq";
CREATE SEQUENCE "public"."comments_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for customers_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."customers_id_seq";
CREATE SEQUENCE "public"."customers_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for history_payment_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."history_payment_id_seq";
CREATE SEQUENCE "public"."history_payment_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for images_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."images_id_seq";
CREATE SEQUENCE "public"."images_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for roles_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."roles_id_seq";
CREATE SEQUENCE "public"."roles_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS "public"."category";
CREATE TABLE "public"."category" (
  "id" int8 NOT NULL DEFAULT nextval('category_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for code_signup
-- ----------------------------
DROP TABLE IF EXISTS "public"."code_signup";
CREATE TABLE "public"."code_signup" (
  "id" int8 NOT NULL DEFAULT nextval('code_signup_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of code_signup
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS "public"."comments";
CREATE TABLE "public"."comments" (
  "id" int8 NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "content" varchar(255) COLLATE "pg_catalog"."default",
  "usernamecomment" varchar(255) COLLATE "pg_catalog"."default",
  "productid" int8
)
;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS "public"."customers";
CREATE TABLE "public"."customers" (
  "id" int8 NOT NULL DEFAULT nextval('customers_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "customername" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "phonenumber" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of customers
-- ----------------------------

-- ----------------------------
-- Table structure for history_payment
-- ----------------------------
DROP TABLE IF EXISTS "public"."history_payment";
CREATE TABLE "public"."history_payment" (
  "id" int8 NOT NULL DEFAULT nextval('history_payment_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "deleted" int4,
  "paymenttype" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4,
  "total" int8,
  "customerid" int8,
  "productid" int8
)
;

-- ----------------------------
-- Records of history_payment
-- ----------------------------

-- ----------------------------
-- Table structure for images
-- ----------------------------
DROP TABLE IF EXISTS "public"."images";
CREATE TABLE "public"."images" (
  "id" int8 NOT NULL DEFAULT nextval('images_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "pathfile" varchar(255) COLLATE "pg_catalog"."default",
  "productid" int8
)
;

-- ----------------------------
-- Records of images
-- ----------------------------

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."product_category";
CREATE TABLE "public"."product_category" (
  "productid" int8 NOT NULL,
  "categoryid" int8 NOT NULL
)
;

-- ----------------------------
-- Records of product_category
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS "public"."roles";
CREATE TABLE "public"."roles" (
  "id" int8 NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO "public"."roles" VALUES (1, '', '2020-08-09 14:56:30', '', '2020-08-09 14:56:30', 'ROLE_MANAGER', 'Quản trị');
INSERT INTO "public"."roles" VALUES (2, '', '2020-08-09 14:56:30', '', '2020-08-09 14:56:30', 'ROLE_STAFF', 'Người dùng');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_role";
CREATE TABLE "public"."user_role" (
  "userid" int8 NOT NULL,
  "roleid" int8 NOT NULL
)
;

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" int8 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  "createdby" varchar(255) COLLATE "pg_catalog"."default",
  "createddate" timestamp(6),
  "modifiedby" varchar(255) COLLATE "pg_catalog"."default",
  "modifieddate" timestamp(6),
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "fullname" varchar(255) COLLATE "pg_catalog"."default",
  "numberphone" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4,
  "username" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."category_id_seq"
OWNED BY "public"."category"."id";
SELECT setval('"public"."category_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."code_signup_id_seq"
OWNED BY "public"."code_signup"."id";
SELECT setval('"public"."code_signup_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."comments_id_seq"
OWNED BY "public"."comments"."id";
SELECT setval('"public"."comments_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."customers_id_seq"
OWNED BY "public"."customers"."id";
SELECT setval('"public"."customers_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."history_payment_id_seq"
OWNED BY "public"."history_payment"."id";
SELECT setval('"public"."history_payment_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."images_id_seq"
OWNED BY "public"."images"."id";
SELECT setval('"public"."images_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."roles_id_seq"
OWNED BY "public"."roles"."id";
SELECT setval('"public"."roles_id_seq"', 2, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."users_id_seq"
OWNED BY "public"."users"."id";
SELECT setval('"public"."users_id_seq"', 2, false);

-- ----------------------------
-- Primary Key structure for table category
-- ----------------------------
ALTER TABLE "public"."category" ADD CONSTRAINT "category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table code_signup
-- ----------------------------
ALTER TABLE "public"."code_signup" ADD CONSTRAINT "code_signup_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table comments
-- ----------------------------
ALTER TABLE "public"."comments" ADD CONSTRAINT "comments_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table customers
-- ----------------------------
ALTER TABLE "public"."customers" ADD CONSTRAINT "customers_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table history_payment
-- ----------------------------
ALTER TABLE "public"."history_payment" ADD CONSTRAINT "history_payment_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table images
-- ----------------------------
ALTER TABLE "public"."images" ADD CONSTRAINT "images_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table roles
-- ----------------------------
ALTER TABLE "public"."roles" ADD CONSTRAINT "roles_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table history_payment
-- ----------------------------
ALTER TABLE "public"."history_payment" ADD CONSTRAINT "fknp8qi045ogovmkscahym3juqq" FOREIGN KEY ("customerid") REFERENCES "public"."customers" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table product_category
-- ----------------------------
ALTER TABLE "public"."product_category" ADD CONSTRAINT "fka9jknept9whwvd0v65rglqbu8" FOREIGN KEY ("categoryid") REFERENCES "public"."category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Keys structure for table user_role
-- ----------------------------
ALTER TABLE "public"."user_role" ADD CONSTRAINT "fkl4qqtaxj0v5ikodha3v2pmfl" FOREIGN KEY ("userid") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."user_role" ADD CONSTRAINT "fkss07htsrasc17qsq2o9422nyh" FOREIGN KEY ("roleid") REFERENCES "public"."roles" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
