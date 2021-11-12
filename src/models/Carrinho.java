package models;

import java.util.ArrayList;

public class Carrinho {

	private ArrayList<Produto> lista_de_produtos;
	
	public Carrinho() {lista_de_produtos = new ArrayList<Produto>();}

	public ArrayList<Produto> getLista_de_produtos() {
		return lista_de_produtos;
	}

	public void setLista_de_produtos(Produto prod) {
		this.lista_de_produtos.add(prod);
	}
	
	public void limparCarrinho() {
		this.lista_de_produtos = new ArrayList<Produto>();
	}
	
}
