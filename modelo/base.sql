/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     23/2/2021 20:27:11                           */
/*==============================================================*/


drop index BANCO_PK;

drop table BANCO;

drop index CLIENTE_PK;

drop table CLIENTE;

drop index PERTENECE_FK;

drop index TIENE_FK;

drop index CUENTA_PK;

drop table CUENTA;

drop index REALIZA_FK;

drop index TRANSACCION_PK;

drop table TRANSACCION;

/*==============================================================*/
/* Table: BANCO                                                 */
/*==============================================================*/
create table BANCO (
   ID                   SERIAL not null,
   RAZON                VARCHAR(255)         not null,
   LOGO                 VARCHAR(255)         not null,
   constraint PK_BANCO primary key (ID)
);

comment on table BANCO is
'Tabla Banco';

comment on column BANCO.ID is
'Id banco';

comment on column BANCO.RAZON is
'Nombre del Banco';

comment on column BANCO.LOGO is
'Path logo banco';

/*==============================================================*/
/* Index: BANCO_PK                                              */
/*==============================================================*/
create unique index BANCO_PK on BANCO (
ID
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   ID                   SERIAL not null,
   USUARIO              VARCHAR(20)          not null,
   CLAVE                VARCHAR(255)         not null,
   CEDULA               VARCHAR(20)          not null,
   NOMBRES              VARCHAR(255)         not null,
   APELLIDOS            VARCHAR(255)         not null,
   CORREO               VARCHAR(255)         not null,
   CELULAR              VARCHAR(20)          not null,
   ESADMIN              BOOL                 not null default false,
   constraint PK_CLIENTE primary key (ID)
);

comment on table CLIENTE is
'Tabla de Clientes';

comment on column CLIENTE.ID is
'Id secuencial cliente';

comment on column CLIENTE.USUARIO is
'Usuario cliente';

comment on column CLIENTE.CLAVE is
'Clave cliente';

comment on column CLIENTE.CEDULA is
'Cedula clilente';

comment on column CLIENTE.NOMBRES is
'Nombres cliente';

comment on column CLIENTE.APELLIDOS is
'Apellidos cliente';

comment on column CLIENTE.CORREO is
'Correo cliente';

comment on column CLIENTE.CELULAR is
'Celular cliente';

comment on column CLIENTE.ESADMIN is
'Es administrador';

/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique index CLIENTE_PK on CLIENTE (
ID
);

/*==============================================================*/
/* Table: CUENTA                                                */
/*==============================================================*/
create table CUENTA (
   ID                   SERIAL not null,
   BAN_ID               INT4                 not null,
   CLI_ID               INT4                 not null,
   NUMERO               VARCHAR(20)          not null,
   TIPO                 VARCHAR(20)          not null,
   SALDO                DECIMAL(9,2)         not null,
   constraint PK_CUENTA primary key (ID)
);

comment on table CUENTA is
'Tabla de Cuentas';

comment on column CUENTA.ID is
'Id cuenta';

comment on column CUENTA.BAN_ID is
'Id banco';

comment on column CUENTA.CLI_ID is
'Id secuencial cliente';

comment on column CUENTA.NUMERO is
'Numero Cuenta';

comment on column CUENTA.TIPO is
'Tipo Cuenta';

comment on column CUENTA.SALDO is
'Saldo Cuenta';

/*==============================================================*/
/* Index: CUENTA_PK                                             */
/*==============================================================*/
create unique index CUENTA_PK on CUENTA (
ID
);

/*==============================================================*/
/* Index: TIENE_FK                                              */
/*==============================================================*/
create  index TIENE_FK on CUENTA (
CLI_ID
);

/*==============================================================*/
/* Index: PERTENECE_FK                                          */
/*==============================================================*/
create  index PERTENECE_FK on CUENTA (
BAN_ID
);

/*==============================================================*/
/* Table: TRANSACCION                                           */
/*==============================================================*/
create table TRANSACCION (
   ID                   SERIAL not null,
   CUE_ID               INT4                 not null,
   FECHAHORA            TIMESTAMP            not null,
   MONTO                DECIMAL(9,2)         not null,
   TIPO                 VARCHAR(20)          not null
      constraint CKC_TIPO_TRANSACC check (TIPO in ('DEPOSITO','RETIRO','TRANSFERENCIA')),
   BANCOTRANSFER        VARCHAR(255)         null,
   TIPOCTATRANSFER      VARCHAR(20)          null,
   NROCTATRANSFER       VARCHAR(20)          null,
   constraint PK_TRANSACCION primary key (ID)
);

comment on column TRANSACCION.ID is
'Id Transaccion';

comment on column TRANSACCION.CUE_ID is
'Id cuenta';

comment on column TRANSACCION.FECHAHORA is
'Fecha Hora Transaccion';

comment on column TRANSACCION.MONTO is
'Monto Transaccion';

comment on column TRANSACCION.TIPO is
'Tipo Transaccion';

comment on column TRANSACCION.BANCOTRANSFER is
'Banco Transferencia';

comment on column TRANSACCION.TIPOCTATRANSFER is
'Tipocta Transferencia';

comment on column TRANSACCION.NROCTATRANSFER is
'Nrocta Transferencia';

/*==============================================================*/
/* Index: TRANSACCION_PK                                        */
/*==============================================================*/
create unique index TRANSACCION_PK on TRANSACCION (
ID
);

/*==============================================================*/
/* Index: REALIZA_FK                                            */
/*==============================================================*/
create  index REALIZA_FK on TRANSACCION (
CUE_ID
);

alter table CUENTA
   add constraint FK_CUENTA_PERTENECE_BANCO foreign key (BAN_ID)
      references BANCO (ID)
      on delete restrict on update restrict;

alter table CUENTA
   add constraint FK_CUENTA_TIENE_CLIENTE foreign key (CLI_ID)
      references CLIENTE (ID)
      on delete restrict on update restrict;

alter table TRANSACCION
   add constraint FK_TRANSACC_REALIZA_CUENTA foreign key (CUE_ID)
      references CUENTA (ID)
      on delete restrict on update restrict;

