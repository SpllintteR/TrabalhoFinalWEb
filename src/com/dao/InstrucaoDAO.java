package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banco.MySQLConnection;
import com.modelodados.Instrucao;

public class InstrucaoDAO {

	public Instrucao obter(final int chamadoid, final int instrucaoid) {
		Connection con = MySQLConnection.getCon();
		String sql = "SELECT text FROM test.Instrucao WHERE ChamadoId=? and InstrucaoID=?";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, chamadoid);
			cmd.setInt(2, instrucaoid);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Instrucao instrucao = new Instrucao();
				instrucao.setId(instrucaoid);
				instrucao.setText(rs.getString("text"));
				return instrucao;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
