package org.max.seminar.provider;

public class YandexNews extends AbstractNews {

    public void notifySubscribers(String news) {
        super.notifySubscribers("Новость от Yandex: " + news);
    }
}
