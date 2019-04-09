package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.*;

public interface ComercianteDAO {
	
	public void create(Comerciante comerciante);
	public Comerciante read(int id);
	public Comerciante readFromUsuario(String usuario);
	public void update(Comerciante comerciante);
	public void delete(Comerciante comerciante);
	public Collection<Comerciante> readAll();

}