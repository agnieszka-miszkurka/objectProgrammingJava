package powtorka;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloThread {
    public static Semaphore semaphore = new Semaphore(0);
    static AtomicInteger i = new AtomicInteger(0);

    static class ReverseThread implements Runnable {
        String name;
        int id;

        public ReverseThread(int id) {
            this.id = id;
            i.set(i.get()+1);
            semaphore.release();
        }

        @Override
        public void run() {


                if (id < 10) {
                    Thread o = new Thread(new ReverseThread(id + 1));
                    o.start();

                    try {
                        o.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Hello from thread number" + id);
                }



        }
    }

        public static void main(String[] args) {
            new Thread((new ReverseThread(0))).start();
        }
    }

