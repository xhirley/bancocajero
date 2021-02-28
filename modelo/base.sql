--
-- PostgreSQL database dump
--

-- Dumped from database version 11.0
-- Dumped by pg_dump version 11.11

-- Started on 2021-02-28 00:11:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE ONLY public.transaccion DROP CONSTRAINT fk_transacc_realiza_cuenta;
ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_tiene_cliente;
ALTER TABLE ONLY public.cuenta DROP CONSTRAINT fk_cuenta_pertenece_banco;
DROP INDEX public.transaccion_pk;
DROP INDEX public.tiene_fk;
DROP INDEX public.realiza_fk;
DROP INDEX public.pertenece_fk;
DROP INDEX public.cuenta_pk;
DROP INDEX public.cliente_pk;
DROP INDEX public.banco_pk;
ALTER TABLE ONLY public.transaccion DROP CONSTRAINT pk_transaccion;
ALTER TABLE ONLY public.cuenta DROP CONSTRAINT pk_cuenta;
ALTER TABLE ONLY public.cliente DROP CONSTRAINT pk_cliente;
ALTER TABLE ONLY public.banco DROP CONSTRAINT pk_banco;
ALTER TABLE public.transaccion ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.cuenta ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.cliente ALTER COLUMN id DROP DEFAULT;
ALTER TABLE public.banco ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE public.transaccion_id_seq;
DROP TABLE public.transaccion;
DROP SEQUENCE public.cuenta_id_seq;
DROP TABLE public.cuenta;
DROP SEQUENCE public.cliente_id_seq;
DROP TABLE public.cliente;
DROP SEQUENCE public.banco_id_seq;
DROP TABLE public.banco;
SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16400)
-- Name: banco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.banco (
    id integer NOT NULL,
    razon character varying(255) NOT NULL,
    logo character varying(255) NOT NULL
);


ALTER TABLE public.banco OWNER TO postgres;

--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 197
-- Name: TABLE banco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.banco IS 'Tabla Banco';


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN banco.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.banco.id IS 'Id banco';


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN banco.razon; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.banco.razon IS 'Nombre del Banco';


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 197
-- Name: COLUMN banco.logo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.banco.logo IS 'Path logo banco';


--
-- TOC entry 196 (class 1259 OID 16398)
-- Name: banco_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.banco_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.banco_id_seq OWNER TO postgres;

--
-- TOC entry 2866 (class 0 OID 0)
-- Dependencies: 196
-- Name: banco_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.banco_id_seq OWNED BY public.banco.id;


--
-- TOC entry 199 (class 1259 OID 16412)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id integer NOT NULL,
    usuario character varying(20) NOT NULL,
    clave character varying(255) NOT NULL,
    cedula character varying(20) NOT NULL,
    nombres character varying(255) NOT NULL,
    apellidos character varying(255) NOT NULL,
    correo character varying(255) NOT NULL,
    celular character varying(20) NOT NULL,
    esadmin boolean NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 2867 (class 0 OID 0)
-- Dependencies: 199
-- Name: TABLE cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.cliente IS 'Tabla de Clientes';


--
-- TOC entry 2868 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.id IS 'Id secuencial cliente';


--
-- TOC entry 2869 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.usuario IS 'Usuario cliente';


--
-- TOC entry 2870 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.clave; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.clave IS 'Clave cliente';


--
-- TOC entry 2871 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.cedula; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.cedula IS 'Cedula clilente';


--
-- TOC entry 2872 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.nombres; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.nombres IS 'Nombres cliente';


--
-- TOC entry 2873 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.apellidos; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.apellidos IS 'Apellidos cliente';


--
-- TOC entry 2874 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.correo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.correo IS 'Correo cliente';


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN cliente.celular; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cliente.celular IS 'Celular cliente';


--
-- TOC entry 198 (class 1259 OID 16410)
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 198
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- TOC entry 201 (class 1259 OID 16424)
-- Name: cuenta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cuenta (
    id integer NOT NULL,
    ban_id integer NOT NULL,
    cli_id integer NOT NULL,
    numero character varying(20) NOT NULL,
    tipo character varying(20) NOT NULL,
    saldo numeric(9,2) NOT NULL
);


ALTER TABLE public.cuenta OWNER TO postgres;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 201
-- Name: TABLE cuenta; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.cuenta IS 'Tabla de Cuentas';


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN cuenta.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuenta.id IS 'Id cuenta';


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN cuenta.ban_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuenta.ban_id IS 'Id banco';


