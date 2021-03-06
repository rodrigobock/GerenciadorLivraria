package core;

public class Lapis extends EntidadeBase {
	
	public Lapis(String descricao, int qtde, String marca, double preco, int cod, String cor) {
		super(descricao, qtde, marca, preco, cod);
	}	
	

	public static void main(String[] args) {
		Lapis lps = new Lapis("Lapis de Cor - 8 Cores", 10, "FaberCastell", 19.90, 002, "8 Cores");
	}

}
