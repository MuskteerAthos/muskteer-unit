/**
 * FileName:   MuskteerTest.java
 * @Description unit test framework.
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.unit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * a simple java unit test framework.
 */
public class MuskteerUnit {

    public static <T> void unit(Class<T> cls) {
        try {
            System.out.println("unit started.\n");
            Pair p = interationMethod(cls.getDeclaredMethods());
            if (p == null) {
                return;
            }
            Object result = ((Method) p.getK()).invoke(
                    cls.newInstance(), buildDataFromUnit(cls, p));
            System.out.println("unit done.\n execute result:"  + result);
        } catch (Exception e) {
            System.out.println("unit done.\n exception :"  + e.getMessage());
        }
        
    }

    private static <T> Object buildDataFromUnit(Class<T> c, Pair p) 
            throws Exception {
        String cunitname = c.getCanonicalName() + "Unit";
        Class<?> cunit = Class.forName(cunitname);
        Method cm = cunit.getMethod(((Method) p.getK()).getName());
        return cm.invoke(cunit.newInstance());
    }

    private static Pair interationMethod(Method[] methods) {
        if (methods.length != 0) {
            for (Method m : methods) {
                Pair p = literateAnnotation(m);
                if (p != null) {
                    return p;
                }
            }
        }
        return null;
    }

    private static Pair literateAnnotation(Method m) {
        Annotation[] annotations = m.getDeclaredAnnotations();
        for (Annotation n : annotations) {
            if (n.annotationType() == Muskteer.class && ((Muskteer) n).state() == true) {
                Pair p = new Pair(m, ((Muskteer) n).param().equalsIgnoreCase("use"), 
                        m.getParameterTypes(),
                        m.getReturnType());
                System.out.println(p.toString());
                return p;
            }
        }
        return null;
    }

}
