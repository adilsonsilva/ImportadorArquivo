/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.controle.service;

import br.com.importadorarquivo.modelo.entidade.Pessoa;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.math.BigDecimal;
import java.util.Date;
import jxl.DateCell;
import java.io.InputStream;

/**
 *
 * @author adilson
 */
public class ImportadorXls implements Serializable, InterfaceImportador {

      @Override
      public List<Pessoa> getDadosImportados(InputStream file) {

            List<Pessoa> pessoasImportadas = new ArrayList<>();

            try {
                  Workbook workbook = Workbook.getWorkbook(file);
                  Sheet sheet = workbook.getSheet(0);
                  int linhas = sheet.getRows();

                  for (int i = 0; i < linhas; i++) {
                        Cell nome = sheet.getCell(0, i);
                        Cell cpf = sheet.getCell(1, i);
                        Cell salario = sheet.getCell(2, i);
                        Cell dataPagamento = sheet.getCell(3, i);

                        pessoasImportadas.add(new Pessoa(nome.getContents(), cpf.getContents(),
                                converteEmNumero(salario), converteEmData(dataPagamento)));
                  }
            } catch (IOException | BiffException ex) {
                  System.out.println("Erro: " + ex.getMessage());
            }
            
            return pessoasImportadas;
      }

      private BigDecimal converteEmNumero(Cell valor) {
            NumberCell nc = (NumberCell) valor;
            BigDecimal salario = new BigDecimal(nc.getValue());
            return salario;
      }

      private Date converteEmData(Cell valor) {
            DateCell dc = (DateCell) valor;
            return dc.getDate();
      }

}
