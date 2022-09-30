--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

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

--
-- Name: folderstatus; Type: TYPE; Schema: public; Owner: mehdilagdimi
--

CREATE TYPE public.folderstatus AS ENUM (
    'En attente',
    'Refus‚',
    'Valid‚'
);


ALTER TYPE public.folderstatus OWNER TO mehdilagdimi;

--
-- Name: typedocument; Type: TYPE; Schema: public; Owner: mehdilagdimi
--

CREATE TYPE public.typedocument AS ENUM (
    'M‚dicament',
    'Analyse',
    'Imagerie'
);


ALTER TYPE public.typedocument OWNER TO mehdilagdimi;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: agent; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.agent (
    id bigint NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    role boolean NOT NULL,
    email character varying(250) NOT NULL,
    password character varying(100) NOT NULL
);


ALTER TABLE public.agent OWNER TO mehdilagdimi;

--
-- Name: agent_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.agent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.agent_id_seq OWNER TO mehdilagdimi;

--
-- Name: agent_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.agent_id_seq OWNED BY public.agent.id;


--
-- Name: analysemedicale; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.analysemedicale (
    id bigint NOT NULL,
    nom character varying(250) NOT NULL,
    is_remboursable boolean NOT NULL,
    taux real NOT NULL
);


ALTER TABLE public.analysemedicale OWNER TO mehdilagdimi;

--
-- Name: analysemedicale_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.analysemedicale_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.analysemedicale_id_seq OWNER TO mehdilagdimi;

--
-- Name: analysemedicale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.analysemedicale_id_seq OWNED BY public.analysemedicale.id;


--
-- Name: conjoint; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.conjoint (
    id bigint NOT NULL,
    id_matricule_patient bigint NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    age integer NOT NULL
);


ALTER TABLE public.conjoint OWNER TO mehdilagdimi;

--
-- Name: conjoint_id_matricule_patient_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.conjoint_id_matricule_patient_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conjoint_id_matricule_patient_seq OWNER TO mehdilagdimi;

--
-- Name: conjoint_id_matricule_patient_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.conjoint_id_matricule_patient_seq OWNED BY public.conjoint.id_matricule_patient;


--
-- Name: conjoint_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.conjoint_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conjoint_id_seq OWNER TO mehdilagdimi;

--
-- Name: conjoint_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.conjoint_id_seq OWNED BY public.conjoint.id;


--
-- Name: consultation; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.consultation (
    id bigint NOT NULL,
    code_dossier bigint NOT NULL,
    id_specialite integer NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    is_conjoint boolean NOT NULL,
    nbr_documents integer NOT NULL,
    montant_paye real NOT NULL
);


ALTER TABLE public.consultation OWNER TO mehdilagdimi;

--
-- Name: consultation_code_dossier_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.consultation_code_dossier_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consultation_code_dossier_seq OWNER TO mehdilagdimi;

--
-- Name: consultation_code_dossier_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.consultation_code_dossier_seq OWNED BY public.consultation.code_dossier;


--
-- Name: consultation_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.consultation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consultation_id_seq OWNER TO mehdilagdimi;

--
-- Name: consultation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.consultation_id_seq OWNED BY public.consultation.id;


--
-- Name: consultation_id_specialite_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.consultation_id_specialite_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consultation_id_specialite_seq OWNER TO mehdilagdimi;

--
-- Name: consultation_id_specialite_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.consultation_id_specialite_seq OWNED BY public.consultation.id_specialite;


--
-- Name: document; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.document (
    id bigint NOT NULL,
    id_consultation bigint NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    type public.typedocument NOT NULL,
    montant_paye real NOT NULL
);


ALTER TABLE public.document OWNER TO mehdilagdimi;

--
-- Name: document_id_consultation_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.document_id_consultation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.document_id_consultation_seq OWNER TO mehdilagdimi;

--
-- Name: document_id_consultation_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.document_id_consultation_seq OWNED BY public.document.id_consultation;


--
-- Name: document_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.document_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.document_id_seq OWNER TO mehdilagdimi;

--
-- Name: document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.document_id_seq OWNED BY public.document.id;


--
-- Name: dossier; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.dossier (
    code bigint NOT NULL,
    id_matricule_patient bigint NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    nbr_consultation integer NOT NULL,
    etat public.folderstatus DEFAULT 'En attente'::public.folderstatus,
    path character varying(255)
);


ALTER TABLE public.dossier OWNER TO mehdilagdimi;

--
-- Name: dossier_code_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.dossier_code_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dossier_code_seq OWNER TO mehdilagdimi;

--
-- Name: dossier_code_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.dossier_code_seq OWNED BY public.dossier.code;


--
-- Name: dossier_id_matricule_patient_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.dossier_id_matricule_patient_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dossier_id_matricule_patient_seq OWNER TO mehdilagdimi;

