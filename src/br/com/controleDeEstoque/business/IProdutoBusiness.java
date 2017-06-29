/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.business;

import br.com.controleDeEstoque.commom.exception.BusinessException;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.enuns.ComposicaoProduto;
import br.com.controleDeEstoque.enuns.TipoProduto;
import br.com.controleDeEstoque.model.Fornecedor;
import br.com.controleDeEstoque.model.Produto;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Onismar
 */
public interface IProdutoBusiness {

    public void salvar(Produto p) throws ExceptionDAO;

    public void atualizar(Produto p) throws ExceptionDAO;

    public Produto getById(Long id) throws ExceptionDAO;

    public List<Produto> getTodos() throws ExceptionDAO;

    public List<Produto> getProdutoPorNome(String nome) throws ExceptionDAO;
    
    public void realizarCadastro(Long id, String nomeProduto, Calendar dataValidade,
            String unidadeMedida, int reposicao, int quantidadeInicial, int quantidadeFinal,
            Long codigoDeBarras, String tipoProduto, String composicaoProduto,
            Fornecedor f) throws ExceptionDAO;

    public Produto addItemProdutoDoEstoque(Long ip, String tipoProduto, String descricao, String composicao,int quantidade,Fornecedor f)throws ExceptionDAO;

    public Produto getProdutoPorCodigoBarras(int codigo) throws ExceptionDAO;
    
    public void gerarTabelaProduto (JTable tabela) throws BusinessException;
   
    }

  