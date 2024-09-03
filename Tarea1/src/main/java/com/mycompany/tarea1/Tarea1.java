/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


        
package com.mycompany.tarea1;

import com.mycompany.tarea1.Controller.ControllerCPU;
import com.mycompany.tarea1.Model.CPU;
import com.mycompany.tarea1.View.CPU_VIEW;

/**
 *
 * @author truez
 */
public class Tarea1 {
    
    private String text;
    private ControllerCPU GUI;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        
        
        
        
        CPU model = new CPU("hola");
        CPU_VIEW view = new CPU_VIEW();

        ControllerCPU controller = new ControllerCPU(view, model);
        controller.init();
    }
}
