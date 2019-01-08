package com.share.lifetime.support.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 解码器
 * @author liaoxiang
 * @date 2018/11/11
 */
public class ByteToStringDecoder extends ByteToMessageDecoder {

    private final String charsetName;

    private final int readableLength;

    private final int lengthFieldLength;

    private boolean flag = true;

    private String dataLengthStr;

    private int dataLength;

    public ByteToStringDecoder(String charsetName, int readableLength, int lengthFieldLength) {
        super();
        if (charsetName == null) {
            throw new NullPointerException("charsetName");
        }
        this.charsetName = charsetName;
        this.readableLength = readableLength;
        this.lengthFieldLength = lengthFieldLength;
    }

    @Override
     protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
         if (flag) {
             byte[] dst = new byte[lengthFieldLength];
             in.readBytes(dst);
             dataLengthStr = new String(dst, charsetName);
             dataLength = Integer.valueOf(dataLengthStr);// 读取报文中消息长度
             flag = false;
         }

         int readableBytes = in.readableBytes();

         if (readableBytes < readableLength) { // 可读长度必须大于基本长度
             return;
         }
         in.markReaderIndex(); // 标记一下当前readIndex的索引位置
         if (dataLength < 0) { // 读到的消息体长度为0，这是不应该出现的情况，关闭连接。
             ctx.close();
             throw new RuntimeException("报文长度不能小于0！");
         }

         readableBytes = in.readableBytes();
         if (readableBytes < dataLength) { // 读到的消息体长度如果小于传送过来的消息长度，则resetReaderIndex.
             // 这个配合markReaderIndex使用的。把readIndex重置到markReaderIndex索引位置
             in.resetReaderIndex();
             return;
         }

         // 读取data数据
         byte[] data = new byte[dataLength];
         in.readBytes(data);
         String dataStr = dataLengthStr + new String(data, charsetName);
         out.add(dataStr);
         flag = true;

     }
}