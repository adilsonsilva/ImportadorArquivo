/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.controle;

import br.com.importadorarquivo.controle.service.InterfaceImportador;
import br.com.importadorarquivo.modelo.entidade.Pessoa;
import br.com.importadorarquivo.modelo.entidade.TipoImportacao;
import br.com.importadorarquivo.modelo.persistence.InterfaceDAO;
import br.com.importadorarquivo.util.DAOFactory;
import br.com.importadorarquivo.util.MensagemUtil;
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

      private final InterfaceDAO dao;
      private List<Pessoa> pessoas = new ArrayList<>();
      private Pessoa pessoa;
      private List<Pessoa> listaPessoa = new ArrayList<>();
      private String mascara = "";

      public GetDadosBean() {
            this.dao = DAOFactory.criarDAO(Pessoa.class);
      }

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
                  MensagemUtil.mensagem(FacesMessage.SEVERITY_ERROR, "Erro: ",
                          "Não foi possível carregar os dados . Causa: " + e.getMessage());
            }
      }

      public List<Pessoa> getPessoas() {
            return pessoas;
      }

      public void salvar() {
            for (Pessoa p : pessoas) {
                  this.dao.salvar(p);
            }
      }

      public void deletar() {
            Pessoa p = (Pessoa) this.dao.pesquisarId(pessoa.getId());

            this.dao.deletar(p);
            MensagemUtil.mensagem(FacesMessage.SEVERITY_INFO, "Sucesso: ", "Pagamento excluido ");
      }

      public List<Pessoa> getListaPessoa() {
            return listaPessoa;
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

            RequestContext.getCurrentInstance().openDialog("DlgDados", opcoes, null);
      }

      public void selecionar(Pessoa pessoa) {
            RequestContext.getCurrentInstance().closeDialog(pessoa);
      }

      public void clienteSelecionado(SelectEvent event) {
            this.pessoa = (Pessoa) event.getObject();
      }

      public void mascararCampo() {
           // this.setMascara("999.999.999-99");
      }

      public Pessoa getPessoa() {
            return pessoa;
      }

      public void setPessoa(Pessoa pessoa) {
            this.pessoa = pessoa;
      }
}
