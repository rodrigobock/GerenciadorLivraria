# Gerenciador de Livraria - API

API REST moderna para gerenciamento de estoque de livraria, desenvolvida com Spring Boot e Java 17. Este projeto foi evoluído de uma aplicação CLI legada para uma arquitetura escalável e pronta para deploy.

## 🚀 Tecnologias Utilizadas

*   **Java 17**
*   **Spring Boot 3**
*   **Spring Data JPA**
*   **PostgreSQL** (Banco de dados em produção, hospedado no Supabase)
*   **H2 Database** (Banco em memória utilizado apenas nos testes automatizados)
*   **Lombok** (Redução de código boilerplate)
*   **SpringDoc OpenAPI (Swagger)** (Documentação de API)
*   **JUnit 5** (Testes automatizados)
*   **Docker** (Containerização)

## 📋 Funcionalidades

*   **Cadastro de Produtos:** Registro de novos itens no estoque (Papel, Lápis, Caneta, Borracha).
*   **Listagem de Estoque:** Consulta de todos os produtos ou filtros por tipo.
*   **Realização de Vendas:** Baixa automática no estoque ao realizar uma venda.
*   **Documentação Automática:** Interface Swagger para teste dos endpoints.

## 🛠️ Como Executar

### Pré-requisitos
*   Java 17 ou superior
*   Maven 3.x
*   Acesso a uma instância PostgreSQL (ex.: Supabase) com o schema `livraria` e suas tabelas já criadas

### Variáveis de ambiente obrigatórias
A aplicação exige as seguintes variáveis para conectar ao banco:

| Variável | Descrição |
| --- | --- |
| `SPRING_DATASOURCE_URL` | URL JDBC do PostgreSQL (ex.: `jdbc:postgresql://<host>:5432/postgres?currentSchema=livraria`) |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |

### Execução local
1. Defina as variáveis de ambiente acima (no IntelliJ: `Run → Edit Configurations → Environment variables`).
2. Execute:
   ```bash
   mvn spring-boot:run
   ```
   Ou, usando o JAR:
   ```bash
   java -jar target/gerenciador-livraria-1.0-SNAPSHOT.jar
   ```

### Documentação (Swagger)
Após iniciar a aplicação, acesse a documentação interativa em:
`http://localhost:8080/swagger-ui.html`

### Docker
Para rodar via Docker (passando as variáveis de ambiente do banco):
```bash
docker build -t gerenciador-livraria .
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://<host>:5432/postgres?currentSchema=livraria" \
  -e SPRING_DATASOURCE_USERNAME="<usuario>" \
  -e SPRING_DATASOURCE_PASSWORD="<senha>" \
  gerenciador-livraria
```

## 🧪 Testes
Os testes automatizados utilizam um banco H2 em memória (modo PostgreSQL), portanto **não dependem do Supabase nem das variáveis de ambiente**:
```bash
mvn test
```

---
Desenvolvido como projeto de portfólio para demonstrar habilidades em Spring Boot, Clean Code e DevOps.
