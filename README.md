# Java Básico
## Introdução e Dicas para quem está começando
### O que é Java?
- Linguagem de Programação
- API
- Ambiente de Execução


#### Linguagem de Programação
- Simples
- Orientada a Objetos
- Distribuída
- Multithreaded
- Dinâmica
- Arquitetura neutra
- Portável
- Ótima Performance
- Robusta
- Segura

- Instalar JVM
- MyProgram.java -> **Compiler** -> MyProgram.class -> **Java VM** -> My Program

- Java é WORA - Write Once Run Anywhere


#### Plataforma e Ambiente Java
- The Java Virtual Machine
- The Java Application Programming Interface (API)

- James Gosling: criador da Java
- Sun MicroSystems -> Foi comprada pela Oracle


#### O que posso fazer com Java?
- Java SE:
  - Servers: Java EE
  - Desktop: Java FX

- Vinicius Senger: Java Embedded

![Java](./assets/01-aula-java.png)

- [AP Info](https://www.apinfo.com/apinfo/)

- [Como Começar a Aprender Java](https://loiane.com/2011/01/como-comecar-a-aprender-java/)

- Participar de algum JUG - Java Users Group
  - Sou Java, por exemplo
- Eventos:
  - JavaOne Brasil
  - Just Java
  - É Dia de Java
  - Javaneiros
  - Uai Jug Tech Days
  - Profissão Java
  - Conexão Java
  - JavaCE Community Conference
  - TDC
  - QCon SP
- Revistas
  - DevMedia
    - Easy Java Magazine
    - Java Magazine
  - MundoJ
  - Java Magazine (Oracle)
- Livros:
  - Head First Java
  - Learning Java - O'Reilly
  - Java Como Programar
  - OCJP - Kathy Sierra
- Cursos:
  - Global Code
  - Caelo
- Fóruns:
  - GUJ
  - Java Ranch
  - Java Free
- Certificações:
  - Java Associate
  - Java Programmer
  - Expert
    - Mobile Application Developer
    - Web Services Developer
    - JSP & Servlet Developer
    - Enterprise JavaBeans Developer
    - Java Persistence Developer
  - Master
    - Enterprise Architect
    - Java Developer

- [The Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- Apostilas Caelum
- Apostilas kl9


## Instalando o Java no Windows
- **JDK (Java Development Kit):** é necessário para desenvolver. Já está incluso o JRE
- **JRE (Java Runtime Environment):** programa para executar um programa Java no computador
  - O Programa de Imposto de Renda usa o JRE


## Começando com Java
### Entendendo os Erros
#### Tipos de Erros
- Erros de Sintaxe
- Erros de Semântica
- Erros em Tempo de Execução


### Introdução a Classes e Objetos
* Paradigmas Estruturas x Orientado a Objetos


#### Vantages OO
- Reuso de código
- Reflete o mundo real
- Facilita manutenção no código


#### Conceitos
- **Classes:** descrição de um grupo de objetos
  - Nome da classe
  - Conjunto de atributos (descrição)
  - Conjunto de métodos (comportamento)
- **Objetos**
- **Herança**
  - Permite reutilização da estrutura e do comportamento de uma classe
- **Polimorfismo** (é mais voltado para comportamentos)
  - Habilidade de variáveis terem "mais de um tipo"


##### Emitir som
- Cachorro: late
- Gato: mia
- Vaca: berra
- Cobra: sibila
- Lobo: ruiva


#### Java: pacotes
- Forma de organizar as classes
- Convenção: domínio + ´projeto + pasta
- Ex: com.loiane.cursojava.aula09


## Programação Básica
### Introdução a Variáveis
#### O que é uma Variável
- Área de memória associada a um nome, que pode armazenar valores de um determinado tipo
- Exemplo: armazenar idade de uma pessoa


### Tipos Primitivos
- Inteiros
- Ponto Flutuante
- Char
- Boolean
- Literais


#### Tipos Primitivos
- Boolean
- Números:
  - Inteiro:
    - byte
    - short
    - int 
    - long
    - char 
  - Ponto Flutuante:
    - float
    - double


##### Tipos Inteiros
| Tipo  | Tamanhos (bits) |                  Intervalo de valores                  |                                      |
| :---: | :-------------: | :----------------------------------------------------: | :----------------------------------: |
| byte  |        8        |                       -128 e 127                       |  -(2<sup>7</sup>) a 2<sup>7</sup>-1  |
| short |       16        |                    -32.768 e 32.767                    | -(2<sup>15</sup>) a 2<sup>15</sup>-1 |
|  int  |       32        |             -2.147.483.648 e 2.147.483.647             | -(2<sup>31</sup>) a 2<sup>31</sup>-1 |
| long  |       64        | -9.223.372.036.854.775.808 e 9.223.372.036.854.775.807 | -(2<sup>63</sup>) a 2<sup>63</sup>-1 |


- Se o valor for superior ao intervalo suportado ele volta para os número negativos.
  - Por exemplo, 2.147.483.647 + 1 = -2.147.483.648
  - 2.147.483.647 + 2 = -2.147.483.647


##### Pontos Flutuantes
|  Tipo  | Tamanhos (bits) |
| :----: | :-------------: |
| float  |       32        |
| double |       64        |


float saldo1 = 100.30f (tem que colocar o **f**, pois é literal)
double saldo2 = 100.30


##### Char
- Pois char usa a Tabela ASCII
- Unicode


##### Boolean
- true ou false


#### Literais
- int idade1 = 20;
- long idade2 = 20l;

- Quando declaramos um primitivo inteiro, por padrão ele assume o **int**, por isso precisamos colocar a letra **l** para tipos long
  - O mesmo acontece com double
  
- double d1 = 123.4;
- double d2 = 1.234e2; (notação científica) 1.234 * 10<sup>2</sup>
- float f1 = 123.4f;


##### Hexadecimais, Octais, Binários
int decVal = 26;
int hexVal = 0x1a;
int octVal = 032;
int binVal = 0b11010; // JDK 7


##### Pode e não pode
- Para ficar mais fácil a leitura podemos separar os números por _:
  - long creditCardNumber = 123_5678_9012_3456L;
  - long cpf = 101_134_156_62L;
  - long hexBytes = 0xFF_EC_DE_5E;
  - long hexWords = 0xCAFE_BABE;
  - long maxLong = 0x7fff_ffff_ffff_ffffL;
  - byte nybbles = 0b0010_0101;
  - long bytes = 0b11010010_01101001_10010100_10010010;


- Não pode colocar underscore perto de ponto 
  - float pi1 = 3_.1415F;
  - float pi2 = 3._1415F;
- Não pode colocar underscore perto de sufixos (L ou 0x)
  - long cpf = 101_134_156_68_L;
  - int x5 = 0_x52;
- Identificador, não é literal
  - int x1 = _52;
- OK 
  - int x2 = 5_2; (decimal literal)
  - int x4 = 5_________2; (decimal literal)
  - int x7 = 0x5_2; (hexadecimal literal)
- Não pode colocar underscore no final de um literal
  - int x3 = 52_;
- Não pode colocar underscore no começo de números
  - int x6 = 0x_52;
- Não pode colocar underscore no final de números
  - int x8 = 0x52_;


#### Curiosidade
int Oct31 = 031;
int Dec25 = 25;

Oct31 == Dec25 resulta em true


#### Escape char
| Sequência de Escape |       Descrição       |
| :-----------------: | :-------------------: |
|         \t          |          tab          |
|         \b          |       backspace       |
|         \n          |      nova linha       |
|         \r          |   retorno de carro    |
|         \f          |   avanço de página    |
|         \\'         |     aspas simples     |
|         \\"         |     aspas duplas      |
|         \\\         |    barra invertida    |
|        \ddd         |    constante octal    |
|       \uxxxx        | constante hexadecimal |


### lendo dados usando a classe Scanner
- Classe Scanner
  - Scanner scan = new Scanner(System.in);
  - String nome = scan.nextLine();
  - String primeiroNome = scanNext();
  - int idade = scan.nextInt();
  - double altura = scan.nextDouble();




