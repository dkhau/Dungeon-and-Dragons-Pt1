package src;

import dnd.die.D20;
import dnd.exceptions.UnusualShapeException;
import dnd.exceptions.NotProtectedException;
import dnd.models.Treasure;
import dnd.models.ChamberShape;
import dnd.models.ChamberContents;
import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
* @author Derek Khau
*/
public class DndMain {

    /**
     * instance variable to make object for chamber shape class.
     */
    private static ChamberShape cs = new ChamberShape();
    /**
     * instance variable to make object for chamber content class.
     */
    private static ChamberContents cc = new ChamberContents();
    /**
     * instance variable to make object for exits class.
     */
    private static Exit exit = new Exit();
    /**
     * instance variable to make object for monster class.
     */
    private static Monster monster = new Monster();
    /**
     * instance variable to make object for stairs class.
     */
    private static Stairs stairs = new Stairs();
    /**
     * instance variable trap object for trap class.
     */
    private static Trap trap = new Trap();
    /**
     * instance variable treasure object for treasure class.
     */
    private static Treasure treasure = new Treasure();
    /**
     * instance variable for the array size to store length, width and area of room.
     */
    static final int ARRAY_SIZE = 3;

    /**
     * max choice for user when choosing size, shape, stairs and trap
     */
    static final int MAX_20 = 20;

    /**
     * max choice for user when choosing monster and/or treasure
     */
    static final int MAX_99 = 99;

    /**
     * calls function to generate the shape, size and number of exits in chamber.
     * @param array used to get the length, width and area
     */
    public void shapeAndSizeOfChamber(int[] array) {
        int userChoice = 0;
        int length = 0;
        int width = 0;
        int area = 0;
        int numExits = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like a random or specific shape for your chamber?");
        System.out.println("1 for random\n2 for specific");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        getShapeAndArea(userChoice, array);
        getExit();
    }

    /**
     * gets the number of exits.
     */
    public void getExit() {
        int numExits = 0;
        int i = 0;

        cs.setNumExits();
        numExits = cs.getNumExits();
    }

    /**
     * generates shape and size of chamber.
     * @param userChoice used to pick random or specific shape
     * @param array      used to get the length, width and area
     */
    public void getShapeAndArea(int userChoice, int[] array) {
        int length = 0;
        int width = 0;
        int area = 0;
        Scanner sc = new Scanner(System.in);
        if (userChoice == 1) {
            cs.setShape();
        } else if (userChoice == 2) {
            System.out.print("Select a shape (1-20): ");
            userChoice = sc.nextInt();
            userChoice = choiceError2(userChoice);
            cs.setShape(userChoice);
        }

        try {
            length = cs.getLength();
            width = cs.getWidth();
        } catch (UnusualShapeException e) {

        }

        area = cs.getArea();
        array[0] = length;
        array[1] = width;
        array[2] = area;
    }

    /**
     * creates all the contents in the chamber.
     */
    public void createContent() {
        int userChoice = 0;
        int maxMonster = 0;
        int minMonster = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like a random or specific content in your chamber?");
        System.out.println("1 for random\n2 for specific");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        if (userChoice == 1) {
            cc.setDescription();
        } else if (userChoice == 2) {
            System.out.print("Select content (1-20): ");
            userChoice = sc.nextInt();
            userChoice = choiceError2(userChoice);
            cc.setDescription(userChoice);
        }

        if (cc.getDescription().equals("monster only")) {
            getMonster();
        } else if (cc.getDescription().equals("monster and treasure")) {
            getMonster();
            chooseTreasure();
        } else if (cc.getDescription().equals("stairs")) {
            selectStairs();
        } else if (cc.getDescription().equals("trap")) {
            selectTraps();
        } else if (cc.getDescription().equals("treasure")) {
            chooseTreasure();
        }
    }

