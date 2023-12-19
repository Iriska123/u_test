package org.max.seminar.subscriber;

public class SubscriberImpl implements Subscriber {

    private final String name;

    public SubscriberImpl(String name) {
        this.name = name;
    }

    @Override
    public void updateSubscriber(String news) {
        System.out.println("Подпсичик " + name + " узнал новость. " + news);
    }
}
