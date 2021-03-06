package core;

public class Caneta extends EntidadeBase {
	
	public Caneta(String descricao, int qtde, String marca, double preco, int cod, String cor) {
		super(descricao, qtde, marca, preco, cod);
	}	
	

	public static void main(String[] args) {
		Caneta cnt = new Caneta("Caneta preta", 50, "BIC", 1.99, 003, "Preto");
	}

}
