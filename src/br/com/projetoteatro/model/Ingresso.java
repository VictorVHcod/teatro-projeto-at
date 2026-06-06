package br.com.projetoteatro.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ingresso {

    private long id;
    private Usuario cliente;
    private Sessao sessao;
    private Assento assento;
    private Setor setor;
    private double valor;
    private LocalDateTime dataCompra;
    private String codigo;

    public Ingresso(Cliente cliente,
                    Sessao sessao,
                    Assento assento,
                    Setor setor,
                    double valor) {

        this.id = System.currentTimeMillis();
        //this.cliente = cliente;
        this.sessao = sessao;
        this.assento = assento;
        this.setor = setor;
        this.valor = valor;

        this.dataCompra = LocalDateTime.now();

        gerarCodigo();
    }

    private void gerarCodigo() {
        this.codigo = UUID.randomUUID().toString();
    }

    public long getId() {
        return id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Assento getAssento() {
        return assento;
    }

    public Setor getSetor() {
        return setor;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Ingresso{" +
                "codigo='" + codigo + '\'' +
                //", cliente=" + cliente.getNome() +
                ", sessao=" + sessao.getData() +
                ", valor=" + valor +
                '}';
    }
}