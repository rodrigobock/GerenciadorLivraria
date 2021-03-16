package core.Servicos;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;

import java.util.Arrays;
import java.util.List;

public class VendaService {
    private final ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
    private final ProdutoUtils utils = new ProdutoUtils();

    private final List<String> TIPO_PRODUTOS = Arrays.asList(VendaEnum.PAPEL.name(), VendaEnum.LAPIS.name(),VendaEnum.CANETA.name(),VendaEnum.BORRACHA.name());
    private final String TIPO_ACAO_VENDA = "venda";
    private final String NOME_ARQUIVO_REGISTRO_VENDA = "registro_vendas.txt";

    public void Setup(){
        for (int i = 0; i < TIPO_PRODUTOS.size(); i++) {
            if (!manipuladorArquivos.arquivoExiste(TIPO_PRODUTOS.get(i) + ".txt")){
                manipuladorArquivos.criaArquivo(TIPO_PRODUTOS.get(i) + ".txt");
            }
        }
    }
    
	private void CriaRegistro(){

		if (!manipuladorArquivos.arquivoExiste("registro_vendas.txt"))
		{
			manipuladorArquivos.criaArquivo("registro_vendas.txt");
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

    public boolean vendas(VendaEnum tipo, int qtde) {

        CriaRegistro();

        List<String> produtos = manipuladorArquivos.lerArquivo(tipo.name() + ".txt");

        int qtdeItem = 0;
        double valorProduto = 0;
        Produto produto;

        for(int i = 0; i < produtos.size(); i++){
            produto = utils.setProduto(produtos.get(i));
            qtdeItem = qtdeItem + produto.getQuantidade();
            valorProduto = produto.getPreco();
        }

        double valorTotal = valorProduto * qtde;
        if (qtde <= qtdeItem && qtde != 0) {

            System.out.println("Compra realizada com sucesso!");

            String conteudo = "Produto: " + tipo.name() +";\n Quantidade: " + qtde + "; Valor unitário: " + valorProduto + ";\n Valor total: " + valorTotal + ";\n";
            manipuladorArquivos.escreveArquivo(NOME_ARQUIVO_REGISTRO_VENDA, conteudo);

            VendaService service = new VendaService();
            Produto pdt = new Produto();

            pdt.setDescricao(TIPO_ACAO_VENDA);
            pdt.setTipoProduto(tipo);
            pdt.setQuantidade(-qtde);

            service.RegistrarProduto(pdt);
        } else if(qtde == 0){
            System.out.println("Impossível realizar a compra com quantidade zerada!");
        }
        else {
            System.out.println("Estoque insuficiente!");
        }
        return true;
    }

    public String listarEstoque(VendaEnum tipo){

        List<String> produtos = manipuladorArquivos.lerArquivo(tipo.name() + ".txt");

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
