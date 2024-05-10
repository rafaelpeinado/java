# Getting Started Unit Testing with JUnit 5
## Writing Your First Test
### The Benefits of Unit Testing
* Um teste unitário é apenas um código, ou seja, estaremos escrevendo uma classe de teste Java
* A classe de teste executará o código de destino
* Uma pequena unidade de código pode ser uma classe Java ou um único método ou função.
  * Um único código pode ser testado em várias classes de uma só vez. Esses testes são chamados de testes de componentes.
* O código de teste obtém os resultados do código de destino e confirma que esses resultados estão corretos

* **Benefits of Unit Testing:**
  * Feedback rápido
  * Testes de Regressão Automatizada
  * Ajuda no design
  * Melhora confiança
  * Documentação


### Demo: Setting up JUnit 5 in an IDE
* No Intellij basta clicar com o botão direito na classe que queremos testar -> Go To -> Test -> Create New Test... 
* No Eclipse, botão direito no arquivo class -> JUnit Test Case
* A annotation **@Test** indica que é um teste do JUnit

* [JUnit 5](https://junit.org/junit5/)

* Quando estiver usando o Maven, basta inserir uma dependency e plugin

* Para rodar testes, basta usar a linha de comando

``` bash
mvn clean test
```


### Your Sample System
* [gettingstartedjunit5](https://github.com/weaverj/gettingstartedjunit5)


### Demo: Writing Your First JUnit 5 Test
* Annotations do Java fornecem metadados sobre o código
* **assertNotNull:** é uma Assertion fornecida pelo JUnit e verifica se a variável é nula. Se for, a execução é interrompida ali e com uma mensagem de falha.

* Os testes unitários costumam ser escritos com 
  * Configurar
  * Executar
  * Verificar


### Demo: Executing Your Test and Interpreting Results
* Podemos tornar o nível de pacote do método de teste em vez de public, pois o teste e a classe que estamos testando estão no mesmo pacote Java 

* No primeiro momento, foi difícil testar o 

``` java
assertEquals(
        "9/1/2018 02:00 PM",
        enteredAppt.getAppointmentDateTime()
                .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
```

* O problema foi na Classe ClinicCalendar, pois não tinha a chamada do toUpperCase(), para transformar o AM ou PM em maiúsculo, pois o java só entende AM ou PM maiúsculo


## Writing More Complex Tests
### Demo: Applying Assertions
* [Class Assertions](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html)

* **assertSame:** irá verificar se as duas variáveis que estão sendo comparadas apontam literalmente para o mesmo objeto na memória. As Instâncias de Enum são únicas, então a comparação não devem ser somente equivalentes, elas devem ser exatamente o mesmo objeto na memória.


* o meu teste **returnCurrentDaysAppointments**, falhou, porque não estou na data do dia do curso.

``` java
assertEquals(calendar.getTodayAppointments(), calendar.getAppointments());
```

* O teste acima também falhou, pois o item actual tem um item a mais. E o que é mostrado são instâncias de objetos. Se usarmos **assertEquals** para comparar coleções, o JUnit verificará se as duas coleções contêm objetos equivalentes usando o método equals em cada um desses objetos e se esses objetos estão na mesma ordem.

* **assertIterableEquals:** um iterable no Java é algo que retorna uma série de objetos para uso em loop for. Se estamos tentando comparar o conteúdo de coleções que são tipos diferentes de coleção, se podemos obter um iterable de ambos, podemos usar assertIterableEquals para compará-los.


### Demo: Setting up and Tearing Down Tests
* São usadas annotations:
  * **@BeforeAll:** será executado uma vez antes de todos os métodos de teste na classe
  * **@BeforeEach:** será executado uma vez antes de cada método de teste
  * * **@AfterEach:** será executado uma vez depois de todos os métodos de teste na classe
  * **@AfterAll:** será executado uma vez depois da conclusão de todos os testes na classe

* **BeforeAll** e **AfterAll** estão no nível da classe, então estão com **static**

* Ao fazer um System.out.println com o ciclo de vida, a impressão foi:
  * Before all...
  * Before each...
  * entry of appointment...
  * After each...
  * Before each...
  * no appts...
  * After each...
  * Before each...
  * current days appts...
  * After each...
  * Before each...
  * has appts...
  * After each...
  * After all...


### Demo: Testing Exceptions
* **assertThrows:** podemos usar para verificar explicitamente que recebemos uma exceção quando determinado código é chamado. O que esperamos de volta é uma exceção de Runtime.


### Demo: Customizing Test Messages and Reporting
* Todos asserts permitem colocar um terceiro parâmetro que é a mensagem de erro que queremos que apareça.
* **@DisplayName("DateTimeConverter should"):** nome que será exibido quando o teste for executado
* Usar strings nas mensagens de erro podem deixar um pouco de atraso, pois serão concebidas pelo JUnit. Podemos usar a sintaxe da expressão lambda, uma função anônima, para fornecer a mensagem de falha em vez de apenas um string fixa como o parâmetro
  * A expressão lambda só será avaliada se houver uma falha
* no JUnit 5 é possível criar testes aninhados usando uma nova classe com a annotation **@Nested**


### Demo: Controlling Test Method Execution
* Uma coisa bastante comum a ser feita é desativar um teste temporariamente. Pode haver alguma falha no teste que gostaríamos de resolver mais tarde e não apagá-lo
* O importante da annotation **@Disabled**, é que ele mostra a existência do código para não deixar esquecê-lo e ainda o apresenta no relatório.
* Podemos usar **assertAll** para validar todos os asserts que foram criado dentro daquele método.
  * A importante desse método é que ele vai testar todos os asserts e apresentar todas as falhas que encontrar

``` java
assertAll(
        () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
        () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
        () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
        () -> assertEquals(
                "9/1/2018 02:00 PM",
                enteredAppt.getAppointmentDateTime()
                        .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"))),
);
```


### Demo: Running Groups of Tests
* Ao colocar a annotation **@Tag("dateTime")** na classe DateTimeConverterShould e configurar no JUnit para rodar todos os testes com a tag dateTime, apenas a classe DateTimeConverterShould foi executada, ignorando as outras
  * Colocando no método, apenas aquele método foi executado.


## Making Existing Code Testable
### Understanding Single Responsibility
* Princípio da Responsabilidade Única, por Bob Martin, é a ideia de que uma unidade de código deve fazer uma coisa e fazer isso bem.
* Coesão e separação de conceitos
* Deve ter apenas um motivo para mudar

#### Finding a Seam to Test Target Code
* Fornecer inputs (entradas) para o código alvo
* Validar outputs (saídas) do código alvo
* Verificar se código alvo cumpre suas responsabilidade única 

#### Mixed Concerns Makes Code Hard to Test
* **Inaccessible system output:** o código pode estar enviando essas saídas diretamente para um relatório ou para uma interface de usuário, sem chance de um teste as interceptar
* **Inaccessible inputs:** podemos não conseguir fornecer entradas para o código que está tentando testar. Código de interface do usuário misto com lógica de negócios é um exemplo clássico disso. O código que obtém diretamente as entradas de um usuário geralmente não fornece uma maneira de fornecer essas entradas programaticamente, o que precisamos ser capaz de fazer em um teste unitário.
* **Undesirable side effects:** podemos ter efeitos colaterais indesejáveis. A lógica que gostaríamos de testar é mesclada com o código de acesso ao banco de dados, por exemplo, pode gerar uma exceção se o banco de dados não estiver disponível ou, mesmo se estiver disponível, pode atrasar o teste. 

#### Extraction - Separate Code to Test
* Então, se estamos tentando testar o código com problemas mistos? Podemos usar uma técnica chamada **Extraction**:
  * separar os conceitos movendo o código, tentando separar o código que queremos testar dos outros códigos
  * essa extração pode assumir várias formas, como ser movido para novos métodos, classes e funções.
  * as extrações mais fáceis e seguras são aquelas que são de natureza tão mecânica que a IDE pode realizá-las para nós quando isso for possível.



## Observações
* [JUnit 5](https://junit.org/junit5/)

