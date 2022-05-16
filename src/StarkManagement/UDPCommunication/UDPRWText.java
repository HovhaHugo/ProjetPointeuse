package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;

public class UDPRWText {

    private byte[] sB; /* The buffer array. */

    /**
     * To create a sending packet send with a txt message.
     */
    protected DatagramPacket getTextSendingPacket(InetSocketAddress isA, String msg) throws
            IOException {
        sB = toBytes(msg, new byte[2048]);
        return new DatagramPacket(sB, 0, sB.length, isA.getAddress(), isA.getPort());
    }

    /**
     * To set the Msg to a parametter packet.
     */
    protected void setMsg(DatagramPacket dP, String msg)  {
        toBytes(msg, dP.getData());
    }

    private byte[] toBytes(String msg, byte[] lbuf) {
        byte[] array = msg.getBytes();
        if (array.length < lbuf.length)
            System.arraycopy(array, 0, lbuf, 0, array.length);
        return lbuf;
    }

    /**
     * To extract the txt message from a packet.
     */
    protected String getMsg(DatagramPacket dP) {
        sB = dP.getData();
        for (int i = 0; i < sB.length; i++) {
            if (sB[i] == 0) {
                p = i;
                i = sB.length;
            }
        }
        return new String(dP.getData(), 0, p);
    }

    private int p;

}
