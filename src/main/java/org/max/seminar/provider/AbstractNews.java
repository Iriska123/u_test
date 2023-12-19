package org.max.seminar.provider;

import org.max.seminar.subscriber.Subscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNews implements NewsPaper {
    List<Subscriber> subscriberList = new ArrayList<>();

    @Override
    public void registerSubscriber(Subscriber subscriber) {
        subscriberList.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscriberList.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String news) {
        for (Subscriber subscriber : subscriberList) {
            subscriber.updateSubscriber(news);
        }
    }
}
