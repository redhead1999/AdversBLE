package com.aold.advers.ble.handlers;

public class JavaPackages {
    public byte[] packet;
    public boolean isSetter, isEmpty, isWait;
    public int[] typeParam;
    public int[] numParam;
    public long[] param;

    JavaPackages(boolean isSetter, int[] typeParam, int[] numParam, long[] param) {
        this.isSetter = isSetter;
        this.typeParam = typeParam;
        this.numParam = numParam;
        this.param = param;
    }

    public final boolean valToPacket() {
        packet = new byte[20];
        packet[0] = 2;
        int i = 0;

        for (i = 0; i <= 2; i++) {
            if (isSetter) {
                packet[1 + i * 3] = (byte) 128;
            } else {
                packet[1 + i * 3] = (byte) 0;
            }

            packet[1 + i * 3] = (byte) (packet[1 + i * 3] + typeParam[i]);
            packet[2 + i * 3] = (byte) numParam[i];
            packet[3 + i * 3] = (byte) ((int) param[i]);
        }
        packet[19] = 0;
        return true;
    }

    public final boolean packetToVal() {
        if (packet[0] == 0x02) {
            isSetter = (packet[1] & 0x80) == 0x80;
            isEmpty = (packet[1] & 0x40) == 0x40;
            isWait = (packet[1] & 0x20) == 0x20;

            typeParam = new int[3];
            numParam = new int[3];
            param = new long[3];

            for (int i = 0; i < 3; i++) {
                typeParam[i] = packet[1 + i * 3] & 0x0f;
                numParam[i] = packet[2 + i * 3];
                param[i] = packet[3 + i * 3] + (packet[4 + i * 3] << 8) + (packet[5 + i * 3] << 8) + (packet[6 + i * 3] << 8);
            }
        } else return false;
        return true;
    }
}

