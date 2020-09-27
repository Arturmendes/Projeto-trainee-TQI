--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.22
-- Dumped by pg_dump version 9.5.22

-- Started on 2020-08-31 10:55:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2119 (class 1262 OID 16437)
-- Name: projeto-tqi; Type: DATABASE; Schema: -; Owner: artur
--

CREATE DATABASE "projeto-tqi" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE "projeto-tqi" OWNER TO artur;

\connect -reuse-previous=on "dbname='projeto-tqi'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2122 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 183 (class 1259 OID 32823)
-- Name: idemprestimo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.idemprestimo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idemprestimo OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 32825)
-- Name: emprestimo; Type: TABLE; Schema: public; Owner: artur
--

CREATE TABLE public.emprestimo (
    id bigint DEFAULT nextval('public.idemprestimo'::regclass) NOT NULL,
    valor double precision,
    qtdparcelas bigint,
    situacao character varying,
    idcliente bigint NOT NULL,
    parcelas double precision
);


ALTER TABLE public.emprestimo OWNER TO artur;

--
-- TOC entry 182 (class 1259 OID 24631)
-- Name: idusuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.idusuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.idusuario OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16441)
-- Name: usuario; Type: TABLE; Schema: public; Owner: artur
--

CREATE TABLE public.usuario (
    login character varying,
    senha character varying,
    tipo character varying,
    id bigint DEFAULT nextval('public.idusuario'::regclass) NOT NULL,
    nome character varying,
    telefone character varying(30),
    cpf character varying(15),
    cep character varying(9),
    endereco character varying(200),
    numero character varying(50),
    complemento character varying(200),
    cidade character varying(200),
    bairro character varying(200),
    estado character varying(50),
    rg character varying(20)
);


ALTER TABLE public.usuario OWNER TO artur;

--
-- TOC entry 2113 (class 0 OID 32825)
-- Dependencies: 184
-- Data for Name: emprestimo; Type: TABLE DATA; Schema: public; Owner: artur
--



--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 183
-- Name: idemprestimo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.idemprestimo', 51, true);


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 182
-- Name: idusuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.idusuario', 74, true);


--
-- TOC entry 2110 (class 0 OID 16441)
-- Dependencies: 181
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: artur
--

INSERT INTO public.usuario (login, senha, tipo, id, nome, telefone, cpf, cep, endereco, numero, complemento, cidade, bairro, estado, rg) VALUES ('07404357608', '123', 'Cliente', 70, 'Daniela de Fatima Moreira', '(34) 3 2148-058', '074.043.576-08', '38402-182', 'Rua Amaral Coutinho', '1008', 'CASA', 'Uberlândia', 'Minas Brasil', 'MG', '13624233');
INSERT INTO public.usuario (login, senha, tipo, id, nome, telefone, cpf, cep, endereco, numero, complemento, cidade, bairro, estado, rg) VALUES ('45252418292', '123', 'Cliente', 72, 'TESTE', '(34) 9 9209-4200', '452.524.182-92', '38402-182', 'Rua Amaral Coutinho', '1008', 'casa 2', 'Uberlândia', 'Minas Brasil', 'MG', '13624233');
INSERT INTO public.usuario (login, senha, tipo, id, nome, telefone, cpf, cep, endereco, numero, complemento, cidade, bairro, estado, rg) VALUES ('07652661641', '123', 'Admin', 74, 'ARTUR MENDES SANTOS', '(34) 9 9687-0601', '076.526.616-41', '38402-182', 'Rua Amaral Coutinho', '1008', 'CASA2', 'Uberlândia', 'Minas Brasil', 'MG', '13624233');
INSERT INTO public.usuario (login, senha, tipo, id, nome, telefone, cpf, cep, endereco, numero, complemento, cidade, bairro, estado, rg) VALUES ('32307780653', '123', 'Cliente', 73, 'Joana Darc dos Santos', '(34) 9 9651-1658', '323.077.806-53', '38410-018', 'Alameda Jandira Siqueira de Lima', '143', 'CASA', 'Uberlândia', 'Granada', 'MG', '13624233');


--
-- TOC entry 1994 (class 2606 OID 32833)
-- Name: emprestimo_pkey; Type: CONSTRAINT; Schema: public; Owner: artur
--

ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_pkey PRIMARY KEY (id);


--
-- TOC entry 1992 (class 2606 OID 32822)
-- Name: usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: artur
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 1995 (class 2606 OID 32834)
-- Name: emprestimo_idcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: artur
--

ALTER TABLE ONLY public.emprestimo
    ADD CONSTRAINT emprestimo_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES public.usuario(id);


--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2020-08-31 10:55:38

--
-- PostgreSQL database dump complete
--

