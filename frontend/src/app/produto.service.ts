import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produto, TipoProduto } from './produto.model';
import { environment } from '../environments/environment';

@Injectable({ providedIn: 'root' })
export class ProdutoService {
  private http = inject(HttpClient);
  private baseUrl = environment.apiUrl;

  listarTodos(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.baseUrl);
  }

  buscarPorTipo(tipo: TipoProduto): Observable<Produto[]> {
    return this.http.get<Produto[]>(`${this.baseUrl}/tipo/${tipo}`);
  }

  criar(produto: Produto): Observable<Produto> {
    return this.http.post<Produto>(this.baseUrl, produto);
  }

  atualizar(id: number, produto: Produto): Observable<Produto> {
    return this.http.put<Produto>(`${this.baseUrl}/${id}`, produto);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  realizarVenda(tipo: TipoProduto, quantidade: number): Observable<string> {
    const params = new HttpParams().set('tipo', tipo).set('quantidade', quantidade);
    return this.http.post(`${this.baseUrl}/venda`, null, { params, responseType: 'text' });
  }
}
