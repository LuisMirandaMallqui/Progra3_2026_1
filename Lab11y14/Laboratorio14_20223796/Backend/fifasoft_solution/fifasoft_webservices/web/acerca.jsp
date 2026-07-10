<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acerca de - FIFASoft 2026</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <style>

        body{
            margin:0;
            font-family:'Segoe UI',sans-serif;
            background:#07111f;
            color:white;
        }

        .navbar{
            background:rgba(0,0,0,.78);
            backdrop-filter:blur(8px);
        }

        .navbar-brand{
            font-weight:800;
        }

        .navbar-brand span{
            color:#6cff3f;
        }

        .page-header{
            padding:140px 0 70px 0;
            background:
                    linear-gradient(rgba(4,10,25,.82),rgba(4,10,25,.95)),
                    url('https://images.unsplash.com/photo-1508098682722-e99c643e7485?q=80&w=1600');
            background-size:cover;
            background-position:center;
            text-align:center;
        }

        .page-header h1{
            font-size:4rem;
            font-weight:900;
            text-transform:uppercase;
        }

        .page-header span{
            color:#6cff3f;
        }

        .section{
            padding:70px 0;
        }

        .glass{
            background:rgba(255,255,255,.08);
            border:1px solid rgba(255,255,255,.15);
            border-radius:22px;
            padding:35px;
            backdrop-filter:blur(10px);
            margin-bottom:30px;
        }

        .glass h3{
            color:#6cff3f;
            font-weight:800;
            margin-bottom:20px;
        }

        .icon{
            font-size:45px;
            color:#6cff3f;
            margin-bottom:15px;
        }

        .feature{
            background:rgba(255,255,255,.08);
            border-radius:18px;
            padding:25px;
            text-align:center;
            height:100%;
            transition:.25s;
        }

        .feature:hover{
            transform:translateY(-5px);
            background:rgba(255,255,255,.12);
        }

        .feature i{
            font-size:45px;
            color:#6cff3f;
            margin-bottom:15px;
        }

        .timeline{
            border-left:4px solid #6cff3f;
            margin-left:15px;
            padding-left:30px;
        }

        .timeline div{
            margin-bottom:35px;
        }

        .timeline h5{
            color:#6cff3f;
            font-weight:700;
        }

        footer{
            background:#030712;
            border-top:1px solid rgba(255,255,255,.15);
            padding:25px;
            text-align:center;
            color:#cbd5e1;
        }

        @media(max-width:768px){

            .page-header h1{
                font-size:2.7rem;
            }

        }

    </style>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark fixed-top">

    <div class="container">

        <a class="navbar-brand" href="index.jsp">
            <i class="bi bi-trophy-fill"></i>
            FIFA<span>Soft</span> 2026
        </a>

        <button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#menu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="menu">

            <ul class="navbar-nav ms-auto">

                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Inicio</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="selecciones.jsp">Selecciones</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="grupos.jsp">Grupos</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="tecnologias.jsp">Tecnologías</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" href="acerca.jsp">Acerca de</a>
                </li>

            </ul>

        </div>

    </div>

</nav>

<header class="page-header">

    <div class="container">

        <h1>Acerca del <span>Proyecto</span></h1>

        <p class="lead mt-3">
            Backend Java para el Laboratorio 14 del curso Programación 3.
        </p>

    </div>

</header>

<section class="section">

    <div class="container">

        <div class="glass">

            <div class="icon">
                <i class="bi bi-info-circle-fill"></i>
            </div>

            <h3>¿Qué es FIFASoft 2026?</h3>

            <p>

                FIFASoft 2026 es un proyecto académico desarrollado como caso de estudio
                para el Laboratorio 14 del curso <strong>Programación 3 (1INF30)</strong>.

                El objetivo consiste en implementar un backend utilizando Java,
                arquitectura por capas y servicios web REST que posteriormente serán
                consumidos desde una aplicación cliente desarrollada en C# utilizando
                Blazor.

            </p>

        </div>

        <div class="row g-4">

            <div class="col-md-4">

                <div class="feature">

                    <i class="bi bi-layers-fill"></i>

                    <h4>Arquitectura</h4>

                    <p>

                        Separación en capas de presentación,
                        negocio y persistencia para favorecer
                        la reutilización del código.

                    </p>

                </div>

            </div>

            <div class="col-md-4">

                <div class="feature">

                    <i class="bi bi-cloud-arrow-up-fill"></i>

                    <h4>Servicios REST</h4>

                    <p>

                        Implementación de endpoints REST para
                        consultar la información de las
                        selecciones participantes.

                    </p>

                </div>

            </div>

            <div class="col-md-4">

                <div class="feature">

                    <i class="bi bi-window-desktop"></i>

                    <h4>Cliente Blazor</h4>

                    <p>

                        Aplicación desarrollada en C#
                        encargada de consumir el backend
                        y mostrar la información al usuario.

                    </p>

                </div>

            </div>

        </div>

        <div class="glass mt-5">

            <h3>Flujo de desarrollo del laboratorio</h3>

            <div class="timeline">

                <div>

                    <h5>1. Base de datos</h5>

                    Diseño de tablas, relaciones y procedimientos almacenados.

                </div>

                <div>

                    <h5>2. Persistencia</h5>

                    Implementación de las operaciones de acceso a datos.

                </div>

                <div>

                    <h5>3. Negocio</h5>

                    Implementación de la lógica de negocio del sistema.

                </div>

                <div>

                    <h5>4. Servicios REST</h5>

                    Exposición de los servicios web utilizando Jakarta REST.

                </div>

                <div>

                    <h5>5. Cliente C#</h5>

                    Consumo de los servicios REST desde Blazor.

                </div>

            </div>

        </div>

        <div class="glass">

            <h3>Información del laboratorio</h3>

            <table class="table table-dark table-striped table-bordered align-middle">

                <tr>

                    <th width="35%">Curso</th>

                    <td>Programación 3 (1INF30)</td>

                </tr>

                <tr>

                    <th>Laboratorio</th>

                    <td>Laboratorio 14</td>

                </tr>

                <tr>

                    <th>Tema</th>

                    <td>Servicios REST y consumo desde C#</td>

                </tr>

                <tr>

                    <th>Backend</th>

                    <td>Java + Jakarta EE + GlassFish</td>

                </tr>

                <tr>

                    <th>Frontend</th>

                    <td>Blazor (.NET)</td>

                </tr>

                <tr>

                    <th>Base de Datos</th>

                    <td>MySQL</td>

                </tr>

                <tr>

                    <th>Universidad</th>

                    <td>Pontificia Universidad Católica del Perú</td>

                </tr>

            </table>

        </div>

    </div>

</section>

<footer>

    <strong>Pontificia Universidad Católica del Perú</strong><br>

    Facultad de Ciencias e Ingeniería<br>

    Programación 3 (1INF30)<br>

    Laboratorio 14 - FIFASoft 2026

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>