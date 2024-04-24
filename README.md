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


### O Container IOC do Spring Framework
* IOC (Inversion of Control)
<img src="./assets/container-ioc.jpg">

* Por exemplo, as classes com o annotation @Repository cria instâncias com escopo Singleton e fica disponível dentro do container e disponível para uso onde precisar

* Além dos itens na imagem acima, a outra forma de fazer o Spring Boot escanear é usando a annotation @ComponentScan(), que especifica quais os componentes de classe de configuração e o objeto que tem que escanear
  * Lembrando que caso o ComponentScan não seja utilizado, os @Service, @Repository etc serão escaneados da mesma forma, sem precisar informar no ComponentScan. Pois tudo o que está abaixo do pacote io.github.rafaelpeinado será escaneado.
  * O ComponentScan vale a pena usar quando vamos usar um pacote de terceiro e o ComponentScan poderá escanear esse pacote com.umaoutrabiblioteca.projeto


### Injeção de Dependências
* O ClientesRepository é uma dependência do ClientesService, porém no primeiro exemplo instanciamos esse objeto com new ClientesRepository();
  * Por exemplo, nesse caso de acessar o banco de dados, isso é uma tarefa complicada e pesada. Se toda vez que fizermos uma ação que conecta ao banco de dados e criar uma nova instância, podemos sobrecarregar o sistema.
  * Com a Injeção de Dependências isso seria resolvido, pois apenas uma classe ClientesRepository ficará disponível
* Para fazer a injeção criamos um construtor e colocamos a annotation @Autowired para informar que há uma injeção naquele construtor
  * No construtor podemos omitir o @Autowired, pois como a classe já está com annotation de @Service, ele já entende que o construtor está injetando uma dependência
* Também podemos injetar diretamente na variável repository, colocando o @Autowired em cima da variável e remover o construtor
* Ou pelo setRepository


### Configuração externalizada: Application Properties
* O arquivo application.properties na pasta resources já é esperado pelo Spring Boot
  * Podemos definir configurações como, por exemplo, o nome da aplicação e usar o annotation @Value e informa a chave criada no [application.properties](./vendas/src/main/resources/application.properties)

* [Common Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)
  * Configurações convencionada:
    * server.port: define a porta que quer executar a aplicação
    * server.servlet.context-path: informa o caminho para o servidor
  * agora o programa irá rodar com localhost:8081/sistema-vendas
* Podemos criar customizações do ambiente, como perfil para teste, produção e dev


### Trabalhando com múltiplos ambientes e profiles
* criar os arquivos com a convenção ainda
  * application-development.properties
  * application-production.properties
* spring.profiles.active: para definir em qual ambiente a aplicação está rodando
  * no caso dos exemplos eram **production** ou **development**

* Também podemos usar os profiles para definir quais configurações funcionarão no ambiente de produção ou ambiente de desenvolvimento.
  * No exemplo, modificamos o arquivo [MinhaConfiguration.java](./vendas/src/main/java/io/github/rafaelpeinado/MinhaConfiguration.java) com a annotation @Profile("development")
  * Criamos uma Bean com o CommandLineRunner que tem a função de quando a aplicação subir o Spring Boot vai procurar todos os @Beans que são CommandLineRunners e vai executar o código que tem dentro dele, que é um código que executa sempre que a aplicação inicializa


### Criando annotations customizadas de configuração
* Criar um arquivo do tipo annotation Development e importar as annotation @Configuration e @Profile("development"), além das annotations obrigatórias de quando estamos criando uma annotation:
  * **@Target(ElementType.TYPE):** será elegível apenas para classe, pois só classe pode ser Configuration
  * **@Retention(RetentionPolicy.RUNTIME)**

Para os exemplos da annotation de Gato e Cachorro foi usado o **@Target(ElementType.FIELD)**, pois é uma variável

Durante o exemplo deu erro, pois existem duas Beans do CommandLineRunner com o nome executar, sendo assim para diferenciar essas beans, uma das beans foi nomeada [executarAnimal]("./vendas/src/main/java/io/github/rafaelpeinado/VendasApplication.java")



## Persistência e Acesso a Dados com Spring Data JPA
### Configurando e obtendo conexões com bases de dados
* Para começar a trabalhar com conexão de base de dados a dependência que precisamos instalar é **spring-boot-starter-data-jpa** e o driver do banco de dados **com.h2database h2**

No [application.properties](./vendas/src/main/resources/application.properties):
* spring.datasource.url=jdbc:h2:mem:testdb
* spring.datasource.driverClassName=org.h2.Driver
* spring.datasource.username=sa
* spring.datasource.password=password
* spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
* spring.h2.console.enabled=true
* spring.h2.console.path=/h2-console

E para rodar esse console, precisaremos instalar a dependência **spring-boot-starter-web**

Por padrão, o Spring Boot utiliza o [HikariCP](https://github.com/brettwooldridge/HikariCP) como data source, como pool de conexões padrão


### Scripts de criação do banco de dados
Como estamos usando o H2 e ele é um banco de memória, ou seja, toda vez que a aplicação for finalizada, perdemos os dados. Sendo assim, foi criado o arquivo [data.sql](./vendas/src/main/resources/data.sql) para armazenar os scripts do banco de dados


### Criando as classes de modelo
* A primeira classe será domain.entity.Cliente
* Usamos BigDecimal para representação monetária


### Salvando e recuperando clientes
* Agora vamos criar os repositories ou DAO (Data Access Object), é uma classe que encapsula todas as operações de uma entidade

* A annotation @Repository recebe o annotation @Component e também informa para o Spring Boot, que essa classe vai fazer operações na base de dados e os Exception Translator
  * Caso não queira usar o @Repository, pode-se usar o @Component

Iremos usar o JdbcTemplate que já vem com as conexões configurada e usamos o @Autowired para injetar o JdbcTemplate e este permite fazer operações na base de dados
* usamos o método **update()** para inserir, atualizar ou deletar no banco de dados 
* usamos o método **query()** para consultar o banco de dados, e o segundo parâmetro precisa ser um **RowMapper**, que mapeia o resultado o banco de dados para uma classe

* System.out::println: **::** é método de reference do Java 8


### Concluindo o cadastro de clientes
Implementado métodos deletar e atualizar.


### Mapeando a Entidade Cliente para JPA
* Para sinalizar que é uma entidade JPA, devemos usar a annotation @Entity e, se quiser, @Table
  * A @Table não é obrigatória, a menos que o nome da entidade seja diferente do nome da tabela
  * por exemplo, se a tabela se chamasse tb_cliente, seria necessário usar o @Table(name = "tb_cliente") e também podemos definir qual schema vamos usar
* **@Id:** para definir qual a primary key da entidade
* **@GeneratedValue:** para auto incremento
* **@Column:** também é opcional igual ao annotation @Table, pois se o nome da propriedade for igual ao da coluna, não é necessário usar.
  * É possível definir se o campo é único, nullable, etc



