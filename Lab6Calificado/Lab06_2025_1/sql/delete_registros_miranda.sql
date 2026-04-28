SET FOREIGN_KEY_CHECKS = 0;
-- el LIMIT 99999 lo puse pq el workbecnh no deja hacer DELETE si no le pones una clausula como WHERE o similar al final
DELETE FROM lab06.citamedica LIMIT 999999;
DELETE FROM medico LIMIT 999999;
DELETE FROM paciente LIMIT 999999;
DELETE FROM tutor LIMIT 999999;

SET FOREIGN_KEY_CHECKS = 1;