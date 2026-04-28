use lab06;

DROP PROCEDURE IF EXISTS insertarCitaMedica;
DROP PROCEDURE IF EXISTS modificarCitaMedica;
DROP PROCEDURE IF EXISTS eliminarCitaMedica;
DROP PROCEDURE IF EXISTS buscarCitaMedicaPorId;
DROP PROCEDURE IF EXISTS listarCitasMedicas;

DELIMITER //
CREATE PROCEDURE insertarCitaMedica(
    IN p_idPaciente INT, 
    IN p_idMedico INT, 
    IN p_motivo VARCHAR(200), 
    IN p_fecha DATE, 
    OUT p_id INT)
BEGIN
    INSERT INTO citamedica(
		idPaciente, 
		idMedico, 
		motivo, 
		fecha) 
	VALUES (
		p_idPaciente, 
		p_idMedico, 
		p_motivo, 
		p_fecha
	);
    SET p_id = LAST_INSERT_ID();
END //

CREATE PROCEDURE modificarCitaMedica(
	IN p_idPaciente INT, 
    IN p_idMedico INT, 
    IN p_motivo VARCHAR(200), 
    IN p_fecha DATE, 
    IN p_id INT)
BEGIN
	UPDATE citamedica
    SET 
		idPaciente = p_idPaciente, 
		idMedico = p_idMedico, 
		motivo = p_motivo, 
		fecha = p_fecha
    WHERE id = p_id;
END //

CREATE PROCEDURE eliminarCitaMedica(IN p_id INT)
BEGIN
	DELETE FROM citamedica WHERE id = p_id;
END //

CREATE PROCEDURE buscarCitaMedicaPorId(IN p_id INT)
BEGIN
	SELECT * FROM citamedica WHERE id = p_id;
END //

CREATE PROCEDURE listarCitasMedicas()
BEGIN
	SELECT * FROM citamedica;
END //