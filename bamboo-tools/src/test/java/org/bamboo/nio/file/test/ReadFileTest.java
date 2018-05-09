package org.bamboo.nio.file.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用NIO读取数据可以分为下面三个步骤：<br/>
 1. 从FileInputStream获取Channel<br/>
 2. 创建Buffer<br/>
 3. 将数据从Channel读取到Buffer中<br/>

 下面是一个简单的使用NIO从文件中读取数据的例子.<br/>
 */
public class ReadFileTest {
    private final static Logger logger = LoggerFactory.getLogger(ReadFileTest.class);

    static public void main(String[] args) {
//        copyFile();
        printTextFile();
    }

    static public void copyFile() {
        logger.debug("拷贝文件开始");
        long start = System.currentTimeMillis();

        FileInputStream fin = null;
        FileOutputStream fout = null;
        FileChannel fc = null;
        FileChannel foc = null;
        long fileLength = 0L;
        String fileName = "";
        String filePath = "F:/[电影天堂-www.dy2018.net]哥伦比亚人BD中英双字.rmvb";
        String filePath2 = "F:/[电影天堂-www.dy2018.net]哥伦比亚人BD中英双字2.rmvb";
        try {
            File file = new File(filePath);
            fileLength = file.length() == 0 ? 0 : file.length() / 1024;
            fileName = file.getName();
            fin = new FileInputStream(file);
            fout = new FileOutputStream(filePath2);

            // 获取通道
            fc = fin.getChannel();

            foc = fout.getChannel();

            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(5 * 1024 * 1024);

            // 读取数据到缓冲区
            int bytesRead = fc.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.remaining() > 0) {
                    foc.write(buffer);
                }
                buffer.clear();
                bytesRead = fc.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (fout != null) {
                    fout.close();
                }
                if (fc != null) {
                    fc.close();
                }
                if (foc != null) {
                    foc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        logger.debug("拷贝 - 文件:{}, 大小:{}KB, 耗时:{}ms", fileName, fileLength, end - start);
    }

    /**
     * 打印英文字符.中文乱码,BOM格式会打印编码标识
     */
    static public void printTextFile() {
        logger.debug("打印文件开始");
        long start = System.currentTimeMillis();
//        FileInputStream fin = null;
        RandomAccessFile fin = null;
        FileChannel fc = null;
        String filePath = "E:/test.txt";
        try {
//            fin = new FileInputStream(filePath);
            fin = new RandomAccessFile(filePath, "r");

            // 获取通道
            fc = fin.getChannel();

            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(5 * 1024);

            // 读取数据到缓冲区
            int bytesRead = fc.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
//                    if (b > 0) {
                        System.out.print((char) b);
//                    }
                }
                buffer.clear();
                bytesRead = fc.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        long end = System.currentTimeMillis();
        logger.debug("打印 - 文件:{}, 耗时:{} ms", filePath, end - start);
    }
}
