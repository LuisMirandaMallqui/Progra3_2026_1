-- ============================================================================
-- Procedimientos_Mejoras.sql  (ejecutar DESPUÉS de Procedimientos.sql)
-- MEJORAS:
--  1) LISTAR/BUSCAR de pokemon ahora hacen JOIN con tipo_pokemon para traer el
--     NOMBRE del tipo (antes solo venía el fid_tipo; el front mostraba un número).
--     Esto cubre el criterio de "incluir la info relacionada" que el profe evalúa.
--  2) Se agregan los ELIMINAR que faltaban (el CRUD estaba incompleto).
-- ============================================================================
USE Lab11Pokemon;

-- 1) LISTAR con nombre del tipo ------------------------------------------------
DROP PROCEDURE IF EXISTS LISTAR_TODOS_POKEMONES;
DELIMITER $$
CREATE PROCEDURE LISTAR_TODOS_POKEMONES()
BEGIN
    SELECT  p.id_pokemon, p.fid_tipo,
            t.nombre AS nombre_tipo,        -- <- NUEVO: nombre del tipo (JOIN)
            p.nombre, p.altura, p.peso, p.estado_evolutivo, p.descripcion
    FROM pokemon p
    INNER JOIN tipo_pokemon t ON p.fid_tipo = t.id_tipo;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS BUSCAR_POKEMON_POR_ID;
DELIMITER $$
CREATE PROCEDURE BUSCAR_POKEMON_POR_ID(IN p_id INT)
BEGIN
    SELECT  p.id_pokemon, p.fid_tipo,
            t.nombre AS nombre_tipo,        -- <- NUEVO
            p.nombre, p.altura, p.peso, p.estado_evolutivo, p.descripcion
    FROM pokemon p
    INNER JOIN tipo_pokemon t ON p.fid_tipo = t.id_tipo
    WHERE p.id_pokemon = p_id;
END $$
DELIMITER ;

-- 2) ELIMINAR (faltaban) -------------------------------------------------------
DROP PROCEDURE IF EXISTS ELIMINAR_POKEMON;
DELIMITER $$
CREATE PROCEDURE ELIMINAR_POKEMON(IN p_id INT)
BEGIN
    DELETE FROM pokemon WHERE id_pokemon = p_id;
END $$
DELIMITER ;

-- OJO: si un tipo tiene pokemones, la FK impide borrarlo (comportamiento correcto).
DROP PROCEDURE IF EXISTS ELIMINAR_TIPO_POKEMON;
DELIMITER $$
CREATE PROCEDURE ELIMINAR_TIPO_POKEMON(IN p_id INT)
BEGIN
    DELETE FROM tipo_pokemon WHERE id_tipo = p_id;
END $$
DELIMITER ;
