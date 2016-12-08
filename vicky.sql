/*==============================================================*/
/* �������ݿ�                                       */
/*==============================================================*/
DROP DATABASE IF EXISTS VICKY;
CREATE DATABASE VICKY;
USE VICKY;

SET FOREIGN_KEY_CHECKS=0;

/*==============================================================*/
/* Ԥ����                                                       */
/*==============================================================*/
drop index collect_user_table_collected_user_index on collect_user;

drop index collect_user_table_collecting_user_index on collect_user;

drop table if exists collect_user;

drop index collect_vedio_table_vedio_id_index on collect_vedio;

drop index collect_vedio_table_collecting_user_index on collect_vedio;

drop table if exists collect_vedio;

drop index comment_floor_table_floor_number_index on comment_floor;

drop index comment_floor_table_create_time_index on comment_floor;

drop index comment_floor_table_vedio_id_index on comment_floor;

drop table if exists comment_floor;

drop index comment_reply_create_time_index on comment_reply;

drop index comment_reply_floor_id_index on comment_reply;

drop table if exists comment_reply;

drop index user_table_email_index on user;

drop table if exists user;

drop table if exists user_head;

drop index vedio_table_create_time_index on vedio;

drop index vedio_table_username_index on vedio;

drop index vedio_table_module_name_index on vedio;

drop index vedio_table_vedio_title_index on vedio;

drop table if exists vedio;

drop index vedio_check_table_create_time_index on vedio_check;

drop index vedio_check_table_username_index on vedio_check;

drop index vedio_check_table_module_name_index on vedio_check;

drop index vedio_check_table_vedio_title_index on vedio_check;

drop table if exists vedio_check;

drop index vedio_like_table_vedio_id_index on vedio_like;

drop index vedio_like_table_username_index on vedio_like;

drop table if exists vedio_like;

drop table if exists vedio_module;

