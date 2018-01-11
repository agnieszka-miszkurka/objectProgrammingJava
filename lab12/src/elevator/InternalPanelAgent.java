package elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {
    static class InternalCall{
        private final int toFloor;
        InternalCall(int toFloor){
            this.toFloor = toFloor;
        }
    }

    InternalPanelAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    ElevatorCar elevatorCar;

    public void run(){
        for(;;){
            // odczytaj wezwanie z kolejki
            // w zależności od aktualnego piętra, na którym jest winda,
            // umieść przystanek w odpowiedniej tablicy ''EleveatorStops''
            InternalCall internalCall = null;
            try {
                internalCall = input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (internalCall != null) {
                ElevatorStops elevatorStops = ElevatorStops.get();
                if(internalCall.toFloor==elevatorCar.getFloor())continue;
                // dodajemy do jednej z tablic zgłoszeń
                if(internalCall.toFloor>elevatorCar.getFloor()){
                    ElevatorStops.get().setLiftStopUp(internalCall.toFloor);
                }else{
                    ElevatorStops.get().setLiftStopDown(internalCall.toFloor);
                }
            }
        }
    }

}