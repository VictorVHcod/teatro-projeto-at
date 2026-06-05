package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.LoginInvalidoException;
import br.com.projetoteatro.exceptions.SenhaInvalidaException;
import br.com.projetoteatro.model.Administrador;
import br.com.projetoteatro.model.Pessoa;
import br.com.projetoteatro.model.Usuario;

import java.util.ArrayList;

public class LoginService {

        public Usuario autenticarUsuario(String email, String senha, ArrayList<Usuario> listaUsuarios) throws LoginInvalidoException {
            for(Usuario pessoa: listaUsuarios){
                if(pessoa.getEmail().equals(email)){
                    if(pessoa.getSenha().equals(senha)){
                        return pessoa;
                    }
                    throw new LoginInvalidoException("Senha inválido!");
                }
            }
            throw new LoginInvalidoException("Usuario não encontrado!");
        }
        public Administrador autenticarAdm (String email, String senha,Administrador adm) throws LoginInvalidoException{
            if(adm==null){
                throw new LoginInvalidoException("Não tem usuarios cadastrados");
            }
            if (!adm.getEmail().equals(email)){
                throw new LoginInvalidoException("Email inválido!");
            }
            if(!adm.getSenha().equals(senha)){
                throw new SenhaInvalidaException("Senha inválida!");
            }
            return adm;

        }
        //alterar senha coloquei no teatro service....talvez seja melhor colocar nessa classe

        //recuperar senha coloquei no teatro service....talvez seja melhor colocar nessa classe
    }

