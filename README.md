# Aprenda BDD com Cucumber em JAVA
## Introdução
### BDD e Cucumber
- TDD
- DDD (Linguagem Ubíqua)

- BDD: testes e códigos evoluem juntos
  - Executar o texto em linguagem natural

- **Como** um usuário/perfil/papel
- **Eu quero** fazer uma determinada ação
- **Para** atingir algum objetivo qualquer


## Fundamentos
### Gherkin
- Primeira história
  - **Como** um aluno (quem vai ser beneficiado com a criação dessa história)
  - **Eu quero** aprender a utilizar o Cucumber (o que de fato deve ser feito para a história ser considerada concluída)
  - **Para** que eu possa automatizar critérios de aceitação (o que ganhamos com a implementação dessa história)

- **Critérios de aceitação:** executar uma especificação
  - **Critério:** Executar uma especificação
    - O teste vai passar se você escrever a especificação de forma correta e executá-la

- Gherkin é uma linguagem simples com poucas palavras chaves que ajuda a estruturar os cenários
  - Criar um teste automatizado a partir dela

  - **Dado** que criei o arquivo corretamente
  - **Quando** executá-lo
  - **Então** a especificação deve finalizar com sucesso

  - **Dado** que está chovendo (cenário inicial do teste)
  - **Quando** andar na rua (indica a ação que vai ser executada)
  - **Então** a camisa fica molhada (indica o que deve acontecer ao final do cenário)


- Ao invés de fazer:
  - **Dado** que está chovendo e estou sem guarda-chuvas
- Faça:
  - **Dado** que está chovendo 
  - **Dado** que estou sem guarda-chuvas
- Isso garante reaproveitar os cenários

- Evitar dependências entre os testes


### Montando o ambiente
- Java JDK
- IDE (Eclipse ou IntelliJ)
- Plugin Cucumber


### Cenário inicial
- [aprender_cucumber.feature](./CursoCucumber/src/test/java/aprender_cucumber.feature)
  - Os arquivos feature, centralizam toda a especificação de determinada funcionalidade
  - É uma fonte única de verdade
- Given
- When
- Then

- Os passos dos cenários precisam ser associados a algum método
  - [AprenderCucumber.java](./CursoCucumber/src/test/java/AprenderCucumber.java)

- Status dos testes
  - Passed
  - Failed
  - Skipped
  - Pending
  - Undefined


### Tudo em português
- Definir "# language: pt"


### Integrando com JUnit

```xml
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>7.20.1</version>
    <scope>test</scope>
</dependency>
```

- Classe Runner
  - @RunWith(Cucumber.class)


### CucumberOptions
- snippets = CucumberOptions.SnippetType.CAMELCASE: ao invés de que_criei_o_arquivo_corretamente, será queCrieiOArquivoCorretamente
- dryRun = true
- strict = true


### Cenário: Contador
- Criação dos cenários com contador


### Asserts
- É necessário o Assert para validar o dado


### Reaproveitando cenários - REGEX
- Ao retirar {int} os próximos cenários não serão reaproveitados
- [Regulex](https://jex.im/regulex)
- [Regex 101](https://regex101.com/)


### Desafio: REGEX
- Usando regex para definir como o texto deve ser redigido







## Observações e Recomendações
- Specification by Example - Gojko Adzic
- User Stories Applied for Agile Software Development - Mike Cohn


