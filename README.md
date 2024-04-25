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


### Persistindo entidades com Entity Manager
* Não será mais necessário utilizar o arquivo data.sql para inicializar o banco, pois, por padrão, o JPA já cria os objetos no banco

* O EntityManager já faz o mapeamento dos campos
  * para salvar, usamos o método **persist(objeto)**
  * usar a annotation **@Transactional** da SpringFramework
  * se não usar a annotation, teremos o erro **TransactionRequiredException**


### Refatorando as outras operações para o JPA
* para **atualizar**, usamos o método **merge(objeto)**
* As entidades JPA tem um estado, por exemplo, antes de salvar ela é uma entidade que não foi salva ainda, ou seja, é uma entidade transiente e ele não possui ID, então quando fazemos o persist, ela passa a entrar no status Manager, ou seja, está sendo gerenciado. Então quando salvamos o cliente, ele passa a ser gerenciado pelo Entity Manager e fica lá no cache e dependendo de como vamos recuperar esse dado, ele não precisa nem consultar na base de dados.
  * Então quando fazemos **merge**, significa sincronizar a mudança do dado para com o que está no EntityManager

* para **remover**, basta usar o método **remove(objeto)**
  * por id fazemos, **find(Cliente.class, id)** para encontrar o cliente 

* **@Transactional(readOnly = true):** informa que a transação é somente de leitura. Sendo assim, essa pesquisa passa por algumas otimizações, o que deixa mais rápido.
* **buscaPorNome** vamos fazer uma **pesquisa jpql**: "select c from Cliente c where c.nome = :nome"
  * :nome define o parâmetro do JPA

* No exemplo deu um erro, Removing a detached instance que significa que estamos tentando remover um Cliente transiente
  * sendo assim, implementamos **entityManager.contains** e fazemos o merge para sincronizar os dados


### Introdução aos repositórios Spring Data
* JPA Repositories: interface para ter algumas implementações prontas
  * Já tem o EntityManager encapsulado
  * o save tanto salva quanto atualiza os dados


### Query Methods
* É um método que se transforma em um query
* Ao usar a convenção findBy Nome, ele vai fazer a busca por nome, pois nome é uma propriedade de Cliente
* A consulta é feita em tempo de compilação
* Outros padrões em HQL:
  * List<Cliente> **findByNomeOrId**(String nome, Integer id), lembrando que as propriedades precisam existir em Cliente
  * List<Cliente> **findByNomeLikeOrId**(String nome, Integer id)
  * List<Cliente> **findByNomeOrIdOrderById**(String nome, Integer id)
  * O find é usado para trazer uma coleção de dados
  * para encontrar apenas um cliente, usamos Cliente **findOneByCpf**(String cpf);
  * os parâmetros precisam estar em ordem, de acordo com o que foi colocado no query methods, por exemplo findByNomeOrId os parâmetros precisam ser (String nome, Integer id)
  * podemos retornar um boolean, para ver se o dado existe **existsByNome**(String nome)


### Logando o SQL gerado no console
* Feature do Spring Data JPA, um recurso conhecido do Hibernate ou JPA
  * Mostrar na saída do console o SQL que está sendo gerado nas consultas
* spring.jpa.properties.hibernate.show_sql=true
* spring.jpa.properties.hibernate.format_sql=true


### Trabalhando com @Query
* Como criar queries string para executar dentro do JPA
* Nesse caso podemos dar qualquer nome para o método e devemos inserir a annotation **@Query**
* Precisa inserir a annotation @Param para informar que aquele parâmetro do método está associado ao :nome do value da Query
* Também podemos usar SQL nativo ao invés do HQL
  * desta forma, mudaríamos de: @Query(value = "select c from Cliente c where c.nome like :nome", nativeQuery = true) 
  * para: @Query(value = "select * from cliente c where c.nome like %:nome%", nativeQuery = true)
