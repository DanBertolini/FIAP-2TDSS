package br.com.natura.fiap.naturatododia.entity;

public class ChatMessage {
    private String message;
    private boolean isBotMessage;

    public ChatMessage(String message, boolean isBotMessage){
        this.message = message;
        this.isBotMessage = isBotMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBotMessage() {
        return isBotMessage;
    }

    public void setIsBotMessage(boolean isBotMessage) {
        this.isBotMessage = isBotMessage;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
