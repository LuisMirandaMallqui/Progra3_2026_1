use lab06;

DROP PROCEDURE IF EXISTS insertarTutor;
DROP PROCEDURE IF EXISTS modificarTutor;
DROP PROCEDURE IF EXISTS eliminarTutor;
DROP PROCEDURE IF EXISTS buscarTutorPorId;
DROP PROCEDURE IF EXISTS listarTutores;

DELIMITER //
CREATE PROCEDURE insertarTutor(
	IN p_dni CHAR(8),
    IN p_nombre VARCHAR(100), 
    IN p_direccion VARCHAR(200), 
    IN p_telefono VARCHAR(20), 
    OUT p_id INT)
BEGIN
    INSERT INTO tutor(
		dni,
		nombre, 
		direccion,  
		telefono 
	) 
	VALUES (
		p_dni,
		p_nombre, 
		p_direccion,  
		p_telefono 
	);
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarTutor(
	IN p_dni CHAR(8),
    IN p_nombre VARCHAR(100), 
    IN p_direccion VARCHAR(200), 
    IN p_telefono VARCHAR(20), 
    IN p_id INT)
BEGIN
	UPDATE tutor
    SET 
		dni = p_dni,
		nombre = p_nombre, 
		direccion = p_direccion,  
		telefono = p_telefono
    WHERE id = p_id;
END //

CREATE PROCEDURE eliminarTutor(IN p_id INT)
BEGIN
	DELETE FROM tutor WHERE id = p_id;
END //

CREATE PROCEDURE buscarTutorPorId(IN p_id INT)
BEGIN
	SELECT * FROM tutor WHERE id = p_id;
END //

CREATE PROCEDURE listarTutores()
BEGIN
	SELECT * FROM tutor;
END //