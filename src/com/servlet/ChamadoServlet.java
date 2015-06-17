package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ChamadoDAO;
import com.dao.InstrucaoDAO;
import com.modelodados.Chamado;
import com.modelodados.Instrucao;

/**
 * Servlet implementation class Chamado
 */
@WebServlet("/Chamado")
public class ChamadoServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChamadoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if ("I".equals(acao) || "A".equals(acao)) {
			int id = Integer.parseInt(request.getParameter("edId"));
			String titulo = request.getParameter("edTitulo");
			String descricao = request.getParameter("edDescricao");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			try {
				Date dataCriacao = sdf.parse(request.getParameter("edDataCriacao"));
				Chamado chamado = new Chamado();
				chamado.setId(id);
				chamado.setTitulo(titulo);
				chamado.setDescricao(descricao);
				chamado.setDataCriacao(dataCriacao);

				ChamadoDAO dao = new ChamadoDAO();
				if ("I".equals(acao)) {
					dao.inserir(chamado);
				} else
					if ("A".equals(acao)) {
						dao.alterar(chamado);
					}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			response.sendRedirect("index.jsp");
		} else {
			if ("D".equals(acao)) {
				int codigo = Integer.parseInt(request.getParameter("edId"));
				ChamadoDAO dao = new ChamadoDAO();

				dao.excluir(codigo);
				response.sendRedirect("index.jsp");
			} else {
				if ("Instrucao".equals(acao)){
					InstrucaoDAO dao = new InstrucaoDAO();
					int chamadoid = Integer.parseInt(request.getParameter("ChamadoID"));
					int instrucaoid = Integer.parseInt(request.getParameter("ChamadoID"));
					Instrucao instrucao = dao.obter(chamadoid, instrucaoid);
					PrintWriter writer = response.getWriter();
					writer.print(instrucao.getText());
					writer.flush();
				}else{
					if("novaInstrucao".equals(acao)){
						InstrucaoDAO dao = new InstrucaoDAO();
						Instrucao instrucao = new Instrucao();
						int chamadoId = Integer.parseInt(request.getParameter("ChamadoID"));
						instrucao.setText(request.getParameter("edInstrucao"));
						dao.inserir(chamadoId, instrucao);
					}
				}
			}
		}
	}
}
