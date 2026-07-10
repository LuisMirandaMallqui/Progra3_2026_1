<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Selecciones - FIFASoft 2026</title>

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
              url('https://images.unsplash.com/photo-1517927033932-b3d18e61fb3a?q=80&w=1600');
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

    .notice-box {
      background: rgba(108, 255, 63, 0.10);
      border: 1px solid rgba(108, 255, 63, 0.35);
      border-radius: 20px;
      padding: 25px;
      color: #e2e8f0;
    }

    .endpoint-box {
      background: #020617;
      border: 1px solid rgba(255,255,255,0.14);
      border-radius: 16px;
      padding: 18px;
      font-family: Consolas, monospace;
      color: #9dff7a;
      overflow-x: auto;
    }

    .group-title {
      font-size: 1.5rem;
      font-weight: 850;
      color: #6cff3f;
      margin-bottom: 20px;
      border-left: 6px solid #6cff3f;
      padding-left: 12px;
    }

    .team-card {
      background: rgba(255, 255, 255, 0.09);
      border: 1px solid rgba(255, 255, 255, 0.16);
      border-radius: 20px;
      padding: 22px;
      height: 100%;
      text-align: center;
      transition: 0.25s;
    }

    .team-card:hover {
      transform: translateY(-5px);
      background: rgba(255, 255, 255, 0.14);
    }

    .team-card img {
      width: 105px;
      height: 70px;
      object-fit: cover;
      border-radius: 8px;
      border: 1px solid rgba(255,255,255,0.25);
      margin-bottom: 15px;
    }

    .team-card h5 {
      font-weight: 850;
      margin-bottom: 8px;
    }

    .team-card .confed {
      color: #cbd5e1;
      font-size: 0.9rem;
    }

    .team-card .rank {
      display: inline-block;
      margin-top: 8px;
      padding: 5px 12px;
      border-radius: 20px;
      background: rgba(108, 255, 63, 0.14);
      color: #9dff7a;
      font-weight: 700;
      font-size: 0.85rem;
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
        <li class="nav-item"><a class="nav-link active" href="selecciones.jsp">Selecciones</a></li>
        <li class="nav-item"><a class="nav-link" href="grupos.jsp">Grupos</a></li>
        <li class="nav-item"><a class="nav-link" href="tecnologias.jsp">Tecnologías</a></li>
        <li class="nav-item"><a class="nav-link" href="acerca.jsp">Acerca de</a></li>
      </ul>
    </div>
  </div>
</nav>

<header class="page-header">
  <div class="container">
    <h1>Selecciones <span>2026</span></h1>
    <p class="lead mt-3">
      Vista referencial del backend Java para el Laboratorio 14.
    </p>
  </div>
</header>

<section class="section">
  <div class="container">

    <div class="notice-box mb-5">
      <h4><i class="bi bi-info-circle-fill"></i> Importante</h4>
      <p class="mb-2">
        Esta página JSP es únicamente informativa dentro del backend Java.
        El listado real de selecciones deberá ser consumido desde la aplicación cliente desarrollada en C# con Blazor.
      </p>
      <p class="mb-0">
        El backend debe exponer un servicio REST que retorne las selecciones junto con su director técnico.
      </p>
    </div>

    <h4 class="mb-3">Endpoint REST sugerido</h4>

    <div class="endpoint-box mb-5">
      GET /webresources/selecciones/listar
    </div>

    <div class="text-center mb-5">
      <h2 class="fw-bold text-uppercase">Selecciones clasificadas</h2>
      <p class="text-secondary">
        Ejemplo visual de las 48 selecciones organizadas por grupo.
      </p>
    </div>

    <%
      String[][] selecciones = {
              {"A","MÉXICO","CONCACAF","15","https://flagcdn.com/w320/mx.png"},
              {"A","SUDÁFRICA","CAF","61","https://flagcdn.com/w320/za.png"},
              {"A","COREA DEL SUR","AFC","23","https://flagcdn.com/w320/kr.png"},
              {"A","REPÚBLICA CHECA","UEFA","42","https://flagcdn.com/w320/cz.png"},

              {"B","CANADÁ","CONCACAF","30","https://flagcdn.com/w320/ca.png"},
              {"B","BOSNIA Y HERZEGOVINA","UEFA","72","https://flagcdn.com/w320/ba.png"},
              {"B","QATAR","AFC","55","https://flagcdn.com/w320/qa.png"},
              {"B","SUIZA","UEFA","19","https://flagcdn.com/w320/ch.png"},

              {"C","BRASIL","CONMEBOL","5","https://flagcdn.com/w320/br.png"},
              {"C","MARRUECOS","CAF","12","https://flagcdn.com/w320/ma.png"},
              {"C","HAITÍ","CONCACAF","83","https://flagcdn.com/w320/ht.png"},
              {"C","ESCOCIA","UEFA","45","https://flagcdn.com/w320/gb-sct.png"},

              {"D","ESTADOS UNIDOS","CONCACAF","16","https://flagcdn.com/w320/us.png"},
              {"D","PARAGUAY","CONMEBOL","48","https://flagcdn.com/w320/py.png"},
              {"D","AUSTRALIA","AFC","24","https://flagcdn.com/w320/au.png"},
              {"D","TURQUÍA","UEFA","27","https://flagcdn.com/w320/tr.png"},

              {"E","ALEMANIA","UEFA","10","https://flagcdn.com/w320/de.png"},
              {"E","CURAZAO","CONCACAF","90","https://flagcdn.com/w320/cw.png"},
              {"E","COSTA DE MARFIL","CAF","39","https://flagcdn.com/w320/ci.png"},
              {"E","ECUADOR","CONMEBOL","25","https://flagcdn.com/w320/ec.png"},

              {"F","JAPÓN","AFC","17","https://flagcdn.com/w320/jp.png"},
              {"F","PAÍSES BAJOS","UEFA","7","https://flagcdn.com/w320/nl.png"},
              {"F","SUECIA","UEFA","28","https://flagcdn.com/w320/se.png"},
              {"F","TÚNEZ","CAF","41","https://flagcdn.com/w320/tn.png"},

              {"G","BÉLGICA","UEFA","8","https://flagcdn.com/w320/be.png"},
              {"G","EGIPTO","CAF","34","https://flagcdn.com/w320/eg.png"},
              {"G","IRÁN","AFC","20","https://flagcdn.com/w320/ir.png"},
              {"G","NUEVA ZELANDA","OFC","86","https://flagcdn.com/w320/nz.png"},

              {"H","ESPAÑA","UEFA","3","https://flagcdn.com/w320/es.png"},
              {"H","URUGUAY","CONMEBOL","11","https://flagcdn.com/w320/uy.png"},
              {"H","ARABIA SAUDITA","AFC","56","https://flagcdn.com/w320/sa.png"},
              {"H","CABO VERDE","CAF","71","https://flagcdn.com/w320/cv.png"},

              {"I","FRANCIA","UEFA","2","https://flagcdn.com/w320/fr.png"},
              {"I","NORUEGA","UEFA","38","https://flagcdn.com/w320/no.png"},
              {"I","IRAK","AFC","58","https://flagcdn.com/w320/iq.png"},
              {"I","SENEGAL","CAF","18","https://flagcdn.com/w320/sn.png"},

              {"J","ARGENTINA","CONMEBOL","1","https://flagcdn.com/w320/ar.png"},
              {"J","ARGELIA","CAF","37","https://flagcdn.com/w320/dz.png"},
              {"J","AUSTRIA","UEFA","22","https://flagcdn.com/w320/at.png"},
              {"J","JORDANIA","AFC","64","https://flagcdn.com/w320/jo.png"},

              {"K","PORTUGAL","UEFA","6","https://flagcdn.com/w320/pt.png"},
              {"K","COLOMBIA","CONMEBOL","14","https://flagcdn.com/w320/co.png"},
              {"K","RD CONGO","CAF","57","https://flagcdn.com/w320/cd.png"},
              {"K","UZBEKISTÁN","AFC","62","https://flagcdn.com/w320/uz.png"},

              {"L","INGLATERRA","UEFA","4","https://flagcdn.com/w320/gb-eng.png"},
              {"L","CROACIA","UEFA","13","https://flagcdn.com/w320/hr.png"},
              {"L","GHANA","CAF","78","https://flagcdn.com/w320/gh.png"},
              {"L","PANAMÁ","CONCACAF","43","https://flagcdn.com/w320/pa.png"}
      };

      String grupoActual = "";
      for (int i = 0; i < selecciones.length; i++) {
        String grupo = selecciones[i][0];

        if (!grupo.equals(grupoActual)) {
          if (!grupoActual.equals("")) {
    %>
  </div>
  </div>
  <%
    }
    grupoActual = grupo;
  %>
  <div class="mb-5">
    <div class="group-title">Grupo <%= grupoActual %></div>
    <div class="row g-4">
      <%
        }
      %>

      <div class="col-sm-6 col-md-4 col-lg-3">
        <div class="team-card">
          <img src="<%= selecciones[i][4] %>" alt="Bandera de <%= selecciones[i][1] %>">
          <h5><%= selecciones[i][1] %></h5>
          <div class="confed"><%= selecciones[i][2] %></div>
          <div class="rank">Ranking FIFA: <%= selecciones[i][3] %></div>
        </div>
      </div>

      <%
        }

        if (!grupoActual.equals("")) {
      %>
    </div>
  </div>
  <%
    }
  %>

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