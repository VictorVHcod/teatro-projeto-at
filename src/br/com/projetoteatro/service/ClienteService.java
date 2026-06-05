package br.com.projetoteatro.service;

import br.com.projetoteatro.exceptions.CPFInvalidoException;
import br.com.projetoteatro.exceptions.ContratanteInvalidoException;
import br.com.projetoteatro.model.Usuario;
import br.com.projetoteatro.service.validators.ValidadorCPF;

import java.util.ArrayList;

public class ClienteService {
    private ArrayList<Usuario> listaClientes;

    public ClienteService(){
        listaClientes=new ArrayList<Usuario> ();
    }
    public void verificadorCPF(String cpf) throws CPFInvalidoException {

        if(cpf == null) {
            throw new CPFInvalidoException("O campo do CPF não pode ser nulo");
        }

        if(!ValidadorCPF.isValido(cpf)) {
           throw new CPFInvalidoException("CPF inválido") ;
        }
    }

    //cadastrar usuario final

    public void cadastrarCliente(Usuario u) throws CPFInvalidoException {
        if(!ValidadorCPF.isValido(u.getCpf())){
            throw new CPFInvalidoException("CPF inválido...");
        }
        listaClientes.add(u);
    }
    // buscar usuario fianl por cpf
    public Usuario buscarCliente(String cpf) throws CPFInvalidoException, ContratanteInvalidoException {
        if(!ValidadorCPF.isValido(cpf)){
            throw new CPFInvalidoException("CPF inválido...");
        }
        for(Usuario u: listaClientes){
            if(u.getCpf().equals(cpf)){
                return u;

            }
        }
        throw new ContratanteInvalidoException("Usuário não encontrado....");
    }
    //lista cliente final
    public ArrayList<Usuario> getListaCliente() {
        return listaClientes;
    }
    public boolean excluirCliente(String cpf)throws CPFInvalidoException, ContratanteInvalidoException{

        Usuario cliente = buscarCliente(cpf);
        return listaClientes.remove(cliente);

    }
}
