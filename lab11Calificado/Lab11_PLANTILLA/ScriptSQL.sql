DROP TABLE IF EXISTS respuesta_alumno;
DROP TABLE IF EXISTS examen_pregunta;
DROP TABLE IF EXISTS opcion_respuesta;
DROP TABLE IF EXISTS examen;
DROP TABLE IF EXISTS pregunta;
DROP TABLE IF EXISTS alumno;

CREATE TABLE alumno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    correo VARCHAR(120) NOT NULL,
	estado CHAR(1)
);

CREATE TABLE examen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_alumno INT NOT NULL,
    titulo VARCHAR(120) NOT NULL,
	fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fechaResolucion DATETIME NULL,
	estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    nota INT DEFAULT 0,
    FOREIGN KEY (id_alumno) REFERENCES alumno(id)
);

CREATE TABLE pregunta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enunciado VARCHAR(300) NOT NULL
);

CREATE TABLE examen_pregunta (
    id INT AUTO_INCREMENT PRIMARY KEY,
	id_examen INT NOT NULL,
    id_pregunta INT NOT NULL,
	orden INT NOT NULL,
	puntaje INT NOT NULL,
    FOREIGN KEY (id_examen) REFERENCES examen(id),
    FOREIGN KEY (id_pregunta) REFERENCES pregunta(id)
);

CREATE TABLE opcion_respuesta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_pregunta INT NOT NULL,
    texto_opcion VARCHAR(200) NOT NULL,
    es_correcta TINYINT(1) NOT NULL DEFAULT 0,
	orden INT NOT NULL,
    FOREIGN KEY (id_pregunta) REFERENCES pregunta(id)
);

CREATE TABLE respuesta_alumno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_examen_pregunta INT NOT NULL,
    id_opcion_respuesta INT NOT NULL,
	es_correcta TINYINT(1) NOT NULL DEFAULT 0,
	puntaje_obtenido INT NOT NULL DEFAULT 0,
    FOREIGN KEY (id_examen_pregunta) REFERENCES examen_pregunta(id),
    FOREIGN KEY (id_opcion_respuesta) REFERENCES opcion_respuesta(id)
);

INSERT INTO alumno (codigo, nombre, apellidos, correo, estado) VALUES
('A001', 'Juan', 'Pérez', 'juan.perez@testsoft.edu.pe', 'A'),
('A002', 'María', 'Gómez', 'maria.gomez@testsoft.edu.pe', 'A');

INSERT INTO examen (id_alumno, titulo, estado, nota) VALUES
(1, 'Evaluación 1', 'PENDIENTE', 0),
(2, 'Evaluación 2', 'PENDIENTE', 0);

INSERT INTO pregunta (enunciado) VALUES
('¿Qué estructura permite almacenar datos bajo el principio LIFO?'),
('¿Cuál es el resultado de 5 + 3 * 2?'),
('¿Qué sentencia se usa para repetir instrucciones en Java?'),
('¿Qué tipo de dato permite almacenar valores verdadero o falso?'),
('¿Qué concepto permite reutilizar código mediante métodos?');

INSERT INTO examen_pregunta (id_examen, id_pregunta, orden, puntaje) VALUES
(1, 1, 1, 4),
(1, 2, 2, 4),
(1, 3, 3, 4),
(1, 4, 4, 4),
(1, 5, 5, 4),

(2, 1, 1, 6),
(2, 2, 2, 6),
(2, 3, 3, 8);


INSERT INTO opcion_respuesta (id_pregunta, texto_opcion, es_correcta, orden) VALUES
(1, 'Cola', 0, 1),
(1, 'Pila', 1, 2),
(1, 'Árbol', 0, 3),
(1, 'Lista circular', 0, 4),

(2, '16', 0, 1),
(2, '11', 1, 2),
(2, '13', 0, 3),
(2, '10', 0, 4),

(3, 'if', 0, 1),
(3, 'switch', 0, 2),
(3, 'for', 1, 3),
(3, 'return', 0, 4),

(4, 'int', 0, 1),
(4, 'double', 0, 2),
(4, 'boolean', 1, 3),
(4, 'String', 0, 4),

(5, 'Programación modular', 1, 1),
(5, 'Compilación', 0, 2),
(5, 'Asignación', 0, 3),
(5, 'Concatenación', 0, 4);

