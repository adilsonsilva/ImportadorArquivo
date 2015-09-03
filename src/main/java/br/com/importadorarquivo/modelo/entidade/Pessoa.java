/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.importadorarquivo.modelo.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 
import javax.persistence.SequenceGenerator; 

/**
 *
 * @author adilson
 */
@Entity
public class Pessoa implements Serializable {

      public Pessoa(String nome, String cpf, BigDecimal salario, Date dataPagamento) {
            this.nome = nome;
            this.cpf = cpf;
            this.salario = salario;
            this.dataPagamento = dataPagamento;
      }

      public Pessoa() {
      }
                 
      @Id
      @SequenceGenerator(name = "SEQ_PESSOA",initialValue = 1, sequenceName = "SEQ_PESSOA")
      @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PESSOA")
      @Column(name="ID_PESSOA")
      private Integer id;
      
      @Column(name="NOME_PESSOA",length = 50)
      private String nome;
      
      @Column(length = 15)
      private String cpf;
      
      @Column(precision = 14, scale = 2)
      private BigDecimal salario;
      
      @Column(name="DATA_PAGAMENTO")
      @Temporal(TemporalType.DATE)
      private Date dataPagamento;

      public Integer getId() {
            return id;
      }

      public void setId(Integer id) {
            this.id = id;
      }

      public String getNome() {
            return nome;
      }

      public void setNome(String nome) {
            this.nome = nome;
      }

      public BigDecimal getSalario() {
            return salario;
      }

      public void setSalario(BigDecimal salario) {
            this.salario = salario;
      }

      public String getCpf() {
            return cpf;
      }

      public void setCpf(String cpf) {
            this.cpf = cpf;
      }

      @Override
      public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.id);
            return hash;
      }

      @Override
      public boolean equals(Object obj) {
            if (obj == null) {
                  return false;
            }
            if (getClass() != obj.getClass()) {
                  return false;
            }
            final Pessoa other = (Pessoa) obj;
            if (!Objects.equals(this.id, other.id)) {
                  return false;
            }
            return true;
      }

      public Date getDataPagamento() {
            return dataPagamento;
      }

      public void setDataPagamento(Date dataPagamento) {
            this.dataPagamento = dataPagamento;
      }
      
}
