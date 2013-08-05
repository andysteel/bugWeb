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
import br.com.k19.entidades.Projeto;

@Stateless
@RolesAllowed({"ADMIN","USERS"})
public class ProjetoRepositorio {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void adiciona(Projeto projeto){
		manager.persist(projeto);
	}
	
	
	public void edita(Projeto projeto){
		manager.merge(projeto);
	}
	
	@RolesAllowed({"ADMIN"})
	public void removeId(Long id){
		Projeto projeto = manager.find(Projeto.class, id);
		
		TypedQuery<Bug> query = manager.createQuery("select x from Bug x where x.projeto = : projeto", Bug.class);
		query.setParameter("projeto", projeto);
		List<Bug> bugs = query.getResultList();
		for(Bug bug : bugs){
			manager.remove(bug);
		}
		
		manager.remove(projeto);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Projeto> listaTodos(){
		TypedQuery<Projeto> query = manager.createQuery("select x from Projeto x", Projeto.class);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Projeto listaId(Long id){
		return manager.find(Projeto.class, id);
	}
	
}
