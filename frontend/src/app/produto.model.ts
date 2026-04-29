export type TipoProduto = 'PAPEL' | 'LAPIS' | 'CANETA' | 'BORRACHA';

export const TIPOS_PRODUTO: TipoProduto[] = ['PAPEL', 'LAPIS', 'CANETA', 'BORRACHA'];

export interface Produto {
  id?: number;
  codigo: number;
  tipoProduto: TipoProduto;
  descricao: string;
  quantidade: number;
  marca: string;
  preco: number;
  cor: string;
  tamanho: string;
}

export const produtoVazio = (): Produto => ({
  codigo: 0,
  tipoProduto: 'PAPEL',
  descricao: '',
  quantidade: 0,
  marca: '',
  preco: 0,
  cor: '',
  tamanho: ''
});
