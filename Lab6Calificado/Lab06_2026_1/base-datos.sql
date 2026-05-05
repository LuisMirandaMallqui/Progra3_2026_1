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

drop procedure if exists buscar_alumno_por_id;
drop procedure if exists listar_alumnos;
drop procedure if exists listar_preguntas;
drop procedure if exists insertar_examen;
drop procedure if exists listar_examenes;
drop procedure if exists insertar_examen_pregunta;
drop procedure if exists listar_preguntas_por_examen;

delimiter //

create procedure buscar_alumno_por_id(in p_id int)
begin
    select id, codigo, nombre, correo
    from alumno
    where id = p_id;
end //

create procedure listar_alumnos()
begin
    select id, codigo, nombre, correo
    from alumno
    order by id;
end //

create procedure listar_preguntas()
begin
    select id, enunciado
    from pregunta
    order by id;
end //

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

create procedure listar_examenes()
begin
    select id, id_alumno, titulo, fecha_creacion
    from examen
    order by id;
end //

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

create procedure listar_preguntas_por_examen(in p_id_examen int)
begin
    select p.id, p.enunciado
    from examen_pregunta ep
    inner join pregunta p on p.id = ep.id_pregunta
    where ep.id_examen = p_id_examen
    order by ep.orden;
end //

delimiter ;

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
