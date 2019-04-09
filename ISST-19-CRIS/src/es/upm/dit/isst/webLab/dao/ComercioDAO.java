package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.*;

public interface ComercioDAO {
	
	public void create(Comercio comercio);
	public Comercio read(String merchantId);
	public void update(Comercio comercio);
	public void delete(Comercio comercio);
	public Collection<Comercio> readAll();
	public Collection<Comercio> readAllFromComerciante(int comercianteId);

}