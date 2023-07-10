package com.aold.advers.ble.handlers;

public class JavaPackages {

    public final void valToPacket(boolean isSetter, int[] typeParam, int[] numParam, long[] param) {

        byte[] packet = new byte[20];
        packet[0] = 2;
        int i = 0;

        for(i = 0; i <= 2; i++) {
            if (isSetter) {
                packet[1 + i * 3] = (byte)128;
            } else {
                packet[1 + i * 3] = (byte)0;
            }

            packet[1 + i * 3] = (byte)(packet[1 + i * 3] + typeParam[i]);
            packet[2 + i * 3] = (byte)numParam[i];
            packet[3 + i * 3] = (byte)((int)param[i]);
        }

        packet[19] = 0;
    }

    public final void packetToVal(boolean isSetter, int[] typeParam, int[] numParam, long[] param) {

        byte[] packet = new byte[20];
        packet[0] = 2;
        int i = 0;

        for(i = 0; i <= 2; i++) {
            if (isSetter) {
                packet[1 + i * 3] = (byte)128;
            } else {
                packet[1 + i * 3] = (byte)0;
            }

            packet[1 + i * 3] = (byte)(packet[1 + i * 3] + typeParam[i]);
            packet[2 + i * 3] = (byte)numParam[i];
            packet[3 + i * 3] = (byte)((int)param[i]);
        }

        packet[19] = 0;
    }
}