--
-- Name: dossier_id_matricule_patient_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.dossier_id_matricule_patient_seq OWNED BY public.dossier.id_matricule_patient;


--
-- Name: imagerie; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.imagerie (
    id bigint NOT NULL,
    nom character varying(250) NOT NULL,
    is_remboursable boolean NOT NULL,
    taux real NOT NULL
);


ALTER TABLE public.imagerie OWNER TO mehdilagdimi;

--
-- Name: imagerie_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.imagerie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.imagerie_id_seq OWNER TO mehdilagdimi;

--
-- Name: imagerie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.imagerie_id_seq OWNED BY public.imagerie.id;


--
-- Name: medicament; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.medicament (
    id bigint NOT NULL,
    nom character varying(250) NOT NULL,
    is_remboursable boolean NOT NULL,
    taux real NOT NULL
);


ALTER TABLE public.medicament OWNER TO mehdilagdimi;

--
-- Name: medicament_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.medicament_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medicament_id_seq OWNER TO mehdilagdimi;

--
-- Name: medicament_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.medicament_id_seq OWNED BY public.medicament.id;


--
-- Name: patient; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.patient (
    id_matricule bigint NOT NULL,
    nom character varying(100) NOT NULL,
    prenom character varying(100) NOT NULL,
    email character varying(250) NOT NULL,
    password character varying(100) NOT NULL
);


ALTER TABLE public.patient OWNER TO mehdilagdimi;

--
-- Name: patient_id_matricule_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.patient_id_matricule_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.patient_id_matricule_seq OWNER TO mehdilagdimi;

--
-- Name: patient_id_matricule_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.patient_id_matricule_seq OWNED BY public.patient.id_matricule;


--
-- Name: specialite; Type: TABLE; Schema: public; Owner: mehdilagdimi
--

CREATE TABLE public.specialite (
    id integer NOT NULL,
    nom character varying(100) NOT NULL
);


ALTER TABLE public.specialite OWNER TO mehdilagdimi;

--
-- Name: specialite_id_seq; Type: SEQUENCE; Schema: public; Owner: mehdilagdimi
--

CREATE SEQUENCE public.specialite_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.specialite_id_seq OWNER TO mehdilagdimi;

--
-- Name: specialite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mehdilagdimi
--

ALTER SEQUENCE public.specialite_id_seq OWNED BY public.specialite.id;


--
-- Name: agent id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.agent ALTER COLUMN id SET DEFAULT nextval('public.agent_id_seq'::regclass);


--
-- Name: analysemedicale id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.analysemedicale ALTER COLUMN id SET DEFAULT nextval('public.analysemedicale_id_seq'::regclass);


--
-- Name: conjoint id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.conjoint ALTER COLUMN id SET DEFAULT nextval('public.conjoint_id_seq'::regclass);


--
-- Name: conjoint id_matricule_patient; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.conjoint ALTER COLUMN id_matricule_patient SET DEFAULT nextval('public.conjoint_id_matricule_patient_seq'::regclass);


--
-- Name: consultation id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.consultation ALTER COLUMN id SET DEFAULT nextval('public.consultation_id_seq'::regclass);


--
-- Name: consultation code_dossier; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.consultation ALTER COLUMN code_dossier SET DEFAULT nextval('public.consultation_code_dossier_seq'::regclass);


--
-- Name: consultation id_specialite; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.consultation ALTER COLUMN id_specialite SET DEFAULT nextval('public.consultation_id_specialite_seq'::regclass);


--
-- Name: document id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.document ALTER COLUMN id SET DEFAULT nextval('public.document_id_seq'::regclass);


--
-- Name: document id_consultation; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.document ALTER COLUMN id_consultation SET DEFAULT nextval('public.document_id_consultation_seq'::regclass);


--
-- Name: dossier code; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.dossier ALTER COLUMN code SET DEFAULT nextval('public.dossier_code_seq'::regclass);


--
-- Name: dossier id_matricule_patient; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.dossier ALTER COLUMN id_matricule_patient SET DEFAULT nextval('public.dossier_id_matricule_patient_seq'::regclass);


--
-- Name: imagerie id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.imagerie ALTER COLUMN id SET DEFAULT nextval('public.imagerie_id_seq'::regclass);


--
-- Name: medicament id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.medicament ALTER COLUMN id SET DEFAULT nextval('public.medicament_id_seq'::regclass);


--
-- Name: patient id_matricule; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.patient ALTER COLUMN id_matricule SET DEFAULT nextval('public.patient_id_matricule_seq'::regclass);


--
-- Name: specialite id; Type: DEFAULT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.specialite ALTER COLUMN id SET DEFAULT nextval('public.specialite_id_seq'::regclass);


--
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.agent (id, nom, prenom, role, email, password) FROM stdin;
\.


