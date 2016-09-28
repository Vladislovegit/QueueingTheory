package sample;

public class Generator {

    private static final Integer Interval = 2;

    public static Boolean isPacketReady(Integer tactNumber) {
        return tactNumber!= 0 && (tactNumber % Interval == 0);
    }

    public static Boolean isPacketLost(Integer tactNumber, Boolean channelBusy) {
        return (isPacketReady(tactNumber) && channelBusy);
    }

}
