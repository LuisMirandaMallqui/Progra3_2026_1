create database if not exists testsoft;
use testsoft;

drop table if exists examen_pregunta;
drop table if exists examen;
drop table if exists pregunta;
drop table if exists alumno;

create table alumno (
    id int auto_increment primary key,
    codigo varchar(20) not null unique,
    nombre varchar(100) not null,
    correo varchar(120) not null unique
);

create table pregunta (
    id int auto_increment primary key,
    enunciado varchar(300) not null
);

create table examen (
    id int auto_increment primary key,
    id_alumno int not null,
    titulo varchar(120) not null,
    fecha_creacion datetime not null default current_timestamp,
    constraint fk_examen_alumno foreign key (id_alumno) references alumno(id)
);

create table examen_pregunta (
    id int auto_increment primary key,
    id_examen int not null,
    id_pregunta int not null,
    orden int not null,
    constraint fk_examen_pregunta_examen foreign key (id_examen) references examen(id),
    constraint fk_examen_pregunta_pregunta foreign key (id_pregunta) references pregunta(id),
    constraint uq_examen_pregunta unique (id_examen, id_pregunta),
    constraint uq_examen_orden unique (id_examen, orden)
);

-- DROPS DE TODOS LOS SPs
drop procedure if exists insertar_alumno;
drop procedure if exists insertar_pregunta;
drop procedure if exists insertar_examen;
drop procedure if exists insertar_examen_pregunta;
drop procedure if exists buscar_alumno_por_id;
drop procedure if exists buscar_alumno_por_codigo;
drop procedure if exists listar_alumnos;
drop procedure if exists listar_preguntas;
drop procedure if exists buscar_examen_por_id;
drop procedure if exists listar_examenes;
drop procedure if exists listar_preguntas_por_examen;
drop procedure if exists eliminar_alumno;
drop procedure if exists eliminar_pregunta;
drop procedure if exists eliminar_examen;
drop procedure if exists modificar_alumno;
drop procedure if exists modificar_pregunta;
drop procedure if exists modificar_examen;

delimiter //

-- =============================================
-- CREAR (INSERT)
-- =============================================

-- Alumno: INSERT con OUT para devolver el id generado
create procedure insertar_alumno(
    in p_codigo varchar(20),
    in p_nombre varchar(100),
    in p_correo varchar(120),
    out p_id int
)
begin
    insert into alumno(codigo, nombre, correo)
    values(p_codigo, p_nombre, p_correo);
    set p_id = last_insert_id();
end //

-- Pregunta: INSERT con OUT
create procedure insertar_pregunta(
    in p_enunciado varchar(300),
    out p_id int
)
begin
    insert into pregunta(enunciado)
    values(p_enunciado);
    set p_id = last_insert_id();
end //

-- Examen: INSERT cabecera (se usa dentro de transaccion)
create procedure insertar_examen(
    in p_id_alumno int,
    in p_titulo varchar(120),
    out p_id int
)
begin
    insert into examen(id_alumno, titulo)
    values(p_id_alumno, p_titulo);
    set p_id = last_insert_id();
end //

-- ExamenPregunta: INSERT detalle (tabla puente, se usa dentro de transaccion)
create procedure insertar_examen_pregunta(
    in p_id_examen int,
    in p_id_pregunta int,
    in p_orden int,
    out p_id int
)
begin
    insert into examen_pregunta(id_examen, id_pregunta, orden)
    values(p_id_examen, p_id_pregunta, p_orden);
    set p_id = last_insert_id();
end //

-- =============================================
-- LEER (SELECT)
-- =============================================

-- Alumno: buscar por id (retorna ResultSet)
create procedure buscar_alumno_por_id(in p_id int)
begin
    select id, codigo, nombre, correo
    from alumno
    where id = p_id;
end //

-- *** PATRON SELECT INTO ***
-- Alumno: buscar por codigo con SELECT INTO (retorna id via OUT, NO ResultSet)
-- Igual al buscar_tipo_pokemon_por_nombre del Lab7
-- Uso: verificar si un alumno ya existe antes de insertar (evitar duplicados)
create procedure buscar_alumno_por_codigo(
    in p_codigo varchar(20),
    out p_id int
)
begin
    set p_id = null;
    select id into p_id
    from alumno
    where codigo = p_codigo;
end //

-- Alumno: listar todos
create procedure listar_alumnos()
begin
    select id, codigo, nombre, correo
    from alumno
    order by id;
end //

-- Pregunta: listar todas
create procedure listar_preguntas()
begin
    select id, enunciado
    from pregunta
    order by id;
end //

-- Examen: buscar por id
create procedure buscar_examen_por_id(in p_id int)
begin
    select id, id_alumno, titulo, fecha_creacion
    from examen
    where id = p_id;
end //

-- Examen: listar todos
create procedure listar_examenes()
begin
    select id, id_alumno, titulo, fecha_creacion
    from examen
    order by id;
end //

-- ExamenPregunta: listar preguntas de un examen (JOIN)
create procedure listar_preguntas_por_examen(in p_id_examen int)
begin
    select p.id, p.enunciado
    from examen_pregunta ep
    inner join pregunta p on p.id = ep.id_pregunta
    where ep.id_examen = p_id_examen
    order by ep.orden;
