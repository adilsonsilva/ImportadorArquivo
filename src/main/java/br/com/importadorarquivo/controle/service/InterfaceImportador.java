/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.importadorarquivo.controle.service;

import br.com.importadorarquivo.modelo.entidade.Pessoa;
import java.util.List;
import java.io.InputStream;

/**
 *
 * @author adilson
 */
public interface InterfaceImportador {
      
      public List<Pessoa> getDadosImportados(InputStream file);
      
}
