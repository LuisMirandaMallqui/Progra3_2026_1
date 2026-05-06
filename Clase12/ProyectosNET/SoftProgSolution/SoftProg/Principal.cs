using Microsoft.Extensions.Configuration;
using MySql.Data.MySqlClient;
using SoftProgDBManager;
using SoftProgModel.Almacen;
using SoftProgModel.GestClientes;
using SoftProgModel.RRHH;
using SoftProgModel.Ventas;
using SoftProgNegocio.Almacen.BO;
using SoftProgNegocio.Almacen.BOI;
using SoftProgNegocio.GestClientes.BO;
using SoftProgNegocio.GestClientes.BOI;
using SoftProgNegocio.RRHH.BO;
using SoftProgNegocio.RRHH.BOI;
using SoftProgNegocio.Ventas.BO;
using SoftProgNegocio.Ventas.BOI;
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

        IAreaBO areaBO = new AreaBOImpl();

        areaBO.insertar(area);

        List<Area> areas = areaBO.listarTodos();

        foreach (Area a in areas)
            System.Console.WriteLine(a);

        for (int i = 0; i < areas.Count; i++)
            System.Console.WriteLine(areas[i]);

        Empleado emp1 = new Empleado();
        emp1.Nombre = "HUGO";
        emp1.ApellidoPaterno = "VILLANUEVA";
        emp1.DNI = "98762211";
        emp1.Area = areas[0];
        emp1.Genero = 'M';
        emp1.FechaNacimiento = DateTime.Now;
        emp1.Cargo = "VENDEDOR";
        emp1.Sueldo = 2400.00;

        IEmpleadoBO empleadoBO = new EmpleadoBOImpl();
        empleadoBO.insertar(emp1);

        List<Empleado> empleados = empleadoBO.listarTodos();

        System.Console.WriteLine("Lectura compleada..");

        IProductoBO productoBO = new ProductoBOImpl();
        List<Producto> productos = productoBO.listarTodos();

        IClienteBO clienteBO = new ClienteBOImpl();
        List<Cliente> clientes = clienteBO.listarTodos();

        OrdenVenta ov = new OrdenVenta();

        LineaOrdenVenta lov1 = new LineaOrdenVenta();
        lov1.Producto = productos[0];
        lov1.CantidadUnidades = 2;
        lov1.Subtotal = lov1.Producto.Precio * lov1.CantidadUnidades;

        LineaOrdenVenta lov2 = new LineaOrdenVenta();
        lov2.Producto = productos[1];
        lov2.CantidadUnidades = 3;
        lov2.Subtotal = lov2.Producto.Precio * lov2.CantidadUnidades;

        ov.LineasOrdenVenta.Add(lov1);
        ov.LineasOrdenVenta.Add(lov2);

        ov.Total = lov1.Subtotal + lov2.Subtotal;

        ov.Cliente = clientes[0];
        ov.Empleado = empleados[0];

        IOrdenVentaBO ordenVentaBO = new OrdenVentaBOImpl();
        ordenVentaBO.insertar(ov);

        System.Console.WriteLine("Se ha registrado la ov...");
    }
}