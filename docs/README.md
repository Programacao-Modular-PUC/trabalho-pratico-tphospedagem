# Documentações do Projeto
Esta pasta contém todos os artefatos de documentação relacionados ao sistema de hospedagem desenvolvido na disciplina de Programação Modular.

#### Dica para Visualização do PDF - 
_Instale a extensão **PDF Viewer** (por Mathematic) para leitura do PDF dentro do VSCode._

### Link do Arquivo Cartões CRC: 
https://docs.google.com/document/d/19jHdlrO-qYZkckMFnB65L3EL4-TomrirKYYfliwtG9Q/edit?usp=sharing

# Sistema de Hospedagem - Backend Spring Boot

Este módulo implementa a API REST do Sistema de Hospedagem desenvolvido para a disciplina de Programação Modular.

A solução foi organizada com foco em:
- Java 17
- Spring Boot
- API REST
- Arquitetura em camadas
- MySQL em ambiente principal
- H2 em ambiente de testes
- JPA/Hibernate
- Programação Orientada a Objetos
- Herança e polimorfismo para tipos de quarto
- Regras de negócio para reservas e diárias

## 1. Visão geral

O backend atende aos recursos principais do sistema:
- Gerenciamento de residências
- Gerenciamento de quartos
- Cadastro de clientes
- Controle de reservas/alugueis
- Histórico de hospedagens
- Controle de disponibilidade
- Cálculo automático de diárias
- Emissão de recibos

## 2. Arquitetura adotada

A estrutura segue a separação por responsabilidade:

- `controller/` - expõe os endpoints REST
- `service/` - concentra as regras de negócio
- `repository/` - acesso ao banco com Spring Data JPA
- `model/` - entidades e regras de domínio
- `dto/` - contratos de entrada e saída da API
- `config/` - configurações auxiliares
- `exception/` - exceções customizadas e tratamento global

## 3. Estrutura de pacotes

```text
src/main/java/com/example/demo
├── DemoApplication.java
├── config
│   └── ApiConfig.java
├── controller
│   ├── AluguelController.java
│   ├── ClienteController.java
│   ├── QuartoController.java
│   └── ResidenciaController.java
├── dto
│   ├── AluguelRequestDTO.java
│   ├── AluguelResponseDTO.java
│   ├── ClienteRequestDTO.java
│   ├── ClienteResponseDTO.java
│   ├── QuartoRequestDTO.java
│   ├── QuartoResponseDTO.java
│   ├── ResidenciaRequestDTO.java
│   └── ResidenciaResponseDTO.java
├── exception
│   ├── ApiErrorResponse.java
│   ├── BusinessRuleException.java
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── model
│   ├── Aluguel.java
│   ├── Cliente.java
│   ├── Quarto.java
│   ├── QuartoDuplo.java
│   ├── QuartoFamilia.java
│   ├── QuartoIndividual.java
│   └── Residencia.java
│   └── enums
│       ├── AluguelStatus.java
│       └── TipoQuarto.java
├── repository
│   ├── AluguelRepository.java
│   ├── ClienteRepository.java
│   ├── QuartoRepository.java
│   └── ResidenciaRepository.java
└── service
    ├── AluguelService.java
    ├── ClienteService.java
    ├── QuartoService.java
    └── ResidenciaService.java
```

## 4. Regras de negócio implementadas

### 4.1 Diárias

### 4.2 Quartos
Todos os quartos possuem:

### 4.3 Tipos de quarto

Por padrão, o backend sobe com MySQL usando o profile `mysql`.

Para subir o banco localmente, use o Docker Compose do backend e depois inicie a API.

Exemplo:

```bash
cd backend/demo
docker compose up -d
./mvnw spring-boot:run
```


- Não permite berço
- Valor cresce conforme camas adicionais
- Capacidade deve ser proporcional ao número de camas

#### Quarto Duplo
- Voltado para casais
- Pode usar cama comum ou queen/king
- Pode permitir berço
- Taxa adicional para berço e queen/king

#### Quarto Família
- Pode misturar camas e ambientes
- Valor baseado em número de hóspedes
- Desconto progressivo para grupos

### 4.4 Disponibilidade
- Não é permitido reservar um quarto já ocupado no período solicitado.
- A verificação de conflito é feita antes de persistir o aluguel.

### 4.5 Relacionamentos
- Uma `Residencia` possui vários `Quarto`
- Um `Quarto` pertence a uma `Residencia`
- Um `Cliente` possui histórico de `Aluguel`
- Um `Aluguel` pertence a um `Cliente` e a um `Quarto`

## 5. Endpoints REST

### 5.1 Residências
- `GET /residencias`
- `POST /residencias`

Exemplo de requisição:
```json
{
  "nome": "Residência Central",
  "endereco": "Rua A, 100",
  "bairro": "Centro",
  "telefone": "11999999999",
  "imagem": "imagem.jpg"
}
```

