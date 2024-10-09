package com.projeto.sistemaVendas.Models;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "cliente")
public class Entrada implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String obs;
    private Double valorTotal;
    private Double quantidadeTotal;
    private Date DataEntrada = new Date();

    @ManyToOne
    private Fornecedor fornecedor;
    @ManyToOne
    private Funcionario funcionario;
    
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Double getQuantidadeTotal() {
        return quantidadeTotal;
    }
    public void setQuantidadeTotal(Double quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
    public Date getDataEntrada() {
        return DataEntrada;
    }
    public void setDataEntrada(Date dataEntrada) {
        DataEntrada = dataEntrada;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    

    

}
