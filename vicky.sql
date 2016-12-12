/*==============================================================*/
/* 创建数据库                                       */
/*==============================================================*/
DROP DATABASE IF EXISTS VICKY;
CREATE DATABASE VICKY;
USE VICKY;

/*==============================================================*/
/* 预处理                                                       */
/*==============================================================*/
drop index collect_user_table_collected_user_index on collect_user;

drop index collect_user_table_collecting_user_index on collect_user;

drop table if exists collect_user;

drop index collect_video_table_vedio_id_index on collect_video;

drop index collect_video_table_collecting_user_index on collect_video;

drop table if exists collect_video;

drop index comment_floor_table_floor_number_index on comment_floor;

drop index comment_floor_table_create_time_index on comment_floor;

drop index comment_floor_table_video_id_index on comment_floor;

drop table if exists comment_floor;

drop index comment_reply_create_time_index on comment_reply;

drop index comment_reply_floor_id_index on comment_reply;

drop table if exists comment_reply;

drop index message_table_status_index on message;

drop index message_table_create_time_index on message;

drop index message_table_username_index on message;

drop table if exists message;

drop index user_table_email_index on user;

drop table if exists user;

drop index video_table_create_time_index on video;

drop index video_table_username_index on video;

drop index video_table_module_name_index on video;

drop index video_table_video_title_index on video;

drop table if exists video;

drop index video_check_table_create_time_index on video_check;

drop index video_check_table_username_index on video_check;

drop index video_check_table_module_name_index on video_check;

drop index video_check_table_video_title_index on video_check;

drop table if exists video_check;

drop index video_like_table_video_id_index on video_like;

drop index video_like_table_username_index on video_like;

drop table if exists video_like;

drop table if exists video_module;

/*==============================================================*/
/* Table: collect_user                                          */
/*==============================================================*/
create table collect_user
(
   collect_user_id      varchar(255) not null comment '收藏用户表主键，java UUID',
   collecting_username  varchar(255) not null comment '收藏用户名',
   collected_username   varchar(255) not null comment '被收藏用户名',
   create_time          datetime not null comment '收藏时间',
   primary key (collect_user_id),
   unique key AK_ing_ed_user_unique (collecting_username, collected_username)
);

alter table collect_user comment '用户收藏用户表';

/*==============================================================*/
/* Index: collect_user_table_collecting_user_index              */
/*==============================================================*/
create index collect_user_table_collecting_user_index on collect_user
(
   collecting_username
);

/*==============================================================*/
/* Index: collect_user_table_collected_user_index               */
/*==============================================================*/
create index collect_user_table_collected_user_index on collect_user
(
   collected_username
);

/*==============================================================*/
/* Table: collect_video                                         */
/*==============================================================*/
create table collect_video
(
   collect_video_id     varchar(255) not null comment '收藏视频表主键，java UUID',
   collecting_username  varchar(255) not null comment '收藏用户名',
   video_id             varchar(255) not null comment '被收藏视频主键',
   create_time          datetime not null comment '收藏时间',
   video_title          varchar(255) not null comment '视频标题',
   cover_relative_path  varchar(255) not null comment '视频封面图片文件相对路径',
   primary key (collect_video_id),
   unique key AK_user_vedio_id_unique (collecting_username, video_id)
);

alter table collect_video comment '用户收藏视频表';

/*==============================================================*/
/* Index: collect_video_table_collecting_user_index             */
/*==============================================================*/
create index collect_video_table_collecting_user_index on collect_video
(
   collecting_username
);

/*==============================================================*/
/* Index: collect_video_table_vedio_id_index                    */
/*==============================================================*/
create index collect_video_table_vedio_id_index on collect_video
(
   video_id
);

/*==============================================================*/
/* Table: comment_floor                                         */
/*==============================================================*/
create table comment_floor
(
   floor_id             varchar(255) not null comment '楼层主键，java uuid',
   username             varchar(255) not null comment '评论用户名',
   content              text not null comment '评论内容，text长字段',
   create_time          datetime not null comment '发表评论的时间',
   video_id             varchar(255) not null comment '评论所属视频ID',
   floor_number         int not null comment '楼层序号，自增',
   primary key (floor_id)
);