* Podemos também usar a opção deleteByNome(String nome)
  * ou usar o Query, porém como não é uma consulta, precisamos informar ao Spring que é uma transação com @Modifying


### Mapeando as entidades Produto, Pedido e ItemsPedido
* Foram inseridos os annotations nos atributos
* **CLASSE PEDIDO**
  * Na Classe Pedido vamos fazer a primeira relação entre Cliente e Pedido
    * sendo assim, usamos a annotation **@ManyToOne**, pois temos muitos pedidos para um cliente, então o Many é referente a entidade atual
    * além disso, usamos o **@JoinColumn**, para apontar que é um foreign key
    * se por algum motivo eu precisar fazer o mapeamento de todos os pedidos do cliente, podemos definir um **Set**<Pedido> na classe Cliente com **@OneToMany** e o mappedBy, pois não temos uma chave para pedidos, quem tem a chave é o Pedido. Desta forma, o join é feito por mappedBy
      * ao invés de Set, poderia ser Collection, List, entre outros
      * o Set evita alguns erros do Hibernate
    * Como o total é um BigDecimal e representa um valor, podemos usar @Column(name = "total", **length** = 20, **precision** = 2)

* **CLASSE ITEMPEDIDO**
  * foi feito as relações com @ManyToOne
  * foi inserido uma List<ItemPedido> na classe Pedido, para ver o comportamento em relação o Set
    * O Set não aceita pedidos repetidos para um cliente 
  * Em Produto não foi feito a associação dos ItemPedido, pois não precisamos resgatar os ItemsPedidos


### Criando os Repositórios das entidades
* O JPARepository tem uma implementação padrão no Spring Data, SimpleJpaRepository

### Fazendo consultas com relacionamentos JPA
* Carregar os pedidos de um cliente usando fetch, que seria trazer junto
  * fetch default LAZY, significa que toda vez que obtermos o cliente da base de dados, ele não vai trazer os pedidos, apenas se fizer um fetch e pudemos mudar para o EAGER usando **@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)**, mas não é recomendado, pois tem momentos que queremos carregar os clientes e não precisaremos dos Pedidos. E usando o EAGER, toda vez que usarmos o cliente, virão os pedidos
  * foi criado o método **findClienteFetchPedidos()** que fará um left join para trazer todos os clientes, independente se ele tiver pedidos ou não


### Carregando os pedidos por cliente
* para arrumar o problema do banco de dados basta usar **@Column(name = "total", precision = 10, scale = 2)**, pois precision define o tamanho do número e o scale a quantidade de casas
* Também podemos buscar no repositórios de Pedidos fazendo um **findByCliente()**
* Não é muito usual trazer os pedidos junto com cliente (findClienteFetchPedidos), exceto se tivermos um formulário ou tela no sistema, que queremos mostrar o cliente e a lista de pedidos do cliente.


### Artigo: Algumas considerações sobre @Transactional
* **Cenário 1:** Tenho um método no meu serviço que salva um pedido, os itens do pedido e a forma de pagamento.
  * Caso um dos passos dê erro na hora de gravar as informações no banco, será feito um rollback da transação como um todo

``` java
@Transactional
public void salvarPedido(Pedido pedido){    
    pedidoRepository.save(pedido);    
    itemPedidoRepository.save(pedido.getItens());    
    pagamentoRepository.save(pedido.getPagamento());
}
```

* **Cenário 2:** Tenho muitas entidades na aplicação e de bancos de dados distintos.
  * Por exemplo, tenho mais de dois banco de dados. O MySQL e o Oracle. Sendo assim, devemos informar para qual banco aquela informação será enviada. 
  * Além disso, é necessário necessário configurar o transactionManager de cada uma nas suas configurações.
  * 
``` java
@Transactional("transactionManagerMySQL")
public void salvarFuncionario(Funcionario fun){    
    funcionarioRepository.save(fun);
}
```