### 5.2 Quartos
- `GET /quartos`
- `GET /quartos?residenciaId=1`
- `POST /quartos`

Exemplo de quarto individual:
```json
{
  "tipo": "INDIVIDUAL",
  "residenciaId": 1,
  "valorBase": 120.00,
  "possuiAR": true,
  "possuiHidro": false,
  "capacidadeMaxima": 2,
  "quantidadeCamasSolteiro": 2,
  "taxaCamaAdicional": 20.00
}
```

Exemplo de quarto duplo:
```json
{
  "tipo": "DUPLO",
  "residenciaId": 1,
  "valorBase": 180.00,
  "possuiAR": true,
  "possuiHidro": true,
  "capacidadeMaxima": 2,
  "camaQueenKing": true,
  "permiteBerco": true,
  "taxaBerco": 15.00,
  "taxaQueenKing": 25.00
}
```

Exemplo de quarto família:
```json
{
  "tipo": "FAMILIA",
  "residenciaId": 1,
  "valorBase": 250.00,
  "possuiAR": true,
  "possuiHidro": true,
  "capacidadeMaxima": 5,
  "quantidadeAmbientes": 2,
  "valorPorHospedeAdicional": 30.00,
  "percentualDescontoGrupo": 0.10
}
```

### 5.3 Clientes
- `GET /clientes`
- `POST /clientes`

Exemplo:
```json
{
  "nome": "Maria Silva",
  "cpf": "12345678900",
  "endereco": "Rua B, 200",
  "telefone": "11988887777",
  "email": "maria@email.com"
}
```

### 5.4 Aluguéis
- `GET /alugueis`
- `GET /alugueis?clienteId=1`
- `POST /alugueis`
- `PUT /alugueis/{id}/finalizar`

Exemplo:
```json
{
  "dataEntrada": "2026-05-20T14:00:00",
  "dataSaida": "2026-05-23T13:00:00",
  "clienteId": 1,
  "quartoId": 1,
  "quantidadeHospedes": 2
}
```

## 6. Respostas de erro

A API retorna erros padronizados com esta estrutura:

```json
{
  "timestamp": "2026-05-17T12:00:00",
  "status": 400,
  "error": "Validation Error",
  "message": "nome: Nome da residência é obrigatório",
  "path": "/residencias"
}
```

## 7. Banco de dados

### 7.1 Ambiente principal
O arquivo `src/main/resources/application.properties` ativa o profile `mysql` por padrão:

```properties
spring.application.name=demo
spring.profiles.active=mysql
```

A conexão principal fica em `src/main/resources/application-mysql.properties`.

### 7.2 Ambiente de testes
Os testes usam H2 em memória com o perfil `test`.

Arquivo:
- `src/test/resources/application-test.properties`

## 8. Como executar

### 8.1 Pré-requisitos
- Java 17
- Maven Wrapper disponível no projeto
- Node.js e npm (para o frontend)
- Docker e Docker Compose para subir o MySQL local

### 8.2 Rodar o projeto completo (Frontend + Backend)

#### Passo 1: Inicie o Backend (em um terminal)
```bash
cd backend/demo
docker compose up -d
./mvnw spring-boot:run
```
O backend estará disponível em: **http://localhost:8080**

#### Passo 2: Inicie o Frontend (em outro terminal)
```bash
cd vite-project
npm install  # primeira vez apenas
npm run dev
```
O frontend estará disponível em: **http://localhost:5173**

#### Passo 3: Acesse a aplicação
Abra o navegador e vá para: **http://localhost:5173**

O frontend se conectará automaticamente ao backend em `http://localhost:8080`.

### 8.3 Rodar os testes
```bash
cd backend/demo
./mvnw test
```

### 8.4 Rodar apenas a aplicação backend
```bash
cd backend/demo
./mvnw spring-boot:run
```

## 9. Como testar no Postman

1. Inicie o backend.
2. Cadastre primeiro uma residência.
3. Cadastre um quarto vinculado à residência.
4. Cadastre um cliente.
5. Crie um aluguel usando os ids corretos.
6. Consulte histórico por cliente com `GET /alugueis?clienteId=1`.
7. Finalize o aluguel com `PUT /alugueis/{id}/finalizar`.

## 10. Observações acadêmicas

A implementação privilegia:
- responsabilidade única por classe
- baixo acoplamento
- alta coesão
- orientação a objetos com herança e polimorfismo
- validação de entrada via Bean Validation
- tratamento centralizado de exceções
- persistência com JPA/Hibernate

## 11. Status de testes

O projeto foi validado com sucesso com:
- compilação do backend
- execução de testes automatizados com perfil H2

## 12. Próximos passos sugeridos

- adicionar testes unitários para `AluguelService`
- adicionar testes de integração para os controllers
- integrar o frontend Vite com os novos contratos da API
- incluir paginação e filtros avançados

