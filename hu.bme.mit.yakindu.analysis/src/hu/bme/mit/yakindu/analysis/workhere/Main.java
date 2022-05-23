package hu.bme.mit.yakindu.analysis.workhere;

import java.util.Scanner;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.base.types.Direction;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;
import org.yakindu.sct.model.stext.stext.EventDefinition;
import org.yakindu.sct.model.stext.stext.VariableDefinition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.RuntimeService;
import hu.bme.mit.yakindu.analysis.TimerService;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		String stmTypeName = s.getName().substring(0, 1).toUpperCase() + s.getName().substring(1) + "statemachine";
		StringBuilder printfunction = new StringBuilder("public static void print(I" + stmTypeName + "s) {\n");
		StringBuilder mainFunction = new StringBuilder("public static void main(String[] args) throws IOException { \n");
		mainFunction.append("\t" + stmTypeName + " s = new " + stmTypeName +"();\r\n");
		mainFunction.append("\ts.setTimer(new TimerService());\r\n")
					.append("\tRuntimeService.getInstance().registerStatemachine(s, 200);\r\n")
					.append("\ts.init();\r\n")
					.append("\ts.enter();\r\n")
					.append("\tScanner scanner = new Scanner(System.in);\r\n")
					.append("\tboolean running = true; \r\n")
					.append("\twhile(running) {\r\n")
					.append("\t\tString next = scanner.next();\r\n")
					.append("\t\tif(next.equals(\"exit\")) running = false;\r\n");

		//System.out.println("public static void print(IExampleStatemachines) {");
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			/*if(content instanceof State) {
				State state = (State) content;
				if(state.getOutgoingTransitions().isEmpty()) {
					System.out.println("Csapda: ");
				}
				System.out.println(state.getName());
				if(state.getName().isEmpty()) {
					System.out.println("No name found! Suggestion: " + state.getNamespace() + "_1");
				}
			} else if(content instanceof Transition){
				Transition transition = (Transition) content;
				System.out.println("Tranzíció: " + transition.getSource().getName() + 
				" -> " + transition.getTarget().getName());
			}*/
			if(content instanceof VariableDefinition) {
				VariableDefinition variableDefinition = (VariableDefinition) content;
				printfunction.append(String.format("\tSystem.out.println(\"%s = \" + s.getSCInterface().get%s());", 
						variableDefinition.getName().toUpperCase().charAt(0), variableDefinition.getName()));
 			} else if(content instanceof EventDefinition) {
 				EventDefinition eventDefinition = (EventDefinition) content;
 				if(eventDefinition.getDirection() == Direction.IN) {
 					mainFunction.append("\t\tif(next.equals(\""+ eventDefinition.getName() + "\")) s.raise" 
 				+ eventDefinition.getName().substring(0, 1).toUpperCase() + eventDefinition.getName().substring(1) + "();\n");
 				}
 			}
		}
		mainFunction.append("\t\ts.runCycle();\n");
		mainFunction.append("\t\t}\n");
		printfunction.append("\n}");
		mainFunction.append("\tscanner.close();\n")
		.append("\tSystem.exit(0)\n}\n");
		System.out.println(mainFunction.toString());
		System.out.println(printfunction.toString());
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}