alter table comment_floor comment '视频评论区的楼层';

/*==============================================================*/
/* Index: comment_floor_table_video_id_index                    */
/*==============================================================*/
create index comment_floor_table_video_id_index on comment_floor
(
   video_id
);

/*==============================================================*/
/* Index: comment_floor_table_create_time_index                 */
/*==============================================================*/
create index comment_floor_table_create_time_index on comment_floor
(
   create_time
);

/*==============================================================*/
/* Index: comment_floor_table_floor_number_index                */
/*==============================================================*/
create index comment_floor_table_floor_number_index on comment_floor
(
   floor_number
);

/*==============================================================*/
/* Table: comment_reply                                         */
/*==============================================================*/
create table comment_reply
(
   reply_id             varchar(255) not null comment '回复主键,java UUID',
   front_username       varchar(255) not null comment '主动回复的用户名',
   after_username       varchar(255) comment '被回复的用户名，为空意味着直接回复楼层',
   content              text not null comment '回复内容，text长字段',
   create_time          datetime not null comment '回复时间',
   floor_id             varchar(255) not null comment '回复所属楼层',
   primary key (reply_id)
);

alter table comment_reply comment '评论楼层回复表';

/*==============================================================*/
/* Index: comment_reply_floor_id_index                          */
/*==============================================================*/
create index comment_reply_floor_id_index on comment_reply
(
   floor_id
);

/*==============================================================*/
/* Index: comment_reply_create_time_index                       */
/*==============================================================*/
create index comment_reply_create_time_index on comment_reply
(
   create_time
);

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   message_id           varchar(255) not null comment '主键',
   username             varchar(255) not null comment '消息所属用户',
   message_title        varchar(255) not null comment '消息标题',
   content              varchar(255) not null comment '消息内容',
   create_time          datetime not null comment '消息创建时间',
   status               varchar(3) not null comment '消息状态，“001”表示未读，“002”表示已读',
   primary key (message_id)
);

alter table message comment '用户通知消息表';

/*==============================================================*/
/* Index: message_table_username_index                          */
/*==============================================================*/
create index message_table_username_index on message
(
   username
);

/*==============================================================*/
/* Index: message_table_create_time_index                       */
/*==============================================================*/
create index message_table_create_time_index on message
(
   create_time
);

/*==============================================================*/
/* Index: message_table_status_index                            */
/*==============================================================*/
create index message_table_status_index on message
(
   status
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   username             varchar(255) not null comment '用户名，主键，不可修改',
   email                varchar(255) not null comment '用户注册邮箱，唯一非空，可作为登录标识',
   create_time          datetime not null comment '用户注册时间，非空',
   password             varchar(255) not null comment '用户密码，非空，经过经过加密',
   sex                  varchar(2) comment '用户性别，只有三种情况，“男”，“女”，“保密”，为空的即是未知',
   signature            varchar(255) comment '个性签名，不能超过255个字',
   image_relative_path  varchar(255) not null comment '用户头像图片文件相对路径',
   image_absolute_path  varchar(255) not null comment '用户头像图片文件绝对路径',
   primary key (username),
   unique key AK_user_email_unique_key (email)
);

alter table user comment '用户表，包含用户的基本信息';

/*==============================================================*/
/* Index: user_table_email_index                                */
/*==============================================================*/
create index user_table_email_index on user
(
   email
);

/*==============================================================*/
/* Table: video                                                 */
/*==============================================================*/
create table video
(
   video_id             varchar(255) not null comment '视频主键，java UUID',
   module_id            varchar(255) comment '所属模块',
   absolute_path        varchar(255) not null comment '视频文件绝对路径',
   relative_path        varchar(255) not null comment '视频文件相对路径',
   video_name           varchar(255) not null comment '视频名字',
   video_title          varchar(255) not null comment '视频标题',
   username             varchar(255) not null comment '投稿视频的用户名',
   create_time          datetime not null comment '视频投稿时间',
   cover_absolute_path  varchar(255) not null comment '视频封面图片文件绝对路径',
   cover_relative_path  varchar(255) not null comment '图片封面图片文件相对路径',
   video_explain        varchar(255) not null comment '视频说明',
   primary key (video_id)
);

