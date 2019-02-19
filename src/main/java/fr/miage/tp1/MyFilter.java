package fr.miage.tp1;

import java.io.File;
import java.io.FilenameFilter;

public class MyFilter implements FilenameFilter {
    String filter;

    public MyFilter(String filter){
        this.filter = filter;
    }

    public MyFilter(){
        this.filter = ".java";
    }


    public boolean accept(File dir, String name) {
        return name.contains(filter);
    }
}
