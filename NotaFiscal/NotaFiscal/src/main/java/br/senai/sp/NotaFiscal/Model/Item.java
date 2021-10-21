package br.senai.sp.NotaFiscal.Model;

public class Item {
	//Id_Item, Descricao, Valor, Quantidade, Valor Total
	
	private int id_item;
	private String descricao;
	private float valor;
	private int qtd;
	private int nrNota;
	
	public Item() {}

	public Item(int id_item, String descricao, float valor, int qtd) {
		this.id_item = id_item;
		this.descricao = descricao;
		this.valor = valor;
		this.qtd = qtd;
	}
	
//	public float valorTotal (valor * qtd) {
//	return 2,00;
//}

	
	public int getId_item() {
		return id_item;
	}


	public void setId_item(int id_item) {
		this.id_item = id_item;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	public int getQtd() {
		return qtd;
	}


	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	
	public int getNrNota() {
		return nrNota;
	}

	public void setNrNota(int nrNota) {
		this.nrNota = nrNota;
	}
}
