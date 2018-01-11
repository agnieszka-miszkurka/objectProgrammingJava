package mean;

import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(128);
    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMean(int cnt){
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        int block = 100000000/cnt;
        int start = 0;
        for (int i=0; i<cnt; i++){
            threads[i] = new MeanCalc(start, start+block);
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

        // oblicz średnią ze średnich
        double mean=0;
        for (int i=0; i<cnt; i++) {
            try {
                mean += results.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mean = mean/cnt;

        /*double mean = 0;
        for (MeanCalc mc : threads){
            mean += mc.mean;
        }
        mean = mean/cnt;*/
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    public static void main(String[] args) {
        /*initArray(100000000);
        MeanCalc meanCalc = new MeanCalc(0,2);
        meanCalc.start();
        parallelMean(10);*/
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMean(cnt);
        }
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            // ??? liczymy średnią
            for (int i=start; i<end; i++)
                mean += array[i];
            mean=mean/(end-start);
            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

}