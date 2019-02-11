package fr.miage.tp1;

import java.io.File;
import java.util.Arrays;

public class Exercice1 {


    public static void main(String[] args){

        String path = ".";

        File file = new File(path);

        System.out.println("Begin");

        System.out.println(Arrays.toString(file.list()));

        System.out.println("End");
    }
}
