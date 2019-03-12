package fr.miage.tp3;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {

        URL[] urls = new URL[]{
                new URL("file:///Users/ame_k/IdeaProjects/MoyennePOO/out/production/MoyennePOO/")};

        URLClassLoader ucl = new URLClassLoader(urls);

        Class c = ucl.loadClass("Moyenne");

        System.out.println("Class name " + c.getName());


    }
}
