-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-02-2025 a las 12:51:21
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdtarea3`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `idpersona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `password`, `usuario`, `idpersona`) VALUES
(1, 'admin', 'admin', 1),
(2, '1234samuel', 'SAMUELVIVERO', 2),
(3, 'javier349023', 'JAVIERVIVERO', 3),
(4, 'sofia39abc', 'SOFIAVIVERO', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idplanta` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `nombre`, `idplanta`) VALUES
(1, 'GIRASOL_1', 1),
(2, 'GIRASOL_2', 1),
(3, 'GIRASOL_3', 1),
(4, 'GIRASOL_4', 1),
(5, 'GIRASOL_5', 1),
(6, 'LAVANDA _1', 2),
(7, 'LAVANDA _2', 2),
(8, 'LAVANDA _3', 2),
(9, 'CACTUS _1', 3),
(10, 'CACTUS _2', 3),
(11, 'MENTA_1', 4),
(12, 'MENTA_2', 4),
(13, 'MENTA_3', 4),
(14, 'TULIPAN_1', 5),
(15, 'TULIPAN_2', 5),
(16, 'TULIPAN_3', 5),
(17, 'JAZMIN_1', 6),
(18, 'JAZMIN_2', 6),
(19, 'JAZMIN_3', 6),
(20, 'JAZMIN_4', 6),
(21, 'JAZMIN_5', 6),
(22, 'JAZMIN_6', 6),
(23, 'GIRASOL_6', 1),
(24, 'GIRASOL_7', 1),
(25, 'GIRASOL_8', 1),
(26, 'LAVANDA _4', 2),
(27, 'LAVANDA _5', 2),
(28, 'LAVANDA _6', 2),
(29, 'MENTA_4', 4),
(30, 'TULIPAN_4', 5),
(31, 'JAZMIN_7', 6),
(32, 'GIRASOL_9', 1),
(33, 'LAVANDA _7', 2),
(34, 'LAVANDA _8', 2),
(35, 'GIRASOL_10', 1),
(36, 'CACTUS _3', 3),
(37, 'MENTA_5', 4),
(38, 'MENTA_6', 4),
(39, 'MENTA_7', 4),
(40, 'TULIPAN_5', 5),
(41, 'JAZMIN_8', 6),
(42, 'TULIPAN_6', 5),
(43, 'TULIPAN_7', 5),
(44, 'JAZMIN_9', 6),
(45, 'JAZMIN_10', 6),
(46, 'LIRIO_1', 7),
(47, 'LIRIO_2', 7),
(48, 'LIRIO_3', 7),
(49, 'LAVANDA _9', 2),
(50, 'GIRASOL_11', 1),
(51, 'GIRASOL_12', 1),
(52, 'MENTA_8', 4),
(53, 'TULIPAN_8', 5),
(54, 'TULIPAN_9', 5),
(55, 'LIRIO_4', 7),
(56, 'LIRIO_5', 7),
(57, 'LIRIO_6', 7),
(58, 'LIRIO_7', 7),
(59, 'LIRIO_8', 7),
(60, 'JAZMIN_11', 6),
(61, 'JAZMIN_12', 6),
(62, 'JAZMIN_13', 6),
(63, 'JAZMIN_14', 6),
(64, 'TULIPAN_10', 5),
(65, 'TULIPAN_11', 5),
(66, 'MENTA_9', 4),
(67, 'MENTA_10', 4),
(68, 'MENTA_11', 4),
(69, 'CACTUS _4', 3),
(70, 'CACTUS _5', 3),
(71, 'CACTUS _6', 3),
(72, 'LAVANDA _10', 2),
(73, 'LAVANDA _11', 2),
(74, 'LAVANDA _12', 2),
(75, 'TULIPAN_12', 5),
(76, 'JAZMIN_15', 6),
(93, 'GIRASOL_13', 1),
(94, 'GIRASOL_14', 1),
(95, 'GIRASOL_15', 1),
(96, 'GIRASOL_16', 1),
(97, 'GIRASOL_17', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` bigint(20) NOT NULL,
  `fechahora` datetime(6) DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `idejemplar` bigint(20) NOT NULL,
  `idpersona` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fechahora`, `mensaje`, `idejemplar`, `idpersona`) VALUES
(1, '2024-12-10 12:36:11.000000', 'Fecha: 10-12-2024 12:36:11  Persona: 1', 1, 1),
(2, '2024-12-10 12:36:12.000000', 'Fecha: 10-12-2024 12:36:12  Persona: 1', 2, 1),
(3, '2024-12-10 12:36:13.000000', 'Fecha: 10-12-2024 12:36:13  Persona: 1', 3, 1),
(4, '2024-12-10 12:36:14.000000', 'Fecha: 10-12-2024 12:36:14  Persona: 1', 4, 1),
(5, '2024-12-10 12:36:15.000000', 'Fecha: 10-12-2024 12:36:15  Persona: 1', 5, 1),
(6, '2024-12-10 12:36:32.000000', 'Fecha: 10-12-2024 12:36:32  Persona: 1', 6, 1),
(7, '2024-12-10 12:36:33.000000', 'Fecha: 10-12-2024 12:36:33  Persona: 1', 7, 1),
(8, '2024-12-10 12:36:34.000000', 'Fecha: 10-12-2024 12:36:34  Persona: 1', 8, 1),
(9, '2024-12-10 12:36:36.000000', 'Fecha: 10-12-2024 12:36:36  Persona: 1', 9, 1),
(10, '2024-12-10 12:36:38.000000', 'Fecha: 10-12-2024 12:36:38  Persona: 1', 10, 1),
(11, '2024-12-10 12:36:39.000000', 'Fecha: 10-12-2024 12:36:39  Persona: 1', 11, 1),
(12, '2024-12-10 12:36:41.000000', 'Fecha: 10-12-2024 12:36:41  Persona: 1', 12, 1),
(13, '2024-12-10 12:36:42.000000', 'Fecha: 10-12-2024 12:36:42  Persona: 1', 13, 1),
(14, '2024-12-10 12:36:43.000000', 'Fecha: 10-12-2024 12:36:43  Persona: 1', 14, 1),
(15, '2024-12-10 12:36:45.000000', 'Fecha: 10-12-2024 12:36:45  Persona: 1', 15, 1),
(16, '2024-12-10 12:36:46.000000', 'Fecha: 10-12-2024 12:36:46  Persona: 1', 16, 1),
(17, '2024-12-10 12:36:48.000000', 'Fecha: 10-12-2024 12:36:48  Persona: 1', 17, 1),
(18, '2024-12-10 12:36:49.000000', 'Fecha: 10-12-2024 12:36:49  Persona: 1', 18, 1),
(19, '2024-12-10 12:36:51.000000', 'Fecha: 10-12-2024 12:36:51  Persona: 1', 19, 1),
(20, '2024-12-10 12:36:52.000000', 'Fecha: 10-12-2024 12:36:52  Persona: 1', 20, 1),
(21, '2024-12-10 12:36:53.000000', 'Fecha: 10-12-2024 12:36:53  Persona: 1', 21, 1),
(22, '2024-12-10 12:36:54.000000', 'Fecha: 10-12-2024 12:36:54  Persona: 1', 22, 1),
(23, '2024-12-10 12:45:34.000000', 'Fecha: 10-12-2024 12:45:34  Persona: 1', 1, 1),
(24, '2024-12-10 12:45:35.000000', 'Fecha: 10-12-2024 12:45:35  Persona: 1', 2, 1),
(25, '2024-12-10 12:45:39.000000', 'Fecha: 10-12-2024 12:45:39  Persona: 1', 3, 1),
(26, '2024-12-10 12:45:42.000000', 'Fecha: 10-12-2024 12:45:42  Persona: 1', 5, 1),
(27, '2024-12-10 12:45:44.000000', 'Fecha: 10-12-2024 12:45:44  Persona: 1', 3, 1),
(28, '2024-12-10 12:46:08.000000', 'Fecha: 10-12-2024 12:46:08  Persona: 1', 1, 1),
(29, '2024-12-10 13:01:00.000000', 'Fecha: 10-12-2024 13:01:00  Persona: 2', 23, 2),
(30, '2024-12-10 13:01:01.000000', 'Fecha: 10-12-2024 13:01:01  Persona: 2', 24, 2),
(31, '2024-12-10 13:01:02.000000', 'Fecha: 10-12-2024 13:01:02  Persona: 2', 25, 2),
(32, '2024-12-10 13:01:08.000000', 'Fecha: 10-12-2024 13:01:08  Persona: 2', 26, 2),
(33, '2024-12-10 13:01:09.000000', 'Fecha: 10-12-2024 13:01:09  Persona: 2', 27, 2),
(34, '2024-12-10 13:01:09.000000', 'Fecha: 10-12-2024 13:01:09  Persona: 2', 28, 2),
(35, '2024-12-10 13:01:10.000000', 'Fecha: 10-12-2024 13:01:10  Persona: 2', 29, 2),
(36, '2024-12-10 13:01:12.000000', 'Fecha: 10-12-2024 13:01:12  Persona: 2', 30, 2),
(37, '2024-12-10 13:01:13.000000', 'Fecha: 10-12-2024 13:01:13  Persona: 2', 31, 2),
(38, '2024-12-10 13:01:17.000000', 'Fecha: 10-12-2024 13:01:17  Persona: 2', 1, 2),
(39, '2024-12-10 13:01:21.000000', 'Fecha: 10-12-2024 13:01:21  Persona: 2', 3, 2),
(40, '2024-12-10 13:01:25.000000', 'Fecha: 10-12-2024 13:01:25  Persona: 2', 5, 2),
(41, '2024-12-10 13:01:27.000000', 'Fecha: 10-12-2024 13:01:27  Persona: 2', 22, 2),
(42, '2024-12-10 13:01:29.000000', 'Fecha: 10-12-2024 13:01:29  Persona: 2', 21, 2),
(43, '2024-12-10 13:01:31.000000', 'Fecha: 10-12-2024 13:01:31  Persona: 2', 31, 2),
(44, '2024-12-10 13:01:33.000000', 'Fecha: 10-12-2024 13:01:33  Persona: 2', 26, 2),
(45, '2024-12-10 13:10:13.000000', 'Fecha: 10-12-2024 13:10:13  Persona: 4', 32, 4),
(46, '2024-12-10 13:10:14.000000', 'Fecha: 10-12-2024 13:10:14  Persona: 4', 33, 4),
(47, '2024-12-10 13:10:15.000000', 'Fecha: 10-12-2024 13:10:15  Persona: 4', 34, 4),
(48, '2024-12-10 13:10:16.000000', 'Fecha: 10-12-2024 13:10:16  Persona: 4', 35, 4),
(49, '2024-12-10 13:10:17.000000', 'Fecha: 10-12-2024 13:10:17  Persona: 4', 36, 4),
(50, '2024-12-10 13:10:18.000000', 'Fecha: 10-12-2024 13:10:18  Persona: 4', 37, 4),
(51, '2024-12-10 13:10:19.000000', 'Fecha: 10-12-2024 13:10:19  Persona: 4', 38, 4),
(52, '2024-12-10 13:10:20.000000', 'Fecha: 10-12-2024 13:10:20  Persona: 4', 39, 4),
(53, '2024-12-10 13:10:21.000000', 'Fecha: 10-12-2024 13:10:21  Persona: 4', 40, 4),
(54, '2024-12-10 13:10:22.000000', 'Fecha: 10-12-2024 13:10:22  Persona: 4', 41, 4),
(55, '2024-12-10 13:10:23.000000', 'Fecha: 10-12-2024 13:10:23  Persona: 4', 42, 4),
(56, '2024-12-10 13:10:24.000000', 'Fecha: 10-12-2024 13:10:24  Persona: 4', 43, 4),
(57, '2024-12-10 13:10:26.000000', 'Fecha: 10-12-2024 13:10:26  Persona: 4', 44, 4),
(58, '2024-12-10 13:10:27.000000', 'Fecha: 10-12-2024 13:10:27  Persona: 4', 45, 4),
(59, '2024-12-10 13:10:28.000000', 'Fecha: 10-12-2024 13:10:28  Persona: 4', 46, 4),
(60, '2024-12-10 13:10:29.000000', 'Fecha: 10-12-2024 13:10:29  Persona: 4', 47, 4),
(61, '2024-12-10 13:10:31.000000', 'Fecha: 10-12-2024 13:10:31  Persona: 4', 48, 4),
(62, '2024-12-10 13:10:37.000000', 'Fecha: 10-12-2024 13:10:37  Persona: 4', 44, 4),
(63, '2024-12-10 13:10:38.000000', 'Fecha: 10-12-2024 13:10:38  Persona: 4', 44, 4),
(64, '2024-12-10 13:10:40.000000', 'Fecha: 10-12-2024 13:10:40  Persona: 4', 33, 4),
(65, '2024-12-10 13:10:41.000000', 'Fecha: 10-12-2024 13:10:41  Persona: 4', 32, 4),
(66, '2024-12-10 13:10:45.000000', 'Fecha: 10-12-2024 13:10:45  Persona: 4', 36, 4),
(67, '2024-12-10 13:15:42.000000', 'Fecha: 10-12-2024 13:15:42  Persona: 3', 49, 3),
(68, '2024-12-10 13:15:43.000000', 'Fecha: 10-12-2024 13:15:43  Persona: 3', 50, 3),
(69, '2024-12-10 13:15:44.000000', 'Fecha: 10-12-2024 13:15:44  Persona: 3', 51, 3),
(70, '2024-12-10 13:15:47.000000', 'Fecha: 10-12-2024 13:15:47  Persona: 3', 52, 3),
(71, '2024-12-10 13:15:48.000000', 'Fecha: 10-12-2024 13:15:48  Persona: 3', 53, 3),
(72, '2024-12-10 13:15:49.000000', 'Fecha: 10-12-2024 13:15:49  Persona: 3', 54, 3),
(73, '2024-12-10 13:15:50.000000', 'Fecha: 10-12-2024 13:15:50  Persona: 3', 55, 3),
(74, '2024-12-10 13:15:52.000000', 'Fecha: 10-12-2024 13:15:52  Persona: 3', 56, 3),
(75, '2024-12-10 13:15:53.000000', 'Fecha: 10-12-2024 13:15:53  Persona: 3', 57, 3),
(76, '2024-12-10 13:15:54.000000', 'Fecha: 10-12-2024 13:15:54  Persona: 3', 58, 3),
(77, '2024-12-10 13:15:54.000000', 'Fecha: 10-12-2024 13:15:54  Persona: 3', 59, 3),
(78, '2024-12-10 13:15:56.000000', 'Fecha: 10-12-2024 13:15:56  Persona: 3', 60, 3),
(79, '2024-12-10 13:15:57.000000', 'Fecha: 10-12-2024 13:15:57  Persona: 3', 61, 3),
(80, '2024-12-10 13:15:57.000000', 'Fecha: 10-12-2024 13:15:57  Persona: 3', 62, 3),
(81, '2024-12-10 13:15:58.000000', 'Fecha: 10-12-2024 13:15:58  Persona: 3', 63, 3),
(82, '2024-12-10 13:15:59.000000', 'Fecha: 10-12-2024 13:15:59  Persona: 3', 64, 3),
(83, '2024-12-10 13:16:00.000000', 'Fecha: 10-12-2024 13:16:00  Persona: 3', 65, 3),
(84, '2024-12-10 13:16:02.000000', 'Fecha: 10-12-2024 13:16:02  Persona: 3', 66, 3),
(85, '2024-12-10 13:16:03.000000', 'Fecha: 10-12-2024 13:16:03  Persona: 3', 67, 3),
(86, '2024-12-10 13:16:04.000000', 'Fecha: 10-12-2024 13:16:04  Persona: 3', 68, 3),
(87, '2024-12-10 13:16:05.000000', 'Fecha: 10-12-2024 13:16:05  Persona: 3', 69, 3),
(88, '2024-12-10 13:16:06.000000', 'Fecha: 10-12-2024 13:16:06  Persona: 3', 70, 3),
(89, '2024-12-10 13:16:07.000000', 'Fecha: 10-12-2024 13:16:07  Persona: 3', 71, 3),
(90, '2024-12-10 13:16:08.000000', 'Fecha: 10-12-2024 13:16:08  Persona: 3', 72, 3),
(91, '2024-12-10 13:16:09.000000', 'Fecha: 10-12-2024 13:16:09  Persona: 3', 73, 3),
(92, '2024-12-10 13:16:10.000000', 'Fecha: 10-12-2024 13:16:10  Persona: 3', 74, 3),
(93, '2024-12-10 13:16:12.000000', 'Fecha: 10-12-2024 13:16:12  Persona: 3', 75, 3),
(94, '2024-12-10 13:16:13.000000', 'Fecha: 10-12-2024 13:16:13  Persona: 3', 76, 3),
(95, '2025-01-21 12:46:42.000000', 'Ejemplar GIRASOL_3 Se ubica en 2 de la seccion SDDSSAS', 3, 2),
(96, '2025-01-21 13:39:56.000000', 'Ejemplar JAZMIN_15 Se ubica en 3 de la seccion QDWEFESF', 76, 1),
(97, '2025-01-21 14:07:01.000000', 'Ejemplar GIRASOL_1 Se ubica en 1 de la seccion SECCIONPRIMERA', 1, 1),
(119, '2025-02-12 12:14:04.000000', 'Fecha: 12/02/2025 12:14:04  Persona: 1', 97, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `email`, `nombre`) VALUES
(1, 'viveroadmin@gmail.com', 'admin'),
(2, 'SAMUELVIVERO@GMAIL.ES', 'SAMUEL'),
(3, 'JAVIERVIVER@GMAIL.ES', 'JAVIER'),
(4, 'SOFIAVIVERO@GMAIL.ES', 'SOFIA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

CREATE TABLE `plantas` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `nombrecientifico` varchar(255) DEFAULT NULL,
  `nombrecomun` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`id`, `codigo`, `nombrecientifico`, `nombrecomun`) VALUES
(1, 'GIRASOL', 'HELIANTHUS ANNUUS', 'GIRASOL '),
(2, 'LAVANDA ', 'LAVANDULA ANGUSTIFOLIA', 'LAVANDAzxcxz'),
(3, 'CACTUS ', 'CACTACEAE', 'CACTUS '),
(4, 'MENTA', 'MENTHA SPICATA', 'MENTA'),
(5, 'TULIPAN', 'TULIPA SPP', 'TULIPAN'),
(6, 'JAZMIN', 'JASMINUM SPP', 'JAZMIN'),
(7, 'LIRIO', 'LILIUM SPPg', 'LIRIO');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgl50fmouks2ue8s9yclvv059j` (`usuario`),
  ADD UNIQUE KEY `UK2qnke2fqcv0nd38l37psgn2jp` (`idpersona`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKclleiwyydddhkx72v38u6uw0l` (`idplanta`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi0faybj2dxdm0opshpnd6ofpk` (`idejemplar`),
  ADD KEY `FKownge66kxf3cic28hro55y809` (`idpersona`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbqo6lbeads0ifdh6dohhfhryp` (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `plantas`
--
ALTER TABLE `plantas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `FK1lb0gapnyucp3hsramcjxj8q6` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `FKclleiwyydddhkx72v38u6uw0l` FOREIGN KEY (`idplanta`) REFERENCES `plantas` (`id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `FKi0faybj2dxdm0opshpnd6ofpk` FOREIGN KEY (`idejemplar`) REFERENCES `ejemplares` (`id`),
  ADD CONSTRAINT `FKownge66kxf3cic28hro55y809` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
