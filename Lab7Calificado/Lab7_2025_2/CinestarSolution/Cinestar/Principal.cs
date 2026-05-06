using CinestarBusiness.Migracion.BO;
using CinestarBusiness.Migracion.BOI;
using CinestarDBManager;
using CinestarModel.Venta;
using Microsoft.Extensions.Configuration;
using MySql.Data.MySqlClient;

public class Principal
{
    // considerar depedencias e importación
    public static void Main(string[] args)
    {
        Venta venta;
        IConfiguration configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();
        string connN = configuration.GetConnectionString("MySqlConnection");
        string connDN = configuration.GetConnectionString("MySqlConnectionDN");

        DBManager.Initialize(connN);

        IMigracionBO migracionBO = new MigracionBOImpl(connDN);
        migracionBO.Migrar();


        Console.WriteLine("EXITOS"); 
    }
}