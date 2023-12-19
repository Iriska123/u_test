package org.max.seminar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.max.seminar.provider.MailNews;
import org.max.seminar.provider.YandexNews;
import org.max.seminar.subscriber.SubscriberMember;

public class NewsTest {

    @ParameterizedTest
    @ValueSource(strings = {"Зима снежная", "Лето холодное"})
    void testSubscriberMember(String news) {
        MailNews mailNews = new MailNews();
        SubscriberMember subscriberMember = new SubscriberMember();

        mailNews.registerSubscriber(subscriberMember);
        mailNews.notifySubscribers(news);

        Assertions.assertEquals("Новость от Mail: " + news, subscriberMember.getNews());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Зима снежная", "Лето холодное"})
    void testSubscriberMemberTwoSubscriptions(String news) {
        MailNews mailNews = new MailNews();
        YandexNews yandexNews = new YandexNews();
        SubscriberMember subscriberMember = new SubscriberMember();

        mailNews.registerSubscriber(subscriberMember);
        mailNews.notifySubscribers(news);

        yandexNews.registerSubscriber(subscriberMember);
        yandexNews.notifySubscribers(news);

        Assertions.assertEquals("Новость от Yandex: " + news, subscriberMember.getNews());
    }

}
