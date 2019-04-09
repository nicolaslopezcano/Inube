package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
import es.upm.dit.isst.webLab.model.Comerciante;
import es.upm.dit.isst.webLab.model.Comercio;

@WebServlet("/ComercianteServlet")
public class ComercianteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Subject currentUser = SecurityUtils.getSubject();
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
		
		/*
		ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
		req.getSession().setAttribute( "professor_list", pdao.readAll() );
		TFGDAO tdao = TFGDAOImplementation.getInstance();
		req.getSession().setAttribute( "tfg_list", tdao.readAll() );
		
		getServletContext().getRequestDispatcher( "/AdminView.jsp" ).forward( req, resp );
		*/
	}
}

