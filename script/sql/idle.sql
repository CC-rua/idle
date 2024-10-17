--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.24
-- Dumped by pg_dump version 9.6.24

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

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: db_check; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.db_check (
    id integer NOT NULL,
    name integer
);


ALTER TABLE public.db_check OWNER TO postgres;

--
-- Name: player_game_idle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player_game_idle (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    config_id bigint NOT NULL,
    settle_time timestamp without time zone,
    create_time time without time zone DEFAULT ('now'::text)::time with time zone NOT NULL
);


ALTER TABLE public.player_game_idle OWNER TO postgres;

--
-- Name: TABLE player_game_idle; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.player_game_idle IS '挂机结算';


--
-- Name: COLUMN player_game_idle.role_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_idle.role_id IS '角色id';


--
-- Name: COLUMN player_game_idle.settle_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_idle.settle_time IS '最后结算时间';


--
-- Name: COLUMN player_game_idle.create_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_idle.create_time IS '创建时间';


--
-- Name: player_game_idle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_game_idle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_game_idle_id_seq OWNER TO postgres;

--
-- Name: player_game_idle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_game_idle_id_seq OWNED BY public.player_game_idle.id;


--
-- Name: player_game_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player_game_item (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    config_id bigint DEFAULT 0 NOT NULL,
    count bigint DEFAULT 0 NOT NULL,
    create_time time without time zone DEFAULT ('now'::text)::time with time zone NOT NULL
);


ALTER TABLE public.player_game_item OWNER TO postgres;

--
-- Name: TABLE player_game_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.player_game_item IS '玩家道具';


--
-- Name: COLUMN player_game_item.role_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_item.role_id IS '角色id';


--
-- Name: COLUMN player_game_item.config_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_item.config_id IS '配置id';


--
-- Name: COLUMN player_game_item.count; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_item.count IS '数量';


--
-- Name: COLUMN player_game_item.create_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_item.create_time IS '创建时间';


--
-- Name: player_game_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_game_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_game_item_id_seq OWNER TO postgres;

--
-- Name: player_game_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_game_item_id_seq OWNED BY public.player_game_item.id;


--
-- Name: player_game_param; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player_game_param (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    param_type integer DEFAULT 0 NOT NULL,
    value character varying,
    create_time time without time zone DEFAULT ('now'::text)::time with time zone NOT NULL
);


ALTER TABLE public.player_game_param OWNER TO postgres;

--
-- Name: TABLE player_game_param; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.player_game_param IS '玩家 - 参数';


--
-- Name: COLUMN player_game_param.role_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_param.role_id IS 'player_game_role 表的id';


--
-- Name: COLUMN player_game_param.param_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_param.param_type IS '参数类型';


--
-- Name: COLUMN player_game_param.value; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_param.value IS '值';


--
-- Name: COLUMN player_game_param.create_time; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_param.create_time IS '创建时间';


--
-- Name: player_game_param_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_game_param_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_game_param_id_seq OWNER TO postgres;

--
-- Name: player_game_param_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_game_param_id_seq OWNED BY public.player_game_param.id;


--
-- Name: player_game_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player_game_role (
    id bigint NOT NULL,
    name character varying,
    is_new boolean DEFAULT true NOT NULL,
    locked boolean DEFAULT false NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    create_time time without time zone DEFAULT ('now'::text)::time with time zone NOT NULL
);


ALTER TABLE public.player_game_role OWNER TO postgres;

--
-- Name: TABLE player_game_role; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.player_game_role IS '游戏角色';


--
-- Name: COLUMN player_game_role.id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_role.id IS '唯一id';


--
-- Name: COLUMN player_game_role.name; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_role.name IS '昵称';


--
-- Name: COLUMN player_game_role.is_new; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_role.is_new IS '是否新用户';


--
-- Name: COLUMN player_game_role.locked; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_role.locked IS '是否锁定';


--
-- Name: COLUMN player_game_role.deleted; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.player_game_role.deleted IS '是否删除';


--
-- Name: player_game_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.player_game_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.player_game_role_id_seq OWNER TO postgres;

--
-- Name: player_game_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.player_game_role_id_seq OWNED BY public.player_game_role.id;


--
-- Name: player_game_idle id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player_game_idle ALTER COLUMN id SET DEFAULT nextval('public.player_game_idle_id_seq'::regclass);


--
-- Name: player_game_item id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player_game_item ALTER COLUMN id SET DEFAULT nextval('public.player_game_item_id_seq'::regclass);


--
-- Name: player_game_param id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player_game_param ALTER COLUMN id SET DEFAULT nextval('public.player_game_param_id_seq'::regclass);


--
-- Name: player_game_role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.player_game_role ALTER COLUMN id SET DEFAULT nextval('public.player_game_role_id_seq'::regclass);


--
-- Data for Name: db_check; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.db_check (id, name) FROM stdin;
1	1
\.


--
-- Data for Name: player_game_idle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player_game_idle (id, role_id, config_id, settle_time, create_time) FROM stdin;
\.


--
-- Name: player_game_idle_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_game_idle_id_seq', 1, false);


--
-- Data for Name: player_game_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player_game_item (id, role_id, config_id, count, create_time) FROM stdin;
\.


--
-- Name: player_game_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_game_item_id_seq', 1, false);


--
-- Data for Name: player_game_param; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player_game_param (id, role_id, param_type, value, create_time) FROM stdin;
\.


--
-- Name: player_game_param_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_game_param_id_seq', 1, false);


--
-- Data for Name: player_game_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player_game_role (id, name, is_new, locked, deleted, create_time) FROM stdin;
\.


--
-- Name: player_game_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.player_game_role_id_seq', 1, false);


--
-- Name: db_check id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.db_check
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

