import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.fail

class CountXOKtTest {

    @Test
    @DisplayName("Testa a quantidade de x e o.")
    fun testCountXO() {
        assertTrue(countXO("xxoo"))
        assertFalse(countXO("xxxoo"))

        assertAll({ countXO("xxoo") }, { countXO("xxxoo") })
    }

    @Test
    fun vaiFalhar() {
        fail("Teste não implementado!")
    }

    @Test
    fun rodaBaseadoEmCondicao() {
        Assumptions.assumeTrue(countXO("xxoo"))
    }
}