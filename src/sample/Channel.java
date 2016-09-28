package sample;

import java.util.Random;

public class Channel {

    private Double probability;
    private Boolean channelBusy;
    private Boolean blocked;

    public Channel(Double probability) {
        channelBusy = false;
        blocked = false;
        this.probability = probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public void startProcessing() {
        channelBusy = true;
    }

    public Boolean isPacketProcessed() {
        Random random = new Random();
        Double r = random.nextDouble();
        if(r < probability) {
            channelBusy = true;
            return false;
        }
        else {
            channelBusy = false;
            return true;
        }
    }

    public Boolean isBusy() {
        return channelBusy;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unblock() {
        blocked = false;
    }
}
