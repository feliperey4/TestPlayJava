# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table insumo (
  id_insumo                 varchar(255) not null,
  nombre                    varchar(255),
  stock                     integer,
  constraint uq_insumo_nombre unique (nombre),
  constraint pk_insumo primary key (id_insumo))
;

create table producto (
  id_producto               varchar(255) not null,
  nombre                    varchar(255),
  descripcion               varchar(500),
  constraint uq_producto_nombre unique (nombre),
  constraint pk_producto primary key (id_producto))
;

create table venta (
  id_venta                  varchar(255) not null,
  comprador                 varchar(255),
  cantidad                  integer,
  id_producto               varchar(255),
  constraint pk_venta primary key (id_venta))
;


create table producto_insumo (
  ID_PRODUCTO                    varchar(255) not null,
  ID_INSUMO                      varchar(255) not null,
  constraint pk_producto_insumo primary key (ID_PRODUCTO, ID_INSUMO))
;
create sequence insumo_seq;

create sequence producto_seq;

create sequence venta_seq;

alter table venta add constraint fk_venta_producto_1 foreign key (id_producto) references producto (id_producto) on delete restrict on update restrict;
create index ix_venta_producto_1 on venta (id_producto);



alter table producto_insumo add constraint fk_producto_insumo_producto_01 foreign key (ID_PRODUCTO) references producto (ID_PRODUCTO) on delete restrict on update restrict;

alter table producto_insumo add constraint fk_producto_insumo_insumo_02 foreign key (ID_INSUMO) references insumo (ID_INSUMO) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists insumo;

drop table if exists producto_insumo;

drop table if exists producto;

drop table if exists venta;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists insumo_seq;

drop sequence if exists producto_seq;

drop sequence if exists venta_seq;

