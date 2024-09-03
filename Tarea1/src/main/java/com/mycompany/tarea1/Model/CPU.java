/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author truez
 */
public class CPU {
    private String processText;
    private Records records;
    private Memory memory;
    private List<String> instructions;
    private int index;
    
    public CPU (String text){
        this.processText = text;
        this.records = new Records();
        this.memory = new Memory();
        this.instructions = new ArrayList<String>();
        this.index = 0;
    }
    
    public void createMemory(int cant) {
        memory.initializeMemory(cant);
    }
    
    public void memoryToString() {
        memory.displayMemory();
    }
    
    public void createRecords(int cant) {
        records.createRecords(cant);
    }
    
    public void recordsToString() {
        records.displayRecords();
    }
    public void setProcessText(String text) {
        this.processText = text;
    }
    
    public void setIntructions(String text){
        String[] lines = text.split("\\R");
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));
        this.instructions = lineList;
    }
    public String getInstruction(){
        if (index == instructions.size()){
            return null;
        } else { 
            return this.instructions.get(this.index++); // Return a copy of the memory list
        }
    }
    
    public List<String> getMemoryArray() {
        return this.memory.getMemoryArray(); // Return a copy of the memory list
    }
    
    public List<String> getInstructions() {
        return this.instructions; // Return a copy of the memory list
    }
    
    public List<Record> getRecordsArray() {
        return this.records.getAllRecords(); // Return a copy of the memory list
    }
    
    public void updateRecord(String id, int value) {
        this.records.updateRecordValue(id, value);
    }
    
    public void updateMemory(int index, String value) {
        this.memory.setMemory(index, value);
    }
    public int getRecordValue(String id) {
        return this.records.getRecord(id).getValue();
    }
    
    public int getLengthIntructions(){
        return this.instructions.size();
    }
    
    public void setIndex(int value){ this.index = value;}
    
    public void resetAllRecords() { records.resetAllRecords(); }
}

