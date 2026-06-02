package br.com.projetoteatro.model;

import java.time.LocalDateTime;

public class Ingresso {
    private long id;
    //private Cliente cliente;
    private Sessao sessao;
    private Assento assento;
    private Setor setor;
    private double valor;
    private LocalDateTime dataCompra;
    private String codigo;

    public void gerarPdf(){

    }

    public void enviarPorEmail(){

    }
}
