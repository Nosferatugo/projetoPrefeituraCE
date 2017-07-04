/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.model;

import br.com.controleDeEstoque.enuns.EstadoCivil;
import br.com.controleDeEstoque.enuns.Sexo;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Onismar
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends Pessoa implements Serializable{

    private String inscricao;
    private String nomeFuncionario;
    private String cpfFuncionario;
    private String funcaoFuncionario;
    private String contatoFuncionario;
    private int nivelAcesso;
    private String login;
    private String senha;
    private boolean autorizarDesconto;
    private Double maxDesconto;

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getFuncaoFuncionario() {
        return funcaoFuncionario;
    }

    public void setFuncaoFuncionario(String funcaoFuncionario) {
        this.funcaoFuncionario = funcaoFuncionario;
    }

    public String getContatoFuncionario() {
        return contatoFuncionario;
    }

    public void setContatoFuncionario(String contatoFuncionario) {
        this.contatoFuncionario = contatoFuncionario;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    
    
  

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

  

    @Override
    public String toString() {
        return "Funcionario{" + "inscricao=" + inscricao + ", nomeFuncionario=" + nomeFuncionario + ", cpfFuncionario=" + cpfFuncionario + ", funcaoFuncionario=" + funcaoFuncionario + ", contatoFuncionario=" + contatoFuncionario + ", nivelAcesso=" + nivelAcesso + ", login=" + login + ", senha=" + senha + ", autorizarDesconto=" + autorizarDesconto + ", maxDesconto=" + maxDesconto + '}';
    }

    

 

   
    
    

   

   
}
