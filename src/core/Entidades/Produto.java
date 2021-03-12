package core.Entidades;

public class Produto {
    private int codigo;
    private VendaEnum tipoProduto;
    private String descricao;
    private int quantidade;
    private String marca;
    private double preco;
    private String cor;
    private String tamanho;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public VendaEnum getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(VendaEnum tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String toString() {
        return codigo + ";" + tipoProduto+ ";" +  descricao+ ";" +  marca + ";" + cor + ";" + tamanho + ";" +  quantidade + ";" + preco + ";" +  "\n";
    }
}
