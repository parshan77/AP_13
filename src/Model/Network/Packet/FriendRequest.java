package Model.Network.Packet;

public class FriendRequest {
    private String SenderUsername;
    private String ReceiverUsername;
    private boolean accepted;

    public FriendRequest(String senderUsername, String receiverUsername) {
        SenderUsername = senderUsername;
        ReceiverUsername = receiverUsername;
    }


    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getSenderUsername() {
        return SenderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        SenderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return ReceiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        ReceiverUsername = receiverUsername;
    }
}
