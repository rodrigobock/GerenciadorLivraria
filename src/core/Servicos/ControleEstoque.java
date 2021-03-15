package core.Servicos;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;

import java.util.ArrayList;
import java.util.List;

public class ControleEstoque {
	
	private ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
    private ProdutoUtils utils = new ProdutoUtils();
    public String listarEstoque(VendaEnum tipo){

        List<String> produtos = manipuladorArquivos.lerArquivo(tipo.name() + ".txt");

        /*
        produto[0] = Código
        produto[1] = Tipo
        produto[2] = Descricao
        produto[3] = Marca
        produto[4] = Cor
        produto[5] = Tamanho
        produto[6] = Quantidade
        produto[7] = Preco
        */

        int qtde = 0;
        Produto produto;
        String retorno;

        for(int i = 0; i < produtos.size(); i++){
            produto = utils.setProduto(produtos.get(i));
            qtde = qtde + produto.getQuantidade();
        }
                
        retorno = "Quantidade total de " + tipo.name() + " = " + qtde;
        System.out.println(retorno);
        return retorno;
     }
}