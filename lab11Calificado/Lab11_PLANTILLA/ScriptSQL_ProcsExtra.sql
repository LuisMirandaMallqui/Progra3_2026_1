-- =====================================================================
-- ScriptSQL_ProcsExtra.sql
-- Procedimientos que NO venían en ScriptSQL.sql pero que el CRUD completo
-- de Alumno necesita (modificar, eliminar lógico, buscar por código - SELECT INTO).
-- Ejecutar DESPUÉS de ScriptSQL.sql.
-- =====================================================================

DROP PROCEDURE IF EXISTS SP_MODIFICAR_ALUMNO;
DROP PROCEDURE IF EXISTS SP_ELIMINAR_ALUMNO;
DROP PROCEDURE IF EXISTS SP_BUSCAR_ALUMNO_POR_CODIGO;

DELIMITER $$

-- UPDATE simple. Devuelve filas afectadas (1 si actualizó, 0 si el id no existe).
CREATE PROCEDURE SP_MODIFICAR_ALUMNO(
    IN p_id        INT,
    IN p_codigo    VARCHAR(20),
    IN p_nombre    VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_correo    VARCHAR(120),
    IN p_estado    CHAR(1)
)
BEGIN
    UPDATE alumno
    SET codigo    = p_codigo,
        nombre    = p_nombre,
        apellidos = p_apellidos,
        correo    = p_correo,
        estado    = p_estado
    WHERE id = p_id;
END $$

-- Baja LÓGICA: no borra la fila, solo marca estado='I'. Conserva historial/FKs.
CREATE PROCEDURE SP_ELIMINAR_ALUMNO(
    IN p_id INT
)
BEGIN
    UPDATE alumno
    SET estado = 'I'
    WHERE id = p_id;
END $$

-- SELECT INTO: mete el id en el OUT en vez de devolver un ResultSet.
-- Si el código no existe, p_id queda NULL (en código se traduce a 0 = no encontrado).
CREATE PROCEDURE SP_BUSCAR_ALUMNO_POR_CODIGO(
    IN  p_codigo VARCHAR(20),
    OUT p_id     INT
)
BEGIN
    SELECT id
    INTO p_id
    FROM alumno
    WHERE codigo = p_codigo
    LIMIT 1;
END $$

DELIMITER ;
