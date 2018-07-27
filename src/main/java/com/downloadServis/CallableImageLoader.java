package com.downloadServis;

import java.util.concurrent.Callable;

public class CallableImageLoader implements Callable<String> {
    private String url;

    public CallableImageLoader(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        Download loader = new Download();
        loader.setUrlLine(url);
        loader.loading();
        System.out.println("Запущен поток "+Thread.currentThread().getName());
        return "Поток "+ Thread.currentThread().getName() +". Изображение загружено";
    }

}
