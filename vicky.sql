/*==============================================================*/
/* �������ݿ�                                       */
/*==============================================================*/
DROP DATABASE IF EXISTS VICKY;
CREATE DATABASE VICKY;
USE VICKY;

/*==============================================================*/
/* Ԥ����                                                       */
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

drop table if exists video_module;

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
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

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
/* Table: collect_video                                         */
/*==============================================================*/
create table collect_video
(
   collect_video_id     varchar(255) not null comment '�ղ���Ƶ��������java UUID',
   username             varchar(255) not null comment '�ղ��û���',
   video_id             varchar(255) not null comment '���ղ���Ƶ����',
   create_time          datetime not null comment '�ղ�ʱ��',
   video_title          varchar(255) not null comment '��Ƶ����',
   cover_relative_path  varchar(255) not null comment '��Ƶ����ͼƬ�ļ����·��',
   primary key (collect_video_id),
   unique key AK_user_vedio_id_unique (username, video_id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table collect_video comment '�û��ղ���Ƶ��';

/*==============================================================*/
/* Index: collect_video_table_collecting_user_index             */
/*==============================================================*/
create index collect_video_table_collecting_user_index on collect_video
(
   username
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
   floor_id             varchar(255) not null comment '¥��������java uuid',
   username             varchar(255) not null comment '�����û���',
   content              text not null comment '�������ݣ�text���ֶ�',
   create_time          datetime not null comment '�������۵�ʱ��',
   video_id             varchar(255) not null comment '����������ƵID',
   primary key (floor_id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table comment_floor comment '��Ƶ��������¥��';

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
   
);

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   message_id           varchar(255) not null comment '����',
   username             varchar(255) not null comment '��Ϣ�����û�',
   message_title        varchar(255) not null comment '��Ϣ����',
   content              varchar(255) not null comment '��Ϣ����',
   create_time          datetime not null comment '��Ϣ����ʱ��',
   status               varchar(3) not null comment '��Ϣ״̬����001����ʾδ������002����ʾ�Ѷ�',
   primary key (message_id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table message comment '�û�֪ͨ��Ϣ��';

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
   username             varchar(255) not null comment '�û����������������޸�',
   email                varchar(255) not null comment '�û�ע�����䣬Ψһ�ǿգ�����Ϊ��¼��ʶ',
   create_time          datetime not null comment '�û�ע��ʱ�䣬�ǿ�',
   password             varchar(255) not null comment '�û����룬�ǿգ�������������',
   sex                  varchar(2) comment '�û��Ա�ֻ��������������С�����Ů���������ܡ���Ϊ�յļ���δ֪',
   signature            varchar(255) comment '����ǩ�������ܳ���255����',
   image_relative_path  varchar(255) not null comment '�û�ͷ��ͼƬ�ļ����·��',
   image_absolute_path  varchar(255) not null comment '�û�ͷ��ͼƬ�ļ�����·��',
   primary key (username),
   unique key AK_user_email_unique_key (email)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table user comment '�û��������û��Ļ�����Ϣ';

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
   video_id             varchar(255) not null comment '��Ƶ������java UUID',
   module_id            varchar(255) comment '����ģ��',
   absolute_path        varchar(255) not null comment '��Ƶ�ļ�����·��',
   relative_path        varchar(255) not null comment '��Ƶ�ļ����·��',
   video_name           varchar(255) not null comment '��Ƶ����',
   video_title          varchar(255) not null comment '��Ƶ����',
   username             varchar(255) not null comment 'Ͷ����Ƶ���û���',
   create_time          datetime not null comment '��ƵͶ��ʱ��',
   cover_absolute_path  varchar(255) not null comment '��Ƶ����ͼƬ�ļ�����·��',
   cover_relative_path  varchar(255) not null comment 'ͼƬ����ͼƬ�ļ����·��',
   video_explain        varchar(255) not null comment '��Ƶ˵��',
   primary key (video_id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table video comment '��Ƶ��';

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
   video_id             varchar(255) not null comment '��Ƶ������java UUID',
   module_id            varchar(255) comment '����ģ������',
   absolute_path        varchar(255) not null comment '��Ƶ�ļ�����·��',
   relative_path        varchar(255) not null comment '��Ƶ�ļ����·��',
   video_name           varchar(255) not null comment '��Ƶ����',
   video_title          varchar(255) not null comment '��Ƶ����',
   username             varchar(255) not null comment 'Ͷ����Ƶ���û���',
   create_time          datetime not null comment '��ƵͶ��ʱ��',
   cover_absolute_path  varchar(255) not null comment '��Ƶ����ͼƬ�ļ�����·��',
   cover_relative_path  varchar(255) not null comment 'ͼƬ����ͼƬ�ļ����·��',
   video_explain        varchar(255) not null comment '��Ƶ˵��',
   primary key (video_id)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table video_check comment '��Ƶ��˱���Ƶ���ͨ���󣬼�¼�ͻ������Ƶ��ͬʱ�����¼��ɾ��';

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
/* Table: video_module                                          */
/*==============================================================*/
create table video_module
(
   module_id            varchar(3) not null comment '��Ƶģ������',
   module_name          varchar(255) not null comment 'ģ������',
   primary key (module_id),
   unique key AK_vedio_mudule_name_unique (module_name)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

alter table video_module comment '��Ƶģ���ģ���Ŀǰд������001����ʾ������������002����ʾ�����硱����003����ʾ�����֡�����004����ʾ���赸';

alter table video add constraint FK_Reference_1 foreign key (module_id)
      references video_module (module_id) on delete restrict on update restrict;

alter table video_check add constraint FK_Reference_2 foreign key (module_id)
      references video_module (module_id) on delete restrict on update restrict;


/*==============================================================*/
/* ��ʼ����Ƶģ���                                         */
/*==============================================================*/
insert into video_module values('001','����');
insert into video_module values('002','����');
insert into video_module values('003','����');
insert into video_module values('004','�赸');
insert into video_module values('005','��Ϸ');
insert into video_module values('006','�Ƽ�');
insert into video_module values('007','����');
insert into video_module values('008','����');
insert into video_module values('009','ʱ��');
insert into video_module values('010','���');
insert into video_module values('011','����');
insert into video_module values('012','Ӱ��');




