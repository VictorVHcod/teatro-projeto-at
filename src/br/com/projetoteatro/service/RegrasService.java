package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.RegraInvalidaException;
import br.com.projetoteatro.model.RegraAluguel;

import java.util.ArrayList;

public class RegrasService {

    private ArrayList<RegraAluguel> listaRegras;

    public RegrasService(){
        listaRegras=new ArrayList<>();
    }

    //listar Regras obs: acho que tem que sobrescrever tostring
    public ArrayList<RegraAluguel> getListaRegras() {
        return listaRegras;
    }

    //cadastrando regras
    public void cadastrarRegra(RegraAluguel regra){
        listaRegras.add(regra);
    }

    //buscar regra por id
    public RegraAluguel buscarRegra(long id) throws RegraInvalidaException {
        for(RegraAluguel x:listaRegras){
            if(x.getId()==id){
                return x;
            }
        }
        throw new RegraInvalidaException("Não existe uma regra cadastrada com o id: "+id);

    }
    //editar a regra de aluguel

    public void editarRegra(long id,float novoValor) throws RegraInvalidaException {
        RegraAluguel regra=buscarRegra(id);
        regra.setValorHora(novoValor);
    }

    //excluir
    public boolean excluirRegra(long id) throws RegraInvalidaException {
        RegraAluguel regra=buscarRegra(id);
        return listaRegras.remove(regra);
    }
}
