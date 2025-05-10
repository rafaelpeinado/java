import java.util.*

fun main() {
    val hello: String = "Hello"
    val world: String = "World"

    println("$hello $world!")
    println("Double max value é: ${Double.MAX_VALUE}.")

    println(helloWorld("Rafael"))
    println(media(10, 8))


    val nome = "John"
    println("Olá, $nome")
    println("Olá, %s %s".format(nome, nome))
    println("Valor: %02d - Salário: %.2f".format(Locale.US, 5, 16855.95000))


//    print("Informe um valor: ")
//    val valor = readLine()
//    println(valor)


    var str: String? = null
    println(str?.length)

    try {
        println(str!!.length)
        val abc = "dfafdafkl"
        print(abc[100])
        println(10 / 0)
    } catch (e: IndexOutOfBoundsException) {
        println(e.message)
    } catch (e: NullPointerException) {
        println(e.message)
    } catch (e: ArithmeticException) {
        println(e.message)
    } catch (e: Exception) {
        println(e.message)
    } finally {
        println("Finalmente!")
    }


    val str1: String? = null
    println(str1 ?: "Nulo")

    str1?.let {
        it.length
        it.lowercase()
        it.contains("abc")
    }


    // Mais sobre funções e parâmetros
    endereco("", "", "")
    endereco("", estado = "estado", cidade = "cidade")

    println(media(8F, 10F, 4F, 6F, 6F, 78F))

    calculo(8) { a -> a * a * a }
    calculo(8, ::quadrado)

}

fun helloWorld(nome: String) = println("Olá, $nome")

fun media(n1: Int, n2: Int): Int = (n1 + n2) / 2

// Mais sobre funções e parâmetros
//fun endereco(rua: String, cidade: String, estado: String) {
//
//}

fun endereco(rua: String, cidade: String, estado: String, cep: String = "") {

}

//fun media(n1: Float, n2: Float): Float {
//    return (n1 + n2) / 2
//}
//
//fun media(n1: Float, n2: Float, n3: Float): Float {
//    return (n1 + n2 + n3) / 3
//}

//fun media(vararg n: Float): Float {
//    var soma = 0F
//    for (i in n) {
//        soma += i
//    }
//    return soma / n.size
//}

fun media(vararg n: Any): Float {
    var soma = 0F
    for (i in n) {
        when (i) {
            is Int -> println("Int")
            is Float -> println("Float")
        }
    }
    return soma / n.size
}

fun calculaJuros(): Nothing {
    throw Exception()
}


fun interface Funcionario {
    fun calculaBonus(str: String, id: Int): Float
}


fun imprimeValorBonus(funcionario: Funcionario) {
    println(funcionario.calculaBonus("teste", 10))
}

fun teste() {
    imprimeValorBonus { str, id ->
        println(str)
        0F
    }
}


fun calculo(n: Int, funcao: (Int) -> Int): Int {
    return if (n < 10) {
        funcao(n)
    } else {
        0
    }
}

fun quadrado(n: Int): Int {
    return n * n
}


