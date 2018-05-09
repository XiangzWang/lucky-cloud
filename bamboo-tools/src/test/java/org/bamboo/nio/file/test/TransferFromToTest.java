package org.bamboo.nio.file.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferFromToTest {
    public static void main(String[] args) {
//        transferFrom();
        transferTo();
    }

    public static void transferFrom() {
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromFile = new RandomAccessFile("E:/fromFile.txt", "rw");
            toFile = new RandomAccessFile("E:/toFile.txt", "rw");
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            System.out.println(count);
            toChannel.transferFrom(fromChannel, position, count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fromFile != null) {
                    fromFile.close();
                }
                if (toFile != null) {
                    toFile.close();
                }
                if (fromChannel != null) {
                    fromChannel.close();
                }
                if (toChannel != null) {
                    toChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void transferTo() {
        RandomAccessFile fromFile = null;
        RandomAccessFile toFile = null;
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromFile = new RandomAccessFile("E:/fromFile.txt", "rw");
            toFile = new RandomAccessFile("E:/toFile.txt", "rw");
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            long position = 0;
            long count = fromChannel.size();
            System.out.println(count);
            fromChannel.transferTo(position, count, toChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fromFile != null) {
                    fromFile.close();
                }
                if (toFile != null) {
                    toFile.close();
                }
                if (fromChannel != null) {
                    fromChannel.close();
                }
                if (toChannel != null) {
                    toChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
