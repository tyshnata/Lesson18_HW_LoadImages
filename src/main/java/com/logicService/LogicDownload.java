package com.logicService;

import com.userUtil.FileUtil;
import com.userUtil.PrintInfoForUser;
import com.downloadServis.CallableImageLoader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogicDownload {

    private String urlLine = "";
    private static Scanner scanner=new Scanner(System.in);

    /** Static method, shows the user possible options for actions and performs an action
     * that the user has chosen.*/
    public static void UserControl(){
        String choiceOfAction;

        while (true){
            PrintInfoForUser.printMenu();
            choiceOfAction=scanner.nextLine();
            switch (choiceOfAction){
                case "f":
                    linksFromFile();
                    break;
                case "c":
                    linksFromConsole();
                    break;
                case "q":
                    System.out.println("Пока.");
                    return;
                default:
                    System.out.println("Неправильно введен символ.");
                    break;
            }
        }
    }

    /** In the method we get an object linksFromFile of type List <String>
     *  by calling the method readFromFile() from com.userUtil.FileUtil for the given file with links.
     * For each link, an object of type C is created and 10 threads are started*/
    private static void linksFromFile(){
        File file=new File("./linksToPictures.txt");
        //        File file=new File("D:\\linksToPictures.txt");
        List<String> linksFromFile=FileUtil.readFromFile(file);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future> listFutures = new LinkedList<>();
        int i=0;
        for (String s:linksFromFile) {
            listFutures.add(executorService.submit(new CallableImageLoader(s)));
        }
        for (Future future: listFutures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }

    /** The method prompts the user for download links and writes them to the list
     *  and a message is displayed that this link is recorded.
     *  For each reference, an object of type C is created and the thread is started.*/
    private static void linksFromConsole(){
        String link="";
        int i=0;
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<String> linksFromConsole=new LinkedList<>();
        List<Future> listFutures = new LinkedList<>();

        System.out.println("Введите ссылки или ^1^ для выхода");
        while (!link.equals("1")){
            link=scanner.nextLine();
            if (!link.equals("1")){
                linksFromConsole.add(link);
                System.out.println(" эта ссылка записана");
                //scanner.next();
            }
        }
        for (String s:linksFromConsole) {
            listFutures.add(executorService.submit(new CallableImageLoader(s)));
        }
        for (Future future: listFutures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

    }
}
