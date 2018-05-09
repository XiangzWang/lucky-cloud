package org.bamboo.nio.file.test;

import org.bamboo.nio.LineReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LineReaderTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fin = new FileInputStream("e:\\artifacts.xml");
        LineReader reader = new LineReader(fin);

        byte[] bytes = reader.next();
        while (bytes != null) {
            System.out.print(new String(bytes, "UTF-8"));
            bytes = reader.next();
        }

        fin.close();

        System.out.println();
        System.out.println(System.currentTimeMillis() - start);
    }
}
