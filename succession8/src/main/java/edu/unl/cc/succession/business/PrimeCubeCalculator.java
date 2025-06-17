package edu.unl.cc.succession.business;
/**
 * @autores Servio Vega, Alejandro Padilla, Doménica Rojas, Alex Sigcho, Daniel Saavedra
 */

import edu.unl.cc.succession.domain.Successionable;

/**
 * Clase que calcula la serie de números primos elevados a la raíz cúbica hasta N términos
 * Serie: S = 1^(1/3) + 3^(1/3) + 5^(1/3) + 7^(1/3) + 11^(1/3) + 13^(1/3)...
 */
public class PrimeCubeCalculator implements Successionable {

    // Número de términos a calcular en la serie
    private Integer numberOfTerms;
    // Número actual que se está evaluando para determinar si es primo
    private Integer currentPrime;
    // Contador de términos encontrados
    private Integer count;
    // Almacena la representación textual de la serie
    private final StringBuilder printableTerms;

    /**
     * Constructor que inicializa el calculador de la serie
     * @param numberOfTerms Número de términos a calcular
     * @throws IllegalArgumentException si el número de términos es menor o igual a 0
     */
    public PrimeCubeCalculator(Number numberOfTerms) {
        if (numberOfTerms.intValue() <= 0) {
            throw new IllegalArgumentException("Number of terms must be greater than 0");
        }
        this.numberOfTerms = numberOfTerms.intValue();
        this.currentPrime = 1;
        this.count = 0;
        this.printableTerms = new StringBuilder("S = ");
    }

    /**
     * Calcula la suma de la serie de raíces cúbicas de números primos
     * @return La suma total de la serie como un Number
     */
    @Override
    public Number calculate() {
        double sum = 0.0;
        while (count < numberOfTerms) {
            if (isPrime(currentPrime)) {
                // Calcula la raíz cúbica del número primo actual
                double cubeRoot = Math.cbrt(currentPrime);
                sum += cubeRoot;
                // Agrega el término a la representación textual
                printableTerms.append(currentPrime).append("^(1/3) + ");
                count++;
            }
            currentPrime++;
        }
        return sum;
    }

    /**
     * Método no utilizado en esta implementación
     */
    @Override
    public Number nextTerm(Number currentTerm) {
        return null;
    }

    /**
     * Establece el número de términos a calcular
     * @param limit Nuevo número de términos
     */
    @Override
    public void setLimit(Number limit) {
        this.numberOfTerms = limit.intValue();
    }

    /**
     * Devuelve la representación textual de la serie
     * @return String con la serie formateada
     */
    @Override
    public String print() {
        return printableTerms.toString();
    }

    /**
     * Verifica si un número es primo
     * @param num Número a verificar
     * @return true si el número es primo, false en caso contrario
     */
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        // Verifica divisibilidad solo hasta la raíz cuadrada del número
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}