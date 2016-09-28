package sample;

import java.util.Random;

public class Channel {

    private Integer probability;
    private Boolean channelBusy = false;

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public Boolean isPacketProcessed() {
        Random random = new Random();
        if(random.nextDouble() < probability) {
            channelBusy = true;
            return false;
        }
        else {
            channelBusy = false;
            return true;
        }
    }

    public Boolean isChannelBusy() {
        return channelBusy;
    }
}
