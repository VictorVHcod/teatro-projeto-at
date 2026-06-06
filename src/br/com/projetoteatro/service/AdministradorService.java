package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.AdiministradorInvalidoException;
import br.com.projetoteatro.model.Administrador;

public class AdministradorService {

    private Administrador adm;

    public AdministradorService(){
        adm=null;
    }

    //cadastrando o administrador unico, usando como set
    public void cadastrarAdministrador(Administrador a)throws AdiministradorInvalidoException {
        if(adm!=null){
            throw new AdiministradorInvalidoException("Administrador já cadastrado");
        }
        adm=a;
    }

    public Administrador getAdm() throws AdiministradorInvalidoException{
        if(adm==null){
            throw new AdiministradorInvalidoException("Administrador não cadastrado");
        }
        return adm;
    }
}
