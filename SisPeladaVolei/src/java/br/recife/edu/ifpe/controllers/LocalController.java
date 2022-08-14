package br.recife.edu.ifpe.controllers;

import br.recife.edu.ifpe.model.dao.ManagerDao;
import br.recife.edu.ifpe.model.negocio.Insumo;
import br.recife.edu.ifpe.model.negocio.Localpelada;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "LocalController")
@SessionScoped
public class LocalController implements Serializable {
    
    
    private Localpelada cadastro;
    private Localpelada selecionado;
    
    public LocalController(){
        this.cadastro= new Localpelada();
    }
    
    public String inserir(){
        
        
        if(this.cadastro.getNome().isEmpty()){
            FacesContext.getCurrentInstance().
                    addMessage("formCadLocal:txtNome", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome inválido"));
        
            return null;
        }
        
         if(this.cadastro.getEndereco().isEmpty()){
            FacesContext.getCurrentInstance().
                    addMessage("formCadLocal:txtNome", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome inválido"));
        
            return null;
        }
        
        
        ManagerDao.getCurrentInstance().insert(this.cadastro);
        this.cadastro = new Localpelada();
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!","Local Cadastrado"));
        
        return "Local.xhtml";
    }
    
    public String alterar(){
        
        ManagerDao.getCurrentInstance().update(this.selecionado);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        "Sucesso!","Local alterado"));
        
        return "Local.xhtml";
    }
    
    public void deletar(){
        
        ManagerDao.getCurrentInstance().delete(this.selecionado);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        "Sucesso!","Local excluído"));
        
    }
    
    public List<Localpelada> readAll(){
        
        return ManagerDao.getCurrentInstance().read("select i from Localpelada i", Localpelada.class);
        
    }

    public Localpelada getCadastro() {
        return cadastro;
    }

    public void setCadastro(Localpelada cadastro) {
        this.cadastro = cadastro;
    }

    public Localpelada getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Localpelada selecionado) {
        this.selecionado = selecionado;
    }
}
