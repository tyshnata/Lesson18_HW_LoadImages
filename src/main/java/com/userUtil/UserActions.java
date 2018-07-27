package com.userUtil;

public enum UserActions {
    DOWNLOAD_LINK_FROM_FILE("f","Ссылки для загрузки находятся в файле. "),
    DOWNLOAD_LINK_FROM_CONSOLE("c","Ссылки для загрузки будут вводится с консоли. "),
    EXIT("q","Выход. ");

    String actionSymbol;
    String infoAboutAction;

    UserActions(String name, String infoAboutAction) {
        this.actionSymbol = name;
        this.infoAboutAction = infoAboutAction;
    }
}
