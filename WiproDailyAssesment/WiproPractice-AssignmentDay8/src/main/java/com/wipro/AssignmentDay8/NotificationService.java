package com.wipro.AssignmentDay8;
interface Notifier {
    void send(String message);
}


class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[EMAIL]  → " + message);
    }
}

class SmsNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[SMS]    → " + message);
    }
}

class PushNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[PUSH]   → " + message);
    }
}


class NotifierFactory {
    public static Notifier create(String type) {
        switch (type.toLowerCase()) {
            case "email": return new EmailNotifier();
            case "sms":   return new SmsNotifier();
            case "push":  return new PushNotifier();
            default: throw new IllegalArgumentException("Unknown notifier: " + type);
        }
    }
}


public class NotificationService {
    public static void main(String[] args) {
        String[] channels = {"email", "sms", "push"};

       for(String channel:channels)
       {
    	   Notifier notifier=NotifierFactory.create(channel);
    	   notifier.send("Your OTP is 4821");
       }
    }
}

