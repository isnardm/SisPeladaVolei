/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ALUNO
 */
public class Pelada {
    
    private int id;
    private Date data;
    private String horarioInicio;
    private String horarioFim;
    private String descricao;
    
    private Adm responsavel;
    private List<Jogador> jogadores;
    private List<Jogador> jogadoresEspera;
    private ItemLocal local;
    private TipoPelada tipo;
    private List<ItemInsumo> insumos;
    
    public Pelada(){
        this.jogadores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Adm getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Adm responsavel) {
        this.responsavel = responsavel;
    }

    public List<Jogador> getJogadoresEspera() {
        return jogadoresEspera;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public ItemLocal getLocal() {
        return local;
    }

    public void setLocal(ItemLocal local) {
        this.local = local;
    }

    public TipoPelada getTipo() {
        return tipo;
    }

    public void setTipo(TipoPelada tipo) {
        this.tipo = tipo;
    }

    public List<ItemInsumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<ItemInsumo> insumos) {
        this.insumos = insumos;
    }
    
    public void addJogador(Jogador jogador){
        if(this.jogadores.size() == this.tipo.getQuantidadeMaxima()){
            this.jogadoresEspera.add(jogador);
            return;
        }
        this.jogadores.add(jogador);
    }
    
    public void removeJogador(Jogador jogador){
        
        for(Jogador j: this.jogadores){
            if(jogador.getId() == j.getId()){
                this.jogadores.remove(j);
                if(this.jogadoresEspera.size()>0){
                    this.jogadores.add(this.jogadoresEspera.get(0));
                    this.jogadoresEspera.remove(0);
                    return;
                }
            }
        }
        
        for(Jogador j: this.jogadoresEspera){
            if(jogador.getId() == j.getId()){
                this.jogadoresEspera.remove(j);
                return;
            }
        }
        
    }
    
    public double getValorTotal(){
        
        int horaInicio = Integer.parseInt(this.horarioInicio.split(":")[0]);
        int minutoInicio = Integer.parseInt(this.horarioInicio.split(":")[1]);
        
        int horaFim = Integer.parseInt(this.horarioFim.split(":")[0]);
        int minutoFim = Integer.parseInt(this.horarioFim.split(":")[1]);
        
        int tempoMinInicio = horaInicio*60 + minutoInicio;
        int tempoMinFim = horaFim*60 + minutoFim;
        
        int tempoPelada = (tempoMinFim - tempoMinInicio)/60;
        
        if(tempoPelada < 1){
            tempoPelada=1;
        }
        
        double valorInsumos = 0;
        
        for(ItemInsumo in: this.insumos){
            
            valorInsumos += in.getValorTotal();
            
        }
        
        double valorPelada = valorInsumos + tempoPelada*this.local.getValor();
        
        
        return valorPelada;
        
    }
    
}
