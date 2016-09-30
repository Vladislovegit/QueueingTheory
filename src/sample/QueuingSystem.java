package sample;

public class QueuingSystem {

    private Channel firstChannel;
    private Channel secondChannel;
    private Integer queueLength;
    private Integer denials;
    private Integer packetsProcessed;

    public Integer getDenials() {
        return denials;
    }

    public QueuingSystem(Double probability1, Double probability2) {
        firstChannel = new Channel(probability1);
        secondChannel = new Channel(probability2);
        queueLength = 0;
        denials = 0;
        packetsProcessed = 0;
    }

    public Integer getQueueLength() {
        return queueLength;
    }

    public Integer getPacketsProcessed() {
        return packetsProcessed;
    }

    public void generateNextState(Integer tactNumber) {
        queueLength += Buffer.getLength();

        if (secondChannel.isBusy()) {
            if (secondChannel.isPacketProcessed()) {
                packetsProcessed++;

                if (Buffer.isHavePacket()) {
                    Buffer.removePacket();
                    secondChannel.startProcessing();
                    if(firstChannel.isBlocked()) {
                        firstChannel.unblock();
                    }
                }
            }
        }

        if (!firstChannel.isBlocked()) {
            if (firstChannel.isBusy()) {
                if (firstChannel.isPacketProcessed()) {
                    if (!Buffer.isFull()) {
                        Buffer.addPacket();
                        if (!secondChannel.isBusy()) {
                            Buffer.removePacket();
                            secondChannel.startProcessing();
                        }
                    } else {
                        firstChannel.block();
                    }
                }
            }
        }

        if (Generator.isPacketReady(tactNumber)) {
            if (!firstChannel.isBlocked() && !firstChannel.isBusy()) {
                firstChannel.startProcessing();
            } else {
                denials++;
            }
        }
    }
}
