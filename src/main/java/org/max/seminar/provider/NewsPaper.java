package org.max.seminar.provider;

import org.max.seminar.subscriber.Subscriber;

public interface NewsPaper {

    void registerSubscriber(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String news);

}
