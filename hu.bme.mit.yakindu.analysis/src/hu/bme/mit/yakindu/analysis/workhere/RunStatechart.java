package hu.bme.mit.yakindu.analysis.workhere;

import java.io.IOException;
import java.util.Scanner;

import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.example.ExampleStatemachine;
import hu.bme.mit.yakindu.analysis.example.IExampleStatemachine;





public class RunStatechart {
	
	public static void main(String[] args) throws IOException {
		ExampleStatemachine s = new ExampleStatemachine();
		s.setTimer(new TimerService());
		RuntimeService.getInstance().registerStatemachine(s, 200);
		s.init();
		s.enter();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		while(running) {
			String next = scanner.next();
			if(next.equals("start")) {
				s.raiseStart();
			} else if(next.equals("white")) {
				s.raiseWhite();
			} else if(next.equals("black")) {
				s.raiseBlack();
			} else if(next.equals("exit")) {
				running = false;
			}
			s.runCycle();
			System.out.println("White time: " + s.getSCInterface().getWhiteTime() + " - Black time: " + s.getSCInterface().getBlackTime());
		}
		scanner.close();
		System.exit(0);
	}

	public static void print(IExampleStatemachine s) {
		System.out.println("W = " + s.getSCInterface().getWhiteTime());
		System.out.println("B = " + s.getSCInterface().getBlackTime());
	}
}
