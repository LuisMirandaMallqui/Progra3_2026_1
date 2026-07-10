<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>PokeSoft WebServices</title>

  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', Arial, sans-serif;
      background: linear-gradient(135deg, #1e3c72, #2a5298);
      color: white;
    }

    .container {
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 40px;
    }

    .card {
      max-width: 900px;
      width: 100%;
      background: rgba(255, 255, 255, 0.12);
      border-radius: 28px;
      padding: 45px;
      box-shadow: 0 25px 60px rgba(0, 0, 0, 0.35);
      backdrop-filter: blur(12px);
      text-align: center;
    }

    .pokemon-img {
      width: 210px;
      margin-bottom: 15px;
    }

    h1 {
      font-size: 48px;
      margin: 10px 0;
    }

    .subtitle {
      font-size: 20px;
      opacity: 0.9;
      margin-bottom: 30px;
    }

    .badge {
      display: inline-block;
      background: #ffcb05;
      color: #2a2a2a;
      padding: 10px 20px;
      border-radius: 999px;
      font-weight: bold;
      margin-bottom: 25px;
    }

    .info {
      display: flex;
      gap: 20px;
      justify-content: center;
      flex-wrap: wrap;
      margin-top: 30px;
    }

    .box {
      background: rgba(255, 255, 255, 0.16);
      padding: 20px;
      border-radius: 18px;
      width: 210px;
    }

    .box h3 {
      margin: 0 0 10px 0;
      color: #ffcb05;
    }

    .footer {
      margin-top: 35px;
      font-size: 14px;
      opacity: 0.8;
    }
  </style>
</head>

<body>
<div class="container">
  <div class="card">

    <img class="pokemon-img"
         src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png"
         alt="Pikachu">

    <div class="badge">Servidor GlassFish desplegado correctamente</div>

    <h1>PokeSoft WebServices</h1>

    <p class="subtitle">
      Proyecto base para el laboratorio de Programación 3
    </p>

    <div class="info">
      <div class="box">
        <h3>Arquitectura</h3>
        <p>Aplicación web Java organizada por capas.</p>
      </div>

      <div class="box">
        <h3>Servicios</h3>
        <p>Preparado para publicar servicios REST o SOAP.</p>
      </div>

      <div class="box">
        <h3>Sin Maven</h3>
        <p>Librerías locales configuradas en el proyecto.</p>
      </div>
    </div>

    <div class="footer">
      PUCP · Ingeniería Informática · PokeSoft Solution
    </div>

  </div>
</div>
</body>
</html>