package org.bamboo.nio.test;

import java.io.*;

public class ReadFileIOTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fin = new FileInputStream("E:\\swz.mp4");
        FileOutputStream fout = new FileOutputStream("e:\\swz2.mp4");

        String str = "";
        try {
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = fin.read(buf)) > 0) {
                fout.write(buf, 0, bytesRead);
            }

            /*BufferedReader reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            System.out.println(sb.toString());*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                fin.close();
            }
            if (fout != null) {
                fout.close();
            }
        }

        System.out.println();
        System.out.println(System.currentTimeMillis() - start);
    }
}
