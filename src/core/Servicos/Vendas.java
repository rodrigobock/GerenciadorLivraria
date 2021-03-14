package core.Servicos;

import java.util.ArrayList;
import java.util.List;

import core.Entidades.Produto;
import core.Entidades.VendaEnum;

public class Vendas {

	private ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
	
    public boolean vendas(VendaEnum tipo, int qtde) {
    	
    	VendaService criaArquivo = new VendaService();
    	
    	criaArquivo.CriaRegistro();
    	
    	List<String> produto = manipuladorArquivos.lerArquivo(tipo.name() + ".txt");
    	        
        String produtos[] = new String[7];
        ArrayList<String> AL = new ArrayList<>();
        int qtdeItem = 0;
        double valorProduto = 0;
        
        for(int i = 0; i < produto.size(); i++){
        	AL.add(produto.get(i));
        }
        
        for(int i = 0; i < AL.size(); i++){
        	
        	String ListaProduto = AL.get(i);
        	produtos = ListaProduto.split(";");
                     
        	qtdeItem = qtdeItem + Integer.parseInt(produtos[6]);
        	valorProduto = Double.parseDouble(produtos[7]);
 
        } 
        
        double valorTotal = valorProduto * qtde;
        
        if (qtde < qtdeItem) {
			System.out.println("Compra realizada com sucesso!");
			
			String conteudo = "Produto: " + tipo.name() +"; Qtde: " + qtde + "; Valor unitário: " + valorProduto + "; Valor total: " + valorTotal; 
			
			manipuladorArquivos.escreveArquivo("registro_vendas.txt", conteudo);
			
			VendaService service = new VendaService();
			Produto pdt = new Produto();
			
			pdt.setDescricao("venda");
			pdt.setTipoProduto(tipo);
			pdt.setQuantidade(-qtde);
			
			service.RegistrarProduto(pdt);

			
		} else {
			System.out.println("Estoque insuficiente");
		}

    	return true;
    }
}
