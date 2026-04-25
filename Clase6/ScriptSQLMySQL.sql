-- Eliminación de PROCEDIMIENTOS
DROP TABLE IF EXISTS linea_orden_venta;
DROP TABLE IF EXISTS orden_venta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS area;
DROP TABLE IF EXISTS producto;
-- Creacion de TABLAS
CREATE TABLE area(
	id_area INT AUTO_INCREMENT,
    nombre VARCHAR(30),
    activa TINYINT,
    PRIMARY KEY(id_area)
)ENGINE=InnoDB;
CREATE TABLE persona(
	id_persona INT AUTO_INCREMENT,
    DNI VARCHAR(8),
    nombre VARCHAR(35),
    apellido_paterno VARCHAR(35),
    genero CHAR,
    fecha_nacimiento DATE,
    PRIMARY KEY(id_persona)
)ENGINE=InnoDB;
CREATE TABLE empleado(
	id_empleado INT,
    fid_area INT,
    cargo VARCHAR(35),
    sueldo DECIMAL(10,2),
    activo TINYINT,
    PRIMARY KEY(id_empleado),
    FOREIGN KEY (id_empleado) REFERENCES persona(id_persona),
    FOREIGN KEY (fid_area) REFERENCES area(id_area)
)ENGINE=InnoDB;
CREATE TABLE cliente(
	id_cliente INT,
    linea_credito DECIMAL(10,2),
    categoria ENUM('STANDARD','VIP','PLATINUM'),
    PRIMARY KEY(id_cliente),
    FOREIGN KEY(id_cliente) REFERENCES persona(id_persona)
)ENGINE=InnoDB;
CREATE TABLE producto(
	id_producto INT AUTO_INCREMENT,
    nombre VARCHAR(45),
    unidad_medida VARCHAR(30),
    precio DECIMAL(10,2),
    activo TINYINT,
    PRIMARY KEY(id_producto)
)ENGINE=InnoDB;
CREATE TABLE orden_venta(
	id_orden_venta INT PRIMARY KEY AUTO_INCREMENT,
    fid_empleado INT,
    fid_cliente INT,
    total DECIMAL(10,2),
    fecha_hora DATETIME,
    activa TINYINT,
    FOREIGN KEY (fid_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (fid_cliente) REFERENCES cliente(id_cliente)
)ENGINE=InnoDB;
CREATE TABLE linea_orden_venta(
	id_linea_orden_venta INT PRIMARY KEY AUTO_INCREMENT,
    fid_orden_venta INT,
    fid_producto INT,
    cantidad_unidades INT,
    subtotal DECIMAL(10,2),
    activa TINYINT,
    FOREIGN KEY (fid_orden_venta) REFERENCES orden_venta(id_orden_venta),
    FOREIGN KEY (fid_producto) REFERENCES producto(id_producto)
)ENGINE=InnoDB;
INSERT INTO area(nombre,activa) VALUES("CONTABILIDAD",1);
-- Eliminando procedimientos de area
DROP PROCEDURE IF EXISTS INSERTAR_AREA;
-- Eliminando procedimientos de empleado
DROP PROCEDURE IF EXISTS INSERTAR_EMPLEADO;
DROP PROCEDURE IF EXISTS MODIFICAR_EMPLEADO;
DROP PROCEDURE IF EXISTS ELIMINAR_EMPLEADO;
DROP PROCEDURE IF EXISTS LISTAR_EMPLEADOS_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_EMPLEADO_X_ID;
-- Eliminando procedimientos de cliente
DROP PROCEDURE IF EXISTS INSERTAR_CLIENTE;
DROP PROCEDURE IF EXISTS MODIFICAR_CLIENTE;
DROP PROCEDURE IF EXISTS ELIMINAR_CLIENTE;
DROP PROCEDURE IF EXISTS LISTAR_CLIENTES_TODOS;
DROP PROCEDURE IF EXISTS LISTAR_CLIENTE_X_ID;
-- Eliminando procedimientos de producto
DROP PROCEDURE IF EXISTS LISTAR_PRODUCTOS_TODOS;
-- Eliminando procedimientos de orden de venta
DROP PROCEDURE IF EXISTS INSERTAR_ORDEN_VENTA;
-- Eliminando procedimientos de linea de orden de venta
DROP PROCEDURE IF EXISTS INSERTAR_LINEA_ORDEN_VENTA;
-- Creando PROCEDIMIENTOS
-- AREAS
DELIMITER $
CREATE PROCEDURE INSERTAR_AREA(
	OUT _id_area INT,
    IN _nombre VARCHAR(30)
)
BEGIN
	INSERT INTO area(nombre,activa) VALUES(_nombre,1);
	SET _id_area = @@last_insert_id;
END$
-- EMPLEADOS
CREATE PROCEDURE INSERTAR_EMPLEADO(
	OUT _id_empleado INT,
    IN _fid_area INT,
    IN _DNI VARCHAR(8),
    IN _nombre VARCHAR(35),
    IN _apellido_paterno VARCHAR(35),
    IN _genero CHAR,
    IN _fecha_nacimiento DATE,
    IN _cargo VARCHAR(35),
    IN _sueldo DECIMAL(10,2)
)
BEGIN
	INSERT INTO persona(DNI,nombre,apellido_paterno,genero,fecha_nacimiento) VALUES (_DNI,_nombre,_apellido_paterno,_genero,_fecha_nacimiento);
    SET _id_empleado = @@last_insert_id;
    INSERT INTO empleado(id_empleado,fid_area,cargo,sueldo,activo) VALUES (_id_empleado,_fid_area,_cargo,_sueldo,1);
END$
CREATE PROCEDURE MODIFICAR_EMPLEADO(
	IN _id_empleado INT,
    IN _fid_area INT,
    IN _DNI VARCHAR(8),
    IN _nombre VARCHAR(35),
    IN _apellido_paterno VARCHAR(35),
    IN _genero CHAR,
    IN _fecha_nacimiento DATE,
    IN _cargo VARCHAR(35),
    IN _sueldo DECIMAL(10,2)
)
BEGIN
	UPDATE persona SET DNI =_DNI, nombre = _nombre, apellido_paterno = _apellido_paterno, genero = _genero, fecha_nacimiento = _fecha_nacimiento WHERE id_persona = _id_empleado;
    UPDATE empleado SET fid_area = _fid_area, cargo = _cargo, sueldo =_sueldo WHERE id_empleado = _id_empleado;
END$
CREATE PROCEDURE ELIMINAR_EMPLEADO(
	IN _id_empleado INT
)
BEGIN
	UPDATE empleado SET activo = 0 WHERE id_empleado = _id_empleado;
END$
CREATE PROCEDURE LISTAR_EMPLEADOS_TODOS(
)
BEGIN
	SELECT p.id_persona, p.DNI, p.nombre, p.apellido_paterno, p.genero, p.fecha_nacimiento, a.id_area, a.nombre as nombre_area, e.cargo, e.sueldo FROM persona p INNER JOIN empleado e ON p.id_persona = e.id_empleado INNER JOIN area a ON e.fid_area = a.id_area WHERE e.activo = 1; 
END$
CREATE PROCEDURE LISTAR_EMPLEADO_X_ID(
	IN _id_empleado INT
)
BEGIN
	SELECT p.id_persona, p.DNI, p.nombre, p.apellido_paterno, p.genero, p.fecha_nacimiento, a.id_area, a.nombre as nombre_area, e.cargo, e.sueldo FROM persona p INNER JOIN empleado e ON p.id_persona = e.id_empleado INNER JOIN area a ON e.fid_area = a.id_area WHERE p.id_persona = _id_empleado; 
END$
-- CLIENTES
CREATE PROCEDURE INSERTAR_CLIENTE(
	OUT _id_cliente INT,
    IN _DNI VARCHAR(8),
    IN _nombre VARCHAR(35),
    IN _apellido_paterno VARCHAR(35),
    IN _genero CHAR,
    IN _fecha_nacimiento DATE,
    IN _linea_credito DECIMAL(10,2),
    IN _categoria ENUM('STANDARD','VIP','PLATINUM')
)
BEGIN
	INSERT INTO persona(DNI,nombre,apellido_paterno,genero,fecha_nacimiento) VALUES (_DNI,_nombre,_apellido_paterno,_genero,_fecha_nacimiento);
    SET _id_cliente = @@last_insert_id;
    INSERT INTO cliente(id_cliente,linea_credito,categoria) VALUES(_id_cliente,_linea_credito,_categoria);
END$
CREATE PROCEDURE MODIFICAR_CLIENTE(
	IN _id_cliente INT,
    IN _DNI VARCHAR(8),
    IN _nombre VARCHAR(35),
    IN _apellido_paterno VARCHAR(35),
    IN _genero CHAR,
    IN _fecha_nacimiento DATE,
    IN _linea_credito DECIMAL(10,2),
    IN _categoria ENUM('STANDARD','VIP','PLATINUM')
)
BEGIN
	UPDATE persona SET DNI = _DNI, nombre = _nombre, apellido_paterno = _apellido_paterno, genero = _genero, fecha_nacimiento = _fecha_nacimiento WHERE id_persona = _id_cliente;
    UPDATE cliente SET linea_credito = _linea_credito, categoria = _categoria WHERE id_cliente = _id_cliente;
END$
CREATE PROCEDURE LISTAR_CLIENTES_TODOS()
BEGIN
	SELECT p.id_persona, p.DNI, p.nombre, p.apellido_paterno, p.genero, p.fecha_nacimiento, c.linea_credito, c.categoria FROM persona p INNER JOIN cliente c ON p.id_persona = c.id_cliente;
END$
CREATE PROCEDURE LISTAR_CLIENTE_X_ID(
	IN _id_cliente INT
)
BEGIN
	SELECT p.id_persona, p.DNI, p.nombre, p.apellido_paterno, p.genero, p.fecha_nacimiento, c.linea_credito, c.categoria FROM persona p INNER JOIN cliente c ON p.id_persona = c.id_cliente WHERE p.id_persona = _id_cliente;
END$
DELIMITER $
CREATE PROCEDURE LISTAR_PRODUCTOS_TODOS()
BEGIN
	SELECT id_producto, nombre, unidad_medida, precio FROM producto where activo = 1;
END$
DELIMITER $
DROP PROCEDURE IF EXISTS INSERTAR_ORDEN_VENTA$
CREATE PROCEDURE INSERTAR_ORDEN_VENTA(
	OUT _id_orden_venta INT,
    IN _fid_empleado INT,
    IN _fid_cliente INT,
    IN _total DECIMAL(10,2)
)
BEGIN
	INSERT INTO orden_venta(fid_empleado,fid_cliente,total,fecha_hora,activa) VALUES(_fid_empleado,_fid_cliente,_total,NOW() - INTERVAL 5 HOUR,1);
    SET _id_orden_venta = @@last_insert_id;
END$
DELIMITER $
CREATE PROCEDURE INSERTAR_LINEA_ORDEN_VENTA(
	OUT _id_linea_orden_venta INT,
    IN _fid_orden_venta INT,
    IN _fid_producto INT,
    IN _cantidad_unidades INT,
    IN _subtotal DECIMAL(10,2)
)
BEGIN
	INSERT INTO linea_orden_venta(fid_orden_venta,fid_producto,cantidad_unidades,subtotal,activa) VALUES(_fid_orden_venta, _fid_producto, _cantidad_unidades,_subtotal,1);
END$
DELIMITER ;
-- Insertando registros
CALL INSERTAR_AREA(@id,'BIENESTAR');

INSERT INTO producto(nombre,unidad_medida,precio,activo) VALUES('GASEOSA INKA KOLA','1.5 LTS',4.5,1);
INSERT INTO producto(nombre,unidad_medida,precio,activo) VALUES('GASEOSA INKA KOLA','2.5 LTS',7.0,1);
INSERT INTO producto(nombre,unidad_medida,precio,activo) VALUES('LAVAVAJILLAS SAPOLIO','500 GR',13.20,1);