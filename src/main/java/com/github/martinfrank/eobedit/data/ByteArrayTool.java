package com.github.martinfrank.eobedit.data;

public class ByteArrayTool {

    public static String asString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            if (b == 0) {
                break;
            }
            sb.append(new String(new byte[]{b}));
        }
        return sb.toString();
    }

    public static byte[] copy(byte[] content, int offset, int length) {
        byte[] data = new byte[length];
        System.arraycopy(content, offset, data, 0, length);
        return data;
    }

    public static void set(byte[] dest, byte[] src, int offset, int length) {
        byte[] srcTrimmed = new byte[length];
        for (int i = 0; i < length; i++) {
            srcTrimmed[i] = i < src.length ? src[i] : 0;
        }
        System.arraycopy(srcTrimmed, 0, dest, offset, length);
    }

    public static byte asByte(String str) {
        return (byte)Integer.parseInt(str, 16);
    }
}
