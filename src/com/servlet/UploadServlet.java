package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/Upload")
public class UploadServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
	}

	@Override
	protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			int cont = 0;
			ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
			List<String> saveItems = new ArrayList<>();
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
				if (fileItemTemp.isFormField()) {
					if(fileItemTemp.getFieldName().equals("chamadoId")){
						ChamadoDAO dao = new ChamadoDAO();
						int id = Integer.parseInt(fileItemTemp.getString());
						Chamado chamado = dao.obter(id);
						chamado.setAnexos(saveItems);
						dao.alterar(chamado);
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
							String dirName = "C:\\Anexos\\";
							File saveTo = new File(dirName + fileName);
							try {
								fileItem.write(saveTo);
								saveItems.add(fileName);
							} catch (Exception e) {}
						}
					}
				}
			} while (it.hasNext());
		}
	}
}
