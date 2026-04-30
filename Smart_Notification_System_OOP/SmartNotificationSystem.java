class User {
    private String userId;
    private String userName;
    private String preferredChannel;

    public User(String userId, String userName, String preferredChannel) {
        this.userId = userId;
        this.userName = userName;
        this.preferredChannel = preferredChannel;
    }

    public boolean validateUser() {
        if (!userId.startsWith("USR-")) return false;

        return Channel.validateChannel(preferredChannel);
    }

    public String getPreferredChannel() {
        return preferredChannel;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }
}

class Notification {
    private String notificationId;
    private User user;
    private String message;
    private int priority;
    private String status;

    public Notification(String notificationId, User user, String message, int priority) {
        this.notificationId = notificationId;
        this.user = user;
        this.message = message;
        this.priority = priority;
        this.status = "PENDING";
    }

    public boolean validateNotification() {
        return notificationId.startsWith("NOT-")
                && message != null && message.length() >= 5
                && priority >= 1 && priority <= 5
                && user != null && user.validateUser();
    }

    public void markAsSent() {
        status = "SENT";
    }

    public int getPriority() {
        return priority;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getNotificationId() {
        return notificationId;
    }
}

class Channel {
    public static String[] supportedChannels = {"Email", "SMS", "App"};

    public static boolean validateChannel(String channelName) {
        for (int i = 0; i < supportedChannels.length; i++) {
            if (supportedChannels[i].equalsIgnoreCase(channelName)) {
                return true;
            }
        }
        return false;
    }

    public void send(Notification notification) {
        if (notification.validateNotification()) {
            notification.markAsSent();
            System.out.println("Notification sent through " + notification.getUser().getPreferredChannel());
        }
    }
}

class NotificationManager {
    private Notification[] notifications;
    private Channel channel;
    private int count;

    public NotificationManager(int capacity) {
        this.notifications = new Notification[capacity];
        this.channel = new Channel();
        this.count = 0;
    }

    public void addNotification(Notification notification) {
        if (notification.validateNotification() && count < notifications.length) {
            notifications[count] = notification;
            count++;
        }
    }

    public void processNotifications() {
        for (int priority = 5; priority >= 1; priority--) {
            for (int i = 0; i < count; i++) {
                if (notifications[i].getPriority() == priority) {
                    channel.send(notifications[i]);
                }
            }
        }
    }

    public void displayReport() {
        System.out.println();
        System.out.println("Notification Report:");

        for (int i = 0; i < count; i++) {
            Notification n = notifications[i];

            System.out.println();
            System.out.println("Notification ID: " + n.getNotificationId());
            System.out.println("User Name: " + n.getUser().getUserName());
            System.out.println("Preferred Channel: " + n.getUser().getPreferredChannel());
            System.out.println("Priority: " + n.getPriority());
            System.out.println("Status: " + n.getStatus());
        }
    }
}

public class SmartNotificationSystem {
    public static void main(String[] args) {

        User user1 = new User("USR-101", "Aarav", "Email");
        User user2 = new User("USR-102", "Riya", "SMS");

        Notification n1 = new Notification("NOT-101", user1, "Server down", 5);
        Notification n2 = new Notification("NOT-102", user2, "Daily report generated", 2);

        NotificationManager manager = new NotificationManager(10);

        manager.addNotification(n1);
        manager.addNotification(n2);

        manager.processNotifications();
        manager.displayReport();
    }
}