package sample.models;

public class Generator {

    private static final Integer interval = 2;
    private static Integer packetsGenerated = 0;

    public static Boolean isPacketReady(Integer tactNumber) {
        if (tactNumber!= 0 && (tactNumber % interval == 0)) {
            packetsGenerated++;
            return true;
        }
        else {
            return false;
        }
    }

    public static Boolean isPacketLost(Integer tactNumber, Boolean channelBusy) {
        return (isPacketReady(tactNumber) && channelBusy);
    }

    public static Integer getPacketsGenerated() {
        return packetsGenerated;
    }

    public static void reset() {
        packetsGenerated = 0;
    }
}
