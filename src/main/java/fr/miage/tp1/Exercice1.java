package fr.miage.tp1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Exercice1 {
    public class MyInternalFilter implements FilenameFilter {
        String filter;

        public MyInternalFilter(String filter){
            this.filter = filter;
        }

        public MyInternalFilter(){
            this.filter = ".java";
        }


        public boolean accept(File dir, String name) {
            return name.contains(filter);
        }
    }

    File file;

    public Exercice1(File file) {
        this.file = file;
    }

    private void printRecusifFile(File file){

        for (File f: file.listFiles()) {
            if (f.isDirectory()) {
                printRecusifFile(f);
            } else {
                System.out.println(f);
            }
        }
    }

    //filter with independent class
    private void filterFileExterne(File file, String filter){
        MyFilter myFilter = new MyFilter(filter);

        for (File f: file.listFiles()) {

            if (f.isDirectory()) {
                filterFileExterne(f, filter);
            } else if (myFilter.accept(f, f.getName())){
                System.out.println(f);
            }
        }
    }

    //filter with intern class
    private void filterFileInternal(File file, String filter){
        MyInternalFilter myFilter = new MyInternalFilter(filter);

        for (File f: file.listFiles()) {

            if (f.isDirectory()) {
                filterFileInternal(f, filter);
            } else if (myFilter.accept(f, f.getName())){
                System.out.println(f);
            }
        }
    }

    //filter with anonymous class
    private void filterFileAnonymous(File file){


        for (File f: file.listFiles()) {

            if (f.isDirectory()) {
                filterFileAnonymous(f);
            } else if (new FilenameFilter(){

                public boolean accept(File dir, String name) {
                    return name.contains(".java");
                }
            }.accept(f, f.getName())){
                System.out.println(f);
            }
        }
    }

    private void filterFileAnonymous2(File file, String filter){

        for (File f: file.listFiles()) {

            if (f.isDirectory()) {
                filterFileAnonymous2(f, filter);
            } else if (this.new MyInternalFilter(filter).accept(f, f.getName())){
                System.out.println(f);
            }
        }
    }

    public static void main(String[] args){


        String path = ".";

        Exercice1 exo1 = new Exercice1(new File(path));

        System.out.println("Begin");

        //TODO : Uncomment code to test

        //liste le contenu de "."
        //System.out.println(Arrays.toString(exo1.file.list()));


        //Liste le contenu de "." de facon recurcive
        //exo1.printRecusifFile(exo1.file);

        //Liste le contenu de "." de facon recurcive en utilisant un filtre
        //Classe externe
        //exo1.filterFileExterne(exo1.file, ".java");

        //Classe interne
        //exo1.filterFileInternal(exo1.file, ".java");

        //Anonymous class
        exo1.filterFileAnonymous(exo1.file);

        System.out.println("End");
    }
}
