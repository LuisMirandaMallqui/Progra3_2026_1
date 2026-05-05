using Microsoft.Extensions.Configuration;
using MySql.Data.MySqlClient;
using SoftProgDBManager;
using SoftProgModel.RRHH;
using SoftProgPersistencia.RRHH.DAO;
using SoftProgPersistencia.RRHH.Impl;

public class Principal
{
    public static void Main(String[] args)
    {
        Area area = new Area("RECURSOS HUMANOS");
        System.Console.WriteLine(area.Nombre);

        IConfiguration configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();
        string connectionString = configuration.GetConnectionString("MySqlConnection");

        System.Console.WriteLine(connectionString);

        DBManager.Initialize(connectionString);

        MySqlConnection con = DBManager.Instance.GetConnection();
        con.Open();
        System.Console.WriteLine("Se ha abierto una conexión con la BD..");

        AreaDAO daoArea = new AreaImpl();

        daoArea.insertar(area);

        List<Area> areas = daoArea.listarTodos();

        foreach (Area a in areas)
            System.Console.WriteLine(a);

        for(int i = 0; i<areas.Count; i++)
            System.Console.WriteLine(areas[i]);

        con.Close();

    }
}