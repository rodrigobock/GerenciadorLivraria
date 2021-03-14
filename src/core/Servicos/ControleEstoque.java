package core.Servicos;

import core.Entidades.VendaEnum;

import java.util.ArrayList;
import java.util.List;

public class ControleEstoque {
	
	private final ManipuladorArquivos manipuladorArquivos = new ManipuladorArquivos();
	
    public String estoque(VendaEnum tipo){
    	
        List<String> produto = manipuladorArquivos.lerArquivo(tipo.name() + ".txt");
                  
        /*
        array[0] = Código
        array[1] = Tipo
        array[2] = Descricao
        array[3] = Marca
        array[4] = Cor
        array[5] = Tamanho
        array[6] = Quantidade
        array[7] = Preco
        */
        
        int qtde = 0;
        
        String array[] = new String[7];
        String retorno = "";
        
        ArrayList<String> AL = new ArrayList<>();
        
        for(int i = 0; i < produto.size(); i++){
        	
        	AL.add(produto.get(i));
        	
        }
        
        for(int i = 0; i < AL.size(); i++){
        	
        	String ListaProduto = AL.get(i);
            array = ListaProduto.split(";");
                     
            qtde = qtde + Integer.parseInt(array[6]);

        } 
                
        retorno = "Quantidade total de " + tipo.name() + " = " + qtde;
        
        System.out.println(retorno);
           
        return retorno;
     }

}