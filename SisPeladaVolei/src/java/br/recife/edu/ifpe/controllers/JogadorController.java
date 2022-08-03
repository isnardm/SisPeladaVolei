/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.controllers;

import br.recife.edu.ifpe.model.dao.ManagerDao;
import br.recife.edu.ifpe.model.negocio.Jogador;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ALUNO
 */
@ManagedBean(name = "jController")
@SessionScoped
public class JogadorController implements Serializable{
    
    private Jogador cadastro;
    private Jogador selecionado;
    
    public JogadorController(){
        this.cadastro = new Jogador();
    }
    
    /*@PostConstruct
    public void init(){
        this.cadastro = new Jogador();
    }*/
    
    public String inserir(){
        
        
        if(this.cadastro.getIdade()<15 || this.cadastro.getIdade()>80){
            FacesContext.getCurrentInstance().
                    addMessage("formCadJogador:txtIdade", 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Idade inválida"));
        
            return null;
        }
        
        ManagerDao.getCurrentInstance().insert(this.cadastro);
        this.cadastro = new Jogador();
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso!","Jogador Cadastrado"));
        
        return "jogadores.xhtml";
    }
    
    public String alterar(){
        
        ManagerDao.getCurrentInstance().update(this.selecionado);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        "Sucesso!","Jogador alterado"));
        
        return "jogadores.xhtml";
    }
    
    public void deletar(){
        
        ManagerDao.getCurrentInstance().delete(this.selecionado);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
        "Sucesso!","Jogador alterado"));
        
    }
    
    public List<Jogador> readAll(){
        
        return ManagerDao.getCurrentInstance().read(
                "select j from Jogador j", Jogador.class);
        
    }

    public Jogador getCadastro() {
        return cadastro;
    }

    public void setCadastro(Jogador cadastro) {
        this.cadastro = cadastro;
    }

    public Jogador getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Jogador selecionado) {
        this.selecionado = selecionado;
    }
    
    
    
}
