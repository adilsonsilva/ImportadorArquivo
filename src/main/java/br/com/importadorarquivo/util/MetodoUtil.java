/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.importadorarquivo.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author adilson
 */
public class MetodoUtil {

      public static void mensagem(Severity severity, String tipo, String msg) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, tipo, msg));
      }

      public static String mascararCpf(String cpf) {

            StringBuilder stringBuilder = new StringBuilder(cpf);
            stringBuilder.insert(cpf.length() - 2, '-');
            stringBuilder.insert(cpf.length() - 5, '.');
            stringBuilder.insert(cpf.length() - 8, '.');

            return stringBuilder.toString();
      }

      public static String desMascararCpf(String cpf) {

            StringBuilder stringBuilder = new StringBuilder(cpf);
            stringBuilder.delete(cpf.length() - 2, '-');
            stringBuilder.insert(cpf.length() - 5, '.');
            stringBuilder.insert(cpf.length() - 8, '.');

            return stringBuilder.toString();
      }

}
