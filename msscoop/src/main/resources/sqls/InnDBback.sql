--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2025-03-13 18:20:07

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16388)
-- Name: e_reserve; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.e_reserve (
    reserve_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    room_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    checkin date,
    checkout date,
    stay_number_of_people integer,
    meal boolean,
    amount integer,
    user_id character varying,
    cancel character varying
);


ALTER TABLE public.e_reserve OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16394)
-- Name: e_reserve_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.e_reserve_detail (
    stay_date date NOT NULL,
    reserve_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    payment integer
);


ALTER TABLE public.e_reserve_detail OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16398)
-- Name: r_customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_customer (
    customer_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    customer_name character varying(10),
    mail character varying(256),
    telnumber character varying(50),
    address character varying(255)
);


ALTER TABLE public.r_customer OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16404)
-- Name: r_meal_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_meal_type (
    meal_type_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    meal_type_name character varying(30)
);


ALTER TABLE public.r_meal_type OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16408)
-- Name: r_reservavle_room_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_reservavle_room_info (
    room_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    businessday date NOT NULL
);


ALTER TABLE public.r_reservavle_room_info OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16412)
-- Name: r_room; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_room (
    room_id character(12) DEFAULT '000000000001'::bpchar NOT NULL,
    room_name character varying(50),
    price integer,
    room_image character varying(250),
    smoking boolean,
    indoor_bathroom boolean
);


ALTER TABLE public.r_room OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16416)
-- Name: r_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.r_user (
    user_id character varying(20) NOT NULL,
    password character varying(128),
    role character varying,
    full_name character varying,
    email character varying(256)
);


ALTER TABLE public.r_user OWNER TO postgres;

--
-- TOC entry 4936 (class 0 OID 16388)
-- Dependencies: 217
-- Data for Name: e_reserve; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.e_reserve (reserve_id, room_id, checkin, checkout, stay_number_of_people, meal, amount, user_id, cancel) FROM stdin;
202501060001	202501050001	2025-01-06	2025-01-07	3	t	300000	\N	\N
202501060002	202501050002	2025-01-06	2025-01-07	1	t	65000	\N	\N
202501060003	202501050003	2025-01-06	2025-01-07	1	t	35000	\N	\N
202501070001	202501050001	2025-01-07	2025-01-08	2	t	200000	\N	\N
202501070002	202501050003	2025-01-07	2025-01-08	2	t	70000	\N	\N
202501070003	202501050002	2025-01-07	2025-01-08	2	t	130000	\N	\N
202501080001	202501050001	2025-01-08	2025-01-09	2	t	200000	\N	\N
202501080002	202501050002	2025-01-08	2025-01-09	2	t	130000	\N	\N
202501080003	202501050003	2025-01-08	2025-01-09	2	t	70000	\N	\N
202501090001	202501050001	2025-01-09	2025-01-10	2	t	200000	\N	\N
202501090002	202501050002	2025-01-09	2025-01-10	2	t	130000	12345	\N
202501090003	202501050003	2025-01-09	2025-01-10	2	t	70000	12345	0
202501100001	202501050001	2025-01-10	2025-01-11	2	t	200000	12345	0
202501100002	202501050002	2025-01-10	2025-01-11	2	t	130000	12345	0
\.


--
-- TOC entry 4937 (class 0 OID 16394)
-- Dependencies: 218
-- Data for Name: e_reserve_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.e_reserve_detail (stay_date, reserve_id, payment) FROM stdin;
\.


--
-- TOC entry 4938 (class 0 OID 16398)
-- Dependencies: 219
-- Data for Name: r_customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.r_customer (customer_id, customer_name, mail, telnumber, address) FROM stdin;
202501050001	中野太郎	nakano@gmail.com	09011112233	東京都中野区中野４丁目１１?１９
202501050002	福島太郎	fukushima@gmail.com	09011112244	福島県福島市杉妻町２?１６
202501050003	秋田花子	akita@gmail.com	09011112255	秋田県秋田市山王４丁目１ー１
202501050004	鳥取花子	tottori@gmail.com	09011112266	鳥取県鳥取市東町１丁目２２０
202501050005	岡山太郎	okayama@gmail.com	09011112277	岡山県岡山市北区内山下２丁目４ー６
202501050006	福岡太郎	fukuoka@gmail.com	09011112288	福岡県福岡市博多区東公園７７
\.


