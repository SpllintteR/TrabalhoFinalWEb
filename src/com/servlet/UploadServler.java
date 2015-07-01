package com.servlet;

import java.io.File;
import java.io.IOException;
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

/**
 * Servlet implementation class Chamado
 */
@WebServlet("/Chamado")
public class UploadServler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServler() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
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
