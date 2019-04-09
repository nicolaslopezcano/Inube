package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.google.gson.Gson;

import es.upm.dit.isst.webLab.dao.ClienteDAO;
import es.upm.dit.isst.webLab.dao.ClienteDAOImplementation;
import es.upm.dit.isst.webLab.dao.ComercianteDAO;
import es.upm.dit.isst.webLab.dao.ComercianteDAOImplementation;
import es.upm.dit.isst.webLab.dao.ComercioDAO;
import es.upm.dit.isst.webLab.dao.ComercioDAOImplementation;
import es.upm.dit.isst.webLab.dao.VentaDAO;
import es.upm.dit.isst.webLab.dao.VentaDAOImplementation;
import es.upm.dit.isst.webLab.model.Cliente;
import es.upm.dit.isst.webLab.model.Comerciante;
import es.upm.dit.isst.webLab.model.Comercio;
import es.upm.dit.isst.webLab.model.Venta;

@WebServlet("/PruebaGoogleChartServlet")
public class PruebaGoogleChartServlet extends HttpServlet {
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
				if (!idClientesDistintos.contains(idActual)) {
					idClientesDistintos.add(idActual);
				}
			}
		}
		
		Map<Integer, Integer> horasVenta = new TreeMap<Integer, Integer>(horasVentaAux);
		
		Collection<Integer> horas = horasVenta.keySet();
		Collection<Integer> cuentaHoras = horasVenta.values();
		
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		String sumaImporteStr = df.format(sumaImporte);
		String importeMedioStr = df.format(importeMedio);
		int numClientesDistintos = idClientesDistintos.size();
		
		Gson gson = new Gson();	
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(gson.toJson(horasVenta));
		
		
		
	}
	
}

