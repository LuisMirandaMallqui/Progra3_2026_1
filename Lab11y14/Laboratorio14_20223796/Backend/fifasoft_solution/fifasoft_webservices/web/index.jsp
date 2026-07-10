<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>FIFASoft - Laboratorio 14</title>

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
            background: rgba(0, 0, 0, 0.75);
            backdrop-filter: blur(8px);
        }

        .navbar-brand {
            font-weight: 800;
            letter-spacing: 1px;
        }

        .navbar-brand span {
            color: #6cff3f;
        }

        .hero {
            min-height: 100vh;
            background:
                    linear-gradient(rgba(4, 10, 25, 0.78), rgba(4, 10, 25, 0.92)),
                    url('https://images.unsplash.com/photo-1522778119026-d647f0596c20?q=80&w=1600');
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            padding-top: 80px;
        }

        .badge-worldcup {
            background: rgba(108, 255, 63, 0.15);
            color: #9dff7a;
            border: 1px solid rgba(108, 255, 63, 0.45);
            padding: 10px 18px;
            border-radius: 30px;
            font-weight: 600;
        }

        .hero-title {
            font-size: 4.6rem;
            font-weight: 900;
            line-height: 1.05;
            text-transform: uppercase;
        }

        .hero-title span {
            color: #6cff3f;
        }

        .hero-text {
            color: #e2e8f0;
            font-size: 1.25rem;
            max-width: 760px;
        }

        .btn-main {
            background: #28c840;
            border: none;
            color: white;
            padding: 14px 30px;
            border-radius: 14px;
            font-weight: 700;
            box-shadow: 0 10px 28px rgba(40, 200, 64, 0.35);
        }

        .btn-main:hover {
            background: #20a934;
            color: white;
        }

        .btn-outline-light {
            padding: 14px 30px;
            border-radius: 14px;
            font-weight: 700;
        }

        .glass-card {
            background: rgba(255, 255, 255, 0.09);
            border: 1px solid rgba(255, 255, 255, 0.18);
            border-radius: 22px;
            padding: 28px;
            backdrop-filter: blur(10px);
            height: 100%;
            transition: 0.25s;
        }

        .glass-card:hover {
            transform: translateY(-6px);
            background: rgba(255, 255, 255, 0.14);
        }

        .icon-box {
            font-size: 2.6rem;
            color: #6cff3f;
            margin-bottom: 12px;
        }

        .section {
            padding: 90px 0;
        }

        .section-title {
            font-size: 2.8rem;
            font-weight: 850;
            text-transform: uppercase;
        }

        .section-title span {
            color: #6cff3f;
        }

        .stat-number {
            font-size: 3.5rem;
            font-weight: 900;
            color: #6cff3f;
        }

        .host-card {
            border-radius: 24px;
            overflow: hidden;
            background: #101c2f;
            border: 1px solid rgba(255,255,255,0.14);
            height: 100%;
        }

        .host-flag {
            font-size: 4.5rem;
            padding-top: 25px;
        }

        .host-card h3 {
            font-weight: 800;
            margin-top: 8px;
        }

        .tech-pill {
            display: inline-block;
            margin: 8px;
            padding: 12px 22px;
            border-radius: 28px;
            background: rgba(255,255,255,0.10);
            border: 1px solid rgba(255,255,255,0.18);
            font-weight: 700;
        }

        footer {
            background: #030712;
            border-top: 1px solid rgba(255,255,255,0.12);
            color: #cbd5e1;
            padding: 28px 0;
        }

        @media (max-width: 768px) {
            .hero-title {
                font-size: 2.8rem;
            }

            .section-title {
                font-size: 2.1rem;
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
                <li class="nav-item"><a class="nav-link active" href="index.jsp">Inicio</a></li>
                <li class="nav-item"><a class="nav-link" href="selecciones.jsp">Selecciones</a></li>
                <li class="nav-item"><a class="nav-link" href="grupos.jsp">Grupos</a></li>
                <li class="nav-item"><a class="nav-link" href="tecnologias.jsp">Tecnologías</a></li>
                <li class="nav-item"><a class="nav-link" href="acerca.jsp">Acerca de</a></li>
            </ul>
        </div>
    </div>
</nav>

<section class="hero">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-8">

                <div class="mb-4">
                    <span class="badge-worldcup">
                        🇨🇦 🇺🇸 🇲🇽 Mundial de Fútbol 2026
                    </span>
                </div>

                <h1 class="hero-title mb-4">
                    Bienvenido al <span>Laboratorio 14</span>
                </h1>

                <h2 class="mb-4">
                    Programación 3 (1INF30)
                </h2>

                <p class="hero-text mb-5">
                    Proyecto backend en Java orientado a la implementación de servicios REST,
                    arquitectura por capas, conexión con MySQL y consumo desde una aplicación
                    cliente desarrollada en C# con Blazor.
                </p>

                <div class="d-flex flex-wrap gap-3">
                    <a href="selecciones.jsp" class="btn btn-main">
                        <i class="bi bi-flag-fill"></i> Ver selecciones
                    </a>

                    <a href="tecnologias.jsp" class="btn btn-outline-light">
                        <i class="bi bi-code-slash"></i> Ver tecnologías
                    </a>
                </div>

            </div>
        </div>
    </div>
</section>

<section class="section">
    <div class="container text-center">
        <h2 class="section-title mb-3">
            ¿Qué se trabaja en este <span>laboratorio?</span>
        </h2>

        <p class="text-secondary mb-5">
            El proyecto backend expone servicios REST que serán consumidos por una interfaz gráfica en C#.
        </p>

        <div class="row g-4">
            <div class="col-md-3">
                <div class="glass-card">
                    <div class="icon-box"><i class="bi bi-cloud-arrow-up-fill"></i></div>
                    <h4>REST</h4>
                    <p>Exposición de servicios web para listar información de selecciones.</p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="glass-card">
                    <div class="icon-box"><i class="bi bi-cup-hot-fill"></i></div>
                    <h4>Java</h4>
                    <p>Implementación del backend usando Java y Jakarta EE.</p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="glass-card">
                    <div class="icon-box"><i class="bi bi-database-fill"></i></div>
                    <h4>MySQL</h4>
                    <p>Consulta de datos desde una base de datos relacional.</p>
                </div>
            </div>

            <div class="col-md-3">
                <div class="glass-card">
                    <div class="icon-box"><i class="bi bi-window-desktop"></i></div>
                    <h4>C# Blazor</h4>
                    <p>Consumo del servicio REST desde una interfaz gráfica moderna.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="section bg-dark">
    <div class="container text-center">
        <h2 class="section-title mb-5">
            Mundial FIFA <span>2026</span>
        </h2>

        <div class="row g-4">
            <div class="col-md-3">
                <div class="glass-card">
                    <div class="stat-number">48</div>
                    <h5>Selecciones</h5>
                </div>
            </div>

            <div class="col-md-3">
                <div class="glass-card">
                    <div class="stat-number">12</div>
                    <h5>Grupos</h5>
                </div>
            </div>

            <div class="col-md-3">
                <div class="glass-card">
                    <div class="stat-number">104</div>
                    <h5>Partidos</h5>
                </div>
            </div>

            <div class="col-md-3">
                <div class="glass-card">
                    <div class="stat-number">3</div>
                    <h5>Países anfitriones</h5>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="section">
    <div class="container">
        <div class="text-center mb-5">
            <h2 class="section-title">
                Países <span>anfitriones</span>
            </h2>
            <p class="text-secondary">
                La Copa Mundial de Fútbol 2026 se desarrollará en Norteamérica.
            </p>
        </div>

        <div class="row g-4">
            <div class="col-md-4">
                <div class="host-card text-center p-4">
                    <div class="host-flag">🇨🇦</div>
                    <h3>Canadá</h3>
                    <p>Toronto y Vancouver serán sedes del torneo.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="host-card text-center p-4">
                    <div class="host-flag">🇺🇸</div>
                    <h3>Estados Unidos</h3>
                    <p>Contará con la mayor cantidad de ciudades anfitrionas.</p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="host-card text-center p-4">
                    <div class="host-flag">🇲🇽</div>
                    <h3>México</h3>
                    <p>Será uno de los países protagonistas del inicio del torneo.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="section bg-dark">
    <div class="container text-center">
        <h2 class="section-title mb-4">
            Tecnologías del <span>proyecto</span>
        </h2>

        <p class="text-secondary mb-4">
            El laboratorio integra tecnologías de backend, base de datos y cliente gráfico.
        </p>

        <div>
            <span class="tech-pill">Java</span>
            <span class="tech-pill">Jakarta REST</span>
            <span class="tech-pill">GlassFish</span>
            <span class="tech-pill">MySQL</span>
            <span class="tech-pill">Stored Procedures</span>
            <span class="tech-pill">C#</span>
            <span class="tech-pill">Blazor</span>
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