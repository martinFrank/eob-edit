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
        return (byte) Integer.parseInt(str, 16);
    }

    public static int asInt(byte[] data) {
        int value = 0;
        for (int i = 0; i < data.length; i++) {
            int shift = i * 8;
            value = value + ((0xff & data[i]) << shift);
        }
        return value;
    }

    public static byte[] fromInt(int value, int size) {
        byte[] data = new byte[size];
        for (int i = 0; i < size; i++) {
            int shift = i * 8;
            data[i] = (byte)(0xFF & (value >> shift));
        }
        return data;
    }
}
