package org.bamboo.io.file.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ReadFileIOTest {
    private final static Logger logger = LoggerFactory.getLogger(ReadFileIOTest.class);

    public static void main(String[] args) throws IOException {
        copyFile();
        copyFile2();
        printTextFile();
        printTextFile2();
    }

    /**
     * BufferedReader按行读取文件
     *
     * @throws IOException
     */
    public static void printTextFile() throws IOException {
        logger.debug("打印文件开始");
        long start = System.currentTimeMillis();
        FileInputStream fin = null;
        String filePath = "E:/test.txt";
        String str = "";
        try {
            fin = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                fin.close();
            }
        }

        System.out.println();
        long end = System.currentTimeMillis();
        logger.debug("打印 - 文件:{}, 耗时:{} ms", filePath, end - start);
    }

    /**
     * BufferedInputStream按字节数组(byte[])读取文件,打印中文乱码
     */
    public static void printTextFile2() {
        logger.debug("打印文件开始");
        long start = System.currentTimeMillis();
        InputStream in = null;
        String filePath = "E:/test.txt";
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));

            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        long end = System.currentTimeMillis();
        logger.debug("打印 - 文件:{}, 耗时:{} ms", filePath, end - start);
    }

    /**
     * FileInputStream拷贝文件
     *
     * @throws IOException
     */
    public static void copyFile() throws IOException {
        logger.debug("拷贝文件开始");
        long start = System.currentTimeMillis();

        File file = null;
        long fileLength = 0;
        String fileName = "";
        FileInputStream fin = null;
        FileOutputStream fout = null;
        String filePath = "E:\\dataInput.log";
        String filePath2 = "E:\\dataInput_copy.log";
        try {
            file = new File(filePath);
            fileLength = file.length() == 0 ? 0 : file.length() / 1024;
            fileName = file.getName();
            fin = new FileInputStream(file);
            fout = new FileOutputStream(filePath2);

            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = fin.read(buf)) > 0) {
                fout.write(buf, 0, bytesRead);
            }
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

        long end = System.currentTimeMillis();
        logger.debug("拷贝 - 文件:{}, 大小:{}KB, 耗时:{}ms", fileName, fileLength, end - start);
    }

    /**
     * BufferedInputStream拷贝文件
     *
     * @throws IOException
     */
    public static void copyFile2() throws IOException {
        logger.debug("拷贝文件开始");
        long start = System.currentTimeMillis();

        File file = null;
        long fileLength = 0;
        String fileName = "";
        InputStream fin = null;
        FileOutputStream fout = null;
        String filePath = "E:\\dataInput.log";
        String filePath2 = "E:\\dataInput_copy.log";
        try {
            file = new File(filePath);
            fileLength = file.length() == 0 ? 0 : file.length() / 1024;
            fileName = file.getName();
            fin = new BufferedInputStream(new FileInputStream(file));
            fout = new FileOutputStream(filePath2);

            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = fin.read(buf)) > 0) {
                fout.write(buf, 0, bytesRead);
            }
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

        long end = System.currentTimeMillis();
        logger.debug("拷贝 - 文件:{}, 大小:{}KB, 耗时:{}ms", fileName, fileLength, end - start);
    }
}
