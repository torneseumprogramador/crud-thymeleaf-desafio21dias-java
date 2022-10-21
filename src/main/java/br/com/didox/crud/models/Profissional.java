package br.com.didox.crud.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profissionais")
public class Profissional {

    public Profissional() {
        this.dataCriacao = LocalDateTime.now();
    }
    
    public Profissional(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nome", nullable=false, length=100)
    private String nome;
    
    @Column(name="observacao", columnDefinition="text")
    private String observacao;

    @Column(name="data_criacao", nullable=false)
    private LocalDateTime dataCriacao;

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
