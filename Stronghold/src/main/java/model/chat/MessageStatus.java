package model.chat;

public enum MessageStatus {
    SENT("sent.png"),
    SEEN("seen.png");

    private final String imagePath;

    MessageStatus(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return "file:src/main/resources/images/chat/" + imagePath;
    }
}
