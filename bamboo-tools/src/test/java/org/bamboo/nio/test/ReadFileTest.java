package org.bamboo.nio.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        long start = System.currentTimeMillis();
        FileInputStream fin = new FileInputStream("E:\\swz.mp4");
        FileOutputStream fout = new FileOutputStream("e:\\swz2.mp4");


        // 获取通道
        FileChannel fc = fin.getChannel();

        FileChannel foc = fout.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(50 * 1024 * 1024);

        // 读取数据到缓冲区
        int bytesRead = fc.read(buffer);

       /* while(bytesRead != -1) {
            buffer.flip();
            while(buffer.hasRemaining()) {
                byte b = buffer.get();
                System.out.print((char)b);
            }
            buffer.compact();
            bytesRead = fc.read(buffer);
        }*/

        /*buffer.flip();
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print(((char)b));
        }*/

        while(bytesRead != -1) {
            buffer.flip();
            while (buffer.remaining() > 0) {
                foc.write(buffer);

//                byte[] bs = new byte[buffer.remaining()];
//                buffer.get(bs, 0, buffer.remaining());
//                System.out.println(new String(bs, "UTF-8"));
            }
            buffer.compact();
            bytesRead = fc.read(buffer);
        }

        fin.close();

        System.out.println();
        System.out.println(System.currentTimeMillis() - start);
    }
}
