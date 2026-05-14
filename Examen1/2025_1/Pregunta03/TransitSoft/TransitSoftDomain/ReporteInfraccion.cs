using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoftDomain
{
    [Serializable]
    public class ReporteInfraccion
    {
        private int _reporteId;
        private int _conductorId;
        private string _paterno;
        private string _materno;
        private string _nombres;
        private int _vehiculoId;
        private string _placa;
        private string _marca;
        private string _modelo;
        private int _anho;
        private int _infraccionId;
        private string _descripcion;
        private double _monto;
        private string _gravedad;

        public ReporteInfraccion() { }

        public ReporteInfraccion(int reporteId, int conductorId, string paterno, string materno, string nombres, int vehiculoId, string placa, string marca, string modelo, int anho, int infraccionId, string descripcion, double monto, string gravedad)
        {
            this._reporteId = reporteId;
            this._conductorId = conductorId;
            this._paterno = paterno;
            this._materno = materno;
            this._nombres = nombres;
            this._vehiculoId = vehiculoId;
            this._placa = placa;
            this._marca = marca;
            this._modelo = modelo;
            this._anho = anho;
            this._infraccionId = infraccionId;
            this._descripcion = descripcion;
            this._monto = monto;
            this._gravedad = gravedad;
        }

        public ReporteInfraccion(int conductorId, string paterno, string materno, string nombres, int vehiculoId, string placa, string marca, string modelo, int anho, int infraccionId, string descripcion, double monto, string gravedad)
        {
            this._conductorId = conductorId;
            this._paterno = paterno;
            this._materno = materno;
            this._nombres = nombres;
            this._vehiculoId = vehiculoId;
            this._placa = placa;
            this._marca = marca;
            this._modelo = modelo;
            this._anho = anho;
            this._infraccionId = infraccionId;
            this._descripcion = descripcion;
            this._monto = monto;
            this._gravedad = gravedad;
        }

        public int ReporteId { get => _reporteId; set => _reporteId = value; }
        public int ConductorId { get => _conductorId; set => _conductorId = value; }
        public string Paterno { get => _paterno; set => _paterno = value; }
        public string Materno { get => _materno; set => _materno = value; }
        public string Nombres { get => _nombres; set => _nombres = value; }
        public int VehiculoId { get => _vehiculoId; set => _vehiculoId = value; }
        public string Placa { get => _placa; set => _placa = value; }
        public string Marca { get => _marca; set => _marca = value; }
        public string Modelo { get => _modelo; set => _modelo = value; }
        public int Anho { get => _anho; set => _anho = value; }
        public int InfraccionId { get => _infraccionId; set => _infraccionId = value; }
        public string Descripcion { get => _descripcion; set => _descripcion = value; }
        public double Monto { get => _monto; set => _monto = value; }
        public string Gravedad { get => _gravedad; set => _gravedad = value; }
    }
}
