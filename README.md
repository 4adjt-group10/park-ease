
# Sistema de Parquímetro

## Descrição do Projeto

Este projeto apresenta o novo sistema de parquímetro desenvolvido para atender às necessidades de uma cidade turística com uma população de 300.000 habitantes. Durante a alta temporada, a cidade recebe em média 250.000 visitantes adicionais, resultando em um aumento significativo no número de veículos. O sistema antigo, sendo lento, não escalável e não confiável, está sendo substituído por este novo sistema, que foi projetado para lidar com a crescente demanda de estacionamento na cidade.

### Visão Geral do Sistema

O sistema de parquímetro oferece funcionalidades como:
- Registro de condutores e veículos
- Controle de tempo estacionado
- Opções flexíveis de pagamento
- Emissão de recibos

## Tecnologias Utilizadas

- **Java Spring Boot**: Framework para desenvolvimento do backend.
- **MongoDB**: Utilizado para o core do sistema.
- **PostgreSQL**: Usado para armazenar os recibos ao final de cada processo.
- **Docker**: Ferramenta para criar e gerenciar containers.
- **Docker Compose**: Orquestração de containers.

## Como Rodar o Projeto

### Pré-requisitos

- Docker instalado na máquina.

### Passos para Executar

1. Clone o repositório:
    ```bash
    git clone git@github.com:4adjt-group10/park-ease.git
    cd park-ease
    ```

2. Execute o projeto com Docker Compose:
    ```bash
    docker-compose up
    ```

## Acesso às APIs

Após o projeto estar em execução, você pode acessar a documentação e testar as APIs através do Swagger:
- [Swagger UI](http://localhost:8080/swagger-ui/index.html#/)
```angular2html
  http://localhost:8080/swagger-ui/index.html#/
```

## Configuração Debug no InteliJ

1. No menu superior acesse RUN > Edit configuration;
2. Clique em "Add New Configuration";
3. Selecione a configuração (Remote JVM Debug):
    - ![img_1.png](img_1.png)

## Acessos aos Serviços

### Mongo Express

- Acesse: [http://0.0.0.0:8081/db/parkesase/](http://0.0.0.0:8081/db/parkesase/) | [http://127.0.0.1:8081/db/parkesase/](http://127.0.0.1:8081/db/parkesase/)
- Usuário: `admin`
- Senha: `pass`

### PgAdmin

1. Acesse: [http://localhost:5050](http://localhost:5050)
2. Credenciais:
  - Email: `pgadmin4@pgadmin.org`
  - Senha: `admin`
3. Configuração do Servidor:
  - Clique em **Add New Server**
  - **Name**: Nome à sua escolha (Ex: ParkEase)
  - **Connection**:
    - **Host name/address**: `postgres`
    - **Port**: `5050`
    - **Maintenance database**: `mydatabase`
    - **Username**: `myuser`
    - **Password**: `secret`
  - Clique em **Save**

## Configurar local no DataGrip/Dbeaver

#### - **Postgres**
- Clique em **Add New Server**
  - **Name**: Nome à sua escolha (Ex: Postgres-ParkEase)
  - **Connection**:
    - **Host name/address**: `localhost`
    - **Port**: `5432`
    - **Maintenance database**: `mydatabase`
    - **Username**: `myuser`
    - **Password**: `secret`
  - Clique em **Save**

#### - **Mongo**
- Clique em **Add New Server**
  - **Name**: Nome à sua escolha (Ex: Mongo-ParkEase)
  - **Connection**:
    - **Host name/address**: `localhost`
    - **Port**: `27017`
    - **User**: `root`
    - **Password**: `secret`
  - Clique em **Save**

---

# Fluxo de Teste

### 1. Criar preço praticado na rota de admin 
- [http://localhost:8080/swagger-ui/index.html#/administrator-controller/create_1](http://localhost:8080/swagger-ui/index.html#/administrator-controller/create_1)

### 2. Registro de condutor e veiculo: 
obs: pode-se cadastrar o condutor junto do veiculo ou separado. 

#### Cadastro de condutor com veiculo: 
- [http://localhost:8080/swagger-ui/index.html#/driver-controller/createDriver](http://localhost:8080/swagger-ui/index.html#/driver-controller/createDriver)

#### Cadastro apenas de veiculo: 
- [http://localhost:8080/swagger-ui/index.html#/vehicle-controller/create](http://localhost:8080/swagger-ui/index.html#/vehicle-controller/create)

### 3. Iniciando fluxo de estacionamento:

- [http://localhost:8080/swagger-ui/index.html#/parking-meter-controller/parkin](http://localhost:8080/swagger-ui/index.html#/parking-meter-controller/parking)
 - Selecione o tipo de parada (Fixo/Variavel);
 - Selecione o tipo de pagamento (Pix/Cartão);

### 4. Sistema realizara o monitoramento automatico de 10min em 10min 
 - Os avisos de monitoramento são exibidos no terminal da aplicação. 

### 5. Quando o fluxo é encerrado, o sistema gera um recibo com as informações
-  Tempo Estacionado
-  Tarifa aplicada 
-  Valor total pago