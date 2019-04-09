package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import es.upm.dit.isst.webLab.dao.ClienteDAO;
import es.upm.dit.isst.webLab.dao.ClienteDAOImplementation;
import es.upm.dit.isst.webLab.dao.ComercianteDAO;
import es.upm.dit.isst.webLab.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.webLab.dao.ComercioDAO;
import es.upm.dit.isst.webLab.dao.ComercioDAOImplementation;
import es.upm.dit.isst.webLab.dao.VentaDAO;
import es.upm.dit.isst.webLab.dao.VentaDAOImplementation;
import es.upm.dit.isst.webLab.model.*;

@WebServlet("/ComercioServlet")
public class ComercioServlet extends HttpServlet {
	
	public String getIdsClientesDistintos(Set<Integer> idClientesDistintos) {
		String ids = "";
		for (Integer i : idClientesDistintos) {
			ids += i + ", ";
		}
		return ids;
	}
	
	public void printMap(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Hora: " + entry.getKey() + " | Nº ventas: " + entry.getValue());
        }
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String merchantId = req.getParameter("merchantid");
		
		ComercioDAO comercioDAO = ComercioDAOImplementation.getInstance();
		Comercio comercio = comercioDAO.read(merchantId);
		
		int numVentas = comercio.getVentas().size();
		Set<Integer> idClientesDistintos = new HashSet<Integer>();
		double sumaImporte = 0.0;
		double importeMedio = 0.0;
		Map<Integer, Integer> horasVentaAux = new HashMap<Integer, Integer>();
		
		Calendar calendar = Calendar.getInstance();
		
		if (numVentas != 0) {
			importeMedio = (double) (sumaImporte / (double) numVentas);
			for (Venta v : comercio.getVentas()) {
				sumaImporte += v.getImporte();
				int idActual = v.getPersona().getId();
				calendar.setTime(v.getFecha());
				int hora = calendar.get(Calendar.HOUR_OF_DAY);
				if (!horasVentaAux.containsKey(hora)) {
					horasVentaAux.put(hora, 1);
				} else {
					int value = horasVentaAux.get(hora);
					horasVentaAux.put(hora, ++value);
				}
				//System.out.println(" --- ComercioServlet - contenido idClientesDistintos: " + getIdsClientesDistintos(idClientesDistintos));
				//System.out.println(" --- ComercioServlet - idActual: " + idActual);
				if (!idClientesDistintos.contains(idActual)) {
					idClientesDistintos.add(idActual);
				}
			}
		}
		
		Map<Integer, Integer> horasVenta = new TreeMap<Integer, Integer>(horasVentaAux);
		printMap(horasVenta);
		
		Collection<Integer> horas = horasVenta.keySet();
		Collection<Integer> cuentaHoras = horasVenta.values();
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		String sumaImporteStr = df.format(sumaImporte);
		String importeMedioStr = df.format(importeMedio);
		int numClientesDistintos = idClientesDistintos.size();
		
		req.getSession().setAttribute("comercio", comercio);
		req.getSession().setAttribute("num_ventas", numVentas);
		req.getSession().setAttribute("suma_importe", sumaImporteStr);
		req.getSession().setAttribute("importe_medio", importeMedioStr);
		req.getSession().setAttribute("num_clientes_distintos", numClientesDistintos);
		req.getSession().setAttribute("horas", horas);
		req.getSession().setAttribute("cuenta_horas", cuentaHoras);
		req.getSession().setAttribute("prueba", horasVenta);
		
		getServletContext().getRequestDispatcher("/ComercioView.jsp").forward(req, resp);
		
		/*Subject currentUser = SecurityUtils.getSubject();
		String currentUserPrincipal = (String) currentUser.getPrincipal();
		
		System.out.println(" --- ComercianteServlet - email: " + req.getSession().getAttribute("email"));
		System.out.println(" --- ComercianteServlet - currentUserPrincipal: " + currentUserPrincipal);		
		
		ComercianteDAO comercianteDAO = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDAO.readFromUsuario(currentUserPrincipal);
		
		if (comerciante != null) {
			System.out.println(" --- ComercianteServlet - comerciante.getUsuario(): " + comerciante.getUsuario());
			System.out.println(" --- ComercianteServlet - comerciante.getId(): " + comerciante.getId());
		} else {
			System.out.println(" --- ComercianteServlet: comerciante es null");
		}
		
		ClienteDAO clienteDao = ClienteDAOImplementation.getInstance();
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		ComercioDAO comercioDao = ComercioDAOImplementation.getInstance();
		VentaDAO ventaDao = VentaDAOImplementation.getInstance();
		
		Collection<Comercio> misComerciosSQL = comercioDao.readAllFromComerciante(comerciante.getId());
		System.out.println(" --- ComercianteServlet: misComerciosSQL.size(): " + misComerciosSQL.size());		
		
		req.getSession().setAttribute("cliente_list", clienteDao.readAll());
		req.getSession().setAttribute("comerciante_list", comercianteDao.readAll());
		req.getSession().setAttribute("comercio_list", comercioDao.readAll());
		req.getSession().setAttribute("my_comercios_list", misComerciosSQL);
		req.getSession().setAttribute("venta_list", ventaDao.readAll());
		
		req.getSession().setAttribute("comerciante_id", comerciante.getId());
		
		getServletContext().getRequestDispatcher("/ComercianteView.jsp").forward(req, resp);
		*/
		
		/*
		ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
		req.getSession().setAttribute( "professor_list", pdao.readAll() );
		TFGDAO tdao = TFGDAOImplementation.getInstance();
		req.getSession().setAttribute( "tfg_list", tdao.readAll() );
		
		getServletContext().getRequestDispatcher( "/AdminView.jsp" ).forward( req, resp );
		*/
	}
}

