/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.controle.service;

import br.com.importadorarquivo.modelo.entidade.Pessoa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author adilson
 */
public class ImportadorTxt implements InterfaceImportador {

      private final List<Pessoa> pessoasImportadas = new ArrayList<>();

      @Override
      public List<Pessoa> getDadosImportados(InputStream file) {
            try {
                  BufferedReader br = null;
                  br = new BufferedReader(new InputStreamReader(file));

                  StringBuilder sb = new StringBuilder();

                  String line;
                  while ((line = br.readLine()) != null) {
                        
                        String nome = line.substring(0, 50);
                        String cpf = line.substring(51, 62); 
                        BigDecimal valor = new BigDecimal(new Double(line.substring(62, 71)));
                        Date data = new Date(line.substring(72, 82));
                        Pessoa p = new Pessoa(nome, cpf, valor, data);
                        pessoasImportadas.add(p);                        
                  }
            } catch (IOException ex) {
                  System.out.println("Erro" + ex.getMessage());
            }
            return pessoasImportadas;
      }
   
   
}
