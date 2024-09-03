/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea1.Controller;

import com.mycompany.tarea1.Model.CPU;
import static com.mycompany.tarea1.Model.FileUtils.readFileContent;
import static com.mycompany.tarea1.Model.FileUtils.validateFileContent;
import com.mycompany.tarea1.Model.Record;
import static com.mycompany.tarea1.Model.StringUtils.isInteger;
import com.mycompany.tarea1.View.CPU_VIEW;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.text.View;
import java.util.Random;

/**
 *
 * @author truez
 */
public class ControllerCPU implements ActionListener {
    private CPU_VIEW view;
    private CPU model;
    private DefaultListModel<String> listModelM;
    private DefaultListModel<String> listModelR;
    private DefaultListModel<String> listModelI;
    private int currentStartProcess;
    private int maxMemorySO;
    // Llenar el modelo con datos iniciales

    
    public ControllerCPU(CPU_VIEW view, CPU model) {
        this.view = view;
        this.model = model;
        this.view.nextStep.addActionListener(this);
        this.view.startBtn.addActionListener(this);
        listModelM = new DefaultListModel<>();
        listModelR = new DefaultListModel<>();
        listModelI = new DefaultListModel<>();
        this.currentStartProcess = 0;
        this.maxMemorySO = 30;
    }
    
    public void init(){
        view.setTitle("CPU");
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    public void init(String path, int cantRecords, String cantMemory, String startMemory) {
        String text = readFileContent(path);
        if (!validateFileContent(text)) {
            return;
        }
        if(Integer.parseInt(cantMemory) -  Integer.parseInt(startMemory) < text.split("\n").length) {
            System.out.println("Error, espacio minimo requerido para el proceso no cumplido.");
        } else {
            model.setIndex(0);
            // Configuraciones basicas.
            model.createRecords(cantRecords);
            model.createMemory(Integer.parseInt(cantMemory));

            Random random = new Random();
            this.currentStartProcess = random.nextInt(Integer.parseInt(cantMemory) - model.getLengthIntructions() - Integer.parseInt(startMemory)) + Integer.parseInt(startMemory);
            setDataRecord();
            setDataMemory();
            model.resetAllRecords();
            model.updateRecord("PC", currentStartProcess);
            model.setIntructions(text);
            setInstructions();
            setDataRecord();
        }
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.nextStep) {
            nextStep();
        }
        if (e.getSource() == view.startBtn) {
            String size = view.sizeMemory.getText();
            String startMemory = view.inicio.getText();
            String fileName = view.currentFile;         
            if(size != null && startMemory != null && fileName != null && !fileName.equals("")){
                if(isInteger(size) && isInteger(startMemory)) {
                    
                    if(Integer.parseInt(startMemory) < maxMemorySO){
                        System.out.println("la memoria minima de inicio es 30");
                    } else {
                            init(fileName, 3, size, startMemory);
                    }
                } else {
                    System.out.println("no son numeros");
                }
               
            }

        }
    }
    
    public void nextStep() {
            // Aquí puedes añadir el dato que quieras a la JList
            String currentInstruction = model.getInstruction();
            if (currentInstruction != null) {
                String[] partsInstruction = currentInstruction.split(",");
                if (partsInstruction.length > 1) {
                    String record = partsInstruction[0].split(" ")[1];
                    String value = partsInstruction[1].trim();
                    int valueInt = Integer.parseInt(value);
                    model.updateRecord(record, valueInt);
                    model.updateRecord("PC", currentStartProcess);
                    model.updateRecord("IR", currentStartProcess + 1);
                    model.updateMemory(currentStartProcess++, currentInstruction);
                    setDataRecord();
                    setDataMemory();
                } else {
                    String oper = partsInstruction[0].split(" ")[0];
                    String record = partsInstruction[0].split(" ")[1];
                    int value = model.getRecordValue(record);
                    int currentIR = model.getRecordValue("AC");
                    if (oper.equals("LOAD")) {model.updateRecord("AC", value);}
                    if (oper.equals("ADD")) {model.updateRecord("AC", value + currentIR);}
                    if (oper.equals("SUB")) {model.updateRecord("AC", currentIR - value);}
                    if (oper.equals("STORE")) {model.updateRecord(record, currentIR);}
                    model.updateRecord("PC", currentStartProcess);
                    model.updateRecord("IR", currentStartProcess + 1);
                    model.updateMemory(currentStartProcess++, currentInstruction);
                    setDataRecord();
                    setDataMemory();
                }
            } else {
                System.out.println("Index Error");
            }
    }
    public void setDataRecord(){
        // Configurar la vista con el modelo de lista
        setListModelFromRecords(model.getRecordsArray());
        view.setDataRegister(listModelR);
    }
    
    public void setDataMemory(){
        // Configurar la vista con el modelo de lista
        setListModelMemoryFromArrayList(model.getMemoryArray());
        view.setDataMemory(listModelM);
    }
    public void setInstructions() {
        setListModelInstructions(model.getInstructions());
        view.setInstructions(listModelI);
    }
    
    
    public void setListModelMemoryFromArrayList(List<String> arrayList) {
        listModelM.clear(); // Clear existing elements
        int cont = 0;
        for (String item : arrayList) {
            listModelM.addElement(cont++ + " : " + item); // Add elements to the model
        }
    }
    
    public void setListModelInstructions(List<String> arrayList) {
        listModelI.clear(); // Clear existing elements
        int cont = 0;
        for (String item : arrayList) {
            listModelI.addElement(cont++ + " : " + item); // Add elements to the model
        }
    }
    
    public void setListModelFromRecords(List<Record> recordList) {
        listModelR.clear(); // Clear existing elements
        for (Record record : recordList) {
            listModelR.addElement(record.toString()); // Add elements to the model
        }
    }
}