--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN cuenta.cli_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuenta.cli_id IS 'Id secuencial cliente';


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN cuenta.numero; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuenta.numero IS 'Numero Cuenta';


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN cuenta.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuenta.tipo IS 'Tipo Cuenta';


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 201
-- Name: COLUMN cuenta.saldo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.cuenta.saldo IS 'Saldo Cuenta';


--
-- TOC entry 200 (class 1259 OID 16422)
-- Name: cuenta_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cuenta_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cuenta_id_seq OWNER TO postgres;

--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 200
-- Name: cuenta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cuenta_id_seq OWNED BY public.cuenta.id;


--
-- TOC entry 203 (class 1259 OID 24612)
-- Name: transaccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaccion (
    id integer NOT NULL,
    cue_id integer NOT NULL,
    fechahora timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    monto numeric(9,2) NOT NULL,
    accion character varying(20) NOT NULL,
    tipo character varying(20) NOT NULL,
    bancotransfer character varying(255) NOT NULL,
    tipoctatransfer character varying(20) NOT NULL,
    nroctatransfer character varying(20) NOT NULL
);


ALTER TABLE public.transaccion OWNER TO postgres;

--
-- TOC entry 2885 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.id IS 'Id Transaccion';


--
-- TOC entry 2886 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.cue_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.cue_id IS 'Id cuenta';


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.fechahora; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.fechahora IS 'Fecha Hora Transaccion';


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.monto; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.monto IS 'Monto Transaccion';


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.accion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.accion IS 'Accion Deposito Retiro Transferencia';


--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.tipo IS 'Tipo Transaccion Debito Credito';


--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.bancotransfer; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.bancotransfer IS 'Banco Transferencia';


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.tipoctatransfer; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.tipoctatransfer IS 'Tipocta Transferencia';


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 203
-- Name: COLUMN transaccion.nroctatransfer; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaccion.nroctatransfer IS 'Nrocta Transferencia';


--
-- TOC entry 202 (class 1259 OID 24610)
-- Name: transaccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaccion_id_seq OWNER TO postgres;

--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 202
-- Name: transaccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaccion_id_seq OWNED BY public.transaccion.id;


