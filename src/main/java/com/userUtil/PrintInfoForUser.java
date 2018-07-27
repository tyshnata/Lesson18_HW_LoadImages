package com.userUtil;

public class PrintInfoForUser {
    /** The method outputs information about possible actions
     * and symbols to the console for selecting an action for the user.*/
    public static void printMenu(){
        for (UserActions userActions: UserActions.values()) {
            System.out.println(userActions.infoAboutAction+ "Введите символ: " + userActions.actionSymbol);
        }


    }
}
