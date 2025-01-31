import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Scheduler {
    private static PCB running;
    private static PCB ioCompleted;
    private static Queue<PCB> ready = new LinkedList<>();
    private static Queue<PCB> waiting = new LinkedList<>();

private static void addTotail(PCB pcb, Queue<PCB> queue){
	//predefine etc
}

private static PCB removeFromHead(Queue<PCB> queue){
	//predefine etc
	return running;
}

private static PCB findAndRemove(Queue<PCB> queue, PCB ioCompleted){
	//predefine etc
	return running;
}
private static void interruptsIsOn(){
	//predefine etc
}
private static void interruptsIsOff(){
	//predefine etc
}
private static void sleep(){
	//predefine etc
}
private static void swithcTo(PCB pcb){
	//predefine etc
}

public static void scheduler_2(int interruptType){
	if (interruptType == 0){
		System.out.println("Timer interrupt: TimerInterrupt");
			addTotail(running, ready);
	}
	else if (interruptType == 1){
		System.out.println("IO interrupt: IOSystemCall");
		addTotail(running, waiting);
	}
	else if (interruptType == 2){
		System.out.println("IO interrupt: IOInterrupt");
		if (running!= null){
			addTotail(running, ready);
		}
		running= findAndRemove(waiting, ioCompleted);
		addTotail(running, ready);
	}
	running= removeFromHead(ready);
	interruptsIsOn();
	if (running!= null){
		sleep();
	}
	else{
		swithcTo(running);
	}
 }

 public static void main(String[] args) {
    PCB myProcess = new PCB(123);
    Random random = new Random();
  	Timer timer = new Timer();

	myProcess.setProcessState("Running");
	System.out.println("Timer interrupt: Process state updated to " + myProcess.getProcessState());
   	 timer.schedule(new TimerTask() { // schedule next time
    @Override
    public void run() {
       	myProcess.setProcessState("Running");
        System.out.println("Timer interrupt: Process state updated to " + myProcess.getProcessState());
        timer.schedule(this, 1000); // schedule next time again
    	}
	}, 1000);

    running= myProcess;
    int rNumber = random.nextInt(3);
    System.out.println(rNumber);
    scheduler_2(rNumber);
    timer.cancel();  // Stop the timer thread
   }
}