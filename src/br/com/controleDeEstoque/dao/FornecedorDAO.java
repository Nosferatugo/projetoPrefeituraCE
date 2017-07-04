/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controleDeEstoque.dao;

import br.com.controleDeEstoque.commom.dao.GenericDAO;
import br.com.controleDeEstoque.commom.exception.ExceptionDAO;
import br.com.controleDeEstoque.model.Fornecedor;
import br.com.controleDeEstoque.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Onismar
 */
public class FornecedorDAO extends GenericDAO<Fornecedor> implements IFornecedorDAO {
//
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;

    @Override
    public void remover(Fornecedor t) throws ExceptionDAO {

        manager = PersistenceUtil.getInstance();
        
        try {
            
            manager.getTransaction().begin();
            manager.remove(manager.getReference(Fornecedor.class, t.getId()));
            manager.getTransaction().commit();
            
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new ExceptionDAO("Erro ao salvar no banco de dados");
        } finally {
            manager.close();
        }

    }
    
}