--
-- Data for Name: analysemedicale; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.analysemedicale (id, nom, is_remboursable, taux) FROM stdin;
\.


--
-- Data for Name: conjoint; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.conjoint (id, id_matricule_patient, nom, prenom, age) FROM stdin;
\.


--
-- Data for Name: consultation; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.consultation (id, code_dossier, id_specialite, date, is_conjoint, nbr_documents, montant_paye) FROM stdin;
\.


--
-- Data for Name: document; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.document (id, id_consultation, date, type, montant_paye) FROM stdin;
\.


--
-- Data for Name: dossier; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.dossier (code, id_matricule_patient, date, nbr_consultation, etat, path) FROM stdin;
\.


--
-- Data for Name: imagerie; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.imagerie (id, nom, is_remboursable, taux) FROM stdin;
\.


--
-- Data for Name: medicament; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.medicament (id, nom, is_remboursable, taux) FROM stdin;
\.


--
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.patient (id_matricule, nom, prenom, email, password) FROM stdin;
\.


--
-- Data for Name: specialite; Type: TABLE DATA; Schema: public; Owner: mehdilagdimi
--

COPY public.specialite (id, nom) FROM stdin;
\.


--
-- Name: agent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.agent_id_seq', 1, false);


--
-- Name: analysemedicale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.analysemedicale_id_seq', 1, false);


--
-- Name: conjoint_id_matricule_patient_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.conjoint_id_matricule_patient_seq', 1, false);


--
-- Name: conjoint_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.conjoint_id_seq', 1, false);


--
-- Name: consultation_code_dossier_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.consultation_code_dossier_seq', 1, false);


--
-- Name: consultation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.consultation_id_seq', 1, false);


--
-- Name: consultation_id_specialite_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.consultation_id_specialite_seq', 1, false);


--
-- Name: document_id_consultation_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.document_id_consultation_seq', 1, false);


--
-- Name: document_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.document_id_seq', 1, false);


--
-- Name: dossier_code_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.dossier_code_seq', 1, false);


--
-- Name: dossier_id_matricule_patient_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.dossier_id_matricule_patient_seq', 1, false);


--
-- Name: imagerie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.imagerie_id_seq', 1, false);


--
-- Name: medicament_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.medicament_id_seq', 1, false);


--
-- Name: patient_id_matricule_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.patient_id_matricule_seq', 1, false);


--
-- Name: specialite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mehdilagdimi
--

SELECT pg_catalog.setval('public.specialite_id_seq', 1, false);


--
-- Name: agent agent_email_key; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT agent_email_key UNIQUE (email);


--
-- Name: agent agent_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.agent
    ADD CONSTRAINT agent_pkey PRIMARY KEY (id);


--
-- Name: analysemedicale analysemedicale_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.analysemedicale
    ADD CONSTRAINT analysemedicale_pkey PRIMARY KEY (id);


--
-- Name: conjoint conjoint_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.conjoint
    ADD CONSTRAINT conjoint_pkey PRIMARY KEY (id);


--
-- Name: consultation consultation_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.consultation
    ADD CONSTRAINT consultation_pkey PRIMARY KEY (id);


--
-- Name: document document_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (id);


--
-- Name: dossier dossier_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.dossier
    ADD CONSTRAINT dossier_pkey PRIMARY KEY (code);


--
-- Name: imagerie imagerie_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.imagerie
    ADD CONSTRAINT imagerie_pkey PRIMARY KEY (id);


--
-- Name: medicament medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (id);


--
-- Name: patient patient_email_key; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_email_key UNIQUE (email);


--
-- Name: patient patient_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id_matricule);


--
-- Name: specialite specialite_pkey; Type: CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.specialite
    ADD CONSTRAINT specialite_pkey PRIMARY KEY (id);


--
-- Name: conjoint conjoint_id_matricule_patient_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.conjoint
    ADD CONSTRAINT conjoint_id_matricule_patient_fkey FOREIGN KEY (id_matricule_patient) REFERENCES public.patient(id_matricule) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: consultation consultation_code_dossier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.consultation
    ADD CONSTRAINT consultation_code_dossier_fkey FOREIGN KEY (code_dossier) REFERENCES public.dossier(code) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: consultation consultation_id_specialite_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.consultation
    ADD CONSTRAINT consultation_id_specialite_fkey FOREIGN KEY (id_specialite) REFERENCES public.specialite(id) ON UPDATE CASCADE;


--
-- Name: document document_id_consultation_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_id_consultation_fkey FOREIGN KEY (id_consultation) REFERENCES public.consultation(id) ON UPDATE CASCADE;


--
-- Name: dossier dossier_id_matricule_patient_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mehdilagdimi
--

ALTER TABLE ONLY public.dossier
    ADD CONSTRAINT dossier_id_matricule_patient_fkey FOREIGN KEY (id_matricule_patient) REFERENCES public.patient(id_matricule) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

