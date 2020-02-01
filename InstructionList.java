package aloizi04.hw1;

/**
 * Represents a list of many Instructor objects
 */
public class InstructionList
{
	private Instruction[] instructions;
	private final int DEFAULT_LIST_SIZE = 50;
	private int loadedInstructions;

	/**
	 * constructs an InstructionList with 0 loaded instructions and an instructions array (of type Instruction) of size 50
	 */
	public InstructionList() {
		instructions = new Instruction[DEFAULT_LIST_SIZE];
		loadedInstructions=0;

	}

	/**
	 * Increases the instructions's capacity by one.
	 */
	private void makeListBigger(){
		//If for some reason we need to load more than 50 instructions
		//We copy the current array to a new one which has as length, the original + 1
		Instruction[] temp = instructions;
		instructions = new Instruction[temp.length+1];
		int c=0;
		for (Instruction instruction: temp)
			instructions[c++]= new Instruction(instruction);
	}



	/**
	 * adds a given command to the list
	 * @param newCommand
	 * command to be added
	 * @param arguments
	 * the command's argument
	 * @return
	 * A message depending if the addition was successful or not.
	 */
	public String addCommand(String newCommand, String arguments)
	{
		if (arguments.startsWith("."))
		{
			return "Cannot modify a file that starts with . as this is a critical file!";
		}

		if(loadedInstructions==instructions.length)
			makeListBigger();
		instructions[loadedInstructions++] = new Instruction(newCommand, arguments);
		return "Successfully added.";
	}

	//This checks to see if there are any instructions left to be executed. In the original version
	//this can be done by looking to see if there are any ; in the String savedCommands

	/**
	 * Checks if there are any instructions left to be executed.
	 * @return
	 * whether the loaded instructions are more than zero
	 */
	public boolean hasNext()
	{
		return loadedInstructions>0;
	}

	//This method should return the next instruction that is available.
	//Currently it does this by looking for the first ; in the string savedCommands.
	//It then returns the String savedCommands up until the first ; and updates
	//saved commands to remove the first command up until the ; so that the command does
	//not get executed multiple times.

	/**
	 * Finds the next instruction to be executed
	 *
	 * precondition:
	 * hasNext() is true
	 *
	 *
	 * @return
	 * the next instruction to be executed in String form (as described in the Instruction class)
	 */
	public String getNextInstruction()
	{
		Instruction next = new Instruction(instructions[0]); //Get the next instruction (first in the array)
		//the other instructions (if any) need to be shifted one to the left
		//so that the next instruction is again the first in the array
		for(int c=0; c<loadedInstructions-1; c++)
			instructions[c]= new Instruction(instructions[c+1]);
		loadedInstructions--;
		return next.toString();
	}


	//This method returns a String[] of all the instructions left to be executed.
	//split does this very easily in the initial case.

	/**
	 *
	 * @return
	 * a String array of the instructions left to be executed
	 */
	public String[] getLoadedInstructions()
	{
		if(loadedInstructions==0)	//return an empty array if no instructions are loaded
			return new String[]{""};
		String[] stringInstructions = new String[loadedInstructions];
		for (int c=0; c<loadedInstructions; c++)
			stringInstructions[c]=instructions[c].toString();
		return stringInstructions;
	}
}