package com.devcaotics.guardeinocorazon.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.guardeinocorazon.model.entities.Desafeto;

public class RepositorioDesafeto
{
	private static RepositorioDesafeto myself = new RepositorioDesafeto();
	
	private RepositorioDesafeto() {}
	
	public static RepositorioDesafeto getCurrentInstance()
	{
		return myself;
	}
	
	public void create(Desafeto desafeto) throws SQLException
	{
		String sql = "INSERT INTO Desafeto(nome, descricao) VALUES(?, ?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, desafeto.getNome());
		pstm.setString(2, desafeto.getDescricao());
		
		pstm.execute();
	}
	
	public Desafeto read(long id)
	{
		String sql = "SELECT * FROM Desafeto WHERE id=?";
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setLong(1, id);
			
			ResultSet result = pstm.executeQuery();
			Desafeto desafeto = null;
			
			if (result.next())
			{
				desafeto = new Desafeto();
				
				desafeto.setId(id);
				desafeto.setNome(result.getString("Nome"));
				desafeto.setDescricao(result.getString("Descricao"));
			}
			
			return desafeto;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Desafeto> readAll()
	{
		String sql = "SELECT * FROM Desafeto";
		List<Desafeto> desafetos = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			
			Desafeto desafeto = null;
			
			while (result.next())
			{
				desafeto = new Desafeto();
				
				desafeto.setId(result.getLong("Id"));
				desafeto.setNome(result.getString("Nome"));
				desafeto.setDescricao(result.getString("Descricao"));
				
				desafetos.add(desafeto);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return desafetos;
	}
}
