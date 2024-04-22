# Spring Boot Expert: JPA, RESTFul API, Security, JWT e Mais
* [Curso Spring Boot Especialista](https://github.com/cursodsousa/curso-spring-boot-especialista)

Primeiros passos instalar:
* Java JDK
* IntelliJ
* MySQL
* Postman
* [Maven](https://maven.apache.org/download.cgi)



## Spring Boot Core e Spring Framework
### Criando um projeto Spring boot do zero no IntelliJ
* Create New Project -> Project SDK: 1.8 Java -> Maven -> Next

* [pom.xml](./vendas/pom.xml): gerenciador do Maven de building para o Java e pode adicionar plugins e dependências etc
  * [Maven Repository](https://mvnrepository.com/)
  * adicionar o parent do Spring Boot, pois o parent é uma biblioteca que vai configurar automaticamente toda a aplicação Spring Boot
  * Pesquisar Spring Boot Starter Parent (quando definimos o PARENT todas as versões da dependência instalarão as versões compatíveis com o parent)
    * Copiar o xml e trocar Dependency por Parent e remove o type pom
    * Adicionar estrutura dependencies
      * definir **groupId** como org.springframework.boot e **artifactId** spring-boot-starter
    * Adicionar estrutura build, plugins e plugin
      * definir **groupId** como org.springframework.boot e **artifactId** spring-boot-maven-plugin

* Criar o arquivo VendasApplication e digitar psvm e o IntelliJ vai criar o método main e colocar a annotation **@SpringBootApplication** e esse annotation vai reconhecer que essa classe que vai inicializar uma aplicação Spring Boot
  * dentro do método main inserir o SpringApplication.run(VendasApplication.class, args);

* Para rodar basta clicar no play ao lado do método main e dar um run


### Hello World com Spring Boot
Para usar a annotation @RestController precisamos adicionar a dependência spring-boot-starter-web e irá configurar o módulo Web do Spring Boot
* Colocar a annotation em cima da classe, que significa que essa classe poderá mandar mensagens para o browser
  * Usar o GetMapping para que quando digitar "/hello", irá executar o método helloWorld()
  * http://localhost:8080/hello


### Starters: Como funciona a mágica por trás do Spring Boot
* Cada starter tem uma série de configurações que vem junto com ele
Starters:
* Data JPA, Security, Test


### Configurations e Beans
* Criar a class MinhaConfiguration
  * Annotation @Configuration é escaneado pelo Spring Boot e aplicar as configurações
  * As configurações são feitas através de Beans
    * com o método applicationName e a annotation Bean com o name de applicationName, está dizendo que é para criar esse objeto String com o valor "Sistema de Vendas" no contexto da aplicação para ser usado onde precisar
    * Poderíamos, por exemplo, fazer uma configuração de conexão com a base de dados, email, etc
  * Annotation @Autowired e @Qualifier (este serve para identificar que ele injete a String do método que possui a Bean na variável String que possui o @Qualifier)





