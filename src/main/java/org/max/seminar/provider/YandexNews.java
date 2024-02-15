package org.max.seminar.provider;

public class YandexNews extends AbstractPaperImpl {

    @Override
    public void notifySubscribers(String news) {
        super.notifySubscribers("Новость от Yandex: " + news);
    }

}
