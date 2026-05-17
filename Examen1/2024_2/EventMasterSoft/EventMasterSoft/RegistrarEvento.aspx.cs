using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace EventMasterSoft
{
    /* Colocar sus datos personales
     * ------------------------------------------------
     * Nombre Completo:
     * Codigo PUCP:
     * ------------------------------------------------
     */
    public partial class RegistrarEvento : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            lblTitulo.Text = "Registrar Evento";

            Cargar_Foto(sender, e);
            
        }

        public void Deshabilitar_Componentes()
        {
            txtIDEvento.Enabled = false;
            txtNombreEvento.Enabled = false;
            ddlProductora.Enabled = false;
            lbGuardar.Visible = false;
            txtDescripcion.Disabled = true;
            rbConcierto.Disabled = true;
            rbObraTeatral.Disabled = true;
            rbAdultos.Disabled = true;
            rbJovenes.Disabled = true;
            rbNinhos.Disabled = true;
            rbTodos.Disabled = true;
            txtCostoRealizacion.Enabled = false;
            dtpFechaRealizacion.Disabled = true;
            cbReingreso.Disabled = true;
            cbGrabacion.Disabled = true;
            fileUploadBannerPromocional.Enabled = false;
        }

        protected void Cargar_Foto(object sender, EventArgs e)
        {
            if (IsPostBack && fileUploadBannerPromocional.PostedFile != null && fileUploadBannerPromocional.HasFile)
            {
                string extension = System.IO.Path.GetExtension(fileUploadBannerPromocional.FileName);
                if (extension.ToLower() == ".jpg" || extension.ToLower() == ".jpeg" || extension.ToLower() == ".png" || extension.ToLower() == ".gif")
                {
                    string filename = Guid.NewGuid().ToString() + extension;
                    string filePath = Server.MapPath("~/Uploads/") + filename;
                    fileUploadBannerPromocional.SaveAs(Server.MapPath("~/Uploads/") + filename);
                    imgBannerPromocional.ImageUrl = "~/Uploads/" + filename;
                    imgBannerPromocional.Visible = true;
                    FileStream fs = new FileStream(filePath, FileMode.Open, FileAccess.Read);
                    BinaryReader br = new BinaryReader(fs);
                    Session["foto"] = br.ReadBytes((int)fs.Length);
                    fs.Close();
                }
                else
                {
                    Response.Write("Por favor, selecciona un archivo de imagen válido.");
                }
            }
        }

        protected void lbRegresar_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListarEventos.aspx");
        }
    }
}