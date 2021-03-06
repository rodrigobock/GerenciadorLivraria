package core;

public class Borracha extends EntidadeBase {
	
	public Borracha(String descricao, int qtde, String marca, double preco, int cod, String cor,String tamanho) {
		super(descricao, qtde, marca, preco, cod);
	}	
	

	public static void main(String[] args) {
		Borracha borracha = new Borracha("Borracha grande fc", 100, "FaberCastell", 2.99, 004, "branca","Grande");
	}

}
