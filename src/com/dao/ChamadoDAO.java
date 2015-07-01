package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.banco.MySQLConnection;
import com.modelodados.Chamado;

public class ChamadoDAO {

	public boolean inserir(final Chamado chamado) {
		Connection con = MySQLConnection.getCon();
		String sql = "INSERT INTO test.Chamado VALUES (?,?,?,?)";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, chamado.getId());
			cmd.setString(2, chamado.getTitulo());
			cmd.setString(3, chamado.getDescricao());
			cmd.setDate(4, new Date(chamado.getDataCriacao().getTime()));
			cmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(final int id) {
		Connection con = MySQLConnection.getCon();
		String sql = "DELETE FROM test.Chamado WHERE id=?";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			cmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterar(final Chamado chamado) {
		Connection con = MySQLConnection.getCon();
		String sql = "UPDATE test.Chamado SET titulo=?, descricao=? " + "WHERE Id=?";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setString(1, chamado.getTitulo());
			cmd.setString(2, chamado.getDescricao());
			cmd.setInt(3, chamado.getId());
			cmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Chamado> listar() {
		ArrayList<Chamado> retorno = new ArrayList<Chamado>();
		Connection con = MySQLConnection.getCon();
		try {
			Statement cmd = con.createStatement();
			ResultSet res = cmd.executeQuery("SELECT * FROM test.Chamado");

			while (res.next()) {
				Chamado Chamado = new Chamado();
				Chamado.setId(res.getInt("id"));
				Chamado.setTitulo(res.getString("titulo"));
				Chamado.setDescricao(res.getString("descricao"));
				Chamado.setDataCriacao(res.getDate("dataCriacao"));
				retorno.add(Chamado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public Chamado obter(final int id) {
		Connection con = MySQLConnection.getCon();
		String sql = "SELECT * FROM test.Chamado WHERE id=?";
		try {
			PreparedStatement cmd = con.prepareStatement(sql);
			cmd.setInt(1, id);
			ResultSet rs = cmd.executeQuery();

			if (rs.next()) {
				Chamado chamado = new Chamado();
				chamado.setId(id);
				chamado.setTitulo(rs.getString("titulo"));
				chamado.setDescricao(rs.getString("descricao"));
				chamado.setDataCriacao(rs.getDate("dataCriacao"));
				return chamado;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
