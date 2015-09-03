/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.modelo.persistence;

import br.com.importadorarquivo.modelo.entidade.Pessoa;
import java.util.List;

/**
 *
 * @author adilson
 * @param <T>
 */
public interface InterfaceDAO<T> {
    
    public void salvar(T bean);
    public void atualizar(T bean);
    public void deletar(T bean);
    public List<T> listar();
    public T pesquisarId(Integer id);
    
}
