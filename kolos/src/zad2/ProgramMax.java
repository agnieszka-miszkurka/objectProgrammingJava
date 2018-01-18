package zad2;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProgramMax {
    static int[] array;
    static BlockingQueue<Integer> results = new ArrayBlockingQueue<Integer>(128);
    static void initArray(int size){
        array = new int[size];
        Random r =new Random();
        for(int i=0;i<size;i++){
            array[i] = r.nextInt(100);
        }
    }


    static void parallelMax(int cnt){
        // utwórz tablicę wątków
        MaxCalc threads[]=new MaxCalc[cnt];

        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        int block = 100/cnt;
        int start = 0;
        for (int i=0; i<cnt; i++){
            threads[i] = new MaxCalc(start, start+block);
            start+=block;
        }
        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime()/1e6;
        //uruchom wątki
        for (int i=0; i<cnt; i++)
            threads[i].start();
        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''

        /*for(MeanCalc mc:threads) {
            try {
                mc.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        // oblicz max z max
        int max = 0;
        int next_max=0;
        for (int i=0; i<cnt; i++) {
            try {
                next_max = results.take();
                if (max < next_max)
                    max = next_max;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*double mean = 0;
        for (MeanCalc mc : threads){
            mean += mc.mean;
        }
        mean = mean/cnt;*/
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f max=%d\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                max);
    }

    public static void main(String[] args) {
        initArray(100);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMax(cnt);
        }
    }

    static class MaxCalc extends Thread{
        private final int start;
        private final int end;

        MaxCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            //  liczymy max
            int max = array[start];
            for (int i=start+1; i<end; i++) {
                if (max < array[i]){
                    max = array[i];
                }
            }
            try {
                results.put(max);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }
}
