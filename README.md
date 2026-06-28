# Sistema de Hospedagem

Projeto da disciplina de Programação Modular, composto por um backend em **Spring Boot** (Java 17 + MySQL) e um frontend em **React** (Vite).

## Pré-requisitos

- [Java 17](https://adoptium.net/)
- [Docker](https://www.docker.com/) e Docker Compose
- [Node.js](https://nodejs.org/) (versão 18+) e npm

## 1. Banco de dados (MySQL via Docker)

O backend depende de um banco MySQL, que é provisionado via Docker Compose.

```bash
cd backend/demo
docker compose up -d
```

Isso cria o container `demo-mysql`, expõe a porta `3306` e cria automaticamente o banco `tp_hospedagem`.

Para parar o banco:

```bash
docker compose down
```

## 2. Backend (Spring Boot)

A partir da pasta `backend/demo`, execute:

```bash
./mvnw spring-boot:run
```

> No Windows, use `mvnw.cmd spring-boot:run`.

O backend sobe por padrão em `http://localhost:8080` e já está configurado para usar o perfil `mysql` (`spring.profiles.active=mysql`), conectando-se ao banco criado no passo anterior.

## 3. Frontend (React + Vite)

Em outro terminal, a partir da pasta `vite-project`:

```bash
cd vite-project
npm install
npm run dev
```

A aplicação ficará disponível em `http://localhost:5173`.

## Resumo dos comandos

```bash
# Terminal 1 - banco de dados
cd backend/demo
docker compose up -d

# Terminal 2 - backend
cd backend/demo
./mvnw spring-boot:run

# Terminal 3 - frontend
cd vite-project
npm install
npm run dev
```

## Documentação adicional

Mais detalhes sobre a arquitetura do backend e os artefatos do projeto (modelagem, cartões CRC, protótipos) estão na pasta [docs](docs/README.md).
