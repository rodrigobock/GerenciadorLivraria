# Gerenciador de Livraria - API

API REST moderna para gerenciamento de estoque de livraria, desenvolvida com Spring Boot e Java 17. Este projeto foi evoluído de uma aplicação CLI legada para uma arquitetura escalável e pronta para deploy.

## 🚀 Tecnologias Utilizadas

*   **Java 17**
*   **Spring Boot 3**
*   **Spring Data JPA**
*   **H2 Database** (Banco em memória para facilidade de teste)
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

### Execução Local (H2)
1. Execute: `mvn spring-boot:run` (usa o perfil `dev` por padrão).

### Execução em Produção (PostgreSQL)
Para rodar usando o banco PostgreSQL do projeto `gz-invest` no schema `gerenciador_livraria`:
1. Certifique-se de definir as variáveis de ambiente `DB_USERNAME` e `DB_PASSWORD`.
2. Execute:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=prod
   ```
   Ou, se estiver usando o JAR:
   ```bash
   java -jar target/gerenciador-livraria-1.0-SNAPSHOT.jar --spring.profiles.active=prod
   ```

### Documentação (Swagger)
Após iniciar a aplicação, acesse a documentação interativa em:
`http://localhost:8080/swagger-ui.html`

### Docker
Para rodar via Docker:
```bash
docker build -t gerenciador-livraria .
docker run -p 8080:8080 gerenciador-livraria
```

## 🧪 Testes
Para executar os testes automatizados:
```bash
mvn test
```

---
Desenvolvido como projeto de portfólio para demonstrar habilidades em Spring Boot, Clean Code e DevOps.
