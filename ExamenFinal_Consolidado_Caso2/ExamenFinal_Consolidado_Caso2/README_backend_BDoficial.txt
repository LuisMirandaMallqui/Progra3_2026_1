CASO 2 - Actualizado según la BD oficial de matrícula que enviaste.

Cambios principales frente a la plantilla anterior:
1. Se usa schemaCaso2, porque el DML oficial trae USE schemaCaso2.
2. Se respetan los nombres reales de tablas y columnas:
   - UNI_AULAS usa UNI_CODIGO_AULA, UNI_UBICACION y UNI_ACTIVO.
   - UNI_HORARIOS_CURSO_DET usa DIA_SEMANA numérico y HORA_INICIO/HORA_FIN como DATETIME.
   - UNI_MATRICULAS usa FECHA_INSCRIPCION DATETIME.
3. Se respetan claves compuestas:
   - UNI_CURSOS_PRERREQUISITOS: CURSO_ID + CURSO_PRERREQ_ID.
   - UNI_MATRICULAS_HORARIOS: MATRICULA_ID + HORARIO_CURSO_ID.
4. Para tablas con ID autoincremental, los procedimientos devuelven el ID generado.
5. Para tablas con ID manual, el backend envía el ID y el procedimiento lo inserta.
6. Se agregaron procedimientos especiales del caso:
   - CONTAR_MATRICULADOS_HORARIO
   - EXISTE_CONFLICTO_HORARIO
   - REGISTRAR_MATRICULA_HORARIO_VALIDADO
   - CALCULAR_PROMEDIO_FINAL

Orden recomendado:
1. Ejecuta tu DROP oficial.
2. Crea la BD:
   CREATE DATABASE schemaCaso2;
   USE schemaCaso2;
3. Ejecuta el DDL oficial.
4. Ejecuta el DML oficial.
5. Ejecuta Scripts_SQL/01_PROCEDURES_Caso2_Matricula_schemaCaso2.sql.
6. Revisa UniversidadExam/DBManager/src/main/resources/db.properties.
7. Maven clean install package.
8. Despliega RestServices/target/RestServicesCaso2.war.
9. Prueba http://localhost:8080/RestServicesCaso2/webresources/test/ping.
