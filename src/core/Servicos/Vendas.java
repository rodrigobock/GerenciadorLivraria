package core.Servicos;

import java.util.List;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;

public class Vendas {

	private ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
	private ProdutoUtils utils = new ProdutoUtils();
	private String TIPO_ACAO_VENDA = "venda";
	private String NOME_ARQUIVO_REGISTRO_VENDA = "registro_vendas.txt";
    public boolean vendas(VendaEnum tipo, int qtde) {
    	
    	VendaService criaArquivo = new VendaService();
    	
    	criaArquivo.CriaRegistro();
    	
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
}
