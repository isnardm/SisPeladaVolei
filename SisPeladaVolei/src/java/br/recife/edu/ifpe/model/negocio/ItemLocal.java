/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.recife.edu.ifpe.model.negocio;

/**
 *
 * @author ALUNO
 */
public class ItemLocal {
    
    private int id;
    private double valor;
    private Localpelada local;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Localpelada getLocal() {
        return local;
    }

    public void setLocal(Localpelada local) {
        this.local = local;
    }
    
    
    
}
