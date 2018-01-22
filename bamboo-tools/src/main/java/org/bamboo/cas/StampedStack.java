package org.bamboo.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class StampedStack {

    public AtomicStampedReference<Node> top = new AtomicStampedReference<Node>(null, 0);

    public void push(Node node) {
        Node oldTop;
        int v;

        do {
            v = top.getStamp();
            oldTop = top.getReference();
            node.next = oldTop;
        } while (!top.compareAndSet(oldTop, node, v, v + 1));
//		}while(!top.compareAndSet(oldTop, node,top.getStamp(),top.getStamp()+1));

        System.out.println("push " + node.item + "(stamp:" + v + "), 输出stack...");
        printNode(top.getReference());
    }

    public Node pop(int time) {
        Node newTop;
        Node oldTop;
        int v;
        do {
            v = top.getStamp();
            oldTop = top.getReference();

            if (oldTop == null) {
                System.out.println("pop " + null + "...");
                return null;
            }
            newTop = oldTop.next;

            try {
                System.out.println("pop " + oldTop.item + "(stamp:" + v + "), sleep " + time + "s...");
                System.out.println("pop " + oldTop.item + "(stamp:" + v + "), sleep前输出stack...");
                printNode(top.getReference());

                TimeUnit.SECONDS.sleep(time);

                System.out.println("pop " + oldTop.item + "(stamp:" + v + "), sleep后输出stack...");
                printNode(top.getReference());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!top.compareAndSet(oldTop, newTop, v, v + 1));
//		}while(!top.compareAndSet(oldTop, newTop,top.getStamp(),top.getStamp()));

        System.out.println("pop " + oldTop.item + "(stamp:" + v + "), sleep后输出stack...");
        printNode(top.getReference());

        return oldTop;
    }

    /*public void get() {
        Node node = top.getReference();
        while (node != null) {
            System.out.println(node.item);
            node = node.next;
        }
    }*/

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