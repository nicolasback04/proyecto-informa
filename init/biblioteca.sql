--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: actualizar_autor(integer, character varying, character varying, date, boolean, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.actualizar_autor(p_id integer, p_nombre character varying, p_nacionalidad character varying, p_fecha_nacimiento date, p_estado boolean, p_usuario_modificacion character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE autores
    SET nombre = p_nombre,
        nacionalidad = p_nacionalidad,
        fecha_nacimiento = p_fecha_nacimiento,
        estado = p_estado,
        fecha_modificacion = NOW(),
        usuario_modificacion = p_usuario_modificacion
    WHERE id = p_id;
END;
$$;


ALTER FUNCTION public.actualizar_autor(p_id integer, p_nombre character varying, p_nacionalidad character varying, p_fecha_nacimiento date, p_estado boolean, p_usuario_modificacion character varying) OWNER TO postgres;

--
-- Name: actualizar_libro(integer, character varying, integer, integer, character varying, numeric, integer, boolean, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.actualizar_libro(p_id integer, p_titulo character varying, p_autor_id integer, p_anio_publicacion integer, p_genero character varying, p_precio numeric, p_stock integer, p_estado boolean, p_usuario_modificacion character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE libros
    SET titulo = p_titulo,
        autor_id = p_autor_id,
        anio_publicacion = p_anio_publicacion,
        genero = p_genero,
        precio = p_precio,
        stock = p_stock,
        estado = p_estado,
        fecha_modificacion = NOW(),
        usuario_modificacion = p_usuario_modificacion
    WHERE id = p_id;
END;
$$;


ALTER FUNCTION public.actualizar_libro(p_id integer, p_titulo character varying, p_autor_id integer, p_anio_publicacion integer, p_genero character varying, p_precio numeric, p_stock integer, p_estado boolean, p_usuario_modificacion character varying) OWNER TO postgres;

--
-- Name: eliminar_autor(integer, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.eliminar_autor(p_id integer, p_usuario_modificacion character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE autores
    SET estado = FALSE,
        fecha_modificacion = NOW(),
        usuario_modificacion = p_usuario_modificacion
    WHERE id = p_id;
END;
$$;


ALTER FUNCTION public.eliminar_autor(p_id integer, p_usuario_modificacion character varying) OWNER TO postgres;

--
-- Name: eliminar_libro(integer, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.eliminar_libro(p_id integer, p_usuario_modificacion character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE libros
    SET estado = FALSE,
        fecha_modificacion = NOW(),
        usuario_modificacion = p_usuario_modificacion
    WHERE id = p_id;
END;
$$;


ALTER FUNCTION public.eliminar_libro(p_id integer, p_usuario_modificacion character varying) OWNER TO postgres;

--
-- Name: insertar_autor(character varying, character varying, date, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insertar_autor(p_nombre character varying, p_nacionalidad character varying, p_fecha_nacimiento date, p_usuario_creacion character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_id INTEGER;
BEGIN
    INSERT INTO autores (
        nombre, 
        nacionalidad, 
        fecha_nacimiento, 
        estado, 
        fecha_creacion, 
        usuario_creacion
    )
    VALUES (
        p_nombre,
        p_nacionalidad,
        p_fecha_nacimiento,
        TRUE,
        NOW(),
        p_usuario_creacion
    )
    RETURNING id INTO v_id;

    RETURN v_id;
END;
$$;


ALTER FUNCTION public.insertar_autor(p_nombre character varying, p_nacionalidad character varying, p_fecha_nacimiento date, p_usuario_creacion character varying) OWNER TO postgres;

--
-- Name: insertar_libro(character varying, integer, integer, character varying, numeric, integer, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insertar_libro(p_titulo character varying, p_autor_id integer, p_anio_publicacion integer, p_genero character varying, p_precio numeric, p_stock integer, p_usuario_creacion character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_id INTEGER;
BEGIN
    INSERT INTO libros (
        titulo,
        autor_id,
        anio_publicacion,
        genero,
        precio,
        stock,
        estado,
        fecha_creacion,
        usuario_creacion
    )
    VALUES (
        p_titulo,
        p_autor_id,
        p_anio_publicacion,
        p_genero,
        p_precio,
        p_stock,
        TRUE,
        NOW(),
        p_usuario_creacion
    )
    RETURNING id INTO v_id;

    RETURN v_id;
END;
$$;


ALTER FUNCTION public.insertar_libro(p_titulo character varying, p_autor_id integer, p_anio_publicacion integer, p_genero character varying, p_precio numeric, p_stock integer, p_usuario_creacion character varying) OWNER TO postgres;

--
-- Name: obtener_autor_por_id(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.obtener_autor_por_id(p_id integer) RETURNS TABLE(id integer, nombre character varying, nacionalidad character varying, fecha_nacimiento date, estado boolean, fecha_creacion timestamp without time zone, usuario_creacion character varying, fecha_modificacion timestamp without time zone, usuario_modificacion character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY
    SELECT 
        a.id,
        a.nombre,
        a.nacionalidad,
        a.fecha_nacimiento,
        a.estado,
        a.fecha_creacion,
        a.usuario_creacion,
        a.fecha_modificacion,
        a.usuario_modificacion
    FROM autores a
    WHERE a.id = p_id;
END;
$$;


ALTER FUNCTION public.obtener_autor_por_id(p_id integer) OWNER TO postgres;

--
-- Name: obtener_libro_por_id(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.obtener_libro_por_id(p_id integer) RETURNS TABLE(id integer, titulo character varying, autor_id integer, anio_publicacion integer, genero character varying, precio numeric, stock integer, estado boolean, fecha_creacion timestamp without time zone, usuario_creacion character varying, fecha_modificacion timestamp without time zone, usuario_modificacion character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
  RETURN QUERY SELECT l.* FROM libros l WHERE l.id = p_id;
END;
$$;


ALTER FUNCTION public.obtener_libro_por_id(p_id integer) OWNER TO postgres;

--
-- Name: obtener_todos_autores(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.obtener_todos_autores() RETURNS TABLE(id integer, nombre character varying, nacionalidad character varying, fecha_nacimiento date, estado boolean, fecha_creacion timestamp without time zone, usuario_creacion character varying, fecha_modificacion timestamp without time zone, usuario_modificacion character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY
    SELECT 
        a.id,
        a.nombre,
        a.nacionalidad,
        a.fecha_nacimiento,
        a.estado,
        a.fecha_creacion,
        a.usuario_creacion,
        a.fecha_modificacion,
        a.usuario_modificacion
    FROM autores a
    WHERE a.estado = TRUE;
END;
$$;


ALTER FUNCTION public.obtener_todos_autores() OWNER TO postgres;

--
-- Name: obtener_todos_libros(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.obtener_todos_libros() RETURNS TABLE(id integer, titulo character varying, genero character varying, anio_publicacion integer, precio numeric, stock integer, autor_id integer, estado boolean, fecha_creacion timestamp without time zone, usuario_creacion character varying, fecha_modificacion timestamp without time zone, usuario_modificacion character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
  RETURN QUERY
  SELECT 
    l.id,
    l.titulo,
    l.genero,
    l.anio_publicacion,
    l.precio,
    l.stock,
    l.autor_id,
    l.estado,
    l.fecha_creacion,
    l.usuario_creacion,
    l.fecha_modificacion,
    l.usuario_modificacion
  FROM libros AS l
  ORDER BY l.titulo;
END;
$$;


ALTER FUNCTION public.obtener_todos_libros() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: autores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.autores (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    nacionalidad character varying(50),
    fecha_nacimiento date,
    estado boolean DEFAULT true,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion character varying(50),
    fecha_modificacion timestamp without time zone,
    usuario_modificacion character varying(50)
);


ALTER TABLE public.autores OWNER TO postgres;

--
-- Name: autores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.autores_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.autores_id_seq OWNER TO postgres;

--
-- Name: autores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.autores_id_seq OWNED BY public.autores.id;


--
-- Name: libros; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.libros (
    id integer NOT NULL,
    titulo character varying(200) NOT NULL,
    autor_id integer NOT NULL,
    anio_publicacion integer,
    genero character varying(50),
    precio numeric(10,2),
    stock integer DEFAULT 0,
    estado boolean DEFAULT true,
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    usuario_creacion character varying(50),
    fecha_modificacion timestamp without time zone,
    usuario_modificacion character varying(50)
);


ALTER TABLE public.libros OWNER TO postgres;

--
-- Name: libros_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.libros_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.libros_id_seq OWNER TO postgres;

--
-- Name: libros_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.libros_id_seq OWNED BY public.libros.id;


--
-- Name: vista_libros_autores; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vista_libros_autores AS
 SELECT l.id AS libro_id,
    l.titulo,
    l.genero,
    l.anio_publicacion,
    l.precio,
    l.stock,
    a.nombre AS autor,
    a.nacionalidad
   FROM (public.libros l
     JOIN public.autores a ON ((l.autor_id = a.id)));


ALTER VIEW public.vista_libros_autores OWNER TO postgres;

--
-- Name: autores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autores ALTER COLUMN id SET DEFAULT nextval('public.autores_id_seq'::regclass);


--
-- Name: libros id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libros ALTER COLUMN id SET DEFAULT nextval('public.libros_id_seq'::regclass);


--
-- Data for Name: autores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.autores (id, nombre, nacionalidad, fecha_nacimiento, estado, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion) FROM stdin;
12	Juan Domingo	Colombiana	1927-03-05	t	2025-07-24 14:19:04.452813	admin	\N	\N
13	Juan Domingo Java	Colombiana	1927-03-05	t	2025-07-24 14:29:36.781161	admin	\N	\N
14	Har Domingo Java	Colombiana	1927-03-05	t	2025-07-24 14:53:59.541417	admin	\N	\N
4	Gabriel García Márquez	Colombiana	1927-03-05	f	2025-07-24 13:50:23.498402	admin_system	2025-07-24 15:01:18.938531	admin_system
\.


--
-- Data for Name: libros; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.libros (id, titulo, autor_id, anio_publicacion, genero, precio, stock, estado, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion) FROM stdin;
5	Cien Años de Soledad (Edición remasterizada)	4	1967	Realismo mágico	35.00	15	t	2025-07-24 15:53:21.249721	admin_system	2025-07-24 16:09:45.666496	admin_system
6	Cien Años de Soledad	4	1967	Realismo mágico	29.90	20	t	2025-07-24 16:17:10.484534	admin_system	\N	\N
\.


--
-- Name: autores_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.autores_id_seq', 14, true);


--
-- Name: libros_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.libros_id_seq', 6, true);


--
-- Name: autores autores_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.autores
    ADD CONSTRAINT autores_pkey PRIMARY KEY (id);


--
-- Name: libros libros_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libros
    ADD CONSTRAINT libros_pkey PRIMARY KEY (id);


--
-- Name: libros libros_autor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.libros
    ADD CONSTRAINT libros_autor_id_fkey FOREIGN KEY (autor_id) REFERENCES public.autores(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

