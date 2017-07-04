/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.business;

import br.com.controleDeEstoque.commom.exception.BusinessException;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.dao.IProdutoDAO;
import br.com.controleDeEstoque.dao.ProdutoDAO;
import br.com.controleDeEstoque.enuns.ComposicaoProduto;
import br.com.controleDeEstoque.enuns.TipoProduto;
import br.com.controleDeEstoque.facade.Facade;
import br.com.controleDeEstoque.gui.CadastrarProduto;
import br.com.controleDeEstoque.model.Fornecedor;

import br.com.controleDeEstoque.model.Produto;
import br.com.controleDeEstoque.strategy.MensagensUtilStrategy;
import br.com.controleDeEstoque.strategy.UtilitariosStrategy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Onismar
 */
public class ProdutoBusiness implements IProdutoBusiness {

    private IProdutoDAO produtoDao;
    private Facade fachada = Facade.getInstance();

    public ProdutoBusiness() {
        produtoDao = new ProdutoDAO();
    }

    @Override
    public void salvar(Produto p) throws ExceptionDAO {
        produtoDao.salvar(p);
    }

    @Override
    public void atualizar(Produto p) throws ExceptionDAO {
        produtoDao.atualizar(p);
    }

    @Override
    public Produto getById(Long id) throws ExceptionDAO {
        return produtoDao.getById(id);
    }

    @Override
    public List<Produto> getTodos() throws ExceptionDAO {
        return produtoDao.getTodos();
    }

    @Override
    public List<Produto> getProdutoPorNome(String nome) throws ExceptionDAO {
        return produtoDao.getProdutoPorNome(nome);
    }

    @Override
    public void realizarCadastro(Long id, String nomeProduto, Calendar dataValidade,
            String unidadeMedida, int reposicao, int quantidadeInicial, int quantidadeFinal,
            Long codigoDeBarras, String tipoProduto, String composicaoProduto,
            Fornecedor f) throws ExceptionDAO {

        UtilitariosStrategy utilitarios = new UtilitariosStrategy();
        Produto p = new Produto();
        p.setId(id);
        p.setNomeProduto(nomeProduto);
        p.setDataValidade(dataValidade);
        p.setDataRegistro(Calendar.getInstance());
        p.setUnidadeMedida(unidadeMedida);
        p.setReposicao(reposicao);
        p.setQuantidadeInicial(quantidadeInicial);
        p.setQuantidadeFinal(quantidadeFinal);
        p.setCodigoDeBarras(codigoDeBarras);
        p.setComposicaoProduto(composicaoProduto);
        p.setTipoProduto(tipoProduto);
        p.setFornecedor(f);

        if (id == null) {
            p.setQuantidadeFinal(quantidadeInicial);
            salvar(p);
            JOptionPane.showMessageDialog(null, MensagensUtilStrategy.getValor(MensagensUtilStrategy.MSG_SUCESSO_SALVAR, ""));

        } else {
            atualizar(p);
            JOptionPane.showMessageDialog(null, MensagensUtilStrategy.getValor(MensagensUtilStrategy.MSG_SUCESSO_SALVAR, "  "));

        }

    }

    @Override
    public Produto addItemProdutoDoEstoque(Long ip, String tipoProduto, String descricao, String composicao, int quantidade, Fornecedor f) {

        Produto p = new Produto();

        p.setId(ip);
        p.setTipoProduto(tipoProduto);
        p.setComposicaoProduto(composicao);
        // p.setDescricao(descricao);
        p.setQuantidadeInicial(quantidade);
        p.setFornecedor(f);
        return p;

    }

    @Override
    public Produto getProdutoPorCodigoBarras(int codigo) throws ExceptionDAO {
        return produtoDao.getProdutoPorCodigoBarras(codigo);
    }

    public void gerarTabelaProduto(JTable tabela) throws BusinessException {

        UtilitariosStrategy utilitaro = new UtilitariosStrategy();
        DefaultTableModel modelo = new DefaultTableModel();
        Produto f = new Produto();

        modelo = (DefaultTableModel) tabela.getModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Validade");
        modelo.addColumn("Medida");
        modelo.addColumn("Fornecedor");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Data Registro");

        //Definindo o tamanho das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(50);

        //Definindo tamanho 0 a coluna ID
//        tabela.getColumnModel().getColumn(0).setMinWidth(0);
//        tabela.getColumnModel().getColumn(0).setPreferredWidth(0);
//        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
        tabela.setRowHeight(25);
        modelo.setNumRows(0);

        List<Produto> listaProduto = new ArrayList<Produto>();

        try {
            listaProduto = getTodos();
        } catch (Exception ex) {

        }

        if (!listaProduto.isEmpty()) {

            for (int i = 0; i < listaProduto.size(); i++) {
                Vector linha = new Vector();
                linha.add(listaProduto.get(i).getId());
                linha.add(listaProduto.get(i).getNomeProduto());
                String data = utilitaro.pasarCalendarParaString(listaProduto.get(i).getDataValidade());
//                linha.add(listaProduto.get(i).getDataValidade());
                linha.add(data);
                linha.add(listaProduto.get(i).getUnidadeMedida());
                linha.add(listaProduto.get(i).getFornecedor().getNome());
                linha.add(listaProduto.get(i).getQuantidadeFinal());
                String data2 = utilitaro.pasarCalendarParaString(listaProduto.get(i).getDataRegistro());
                linha.add(data2);
//                linha.add(listaProduto.get(i).getDataRegistro().getTime());
                modelo.addRow(linha);

            }

        }

    }

    @Override
    public void remover(Produto p) throws ExceptionDAO {
        produtoDao.remover(p);
    }

}
