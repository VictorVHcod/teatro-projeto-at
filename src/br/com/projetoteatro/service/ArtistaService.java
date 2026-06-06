package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.CPFInvalidoException;
import br.com.projetoteatro.exceptions.ContratanteInvalidoException;
import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.service.validators.ValidadorCPF;

import java.util.ArrayList;

public class ArtistaService {
   //CRUD DE ARTISTA

    private ArrayList<Contratante> listaContratante;
    public ArtistaService(){
        listaContratante=new ArrayList<>();

    }

    //cadastrar contratante
    public void cadastrarContratante(Contratante c) throws CPFInvalidoException {
        if(!ValidadorCPF.isValido(c.getCpf())){
            throw new CPFInvalidoException("CPF inválido...");
        }
        listaContratante.add(c);
    }

    //buscar contratante por cpf
    public Contratante buscarContratante(String cpf) throws CPFInvalidoException, ContratanteInvalidoException {
        if(!ValidadorCPF.isValido(cpf)){
            throw new CPFInvalidoException("CPF inválido...");
        }
        for(Contratante x: listaContratante){
            if(x.getCpf().equals(cpf)){
                return x;

            }
        }
        throw new ContratanteInvalidoException("Contratante não encontrado....");

    }

    //lista contratante
    public ArrayList<Contratante> getListaContratante() {
        return listaContratante;
    }

    public boolean excluirContratante(String cpf)throws CPFInvalidoException, ContratanteInvalidoException{

        Contratante contratante = buscarContratante(cpf);
        return listaContratante.remove(contratante);

    }

}
