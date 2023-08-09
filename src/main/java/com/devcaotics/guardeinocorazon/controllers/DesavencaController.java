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
import com.devcaotics.guardeinocorazon.model.entities.Desavenca;
import com.devcaotics.guardeinocorazon.model.repositories.RepositorioDesafeto;
import com.devcaotics.guardeinocorazon.model.repositories.RepositorioDesavenca;

@CrossOrigin("*")
@RestController
@RequestMapping("/desavenca")
public class DesavencaController
{
	@PostMapping("/{desafetoId}")
	public ResponseEntity<?> create(@RequestBody Desavenca desavenca, @PathVariable("desafetoId") long desafetoId)
	{
		try
		{
			Desafeto desafeto = RepositorioDesafeto.getCurrentInstance().read(desafetoId);
			desavenca.setDesafeto(desafeto);
			
			RepositorioDesavenca.getCurrentInstance().create(desavenca);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(SQLException e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar Desavenca!");
		}
	}
	
	@GetMapping("/{desafetoId}")
	public ResponseEntity<List<Desavenca>> readAll(@PathVariable("desafetoId") long desafetoId)
	{
		List<Desavenca> desavencas = RepositorioDesavenca.getCurrentInstance().read(desafetoId);
		return new ResponseEntity<List<Desavenca>>(desavencas, HttpStatus.OK);
	}
}
