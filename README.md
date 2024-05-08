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
* **assertThrows:** podemos usar para verificar explicitamente que recebemos uma exceção quando determinado código é chamado. O que esperamos de volta é uma exceção de Runtime




## Observações
* [JUnit 5](https://junit.org/junit5/)

