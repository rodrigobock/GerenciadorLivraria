package testes;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;
import core.Servicos.ControleEstoque;
import core.Servicos.VendaService;
import core.Servicos.Vendas;

public class Testes {

	public static void main(String[] args) {
				
		VendaService service = new VendaService();
		Produto produto = new Produto();
		ControleEstoque estoque = new ControleEstoque();
		Vendas vendas = new Vendas();		
		
		service.Setup();

		produto.setCodigo(121);
		produto.setTipoProduto(VendaEnum.BORRACHA);
		produto.setDescricao("123");
		produto.setMarca("saeads");
		// produto.setCor();
		produto.setTamanho("Grande");
		produto.setQuantidade(1);
		produto.setPreco(8.99);

		service.RegistrarProduto(produto);
		service.RecuperarProdutos(VendaEnum.LAPIS);
		service.RecuperarProdutos(VendaEnum.BORRACHA);
		

		
		System.out.println("Controle de estoque: ");
		
		estoque.estoque(VendaEnum.BORRACHA);
		estoque.estoque(VendaEnum.LAPIS);
		estoque.estoque(VendaEnum.PAPEL);
		estoque.estoque(VendaEnum.CANETA);

		vendas.vendas(VendaEnum.BORRACHA, 80);
		
	}
}
