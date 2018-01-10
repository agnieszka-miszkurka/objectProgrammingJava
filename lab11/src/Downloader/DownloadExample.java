package Downloader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadExample {

    static AtomicInteger count = new AtomicInteger(0);
    static Semaphore sem = new Semaphore(0);
    static double t1 = 0;
    static double t2 = 0;

    // lista plików do pobrania
    static String [] toDownload = {
            "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
            "http://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
    };

    static class Downloader implements Runnable{
        private final String url;

        Downloader(String url){
            this.url = url;
        }

        public void run(){
            String fileName = url.substring(url.lastIndexOf("/")+1);

            try(InputStream in = new URL(url).openStream(); FileOutputStream out = new FileOutputStream(fileName) ){
                for(;;){
                    int c= in.read();
                    if (c<0)
                        break;
                    out.write(c);
                    // czytaj znak z in
                    // jeśli <0 break
                    //zapisz znak do out
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done:"+fileName);
            count.incrementAndGet();
            sem.release();
        }



    }

    static void sequentialDownload(){
        double t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            new Downloader(url).run();
        }
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"sequential time: t2-t1=%f\n",t2-t1);
    }


    static void concurrentDownload(){
        t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            // uruchom Downloader jako wątek...
            (new Thread(new Downloader(url))).start();

        }
        //double t2 = System.nanoTime()/1e6;
        //System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

    static void concurrentDownload2(){
        while(count.get()!=(toDownload.length)){
            // wait...
           // System.out.println(count.get());
            Thread.yield();
        }
        t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"concurrent1: t2-t1=%f\n",t2-t1);
        t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            // uruchom Downloader jako wątek...
            (new Thread(new Downloader(url))).start();

        }
        //double t2 = System.nanoTime()/1e6;
        //System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
    }

    static void concurrentDownload3() throws InterruptedException {
        sem.acquire(count.get()+2*toDownload.length);  // bo count ma warotsc bez thredow jeszcze
        t2 = System.nanoTime()/1e6;
        //System.out.printf(Locale.US,"concurrent2: t2-t1=%f\n",t2-t1);
        t1 = System.nanoTime()/1e6;
        for(String url:toDownload){
            // uruchom Downloader jako wątek...
            (new Thread(new Downloader(url))).start();
        }
        //czas concurrentDwonload3:
        System.out.println(count.get());
        sem.acquire(toDownload.length);
        t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"concurrent3: t2-t1=%f\n",t2-t1);
    }


    public static void main(String[] args) {
        DownloadExample.sequentialDownload();
        count.set(0);
        DownloadExample.concurrentDownload();
        DownloadExample.concurrentDownload2();
        try {
            DownloadExample.concurrentDownload3();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}