## Spring Web: Desenvolvimento de API RESTful
### Criando o controller de clientes
* Vamos informar que é um Controller e vai receber requisições HTTP e vai se comunicar dentro da arquitetura REST
  * **@Controller:** definir o annotation que vai informar que essa é a camada que vai se comunicar com os clientes 
  * **@RequestMapping("/api/clientes"):** vai ser a url base da api de clientes com "/api/clientes", ou seja, toda requisição que tiver essa url, ele vai entrar no ClienteController. **Esse RequestMapping não é obrigatório. Todo o caminho pode ser definido no RequestMapping do método**
  * **@RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET):** definiu a url que será usada para entrar no método helloClientes, do tipo GET
    * **@PathVariable("nome"):** informando que vamos receber uma variável do Path
  * **@ResponseBody:** para definir que a String que está sendo retornada é o corpo de uma resposta
* http://localhost:8080/api/clientes/hello/nome-do-cliente


### Mapeando as Requisições com Request Mapping
* **@RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET):** eu posso definir mais de um value, informando uma array de urls
  * podemos definir **consumes:** podemos mandar no corpo da requisição objetos do tipo JSON ou XML = {"application/json", "application/xml"} (mas como no exemplo é do tipo GET, não será enviado nada)
  * **value:** array de urls = {"/hello/{nome}", "/{nome}"}
  * **produces:** tem a mesma lógica do consumes, porém é a forma como vamos retornar os objetos e é o cliente quem definirá o tipo de retorno
  * caso for usado o método POST, igual no helloClientes2, o consumes poderá ser definido e o body será recebido pela annotation @RequestBody


### Requisição GET com parâmetros e Response Entity
* Não vamos definir o consumes e o produces, porque o Spring trabalha com JSON por padrão
* Toda a annotation **RequestMapping** pode ser trocada por uma annotation especializada, a **GetMapping**
  * Temos que manter o **ResponseBody**, porque ele retorna um objeto do tipo JSON
* O retorno do objeto é do tipo **ResponseEntity**
  * é um objeto que representa o corpo da resposta
  * podemos fazer uma série de configurações nele
    * HttpStatus
    * Body
    * Headers
* **(@PathVariable Integer id):** não precisa inserir o nome, porque os parâmetros são iguais
* **clientes.findById(id):** retorna um Optional, porque pode ou não ter um cliente


### Requisição POST e Request Body
* No Postman, vamos criar um Request do 
  * tipo POST
  * url: http://localhost:8080/api/clientes 
  * Body do tipo raw JSON, pois podemos digitar o que quisermos

* Para não vir os pedidos quando fizer um getClienteById, usamos o **@JsonIgnore**


### Delete Mapping: deletando um recurso no servidor
* **NoContent:** porque é um status de sucesso, mas não precisamos retornar nada e também não precisa receber nenhuma informação
* **NotFound:** como estamos navegando para uma url, caso o recurso não exista, devemos informar not found


### Put Mapping - Atualizando um cliente
* Put é usado para atualizar integralmente um recurso no servidor, por exemplo, se não enviarmos um dos dados, o dado será salvo como nulo
* **map:** método do Optional que caso tenha um cliente, entrará no método
* **NoContent:** pois quando estamos fazendo atualização não precisamos retornar nenhum item
* **Supplier:** é uma interface funcional que não recebe nenhum parâmetro e retorna qualquer coisa


### Pesquisa de Clientes por parâmetros
* **Example:** vai pegar as propriedades que estão populadas e vai criar um Example
* **matching().withIgnoreCase():** ignorar que independente da String estar em caixa alta ou baixa (case), que faça o matching
* **withStringMatcher():** a forma como vamos definir como é para procurar os valores String
* Todas essa configuração de matcher é enviado via queryParams



## Observações
### Atalhos IntelliJ
* Ctrl + O: Organiza todos os importes, inclusive apaga os que não estão sendo usados
* Ctrl + Alt + L: Indenta o código
* Alt + Insert: Getter and Setter, toString, etc

