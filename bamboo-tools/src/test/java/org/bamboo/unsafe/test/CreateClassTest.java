package org.bamboo.unsafe.test;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

class A implements Serializable {
    private int num;
    public A(int num) {
        System.out.println("Hello Num");
        this.num = num;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}

public class CreateClassTest {
    public static void main(String[] args) {
        Field f = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            Class aClass = A.class;
            A a = (A) unsafe.allocateInstance(aClass);
            a.setNum(128);
            System.out.println(a.getNum());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
