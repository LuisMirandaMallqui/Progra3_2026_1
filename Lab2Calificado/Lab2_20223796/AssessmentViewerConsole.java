import java.util.List;

public class AssessmentViewerConsole{
	public static void show(Assessment assessment){
		String reporte = "";
		reporte += "Inicio de examen ======= \n";
		reporte += "Duracion: "+  assessment.getFechaHoraInicio()+"\n";
		List<Item> items = assessment.getItems();
		for(Item item : items){
			reporte += item.getPregunta().devolverDatos() + "\n";
		}
		System.out.println(reporte);
	}
}