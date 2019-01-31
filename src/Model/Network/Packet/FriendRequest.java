package Model.Network.Packet;

public class FriendRequest {
    private String SenderUsername;
    private String ReceiverUsername;

    public FriendRequest(String senderUsername, String receiverUsername) {
        SenderUsername = senderUsername;
        ReceiverUsername = receiverUsername;
    }
}
