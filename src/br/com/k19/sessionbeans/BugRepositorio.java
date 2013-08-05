package br.com.k19.sessionbeans;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.k19.entidades.Bug;

@Stateless
@RolesAllowed({"ADMIN","USERS"})
public class BugRepositorio {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void adiciona(Bug bug){
		manager.persist(bug);
	}
	
	public void edita(Bug bug){
		manager.merge(bug);
	}
	
	@RolesAllowed({"ADMIN"})
	public void removeId(Long id){
		Bug bug = manager.find(Bug.class, id);
		manager.remove(bug);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Bug> listaTodos(){
		TypedQuery<Bug> query = manager.createQuery("select x from Bug x", Bug.class);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Bug listaId(Long id){
		return manager.find(Bug.class, id);
	}
	
	
}