--
-- TOC entry 2705 (class 2604 OID 16403)
-- Name: banco id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.banco ALTER COLUMN id SET DEFAULT nextval('public.banco_id_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 16415)
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- TOC entry 2707 (class 2604 OID 16427)
-- Name: cuenta id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta ALTER COLUMN id SET DEFAULT nextval('public.cuenta_id_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 24615)
-- Name: transaccion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaccion ALTER COLUMN id SET DEFAULT nextval('public.transaccion_id_seq'::regclass);


--
-- TOC entry 2850 (class 0 OID 16400)
-- Dependencies: 197
-- Data for Name: banco; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.banco VALUES (1, 'BANCO1', '');


--
-- TOC entry 2852 (class 0 OID 16412)
-- Dependencies: 199
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cliente VALUES (1, 'lguzman', '68053af2923e00204c3ca7c6a3150cf7', '0000', 'LUIS ART', 'GUZMAN', 'laguz81@gmail.com', '9999999999', true);
INSERT INTO public.cliente VALUES (3, '', '', '7777', 'SILVIA', 'GALLOS', 'ryry@p.com', '33535', false);
INSERT INTO public.cliente VALUES (4, 'aperez', '2801d271c539286ca17a54c81aec9e18', '9999', 'ALBERTO', 'PEREZ', 'sdfsad@hotmail.com', '', false);


--
-- TOC entry 2854 (class 0 OID 16424)
-- Dependencies: 201
-- Data for Name: cuenta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cuenta VALUES (5, 1, 3, '7879-9', 'CORRIENTE', 0.00);
INSERT INTO public.cuenta VALUES (11, 1, 4, '98', 'AHORROS', 1500.00);
INSERT INTO public.cuenta VALUES (1, 1, 1, '123-4', 'AHORROS', 100.18);
INSERT INTO public.cuenta VALUES (10, 1, 1, '5896-8', 'AHORROS', 135.24);


--
-- TOC entry 2856 (class 0 OID 24612)
-- Dependencies: 203
-- Data for Name: transaccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transaccion VALUES (1, 1, '2021-02-27 17:45:46.649801', 100.00, 'DEPOSITO', 'DEBITO', '', '', '');
INSERT INTO public.transaccion VALUES (2, 10, '2021-02-27 20:28:53.70367', 10.00, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (3, 10, '2021-02-27 20:38:12.52379', 10.00, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (4, 1, '2021-02-27 21:22:14.166999', 150.00, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (5, 10, '2021-02-27 21:27:37.247479', 10.55, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (6, 10, '2021-02-27 21:29:19.804002', 55.61, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (7, 10, '2021-02-27 22:03:55.372918', 16.16, 'RETIRO', 'DEBITO', '', '', '');
INSERT INTO public.transaccion VALUES (8, 10, '2021-02-27 22:10:42.388692', 10.00, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (9, 10, '2021-02-27 22:13:10.099549', 4.99, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (10, 10, '2021-02-27 22:13:37.13831', 84.00, 'RETIRO', 'DEBITO', '', '', '');
INSERT INTO public.transaccion VALUES (11, 1, '2021-02-27 22:14:13.659114', 15.57, 'RETIRO', 'DEBITO', '', '', '');
INSERT INTO public.transaccion VALUES (12, 11, '2021-02-27 23:03:14.505725', 1500.00, 'DEPOSITO', 'CREDITO', '', '', '');
INSERT INTO public.transaccion VALUES (13, 1, '2021-02-28 00:01:22.137901', 134.25, 'TRANSFERENCIA', 'DEBITO', 'BANCO1', 'AHORROS', '5896-8');
INSERT INTO public.transaccion VALUES (14, 10, '2021-02-28 00:01:52.529102', 134.25, 'TRANSFERENCIA', 'CREDITO', 'BANCO1', 'AHORROS', '123-4');


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 196
-- Name: banco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.banco_id_seq', 3, true);


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 198
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 4, true);


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 200
-- Name: cuenta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cuenta_id_seq', 11, true);


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 202
-- Name: transaccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaccion_id_seq', 14, true);


--
-- TOC entry 2712 (class 2606 OID 16408)
-- Name: banco pk_banco; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.banco
    ADD CONSTRAINT pk_banco PRIMARY KEY (id);


--
-- TOC entry 2715 (class 2606 OID 16420)
-- Name: cliente pk_cliente; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (id);


--
-- TOC entry 2719 (class 2606 OID 16429)
-- Name: cuenta pk_cuenta; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT pk_cuenta PRIMARY KEY (id);


--
-- TOC entry 2722 (class 2606 OID 24618)
-- Name: transaccion pk_transaccion; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT pk_transaccion PRIMARY KEY (id);


--
-- TOC entry 2710 (class 1259 OID 16409)
-- Name: banco_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX banco_pk ON public.banco USING btree (id);


--
-- TOC entry 2713 (class 1259 OID 16421)
-- Name: cliente_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX cliente_pk ON public.cliente USING btree (id);


--
-- TOC entry 2716 (class 1259 OID 16430)
-- Name: cuenta_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX cuenta_pk ON public.cuenta USING btree (id);


--
-- TOC entry 2717 (class 1259 OID 16432)
-- Name: pertenece_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX pertenece_fk ON public.cuenta USING btree (ban_id);


--
-- TOC entry 2723 (class 1259 OID 24620)
-- Name: realiza_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX realiza_fk ON public.transaccion USING btree (cue_id);


--
-- TOC entry 2720 (class 1259 OID 16431)
-- Name: tiene_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX tiene_fk ON public.cuenta USING btree (cli_id);


--
-- TOC entry 2724 (class 1259 OID 24619)
-- Name: transaccion_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX transaccion_pk ON public.transaccion USING btree (id);


--
-- TOC entry 2725 (class 2606 OID 16444)
-- Name: cuenta fk_cuenta_pertenece_banco; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_pertenece_banco FOREIGN KEY (ban_id) REFERENCES public.banco(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2726 (class 2606 OID 16449)
-- Name: cuenta fk_cuenta_tiene_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT fk_cuenta_tiene_cliente FOREIGN KEY (cli_id) REFERENCES public.cliente(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2727 (class 2606 OID 24621)
-- Name: transaccion fk_transacc_realiza_cuenta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaccion
    ADD CONSTRAINT fk_transacc_realiza_cuenta FOREIGN KEY (cue_id) REFERENCES public.cuenta(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2021-02-28 00:11:36

--
-- PostgreSQL database dump complete
--

