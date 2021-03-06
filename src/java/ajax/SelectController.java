package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jefferson.lima
 */
@WebServlet(name = "SelectController", urlPatterns = {"/selectController"})
public class SelectController extends HttpServlet {

    public final class Veiculo implements Serializable {

        private String id;
        private String modelo;
        private String placa;
        private String codAtividade;

        public Veiculo(String id, String modelo, String placa, String codAtividade) {
            this.id = id;
            this.modelo = modelo;
            this.placa = placa;
            this.codAtividade = codAtividade;
        }

        public String getId() {
            return this.id;
        }

        public String getModelo() {
            return this.modelo;
        }

        public String getPlaca() {
            return this.placa;
        }

        public String getCodAtividade() {
            return this.codAtividade;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public void setPlaca(String placa) {
            this.placa = placa;
        }

        public void setCodAtividade(String codAtividade) {
            this.codAtividade = codAtividade;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoAtividade = request.getParameter("codigoAtividade");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder stringBuilder = new StringBuilder("");

        List<Veiculo> listaCarros = this.consultaCarrosAtividade(codigoAtividade);

        for (int i = 0; i < listaCarros.size(); i++) {
            stringBuilder.append(listaCarros.get(i).getCodAtividade() + "-" + listaCarros.get(i).getModelo() + "-" + listaCarros.get(i).getPlaca() + "/");;
        }
        out.write(stringBuilder.toString());
        out.close();
    }

    private List<Veiculo> consultaCarrosAtividade(String codigoAtividade) {
        List<Veiculo> listaCarros = new ArrayList<Veiculo>();
        Veiculo a = new Veiculo("0", "GOL", "GVW 5510", "A1");
        Veiculo b = new Veiculo("1", "UNO", "EQP 0002", "A2");
        Veiculo c = new Veiculo("2", "FIESTA", "BRA 2002", "A3");
        Veiculo d = new Veiculo("3", "HILLUX", "GON 6606", "A1");
        Veiculo e = new Veiculo("4", "FREEMONT", "GAH 2640", "A2");
        Veiculo f = new Veiculo("5", "S10", "CSC 2013", "A3");
        Veiculo g = new Veiculo("6", "TUCSON", "BRA 2014", "A1");
        Veiculo h = new Veiculo("7", "AMAROK", "NQC 0876", "A2");
        Veiculo i = new Veiculo("8", "SAVEIRO", "PLM 5748", "A3");

        listaCarros.add(a);
        listaCarros.add(b);
        listaCarros.add(c);
        listaCarros.add(d);
        listaCarros.add(e);
        listaCarros.add(f);
        listaCarros.add(g);
        listaCarros.add(h);
        listaCarros.add(i);

        List<Veiculo> carrosRelacionados = new ArrayList<Veiculo>();

        for (int k = 0; k < listaCarros.size(); k++) {
            if (listaCarros.get(k).getCodAtividade().equals(codigoAtividade)) {
                carrosRelacionados.add(listaCarros.get(k));
            }
        }

        return carrosRelacionados;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
