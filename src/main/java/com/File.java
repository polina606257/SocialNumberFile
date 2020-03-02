package com;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class File {

    Map<String, Double> listNoCopy = new HashMap<>();


    public void createListWithoutCopy(String data) {
        String[] splitData = data.split(";"); // разделяем данные между ; на соц. номер и сумму
        if (!listNoCopy.isEmpty() && listNoCopy.containsKey(splitData[0])) {
            double newSumValue = listNoCopy.get(splitData[0]).doubleValue() + Double.valueOf(splitData[1]).doubleValue(); // общее значение для дубликата соц.номера
            listNoCopy.put(splitData[0], newSumValue);
        } else {
            listNoCopy.put(splitData[0], Double.valueOf(splitData[1]));
        }
    }


    public void readFileDeletingCopy() {

        try {
            Scanner myReader = new Scanner(new java.io.File("src/socNumbers.txt"));

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                createListWithoutCopy(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void writeFileWithoutCopy() {
        try {
        BufferedWriter myWriter = new BufferedWriter(new FileWriter("src/newSocNumbers.txt"));
        for (Map.Entry<String, Double> entry : listNoCopy.entrySet()) {
            myWriter.write(entry.getKey() + ";" + String.format("%.2f", entry.getValue()));
            myWriter.newLine();
        }
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}

    public void getFileWithoutDuplicates() {
        readFileDeletingCopy();
        writeFileWithoutCopy();
    }
}
