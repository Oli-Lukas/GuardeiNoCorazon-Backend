package com.devcaotics.guardeinocorazon.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devcaotics.guardeinocorazon.model.entities.Desafeto;
import com.devcaotics.guardeinocorazon.model.repositories.RepositorioDesafeto;

@CrossOrigin("*")
@RestController
@RequestMapping("/desafeto")
public class DesafetoController
{
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Desafeto desafeto)
	{
		try
		{
			RepositorioDesafeto.getCurrentInstance().create(desafeto);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(SQLException e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar Desafeto!");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Desafeto> read(@PathVariable("id") long id)
	{
		Desafeto desafeto = RepositorioDesafeto.getCurrentInstance().read(id);
		
		if (desafeto != null)
		{
			return new ResponseEntity<Desafeto>(desafeto, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Desafeto>> readAll()
	{
		List<Desafeto> desafetos = RepositorioDesafeto.getCurrentInstance().readAll();
		
		return new ResponseEntity<List<Desafeto>>(desafetos, HttpStatus.OK);
	}
}
