// Any modifications to this file will be ignored.

public class Assignment11 {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("One command line parameter only: must be an interger (or long) number");
		} else {
			long iterationsPerThread = Long.parseLong(args[0]);

			// Call to student's class:
			// takes number of iterations per thread as input
			// returns result of two threads each incrementing a shared
			// variable "in" (initialised with 0) "iterationsPerThread" times.
			long result = MyAssignment11.main(iterationsPerThread);

			System.out.println(result);
		}
	}
}
