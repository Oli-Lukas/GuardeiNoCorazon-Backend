package com.devcaotics.guardeinocorazon.model.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.guardeinocorazon.model.entities.Desafeto;
import com.devcaotics.guardeinocorazon.model.entities.Desavenca;

public class RepositorioDesavenca
{
	private static RepositorioDesavenca myself = new RepositorioDesavenca();
	private RepositorioDesavenca() {}
	
	public static RepositorioDesavenca getCurrentInstance()
	{ return myself; }
	
	public void create(Desavenca desavenca) throws SQLException
	{
		String sql = "INSERT INTO Desavenca(idDesafeto, titulo, descricao) VALUES(?, ?, ?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setLong(1, desavenca.getDesafeto().getId());
		pstm.setString(2, desavenca.getTitulo());
		pstm.setString(3, desavenca.getDescricao());
		
		pstm.execute();
	}
	
	public List<Desavenca> read(long idDesafeto)
	{
		String sql = "SELECT * FROM Desavenca as desavenca JOIN Desafeto as desafeto ON (desavenca.idDesafeto = desafeto.id) WHERE desafeto.id = ?";
		List<Desavenca> desavencas = new ArrayList<>();
		
		try
		{
			PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
			
			pstm.setLong(1, idDesafeto);
			ResultSet result = pstm.executeQuery();
			
			Desavenca desavenca = null;
			
			while (result.next())
			{
				desavenca = new Desavenca();
				desavenca.setId(result.getLong("id"));
				desavenca.setTitulo(result.getString("titulo"));
				desavenca.setDescricao(result.getString("descricao"));
				
				// Desafeto desafeto = new Desafeto();
				// desafeto.setId(idDesafeto);
				// desafeto.setNome(result.getString("nome"));
				// desafeto.setDescricao(result.getString(7));
				
				// desavenca.setDesafeto(desafeto);
				
				desavencas.add(desavenca);
			}
			System.out.println(desavencas.size());
			
			return desavencas;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
