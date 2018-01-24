package org.bamboo.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class LineReader {

    private FileInputStream fin;
    private FileChannel fc;
    // 创建缓冲区
    private ByteBuffer fbb = ByteBuffer.allocate(4 * 1024);
    // 每行缓存的字节,根据实际需求
    private ByteBuffer lineLeft = ByteBuffer.allocate(1026);

    private boolean EOF = false;

    public LineReader(FileInputStream fin) throws IOException {
        this.fin = fin;
        // 获取通道
        this.fc = fin.getChannel();
    }

    public byte[] next() throws IOException {
        fbb.clear();
        fc.read(fbb);
        fbb.flip();
        ByteBuffer res = ByteBuffer.allocate(5 * 1024);
        res.flip();
        if (fbb.remaining() > 0) {
            while (fbb.remaining() > 0) {
                byte b = fbb.get();
                lineLeft.put(b);
                if (b == 10) {
                    lineLeft.flip();
                    res.limit(res.limit() + lineLeft.limit());
                    res.put(lineLeft);
                    lineLeft.clear();
                }
            }
        } else { // 处理最后一行不带换行
            lineLeft.flip();
            res.limit(res.limit() + lineLeft.limit());
            res.put(lineLeft);
            lineLeft.clear();
        }

        res.flip();
        if (res.position() != res.limit()) {
            return Arrays.copyOfRange(res.array(), res.position(), res.limit());
        }
        return null;
    }
}
