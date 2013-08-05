package br.com.k19.managedbeans;
 
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.k19.entidades.Projeto;
import br.com.k19.sessionbeans.ProjetoRepositorio;

@Named
@RequestScoped
public class ProjetoMB {

	@Inject
	private ProjetoRepositorio pRep;
	
	private Projeto projeto = new Projeto();
	
	private List<Projeto> projetos;
	
	
	public void salvar(){
		if(projeto.getId() == null){
			pRep.adiciona(projeto);
		}else{
			pRep.edita(projeto);
		}
		
		projeto = new Projeto();
		projetos = null;
	}
	
	public void apaga(Long id){
		pRep.removeId(id);
		projetos = null;
	}
	
	public void buscaId(Long id){
		projeto = pRep.listaId(id);
	}
	
	public Projeto getProjeto(){
		return projeto;
	}
	
	public List<Projeto> getProjetos(){
		if(projetos == null){
			projetos = pRep.listaTodos();
		}
		
		return projetos;
	}
	
}