    /**
     * generates random or specific treasure and container.
     */
    public void chooseTreasure() {
        int userChoice = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like a random or specific treasure?");
        System.out.println("1 for random\n2 for specific treasure");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        if (userChoice == 1) {
            treasure.setDescription();
        } else if (userChoice == 2) {
            System.out.print("If you choose 100 please enter it as '00'");
            System.out.print("\nSelect a treasure (1-100): ");
            userChoice = sc.nextInt();
            userChoice = choiceError3(userChoice);
            treasure.setDescription(userChoice);
        }

        System.out.println("\nWould you like a random or specific container?");
        System.out.println("1 for random\n2 for specific");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        if (userChoice == 1) {
            treasure.setContainer();
        } else if (userChoice == 2) {
            System.out.print("Select a container (1-20): ");
            userChoice = sc.nextInt();
            userChoice = choiceError2(userChoice);
            treasure.setContainer(userChoice);
        }
}
    /**
     * generates random or specific monster.
     */
    public void getMonster() {
        int maxMonster = 0;
        int minMonster = 0;
        int userChoice = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like to choose a random or specific monster?");
        System.out.println("1 for random\n2 for specific");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        maxMonster = monster.getMaxNum();
        minMonster = monster.getMinNum();

        if (userChoice == 1) {
            monster.setType();
        } else if (userChoice == 2) {
            System.out.print("Select a monster: ");
            userChoice = sc.nextInt();
            monster.setType(userChoice);
        }
    }

    /**
     * generates random or specific stairs.
     */
    public void selectStairs() {
        int userChoice;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like a random or specific staircase?");
        System.out.println("1 for random\n 2 for specific");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        if (userChoice == 1) {
            stairs.setType();
        } else if (userChoice == 2) {
            System.out.print("Select a staircase (1-20): ");
            userChoice = sc.nextInt();
            userChoice = choiceError2(userChoice);
            stairs.setType(userChoice);
        }
    }

    /**
     * generates random or specific trap.
     */
    public void selectTraps() {
        int userChoice = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("\nWould you like a random or specific trap?");
        System.out.println("1 for random\n 2 for specific");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = choiceError(userChoice);

        if (userChoice == 1) {
            trap.setDescription();
        } else if (userChoice == 2) {
            System.out.print("Select a trap (1-20): ");
            userChoice = sc.nextInt();
            userChoice = choiceError2(userChoice);
            trap.setDescription(userChoice);
        }
    }

    /**
     * completely randomizes chamber.
     */
    public void randomizeChamber() {

        int length = 0;
        int width = 0;
        int area = 0;

        cs.setShape();
        try {
            length = cs.getLength();
            width = cs.getWidth();
        } catch (UnusualShapeException e) {

        }
        area = cs.getArea();
        cs.setNumExits();

        cc.setDescription();
        if (cc.getDescription().equals("monster only")) {
            monster.setType();
        } else if (cc.getDescription().equals("monster and treasure")) {
            monster.setType();
            treasure.setDescription();
            treasure.setContainer();
            try {
            treasure.getProtection();
            } catch (NotProtectedException e) {

            }
        } else if (cc.getDescription().equals("stairs")) {
            stairs.setType();
        } else if (cc.getDescription().equals("trap")) {
            trap.setDescription();
        } else if (cc.getDescription().equals("treasure")) {
            treasure.setDescription();
            treasure.setContainer();
            try {
            treasure.getProtection();
            } catch (NotProtectedException e) {

            }
        }

        printStory(length, width, area);
    }

