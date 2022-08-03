package br.recife.edu.ifpe.controllers;

import br.recife.edu.ifpe.model.dao.ManagerDao;
import br.recife.edu.ifpe.model.negocio.Insumo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "insumoController")
@SessionScoped
public class InsumoController implements Serializable {
    
    
    private Insumo cadastro;
    private Insumo selecionado;
    
    public InsumoController(){
        this.cadastro= new Insumo();
    }
    
    public String inserir(){
        
        
        if(this.cadastro.getNome().isEmpty()){
            FacesContext.getCurrentInstance().
                    addMessage("formCadInsumo:txtNome", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome inválido"));
        
            return null;
        }
        
        if(Double.compare(this.cadastro.getValorUnitario(), 0) < 0){
            FacesContext.getCurrentInstance().
                    addMessage("formCadInsumo:valorUnitario", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Valor Unitário inválido"));
        
            return null;
        }
        
        
        ManagerDao.getCurrentInstance().insert(this.cadastro);
        this.cadastro = new Insumo();
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!","Insumo Cadastrado"));
        
        return "insumos.xhtml";
    }
    
    public String alterar(){
        
        ManagerDao.getCurrentInstance().update(this.selecionado);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        "Sucesso!","Insumo alterado"));
        
        return "insumos.xhtml";
    }
    
    public void deletar(){
        
        ManagerDao.getCurrentInstance().delete(this.selecionado);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        "Sucesso!","Insumo excluído"));
        
    }
    
    public List<Insumo> readAll(){
        
        return ManagerDao.getCurrentInstance().read(
                "select i from Insumo i", Insumo.class);
        
    }

    public Insumo getCadastro() {
        return cadastro;
    }

    public void setCadastro(Insumo cadastro) {
        this.cadastro = cadastro;
    }

    public Insumo getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Insumo selecionado) {
        this.selecionado = selecionado;
    }
}
