package br.com.projetoteatro.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ingresso {
    private long id;
    //private Cliente cliente;
    private Sessao sessao;
    private Assento assento;
    private Setor setor;
    private double valor;
    private LocalDateTime dataCompra;
    private String codigo;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Sessao getSessao() {
        return sessao;
    }
    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Assento getAssento() {
        return assento;
    }
    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Setor getSetor() {
        return setor;
    }
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    //Gerar código do ingresso automaticamente - Checar se está certo/precisa
    public void gerarCodigo() {
        this.codigo = UUID.randomUUID().toString();
    }

}
