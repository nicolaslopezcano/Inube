package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.*;

public interface VentaDAO {
	
	public void create(Venta venta);
	public Venta read(int id);
	public void update(Venta venta);
	public void delete(Venta venta);
	public Collection<Venta> readAll();

}