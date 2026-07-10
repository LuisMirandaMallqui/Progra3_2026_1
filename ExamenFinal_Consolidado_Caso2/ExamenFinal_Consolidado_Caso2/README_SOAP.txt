CASO 2 - Universidad con SOAP

Se agregó el módulo UniversidadExam/SoapServices con servicios SOAP para:

- FacultadWS
- EspecialidadWS
- EstudianteWS
- DepartamentoWS
- DocenteWS
- AulaWS
- CursoWS
- CursoPrerrequisitoWS
- HorarioCursoWS
- HorarioCursoDetWS
- MatriculaWS
- MatriculaHorarioWS
- EvaluacionWS
- NotaWS
- TestBDWS

1) Compilación backend
Desde UniversidadExam ejecuta:

    mvn clean package

Luego despliega:

    UniversidadExam/SoapServices/target/SoapServicesCaso2.war

2) WSDL para Visual Studio
Si el WAR se despliega como SoapServicesCaso2, usa estas URLs:

http://localhost:8080/SoapServicesCaso2/FacultadWS?wsdl
http://localhost:8080/SoapServicesCaso2/EspecialidadWS?wsdl
http://localhost:8080/SoapServicesCaso2/EstudianteWS?wsdl
http://localhost:8080/SoapServicesCaso2/DepartamentoWS?wsdl
http://localhost:8080/SoapServicesCaso2/DocenteWS?wsdl
http://localhost:8080/SoapServicesCaso2/AulaWS?wsdl
http://localhost:8080/SoapServicesCaso2/CursoWS?wsdl
http://localhost:8080/SoapServicesCaso2/CursoPrerrequisitoWS?wsdl
http://localhost:8080/SoapServicesCaso2/HorarioCursoWS?wsdl
http://localhost:8080/SoapServicesCaso2/HorarioCursoDetWS?wsdl
http://localhost:8080/SoapServicesCaso2/MatriculaWS?wsdl
http://localhost:8080/SoapServicesCaso2/MatriculaHorarioWS?wsdl
http://localhost:8080/SoapServicesCaso2/EvaluacionWS?wsdl
http://localhost:8080/SoapServicesCaso2/NotaWS?wsdl
http://localhost:8080/SoapServicesCaso2/TestBDWS?wsdl

3) Nombres de Connected Services esperados en el frontend

FacultadWSReference
EspecialidadWSReference
EstudianteWSReference
DepartamentoWSReference
DocenteWSReference
AulaWSReference
CursoWSReference
CursoPrerrequisitoWSReference
HorarioCursoWSReference
HorarioCursoDetWSReference
MatriculaWSReference
MatriculaHorarioWSReference
EvaluacionWSReference
NotaWSReference

4) Frontend
Se agregó:

Caso2FrontCursos/Caso2FrontCursos/Managers/UniversidadSOAPManager.cs

Y se modificó:

Caso2FrontCursos/Caso2FrontCursos/Program.cs
Caso2FrontCursos/Caso2FrontCursos/Components/Pages/Universidad.razor

La página ahora inyecta UniversidadSOAPManager en vez de UniversidadRSManager.

5) Nota
REST sigue en el proyecto, pero el frontend de Universidad está preparado para SOAP.