DROP PROCEDURE IF EXISTS SP_OBTENER_ALUMNO_POR_ID;
DROP PROCEDURE IF EXISTS SP_LISTAR_EXAMENES_PENDIENTES_X_ALUMNO;
DROP PROCEDURE IF EXISTS SP_OBTENER_EXAMEN_POR_ID;
DROP PROCEDURE IF EXISTS SP_ACTUALIZAR_RESULTADO_EXAMEN;
DROP PROCEDURE IF EXISTS SP_LISTAR_PREGUNTAS_X_EXAMEN;
DROP PROCEDURE IF EXISTS SP_LISTAR_OPCIONES_X_PREGUNTA;
DROP PROCEDURE IF EXISTS SP_INSERTAR_RESPUESTA_ALUMNO;
DROP PROCEDURE IF EXISTS SP_LISTAR_ALUMNOS_X_NOMBRE_APELLIDO;
DROP PROCEDURE IF EXISTS SP_INSERTAR_ALUMNO;

DELIMITER $$

CREATE PROCEDURE SP_OBTENER_ALUMNO_POR_ID(
    IN p_id INT
)
BEGIN
    SELECT 
        id,
        codigo,
        nombre,
        apellidos,
        correo,
        estado
    FROM alumno
    WHERE id = p_id;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_LISTAR_EXAMENES_PENDIENTES_X_ALUMNO(
    IN p_id_alumno INT
)
BEGIN
    SELECT 
        id,
        titulo,
        fechaCreacion,
        fechaResolucion,
        estado,
        nota,
        id_alumno
    FROM examen
    WHERE id_alumno = p_id_alumno
      AND estado = 'PENDIENTE';
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_OBTENER_EXAMEN_POR_ID(
    IN p_id INT
)
BEGIN
    SELECT 
        id,
        titulo,
        fechaCreacion,
        fechaResolucion,
        estado,
        nota,
        id_alumno
    FROM examen
    WHERE id = p_id;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_ACTUALIZAR_RESULTADO_EXAMEN(
    IN p_estado VARCHAR(20),
    IN p_nota INT,
    IN p_id INT
)
BEGIN
    UPDATE examen
    SET 
        estado = p_estado,
        nota = p_nota,
        fechaResolucion = NOW()
    WHERE id = p_id;
END	$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_LISTAR_PREGUNTAS_X_EXAMEN(
    IN p_id_examen INT
)
BEGIN
    SELECT 
        ep.id,
        ep.orden,
        ep.puntaje,
        p.id AS id_pregunta,
        p.enunciado
    FROM examen_pregunta ep
    INNER JOIN pregunta p 
        ON ep.id_pregunta = p.id
    WHERE ep.id_examen = p_id_examen
    ORDER BY ep.orden ASC;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_LISTAR_OPCIONES_X_PREGUNTA(
    IN p_id_pregunta INT
)
BEGIN
    SELECT 
        id,
        texto_opcion,
        es_correcta,
        orden
    FROM opcion_respuesta
    WHERE id_pregunta = p_id_pregunta
    ORDER BY orden ASC;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_INSERTAR_RESPUESTA_ALUMNO(
    IN p_id_examen_pregunta INT,
    IN p_id_opcion_respuesta INT,
    IN p_es_correcta TINYINT,
    IN p_puntaje_obtenido INT
)
BEGIN
    INSERT INTO respuesta_alumno (
        id_examen_pregunta,
        id_opcion_respuesta,
        es_correcta,
        puntaje_obtenido
    )
    VALUES (
        p_id_examen_pregunta,
        p_id_opcion_respuesta,
        p_es_correcta,
        p_puntaje_obtenido
    );
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_LISTAR_ALUMNOS_X_NOMBRE_APELLIDO(
    IN p_texto VARCHAR(100)
)
BEGIN
    SELECT
        id,
        codigo,
        nombre,
        apellidos,
        correo,
        estado
    FROM alumno
    WHERE 
        p_texto IS NULL
        OR p_texto = ''
        OR nombre LIKE CONCAT('%', p_texto, '%')
        OR apellidos LIKE CONCAT('%', p_texto, '%')
        OR CONCAT(nombre,' ', apellidos) LIKE CONCAT('%',p_texto,'%');
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_INSERTAR_ALUMNO(
    OUT p_id INT,
    IN p_codigo VARCHAR(20),
    IN p_nombre VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_correo VARCHAR(120),
    IN p_estado CHAR(1)
)
BEGIN
    INSERT INTO alumno (
        codigo,
        nombre,
        apellidos,
        correo,
        estado
    )
    VALUES (
        p_codigo,
        p_nombre,
        p_apellidos,
        p_correo,
        p_estado
    );

    SET p_id = LAST_INSERT_ID();
END $$

DELIMITER ;