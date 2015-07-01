package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

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
			String anexo = request.getParameter("anexo");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			try {
				Date dataCriacao = sdf.parse(request.getParameter("edDataCriacao"));
				Chamado chamado = new Chamado();
				chamado.setId(id);
				chamado.setTitulo(titulo);
				chamado.setDescricao(descricao);
				chamado.setDataCriacao(dataCriacao);
				chamado.setAnexo(anexo);

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
					String file = chamado.getAnexo();
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
				}else{
					if("upload".equals(acao)){
						if (ServletFileUpload.isMultipartContent(request)) {
							int cont = 0;
							ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
							List fileItemsList = null;
							try {
								fileItemsList = servletFileUpload.parseRequest(request);
							} catch (FileUploadException e) {
								e.printStackTrace();
							}
							String optionalFileName = "";
							FileItem fileItem = null;
							Iterator it = fileItemsList.iterator();
							do {
								cont++;
								FileItem fileItemTemp = (FileItem) it.next();
								System.out.println("id inicio:" + fileItemTemp.getFieldName());
								if (fileItemTemp.isFormField()) {
									if (fileItemTemp.getFieldName().equals("file")) {
										optionalFileName = fileItemTemp.getString();
									}
									if (fileItemTemp.getFieldName().equals("nome")) {
										System.out.println("id:" + fileItemTemp.getFieldName());
										System.out.println("campo:" + fileItemTemp.getString());
									}
								} else {
									fileItem = fileItemTemp;
								}
								if (cont != (fileItemsList.size())) {
									if (fileItem != null) {
										String fileName = fileItem.getName();
										if (fileItem.getSize() > 0) {
											if (optionalFileName.trim().equals("")) {
												fileName = FilenameUtils.getName(fileName);
											} else {
												fileName = optionalFileName;
											}
											String dirName = request.getServletContext().getRealPath("/");
											File saveTo = new File(dirName + fileName);
											System.out.println("caminho: " + saveTo.toString());
											try {
												fileItem.write(saveTo);
											} catch (Exception e) {
											}
										}
									}
								}
							} while (it.hasNext());
						}
					}
				}
			}
		}
	}
}
