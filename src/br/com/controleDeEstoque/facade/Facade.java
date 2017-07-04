/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.facade;

import br.com.controleDeEstoque.business.EscolaBusiness;

import br.com.controleDeEstoque.business.FornecedorBusiness;
import br.com.controleDeEstoque.business.FuncionarioBusiness;

import br.com.controleDeEstoque.business.IFornecedorBusiness;
import br.com.controleDeEstoque.business.IFuncionarioBusiness;

import br.com.controleDeEstoque.business.IProdutoBusiness;


import br.com.controleDeEstoque.business.ProdutoBusiness;

import br.com.controleDeEstoque.commom.exception.BusinessException;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.enuns.ComposicaoProduto;
import br.com.controleDeEstoque.enuns.EstadoCivil;
import br.com.controleDeEstoque.enuns.Sexo;

import br.com.controleDeEstoque.enuns.TipoProduto;
import br.com.controleDeEstoque.model.Escola;

import br.com.controleDeEstoque.model.Contato;
import br.com.controleDeEstoque.model.Fornecedor;
import br.com.controleDeEstoque.model.Funcionario;

import br.com.controleDeEstoque.model.Produto;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import sun.security.jca.GetInstance;
import br.com.controleDeEstoque.business.IEscolaBusiness;
import br.com.controleDeEstoque.enuns.TipoDeEscola;

/**
 *
 * @author Onismar
 */
public class Facade implements IFacade {

//    public static Facade getInstance() {
//        return instance;
//    }
    private static IEscolaBusiness escolaDao;
    private static IFornecedorBusiness fornecedorDao;
 
    private static IProdutoBusiness produtoDao;
    private static IFuncionarioBusiness funcionarioDao;
 

    
    private final static Facade instance = new Facade();
//      private static final Facade instance;

//    static {
//        instance = new Facade();
//       
//    }
    private Facade() {
        escolaDao = new EscolaBusiness();
        fornecedorDao = new FornecedorBusiness();
     
        produtoDao = new ProdutoBusiness();
        funcionarioDao = new FuncionarioBusiness();
     
       
    }
    
    public static Facade getInstance() {
        
        return instance;
    }

    //-----------------------Escola
    @Override
    public void salvarEscola(Escola e) throws BusinessException, ExceptionDAO {
        escolaDao.salvar(e);
    }
    
    @Override
    public void realizarCadastroDaEscola(Long id, String nome,TipoDeEscola tipoEscola,String rua, String numeroDaRua, String bairro, String cep,
            String cidade, String estado, List<Contato> listaContatos) throws BusinessException, ExceptionDAO {
        
        escolaDao.realizarCadastro(id, nome, tipoEscola, rua, numeroDaRua, bairro, cep, cidade, estado, listaContatos);
    }
    
    @Override
    public void atualizarEscola(Escola e) throws BusinessException, ExceptionDAO {
        escolaDao.atualizar(e);
    }
    
     @Override
    public void removerEscola(Escola e) throws ExceptionDAO {
        escolaDao.remover(e);
    }
    
    @Override
    public Escola getByIdEscola(Long id) throws BusinessException, ExceptionDAO {
        return escolaDao.getById(id);
        
    }
    
    @Override
    public List<Escola> getTodosEscola() throws BusinessException, ExceptionDAO {
        return escolaDao.getTodos();
    }
    
    @Override
    public Escola getporNomeEscola(String nome) throws BusinessException, ExceptionDAO {
        return escolaDao.getporNome(nome);
    }
    
  
    
    @Override
    public void gerarTabelaEscola(JTable tabela) throws BusinessException {
        escolaDao.gerarTabelaEscola(tabela);
    }
    
    @Override
    public void gerarTelaEditarEscola(Escola e) throws BusinessException {
        escolaDao.gerarTelaEditarEscola(e);
    }

    
    //------------------------Forncedor
    @Override
    public void salvarFornecedor(Fornecedor f) throws ExceptionDAO {
        fornecedorDao.salvar(f);
    }
    
    @Override
    public void atualizarForncedor(Fornecedor f) throws ExceptionDAO {
        fornecedorDao.atualizar(f);
    }
    
    @Override
    public void removerFornecedor(Fornecedor f) throws ExceptionDAO {
        fornecedorDao.remover(f);
    }

    
    @Override
    public Fornecedor getByIdFornecedor(Long id) throws ExceptionDAO {
        return fornecedorDao.getById(id);
    }
    
    @Override
    public List<Fornecedor> getTodosFornecedores() throws ExceptionDAO {
        return fornecedorDao.getTodos();
    }
    
     
    public Fornecedor getUltimoFornecedores() throws ExceptionDAO {
        
        List<Fornecedor> listaFor = getTodosFornecedores();
        
        return listaFor.get(listaFor.size()-1);
    }
    
//    @Override
//    public Boolean realizarCadastraFornecedor(Long id,String nome, String cnpj, String rua, String cep, String numero, String bairro, String cidade, String estado) throws ExceptionDAO {
//       return  fornecedorDao.realizarCadastraFornecedor(id,nome, cnpj, rua, cep, numero, bairro, cidade, estado);
//    }
    
    @Override
    public void gerarTabelaFornecedor(JTable tabela) throws BusinessException {
        fornecedorDao.gerarTabelaFornecedor(tabela);
    }
    