/*==============================================================*/
/* Table: collect_user                                          */
/*==============================================================*/
create table collect_user
(
   collect_user_id      varchar(255) not null comment '�ղ��û���������java UUID',
   collecting_username  varchar(255) not null comment '�ղ��û���',
   collected_username   varchar(255) not null comment '���ղ��û���',
   create_time          datetime not null comment '�ղ�ʱ��',
   primary key (collect_user_id),
   unique key AK_ing_ed_user_unique (collecting_username, collected_username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table collect_user comment '�û��ղ��û���';

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
/* Table: collect_vedio                                         */
/*==============================================================*/
create table collect_vedio
(
   collect_vedio_id     varchar(255) not null comment '�ղ���Ƶ��������java UUID',
   collecting_username  varchar(255) not null comment '�ղ��û���',
   vedio_id             varchar(255) not null comment '���ղ���Ƶ����',
   create_time          datetime not null comment '�ղ�ʱ��',
   vedio_title          varchar(255) not null comment '��Ƶ����',
   cover_relative_path  varchar(255) not null comment '��Ƶ����ͼƬ�ļ����·��',
   primary key (collect_vedio_id),
   unique key AK_user_vedio_id_unique (collecting_username, vedio_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table collect_vedio comment '�û��ղ���Ƶ��';

/*==============================================================*/
/* Index: collect_vedio_table_collecting_user_index             */
/*==============================================================*/
create index collect_vedio_table_collecting_user_index on collect_vedio
(
   collecting_username
);

/*==============================================================*/
/* Index: collect_vedio_table_vedio_id_index                    */
/*==============================================================*/
create index collect_vedio_table_vedio_id_index on collect_vedio
(
   vedio_id
);

/*==============================================================*/
/* Table: comment_floor                                         */
/*==============================================================*/
create table comment_floor
(
   floor_id             varchar(255) not null comment '¥��������java uuid',
   username             varchar(255) not null comment '�����û���',
   content              text not null comment '�������ݣ�text���ֶ�',
   create_time          datetime not null comment '�������۵�ʱ��',
   vedio_id             varchar(255) not null comment '����������ƵID',
   floor_number         int not null comment '¥����ţ�����',
   primary key (floor_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table comment_floor comment '��Ƶ��������¥��';

/*==============================================================*/
/* Index: comment_floor_table_vedio_id_index                    */
/*==============================================================*/
create index comment_floor_table_vedio_id_index on comment_floor
(
   vedio_id
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
   reply_id             varchar(255) not null comment '�ظ�����,java UUID',
   front_username       varchar(255) not null comment '�����ظ����û���',
   after_username       varchar(255) comment '���ظ����û�����Ϊ����ζ��ֱ�ӻظ�¥��',
   content              text not null comment '�ظ����ݣ�text���ֶ�',
   create_time          datetime not null comment '�ظ�ʱ��',
   floor_id             varchar(255) not null comment '�ظ�����¥��',
   primary key (reply_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table comment_reply comment '����¥��ظ���';

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
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   username             varchar(255) not null comment '�û����������������޸�',
   email                varchar(255) not null comment '�û�ע�����䣬Ψһ�ǿգ�����Ϊ��¼��ʶ',
   create_time          datetime not null comment '�û�ע��ʱ�䣬�ǿ�',
   password             varchar(255) not null comment '�û����룬�ǿգ�������������',
   sex                  varchar(2) comment '�û��Ա�ֻ��������������С�����Ů���������ܡ���Ϊ�յļ���δ֪',
   signature            varchar(255) comment '����ǩ�������ܳ���255����',
   primary key (username),
   unique key AK_user_email_unique_key (email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table user comment '�û��������û��Ļ�����Ϣ';

/*==============================================================*/
/* Index: user_table_email_index                                */
/*==============================================================*/
create index user_table_email_index on user
(
   email
);

/*==============================================================*/
/* Table: user_head                                             */
/*==============================================================*/
create table user_head
(
   username             varchar(255) not null comment '�û����������û�������',
   image_relative_path  varchar(255) not null comment '�û�ͷ��ͼƬ�ļ����·��',
   image_absolute_path  varchar(255) not null comment '�û�ͷ��ͼƬ�ļ�����·��',
   primary key (username)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table user_head comment '�û�ͷ���,�����û�ͷ��ͼƬ��Ϣ';

/*==============================================================*/
/* Table: vedio                                                 */
/*==============================================================*/
create table vedio
(
   vedio_id             varchar(255) not null comment '��Ƶ������java UUID',
   module_name          varchar(255) comment '����ģ������',
   absolute_path        varchar(255) not null comment '��Ƶ�ļ�����·��',
   relative_path        varchar(255) not null comment '��Ƶ�ļ����·��',
   vedio_name           varchar(255) not null comment '��Ƶ����',
   vedio_title          varchar(255) not null comment '��Ƶ����',
   username             varchar(255) not null comment 'Ͷ����Ƶ���û���',
   create_time          datetime not null comment '��ƵͶ��ʱ��',
   cover_absolute_path  varchar(255) not null comment '��Ƶ����ͼƬ�ļ�����·��',
   cover_relative_path  varchar(255) not null comment 'ͼƬ����ͼƬ�ļ����·��',
   vedio_explain        varchar(255) not null comment '��Ƶ˵��',
   primary key (vedio_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table vedio comment '��Ƶ��';

/*==============================================================*/
/* Index: vedio_table_vedio_title_index                         */
/*==============================================================*/
create index vedio_table_vedio_title_index on vedio
(
   vedio_title
);

/*==============================================================*/
/* Index: vedio_table_module_name_index                         */
/*==============================================================*/
create index vedio_table_module_name_index on vedio
(
   module_name
);

/*==============================================================*/
/* Index: vedio_table_username_index                            */
/*==============================================================*/
create index vedio_table_username_index on vedio
(
   username
);

/*==============================================================*/
/* Index: vedio_table_create_time_index                         */
/*==============================================================*/
create index vedio_table_create_time_index on vedio
(
   create_time
);

/*==============================================================*/
/* Table: vedio_check                                           */
/*==============================================================*/
create table vedio_check
(
   vedio_id             varchar(255) not null comment '��Ƶ������java UUID',
   module_name          varchar(255) comment '����ģ������',
   absolute_path        varchar(255) not null comment '��Ƶ�ļ�����·��',
   relative_path        varchar(255) not null comment '��Ƶ�ļ����·��',
   vedio_name           varchar(255) not null comment '��Ƶ����',
   vedio_title          varchar(255) not null comment '��Ƶ����',
   username             varchar(255) not null comment 'Ͷ����Ƶ���û���',
   create_time          datetime not null comment '��ƵͶ��ʱ��',
   cover_absolute_path  varchar(255) not null comment '��Ƶ����ͼƬ�ļ�����·��',
   cover_relative_path  varchar(255) not null comment 'ͼƬ����ͼƬ�ļ����·��',
   vedio_explain        varchar(255) not null comment '��Ƶ˵��',
   primary key (vedio_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table vedio_check comment '��Ƶ��˱���Ƶ���ͨ���󣬼�¼�ͻ������Ƶ��ͬʱ�����¼��ɾ��';

/*==============================================================*/
/* Index: vedio_check_table_vedio_title_index                   */
/*==============================================================*/
create index vedio_check_table_vedio_title_index on vedio_check
(
   vedio_title
);

/*==============================================================*/
/* Index: vedio_check_table_module_name_index                   */
/*==============================================================*/
create index vedio_check_table_module_name_index on vedio_check
(
   module_name
);

/*==============================================================*/
/* Index: vedio_check_table_username_index                      */
/*==============================================================*/
create index vedio_check_table_username_index on vedio_check
(
   username
);

/*==============================================================*/
/* Index: vedio_check_table_create_time_index                   */
/*==============================================================*/
create index vedio_check_table_create_time_index on vedio_check
(
   create_time
);

/*==============================================================*/
/* Table: vedio_like                                            */
/*==============================================================*/
create table vedio_like
(
   vedio_like_id        varchar(255) not null comment '����Id��java UUID',
   username             varchar(255) not null comment '���޵��û���',
   vedio_id             varchar(255) not null comment '�����޵���ƵId',
   cover_relative_path  varchar(255) not null comment '��������Ƶ����ͼƬ�ļ����·��',
   vedio_title          varchar(255) not null comment '��������Ƶ����',
   like_time            datetime not null comment '����ʱ��',
   primary key (vedio_like_id),
   unique key AK_username_vedio_unique (username, vedio_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table vedio_like comment '��Ƶ���ޱ�';

/*==============================================================*/
/* Index: vedio_like_table_username_index                       */
/*==============================================================*/
create index vedio_like_table_username_index on vedio_like
(
   username
);

/*==============================================================*/
/* Index: vedio_like_table_vedio_id_index                       */
/*==============================================================*/
create index vedio_like_table_vedio_id_index on vedio_like
(
   vedio_id
);

/*==============================================================*/
/* Table: vedio_module                                          */
/*==============================================================*/
create table vedio_module
(
   module_name          varchar(255) not null comment 'ģ������',
   create_time          date not null comment 'ģ�鴴��ʱ��',
   primary key (module_name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table vedio_module comment '��Ƶģ���';

alter table comment_reply add constraint FK_Reference_3 foreign key (floor_id)
      references comment_floor (floor_id) on delete restrict on update restrict;

alter table vedio add constraint FK_Reference_1 foreign key (module_name)
      references vedio_module (module_name) on delete restrict on update restrict;

alter table vedio_check add constraint FK_Reference_2 foreign key (module_name)
      references vedio_module (module_name) on delete restrict on update restrict;
