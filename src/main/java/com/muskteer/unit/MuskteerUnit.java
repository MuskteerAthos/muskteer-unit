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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.muskteer.unit.util.LoggerFactory;
import com.muskteer.unit.util.MuskLogger;

/**
 * a simple java unit test framework.<br>
 * support multi-method simultaneous testing.<br>
 * when multi-method testing, you can do testing in order by sequence.<br>
 * due to the sequence, it also can support simple transaction testing.<br>
 */
public class MuskteerUnit {
    
    private static Class<?> muskteerClass;
    private static Class<?> muskteerClassUnit;
    
    private static MuskLogger logger = 
            LoggerFactory.getLogInstance(MuskteerUnit.class, "Unit Testing");

    public static <T> void unit(Class<T> testclass) {
        try {
            logger.info("unit started.\n");
            checkMuskteerClass(testclass);
            List<Pair> pairs = interationMethod(muskteerClass.getDeclaredMethods());
            executePairs(pairs);
            logger.info("unit done.\n" );
        } catch (MessageException e) {
            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.info("unit done.\n exception :"  + e.getMessage());
        }
        
    }

    private static <T> void checkMuskteerClass(Class<T> testCls) throws Exception {
        if((muskteerClass = testCls) == null){
            throw new MessageException("unit exit, can not load execute class");
        }
        String cunitname = muskteerClass.getCanonicalName() + "Unit";
        muskteerClassUnit = Class.forName(cunitname);
    }

    private static void executePairs(List<Pair> pairs) throws Exception {
        if (pairs.size() == 0) {
            return;
        }
        Iterator<Pair> it = pairs.iterator();
        Pair p;
        while(it.hasNext()){
            p = it.next();
            invokeUnit(p);
        }
    }

    private static void invokeUnit(Pair p) {
        if (p == null) return;
        Method pMethod = (Method) p.getK();
        logger.info("execute method : ".concat(pMethod.getName().concat(" started")));
        try {
            pMethod.invoke(
                    muskteerClass.newInstance(), p.getJ() == null ? null : buildDataFromUnit(pMethod));
            logger.info("execute method : ".concat(pMethod.getName().concat(" finish ")));
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("execute method : ".concat(pMethod.getName().concat(" exception : " + e.getMessage())));
        }
    }

    private static <T> Object buildDataFromUnit(Method pMethod) 
            throws Exception {
        Method cm = muskteerClassUnit.getMethod(pMethod.getName());
        return cm.invoke(muskteerClassUnit.newInstance());
    }

    private static ArrayList<Pair> interationMethod(Method[] methods) {
        ArrayList<Pair> list = new ArrayList<Pair>();
        if (methods.length == 0) {
            return null;
        }
        for (Method m : methods) {
            Pair p = literateAnnotation(m);
            if (p != null) {
                list.add(p);
            }
        }
        Collections.sort(list, new CompareBySequence());
        return list;
    }

    private static Pair literateAnnotation(Method m) {
        Annotation[] annotations = m.getDeclaredAnnotations();
        for (Annotation n : annotations) {
            if (n.annotationType() == Muskteer.class && ((Muskteer) n).state() == true) {
                Pair p = new Pair(m, ((Muskteer) n).sequence(), 
                        m.getParameterTypes(),
                        m.getReturnType());
                System.out.println(p.toString());
                return p;
            }
        }
        return null;
    }

}
