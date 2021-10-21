package br.senai.sp.NotaFiscal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.NotaFiscal.Model.NotaFiscal;
import br.senai.sp.NotaFiscal.Repository.NotasDAO;

@RestController
@RequestMapping("/notafiscal")
public class NotasController {
	
	@Autowired
	NotasDAO notaDao;
	
	@PostMapping
	public NotaFiscal inserir(@RequestBody NotaFiscal nota) {
		
		return notaDao.inserir(nota);
	}
	
	@PutMapping("/{id}")
	public NotaFiscal alterar(@RequestBody NotaFiscal nota, @PathVariable int id) {
		notaDao.alterar(nota, id);
		
		return nota;
	}
	
	@GetMapping("/{id}")
	public NotaFiscal buscarPorId(@PathVariable int id) {
		return notaDao.buscarPorId(id);
	}
	
	@GetMapping
	public List<NotaFiscal> buscarTodos() {
		System.out.println("passei pelo buscar todos!!");
		return notaDao.buscarTodos();
	}
	
	@DeleteMapping("/{id}")
	public int excluir(@PathVariable int id) {
		notaDao.excluir(id);
		return id;
	}
}
