package core.Servicos;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;

public class ProdutoUtils {


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

    public Produto setProduto(String produtoString){
        String produtoSplit[] = produtoString.split(";");
        Produto produto = new Produto();

        produto.setCodigo(Integer.parseInt(produtoSplit[0]));
        produto.setTipoProduto(VendaEnum.valueOf(produtoSplit[1]));
        produto.setDescricao(produtoSplit[2]);
        produto.setMarca(produtoSplit[3]);
        produto.setCor(produtoSplit[4]);
        produto.setTamanho(produtoSplit[5]);
        produto.setQuantidade(Integer.parseInt(produtoSplit[6]));
        produto.setPreco(Double.parseDouble(produtoSplit[7]));

        return produto;
     }
}
