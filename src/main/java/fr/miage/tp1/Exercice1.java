package fr.miage.tp1;

import java.io.File;
import java.util.Arrays;

public class Exercice1 {

    File file;

    public Exercice1(File file) {
        this.file = file;
    }

    private void printRecusifFile(File file){
        System.out.println("Bablabla");

        for (File f: file.listFiles()) {
            if (f.isDirectory()) {
                printRecusifFile(f);
            } else {
                System.out.println(f);
            }
        }
    }

//    //filter with independent class
//    private void filterFile(File file, String filter){
//        MyFilter myFilter = new MyFilter(filter);
//
//        for (File f: file.listFiles(myFilter)) {
//            if (f.isDirectory()) {
//                System.out.println(file.toString());
//                printRecusifFile(file);
//            } else {
//                System.out.println(f);
//            }
//        }
//    }

    public static void main(String[] args){


        String path = ".";

        Exercice1 exo1 = new Exercice1(new File(path));

        System.out.println("Begin");

        System.out.println(Arrays.toString(exo1.file.list()));

        exo1.printRecusifFile(exo1.file);

        //exo1.filterFile(exo1.file, "*.java");

        System.out.println("End");
    }
}
