/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.dao;

import br.com.controleDeEstoque.commom.dao.GenericDAO;
import br.com.controleDeEstoque.util.PersistenceUtil;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Onismar
 */
public class ProdutoDAO extends GenericDAO<Produto> implements IProdutoDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;

    @Override
public void remover(Produto p) throws ExceptionDAO {

        manager = PersistenceUtil.getInstance();
        
        try {
            
            manager.getTransaction().begin();
            manager.remove(manager.getReference(Produto.class, p.getId()));
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao salvar no banco de dados");
        } finally {
            manager.close();
        }

    }

    @Override
    public List<Produto> getProdutoPorNome(String nome) throws ExceptionDAO {
        List<Produto> lista = null;
        manager = PersistenceUtil.getInstance();
        //manager = this.getEntityManager();
        manager.getTransaction().begin();
        Criteria criteria = this.getCriteria();
        criteria.add(Restrictions.like("descricao", nome, MatchMode.ANYWHERE).ignoreCase());
        criteria.addOrder(Order.asc("descricao"));
        lista = criteria.list();

        return lista;
    }

    @Override
    public Produto getProdutoPorCodigoBarras(int codigo) throws ExceptionDAO {
        List<Produto> lista = null;
        Produto p = new Produto();
        lista = getTodos();
        for (Produto produto : lista) {
            if (produto.getCodigoDeBarras() != null) {
                if (produto.getCodigoDeBarras() == codigo) {
                    p = produto;
                }
            }
        }

        return p;
    }
}
