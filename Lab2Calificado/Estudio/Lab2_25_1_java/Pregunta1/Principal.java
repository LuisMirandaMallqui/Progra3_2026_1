//package pregunta1; // En Java el namespace se convierte en package

import java.time.LocalDateTime; // Importante para manejar fechas en JDK 25

public class Principal {
    public static void main(String[] args) { // Main con 'm' minúscula y String con 'S' mayúscula
        
        // Instanciamos los objetos
        Postulante postulante = new Postulante();
        FichaEvaluacion ficha = new FichaEvaluacion();
        Admision admision = new Admision();

        // --- PRIMER POSTULANTE ---
        // En Java usamos setNombreMetodo() en lugar de propiedades directas
        postulante.setPaterno("Pérez");
        postulante.setMaterno("Deza");
        postulante.setNombre("Juan Alonso");
        postulante.setDni("75355946B");
        
        ficha.setCandidato(postulante);
        // LocalDateTime.of(año, mes, día, hora, minuto, segundo)
        ficha.setFechaHora(LocalDateTime.of(2025, 2, 20, 14, 0, 0));
        ficha.setEvaluacionExpediente(22);
        ficha.setEvaluacionEntrevista(47);
        ficha.setEvaluacionExamen(18);
        
        admision.agregarFichaDeEvaluacion(ficha);

        // --- SEGUNDO POSTULANTE ---
        // Reutilizamos las variables (esto funciona porque tu Admision hace "new" en el constructor copia)
        postulante.setPaterno("León");
        postulante.setMaterno("Mendoza");
        postulante.setNombre("Carmen");
        postulante.setDni("87332141Z");
        
        ficha.setCandidato(postulante);
        ficha.setFechaHora(LocalDateTime.of(2025, 2, 20, 14, 30, 0));
        ficha.setEvaluacionExpediente(12);
        ficha.setEvaluacionEntrevista(22);
        ficha.setEvaluacionExamen(17);
        
        admision.agregarFichaDeEvaluacion(ficha);

        // --- TERCER POSTULANTE ---
        postulante.setPaterno("Sandoval");
        postulante.setMaterno("García");
        postulante.setNombre("Eric");
        postulante.setDni("73734226K");
        
        ficha.setCandidato(postulante);
        ficha.setFechaHora(LocalDateTime.of(2025, 2, 20, 15, 0, 0));
        ficha.setEvaluacionExpediente(15);
        ficha.setEvaluacionEntrevista(45);
        ficha.setEvaluacionExamen(23);
        
        admision.agregarFichaDeEvaluacion(ficha);

        // Imprimimos el reporte (Llama automáticamente al toString() de Admision)
        System.out.println(admision);
    }
}