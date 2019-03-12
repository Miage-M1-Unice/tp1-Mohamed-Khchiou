package fr.miage.tp3;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;

public class MyClassLoader extends SecureClassLoader {

    private ArrayList<File> path = null;
    public MyClassLoader(ArrayList<File> p) {
        this.path = p;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws ClassNotFoundException {

        for (int i = 0; i < path.size(); i++) {
            String url = path.get(i).getAbsolutePath() + File.separator + name.replace(".", File.separator) + ".class";
            File f = new File(url);

            if (f.exists()) {
                try {
                    return Files.readAllBytes(f.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

        ArrayList<File> al = new ArrayList<File>();
        al.add(new File("C:\\Users\\ame_k\\Documents\\Cours\\M1 MIAGE\\Outils de G. Logicielle\\outils-gl-Mohamed-Khchiou\\target\\classes"));

        MyClassLoader myCL = new MyClassLoader(al);
        try {
            myCL.loadClass("fr.unice.miage.Board");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}