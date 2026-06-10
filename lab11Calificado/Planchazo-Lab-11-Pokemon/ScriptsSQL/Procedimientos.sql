-- Creación de procedimientos
-- Tal vez no son necesarios todos pero anyway
Use Lab11Pokemon;


-- --------------------INSERCION----------------------
-- Si existe lo eliminamos
DROP PROCEDURE IF EXISTS INSERTAR_POKEMON;
DELIMITER $$ 
CREATE PROCEDURE INSERTAR_POKEMON(
	OUT p_id int,
	IN p_fid_tipo int,
    IN p_nombre varchar(80),
    IN p_altura decimal(5,2),
    IN p_peso decimal(5,2),
    IN p_estado_evolutivo ENUM('BASICO','INTERMEDIO','FINAL'),
    IN p_descripcion varchar(255)
)	
BEGIN
	INSERT INTO pokemon (fid_tipo,nombre, altura, peso, estado_evolutivo, descripcion) 
    VALUES (p_fid_tipo, p_nombre, p_altura, p_peso, p_estado_evolutivo,p_descripcion);
    
    -- Guardamos el último id insertado, servirá para java y verificar si la inserción ha ocurrido
    SET p_id = @@last_insert_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS INSERTAR_TIPO_POKEMON;
DELIMITER $$ 
CREATE PROCEDURE INSERTAR_TIPO_POKEMON(
	OUT p_id int,
	IN p_nombre varchar(50)
)
BEGIN
	INSERT INTO tipo_pokemon (nombre) 
    VALUES (p_nombre);
    
    -- Guardamos el último id insertado
    SET p_id = @@last_insert_id;
END $$

DELIMITER ;

-- ------------FIN INSERCIONES----------------

-- -------------MODIFICACIONES---------------
DROP PROCEDURE IF EXISTS MODIFICAR_POKEMON;
DELIMITER $$ 
CREATE PROCEDURE MODIFICAR_POKEMON(
	IN p_id int,
	IN p_fid_tipo int,
    IN p_nombre varchar(80),
    IN p_altura decimal(5,2),
    IN p_peso decimal(5,2),
    IN p_estado_evolutivo ENUM('BASICO','INTERMEDIO','FINAL'),
    IN p_descripcion varchar(255)
)
BEGIN
	UPDATE pokemon 
    SET
		fid_tipo = p_fid_tipo,
        nombre = p_nombre,
        altura = p_altura,
        peso = p_peso,
        estado_evolutivo = p_estado_evolutivo,
        descripcion = p_descripcion
	WHERE id_pokemon = p_id; -- Condición para encontrar al pokemon que queremos modificar
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS MODIFICAR_TIPO_POKEMON;
DELIMITER $$ 
CREATE PROCEDURE MODIFICAR_TIPO_POKEMON(
	IN p_id int,
    IN p_nombre varchar(50)
)
BEGIN
	UPDATE tipo_pokemon
    SET
		nombre = p_nombre
	WHERE id_tipo = p_id; -- Condición para encontrar el tipo que queremos modificar
END $$

DELIMITER ;

-- -------------FIN MODIFICACIONES---------------

-- ELIMINAR
-- NO ES NECESARIO
-- FIN ELIMINAR


-- ------------------BUSQUEDA POR ID--------------------

 

DROP PROCEDURE IF EXISTS BUSCAR_POKEMON_POR_ID; 
DELIMITER $$ 
CREATE PROCEDURE BUSCAR_POKEMON_POR_ID(
	IN p_id int
)
BEGIN
	SELECT id_pokemon, fid_tipo, nombre, altura, peso, estado_evolutivo, descripcion FROM pokemon p
    WHERE p.id_pokemon = p_id;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS BUSCAR_TIPO_POKEMON_POR_ID; 
DELIMITER $$ 
CREATE PROCEDURE BUSCAR_TIPO_POKEMON_POR_ID(
	IN p_id int
)
BEGIN
	SELECT id_tipo, nombre FROM tipo_pokemon tp
    WHERE tp.id_tipo = p_id;
END $$

DELIMITER ;

DROP PROCEDURE IF EXISTS BUSCAR_TIPO_POKEMON_POR_NOMBRE; 
DELIMITER $$ 
CREATE PROCEDURE BUSCAR_TIPO_POKEMON_POR_NOMBRE(
	IN p_nombre varchar(50)
)
BEGIN
	SELECT id_tipo, nombre FROM tipo_pokemon tp
    WHERE tp.nombre = p_nombre;
END $$

DELIMITER ;

-- ------------------FIN BUSQUEDA POR ID----------------

-- ------------------ LISTAR TODOS -----------------------

DROP PROCEDURE IF EXISTS LISTAR_TODOS_POKEMONES;
DELIMITER $$ 
CREATE PROCEDURE LISTAR_TODOS_POKEMONES (
	
)
BEGIN
	SELECT id_pokemon, fid_tipo, nombre, altura, peso, estado_evolutivo, descripcion FROM pokemon;
END $$

DELIMITER ;


DROP PROCEDURE IF EXISTS LISTAR_TODOS_TIPOS_POKEMONES;
DELIMITER $$ 
CREATE PROCEDURE LISTAR_TODOS_TIPOS_POKEMONES (
	
)
BEGIN
	SELECT id_tipo, nombre FROM tipo_pokemon;
END $$

DELIMITER ;
-- ------------------ FIN LISTAR TODOS --------------------