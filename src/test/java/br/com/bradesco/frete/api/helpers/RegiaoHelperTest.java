package br.com.bradesco.frete.api.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RegiaoHelperTest {

    /**
     * Method under test: {@link RegiaoHelper#isSudeste(Integer)}
     */
    @Test
    void quandoCepInferiorAoRangeSudesteNaoESudeste() {

        int cepNumerico = 10;

        boolean expectedIsSudesteResult = false;

        assertEquals(expectedIsSudesteResult, RegiaoHelper.isSudeste(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNorte(Integer)}
     */
    @Test
    void quandoCepInferiorAoPrimeiroRangeNorteNaoENorte() {
        // Arrange
        int cepNumerico = 10;

        // Act and Assert
        boolean expectedIsNorteResult = false;
        assertEquals(expectedIsNorteResult, RegiaoHelper.isNorte(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNorte(Integer)}
     */
    @Test
    void quandoCepDentroDoPrimeiroRangeNorteENorte() {

        int cepNumerico = 66000000;

        boolean expectedIsNorteResult = true;

        assertEquals(expectedIsNorteResult, RegiaoHelper.isNorte(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNorte(Integer)}
     */
    @Test
    void quandoCepDentroDoSegundoRangeNorteENorte() {

        int cepNumerico = 76800000;

        boolean expectedIsNorteResult = true;

        assertEquals(expectedIsNorteResult, RegiaoHelper.isNorte(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNorte(Integer)}
     */
    @Test
    void quandoCepMaiorQueSgundoRangeNorteNaoENorte() {

        int cepNumerico = 80000000;

        boolean expectedIsNorteResult = false;

        assertEquals(expectedIsNorteResult, RegiaoHelper.isNorte(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isSudeste(Integer)}
     */
    @Test
    void quandoCepMaiorAoRangeSudesteNaoESudeste() {

        int cepNumerico = 40000000;

        boolean expectedIsSudesteResult = false;

        assertEquals(expectedIsSudesteResult, RegiaoHelper.isSudeste(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isSudeste(Integer)}
     */
    @Test
    void quandoCepDentroDoRangeSudesteESudeste() {

        int cepNumerico = 1000001;

        boolean expectedIsSudesteResult = true;

        assertEquals(expectedIsSudesteResult, RegiaoHelper.isSudeste(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNordeste(Integer)}
     */
    @Test
    void quandoCepInferiorAoRangeNordesteNaoENordeste() {

        int cepNumerico = 39999999;

        boolean expectedIsNordesteResult = false;

        assertEquals(expectedIsNordesteResult, RegiaoHelper.isNordeste(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNordeste(Integer)}
     */
    @Test
    void quandoCepMaiorAoRangeNordesteNaoENordeste() {

        int cepNumerico = 66000000;

        boolean expectedIsNordesteResult = false;

        assertEquals(expectedIsNordesteResult, RegiaoHelper.isNordeste(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isNordeste(Integer)}
     */
    @Test
    void quandoCepDentroDoRangeNordesteNaoENordeste() {

        int cepNumerico = 40000001;

        boolean expectedIsNordesteResult = true;

        assertEquals(expectedIsNordesteResult, RegiaoHelper.isNordeste(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isSul(Integer)}
     */
    @Test
    void quandoCepInferiorAoRangeSulNaoESul() {

        int cepNumerico = 77999999;

        boolean expectedIsSulResult = false;

        assertEquals(expectedIsSulResult, RegiaoHelper.isSul(cepNumerico));
    }

    /**
     * Method under test: {@link RegiaoHelper#isSul(Integer)}
     */
    @Test
    void quandoCepMaiorAoRangeSulNaoESul() {

        int cepNumerico = 100000000;

        boolean expectedIsSulResult = false;

        assertEquals(expectedIsSulResult, RegiaoHelper.isSul(cepNumerico));
    }

    @Test
    void quandoCepDentroDoRangeSulNaoESul() {

        int cepNumerico = 99999999;

        boolean expectedIsSulResult = true;

        assertEquals(expectedIsSulResult, RegiaoHelper.isSul(cepNumerico));
    }
}

