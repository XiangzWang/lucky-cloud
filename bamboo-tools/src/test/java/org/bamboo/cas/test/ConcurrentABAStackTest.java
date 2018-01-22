package org.bamboo.cas.test;

import org.bamboo.cas.ConcurrentABAStack;
import org.bamboo.cas.Node;

import java.util.concurrent.TimeUnit;

public class ConcurrentABAStackTest {
    public static void main(String[] args) {
        final ConcurrentABAStack stack = new ConcurrentABAStack();
        stack.push(new Node("A"));
        stack.push(new Node("B"));

        Thread t1 = new Thread() {
            public void run() {
                stack.pop(3);
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                Node A = stack.pop(0);
                stack.pop(0);
                stack.push(new Node("D"));
                stack.push(new Node("C"));
                stack.push(A);
            }
        };

        t1.start();
        t2.start();

        try {
            System.out.println("main, sleep 7s...");
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main输出stack...");
        printStack(stack.top.get());
    }

    private static void printStack(Node node) {
        if (node != null) {
            System.out.println(node.item + "->");
            if (node.next != null) {
                printStack(node.next);
            }
        } else {
            System.out.println(null + "->");
        }
    }
}
