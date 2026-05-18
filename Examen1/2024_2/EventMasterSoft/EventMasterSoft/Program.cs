using SoftProgDBManager;
using System.Configuration;

public class Principal
{
    public static void Main(String[] args)
    {
        IConfiguration configuration = new ConfigurationBuilder()
      .SetBasePath(Directory.GetCurrentDirectory())
      .AddJsonFile("appsettings.json")
      .Build();

        DBManager.
        DBManager.Initialize("Server=localhost;Database=eventmastersoftdb;Uid=root;Pwd=;", "mysql");
    }
}