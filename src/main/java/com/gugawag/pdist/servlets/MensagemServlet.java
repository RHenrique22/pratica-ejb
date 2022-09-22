package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.service.MensagemService;
import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup = "java:module/mensagemService")
    private MensagemService mensagemService;

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
            throws ServletException, IOException {
        String operacao = req.getParameter("operacao");
        PrintWriter resposta = resp.getWriter();

        switch (operacao) {
            case "1": {
                long id = Integer.parseInt(req.getParameter("id"));
                String texto = req.getParameter("texto");
                mensagemService.inserir(id, texto);
                break;
            }
            case "2": {
                for (Mensagem m: mensagemService.listar()) {
                    resposta.println(m.getTexto());
                }
                break;
            }
            case "3":{
                resposta.println(mensagemService.pesquisar(Integer.parseInt(req.getParameter("idPesq"))));
                break;
            }
        }
    }
}
