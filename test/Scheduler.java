import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Scheduler {

  /**
   * The different interrupt types that lead to calling the scheduler.
   */
  enum InterruptType {
    TIMER_INTERRUPT, IO_SYSTEM_CALL, IO_COMPLETED_INTERRUPT
  }

  /**
   * The Process Control Block (PCB). To have all in one file, an inner class is
   * used here.
   */
  public class PCB {
    private int processID;
    private int programCounter;
    // ... other PCB fields

    public PCB(int processID) {
      this.processID = processID;
      this.programCounter = 0;
      // ... initialize other fields
    }

    public int getProcessId() {
      return processID;
    }

    public int getProgramCounter() {
      return programCounter;
    }

    public void setProgramCounter(int programCounter) {
      this.programCounter = programCounter;
    }

    // ... PCB methods for other fields

  }

  /**
   * The PCB of the currently running process.
   */
  protected PCB running;

  /**
   * The queue of PCBs of ready processes.
   */
  protected Queue<PCB> ready = new LinkedList<>();

  /**
   * The queue of PCBs of blocked/waiting processes. (For simplicity, only one
   * kind of I/O device and thus only one waiting queue is assumed.)
   */
  protected Queue<PCB> waiting = new LinkedList<>();

  /**
   * The interrupt processing state.
   * 
   * @value true interrupt processing is enabled
   * @value false interrupt processing is disabled
   */
  protected boolean interruptsEnabled = false;

  /**
   * Appends a PCB to the tail of a PCB queue.
   * 
   * @param pcb   the PCB to be appended
   * @param queue the PCB queue to append to
   */
  protected void addToTail(PCB pcb, Queue<PCB> queue) {
    queue.add(pcb);
  }

  /**
   * Removes the element at the head of a queue and returns this element. If the
   * queue is empty, null is returned.
   * 
   * @return the PCB removed from the queue or null if this queue is empty
   */
  protected PCB removeFromHead(Queue<PCB> queue) {
    return queue.poll();
  }

  /**
   * Searches for an PCB entry with a PId in a PCB queue and removes the found PCB
   * entry from the PCB queue.
   * 
   * @param pid   the PId to be searched for
   * @param queue the PCB queue to be searched
   * @return the found PCB
   * @NoSuchElementException - if this PId is not found in queue
   */
  protected PCB findAndRemove(int pid, Queue<PCB> queue) {
    for (PCB pcb : queue) {
      if (pcb.getProcessId() == pid) {
        queue.remove(pcb);
        return pcb;
      }
    }
    throw new NoSuchElementException(); // This should never happen
  }

  /**
   * Disables all interrupts. (When entering the function schedule, all interrupts
   * get disabled to prevent that the scheduler itself gets interrupted.)
   */
  protected void switchInterruptsOff() {
    interruptsEnabled = false;
    // We cannot actually mess around with interrupts in Java,
    // so let's just assume that this method switches off interrupts.
  }

  /**
   * Enables all interrupts again. (Before entering the function schedule, the
   * kernel disabled all interrupts to prevent that the scheduler itself gets
   * interrupted.) Do not forget to enable interrupts again, otherwise the
   * scheduler will never get activated again in future by a timer or I/O
   * interrupt.
   */
  protected void switchInterruptsOn() {
    interruptsEnabled = true;
    // We cannot actually mess around with interrupts in Java,
    // so let's just assume that this method switches on interrupts.
  }

  /**
   * Puts the CPU into sleep mode, only an interrupt will wake up the CPU.
   */
  protected void sleep() {
    // We cannot put a CPU to sleep with Java,
    // but let's assume that we need to call this method to put the CPU to sleep.
  }

  /**
   * Restarts the timer of the time slice expiration interrupt and switches to the
   * context stored in PCB pcb. This method does not return to the caller, but
   * directly switches to the process specified by its PCB by jumping to the
   * program counter location stored in the PCB, i.e. any line following the call
   * to switchTo will never get executed.
   * 
   * @param pcb the PCB of the process to be switched to
   */
  protected void switchTo(PCB pcb) {
    pcb.getProgramCounter();
    // We cannot switch to a programm counter in Java,
    // but let's assume that we need to call this method to achieve this.
  }

  //---------------------- Add code below --------------------------------
  
  /**
   * Gets called if the timer interrupt occurred, i.e. the time slice of current
   * process expired.
   */
  protected void handleTimerInterrupt() {
    // TODO: Implement this method
  }

  /**
   * Gets called if the current process made a system call that leads to I/O.
   */
  protected void handleSystemCallLeadingToIO() {
    // TODO: Implement this method
  }

  /**
   * Gets called if the I/O of a process has been completed.
   * 
   * @param pIdOfProcessWithCompletedIO the PId of the process whose I/O has just
   *                                    completed
   */
  protected void handleIOcompletedInterrupt(int pIdOfProcessWithCompletedIO) {
    // TODO: Implement this method
  }

  /**
   * Gets called at the end of any type of interrupt handling by the scheduler.
   * Used for code that is common to all interrupt handling.
   */
  protected void handlingCommonCode() {
    // TODO: Implement this method
  }

  //---------------------- Add code above --------------------------------
  
  /**
   * Schedule processes based on the interrupt type that occurred. Involves
   * switching processes and CPU sleep as needed. (A real scheduler that is
   * implemented inside the OS kernel would not return if processed are switched
   * or if the CPU sleeps. Because we are not really the kernel here in Java, this
   * method returns however even in these cases.)
   * 
   * @param interruptType  the interrupt type that occurred
   * @param iOcompletedPId PId of process whose I/O completed and therefore raised
   *                       an interrupt. Only relevant if {@link InterruptType} is
   *                       IO_COMPLETED_INTERRUPT
   */
  public void schedule(InterruptType interruptType, int iOcompletedPId) {
    switchInterruptsOff();
    if (interruptType == InterruptType.TIMER_INTERRUPT) {
      handleTimerInterrupt();
    } else if (interruptType == InterruptType.IO_SYSTEM_CALL) {
      handleSystemCallLeadingToIO();
    } else if (interruptType == InterruptType.IO_COMPLETED_INTERRUPT) {
      handleIOcompletedInterrupt(iOcompletedPId);
    }
    handlingCommonCode();
  }

  /**
   * Print status of queues and running process
   */
  public void debug() {
    if (running != null) {
      System.out.println("Running: " + running.getProcessId());
    } else {
      System.out.println("Running: " + running);
    }
    System.out.print("Ready:");
    for (PCB pcb : ready) {
      System.out.print(" " + pcb.getProcessId());
    }
    System.out.println();

    System.out.print("Waiting:");
    for (PCB pcb : waiting) {
      System.out.print(" " + pcb.getProcessId());
    }
    System.out.println();

  }

  /**
   * Execute main method: you are allowed to modify, e.g. add assert (needs JVM
   * parameter "-ea") or to add scheduler.debug() or to change the
   * scheduler.schedule calls.
   */
  public static void main(String[] args) {

    Scheduler scheduler = new Scheduler();
    assert scheduler.interruptsEnabled == false : "Interrupts are not disabled"; // If you want to have these assertions
                                                                                 // evaluated, need to use the "-ea" JVM
                                                                                 // parameter!

    PCB process1 = scheduler.new PCB(1); // As PCB is an inner class, its constructor can only be called using a
                                         // reference to an object of type Scheduler.
    PCB process2 = scheduler.new PCB(2);
    PCB process5 = scheduler.new PCB(5);
    PCB process8 = scheduler.new PCB(8);

    scheduler.addToTail(process1, scheduler.ready);
    assert scheduler.ready.peek() == process1 : "Wrong process at front of queue";

    scheduler.addToTail(process2, scheduler.ready);
    scheduler.addToTail(process5, scheduler.ready);
    assert Arrays.equals(scheduler.ready.toArray(), new PCB[] { process1, process2, process5 })
        : "Ready queue has wrong contents";

    scheduler.running = process8; // PId 8 should now be running
    assert scheduler.running == process8 : "Wrong process running";
    assert Arrays.equals(scheduler.waiting.toArray(), new PCB[] {}) : "Waiting queue has wrong contents";

    scheduler.switchInterruptsOn();
    assert scheduler.interruptsEnabled == true : "Interrupts are not enabled";

    scheduler.debug(); // Print status

    // Now, everything is set up and we can let the scheduler shuffle processes
    // around based on the different interrupt types

    scheduler.schedule(InterruptType.TIMER_INTERRUPT, -1); // Timeslice of currently running process expired

    scheduler.schedule(InterruptType.TIMER_INTERRUPT, -1); // Timeslice of currently running process expired

    scheduler.schedule(InterruptType.IO_SYSTEM_CALL, -1); // Currently running process made an I/O system call

    scheduler.schedule(InterruptType.TIMER_INTERRUPT, -1); // Timeslice of currently running process expired

    scheduler.schedule(InterruptType.IO_COMPLETED_INTERRUPT, 2); // I/O of PId 2 has been completed

    scheduler.schedule(InterruptType.IO_SYSTEM_CALL, -1); // Currently running process made an I/O system call
    scheduler.schedule(InterruptType.IO_SYSTEM_CALL, -1); // Currently running process made an I/O system call
    scheduler.schedule(InterruptType.IO_SYSTEM_CALL, -1); // Currently running process made an I/O system call
    scheduler.schedule(InterruptType.IO_SYSTEM_CALL, -1); // Currently running process made an I/O system call

    scheduler.debug(); // Print status

  }

}