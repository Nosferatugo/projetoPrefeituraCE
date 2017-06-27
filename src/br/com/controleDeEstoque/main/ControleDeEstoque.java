/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.main;

import br.com.controleDeEstoque.commom.exception.BusinessException;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.dao.FuncionarioDAO;
import br.com.controleDeEstoque.dao.ProdutoDAO;
import br.com.controleDeEstoque.facade.Facade;
import br.com.controleDeEstoque.model.Fornecedor;
import br.com.controleDeEstoque.model.Funcionario;
import br.com.controleDeEstoque.model.Pessoa;
import br.com.controleDeEstoque.model.Produto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ControleDeEstoque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BusinessException {
        Facade fachada = Facade.getInstance();
        FuncionarioDAO daof = new FuncionarioDAO();
        
        Funcionario f = new Funcionario();
        Fornecedor forne =  new Fornecedor();
        
        f.setNome("Alan");

        forne.setNome("Antonio");
        
        
        ProdutoDAO pdao = new ProdutoDAO();
        Produto p = new Produto();
        
        try {
//            daof.salvar(f);
//            fachada.salvarFuncionario(f);

//              fachada.salvarFornecedor(forne);

//            List<Funcionario> lista =  fachada.getTodosFuncionarios();
//            System.out.println(fachada.getUltimoFornecedores().getNome());
//for (int i = 0; i < lista.size(); i++) {
//                 System.out.println(fachada.getByIdFornecedor(1L).toString());
//            }
           
    p = pdao.getProdutoPorCodigoBarras(1000000001);
            System.out.println("Nome "+p.getNomeProduto()+"Codigo de barras "+p.getCodigoDeBarras());

        } catch (ExceptionDAO ex) {
            Logger.getLogger(ControleDeEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