alter table video comment '视频表';

/*==============================================================*/
/* Index: video_table_video_title_index                         */
/*==============================================================*/
create index video_table_video_title_index on video
(
   video_title
);

/*==============================================================*/
/* Index: video_table_module_name_index                         */
/*==============================================================*/
create index video_table_module_name_index on video
(
   
);

/*==============================================================*/
/* Index: video_table_username_index                            */
/*==============================================================*/
create index video_table_username_index on video
(
   username
);

/*==============================================================*/
/* Index: video_table_create_time_index                         */
/*==============================================================*/
create index video_table_create_time_index on video
(
   create_time
);

/*==============================================================*/
/* Table: video_check                                           */
/*==============================================================*/
create table video_check
(
   video_id             varchar(255) not null comment '视频主键，java UUID',
   module_id            varchar(255) comment '所属模块主键',
   absolute_path        varchar(255) not null comment '视频文件绝对路径',
   relative_path        varchar(255) not null comment '视频文件相对路径',
   video_name           varchar(255) not null comment '视频名字',
   video_title          varchar(255) not null comment '视频标题',
   username             varchar(255) not null comment '投稿视频的用户名',
   create_time          datetime not null comment '视频投稿时间',
   cover_absolute_path  varchar(255) not null comment '视频封面图片文件绝对路径',
   cover_relative_path  varchar(255) not null comment '图片封面图片文件相对路径',
   video_explain        varchar(255) not null comment '视频说明',
   primary key (video_id)
);

alter table video_check comment '视频审核表，视频审核通过后，记录就会插入视频表，同时本表记录被删除';

/*==============================================================*/
/* Index: video_check_table_video_title_index                   */
/*==============================================================*/
create index video_check_table_video_title_index on video_check
(
   video_title
);

/*==============================================================*/
/* Index: video_check_table_module_name_index                   */
/*==============================================================*/
create index video_check_table_module_name_index on video_check
(
   
);

/*==============================================================*/
/* Index: video_check_table_username_index                      */
/*==============================================================*/
create index video_check_table_username_index on video_check
(
   username
);

/*==============================================================*/
/* Index: video_check_table_create_time_index                   */
/*==============================================================*/
create index video_check_table_create_time_index on video_check
(
   create_time
);

/*==============================================================*/
/* Table: video_like                                            */
/*==============================================================*/
create table video_like
(
   video_like_id        varchar(255) not null comment '点赞Id，java UUID',
   username             varchar(255) not null comment '点赞的用户名',
   video_id             varchar(255) not null comment '被点赞的视频Id',
   cover_relative_path  varchar(255) not null comment '被点赞视频封面图片文件相对路径',
   video_title          varchar(255) not null comment '被点赞视频标题',
   like_time            datetime not null comment '点赞时间',
   primary key (video_like_id),
   unique key AK_username_vedio_unique (username, video_id)
);

alter table video_like comment '视频点赞表';

/*==============================================================*/
/* Index: video_like_table_username_index                       */
/*==============================================================*/
create index video_like_table_username_index on video_like
(
   username
);

/*==============================================================*/
/* Index: video_like_table_video_id_index                       */
/*==============================================================*/
create index video_like_table_video_id_index on video_like
(
   video_id
);

/*==============================================================*/
/* Table: video_module                                          */
/*==============================================================*/
create table video_module
(
   module_id            varchar(255) not null comment '视频模块主键',
   module_name          varchar(255) not null comment '模块名字',
   create_time          date not null comment '模块创建时间',
   primary key (module_id),
   unique key AK_vedio_mudule_name_unique (module_name)
);

alter table video_module comment '视频模块表';

alter table comment_reply add constraint FK_Reference_3 foreign key (floor_id)
      references comment_floor (floor_id) on delete restrict on update restrict;

alter table video add constraint FK_Reference_1 foreign key (module_id)
      references video_module (module_id) on delete restrict on update restrict;

alter table video_check add constraint FK_Reference_2 foreign key (module_id)
      references video_module (module_id) on delete restrict on update restrict;
