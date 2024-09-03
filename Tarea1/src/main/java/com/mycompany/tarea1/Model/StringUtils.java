/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1.Model;

/**
 *
 * @author truez
 */
public class StringUtils {

    // Método estático que valida si un String es un entero
    public static boolean isInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false; // Retorna falso si la cadena es nula o vacía
        }

        try {
            Integer.parseInt(input); // Intenta convertir la cadena a entero
            return true; // Retorna verdadero si la conversión es exitosa
        } catch (NumberFormatException e) {
            return false; // Retorna falso si ocurre una excepción de formato
        }
    }
}

