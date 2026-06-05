package br.com.projetoteatro.service;

import br.com.projetoteatro.enums.StatusProposta;
import br.com.projetoteatro.exceptions.*;
import br.com.projetoteatro.model.*;
import br.com.projetoteatro.service.validators.ValidadorCPF;
import br.com.projetoteatro.service.validators.ValidadorHorarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicoTeatro {
    private ClienteService clienteService;
    private ArtistaService artistaService;
    private AdministradorService administradorService;
    private LoginService loginService;
    private PropostaService propostaService;
    private RegrasService regrasService;
    //private ArrayList<Peca> listaPecas;
    // private ArrayList<Sessao> listaSessoes;
    //private ArrayList<Ingresso> listaIngressos;

    public ServicoTeatro(){

        clienteService = new ClienteService();
        artistaService = new ArtistaService();
        administradorService = new AdministradorService();
        regrasService=new RegrasService();
        propostaService= new PropostaService();

        loginService = new LoginService(administradorService,clienteService,artistaService);
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public ArtistaService getArtistaService() {
        return artistaService;
    }

    public AdministradorService getAdministradorService() {
        return administradorService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public PropostaService getPropostaService() {
        return propostaService;
    }

    public RegrasService getRegrasService() {
        return regrasService;
    }
}
