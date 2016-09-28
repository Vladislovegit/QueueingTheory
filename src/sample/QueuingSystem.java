package sample;

public class QueuingSystem {

    private Channel channel1;
    private Channel channel2;
    private Integer queueLength;
    private Integer denials;

    public Integer getDenials() {
        return denials;
    }

    public QueuingSystem(Double probability1, Double probability2) {
        channel1 = new Channel(probability1);
        channel2 = new Channel(probability2);
        queueLength = 0;
        denials = 0;
    }

    public Integer getQueueLength() {
        return queueLength;
    }

    public void generateNextState(Integer tactNumber) {
        queueLength += Buffer.getLength();

        if (channel2.isBusy()) {
            if (channel2.isPacketProcessed()) {
                if (Buffer.isHavePacket()) {
                    Buffer.removePacket();
                    channel2.startProcessing();
                    if(channel1.isBlocked()) {
                        channel1.unblock();
                    }
                }
            }
        }

        if (!channel1.isBlocked()) {
            if (channel1.isBusy()) {
                if (channel1.isPacketProcessed()) {
                    if (!Buffer.isFull()) {
                        Buffer.addPacket();
                        if (!channel2.isBusy()) {
                            Buffer.removePacket();
                            channel2.startProcessing();
                        }
                    } else {
                        channel1.block();
                    }
                }
            }
        }

        if (Generator.isPacketReady(tactNumber)) {
            if (!channel1.isBlocked() && !channel1.isBusy()) {
                channel1.startProcessing();
            } else {
                denials++;
            }
        }
    }
}
