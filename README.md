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



## Observações
* [JUnit 5](https://junit.org/junit5/)