--
-- TOC entry 4939 (class 0 OID 16404)
-- Dependencies: 220
-- Data for Name: r_meal_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.r_meal_type (meal_type_id, meal_type_name) FROM stdin;
202501050001	朝夕
202501050002	朝のみ
202501050003	夕のみ
202501050004	朝昼夕
202501050005	なし
\.


--
-- TOC entry 4940 (class 0 OID 16408)
-- Dependencies: 221
-- Data for Name: r_reservavle_room_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.r_reservavle_room_info (room_id, businessday) FROM stdin;
202501050001	2025-01-06
202501050002	2025-01-06
202501050003	2025-01-06
202501050001	2025-01-07
202501050002	2025-01-07
202501050003	2025-01-07
202501050001	2025-01-08
202501050002	2025-01-08
202501050003	2025-01-08
202501050001	2025-01-09
202501050002	2025-01-09
202501050003	2025-01-09
202501050001	2025-01-10
202501050002	2025-01-10
202501050003	2025-01-10
\.


--
-- TOC entry 4941 (class 0 OID 16412)
-- Dependencies: 222
-- Data for Name: r_room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.r_room (room_id, room_name, price, room_image, smoking, indoor_bathroom) FROM stdin;
202501050001	雁の間	100000	/images/202501050001.png	f	t
202501050002	鶴の間	65000	/images/202501050002.png	f	t
202501050003	雉の間	35000	/images/202501050003.png	f	t
\.


--
-- TOC entry 4942 (class 0 OID 16416)
-- Dependencies: 223
-- Data for Name: r_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.r_user (user_id, password, role, full_name, email) FROM stdin;
12345	$2a$10$oJYpsCmFmDit/zEM2f7R0uEL7DMccpWpEj6eVP5NsWaeqEWoq9RFe	ROLE_ADMIN	鳥取一郎	mishida@support-s-frontier.com
6789	6789	ROLE_ADMIN	鳥取二郎	mishida@support-s-frontier.com
\.


--
-- TOC entry 4776 (class 2606 OID 16422)
-- Name: e_reserve_detail e_reserve_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.e_reserve_detail
    ADD CONSTRAINT e_reserve_detail_pkey PRIMARY KEY (stay_date, reserve_id);


--
-- TOC entry 4773 (class 2606 OID 16424)
-- Name: e_reserve e_reserve_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.e_reserve
    ADD CONSTRAINT e_reserve_pkey PRIMARY KEY (reserve_id);


--
-- TOC entry 4778 (class 2606 OID 16426)
-- Name: r_customer r_customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_customer
    ADD CONSTRAINT r_customer_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 4780 (class 2606 OID 16428)
-- Name: r_meal_type r_meal_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_meal_type
    ADD CONSTRAINT r_meal_type_pkey PRIMARY KEY (meal_type_id);


--
-- TOC entry 4782 (class 2606 OID 16430)
-- Name: r_reservavle_room_info r_reservable_room_ino_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_reservavle_room_info
    ADD CONSTRAINT r_reservable_room_ino_pkey PRIMARY KEY (room_id, businessday);


--
-- TOC entry 4784 (class 2606 OID 16432)
-- Name: r_room r_room_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_room
    ADD CONSTRAINT r_room_type_pkey PRIMARY KEY (room_id);


--
-- TOC entry 4786 (class 2606 OID 16434)
-- Name: r_user r_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_user
    ADD CONSTRAINT r_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4774 (class 1259 OID 16462)
-- Name: fki_e_reserve_user_id_fkey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_e_reserve_user_id_fkey ON public.e_reserve USING btree (user_id);


--
-- TOC entry 4789 (class 2606 OID 16440)
-- Name: e_reserve_detail e_reserve_detail_reserve_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.e_reserve_detail
    ADD CONSTRAINT e_reserve_detail_reserve_id_fkey FOREIGN KEY (reserve_id) REFERENCES public.e_reserve(reserve_id);


--
-- TOC entry 4787 (class 2606 OID 16445)
-- Name: e_reserve e_reserve_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.e_reserve
    ADD CONSTRAINT e_reserve_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.r_room(room_id);


--
-- TOC entry 4788 (class 2606 OID 16457)
-- Name: e_reserve e_reserve_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.e_reserve
    ADD CONSTRAINT e_reserve_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.r_user(user_id) NOT VALID;


--
-- TOC entry 4790 (class 2606 OID 16450)
-- Name: r_reservavle_room_info r_reservable_room_ino_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.r_reservavle_room_info
    ADD CONSTRAINT r_reservable_room_ino_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.r_room(room_id);


-- Completed on 2025-03-13 18:20:07

--
-- PostgreSQL database dump complete
--

