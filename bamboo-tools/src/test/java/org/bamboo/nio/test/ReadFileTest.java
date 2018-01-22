package org.bamboo.nio.test;

import java.io.FileInputStream;
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
    static public void main( String args[] ) throws Exception {
        FileInputStream fin = new FileInputStream("e:\\test.txt");

        // 获取通道
        FileChannel fc = fin.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        /*while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print(((char)b));
        }*/

        while (buffer.remaining() > 0) {
            byte[] bs = new byte[1024];
            buffer.get(bs, 0, buffer.remaining());

            System.out.print(new String(bs, "UTF-8"));
        }

        fin.close();
    }
}
