DROP SCHEMA IF EXISTS cinestar_dn;
CREATE SCHEMA cinestar_dn;
USE cinestar_dn;
CREATE TABLE venta_dn(
	id_venta INT PRIMARY KEY AUTO_INCREMENT,
    nombre_cliente VARCHAR(50),
    apellido_cliente VARCHAR(50),
    email_cliente VARCHAR(75),
    nombre_pelicula VARCHAR(150),
    genero_pelicula ENUM('ACCION','ANIMACION','DRAMA','CIENCIA FICCION'),
    nombre_sucursal VARCHAR(150),
    fecha_venta DATE,
    cantidad_asientos INT,
    total_venta DECIMAL(10,2)
)ENGINE=InnoDB;
INSERT INTO venta_dn(nombre_cliente,apellido_cliente,email_cliente,nombre_pelicula,genero_pelicula,nombre_sucursal,fecha_venta,cantidad_asientos,total_venta) VALUES
('ANDREA','VENERO','andrea@pucp.edu.pe','AVENGERS: ENDGAME','ACCION','CINE SAN ISIDRO','2025-09-20',2,50),
('JUAN','PEREZ','juan@pucp.edu.pe','AVENGERS: ENDGAME','ACCION','CINE SAN ISIDRO','2025-09-20',1,25),
('ANA','TORRES','ana@pucp.edu.pe','INTENSAMENTE 2','ANIMACION','CINE PRIMAVERA','2025-09-21',3,75),
('ANDREA','VENERO','andrea@pucp.edu.pe','INTENSAMENTE 2','ANIMACION','CINE SAN ISIDRO','2025-09-22',1,25),
('PEDRO','DIAZ','pedro@pucp.edu.pe','JOKER 2','DRAMA','CINE PRIMAVERA','2025-09-23',2,60),
('CAMILA','SANDOVAL','camila@pucp.edu.pe','JOKER 2','DRAMA','CINE SAN MIGUEL','2025-09-23',1,30),
('JUAN','PEREZ','juan@pucp.edu.pe','DUNE: PARTE 2','CIENCIA FICCION','CINE SAN ISIDRO','2025-09-24',2,70),
('ANDREA','VENERO','andrea@pucp.edu.pe','INTENSAMENTE 2','ANIMACION','CINE PRIMAVERA','2025-09-24',4,100),
('SOFIA','LOPEZ','sofia@pucp.edu.pe','DUNE: PARTE 2','CIENCIA FICCION','CINE SAN MIGUEL','2025-09-25',1,35),
('PEDRO','DIAZ','pedro@pucp.edu.pe','AVENGERS: ENDGAME','ACCION','CINE SAN ISIDRO','2025-09-25',2,50);

DELIMITER $
CREATE PROCEDURE LISTAR_VENTAS_TODAS()
BEGIN
	SELECT id_venta, nombre_cliente, apellido_cliente, email_cliente, nombre_pelicula, genero_pelicula, nombre_sucursal, fecha_venta, cantidad_asientos, total_venta FROM venta_dn;
END$

DELIMITER ;
DROP SCHEMA IF EXISTS cinestar_n;
CREATE SCHEMA cinestar_n;
USE cinestar_n;
CREATE TABLE cliente(
	id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre_cliente VARCHAR(50),
    apellido_cliente VARCHAR(50),
    email_cliente VARCHAR(75)
)ENGINE=InnoDB;
CREATE TABLE pelicula(
	id_pelicula INT PRIMARY KEY AUTO_INCREMENT,
    nombre_pelicula VARCHAR(150),
    genero_pelicula ENUM('ACCION','ANIMACION','DRAMA','CIENCIA FICCION')
)ENGINE=InnoDB;
CREATE TABLE sucursal(
	id_sucursal INT PRIMARY KEY AUTO_INCREMENT,
    nombre_sucursal VARCHAR(150)
)ENGINE=InnoDB;
CREATE TABLE venta(
	id_venta INT PRIMARY KEY AUTO_INCREMENT,
    fid_cliente INT,
    fid_pelicula INT,
    fid_sucursal INT,
    fecha_venta DATE,
    cantidad_asientos INT,
    total_venta DECIMAL(10,2),
    FOREIGN KEY(fid_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY(fid_pelicula) REFERENCES pelicula(id_pelicula),
    FOREIGN KEY(fid_sucursal) REFERENCES sucursal(id_sucursal)
)ENGINE=InnoDB;
DELIMITER $
CREATE PROCEDURE INSERTAR_CLIENTE(
	OUT _id_cliente INT,
    IN _nombre_cliente VARCHAR(50),
    IN _apellido_cliente VARCHAR(50),
    IN _email_cliente VARCHAR(75)
)
BEGIN
	INSERT INTO cliente(nombre_cliente, apellido_cliente, email_cliente) VALUES(_nombre_cliente, _apellido_cliente, _email_cliente);
    SET _id_cliente = @@last_insert_id;
END$
CREATE PROCEDURE INSERTAR_PELICULA(
	OUT _id_pelicula INT,
    IN _nombre_pelicula VARCHAR(150),
    IN _genero_pelicula ENUM('ACCION','ANIMACION','DRAMA','CIENCIA FICCION')
)
BEGIN
	INSERT INTO pelicula(nombre_pelicula, genero_pelicula) VALUES(_nombre_pelicula,_genero_pelicula);
    SET _id_pelicula = @@last_insert_id;
END$
CREATE PROCEDURE INSERTAR_SUCURSAL(
	OUT _id_sucursal INT,
    IN _nombre_sucursal VARCHAR(150)
)
BEGIN
	INSERT INTO sucursal(nombre_sucursal) VALUES(_nombre_sucursal);
    SET _id_sucursal = @@last_insert_id;
END$
CREATE PROCEDURE INSERTAR_VENTA(
	OUT _id_venta INT,
    IN _fid_cliente INT,
    IN _fid_pelicula INT,
    IN _fid_sucursal INT,
    IN _fecha_venta DATE,
    IN _cantidad_asientos INT,
    IN _total_venta DECIMAL(10,2)
)
BEGIN
	INSERT INTO venta(fid_cliente,fid_pelicula,fid_sucursal,fecha_venta,cantidad_asientos,total_venta) VALUES(_fid_cliente,_fid_pelicula,_fid_sucursal,_fecha_venta,_cantidad_asientos,_total_venta);
    SET _id_venta = @@last_insert_id;
END$