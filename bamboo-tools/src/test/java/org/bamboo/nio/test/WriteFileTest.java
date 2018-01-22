package org.bamboo.nio.test;

import java.io.FileOutputStream;
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

    private static String msg = "会挽雕弓如满月，西北望，射天狼...hahahaha...";
    static private final byte message[] = msg.getBytes();

    static public void main(String args[]) throws Exception {
        FileOutputStream fout = new FileOutputStream("e:\\test1.txt");

        // 获取通道
        FileChannel fc = fout.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }

        buffer.flip();

        // 数据从缓冲区写入通道
        fc.write(buffer);

        fout.close();
    }
}
