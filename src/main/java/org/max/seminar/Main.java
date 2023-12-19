package org.max.seminar;

import org.max.seminar.provider.MailNews;
import org.max.seminar.provider.NewsPaper;
import org.max.seminar.provider.YandexNews;
import org.max.seminar.subscriber.Subscriber;
import org.max.seminar.subscriber.SubscriberImpl;
import org.max.seminar.subscriber.SubscriberMember;

public class Main {

    public static void main(String[] args) {
        NewsPaper yandex = new YandexNews();
        NewsPaper mail = new MailNews();

        SubscriberImpl subscriber1 = new SubscriberImpl("Иван");
        SubscriberImpl subscriberYandex = new SubscriberImpl("Николай");
        SubscriberImpl subscriberMail1 = new SubscriberImpl("Олег");
        SubscriberImpl subscriberMail2 = new SubscriberImpl("Оля");

        SubscriberMember subscriberMember = new SubscriberMember();

        yandex.registerSubscriber(subscriber1);
        mail.registerSubscriber(subscriber1);

        yandex.registerSubscriber(subscriberYandex);
        yandex.registerSubscriber(subscriberMember);

        mail.registerSubscriber(subscriberMail1);
        mail.registerSubscriber(subscriberMail2);

        yandex.notifySubscribers("Яндекс выпустил новые карты");
        mail.notifySubscribers("Идёт новый циклон");
        System.out.println(subscriberMember.getNews());

        mail.unsubscribe(subscriberMail2);

        mail.notifySubscribers("Циклон покинул наш регион");
        System.out.println(subscriberMember.getNews());

        yandex.notifySubscribers("Зима будет снежной");
        System.out.println(subscriberMember.getNews());
    }
}
