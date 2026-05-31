package br.com.projetoteatro.service.validators;

import br.com.projetoteatro.exceptions.*;
import br.com.projetoteatro.model.Contratante;
import br.com.projetoteatro.model.PropostaAluguel;
import br.com.projetoteatro.model.RegraAluguel;

import java.util.ArrayList;

public class ServicoTeatro {
    private ArrayList<RegraAluguel> listaRegras;
    private ArrayList<PropostaAluguel> listaPropostas;
    private ArrayList<Contratante> listaContratante;
    private ValidadorHorarios validador;


    public ServicoTeatro(){

        listaRegras=new ArrayList<>();
        listaPropostas=new ArrayList<>();
        listaContratante=new ArrayList<>();
        validador=new ValidadorHorarios();

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
    //cadastrar proposta
    public void cadastrarProposta(PropostaAluguel p)throws ConflitoHorarioException{
        validador.validarConflitoHorario(p,listaPropostas);
        listaPropostas.add(p);
    }
    //buscar proposta por id
    public PropostaAluguel buscarProposta(long id) throws PropostaInvalidaException {
        for(PropostaAluguel p: listaPropostas){
            if(p.getId()==id){
                return p;
            }
        }
        throw new PropostaInvalidaException("Proposta não encontrada....");

    }
    //lista Proposta
    public ArrayList<PropostaAluguel> getListaPropostas() {
        return listaPropostas;
    }

    //cadastrar contratante
    public void cadastrarContratante(Contratante c) throws CPFInvalidoException{
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
}
