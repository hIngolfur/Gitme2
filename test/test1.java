public class Scheduler {

private static PCB running;
private static Queue <PCB> ready = LinkedList <>();
private static Queue <PCB> waiting = LinkedList <>();
private static int ioCompleted;


private static void adddTotail(PCB pcb, Queue<PCB> queue){

	//predefine etc
}
private static void removeFromHead(Queue<PCB> queue){

	//predefine etc
}
private static void findAndRemove(int PID, Queue<PCB> queue){

	//predefine etc
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


public static void scheduler(int interruptType){
	if (interruptType == TimerInterrupt, TYPE){
		if (ready.isEmpty()){
			addTotail(running,waiting);
			running= removedFromHead(ready)
			swithcTo(running);
		}
	}
	else if (interruptType == IOSystemCall.TYPE){

		addToTail(running, waiting);
		interruptsOn();
		sleep()
		if (ioCompleted!= 0){
			running= findAndRemove(iocmpleted, waiting);
			ioCompleted= 0;
			switchTo(running);
		}
	}
	else if (interruptType == IOInterrupt.TYPE){
		running= FindAndRemove(ioCompleted, waiting);
		ioCompleted= 0;
		swithcTo(running);
	}
	interruptOn()
}