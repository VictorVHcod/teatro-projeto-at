package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.CPFInvalidoException;
import br.com.projetoteatro.exceptions.ContratanteInvalidoException;
import br.com.projetoteatro.exceptions.LoginInvalidoException;
import br.com.projetoteatro.exceptions.SenhaInvalidaException;
import br.com.projetoteatro.model.Administrador;
import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.model.Pessoa;
import br.com.projetoteatro.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    private Map<String, String> codigosRecuperacaoSenha = new HashMap<>();
    private ArrayList<Pessoa> listaPessoas;

    private ClienteService clienteService;
    private AdministradorService admService;
    private ArtistaService artistaService;
    public LoginService(AdministradorService admService,ClienteService clienteService,ArtistaService artistaService){
        this.clienteService=clienteService;
        this.admService=admService;
        this.artistaService=artistaService;

    }

    public Pessoa autenticar(String email, String senha) throws LoginInvalidoException {
        for(Contratante pessoa: artistaService.getListaContratante()){
            if(pessoa.getEmail().equals(email)){
                if(pessoa.getSenha().equals(senha)){
                    return pessoa;
                }
                throw new LoginInvalidoException("Senha inválido!");
            }
        }

        Administrador adm=admService.getAdm();
        if(adm!=null&&adm.getEmail().equals(email)){
            if(adm.getSenha().equals(senha)){
                return adm;
            }
            throw new LoginInvalidoException("Senha inválido!");
        }

        for(Usuario pessoa: clienteService.getListaCliente()){
            if(pessoa.getEmail().equals(email)){
                if(pessoa.getSenha().equals(senha)){
                    return pessoa;
                }
                throw new LoginInvalidoException("Senha inválido!");
            }
        }
        throw new LoginInvalidoException("Usuario não encontrado!");

    }
    private Pessoa buscarPessoa(String cpf){

        try{
            return clienteService.buscarCliente(cpf);
        }catch(Exception e){}

        try{
            return artistaService.buscarContratante(cpf);
        }catch(Exception e){}

        try{
            Administrador adm = admService.getAdm();

            if(adm.getCpf().equals(cpf)){
                return adm;
            }
        }catch(Exception e){}

        return null;
    }


    public void solicitarMudancaSenha(String cpf)throws CPFInvalidoException, ContratanteInvalidoException {
        Pessoa c=buscarPessoa(cpf);
        if(c==null){
            throw new CPFInvalidoException("Cpf não cadastrado...");
        }

            //coloquei downcast de string não funcionou ver se o valueof...deu erro tbm
            String codigo=String.valueOf((int)(Math.random()*10000));
            codigosRecuperacaoSenha.put(cpf,codigo);
            EmailService.enviarEmailCodigoSenha(c.getEmail(),"Mudança de SENHA","Segue o código validador para mudança de senha "+codigo);

        System.out.println("Código gerado: " + codigo);
        System.out.println("Email destino: " + c.getEmail());
        System.out.println("cpf: " + c.getCpf());
    }
    public void redefinirSenha(String cpf,String codigo,String novaSenha)throws CPFInvalidoException, ContratanteInvalidoException{
        Pessoa c=buscarPessoa(cpf);
        if(c==null){
            throw new CPFInvalidoException("Cpf não cadastrado...");
        }
        String codigoGuardado=codigosRecuperacaoSenha.get(cpf);

        if(codigoGuardado==null||!codigoGuardado.equals(codigo)){
            throw new IllegalArgumentException("Código inválido");
        }
        c.setSenha(novaSenha);
        codigosRecuperacaoSenha.remove(cpf);
    }
    }

