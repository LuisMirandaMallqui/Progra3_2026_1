use lab06;

DROP PROCEDURE IF EXISTS insertarPaciente;
DROP PROCEDURE IF EXISTS modificarPaciente;
DROP PROCEDURE IF EXISTS eliminarPaciente;
DROP PROCEDURE IF EXISTS buscarPacientePorId;
DROP PROCEDURE IF EXISTS listarPacientes;

DELIMITER //
CREATE PROCEDURE insertarPaciente(
	IN p_idTutor INT, 
    IN p_nombre VARCHAR(100), 
    IN p_especie VARCHAR(10), 
    IN p_raza VARCHAR(50), 
    IN p_edad INT, 
    IN p_estado VARCHAR(50), 
    OUT p_id INT)
BEGIN
    INSERT INTO paciente(
		idTutor, 
        nombre, 
        especie, 
        raza, 
        edad, 
        estado) 
	VALUES(
		p_idTutor, 
        p_nombre, 
        p_especie,
        p_raza, 
        p_edad, 
        p_estado);
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarPaciente(
	IN p_idTutor INT, 
    IN p_nombre VARCHAR(100), 
    IN p_especie VARCHAR(10), 
    IN p_raza VARCHAR(50), 
    IN p_edad INT, 
    IN p_estado VARCHAR(50), 
    IN p_id INT)
BEGIN
	UPDATE paciente
    SET 
		idTutor = p_idTutor, 
        nombre = p_nombre, 
        especie = p_especie, 
        raza = p_raza, 
        edad = p_edad, 
        estado = p_estado
    WHERE id = p_id;
END //

CREATE PROCEDURE eliminarPaciente(IN p_id INT)
BEGIN
	DELETE FROM paciente WHERE id = p_id;
END //

CREATE PROCEDURE buscarPacientePorId(IN p_id INT)
BEGIN
	SELECT * FROM paciente WHERE id = p_id;
END //

CREATE PROCEDURE listarPacientes()
BEGIN
	SELECT * FROM paciente;
END //