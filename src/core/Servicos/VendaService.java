package core.Servicos;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;

import java.util.Arrays;
import java.util.List;

public class VendaService {
    private final ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
    private final List<String> TIPO_PRODUTOS = Arrays.asList(VendaEnum.PAPEL.name(), VendaEnum.LAPIS.name(),VendaEnum.CANETA.name(),VendaEnum.BORRACHA.name());

    public void Setup(){
        for (int i = 0; i < TIPO_PRODUTOS.size(); i++) {
            if (!manipuladorArquivos.arquivoExiste(TIPO_PRODUTOS.get(i) + ".txt")){
                manipuladorArquivos.criaArquivo(TIPO_PRODUTOS.get(i) + ".txt");
            }
        }
    }

    public void RegistrarProduto(Produto produto){
        if (produto.getTipoProduto() == VendaEnum.PAPEL){
            produto.setCor(null);
        }
        else if (produto.getTipoProduto() == VendaEnum.LAPIS || produto.getTipoProduto() == VendaEnum.CANETA ){
            produto.setTamanho(null);
        }
        manipuladorArquivos.escreveArquivo(produto.getTipoProduto().name() + ".txt", produto.toString());
    }

    public List<String> RecuperarProdutos(VendaEnum tipo){
       List<String> produtos = manipuladorArquivos.lerArquivo(tipo.name() + ".txt");
       for(int i = 0; i < produtos.size(); i++){
           System.out.println(produtos.get(i));
       }
       return produtos;
    }
}
