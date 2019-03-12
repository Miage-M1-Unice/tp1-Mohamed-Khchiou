package fr.miage.tp2;

import java.awt.*;
import java.lang.reflect.*;

public class GenericToString {

    String returnStatement;

    public GenericToString() {
        this.returnStatement = "";
    }

    public String toString(Object object) throws IllegalAccessException {
        return toString(object, 2);
    }

    public String toString(Object object, int profondeur) throws IllegalAccessException {
        if(profondeur <= 0)
            return "";
        Class cl = object.getClass();
        returnStatement += cl.getName()+"[";
        Field[] champs = cl.getDeclaredFields();
        int i = 1;
        for (Field f:champs) {
            f.setAccessible(true);

            returnStatement += f.getName()+ " = ";
            if(f.getType().isPrimitive()){
                returnStatement += f.get(object);
                if(i<champs.length){
                    returnStatement += "; ";
                    i++;
                }

            }else if(f.getType().isArray()){
                returnStatement += "{ ";
                for (int y = 0; y < Array.getLength(f.get(object)); y++) {
                    returnStatement += Array.get(f.get(object), i).toString();
                    if(y < Array.getLength(f.get(object))-1)
                        returnStatement += ", ";
                }
                returnStatement += " }; ";

            }else {
                returnStatement += "\n";
                returnStatement += toString(f.get(object), profondeur-1);

            }
        }
        return returnStatement+"]";
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(new GenericToString().toString(new Point(12,24)));

        Polygon pol = new Polygon(new int[]{10, 20, 30}, new int[]{20,30, 40}, 3);
        pol.getBounds();
        System.out.println(new GenericToString().toString(pol, 2));
    }
}
