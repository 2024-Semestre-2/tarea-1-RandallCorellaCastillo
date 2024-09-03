/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author truez
 */
public class Memory {
    private List<String> memory;

    public Memory() {
        this.memory = new ArrayList<>();
    }

    public void initializeMemory(int size) {
        this.memory = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            memory.add(""); // Initialize with an empty string or any default value
        }
    }

    public void setMemory(int index, String value) {
        if (index >= 0 && index < memory.size()) {
            memory.set(index, value);
        } else {
            System.out.println("Index out of bounds.");
        }
    }

    public String getMemory(int index) {
        if (index >= 0 && index < memory.size()) {
            return memory.get(index);
        } else {
            System.out.println("Index out of bounds.");
            return null;
        }
    }
    List<String> getMemoryArray() {
        return new ArrayList<>(memory); // Return a copy of the memory list
    }
    
    public void displayMemory() {
        for (int i = 0; i < memory.size(); i++) {
            System.out.println("Address " + i + ": " + memory.get(i));
        }
    }  
}