end //

-- =============================================
-- MODIFICAR (UPDATE)
-- =============================================

create procedure modificar_alumno(
    in p_id int,
    in p_codigo varchar(20),
    in p_nombre varchar(100),
    in p_correo varchar(120)
)
begin
    update alumno
    set codigo = p_codigo, nombre = p_nombre, correo = p_correo
    where id = p_id;
end //

create procedure modificar_pregunta(
    in p_id int,
    in p_enunciado varchar(300)
)
begin
    update pregunta
    set enunciado = p_enunciado
    where id = p_id;
end //

create procedure modificar_examen(
    in p_id int,
    in p_titulo varchar(120)
)
begin
    update examen
    set titulo = p_titulo
    where id = p_id;
end //

-- =============================================
-- ELIMINAR (DELETE)
-- =============================================

create procedure eliminar_alumno(in p_id int)
begin
    delete from alumno where id = p_id;
end //

create procedure eliminar_pregunta(in p_id int)
begin
    delete from pregunta where id = p_id;
end //

-- Examen: elimina primero los detalles (tabla puente) y luego la cabecera
create procedure eliminar_examen(in p_id int)
begin
    delete from examen_pregunta where id_examen = p_id;
    delete from examen where id = p_id;
end //

delimiter ;

-- =============================================
-- DATOS DE PRUEBA
-- =============================================

insert into alumno(codigo, nombre, correo) values
('2026001', 'Alumno 1', 'alumno1@testsoft.edu.pe'),
('2026002', 'Alumno 2', 'alumno2@testsoft.edu.pe'),
('2026003', 'Alumno 3', 'alumno3@testsoft.edu.pe'),
('2026004', 'Alumno 4', 'alumno4@testsoft.edu.pe'),
('2026005', 'Alumno 5', 'alumno5@testsoft.edu.pe'),
('2026006', 'Alumno 6', 'alumno6@testsoft.edu.pe'),
('2026007', 'Alumno 7', 'alumno7@testsoft.edu.pe'),
('2026008', 'Alumno 8', 'alumno8@testsoft.edu.pe'),
('2026009', 'Alumno 9', 'alumno9@testsoft.edu.pe'),
('2026010', 'Alumno 10', 'alumno10@testsoft.edu.pe'),
('2026011', 'Alumno 11', 'alumno11@testsoft.edu.pe'),
('2026012', 'Alumno 12', 'alumno12@testsoft.edu.pe'),
('2026013', 'Alumno 13', 'alumno13@testsoft.edu.pe'),
('2026014', 'Alumno 14', 'alumno14@testsoft.edu.pe'),
('2026015', 'Alumno 15', 'alumno15@testsoft.edu.pe'),
('2026016', 'Alumno 16', 'alumno16@testsoft.edu.pe'),
('2026017', 'Alumno 17', 'alumno17@testsoft.edu.pe'),
('2026018', 'Alumno 18', 'alumno18@testsoft.edu.pe'),
('2026019', 'Alumno 19', 'alumno19@testsoft.edu.pe'),
('2026020', 'Alumno 20', 'alumno20@testsoft.edu.pe');

insert into pregunta(enunciado) values
('Que patron de diseno permite crear familias de objetos relacionados?'),
('Cual es la diferencia entre una interfaz y una clase abstracta?'),
('Que es una transaccion en base de datos?'),
('Para que se utiliza commit en SQL?'),
('Que problema resuelve el patron singleton?'),
('Que es inyeccion de dependencias?'),
('Que caracteriza a una relacion muchos a muchos?'),
('Que es normalizacion en una base de datos relacional?'),
('Cual es la complejidad de busqueda binaria?'),
('Que es polimorfismo en orientacion a objetos?'),
('Que hace la clausula group by?'),
('Que diferencia existe entre where y having?'),
('Que es una foreign key?'),
('Que patron permite recorrer colecciones sin exponer su estructura?'),
('Que es acoplamiento en arquitectura de software?'),
('Para que sirve una prueba unitaria?'),
('Que es mocking en pruebas?'),
('Que es una excepcion checked en Java?'),
('Para que se usa try with resources?'),
('Que es sobrecarga de metodos?'),
('Que es sobreescritura de metodos?'),
('Que representa una API REST?'),
('Que codigo HTTP representa creado?'),
('Que es un indice en una tabla SQL?'),
('Que diferencia hay entre delete y truncate?'),
('Que es consistencia en ACID?'),
('Que es aislamiento en ACID?'),
('Que hace un LEFT JOIN?'),
('Que hace un INNER JOIN?'),
('Que es refactorizacion?'),
('Para que sirve maven en Java?'),
('Que es una dependencia transitiva en maven?'),
('Que es cohesion alta en diseno?'),
('Que es una capa de persistencia?'),
('Que es una capa de negocio?'),
('Que es una capa de presentacion?'),
('Que es encapsulamiento?'),
('Que ventaja tiene usar procedimientos almacenados?'),
('Que es un deadlock en bases de datos?'),
('Que significa idempotencia en servicios web?');
