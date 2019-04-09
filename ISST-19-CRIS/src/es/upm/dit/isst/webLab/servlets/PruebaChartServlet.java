package es.upm.dit.isst.webLab.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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

@WebServlet("/PruebaChartServlet")
public class PruebaChartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("image/png");
		
		OutputStream os = resp.getOutputStream();
		
		JFreeChart chart = getChart2();
		int width = 500;
		int height = 350;
		
		ChartUtilities.writeChartAsPNG(os, chart, width, height);
		
	}
	
	public JFreeChart getChart1() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Croatia", 22);
		dataset.setValue("Bohemia", 34);
		dataset.setValue("Bulgaria", 18);
		dataset.setValue("Spain", 5);
		dataset.setValue("Others", 21);
		
		JFreeChart chart = ChartFactory.createPieChart("Popular destinations", dataset, true, true, false);
		chart.setBorderVisible(false);
		
		return chart;
	}
	
	public JFreeChart getChart2() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    // Population in 2005  
	    dataset.addValue(10, "USA", "2005");  
	    dataset.addValue(15, "India", "2005");  
	    dataset.addValue(20, "China", "2005");  
	    // Population in 2010  
	    dataset.addValue(15, "USA", "2010");  
	    dataset.addValue(20, "India", "2010");  
	    dataset.addValue(25, "China", "2010");  
	    // Population in 2015  
	    dataset.addValue(20, "USA", "2015");  
	    dataset.addValue(25, "India", "2015");  
	    dataset.addValue(30, "China", "2015");
	    
	    DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
	    dataset2.addValue(3, "8", "");
	    dataset2.addValue(5, "9", "");
	    dataset2.addValue(8, "10", "");
	    dataset2.addValue(6, "11", "");
	    
	    JFreeChart chart = ChartFactory.createBarChart("Nº ventas por hora", "Hora", "Nº ventas", dataset2, PlotOrientation.VERTICAL, true, true, false);
	    
		return chart;
	}
}

