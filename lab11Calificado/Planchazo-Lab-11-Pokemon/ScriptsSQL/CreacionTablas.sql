USE Lab11Pokemon;

 
DROP TABLE IF EXISTS pokemon;
DROP TABLE IF EXISTS tipo_pokemon;



CREATE TABLE tipo_pokemon (
	id_tipo INT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE pokemon (
	id_pokemon INT AUTO_INCREMENT PRIMARY KEY,
	fid_tipo INT NOT NULL,
	nombre VARCHAR(80) NOT NULL,
	altura DECIMAL(5,2),
	peso DECIMAL(5,2),
	estado_evolutivo ENUM('BASICO','INTERMEDIO','FINAL') NOT NULL,
	descripcion VARCHAR(255),
	FOREIGN KEY (fid_tipo) REFERENCES tipo_pokemon(id_tipo)
);