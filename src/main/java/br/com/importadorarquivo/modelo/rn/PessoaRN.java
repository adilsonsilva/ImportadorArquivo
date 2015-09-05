/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.importadorarquivo.modelo.rn;

import br.com.importadorarquivo.modelo.entidade.Pessoa;
import br.com.importadorarquivo.modelo.persistence.InterfaceDAO;
import br.com.importadorarquivo.util.DAOFactory;

import java.util.List;

/**
 *
 * @author adilson
 */
public class PessoaRN {
      
      private InterfaceDAO dao;
      
      public PessoaRN(){
            dao = DAOFactory.criarDAO(Pessoa.class);
      }
      
      public void salvar(Pessoa p){
            Integer codigo = p.getId();
            
            if(codigo == null || codigo == 0 ){
                  this.dao.salvar(p);
            }else{
                  this.dao.atualizar(p);
            }
      }
      
      public void deletar(Pessoa p){
            this.dao.deletar(p);
      }
      
      public Pessoa pesquisarId(Integer id){
            return (Pessoa) this.dao.pesquisarId(id);
      }
      
      public List<Pessoa> listar(){
            return this.dao.listar();
      }
      
}
