package core;

public class EntidadeBase {
	public String descricao;
	public int quantidade;
	public String marca;
	public double preco;
	private int codigo;
	
	
	public EntidadeBase(String descricao, int qtde, String marca, double preco, int cod) {
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
	
	// Metodos do sistema
	
	// Salvar valores no arquivo externo, precisa verificar se (valor > 0)
	
	public void salvar()
	{
		
	};
	
	// Controle de estoque -> Verifica se quantidade � maior que 0
	
	public void verificaEstoque() {
		
	};
	
	// Registrar itens vendidos
	
	public void registra() {
	
	};
	
	// Listar todos os itens do estoque
	
	public void listaEstoque() {
		
	};
	
	// Lista todos os itens vendidos
	
	public void vendidos() {
		
	};
	
}
