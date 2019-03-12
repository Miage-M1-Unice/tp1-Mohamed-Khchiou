package fr.miage.tp2;
/**
 * @version 1.00 23 Mars 2001
 * @author Michel Buffa
 * Inspiré par la classe Reflectiontest.java de
 * Cay S. Horstmann & Gary Cornell, publiée dans le livre Core Java, Sun Press
 */

import java.awt.*;
import java.lang.reflect.*;
import java.io.*;

public class AnalyseurDeClasse {

  public static void analyseClasse(String nomClasse) throws ClassNotFoundException {
    // Récupération d'un objet de type Class correspondant au nom passé en paramètres
    Class cl = getClasse(nomClasse);

    afficheEnTeteClasse(cl);

    System.out.println("\t// Champs");
    afficheAttributs(cl);

    System.out.println("\t// Constructeurs");
    afficheConstructeurs(cl);

    System.out.println("\t// Méthodes");
    afficheMethodes(cl);

    // L'accolade fermante de fin de classe !
    System.out.println("}");
  }


  /** Retourne la classe dont le nom est passé en paramètre */
  public static Class getClasse(String nomClasse) throws ClassNotFoundException {
    return Class.forName(nomClasse);
  }

  /** Cette méthode affiche par ex "public class Toto extends Tata implements Titi, Tutu {" */
  public static void afficheEnTeteClasse(Class cl) {
    //  Affichage du modifier et du nom de la classe
    System.out.print("public class "+ cl.getName());

   // Récupération de la superclasse si elle existe (null si cl est le type Object)
    Class supercl = cl.getSuperclass();// CODE A ECRIRE

    // On ecrit le "extends " que si la superclasse est non nulle et différente de Object

    if(supercl != null && supercl.getName() != "java.lang.Object")
      System.out.print(" extends "+ supercl.getName()+" ");

    // Affichage des interfaces que la classe implemente
    if(cl.getInterfaces().length >=1){
      System.out.print(" implements ");
      for (Class i : cl.getInterfaces()) {
        System.out.print(i.toString());
      }
    }

    // Enfin, l'accolade ouvrante !
    System.out.println(" {\n");
  }

  public static void afficheAttributs(Class cl) {
    for (Field f : cl.getDeclaredFields()) {
      System.out.println("\t"+Modifier.toString(f.getModifiers()) + " "+ f.getType().toString() +" " + f.getName()+";");
    }
    System.out.println("\n");
  }

  public static void afficheConstructeurs(Class cl) {
    for (Constructor c :cl.getConstructors()) {
      System.out.println("\t"+c.toString()+";");
    }
    System.out.println("\n");
  }

  public static void afficheMethodes(Class cl) {
    for (Method m :cl.getDeclaredMethods()) {
      System.out.print("\t"+Modifier.toString(m.getModifiers()) + " "+ m.getReturnType().toString() +" " + m.getName() + "(");
      int i = 1;
      for (Parameter p: m.getParameters()) {
        System.out.print(p.getType().toString());
        if(i<m.getParameters().length){
          System.out.print(", ");
          i++;
        }
      }
      System.out.print(");\n");
    }
    System.out.print("\n");

  }

  public static String litChaineAuClavier() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      return br.readLine();
  }

  public static void main(String[] args) {
    boolean ok = false;

    Point p;
    while(!ok) {
      try {
        //System.out.print("Entrez le nom d'une classe (ex : java.awt.Point): ");
        String nomClasse = "java.awt.Point"; //litChaineAuClavier();

        analyseClasse(nomClasse);

        ok = true;
      } catch(ClassNotFoundException e) {
        System.out.println("Classe non trouvée.");
      }
    }
  }
}
