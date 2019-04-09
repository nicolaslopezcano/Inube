package es.upm.dit.isst.webLab.dao;

import java.util.Collection;

import org.hibernate.Session;

import es.upm.dit.isst.webLab.model.Venta;

public class VentaDAOImplementation implements VentaDAO {
	
	private static VentaDAOImplementation instance = null;
	private VentaDAOImplementation() {
	}
	
	public static VentaDAOImplementation getInstance() {
		if (instance == null) {
			instance = new VentaDAOImplementation();
		}
		return instance;
	}

	@Override
	public void create(Venta venta) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(venta);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Venta read(int id) {
		Session session = SessionFactoryService.get().openSession();
		Venta venta = null;
		try {
			session.beginTransaction();
			venta = session.get(Venta.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return venta;
	}

	@Override
	public void update(Venta venta) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(venta);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Venta venta) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(venta);
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
	}

	@Override
	public Collection<Venta> readAll() {
		Session session = SessionFactoryService.get().openSession();
		Collection<Venta> ventas = null;
		try {
			session.beginTransaction();
			ventas = session.createQuery("from Venta").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			// manejar
		} finally {
			session.close();
		}
		return ventas;
	}

}



