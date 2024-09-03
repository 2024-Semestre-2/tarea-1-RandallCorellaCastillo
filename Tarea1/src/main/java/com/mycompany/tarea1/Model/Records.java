/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1.Model;

/**
 *
 * @author truez
 */
import java.util.ArrayList;
import java.util.List;

public class Records {
    private List<Record> records;

    public Records() {
        this.records = new ArrayList<>();
    }

    // Añadir un nuevo registro
    public void addRecord(Record record) {
        records.add(record);
    }

    // Obtener un registro por su índice
    public Record getRecord(String id) {
    for (Record record : records) {
        if (record.getId().equals(id)) {
            return record;
        }
    }
    return null; // Si no se encuentra el registro, devolver null
}

    // Obtener todos los registros
    public List<Record> getAllRecords() {
        return records;
    }
    
    public void createRecords(int cant) {
        if (cant <= 0) {
            return;
        }
        Record recordPC = new Record("PC", 0);
        addRecord(recordPC);
        Record recordIR = new Record("IR", 0);
        addRecord(recordIR);
        Record recordAC = new Record("AC", 0);
        addRecord(recordAC);
        int index = 0;
        for (char firstChar = 'A'; firstChar <= 'Z'; firstChar++) {
            for (char secondChar = 'X'; secondChar <= 'X'; secondChar++) {
                if (index > cant) {
                    return; // Exit if the required number of records is reached
                }
                String id = "" + firstChar + secondChar;
                Record record = new Record(id, 0); // Initialize with value 0
                addRecord(record);
                index++;
            }
        }
    }
    
    public void updateRecordValue(String id, int newValue) {
        Record record = getRecord(id);
        if (record != null) {
            record.setValue(newValue);
        } else {
            System.out.println("Record with ID " + id + " not found.");
        }
    }
    
    public void displayRecords() {
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }
    }
    
    public void resetAllRecords() {
        for (Record record : records) {
            record.setValue(0); // Resetear el valor a 0 (o cualquier valor inicial deseado)
        }
        System.out.println("All records have been reset.");
    }
    
}
