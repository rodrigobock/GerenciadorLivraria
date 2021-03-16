package testes;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;
import core.Servicos.VendaService;

public class Testes {

	public static void main(String[] args) {
				
		VendaService service = new VendaService();
		Produto produto = new Produto();

		service.Setup();

		produto.setCodigo(121);
		produto.setTipoProduto(VendaEnum.BORRACHA);
		produto.setDescricao("123");
		produto.setMarca("saeads");
		// produto.setCor();
		produto.setTamanho("Grande");
		produto.setQuantidade(10);
		produto.setPreco(8.99);

		service.RegistrarProduto(produto);
		service.RecuperarProdutos(VendaEnum.LAPIS);
		service.RecuperarProdutos(VendaEnum.BORRACHA);



		System.out.println("Controle de estoque: ");

		service.listarEstoque(VendaEnum.BORRACHA);
		service.listarEstoque(VendaEnum.LAPIS);
		service.listarEstoque(VendaEnum.PAPEL);
		service.listarEstoque(VendaEnum.CANETA);

		service.vendas(VendaEnum.BORRACHA, 3);
		service.listarEstoque(VendaEnum.BORRACHA);
	}
}
