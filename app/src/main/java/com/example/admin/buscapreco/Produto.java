package com.example.admin.buscapreco;

import java.io.Serializable;

/**
 * Created by admin on 07/08/2017.
 */

public class Produto implements Serializable {
    private String cod;
    private String nomeProduto;
    private String preco;
    private boolean response;


    public boolean getResponse() {return response;}

    public void setResponse(boolean response) {this.response = response;}

    public String getCod() {return cod;}

    public void setCod(String cod) {this.cod = cod;}

    public String getNomeProduto() {return nomeProduto;}

    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}

    public String getPreco() {return preco;}

    public void setPreco(String preco) {this.preco = preco;}

    @Override
    public String toString() {
        return getNomeProduto() + getPreco();
    }

}
