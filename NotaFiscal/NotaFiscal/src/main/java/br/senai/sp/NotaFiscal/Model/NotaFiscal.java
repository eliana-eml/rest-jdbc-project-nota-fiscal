package br.senai.sp.NotaFiscal.Model;

import java.time.LocalDate;
import java.util.List;

public class NotaFiscal {

	private int nr_nota;
	private String cliente;
	private LocalDate data_emissao;
	private List<Item> items;
	
	
//	public float valorTotal (valor * qtd) {
//	return 2,00;
//}

	
	public NotaFiscal() {}
	
	public int getNr_nota() {
		return nr_nota;
	}

	public void setNr_nota(int nr_nota) {
		this.nr_nota = nr_nota;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public LocalDate getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(LocalDate data_emissao) {
		this.data_emissao = data_emissao;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}



}
