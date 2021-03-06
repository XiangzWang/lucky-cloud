package org.bamboo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentABAStack {
    public AtomicReference<Node> top = new AtomicReference<>();

    public void push(Node node) {
        Node newTop = node;
        Node oldTop;
        do {
            oldTop = top.get();
            newTop.next = oldTop;
        } while (!top.compareAndSet(oldTop, newTop));

        System.out.println("push " + node.item + ", 输出stack...");
        printNode(top.get());
    }

    public Node pop(int time) {
        Node newTop;
        Node oldTop;
        do {
            oldTop = top.get();
            if (oldTop == null) {
                System.out.println("pop " + null + "...");
                return null;
            }
            newTop = oldTop.next;

            try {
                System.out.println("pop " + oldTop.item + ", sleep " + time + "s...");
                System.out.println("pop " + oldTop.item + ", sleep前输出stack...");
                printNode(top.get());

                TimeUnit.SECONDS.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!top.compareAndSet(oldTop, newTop));

        System.out.println("pop " + oldTop.item + ", sleep后输出stack...");
        printNode(top.get());

        return oldTop;
    }

    private void printNode(Node node) {
        if (node != null) {
            System.out.println(node.item + "->");
            if (node.next != null) {
                printNode(node.next);
            }
        } else {
            System.out.println(null + "->");
        }
    }
}
