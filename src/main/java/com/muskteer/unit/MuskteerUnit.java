/**
 * FileName:   MuskteerTest.java
 * @Description unit test framework.
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.unit;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * a simple java unit test framework.
 */
public class MuskteerUnit {

    /**
     * @param cls
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public static <T> void unit(Class<T> cls) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException,
            SecurityException {
        Pair p = interationMethod(cls.getDeclaredMethods());
        if (p == null) {return;}
        Object result = ((Method) p.getK()).invoke(cls.newInstance(), buildDataFromUnit(cls, p));
        System.out.println(result);
    }

    /**
     * @param c
     * @param p
     * @return
     * @throws ClassNotFoundException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private static <T> Object buildDataFromUnit(Class<T> c, Pair p) throws ClassNotFoundException,
            NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException {
        String cunitname = c.getCanonicalName() + "Unit";
        Class<?> cunit = Class.forName(cunitname);
        Method cm = cunit.getMethod(((Method) p.getK()).getName());
        Object o = cm.invoke(cunit.newInstance());
        return o;
    }

    /**
     * @param methods
     * @return
     */
    private static Pair interationMethod(Method[] methods) {
        if (methods.length == 0) {
            return null;
        }
        for (Method m : methods) {
            Pair p = literateAnnotation(m);
            if (p != null) {
                return p;
            }
        }
        return null;
    }

    /**
     * @param m
     * @return
     */
    private static Pair literateAnnotation(Method m) {
        Annotation[] annotations = m.getDeclaredAnnotations();
        for (Annotation n : annotations) {
            if (n.annotationType() == Muskteer.class && ((Muskteer) n).state() == true) {
                Pair p = new Pair(m, ((Muskteer) n).param().equalsIgnoreCase("use"), m.getParameterTypes(),
                        m.getReturnType());
                System.out.println(p.toString());
                return p;
            }
        }
        return null;
    }

}
