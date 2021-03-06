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
		produto.setDescricao("123");
		produto.setTipoProduto(VendaEnum.LAPIS);
		produto.setQuantidade(2);
		produto.setMarca("saeads12");
		produto.setPreco(22.22);

		service.RegistrarProduto(produto);
		service.RecuperarProdutos(VendaEnum.LAPIS);
	}
}
