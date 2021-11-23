README.md# Example

Este é um projeto do Spring Boot.

## Público-alvo
Voltado para arquitetos de software, líderes de equipe, desenvolvedores em geral...

## Objetivo
A arquitetura de micro-serviços já vêm em constante ascensão há um tempo. Aprenda o que são, como é o panorama atual e as práticas e metodologias disponíveis para entrar ou crescer na área mais ágil e dinâmica da programação.

## Recursos utilizados

  - Arquitetura de micro-serviços
  - REST, RESTful
  - Spring MVC
  - Spring Data
  - Spring Data REST
  - Spring Profiles
  - Spring Cloud Config
  - Eureka - Descoberta de serviços
  - Ribbon - Balanceamento de carga
  - Feign - REST client
  - Security - OAuth2 e JWT
  - Zuul - API Gateway


## Resultado Esperado
Compreensão a definição de uma arquitetura de micro-serviços, e entenda os benefícios e problemas enfrentados na adoção da mesma;
Implemente APIs RESTful utilizando Spring Boot, com suporte a persistência de dados, segurança, e boas práticas de programação;
Implemente micro-serviços com Spring Cloud e com o suporte dos projetos Netflix OSS.

## Configurações de instalação
- Java
	- Versão utilizada Java SE Development Kit 8.
	* Configurar variável de ambiente
- Maven
	- Versão utilizada 3.5.4.
	* Configurar variável de ambiente
- IDE
	- Spring Tool Suite(STS), Intellij, Visual Studio Code, etc.
	
- MySQL. 
	- Utilize o MySQL para realizar o download da versão 5.6 ou 5.7 https://dev.mysql.com/downloads/mysql/5.7.html.
	- Realize a instalação e crie um usuário root e senha root. 
	- Adicione um novo schema de nome example.
	- Ainda no MySQL Workbench no menu Users and Privileges crie um usuário example com acesso total e com a seguinte senha 3x4mpl3.
	- Para acessar local o banco por outro gerenciador basta colocar as seguintes credenciais:
		host: localhost 
		port: 3306
		database: example
		user: example
		password: 3x4mpl3


## Compilar

- Com o Maven, você pode compilar e empacotá-lo sem testes usando
	- Vá para a pasta raiz do projeto spring-boot-microservices e execute o seguinte comando: mvn clean compile -DskipTests.
	* Este procedimento se estende por certo um certo tempo, logo aguarde a conclusão.

- Abra a sua IDE para importa o projeto
	- Importe a pastas spring-boot-microservices utilizando o Import Maven Projects da IDE.

- Finalizada a importação dos microservices, agora é necessário importar a base de dados
	- Na pasta spring-boot-microservices, verifique se está no repositório git dev e execute o comando mvn flyway:migrate com isso vai realizar a importação da base contendo o DDL e DML que leva por volta de 3 minutos.

## Run it

### Microservice config

- O Spring Cloud Config fornece suporte do lado do servidor e do lado do cliente para configuração externalizada em um sistema distribuído. 
  Com o Config Server, você tem um local central para gerenciar propriedades externas para aplicativos em todos os ambientes.
  - Efetue o clone de https://bitbucket.org/WILJA/spring-boot-microservices-conf/ e coloque eu seu repositório pois as configurações dele são de extrema importância para os microservices.
    * No microservice config no arquivo application.yml altere as seguintes informações do git:
      * uri: https://bitbucket.org/SEUREP/spring-boot-microservices-conf/
      * username: USERADMINREP
      * password: PASSWORKADMINREP

### Para iniciar os microservices existem 3 formas que são: Docker, Maven e IDE, neste caso procure por Run/Debug configurations.
- Configurar os microservices que for utilizar com o profile dev, abaixo alguns exemplos:
     - config 
     - eureka
     - security
     - core
     - zuul
	  * Caso a IDE for o Intellij adicione a VM OPTIONS a seguinte informação: -Dspring.profiles.active=dev. Repita este procedimento do microservice config até o microservice zuul.
	  * Caso a IDE for o STS procure por Boot Dashboard. Clique com o botão direito no primeiro microservico config e será apresentada uma tela para selecionar o Profile
        escreva dev e de um Apply e após feito isso um Close. Repita este procedimento até o microservico Zuul.

- Sempre iniciar os microservices pela seguinte ordem: 
     - config
     - eureka
     - security
     - aluno-service 8080
     - disciplina-service 8081
        - zuul
         * Caso necessite de outro microservice inicie ele antes do zuul.
	
- Pronto os microservices vão estar disponíveis nas seguintes portas:
     - config 8888
     - eureka 8761
     - security 9999
     - aluno-service 8080
     - disciplina-service 8081
     - zuul 8000

- Utilize o comando abaixo caso for iniciar os microservices via terminal.
```
mvn spring-boot:run
```
### Login:

- Acesse localhost:8080/index.html
- Via client credentials
```
Token Name - example
Grant Type - Client Credentials
Access Token URL - http://localhost:8080/security/oauth/token
Client ID - example
Client Secret - 5jr6uIUzfaIW0iVHDo2dMB6yu3TlTnsN
Scope - e.g. read:org
Client Authentication - Send as Basic
```
- Via usuário
```
INSERT INTO nexti.oauth2_client_details
(id, client_id, access_token_validity, client_secret, grant_types, redirect_uris, refresh_token_validity, resources, scopes, secret_required, customer_id)
VALUES(3, 'frontend', 86400, 'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ', ''password'', NULL, 86400, NULL, 'openid', 1, NULL);

POST - http://localhost:8080/security/oauth/token?grant_type=password&username=william@example.com&password=123
TYPE - BASIC AUTH
USERNAME - frontend
PASSWORD - eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ


```

## Referências
- https://github.com/spring-cloud
- https://github.com/rcandidosilva
- https://www.baeldung.com/spring-microservices-guide
