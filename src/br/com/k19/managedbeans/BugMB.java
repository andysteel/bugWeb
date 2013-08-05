package br.com.k19.managedbeans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.k19.entidades.Bug;
import br.com.k19.entidades.Projeto;
import br.com.k19.sessionbeans.BugRepositorio;
import br.com.k19.sessionbeans.ProjetoRepositorio;

@Named
@RequestScoped
public class BugMB {

	@Inject
	private BugRepositorio bRep;
	
	@Inject
	private ProjetoRepositorio pRep;
	
	private Bug bug = new Bug();
	
	private List<Bug> bugs;
	
	private Long projetoId;
	
	
	public void salvar(){
		Projeto projeto = pRep.listaId(projetoId);
		bug.setProjeto(projeto);
		
		if(bug.getB_id() == null){
			bRep.adiciona(bug);
		}else{
			bRep.edita(bug);
		}
		
		bug = new Bug();
		bugs = null;
	}
	
	
	public void apaga(Long id){
		bRep.removeId(id);
		bugs = null;
	}
	
	
	public void buscaId(Long id){
		bug = bRep.listaId(id);
	}
	
	
	public Bug getBug(){
		return bug;
	}
	
	public List<Bug> getBugs(){
		if(bugs == null){
			bugs = bRep.listaTodos();
		}
		
		return bugs;
	}


	public Long getProjetoId() {
		return projetoId;
	}


	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}
}
