<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tecnologías - FIFASoft 2026</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: #07111f;
            color: white;
        }

        .navbar {
            background: rgba(0, 0, 0, 0.78);
            backdrop-filter: blur(8px);
        }

        .navbar-brand {
            font-weight: 800;
            letter-spacing: 1px;
        }

        .navbar-brand span {
            color: #6cff3f;
        }

        .page-header {
            padding: 140px 0 70px 0;
            background:
                    linear-gradient(rgba(4, 10, 25, 0.82), rgba(4, 10, 25, 0.95)),
                    url('https://images.unsplash.com/photo-1515879218367-8466d910aaa4?q=80&w=1600');
            background-size: cover;
            background-position: center;
            text-align: center;
        }

        .page-header h1 {
            font-size: 4rem;
            font-weight: 900;
            text-transform: uppercase;
        }

        .page-header span {
            color: #6cff3f;
        }

        .section {
            padding: 70px 0;
        }

        .tech-card {
            background: rgba(255, 255, 255, 0.09);
            border: 1px solid rgba(255, 255, 255, 0.16);
            border-radius: 22px;
            padding: 28px;
            height: 100%;
            transition: 0.25s;
        }

        .tech-card:hover {
            transform: translateY(-6px);
            background: rgba(255, 255, 255, 0.14);
        }

        .tech-icon {
            font-size: 3rem;
            color: #6cff3f;
            margin-bottom: 15px;
        }

        .tech-card h4 {
            font-weight: 850;
            margin-bottom: 12px;
        }

        .tech-card p {
            color: #cbd5e1;
            margin-bottom: 0;
        }

        .flow-box {
            background: rgba(255, 255, 255, 0.08);
            border: 1px solid rgba(255,255,255,0.16);
            border-radius: 22px;
            padding: 24px;
            text-align: center;
            font-weight: 800;
        }

        .flow-arrow {
            font-size: 2rem;
            color: #6cff3f;
            margin: 10px 0;
        }

        .notice-box {
            background: rgba(108, 255, 63, 0.10);
            border: 1px solid rgba(108, 255, 63, 0.35);
            border-radius: 20px;
            padding: 25px;
            color: #e2e8f0;
        }

        footer {
            background: #030712;
            border-top: 1px solid rgba(255,255,255,0.12);
            color: #cbd5e1;
            padding: 28px 0;
        }

        @media (max-width: 768px) {
            .page-header h1 {
                font-size: 2.6rem;
            }
        }
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">
            <i class="bi bi-trophy-fill"></i> FIFA<span>Soft</span> 2026
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menuPrincipal">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="menuPrincipal">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
                <li class="nav-item"><a class="nav-link" href="selecciones.jsp">Selecciones</a></li>
                <li class="nav-item"><a class="nav-link" href="grupos.jsp">Grupos</a></li>
                <li class="nav-item"><a class="nav-link active" href="tecnologias.jsp">Tecnologías</a></li>
                <li class="nav-item"><a class="nav-link" href="acerca.jsp">Acerca de</a></li>
            </ul>
        </div>
    </div>
</nav>

<header class="page-header">
    <div class="container">
        <h1>Tecnologías del <span>proyecto</span></h1>
        <p class="lead mt-3">
            Backend Java, servicios REST, base de datos MySQL y consumo desde C#.
        </p>
    </div>
</header>

<section class="section">
    <div class="container">

        <div class="notice-box mb-5">
            <h4><i class="bi bi-cpu-fill"></i> Arquitectura del laboratorio</h4>
            <p class="mb-0">
                El proyecto Java funciona como backend y expone servicios REST.
                La aplicación cliente desarrollada en C# con Blazor consumirá dichos servicios para mostrar el listado de selecciones.
            </p>
        </div>

        <div class="row g-4 mb-5">
            <div class="col-md-4">
                <div class="tech-card">
                    <div class="tech-icon"><i class="bi bi-cup-hot-fill"></i></div>
                    <h4>Java</h4>
                    <p>Lenguaje utilizado para implementar las capas de negocio, persistencia y servicios web.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="tech-card">
                    <div class="tech-icon"><i class="bi bi-cloud-arrow-up-fill"></i></div>
                    <h4>Jakarta REST</h4>
                    <p>Permite exponer endpoints REST para consultar la información de las selecciones.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="tech-card">
                    <div class="tech-icon"><i class="bi bi-server"></i></div>
                    <h4>GlassFish</h4>
                    <p>Servidor de aplicaciones utilizado para desplegar el proyecto web Java.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="tech-card">
                    <div class="tech-icon"><i class="bi bi-database-fill"></i></div>
                    <h4>MySQL</h4>
                    <p>Motor de base de datos relacional que almacena las selecciones y directores técnicos.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="tech-card">
                    <div class="tech-icon"><i class="bi bi-diagram-3-fill"></i></div>
                    <h4>Arquitectura por capas</h4>
                    <p>Separación entre presentación, servicios, negocio, persistencia y acceso a datos.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="tech-card">
                    <div class="tech-icon"><i class="bi bi-window-desktop"></i></div>
                    <h4>C# Blazor</h4>
                    <p>Aplicación cliente encargada de consumir el servicio REST y mostrar la información en una interfaz gráfica.</p>
                </div>
            </div>
        </div>

        <div class="text-center mb-5">
            <h2 class="fw-bold text-uppercase">Flujo de comunicación</h2>
            <p class="text-secondary">
                Relación entre la interfaz gráfica, el backend y la base de datos.
            </p>
        </div>

        <div class="row justify-content-center">
            <div class="col-lg-7">

                <div class="flow-box">
                    <i class="bi bi-window-desktop"></i><br>
                    Cliente C# Blazor
                </div>

                <div class="flow-arrow text-center">
                    <i class="bi bi-arrow-down"></i>
                </div>

                <div class="flow-box">
                    <i class="bi bi-cloud-arrow-up-fill"></i><br>
                    Servicio REST Java
                </div>

                <div class="flow-arrow text-center">
                    <i class="bi bi-arrow-down"></i>
                </div>

                <div class="flow-box">
                    <i class="bi bi-cpu-fill"></i><br>
                    Capa de negocio
                </div>

                <div class="flow-arrow text-center">
                    <i class="bi bi-arrow-down"></i>
                </div>

                <div class="flow-box">
                    <i class="bi bi-folder-symlink-fill"></i><br>
                    Capa de persistencia
                </div>

                <div class="flow-arrow text-center">
                    <i class="bi bi-arrow-down"></i>
                </div>

                <div class="flow-box">
                    <i class="bi bi-database-fill"></i><br>
                    Base de datos MySQL
                </div>

            </div>
        </div>

    </div>
</section>

<footer>
    <div class="container text-center">
        <strong>Pontificia Universidad Católica del Perú</strong><br>
        Facultad de Ciencias e Ingeniería<br>
        Programación 3 (1INF30) - Laboratorio 14
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>