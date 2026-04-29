# Frontend — Gerenciador Papelaria

SPA em **Angular 17 (standalone components)** que consome a API REST do backend e oferece o CRUD básico de produtos da papelaria + simulação de venda em uma única página.

## 🧱 Estrutura

```
frontend/
├── angular.json            # Config do workspace Angular
├── package.json            # Dependências do projeto
├── tsconfig.json           # Config do TypeScript
├── tsconfig.app.json       # Config TS específica da aplicação
└── src/
    ├── index.html          # HTML raiz
    ├── main.ts             # Bootstrap da aplicação standalone
    ├── styles.css          # Estilos globais
    └── app/
        ├── app.component.ts   # Componente principal (template + lógica de UI)
        ├── produto.model.ts   # Tipos: Produto e TipoProduto
        └── produto.service.ts # Cliente HTTP do backend
```

## 🔌 Endpoints consumidos

| Método | Rota                           | Uso na UI                |
|--------|--------------------------------|--------------------------|
| GET    | `/api/produtos`                | Listar todos             |
| GET    | `/api/produtos/tipo/{tipo}`    | Filtrar por tipo         |
| POST   | `/api/produtos`                | Criar produto            |
| PUT    | `/api/produtos/{id}`           | Atualizar produto        |
| DELETE | `/api/produtos/{id}`           | Excluir produto          |
| POST   | `/api/produtos/venda`          | Realizar venda           |

A URL base do backend está hard-coded em `src/app/produto.service.ts` (`http://localhost:8080`). Para apontar para o ambiente em Fly.io, edite essa constante.

## ▶️ Como rodar

### Pré-requisitos
- **Node.js 18+** e **npm**
- Backend Spring Boot rodando em `http://localhost:8080` (ver README da raiz)

### Passo a passo
```bash
cd frontend
npm install
npm start
```
A aplicação sobe em `http://localhost:4200`.

> ℹ️ O backend já tem CORS liberado para `http://localhost:4200` (ver `WebConfig.java`). Para deploy, ajuste as origens permitidas.

## 🧪 Como testar o CRUD
1. **Criar:** preencha o formulário no topo e clique em **Cadastrar**.
2. **Ler:** a tabela atualiza automaticamente. Use o filtro por tipo se desejar.
3. **Atualizar:** clique em **Editar** numa linha — o formulário muda para modo edição.
4. **Excluir:** clique em **Excluir** e confirme.
5. **Venda:** use o card "Realizar venda" para dar baixa no estoque por tipo.
