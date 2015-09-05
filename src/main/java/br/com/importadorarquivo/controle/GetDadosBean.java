/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.controle;

import br.com.importadorarquivo.controle.service.InterfaceImportador;
import br.com.importadorarquivo.modelo.entidade.Pessoa;
import br.com.importadorarquivo.modelo.entidade.TipoImportacao;
import br.com.importadorarquivo.modelo.rn.PessoaRN;
import br.com.importadorarquivo.util.MetodoUtil;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author adilson
 */
@ManagedBean(name = "getDadosBean")
@ViewScoped
public class GetDadosBean implements Serializable {

      private List<Pessoa> pessoas = new ArrayList<>();
      private Pessoa pessoa;
      private List<Pessoa> listaPessoa = new ArrayList<>();
      private String mascara = "";

      public void carregaArquivo(FileUploadEvent event) {
            try {
                  InputStream file;
                  String tipoArquivo;
                  file = event.getFile().getInputstream();
                  tipoArquivo = event.getFile().getContentType();

                  TipoImportacao tipo = TipoImportacao.values()[tipoArquivo(tipoArquivo)];

                  InterfaceImportador importador = tipo.obterTipo();
                  this.pessoas = importador.getDadosImportados(file);
            } catch (IOException e) {
                  MetodoUtil.mensagem(FacesMessage.SEVERITY_ERROR, "Erro: ",
                          "Não foi possível carregar os dados . Causa: " + e.getMessage());
            }
      }

      public List<Pessoa> getPessoas() {
            return pessoas;
      }

      public void salvar() {
            PessoaRN pessoaRN = new PessoaRN();
            for (Pessoa p : pessoas) {
                  pessoaRN.salvar(p);
            }
            
            this.pessoas = new ArrayList<>();
      }

      public void deletar() {
            PessoaRN pessoaRN = new PessoaRN();

            Pessoa p = (Pessoa) pessoaRN.pesquisarId(pessoa.getId());

            pessoaRN.deletar(p);
            MetodoUtil.mensagem(FacesMessage.SEVERITY_INFO, "Sucesso: ", "Pagamento excluido ");
      }

      public List<Pessoa> getListaPessoa() {
            PessoaRN pessoaRN = new PessoaRN();
            return listaPessoa = pessoaRN.listar();
      }

      private int tipoArquivo(String type) {
            int retorno = 0;
            switch (type) {
                  case "application/vnd.ms-excel":
                        retorno = 0;
                        break;
                  case "text/plain":
                        retorno = 1;
                        break;
            }
            return retorno;
      }

      public void abrirDialogo() {
            Map<String, Object> opcoes = new HashMap<>();
            opcoes.put("modal", true);
            opcoes.put("resizable", false);
            opcoes.put("contentHeight", 470);
            opcoes.put("contentWidth", 900);

            RequestContext.getCurrentInstance().openDialog("DlgDados", opcoes, null);
      }

      public void selecionar(Pessoa pessoa) {
            RequestContext.getCurrentInstance().closeDialog(pessoa);
      }

      public void clienteSelecionado(SelectEvent event) {
            this.pessoa = (Pessoa) event.getObject();
      }

      public Pessoa getPessoa() {
            return pessoa;
      }

      public void setPessoa(Pessoa pessoa) {
            this.pessoa = pessoa;
      }
}
