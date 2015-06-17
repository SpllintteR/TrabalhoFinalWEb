package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public List obter(final int chamadoId) {
		List<Instrucao> retorno = new ArrayList<>();
		Connection con = MySQLConnection.getCon();
		String sql = "SELECT InstrucaoId, text FROM test.Instrucao WHERE ChamadoId=? order by InstrucaoId";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, chamadoId);
			ResultSet rs = cmd.executeQuery();

			while(rs.next()) {
				Instrucao instrucao = new Instrucao();
				instrucao.setId(rs.getInt("InstrucaoId"));
				instrucao.setText(rs.getString("text"));
				retorno.add(instrucao);
			}
			return retorno;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void inserir(final int chamadoId, final Instrucao instrucao){
		Connection con = MySQLConnection.getCon();
		String sql = "INSERT INTO test.Instrucao (ChamadoId, InstrucaoId, text) VALUES (?,(select max(InstrucaoId) from test.Instrucao),?)";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, chamadoId);
			cmd.setString(2, instrucao.getText());
			cmd.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
