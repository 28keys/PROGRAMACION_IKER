-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-06-2023 a las 00:13:15
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
-- Base de datos: `taller`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arreglos`
--

CREATE TABLE `arreglos` (
  `idFactura` int(11) NOT NULL,
  `idMecanico` int(11) NOT NULL,
  `matricula` varchar(7) NOT NULL,
  `fechaEntrada` date NOT NULL,
  `fechaSalida` date DEFAULT NULL,
  `importe` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `arreglos`
--

INSERT INTO `arreglos` (`idFactura`, `idMecanico`, `matricula`, `fechaEntrada`, `fechaSalida`, `importe`) VALUES
(0, 2, '6666AAA', '2023-01-22', '2003-01-31', 350),
(1, 1, '1111BNJ', '2023-05-09', '2023-05-16', 250),
(2, 2, '2222ABC', '2022-09-01', NULL, 350),
(3, 3, '3333CDF', '2023-02-28', NULL, 500),
(4, 2, '1111BNJ', '2023-02-06', NULL, 240),
(5, 3, '3333CDF', '2023-02-27', '2023-03-03', 320);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `carnet` int(11) NOT NULL,
  `visitas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`dni`, `nombre`, `telefono`, `direccion`, `carnet`, `visitas`) VALUES
('11111111A', 'Ana Sanz', '655897678', 'Bravo Murillo, 28', 12, 2),
('11111111H', 'Juan Pérez', '636208102', 'San Benito', 0, 1),
('22222222B', 'Javier García', '677123456', 'Castellana, 78', 14, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mecanicos`
--

CREATE TABLE `mecanicos` (
  `idMecanico` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `mecanicos`
--

INSERT INTO `mecanicos` (`idMecanico`, `nombre`, `telefono`) VALUES
(1, 'Pepe Pérez', '657654323'),
(2, 'Juan Sánchez', '677887789'),
(3, 'Luis Rodríguez', '657543444');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `matricula` varchar(7) NOT NULL,
  `modelo` varchar(100) NOT NULL,
  `anyoMatriculacion` int(4) NOT NULL,
  `idCliente` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`matricula`, `modelo`, `anyoMatriculacion`, `idCliente`) VALUES
('1111BNJ', 'Seat Ibiza', 2008, '11111111A'),
('2222ABC', 'Audi A4', 2015, '22222222B'),
('3333CDF', 'Mercedes clase A', 2014, '11111111A'),
('6666AAA', 'Ibiza', 2015, '11111111H');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arreglos`
--
ALTER TABLE `arreglos`
  ADD PRIMARY KEY (`idFactura`),
  ADD KEY `idMecanico` (`idMecanico`),
  ADD KEY `idMecanico_2` (`idMecanico`),
  ADD KEY `matricula` (`matricula`),
  ADD KEY `matricula_2` (`matricula`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `mecanicos`
--
ALTER TABLE `mecanicos`
  ADD PRIMARY KEY (`idMecanico`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`matricula`),
  ADD KEY `idCliente` (`idCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mecanicos`
--
ALTER TABLE `mecanicos`
  MODIFY `idMecanico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `arreglos`
--
ALTER TABLE `arreglos`
  ADD CONSTRAINT `arreglos_ibfk_2` FOREIGN KEY (`matricula`) REFERENCES `vehiculos` (`matricula`),
  ADD CONSTRAINT `arreglos_ibfk_3` FOREIGN KEY (`idMecanico`) REFERENCES `mecanicos` (`idMecanico`);

--
-- Filtros para la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD CONSTRAINT `vehiculos_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
