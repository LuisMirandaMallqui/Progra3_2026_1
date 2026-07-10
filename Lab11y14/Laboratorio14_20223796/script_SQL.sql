DROP TABLE IF EXISTS seleccion;
DROP TABLE IF EXISTS director_tecnico;


CREATE TABLE director_tecnico (
    id_director_tecnico INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(80),
    edad INT
);

CREATE TABLE seleccion (
    id_seleccion INT AUTO_INCREMENT PRIMARY KEY,
    fid_director_tecnico INT NOT NULL,
    nombre VARCHAR(80) NOT NULL,
    confederacion VARCHAR(30) NOT NULL,
    grupo CHAR(1) NOT NULL,
    ranking_fifa INT,
    url_bandera VARCHAR(300),
    clasificado TINYINT DEFAULT 1,
    FOREIGN KEY (fid_director_tecnico)
    REFERENCES director_tecnico(id_director_tecnico)
);

INSERT INTO director_tecnico(nombre,nacionalidad,edad) VALUES
('Javier Aguirre','México',67),
('Hugo Broos','Bélgica',73),
('Hong Myung-bo','Corea del Sur',56),
('Ivan Hasek','República Checa',62),

('Jesse Marsch','Canadá',52),
('Sergej Barbarez','Bosnia',53),
('Tintín Márquez','España',63),
('Murat Yakin','Suiza',51),

('Dorival Júnior','Brasil',63),
('Walid Regragui','Marruecos',50),
('Sébastien Migné','Francia',52),
('Steve Clarke','Escocia',62),

('Mauricio Pochettino','Argentina',54),
('Gustavo Alfaro','Argentina',63),
('Tony Popovic','Australia',53),
('Vincenzo Montella','Italia',51),

('Julian Nagelsmann','Alemania',39),
('Dick Advocaat','Países Bajos',77),
('Emerse Faé','Costa de Marfil',41),
('Sebastián Beccacece','Argentina',44),

('Hajime Moriyasu','Japón',57),
('Jon Dahl Tomasson','Dinamarca',48),
('Tom Saintfiet','Bélgica',52),
('Faouzi Benzarti','Túnez',75),

('Domenico Tedesco','Bélgica',40),
('Hossam Hassan','Egipto',59),
('Amir Ghalenoei','Irán',62),
('Darren Bazeley','Nueva Zelanda',53),

('Luis de la Fuente','España',64),
('Marcelo Bielsa','Argentina',70),
('Hervé Renard','Francia',57),
('Bubista','Cabo Verde',55),

('Didier Deschamps','Francia',57),
('Ståle Solbakken','Noruega',57),
('Jesús Casas','España',51),
('Pape Thiaw','Senegal',44),

('Lionel Scaloni','Argentina',48),
('Vladimir Petkovic','Bosnia',62),
('Ralf Rangnick','Austria',68),
('Jamal Sellami','Marruecos',54),

('Roberto Martínez','España',52),
('Néstor Lorenzo','Argentina',59),
('Héctor Cúper','Argentina',69),
('Srecko Katanec','Eslovenia',62),

('Thomas Tuchel','Alemania',52),
('Zlatko Dalic','Croacia',59),
('Otto Addo','Alemania',50),
('Thomas Christiansen','Dinamarca',52);

INSERT INTO seleccion
(fid_director_tecnico,nombre,confederacion,grupo,ranking_fifa,url_bandera,clasificado)
VALUES

(1,'MÉXICO','CONCACAF','A',15,'https://flagcdn.com/w320/mx.png',1),
(2,'SUDÁFRICA','CAF','A',61,'https://flagcdn.com/w320/za.png',1),
(3,'COREA DEL SUR','AFC','A',23,'https://flagcdn.com/w320/kr.png',1),
(4,'REPÚBLICA CHECA','UEFA','A',42,'https://flagcdn.com/w320/cz.png',1),

(5,'CANADÁ','CONCACAF','B',30,'https://flagcdn.com/w320/ca.png',1),
(6,'BOSNIA Y HERZEGOVINA','UEFA','B',72,'https://flagcdn.com/w320/ba.png',1),
(7,'QATAR','AFC','B',55,'https://flagcdn.com/w320/qa.png',1),
(8,'SUIZA','UEFA','B',19,'https://flagcdn.com/w320/ch.png',1),

(9,'BRASIL','CONMEBOL','C',5,'https://flagcdn.com/w320/br.png',1),
(10,'MARRUECOS','CAF','C',12,'https://flagcdn.com/w320/ma.png',1),
(11,'HAITÍ','CONCACAF','C',83,'https://flagcdn.com/w320/ht.png',1),
(12,'ESCOCIA','UEFA','C',45,'https://flagcdn.com/w320/gb-sct.png',1),

