--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-05-23 22:15:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2234 (class 0 OID 16401)
-- Dependencies: 185
-- Data for Name: dostawcy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO dostawcy VALUES (1, '758-786-89-56', 'PolSport', '867546735', 'Pocztowa', '3', 5, '67-560', 'Olecko');
INSERT INTO dostawcy VALUES (3, '567-534-45-67', 'Intersport', '657345647', 'Testowa', '8', 7, '67-890', 'Gdańsk');
INSERT INTO dostawcy VALUES (4, '765-456-34-89', 'SportHurt', '564765876', 'Wiosenna', '0', 8, '34-230', 'Gdynia');
INSERT INTO dostawcy VALUES (6, '654-567-90-45', 'Gut', '567234567', 'Zimowa', '8', 7, '56-230', 'Olsztyn');
INSERT INTO dostawcy VALUES (7, '275-450-67-34', 'OstródzkiSport', '675867984', 'Towarowa', '8', 7, '24-230', 'Ostróda');
INSERT INTO dostawcy VALUES (5, '765-324-56-56', 'HurtSport', '345345344', 'Brzeźnieńska', '56', 23, '56-450', 'Gdynia');
INSERT INTO dostawcy VALUES (8, '765-987-12-23', 'HurtowniaSportowa', '567845978', 'Jesienna', '8', 7, '54-230', 'Grudziądz');
INSERT INTO dostawcy VALUES (10, '756-324-00-34', 'ChinaSport', '546736540', 'Pomorska', '6', 5, '34-450', 'Toruń');
INSERT INTO dostawcy VALUES (11, '654-236-78-56', 'SportPol', '645867980', 'Toruńska', '7', 6, '45-230', 'Bydgoszcz');
INSERT INTO dostawcy VALUES (12, '657-456-23-23', 'Nike', '456783456', 'SĹ‚oneczna', '5', 5, '56-340', 'BiaĹ‚ystok');


--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 186
-- Name: dostawcy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('dostawcy_id_seq', 12, true);


--
-- TOC entry 2236 (class 0 OID 16406)
-- Dependencies: 187
-- Data for Name: klienci; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO klienci VALUES (7, '111-334-23-76', 'Kaszub', '111111111', 'Maja', '10', 10, '90-000', 'Gdansk', 10);
INSERT INTO klienci VALUES (10, '578-456-23-67', 'Przykładowa', '524242344', 'Broniewskiego', '7', 0, '80-524', 'Toruń', 0);
INSERT INTO klienci VALUES (8, '345-456-23-56', 'Soltys', '111222333', 'Rzeźników', '456', 5, '56-460', 'Gdynia', 0);
INSERT INTO klienci VALUES (11, '567-345-56-78', 'Dobry klient', '768546765', 'Testowa', '3', 0, '76-560', 'Tczew', 0);
INSERT INTO klienci VALUES (12, '234-345-23-34', 'Paweł Zawadzki', '725345765', 'Czerwcowa', '8', 7, '23-450', 'Zakopane', 6);
INSERT INTO klienci VALUES (13, '654-234-45-67', 'Przemysław Sołtys', '234567890', 'Przemysłowa', '9', 8, '12-340', 'Nowy Targ', 0);
INSERT INTO klienci VALUES (14, '234-456-23-56', 'SklepSportowy', '758456234', 'Przemysła', '8', 0, '96-503', 'Gorzów', 0);
INSERT INTO klienci VALUES (15, '738-450-22-45', 'PolPex', '876456734', 'Jesienna', '8', 8, '45-230', 'Ostróda', 0);
INSERT INTO klienci VALUES (16, '239-456-23-34', 'Piotr Pawłowski', '645738760', 'Pana Tadeusza', '9', 8, '78-230', 'Iława', 0);
INSERT INTO klienci VALUES (17, '543-567-45-90', 'PaweĹ‚ Piotrowski', '234235123', 'Soplicy', '43', 3, '67-230', 'Dobrocin', 10);


--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 188
-- Name: klienci_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('klienci_id_seq', 17, true);


