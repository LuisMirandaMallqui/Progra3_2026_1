
using Microsoft.Extensions.Configuration;
using PokeSoftBusiness.Migrator.BO;
using PokeSoftBusiness.Migrator.BOI;
using PokeSoftDBManager;

/**
 * Código PUCP: 20223796
 * Nombre Completo: Luis Alberto Miranda Mallqui
 */
namespace PokemonSoftApp;
public class Program
{
    public static void Main(String[] args)
    {
        System.Console.WriteLine("Laboratorio 06 - PROG3");
        IConfiguration configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("appsettings.json")
            .Build();
        string conn = configuration.GetConnectionString("MySqlConnection");

        DBManager.Initialize(conn);

        MigratorBO migracionBO = new MigratorBOImpl();
        migracionBO.run();
    }
}