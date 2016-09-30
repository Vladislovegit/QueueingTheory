package sample;

public class Buffer {

    private static final Integer amountOfSpace = 2;
    private static Integer packetsInQueue = 0;

    public static Boolean isFull() {
        return (packetsInQueue.equals(amountOfSpace));
    }

    public static Boolean isHavePacket() {
        return ((packetsInQueue == 1) || (packetsInQueue == 2));
    }

    public static void addPacket() {
        if (packetsInQueue < amountOfSpace) {
            packetsInQueue++;
        }
    }

    public static void removePacket() {
        if (packetsInQueue > 0) {
            packetsInQueue--;
        }
    }

    public static Integer getLength() {
        return packetsInQueue;
    }

    public static void reset() {
        packetsInQueue = 0;
    }
}
