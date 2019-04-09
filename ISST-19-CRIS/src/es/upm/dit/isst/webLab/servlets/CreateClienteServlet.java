package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;

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

@WebServlet("/CreateClienteServlet")
public class CreateClienteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recoger datos formulario (cp, sex, age)
		int cp = Integer.parseInt(req.getParameter("cp"));
		int sexo = Integer.parseInt(req.getParameter("sex"));
		int edad = Integer.parseInt(req.getParameter("age"));
		
		Cliente cliente = new Cliente();
		cliente.setCp(cp);
		cliente.setSexo(sexo);
		cliente.setEdad(edad);
		
		ClienteDAO clienteDAO = ClienteDAOImplementation.getInstance();
		clienteDAO.create(cliente);
		
		resp.sendRedirect(req.getContextPath() + "/AdminServlet");
		
		/*
		String merchantId = req.getParameter("merchantid");
		String nombreComercio = req.getParameter("name");
		String sector = req.getParameter("sector");
		int cp = Integer.parseInt(req.getParameter("cp"));
		String banco = req.getParameter("banco");
		
		
		int comercianteId = Integer.parseInt(req.getParameter("comerciante_id"));
		
		System.out.println(" --- CreateComercioServlet - par�metro comercianteId: " + req.getParameter("comerciante_id"));
		System.out.println(" --- CreateComercioServlet - par�metro comercianteId parsed: " + comercianteId);
		
		Comercio comercio = new Comercio();
		comercio.setMerchantId(merchantId);
		comercio.setNombreComercio(nombreComercio);
		comercio.setSector(sector);
		comercio.setCp(cp);
		comercio.setBanco(banco);
		
		ComercianteDAO comercianteDao = ComercianteDAOImplementation.getInstance();
		Comerciante comerciante = comercianteDao.read(comercianteId);
		System.out.println(" --- CreateComercioServlet - comerciante.toString(): " + comerciante.toString());
		System.out.println(" --- CreateComercioServlet - comerciante.getId(): " + comerciante.getId());
		comercio.setComerciante(comerciante);
		
		ComercioDAO comercioDao = ComercioDAOImplementation.getInstance();
		comercioDao.create(comercio);

		System.out.println(" --- CreateComercianteServlet - comercios.size(): " + comercioDao.readAll().size());
		
		resp.sendRedirect(req.getContextPath() + "/ComercianteServlet");
		*/
		
		/*
		String name = req.getParameter( "name" );
		String password = req.getParameter( "password" );
		String email = req.getParameter( "email" );
		Professor professor = new Professor();
		professor.setName( name );
		professor.setEmail( email );
		
		professor.setPassword( new Sha256Hash( password ).toString() );
		
		ProfessorDAO pdao = ProfessorDAOImplementation.getInstance();
		pdao.create( professor );
		
		resp.sendRedirect( req.getContextPath() + "/AdminServlet" );
		*/
	}
}

