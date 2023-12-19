package org.max.seminar.provider;

public class MailNews extends AbstractNews {

    public void notifySubscribers(String news) {
        super.notifySubscribers("Новость от Mail: " + news);
    }
}