    /**
     * prints the story depending on what user chooses.
     * @param length length of room
     * @param width  width of room
     * @param area   area of room
     */
    public void printStory(int length, int width, int area) {
        int i = 0;

        System.out.print("\nThe shape of the chamber is a(n) " + cs.getShape());

        if (length == 0 && width == 0) {
            System.out.print(" with an area of " + area + " sq. ft.");
        } else {
            System.out.print(". It's dimensions are " + length + "x" + width
                            + " and has an area of " + area + " sq. ft. ");
        }

        System.out.print("The room has " + cs.getNumExits() + " exit(s).\n");

        for (i = 0; i < cs.getNumExits(); i++) {
            System.out.println("Exit " + (i + 1) + ". is " + cs.getExits().get(i).getDirection() + "."
                                + " and is located on the " + cs.getExits().get(i).getLocation() + ".");
        }

        if (cc.getDescription().equals("empty")) {
            System.out.println("This room is empty.");
        } else if (cc.getDescription().equals("monster only")) {
            System.out.println("This room contains " + monster.getMinNum() + "-" + monster.getMaxNum()
                               + " " + monster.getDescription() + ".");
        } else if (cc.getDescription().equals("monster and treasure")) {
            System.out.println("This room contains " + monster.getMinNum() + "-" + monster.getMaxNum()
                               + " " + monster.getDescription() + " and has " + treasure.getDescription()
                               + " in the treasure chest. You place it in a " + treasure.getContainer() + ".");
            try {
            System.out.println("It is protected with " + treasure.getProtection() + ".");
            } catch (NotProtectedException e) {
                System.out.println("The treasure is not guarded.");
            }
        } else if (cc.getDescription().equals("stairs")) {
            System.out.println("This room contains stairs that lead to " + stairs.getDescription() + ".");
        } else if (cc.getDescription().equals("trap")) {
            System.out.println("You run into " + trap.getDescription());
        } else if (cc.getDescription().equals("treasure")) {
            System.out.println("You see a treasure chest in the room that contains " + treasure.getDescription() + "."
                                + " You place it in a " + treasure.getContainer() + ".");
            try {
            System.out.println("The treasure is protected with " + treasure.getProtection() + ".");
            } catch (NotProtectedException e) {
                System.out.println("The treasure is not guarded.");
            }
        }
    }

    /**
     * prompts user to reenter choice from 1-2.
     * @param  num used to store user's choice
     * @return     returns user's choice
     */
    int choiceError(int num) {
        Scanner sc = new Scanner(System.in);

        while (num < 1 || num > 2) {
            System.out.print("Choice: ");
            num = sc.nextInt();
        }
        return num;
    }

    /**
     * prompts user to reenter choice from 1-20.
     * @param  num used to store user's choice
     * @return     returns user's choice
     */
    int choiceError2(int num) {
        Scanner sc = new Scanner(System.in);

        while (num < 1 || num > MAX_20) {
            System.out.print("Choice: ");
            num = sc.nextInt();
        }
        return num;
    }

     /**
     * prompts user to reenter choice from 1-100.
     * @param  num used to store user's choice
     * @return     returns user's choice
     */
    int choiceError3(int num) {
        Scanner sc = new Scanner(System.in);

        while (num < 1 || num > MAX_99) {
            System.out.print("Choice: ");
            num = sc.nextInt();
        }
        return num;
    }

    /**
     * calls all methods and creates interface.
     * @param args allows command line arguments
     */
    public static void main(String[] args) {
        int userChoice = 0;
        int length = 0;
        int width = 0;
        int area = 0;
        int[] intArray = new int[ARRAY_SIZE];

        DndMain dnd = new DndMain();

        Scanner sc = new Scanner(System.in);

        System.out.println("----------------------WELCOME TO DUNGEON MASTER----------------------\n");
        System.out.println("Would you like to randomize or customize your chamber?");
        System.out.println("1 for random\n2 for custom");
        System.out.print("Choice: ");
        userChoice = sc.nextInt();
        userChoice = dnd.choiceError(userChoice);

        if (userChoice == 1) {
            dnd.randomizeChamber();

        } else if (userChoice == 2) {
            dnd.shapeAndSizeOfChamber(intArray);
            dnd.createContent();
            length = intArray[0];
            width = intArray[1];
            area = intArray[2];
            dnd.printStory(length, width, area);
        }

    }
}
