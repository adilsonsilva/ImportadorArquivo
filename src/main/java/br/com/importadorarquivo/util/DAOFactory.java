/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.util;

import br.com.importadorarquivo.modelo.persistence.InterfaceDAO;
import br.com.importadorarquivo.modelo.persistence.PersistenceDAO;

/**
 *
 * @author adilson
 */
public class DAOFactory {

      public static InterfaceDAO<Class> criarDAO(Class classe) {
            PersistenceDAO persistenceDAO = new PersistenceDAO(classe);
            persistenceDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
            return persistenceDAO;
      }

}
