package core;

public class Papel extends EntidadeBase {

		public Papel(String descricao, int qtde, String marca, double preco, int cod, String tamanho) {
			super(descricao, qtde, marca, preco, cod);
		}	
		
		public static void main(String[] args) {
			Papel ppl = new Papel("Papel A4", 500, "Tilibra", 24.98, 001, "A4");
		}
}
