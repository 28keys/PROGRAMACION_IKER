-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2024 a las 23:42:25
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gym`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clases`
--

CREATE TABLE `clases` (
  `disciplina` varchar(20) NOT NULL,
  `plazas` int(11) NOT NULL,
  `ocupadas` int(11) NOT NULL,
  `hora` int(11) NOT NULL,
  `codigo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clases`
--

INSERT INTO `clases` (`disciplina`, `plazas`, `ocupadas`, `hora`, `codigo`) VALUES
('BIKE', 10, 4, 10, 'BIK10'),
('GAP', 5, 4, 13, 'GAP13'),
('YOGA', 8, 7, 12, 'YOG12'),
('ZEN', 15, 15, 14, 'ZE15'),
('ZUMBA', 12, 10, 11, 'ZUM11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `numero` int(11) NOT NULL,
  `codigoclase` varchar(5) NOT NULL,
  `usuario` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`numero`, `codigoclase`, `usuario`) VALUES
(1, 'BIK10', 'JLS21'),
(3, 'GAP13', 'LRF33'),
(4, 'GAP13', 'PGT88'),
(5, 'BIK10', 'LRF33'),
(6, 'YOG12', 'LRF33'),
(7, 'BIK10', 'ADG13'),
(8, 'ZE15', 'PGT88');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `clave` varchar(5) NOT NULL,
  `pin` int(6) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `tarifa` varchar(1) NOT NULL,
  `numeromiembros` int(11) NOT NULL,
  `descuento` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`clave`, `pin`, `nombre`, `apellidos`, `tarifa`, `numeromiembros`, `descuento`) VALUES
('ADG13', 222222, 'Ana', 'Diaz Granda', 'F', 3, '0'),
('JLS21', 111111, 'Juan', 'Lorenzo Sanz', 'N', 0, '0'),
('LRF33', 444444, 'Laura', 'Rodriguez Feliu', 'N', 0, '20'),
('PGT88', 333333, 'Pedro', 'Garrido Torcal', 'F', 5, '0');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clases`
--
ALTER TABLE `clases`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`numero`),
  ADD KEY `codigoclase` (`codigoclase`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`clave`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `numero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`codigoclase`) REFERENCES `clases` (`codigo`),
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`clave`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