--
-- TOC entry 2245 (class 0 OID 16432)
-- Dependencies: 196
-- Data for Name: towary; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO towary VALUES (1, 534543, 124523, 'sztuki', 'Piłki', 'Adidas', 'Glider', 'Bez pompki', 23);
INSERT INTO towary VALUES (3, 756843, 7564765, 'sztuki', 'Kaczka', 'Smyk', 'Kaczucha', 'gumowa', 8);
INSERT INTO towary VALUES (4, 765823, 124873, 'sztuki', 'PiĹ‚ki', 'Adidas', 'Bezel', 'BiaĹ‚a', 23);
INSERT INTO towary VALUES (5, 435345, 42342, 'sztuki', 'Koszulki', 'Nike', 'Barcelona', 'Domowa', 23);
INSERT INTO towary VALUES (6, 343235, 23423, 'sztuki', 'Koszulki', 'Reebok', 'Newcastle', 'Wyjazdowa', 23);
INSERT INTO towary VALUES (7, 23423, 4234, 'sztuki', 'Koszulki', 'Adidas', 'Celtic', 'Zielona', 18);
INSERT INTO towary VALUES (9, 5675, 23423, 'opakowania', 'Tenis', 'Rocket', 'Siatka', 'Tenis stoĹ‚owy', 18);
INSERT INTO towary VALUES (10, 56756, 23423, 'sztuki', 'Tenis', 'Net', 'Rakieta', 'Tenis ziemny', 18);
INSERT INTO towary VALUES (11, 345334, 34534, 'sztuki', 'Gry', 'Dominos', 'Domino', 'Od 3 lat', 8);
INSERT INTO towary VALUES (12, 345534, 64564, 'pary', 'Spodnie', 'Reebok', 'Athletic', 'Dresowe', 23);


--
-- TOC entry 2252 (class 0 OID 16456)
-- Dependencies: 203
-- Data for Name: zakupy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO zakupy VALUES (1, '1995-04-17', 1, 'FE-FK-GH');
INSERT INTO zakupy VALUES (3, '2013-02-12', 1, '56-45-ASD');
INSERT INTO zakupy VALUES (4, '2014-12-31', 3, '54-234-FG');
INSERT INTO zakupy VALUES (5, '2015-10-30', 5, 'HGF-FD-56');
INSERT INTO zakupy VALUES (6, '2015-12-31', 4, 'HGF-DF-HJ');
INSERT INTO zakupy VALUES (7, '2016-07-16', 7, 'GFH-56-GH');
INSERT INTO zakupy VALUES (8, '2014-10-21', 8, 'BVC-45-234');
INSERT INTO zakupy VALUES (10, '2010-05-10', 10, 'TRE-QW-89');
INSERT INTO zakupy VALUES (11, '2016-01-01', 11, 'BGF-SDT-09');
INSERT INTO zakupy VALUES (14, '2015-07-07', 12, 'BGH-DF-34');


--
-- TOC entry 2247 (class 0 OID 16440)
-- Dependencies: 198
-- Data for Name: towarymagazyn; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO towarymagazyn VALUES (1, 1, 46.99, 'MagazynB', 1, 3);
INSERT INTO towarymagazyn VALUES (2, 5, 30.00, 'MagazynA', 3, 30);
INSERT INTO towarymagazyn VALUES (3, 5, 35.00, 'MagazynA', 4, 100);
INSERT INTO towarymagazyn VALUES (4, 6, 56.00, 'MagazynD', 7, 20);
INSERT INTO towarymagazyn VALUES (5, 3, 67.00, 'MagazynC', 6, 45);
INSERT INTO towarymagazyn VALUES (6, 7, 10.00, 'MagazynE', 8, 12);
INSERT INTO towarymagazyn VALUES (10, 10, 100.00, 'MagazynD', 10, 165);
INSERT INTO towarymagazyn VALUES (11, 9, 45.99, 'MagazynA', 11, 32);
INSERT INTO towarymagazyn VALUES (12, 11, 89.99, 'MagazynC', 6, 12);
INSERT INTO towarymagazyn VALUES (13, 6, 50.99, 'MagazynC', 7, 78);


--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 190
-- Name: pozycjesprzedazy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pozycjesprzedazy_id_seq', 22, true);


--
-- TOC entry 2240 (class 0 OID 16416)
-- Dependencies: 191
-- Data for Name: pozycjezakupow; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pozycjezakupow VALUES (1, 1, 59.99, 5);
INSERT INTO pozycjezakupow VALUES (6, 1, 45.99, 45);
INSERT INTO pozycjezakupow VALUES (7, 3, 12.90, 2);
INSERT INTO pozycjezakupow VALUES (8, 5, 900.89, 120);
INSERT INTO pozycjezakupow VALUES (9, 4, 56.99, 10);
INSERT INTO pozycjezakupow VALUES (10, 7, 10.99, 200);
INSERT INTO pozycjezakupow VALUES (11, 6, 14.99, 4);
INSERT INTO pozycjezakupow VALUES (12, 8, 13.99, 100);
INSERT INTO pozycjezakupow VALUES (14, 10, 100.00, 5);
INSERT INTO pozycjezakupow VALUES (15, 11, 19.99, 60);
INSERT INTO pozycjezakupow VALUES (16, 11, 45.99, 123);


