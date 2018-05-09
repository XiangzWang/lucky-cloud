package org.bamboo.nio.file.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用NIO写入数据可以分为下面三个步骤：<br/>
 1. 从FileOutputStream获取Channel<br/>
 2. 创建Buffer<br/>
 3. 将数据从Buffer写入到Channel中<br/>

 下面是一个简单的使用NIO从文件中读取数据的例子.<br/>
 */
public class WriteFileTest {

    public static void main(String args[]) {
        String msg = "会挽雕弓如满月，西北望，射天狼...hahahaha...";
        writeFile(msg);
    }

    public static void writeFile(String msg) {
        if (msg == null) {
            msg = "";
        }

        FileOutputStream fout = null;
        FileChannel fc = null;
        String filePath = "e:\\test1.txt";
        try {
            fout = new FileOutputStream(filePath);

            // 获取通道
            fc = fout.getChannel();

            byte[] msgBytes = msg.getBytes();

            // 创建缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(msgBytes.length);

            for (int i = 0; i < msgBytes.length; ++i) {
                buffer.put(msgBytes[i]);
            }

            buffer.flip();

            // 数据从缓冲区写入通道
            fc.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
