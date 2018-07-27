package com.downloadServis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Download {

    private String urlLine = null;

    public String getUrlLine() {
        return urlLine;
    }

    public void setUrlLine(String urlLine) throws EmptyURLException {
        if (urlLine == null || urlLine.length() == 0) {
            throw new EmptyURLException("Ссылка на изображение пуста.");
        }
        this.urlLine = urlLine;
    }

    /** The method downloads an image from the specified link
     * and stores it in the directory of images, choosing the image
     * name from the link after the last character  '/' */
    public void loading() throws IOException {
        URL url = new URL(urlLine);
        int begin = urlLine.lastIndexOf("/");
        while (begin == -1) {
            System.out.println("Неверно указан путь. ");
        }
        String name = urlLine.substring(begin + 1, urlLine.length());
        Path path2 = Paths.get("./images");
        //        Path path2 = Paths.get("D:\\images");
        if (!Files.exists(path2)) {
            Files.createDirectories(path2);
        }
        Path downloadPath = Paths.get("./images\\" + name);
        //        Path downloadPath = Paths.get("D:\\images\\" + name);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(downloadPath.toFile());
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}