    @Override
    public void gerarTelaEditarFornecedor(Fornecedor f) throws BusinessException {
        fornecedorDao.gerarTelaEditarFornecedor(f);
    }

   
    //---------------Produto
    
    public void gerarTabelaProduto(JTable tabela) throws BusinessException {
        produtoDao.gerarTabelaProduto(tabela);
    }
    
    @Override
    public void salvarProduto(Produto p) throws ExceptionDAO {
        produtoDao.salvar(p);
    }
    
    @Override
    public void atualizarProduto(Produto p) throws ExceptionDAO {
        produtoDao.atualizar(p);
    }
    
     @Override
    public void removerProduto(Produto p) throws ExceptionDAO {
       produtoDao.remover(p);
    }
    
    @Override
    public Produto getByIdProduto(Long id) throws ExceptionDAO {
        return produtoDao.getById(id);
    }
    
    @Override
    public List<Produto> getTodosProdutos() throws ExceptionDAO {
        return produtoDao.getTodos();
    }
    
    @Override
    public List<Produto> getProdutoPorNome(String nome) throws ExceptionDAO {
        return produtoDao.getProdutoPorNome(nome);
    }
    
    @Override
    public void realizarCadastroProduto(Long id, String nomeProduto, Calendar dataValidade,
            String unidadeMedida, int reposicao, int quantidadeInicial, int quantidadeFinal,
            Long codigoDeBarras, String tipoProduto, String composicaoProduto,
            Fornecedor f) throws ExceptionDAO {
        produtoDao.realizarCadastro(id, nomeProduto, dataValidade, unidadeMedida, reposicao, quantidadeInicial, quantidadeFinal, codigoDeBarras, tipoProduto, composicaoProduto, f);
                }
    
    @Override
    public Produto addItemProdutoDoEstoque(Long ip, String tipoProduto, String descricao, String composicao, int quantidade,Fornecedor f) throws ExceptionDAO {
        return produtoDao.addItemProdutoDoEstoque(ip, tipoProduto, descricao, composicao,quantidade, f);
    }
    
    public Produto getUltimoProduto() throws ExceptionDAO {
        
        List<Produto> listaProd = getTodosProdutos();
        
        return listaProd.get(listaProd.size()-1);
    }

     @Override
    public Produto getProdutoPorCodigoBarras(int codigo) throws ExceptionDAO {
        return produtoDao.getProdutoPorCodigoBarras(codigo);
    }

    //-------------------------Funcionario
    @Override
    public void salvarFuncionario(Funcionario f) throws BusinessException, ExceptionDAO {
        funcionarioDao.salvar(f);
    }
    
    @Override
    public void atualizarFuncionario(Funcionario f) throws BusinessException, ExceptionDAO {
        funcionarioDao.atualizar(f);
    }
    
    @Override
    public Funcionario getByIdFuncionario(Long id) throws BusinessException, ExceptionDAO {
        return funcionarioDao.getById(id);
    }
    
    @Override
    public List<Funcionario> getTodosFuncionarios() throws BusinessException, ExceptionDAO {
        return funcionarioDao.getTodos();
    }
    
    @Override
    public void modificaStatusFuncionario(Funcionario f, boolean status) throws BusinessException, ExceptionDAO {
        funcionarioDao.modificaStatus(f, status);
    }
    
    @Override
    public List<Funcionario> autorizacaoDescontoFuncionario(boolean status) throws BusinessException, ExceptionDAO {
        return funcionarioDao.autorizacaoDesconto(status);
    }
    
    @Override
    public List<Funcionario> statusFuncionario(boolean status) throws BusinessException, ExceptionDAO {
        return funcionarioDao.statusFuncionario(status);
    }
    
    @Override
    public Funcionario retornaFuncionario(String cpf) throws BusinessException, ExceptionDAO {
        return funcionarioDao.retornaFuncionario(cpf);
    }
    
    @Override
    public void realizarCadastroFuncionario(Long id,String nome, String cpf, String rg, String dataNascimento, String dataExpedicao, String orgaoEmissor, String rua, String numeroDaRua, String bairro, String cep, String cidade, String estado, List<Contato> listaContatos, Sexo sexo, EstadoCivil estadoSivil) throws BusinessException, ExceptionDAO {
        funcionarioDao.realizarCadastroFuncionario(id,nome, cpf, rg, dataNascimento, dataExpedicao, orgaoEmissor, rua, numeroDaRua, bairro, cep, cidade, estado, listaContatos, sexo, estadoSivil);
    }
    
     @Override
    public void gerarTabelaFuncionario(JTable tabela) throws BusinessException {
        funcionarioDao.gerarTabelaFuncionario(tabela);
    }

    @Override
    public void gerarTelaEditarFuncionario(Funcionario f) throws BusinessException {
        funcionarioDao.gerarTelaEditarFuncionario(f);
    }
    
    
    @Override
    public Funcionario retornaFuncionarioPorLogin(String login) throws ExceptionDAO, BusinessException {
       return funcionarioDao.retornaFuncionarioPorLogin(login);
    }
    
     @Override
    public Boolean confirmaFuncionarioPorLogin(String login, String senha) throws ExceptionDAO, BusinessException {
       return funcionarioDao.confirmaFuncionarioPorLogin(login, senha);
    }

    @Override
    public void removerFuncionario(Funcionario f) throws ExceptionDAO {
        funcionarioDao.remover(f);
    }

   

    


    


   
   
    
}
