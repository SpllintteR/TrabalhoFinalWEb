<%@page import="java.util.Date"%>
<%@page import="com.dao.ChamadoDAO"%>
<%@page import="com.modelodados.Chamado"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Chamados</title>
</head>
<script>
	function updateInstrucao(val, id){
		var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("txtHint").setAttribute("text", xmlhttp.responseText);
            }
        }
        xmlhttp.open("GET", "Chamado?acao=Instrucao&ChamadoID=" + id + "&InstrucaoID="+ val, true);
        xmlhttp.send();
	}
	
	function novaInstrucao(val, id, chamadoID){
		var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                document.getElementById("txtHint").setAttribute("text", xmlhttp.responseText);
            }
        }
        xmlhttp.open("GET", "Chamado?acao=novaInstrucao&ChamadoID=" + chamadoID + "&InstrucaoID="+ id + "&text=" + val, true);
        xmlhttp.send();
	}
</script>
<body>
	<%
	String acao = request.getParameter("acao");
	boolean alterar = acao.equals("A");
	int instrucaoAtual = 0;
	Chamado chamado = new Chamado();
	if (alterar) {
		int codigo = Integer.parseInt(request.getParameter("edId"));
		ChamadoDAO dao = new ChamadoDAO();
		chamado = dao.obter(codigo);
	} else {
		acao = "I";
		chamado.setTitulo("");
		chamado.setDescricao("");
		chamado.setDataCriacao(new Date());
	}
%>

	<form action="Chamado">
		Id: <input type="text" name="edId" value="<%= chamado.getId() %>" />
			<br />

		Título: <input type="text" name="edTitulo" value="<%= chamado.getTitulo() %>" />
			<br /> 
		
		Descrição: <input type="text" name="edDescricao" value="<%= chamado.getDescricao() %>" />
			<br />

		Data de Criação: <input type="text" name="edDataCriacao"
			value="<%= chamado.getDataCriacao().toLocaleString() %>" />
			<br />
			
		Instruções:
			<input type="text" name="edInstrucao" value="<%= chamado.getInstrucoes().size() > 0 ? chamado.getInstrucoes().get(instrucaoAtual).getText() : "" %>"/>
			<select id="comboInstrucoes" onchange="updateInstrucao(this.value)">  
            	<%for (int i = 0; i < chamado.getInstrucoes().size(); i++) {%>  
                <option value = "<%=i%>"><%=i+1%></option>  
                <%}%>  
        	</select>
        	<button type="button" onclick="novaInstrucao(edInstrucao.value, <%= chamado.getInstrucoes().size() %>,<%= chamado.getId() %>)"> Adicionar Instrução </button>  
			<br />
			<input type="submit" value="Gravar" /><br /> <input type="hidden" name="acao" value="<%= acao %>" />
		}
	</form>
	$('select').change(function () {
  		if ($(this).val() === 'New') {
    	// Handle new option
  	}
});
</body>
</html>