(13,'ESTADOS UNIDOS','CONCACAF','D',16,'https://flagcdn.com/w320/us.png',1),
(14,'PARAGUAY','CONMEBOL','D',48,'https://flagcdn.com/w320/py.png',1),
(15,'AUSTRALIA','AFC','D',24,'https://flagcdn.com/w320/au.png',1),
(16,'TURQUÍA','UEFA','D',27,'https://flagcdn.com/w320/tr.png',1),

(17,'ALEMANIA','UEFA','E',10,'https://flagcdn.com/w320/de.png',1),
(18,'CURAZAO','CONCACAF','E',90,'https://flagcdn.com/w320/cw.png',1),
(19,'COSTA DE MARFIL','CAF','E',39,'https://flagcdn.com/w320/ci.png',1),
(20,'ECUADOR','CONMEBOL','E',25,'https://flagcdn.com/w320/ec.png',1),

(21,'JAPÓN','AFC','F',17,'https://flagcdn.com/w320/jp.png',1),
(22,'PAÍSES BAJOS','UEFA','F',7,'https://flagcdn.com/w320/nl.png',1),
(23,'SUECIA','UEFA','F',28,'https://flagcdn.com/w320/se.png',1),
(24,'TÚNEZ','CAF','F',41,'https://flagcdn.com/w320/tn.png',1),

(25,'BÉLGICA','UEFA','G',8,'https://flagcdn.com/w320/be.png',1),
(26,'EGIPTO','CAF','G',34,'https://flagcdn.com/w320/eg.png',1),
(27,'IRÁN','AFC','G',20,'https://flagcdn.com/w320/ir.png',1),
(28,'NUEVA ZELANDA','OFC','G',86,'https://flagcdn.com/w320/nz.png',1),

(29,'ESPAÑA','UEFA','H',3,'https://flagcdn.com/w320/es.png',1),
(30,'URUGUAY','CONMEBOL','H',11,'https://flagcdn.com/w320/uy.png',1),
(31,'ARABIA SAUDITA','AFC','H',56,'https://flagcdn.com/w320/sa.png',1),
(32,'CABO VERDE','CAF','H',71,'https://flagcdn.com/w320/cv.png',1),

(33,'FRANCIA','UEFA','I',2,'https://flagcdn.com/w320/fr.png',1),
(34,'NORUEGA','UEFA','I',38,'https://flagcdn.com/w320/no.png',1),
(35,'IRAK','AFC','I',58,'https://flagcdn.com/w320/iq.png',1),
(36,'SENEGAL','CAF','I',18,'https://flagcdn.com/w320/sn.png',1),

(37,'ARGENTINA','CONMEBOL','J',1,'https://flagcdn.com/w320/ar.png',1),
(38,'ARGELIA','CAF','J',37,'https://flagcdn.com/w320/dz.png',1),
(39,'AUSTRIA','UEFA','J',22,'https://flagcdn.com/w320/at.png',1),
(40,'JORDANIA','AFC','J',64,'https://flagcdn.com/w320/jo.png',1),

(41,'PORTUGAL','UEFA','K',6,'https://flagcdn.com/w320/pt.png',1),
(42,'COLOMBIA','CONMEBOL','K',14,'https://flagcdn.com/w320/co.png',1),
(43,'RD CONGO','CAF','K',57,'https://flagcdn.com/w320/cd.png',1),
(44,'UZBEKISTÁN','AFC','K',62,'https://flagcdn.com/w320/uz.png',1),

(45,'INGLATERRA','UEFA','L',4,'https://flagcdn.com/w320/gb-eng.png',1),
(46,'CROACIA','UEFA','L',13,'https://flagcdn.com/w320/hr.png',1),
(47,'GHANA','CAF','L',78,'https://flagcdn.com/w320/gh.png',1),
(48,'PANAMÁ','CONCACAF','L',43,'https://flagcdn.com/w320/pa.png',1);

DROP PROCEDURE IF EXISTS OBTENER_DIRECTOR_TECNICO_X_ID;
DELIMITER $$

CREATE PROCEDURE OBTENER_DIRECTOR_TECNICO_X_ID
(
    IN p_id_director_tecnico INT
)
BEGIN

    SELECT
        id_director_tecnico,
        nombre,
        nacionalidad,
        edad
    FROM director_tecnico
    WHERE id_director_tecnico = p_id_director_tecnico;

END $$

DELIMITER ;

DROP PROCEDURE IF EXISTS LISTAR_SELECCIONES;
DELIMITER $$

CREATE PROCEDURE LISTAR_SELECCIONES()
BEGIN

    SELECT
        id_seleccion,
        fid_director_tecnico,
        nombre,
        confederacion,
        grupo,
        ranking_fifa,
        url_bandera,
        clasificado
    FROM seleccion
    ORDER BY grupo, nombre;

END $$

DELIMITER ;