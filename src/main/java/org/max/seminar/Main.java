package org.max.seminar;

import org.max.seminar.provider.MailNews;
import org.max.seminar.provider.NewsPaper;
import org.max.seminar.provider.YandexNews;
import org.max.seminar.subscriber.Subscriber;
import org.max.seminar.subscriber.SubscriberImpl;

public class Main {

    public static void main(String[] args) {
        NewsPaper mail = new MailNews();
        NewsPaper yandex = new YandexNews();

        Subscriber yandexSubscriber1 = new SubscriberImpl("Yandex Subscriber Number 1");
        Subscriber yandexSubscriber2 = new SubscriberImpl("Yandex Subscriber Number 2");

        Subscriber mailSubscriber1 = new SubscriberImpl("Mail Subscriber Number 1");
        Subscriber mailSubscriber2 = new SubscriberImpl("Mail Subscriber Number 2");

        Subscriber subscriber = new SubscriberImpl("Yandex and Mail subscriber");

        mail.registerSubscriber(subscriber);
        mail.registerSubscriber(mailSubscriber1);
        mail.registerSubscriber(mailSubscriber2);

        yandex.registerSubscriber(subscriber);
        yandex.registerSubscriber(yandexSubscriber1);
        yandex.registerSubscriber(yandexSubscriber2);

        mail.notifySubscribers("Надвигается плохая погода.");
        yandex.notifySubscribers("Яндекс выпустил новые карты.");
        System.out.println("\n");

        mail.unsubscribe(mailSubscriber2);
        yandex.unsubscribe(yandexSubscriber1);

        mail.notifySubscribers("Надвигается хорошая погода.");
        yandex.notifySubscribers("Яндекс обновил карты снова.");
        System.out.println("\n");

        mail.registerSubscriber(yandexSubscriber2);

        mail.notifySubscribers("Надвигается ну очень хорошая погода.");
    }

}