--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 192
-- Name: pozycjezakupow_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pozycjezakupow_id_seq', 16, true);


--
-- TOC entry 2249 (class 0 OID 16448)
-- Dependencies: 200
-- Data for Name: uprawnienia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO uprawnienia VALUES (1, 'NONE', 'NONE', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (2, 'NONE', 'NONE', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (3, 'NONE', 'NONE', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (4, 'NONE', 'NONE', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (5, 'NONE', 'NONE', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (6, 'NONE', 'NONE', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (7, 'NONE', 'NONE', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (8, 'NONE', 'NONE', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (9, 'NONE', 'NONE', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (10, 'NONE', 'ACCESS', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (11, 'NONE', 'ACCESS', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (12, 'NONE', 'ACCESS', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (13, 'NONE', 'ACCESS', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (14, 'NONE', 'ACCESS', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (15, 'NONE', 'ACCESS', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (16, 'NONE', 'ACCESS', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (17, 'NONE', 'ACCESS', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (18, 'NONE', 'ACCESS', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (19, 'NONE', 'GRANT', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (20, 'NONE', 'GRANT', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (21, 'NONE', 'GRANT', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (22, 'NONE', 'GRANT', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (23, 'NONE', 'GRANT', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (24, 'NONE', 'GRANT', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (25, 'NONE', 'GRANT', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (26, 'NONE', 'GRANT', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (27, 'NONE', 'GRANT', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (28, 'ACCESS', 'NONE', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (29, 'ACCESS', 'NONE', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (30, 'ACCESS', 'NONE', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (31, 'ACCESS', 'NONE', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (32, 'ACCESS', 'NONE', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (33, 'ACCESS', 'NONE', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (34, 'ACCESS', 'NONE', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (35, 'ACCESS', 'NONE', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (36, 'ACCESS', 'NONE', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (37, 'ACCESS', 'ACCESS', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (38, 'ACCESS', 'ACCESS', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (39, 'ACCESS', 'ACCESS', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (40, 'ACCESS', 'ACCESS', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (41, 'ACCESS', 'ACCESS', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (42, 'ACCESS', 'ACCESS', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (43, 'ACCESS', 'ACCESS', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (44, 'ACCESS', 'ACCESS', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (45, 'ACCESS', 'ACCESS', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (46, 'ACCESS', 'GRANT', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (47, 'ACCESS', 'GRANT', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (48, 'ACCESS', 'GRANT', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (49, 'ACCESS', 'GRANT', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (50, 'ACCESS', 'GRANT', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (51, 'ACCESS', 'GRANT', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (52, 'ACCESS', 'GRANT', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (53, 'ACCESS', 'GRANT', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (54, 'ACCESS', 'GRANT', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (55, 'GRANT', 'NONE', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (56, 'GRANT', 'NONE', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (57, 'GRANT', 'NONE', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (58, 'GRANT', 'NONE', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (59, 'GRANT', 'NONE', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (60, 'GRANT', 'NONE', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (61, 'GRANT', 'NONE', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (62, 'GRANT', 'NONE', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (63, 'GRANT', 'NONE', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (64, 'GRANT', 'ACCESS', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (65, 'GRANT', 'ACCESS', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (66, 'GRANT', 'ACCESS', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (67, 'GRANT', 'ACCESS', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (68, 'GRANT', 'ACCESS', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (69, 'GRANT', 'ACCESS', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (70, 'GRANT', 'ACCESS', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (71, 'GRANT', 'ACCESS', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (72, 'GRANT', 'ACCESS', 'GRANT', 'GRANT');
INSERT INTO uprawnienia VALUES (73, 'GRANT', 'GRANT', 'NONE', 'NONE');
INSERT INTO uprawnienia VALUES (74, 'GRANT', 'GRANT', 'NONE', 'ACCESS');
INSERT INTO uprawnienia VALUES (75, 'GRANT', 'GRANT', 'NONE', 'GRANT');
INSERT INTO uprawnienia VALUES (76, 'GRANT', 'GRANT', 'ACCESS', 'NONE');
INSERT INTO uprawnienia VALUES (77, 'GRANT', 'GRANT', 'ACCESS', 'ACCESS');
INSERT INTO uprawnienia VALUES (78, 'GRANT', 'GRANT', 'ACCESS', 'GRANT');
INSERT INTO uprawnienia VALUES (79, 'GRANT', 'GRANT', 'GRANT', 'NONE');
INSERT INTO uprawnienia VALUES (80, 'GRANT', 'GRANT', 'GRANT', 'ACCESS');
INSERT INTO uprawnienia VALUES (81, 'GRANT', 'GRANT', 'GRANT', 'GRANT');


--
-- TOC entry 2250 (class 0 OID 16451)
-- Dependencies: 201
-- Data for Name: uzytkownicy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO uzytkownicy VALUES (10, 'test', 'test', 'test@test.com');
INSERT INTO uzytkownicy VALUES (21, 'kaszub', '$2a$10$8422xJpLk0C17C/G07f96eUWPZE9rquUKWYlXQUpkg0ysXdVWxIBq', 'test2_changed@test.com');
INSERT INTO uzytkownicy VALUES (27, 'alfatest', '$2a$10$RWzliqF0q1.PPa//qs03PukE0GFMCgCNsllmgnN2pDMKxcenBHwIW', 'alfa@alfa.com');
INSERT INTO uzytkownicy VALUES (22, 'piotr3', '$2a$10$u3JfZ2Tbpj77ESauulpUDeDWk65nFXhla8//AwslGQK3vifOWCqjW', 'piotrek@test.com');


--
-- TOC entry 2242 (class 0 OID 16424)
-- Dependencies: 193
-- Data for Name: przekazywanieuprawnien; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO przekazywanieuprawnien VALUES (10, 21, 1, 1, 1, 1, 2, 3, 2,  1, true);
INSERT INTO przekazywanieuprawnien VALUES (21, 22, 81, 81, 81, 11, 11, 73, 1, 1,false);
INSERT INTO przekazywanieuprawnien VALUES (10, 22, 10, 1, 1, 1, 1, 1, 1, 1,false);


--
-- TOC entry 2243 (class 0 OID 16427)
-- Dependencies: 194
-- Data for Name: sprzedaze; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sprzedaze VALUES (1, 'FD-FG-GF4', 7, '2016-05-20');
INSERT INTO sprzedaze VALUES (4, 'GDA-FG-23', 13, '2016-12-01');
INSERT INTO sprzedaze VALUES (2, 'FGH-DF-23', 8, '2016-04-17');
INSERT INTO sprzedaze VALUES (3, 'BV-VVC-NM', 7, '2016-02-17');
INSERT INTO sprzedaze VALUES (5, 'DSF-GF-43', 14, '2017-04-11');
INSERT INTO sprzedaze VALUES (6, 'GDF-FG-GF', 15, '2017-05-02');
INSERT INTO sprzedaze VALUES (7, 'KLJ-KS-KF', 16, '2016-12-31');
INSERT INTO sprzedaze VALUES (8, 'BVH-45-GF', 17, '2016-11-30');
INSERT INTO sprzedaze VALUES (9, 'GHJ-XC-45', 7, '2016-12-13');
INSERT INTO sprzedaze VALUES (10, 'BHL-FD-78', 10, '2017-10-11');



--
-- TOC entry 2238 (class 0 OID 16411)
-- Dependencies: 189
-- Data for Name: pozycjesprzedazy; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pozycjesprzedazy VALUES (1, 1, 1, 8, 5);
INSERT INTO pozycjesprzedazy VALUES (7, 2, 4, 12, 4);
INSERT INTO pozycjesprzedazy VALUES (8, 3, 5, 45, 5);
INSERT INTO pozycjesprzedazy VALUES (9, 3, 6, 10, 0);
INSERT INTO pozycjesprzedazy VALUES (13, 5, 10, 1, 10);
INSERT INTO pozycjesprzedazy VALUES (14, 7, 10, 15, 0);
INSERT INTO pozycjesprzedazy VALUES (15, 6, 11, 100, 30);
INSERT INTO pozycjesprzedazy VALUES (16, 6, 12, 4, 2);
INSERT INTO pozycjesprzedazy VALUES (18, 5, 13, 10, 5);
INSERT INTO pozycjesprzedazy VALUES (19, 10, 4, 19, 1);
INSERT INTO pozycjesprzedazy VALUES (22, 8, 3, 10, 0);


--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 195
-- Name: sprzedaze_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sprzedaze_id_seq', 10, true);


--
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 197
-- Name: towary_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('towary_id_seq', 12, true);


--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 199
-- Name: towarymagazyn_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('towarymagazyn_id_seq', 13, true);


--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 202
-- Name: uzytkownicy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('uzytkownicy_id_seq', 27, true);


--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 204
-- Name: zakupy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('zakupy_id_seq', 14, true);


-- Completed on 2017-05-23 22:15:06

--
-- PostgreSQL database dump complete
--
