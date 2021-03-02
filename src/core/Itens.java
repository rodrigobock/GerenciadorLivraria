package core;

public class Itens {
	public String descricao;
	public int quantidade;
	public String marca;
	public double preco;
	private int codigo;
	
	
	public Itens(String descricao, int qtde, String marca, double preco, int cod) {
		this.descricao = descricao;
		this.quantidade = qtde;
		this.marca = marca;
		this.preco = preco;
		this.codigo = cod;
	}
	

	// GETTERS AND SETTERS

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}


	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
