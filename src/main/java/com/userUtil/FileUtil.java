package com.userUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static void writeToFile(File file, List<String> text){
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            fileWriter= new FileWriter(file);
            bufferedWriter= new BufferedWriter(fileWriter);
            for (String str: text   ) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Method, on the input accepts object file of the class File.
     * If such a file does not exist, or an I / O error occurred,
     * a message is displayed about an error of type FileNotFoundException, IOException.
     * The method writes each line from the specified file to a list of type String and returns this list.*/
    public static List<String> readFromFile(File file){
        List<String> contentsOfFile=new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {
            fileReader=new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            if (file.isFile()){
                while ((line=bufferedReader.readLine())!=null){
                    contentsOfFile.add(line);
                }
            }
            else {
                System.out.println("Файл не найден.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentsOfFile;
    }
}
