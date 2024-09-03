/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1.Model;

/**
 *
 * @author truez
 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FileUtils {

    // Expresión regular para validar las instrucciones
    private static final String REGEX = "^(MOV (AX|BX), ?-?\\d+|LOAD (AX|BX)|ADD (AX|BX)|SUB (AX|BX)|STORE (AX|BX))$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    // Método que recibe el nombre del archivo y retorna su contenido como un String
    public static String readFileContent(String fileName) {
        try {
            // Lee todo el contenido del archivo y lo retorna como String
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            // Maneja posibles errores y retorna un mensaje de error
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return ""; // Retorna una cadena vacía en caso de error
        }
    }

    // Método para validar cada línea del contenido del archivo
    public static boolean validateFileContent(String content) {
        String[] lines = content.split("\\R"); 
        for (String line : lines) {
            Matcher matcher = PATTERN.matcher(line.trim());
            System.out.println(line);
            if (!matcher.matches()) {
                System.out.println("Invalid instruction found: " + line);
                return false; 
            }
        }
        return true;
    }

}
