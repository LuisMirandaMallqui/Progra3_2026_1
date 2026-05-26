-- crea el esquema reniec
use reniec;

CREATE TABLE REG_PERSONAS (
  DNI CHAR(8) NOT NULL,
  PATERNO VARCHAR(45) NOT NULL,
  MATERNO VARCHAR(45) NOT NULL,
  NOMBRES VARCHAR(45) NOT NULL,
  PRIMARY KEY (DNI)
);


delimiter // 
create procedure insertar_persona(
	in p_dni char(8),
    in p_paterno varchar(45),
    in p_materno varchar(45),
	in p_nombres varchar(45)
)
begin
	insert into REG_PERSONAs(DNI, PATERNO, MATERNO, NOMBRES)
    values (p_dni,p_paterno,p_materno,p_nombres);
end //

create procedure obtenerPersona(
	in p_dni char(8)
)
begin
	select * 
    from REG_PERSONAS
    where p_dni = DNI;
end //