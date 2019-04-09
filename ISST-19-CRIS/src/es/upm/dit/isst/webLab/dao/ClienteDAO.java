package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import es.upm.dit.isst.webLab.model.*;

public interface ClienteDAO {
	
	public void create(Cliente cliente);
	public Cliente read(int id);
	public void update(Cliente cliente);
	public void delete(Cliente cliente);
	public Collection<Cliente> readAll();

}