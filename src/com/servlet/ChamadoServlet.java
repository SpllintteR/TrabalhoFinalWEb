package com.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ChamadoDAO;
import com.modelodados.Chamado;

/**
 * Servlet implementation class Chamado
 */
@WebServlet("/Chamado")
public class ChamadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
				if ("DownloadAnexo".equals(acao)) {
					int codigo = Integer.parseInt(request.getParameter("edId"));
					ChamadoDAO dao = new ChamadoDAO();
					Chamado chamado = dao.obter(codigo);
					List<String> files = chamado.getAnexos();
					for(String file: files){
						String fileName = "C:\\Anexos\\" + file.substring(file.lastIndexOf("/") + 1);
						response.setContentType("APPLICATION/OCTET-STREAM");
						response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
						InputStream is = null;
						OutputStream out2 = null;
						try {
							out2 = response.getOutputStream();
							is = new FileInputStream(file);
							System.out.println(is);
							int i;
							while ((i = is.read()) != -1) {
								out2.write(i);
							}
						} finally {
							if (out2 != null) {
								out2.flush();
								out2.close();
							}
							if (is != null) {
								is.close();
							}
						}
					}
				}
			}
		}
	}
}
