/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.util;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

public class ConexaoHibernateFilter implements Filter {

      private SessionFactory sf;

      @Override
      public void init(FilterConfig config) throws ServletException {
            this.sf = HibernateUtil.getSessionFactory();
      }

      @Override
      public void destroy() {

      }

      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException {
            try {
                  this.sf.getCurrentSession().beginTransaction();
                  chain.doFilter(servletRequest, servletResponse);
                  this.sf.getCurrentSession().getTransaction().commit();
            } catch (Throwable ex) {
                  try {
                        if (this.sf.getCurrentSession().getTransaction().isActive()) {
                              this.sf.getCurrentSession().getTransaction().rollback();
                        }
                  } catch (Throwable t) {
                        t.printStackTrace();
                  }
                  throw new ServletException(ex);
            } finally {
                  this.sf.getCurrentSession().close();
            }
      }
}
