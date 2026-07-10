<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Grupos - FIFASoft 2026</title>

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
                    url('https://images.unsplash.com/photo-1431324155629-1a6deb1dec8d?q=80&w=1600');
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

        .group-card {
            background: rgba(255, 255, 255, 0.09);
            border: 1px solid rgba(255, 255, 255, 0.16);
            border-radius: 24px;
            padding: 24px;
            height: 100%;
            transition: 0.25s;
            overflow: hidden;
            position: relative;
        }

        .group-card:hover {
            transform: translateY(-6px);
            background: rgba(255, 255, 255, 0.14);
        }

        .group-letter {
            width: 58px;
            height: 58px;
            border-radius: 50%;
            background: #6cff3f;
            color: #07111f;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.9rem;
            font-weight: 900;
            margin-bottom: 18px;
        }

        .group-card h3 {
            font-weight: 850;
            margin-bottom: 18px;
        }

        .team-row {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 10px 0;
            border-bottom: 1px solid rgba(255,255,255,0.10);
        }

        .team-row:last-child {
            border-bottom: none;
        }

        .team-row img {
            width: 42px;
            height: 28px;
            object-fit: cover;
            border-radius: 4px;
            border: 1px solid rgba(255,255,255,0.25);
        }

        .team-name {
            font-weight: 700;
            font-size: 0.95rem;
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
                <li class="nav-item"><a class="nav-link active" href="grupos.jsp">Grupos</a></li>
                <li class="nav-item"><a class="nav-link" href="tecnologias.jsp">Tecnologías</a></li>
                <li class="nav-item"><a class="nav-link" href="acerca.jsp">Acerca de</a></li>
            </ul>
        </div>
    </div>
</nav>

<header class="page-header">
    <div class="container">
        <h1>Grupos <span>Mundial 2026</span></h1>
        <p class="lead mt-3">
            Distribución referencial de selecciones para el Laboratorio 14.
        </p>
    </div>
</header>

<section class="section">
    <div class="container">

        <div class="notice-box mb-5">
            <h4><i class="bi bi-diagram-3-fill"></i> Organización por grupos</h4>
            <p class="mb-0">
                Esta página muestra los grupos A-L utilizados como datos de prueba del backend Java.
                El cliente en C# consumirá la información real desde los servicios REST expuestos por el proyecto.
            </p>
        </div>

        <div class="row g-4">

            <%
                String[][] grupos = {
                        {"A","MÉXICO","https://flagcdn.com/w320/mx.png","SUDÁFRICA","https://flagcdn.com/w320/za.png","COREA DEL SUR","https://flagcdn.com/w320/kr.png","REPÚBLICA CHECA","https://flagcdn.com/w320/cz.png"},
                        {"B","CANADÁ","https://flagcdn.com/w320/ca.png","BOSNIA Y HERZEGOVINA","https://flagcdn.com/w320/ba.png","QATAR","https://flagcdn.com/w320/qa.png","SUIZA","https://flagcdn.com/w320/ch.png"},
                        {"C","BRASIL","https://flagcdn.com/w320/br.png","MARRUECOS","https://flagcdn.com/w320/ma.png","HAITÍ","https://flagcdn.com/w320/ht.png","ESCOCIA","https://flagcdn.com/w320/gb-sct.png"},
                        {"D","ESTADOS UNIDOS","https://flagcdn.com/w320/us.png","PARAGUAY","https://flagcdn.com/w320/py.png","AUSTRALIA","https://flagcdn.com/w320/au.png","TURQUÍA","https://flagcdn.com/w320/tr.png"},
                        {"E","ALEMANIA","https://flagcdn.com/w320/de.png","CURAZAO","https://flagcdn.com/w320/cw.png","COSTA DE MARFIL","https://flagcdn.com/w320/ci.png","ECUADOR","https://flagcdn.com/w320/ec.png"},
                        {"F","JAPÓN","https://flagcdn.com/w320/jp.png","PAÍSES BAJOS","https://flagcdn.com/w320/nl.png","SUECIA","https://flagcdn.com/w320/se.png","TÚNEZ","https://flagcdn.com/w320/tn.png"},
                        {"G","BÉLGICA","https://flagcdn.com/w320/be.png","EGIPTO","https://flagcdn.com/w320/eg.png","IRÁN","https://flagcdn.com/w320/ir.png","NUEVA ZELANDA","https://flagcdn.com/w320/nz.png"},
                        {"H","ESPAÑA","https://flagcdn.com/w320/es.png","URUGUAY","https://flagcdn.com/w320/uy.png","ARABIA SAUDITA","https://flagcdn.com/w320/sa.png","CABO VERDE","https://flagcdn.com/w320/cv.png"},
                        {"I","FRANCIA","https://flagcdn.com/w320/fr.png","NORUEGA","https://flagcdn.com/w320/no.png","IRAK","https://flagcdn.com/w320/iq.png","SENEGAL","https://flagcdn.com/w320/sn.png"},
                        {"J","ARGENTINA","https://flagcdn.com/w320/ar.png","ARGELIA","https://flagcdn.com/w320/dz.png","AUSTRIA","https://flagcdn.com/w320/at.png","JORDANIA","https://flagcdn.com/w320/jo.png"},
                        {"K","PORTUGAL","https://flagcdn.com/w320/pt.png","COLOMBIA","https://flagcdn.com/w320/co.png","RD CONGO","https://flagcdn.com/w320/cd.png","UZBEKISTÁN","https://flagcdn.com/w320/uz.png"},
                        {"L","INGLATERRA","https://flagcdn.com/w320/gb-eng.png","CROACIA","https://flagcdn.com/w320/hr.png","GHANA","https://flagcdn.com/w320/gh.png","PANAMÁ","https://flagcdn.com/w320/pa.png"}
                };

                for (int i = 0; i < grupos.length; i++) {
            %>

            <div class="col-md-6 col-lg-4">
                <div class="group-card">
                    <div class="group-letter"><%= grupos[i][0] %></div>
                    <h3>Grupo <%= grupos[i][0] %></h3>

                    <div class="team-row">
                        <img src="<%= grupos[i][2] %>" alt="<%= grupos[i][1] %>">
                        <span class="team-name"><%= grupos[i][1] %></span>
                    </div>

                    <div class="team-row">
                        <img src="<%= grupos[i][4] %>" alt="<%= grupos[i][3] %>">
                        <span class="team-name"><%= grupos[i][3] %></span>
                    </div>

                    <div class="team-row">
                        <img src="<%= grupos[i][6] %>" alt="<%= grupos[i][5] %>">
                        <span class="team-name"><%= grupos[i][5] %></span>
                    </div>

                    <div class="team-row">
                        <img src="<%= grupos[i][8] %>" alt="<%= grupos[i][7] %>">
                        <span class="team-name"><%= grupos[i][7] %></span>
                    </div>
                </div>
            </div>

            <%
                }
            %>

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