use lab06;

DROP PROCEDURE IF EXISTS insertarMedico;
DROP PROCEDURE IF EXISTS modificarMedico;
DROP PROCEDURE IF EXISTS eliminarMedico;
DROP PROCEDURE IF EXISTS buscarMedicoPorId;
DROP PROCEDURE IF EXISTS listarMedicos;

DELIMITER //
CREATE PROCEDURE insertarMedico(IN p_dni CHAR(8), IN p_nombre VARCHAR(100), IN p_especialidad VARCHAR(60), OUT p_id INT)
BEGIN
    INSERT INTO medico(dni, nombre, especialidad) VALUES(p_dni, p_nombre, p_especialidad);
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarMedico(IN p_dni CHAR(8), IN p_nombre VARCHAR(100), IN p_especialidad VARCHAR(60), IN p_id INT)
BEGIN
	UPDATE medico
    SET 
		dni = p_dni, 
        nombre = p_nombre, 
        especialidad = p_especialidad
    WHERE id = p_id;
END //

CREATE PROCEDURE eliminarMedico(IN p_id INT)
BEGIN
	DELETE FROM medico WHERE id = p_id;
END //

CREATE PROCEDURE buscarMedicoPorId(IN p_id INT)
BEGIN
	SELECT * FROM medico WHERE id = p_id;
END //

CREATE PROCEDURE listarMedicos()
BEGIN
	SELECT * FROM medico;
END //