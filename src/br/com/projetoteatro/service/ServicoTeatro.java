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
    private ArrayList<RegraAluguel> listaRegras;
    private ArrayList<PropostaAluguel> listaPropostas;
    private ArrayList<Contratante> listaContratante;
    private ValidadorHorarios validador;
    private Administrador[] adm;
    //private ArrayList<Peca> listaPecas;
    // private ArrayList<Sessao> listaSessoes;
    //private ArrayList<Ingresso> listaIngressos;
    private ArrayList<Usuario> listaClientes;
    //ver se dá certo isso que o prof. sugeriu map
    private Map<String, String> codigosRecuperacaoSenha = new HashMap<>();


    public ServicoTeatro(){

        listaRegras=new ArrayList<>();
        listaPropostas=new ArrayList<>();
        listaContratante=new ArrayList<>();
        validador=new ValidadorHorarios();
        // listaPecas=new ArrayList<Peca> ();
        //listaSessoes=new ArrayList<Sessao> ();
        // listaIngressos=new ArrayList<Ingresso> ();
        listaClientes=new ArrayList<Usuario> ();
        adm=new Administrador[1];

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
    public void cadastrarProposta(PropostaAluguel p)throws ConflitoHorarioException {
        validador.validarConflitoHorario(p,listaPropostas);
        listaPropostas.add(p);
    }
    //gerar proposta pdf
    public void geradorPropostaPDF(long id)throws PropostaInvalidaException{
        PropostaAluguel proposta=buscarProposta(id);
        PdfService.gerarContrato(proposta);
    }
    //enviar proposta email
    public boolean enviarPropostaPorEmail(long id)throws PropostaInvalidaException{
        PropostaAluguel proposta=buscarProposta(id);
        String arquivo = "Proposta_" + proposta.getId() + ".pdf";
        geradorPropostaPDF(id);
        return EmailService.enviarEmail(proposta.getContratante().getEmail(),"Proposta Teatro","Segue em anexo a proposta do teatro.",arquivo,"Anexo_Proposta");

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
    public void contratarProposta(long id) throws PropostaInvalidaException {
        PropostaAluguel proposta=buscarProposta(id);
        proposta.setStatusProposta(StatusProposta.CONTRATADO);
    }
    //estenderproposta
    public void estenderProposta(long id, int dias)throws PropostaInvalidaException {
        PropostaAluguel proposta=buscarProposta(id);
        if(proposta.getStatusProposta()!=StatusProposta.CONTRATADO){
            throw new PropostaInvalidaException("Proposta não contratada, portanto não pode ser extendida!");
        }
        proposta.setDataFim(proposta.getDataFim().plusDays(dias));
    }

    //excluir proposta
    public void encerrarProposta(long id)throws PropostaInvalidaException {
        PropostaAluguel proposta=buscarProposta(id);
        if(proposta.getStatusProposta()==StatusProposta.ENCERRADO){

        }else{proposta.setStatusProposta(StatusProposta.ENCERRADO);}
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

    public boolean excluirContratante(String cpf)throws CPFInvalidoException, ContratanteInvalidoException{

        Contratante contratante = buscarContratante(cpf);
        return listaContratante.remove(contratante);

    }
    public void solicitarMudancaSenhaContratante(String cpf)throws CPFInvalidoException, ContratanteInvalidoException{
        Contratante c=buscarContratante(cpf);
        //coloquei downcast de string não funcionou ver se o valueof...deu erro tbm
        String codigo=String.valueOf((int)(Math.random()*10000));
        codigosRecuperacaoSenha.put(cpf,codigo);
        //ver como deixar mais generico sem esses dois argumentos  vazios no final
        EmailService.enviarEmailCodigoSenha(c.getEmail(),"Mudança de SENHA","Segue o código validador para mudança de senha "+codigo);
    }
    public void redefinirSenhaContratante(String cpf,String codigo,String novaSenha)throws CPFInvalidoException, ContratanteInvalidoException{
        Contratante c=buscarContratante(cpf);
        String codigoGuardado=codigosRecuperacaoSenha.get(cpf);
        if(codigoGuardado==null||!codigoGuardado.equals(codigo)){
            throw new IllegalArgumentException("Código inválido");
        }
        c.setSenha(novaSenha);
        codigosRecuperacaoSenha.remove(cpf);

    }

    //editar e excluir contratante será que precisa???


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

    public void solicitarMudancaSenha(String cpf)throws CPFInvalidoException, ContratanteInvalidoException{
        Usuario c=buscarCliente(cpf);
        //coloquei downcast de string não funcionou ver se o valueof...deu erro tbm
        String codigo=String.valueOf((int)(Math.random()*10000));
        codigosRecuperacaoSenha.put(cpf,codigo);
        //ver como deixar mais generico sem esses dois argumentos  vazios no final
        EmailService.enviarEmailCodigoSenha(c.getEmail(),"Mudança de SENHA","Segue o código validador para mudança de senha "+codigo);
    }
    public void redefinirSenha(String cpf,String codigo,String novaSenha)throws CPFInvalidoException, ContratanteInvalidoException{
        Usuario c=buscarCliente(cpf);
        String codigoGuardado=codigosRecuperacaoSenha.get(cpf);
        if(codigoGuardado==null||!codigoGuardado.equals(codigo)){
            throw new IllegalArgumentException("Código inválido");
        }
        c.setSenha(novaSenha);
        codigosRecuperacaoSenha.remove(cpf);

    }
    public boolean excluirCliente(String cpf)throws CPFInvalidoException, ContratanteInvalidoException{

        Usuario cliente = buscarCliente(cpf);
        return listaClientes.remove(cliente);

    }


    //cadastrando o administrador unico, usando como set
    public void cadastrarAdministrador(Administrador a)throws AdiministradorInvalidoException{
        if(adm[0]!=null){
            throw new AdiministradorInvalidoException("Administrador já cadastrado");
        }
        adm[0]=a;
    }

    public Administrador getAdm() throws AdiministradorInvalidoException{
        if(adm[0]==null){
            throw new AdiministradorInvalidoException("Administrador não cadastrado");
        }
        return adm[0];
    }

    //editar senha adm, se o cpf bate com o do adm ele envia para o email cadastrado
    public void solicitarMudancaSenhaAdm(String cpf)throws EmailInvalidoException, AdiministradorInvalidoException{
        if(adm[0]==null){
            throw new AdiministradorInvalidoException("Administrador não cadastrado");
        }
        //coloquei downcast de string não funcionou ver se o valueof...deu erro tbm
        String codigo=String.valueOf((int)(Math.random()*10000));
        System.out.println("Código gerado: " + codigo);
        System.out.println("Email destino: " + adm[0].getEmail());
        System.out.println("cpf: " + adm[0].getCpf());
        codigosRecuperacaoSenha.put(cpf,codigo);
        EnviarEmailService.enviarEmailCodigoSenha(adm[0].getEmail(),"Mudança de SENHA",
                "Segue o código validador para mudança de senha"+codigo);
    }
    public void redefinirSenhaAdm(String cpf,String codigo,String novaSenha)throws EmailInvalidoException, AdiministradorInvalidoException{
        if(adm[0]==null){
            throw new AdiministradorInvalidoException("Administrador não cadastrado");
        }
        String codigoGuardado=codigosRecuperacaoSenha.get(cpf);
        if(codigoGuardado==null||!codigoGuardado.equals(codigo)){
            throw new IllegalArgumentException("Código inválido");
        }
        adm[0].setSenha(novaSenha);
        codigosRecuperacaoSenha.remove(cpf);

    }

    //filtragem

    public ArrayList<PropostaAluguel> filtrarPropostaPorStatus(StatusProposta s)  {
        ArrayList<PropostaAluguel> listagemResultado=new ArrayList<PropostaAluguel>();
        for(PropostaAluguel x:listaPropostas){
            if(x.getStatusProposta()==s){
                listagemResultado.add(x);
            }
        }
        return listagemResultado;
    }
    public ArrayList<PropostaAluguel> filtrarPropostaPorContratante(String n){
        ArrayList<PropostaAluguel> listagemResultado=new ArrayList<PropostaAluguel>();
        for(PropostaAluguel x:listaPropostas){
            if(x.getContratante().getNome().toLowerCase().contains(n.toLowerCase())){
                listagemResultado.add(x);
            }
        }
        return listagemResultado;
    }
    public ArrayList<PropostaAluguel> filtrarPropostaPorNomePeca(String n){
        ArrayList<PropostaAluguel> listagemResultado=new ArrayList<PropostaAluguel>();
        for(PropostaAluguel x:listaPropostas){
            if(x.getNomePeca().toLowerCase().contains(n.toLowerCase())){
                listagemResultado.add(x);
            }
        }
        return listagemResultado;
    }

}
