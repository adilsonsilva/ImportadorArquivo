/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.modelo.entidade;

import br.com.importadorarquivo.controle.service.ImportadorTxt;
import br.com.importadorarquivo.controle.service.ImportadorXls;
import br.com.importadorarquivo.controle.service.InterfaceImportador;

/**
 *
 * @author adilson
 */
public enum TipoImportacao {

      XLS {
                    @Override
                    public InterfaceImportador obterTipo() {
                          return new ImportadorXls();
                    }
              },
      TXT {
                    @Override
                    public InterfaceImportador obterTipo() {
                          return new ImportadorTxt();
                    }
              };

      public abstract InterfaceImportador obterTipo();

}
