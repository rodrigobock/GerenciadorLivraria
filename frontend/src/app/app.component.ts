import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Produto, TipoProduto, TIPOS_PRODUTO, produtoVazio } from './produto.model';
import { ProdutoService } from './produto.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="container">
      <header>
        <h1>📚 Gerenciador de Papelaria</h1>
        <p class="subtitle">CRUD de produtos + simulação de venda</p>
      </header>

      <section class="card">
        <h2>{{ modoEdicao ? 'Editar produto #' + produtoForm.id : 'Cadastrar novo produto' }}</h2>
        <form (ngSubmit)="salvar()" #f="ngForm">
          <div class="grid">
            <label>
              Código
              <input type="number" name="codigo" [(ngModel)]="produtoForm.codigo" required />
            </label>
            <label>
              Tipo
              <select name="tipoProduto" [(ngModel)]="produtoForm.tipoProduto" required>
                <option *ngFor="let t of tipos" [value]="t">{{ t }}</option>
              </select>
            </label>
            <label>
              Descrição
              <input name="descricao" [(ngModel)]="produtoForm.descricao" required />
            </label>
            <label>
              Marca
              <input name="marca" [(ngModel)]="produtoForm.marca" />
            </label>
            <label>
              Quantidade
              <input type="number" name="quantidade" [(ngModel)]="produtoForm.quantidade" min="0" required />
            </label>
            <label>
              Preço
              <input type="number" step="0.01" name="preco" [(ngModel)]="produtoForm.preco" min="0" required />
            </label>
            <label>
              Cor
              <input name="cor" [(ngModel)]="produtoForm.cor" />
            </label>
            <label>
              Tamanho
              <input name="tamanho" [(ngModel)]="produtoForm.tamanho" />
            </label>
          </div>
          <div class="actions">
            <button type="submit" class="primary" [disabled]="f.invalid">
              {{ modoEdicao ? 'Atualizar' : 'Cadastrar' }}
            </button>
            <button type="button" class="ghost" (click)="cancelarEdicao()" *ngIf="modoEdicao">Cancelar</button>
          </div>
        </form>
      </section>

      <section class="card">
        <div class="row-between">
          <h2>Produtos</h2>
          <div class="filtros">
            <select [(ngModel)]="filtroTipo" (change)="aplicarFiltro()">
              <option value="">Todos os tipos</option>
              <option *ngFor="let t of tipos" [value]="t">{{ t }}</option>
            </select>
            <button class="ghost" (click)="recarregar()">↻ Recarregar</button>
          </div>
        </div>

        <p *ngIf="mensagem" class="msg" [class.erro]="erro">{{ mensagem }}</p>

        <table *ngIf="produtos.length > 0; else vazio">
          <thead>
            <tr>
              <th>ID</th>
              <th>Código</th>
              <th>Tipo</th>
              <th>Descrição</th>
              <th>Marca</th>
              <th>Qtd</th>
              <th>Preço</th>
              <th>Cor</th>
              <th>Tamanho</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let p of produtos">
              <td>{{ p.id }}</td>
              <td>{{ p.codigo }}</td>
              <td>{{ p.tipoProduto }}</td>
              <td>{{ p.descricao }}</td>
              <td>{{ p.marca }}</td>
              <td>{{ p.quantidade }}</td>
              <td>{{ p.preco | currency:'BRL':'symbol':'1.2-2' }}</td>
              <td>{{ p.cor }}</td>
              <td>{{ p.tamanho }}</td>
              <td class="acoes">
                <button class="ghost" (click)="editar(p)">Editar</button>
                <button class="danger" (click)="remover(p)">Excluir</button>
              </td>
            </tr>
          </tbody>
        </table>
        <ng-template #vazio>
          <p class="vazio">Nenhum produto cadastrado.</p>
        </ng-template>
      </section>

      <section class="card">
        <h2>Realizar venda</h2>
        <div class="grid-venda">
          <select [(ngModel)]="vendaTipo">
            <option *ngFor="let t of tipos" [value]="t">{{ t }}</option>
          </select>
          <input type="number" min="1" [(ngModel)]="vendaQtd" placeholder="Quantidade" />
          <button class="primary" (click)="vender()">Vender</button>
        </div>
      </section>
    </div>
  `,
  styles: [`
    .container { max-width: 1200px; margin: 0 auto; padding: 32px 20px; }
    header h1 { margin: 0 0 4px; }
    .subtitle { color: #6e6e73; margin: 0 0 24px; }
    .card {
      background: white;
      border-radius: 12px;
      padding: 24px;
      margin-bottom: 20px;
      box-shadow: 0 1px 3px rgba(0,0,0,0.04);
    }
    .card h2 { margin: 0 0 16px; font-size: 18px; }
    .grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
      gap: 14px;
    }
    label { display: flex; flex-direction: column; gap: 6px; font-size: 13px; color: #515154; }
    .actions { margin-top: 16px; display: flex; gap: 10px; }
    .row-between { display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px; }
    .filtros { display: flex; gap: 10px; }
    .filtros select { width: auto; }
    .acoes { display: flex; gap: 8px; }
    .vazio { color: #6e6e73; text-align: center; padding: 30px 0; }
    .grid-venda { display: grid; grid-template-columns: 1fr 1fr auto; gap: 10px; max-width: 500px; }
    .msg { padding: 10px 12px; border-radius: 6px; background: #e8f5e9; color: #1b5e20; }
    .msg.erro { background: #ffebee; color: #b71c1c; }
  `]
})
export class AppComponent implements OnInit {
  private service = inject(ProdutoService);

  tipos = TIPOS_PRODUTO;
  produtos: Produto[] = [];
  produtoForm: Produto = produtoVazio();
  modoEdicao = false;

  filtroTipo: TipoProduto | '' = '';
  vendaTipo: TipoProduto = 'PAPEL';
  vendaQtd = 1;

  mensagem = '';
  erro = false;

  ngOnInit(): void {
    this.recarregar();
  }

  recarregar(): void {
    this.filtroTipo = '';
    this.service.listarTodos().subscribe({
      next: (lista) => (this.produtos = lista),
      error: (e) => this.exibirErro('Falha ao listar produtos: ' + e.message)
    });
  }

  aplicarFiltro(): void {
    if (!this.filtroTipo) {
      this.recarregar();
      return;
    }
    this.service.buscarPorTipo(this.filtroTipo).subscribe({
      next: (lista) => (this.produtos = lista),
      error: (e) => this.exibirErro('Falha ao filtrar: ' + e.message)
    });
  }

  salvar(): void {
    if (this.modoEdicao && this.produtoForm.id) {
      this.service.atualizar(this.produtoForm.id, this.produtoForm).subscribe({
        next: () => {
          this.exibirSucesso('Produto atualizado.');
          this.cancelarEdicao();
          this.recarregar();
        },
        error: (e) => this.exibirErro('Falha ao atualizar: ' + e.message)
      });
    } else {
      this.service.criar(this.produtoForm).subscribe({
        next: () => {
          this.exibirSucesso('Produto cadastrado.');
          this.produtoForm = produtoVazio();
          this.recarregar();
        },
        error: (e) => this.exibirErro('Falha ao cadastrar: ' + e.message)
      });
    }
  }

  editar(p: Produto): void {
    this.produtoForm = { ...p };
    this.modoEdicao = true;
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  cancelarEdicao(): void {
    this.produtoForm = produtoVazio();
    this.modoEdicao = false;
  }

  remover(p: Produto): void {
    if (!p.id) return;
    if (!confirm(`Excluir o produto "${p.descricao}"?`)) return;
    this.service.remover(p.id).subscribe({
      next: () => {
        this.exibirSucesso('Produto removido.');
        this.recarregar();
      },
      error: (e) => this.exibirErro('Falha ao remover: ' + e.message)
    });
  }

  vender(): void {
    if (this.vendaQtd <= 0) {
      this.exibirErro('Quantidade deve ser maior que zero.');
      return;
    }
    this.service.realizarVenda(this.vendaTipo, this.vendaQtd).subscribe({
      next: (msg) => {
        this.exibirSucesso(msg);
        this.recarregar();
      },
      error: (e) => {
        const corpo = typeof e.error === 'string' ? e.error : e.message;
        this.exibirErro(corpo);
      }
    });
  }

  private exibirSucesso(msg: string): void {
    this.mensagem = msg;
    this.erro = false;
    setTimeout(() => (this.mensagem = ''), 3500);
  }

  private exibirErro(msg: string): void {
    this.mensagem = msg;
    this.erro = true;
    setTimeout(() => (this.mensagem = ''), 5000);
  }
}
