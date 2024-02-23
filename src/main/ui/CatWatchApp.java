package ui;

import model.Cat;
import model.Neighborhood;

import java.util.Scanner;

// Cat Watch application
public class CatWatchApp {
    private Neighborhood neighborhood;
    private Scanner input;

    // EFFECTS: runs the Cat Watch application
    public CatWatchApp() {
        runCatWatch();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // NOTE: based on the course project code of the TellerApp
    private void runCatWatch() {
        boolean keepGoing = true;
        String command = null;

        init();


        while (keepGoing) {
            warnUser();
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // NOTE: based on the course project code of the TellerApp
    private void processCommand(String command) {
        if (command.equals("a")) {
            addCatOption();
        } else if (command.equals("d")) {
            deleteCatOption();
        } else if (command.equals("e")) {
            editCatOption();
        } else if (command.equals("l")) {
            viewList();
        } else if (command.equals("p")) {
            viewPresent();
        } else if (command.equals("r")) {
            viewRescued();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the neighborhood
    // NOTE: based on the course project code of the TellerApp
    private void init() {
        neighborhood = new Neighborhood();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    // NOTE: based on the course project code of the TellerApp
    private void displayMenu() {
        System.out.println("\nPlease choose from:");
        System.out.println("\ta -> add a cat to the list");
        System.out.println("\td -> delete a cat to the list");
        System.out.println("\te -> edit info about a cat on the list");
        System.out.println("\tl -> view the list");
        System.out.println("\tp -> return number of cats present in the list");
        System.out.println("\tr -> return number of cats rescued");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: neighborhood.getCatList()
    // EFFECTS: adds a Cat into the list of cats, with given name inputted by user
    private void addCatOption() {
        System.out.print("Give a nickname for the new cat:");
        String nickname = input.next().trim();

        if (nickname.isEmpty()) {
            System.out.printf("Cannot add a new cat with no name...\n");
        } else {
            boolean nameExists = false;
            for (int i = 0; i < neighborhood.getCatList().size(); i++) {
                if (neighborhood.getCatList().get(i).getName().equals(nickname)) {
                    System.out.printf("Sorry, no duplication of names allowed!");
                    nameExists = true;
                }
            }
            if (!nameExists) {
                Cat newCat = new Cat(nickname);
                neighborhood.addCat(newCat);
                System.out.printf("%s has been added to the list!", newCat.getName());
            }
        }
    }

    // MODIFIES: neighborhood.getCatList()
    // EFFECTS: removes a Cat into the list of cats, with given name inputted by user
    //          produces appropriate response based on how many days fed
    private void deleteCatOption() {
        System.out.print("Type the name of the cat to delete from the list:");
        String catDelete = input.next().trim();

        if (catDelete.isEmpty()) {
            System.out.printf("Cannot delete a cat with an empty name...\n");
        }

        boolean catFound = false;
        for (int i = 0; i < neighborhood.getCatList().size(); i++) {
            if (neighborhood.getCatList().get(i).getName().equals(catDelete)) {
                catFound = true;
                if (neighborhood.getCatList().get(i).getDaysPassed() != 7) {
                    System.out.printf("Seems like %s has been reunited with the owner!", catDelete);
                } else {
                    System.out.printf("%s is removed! %s", catDelete,
                            neighborhood.getCatList().get(i).sendToVet() ? "PLEASE send to the vet!!!" :
                                    "You've maintained a healthy cat! Send it to the shelter for adoption. Great job!");
                }
                neighborhood.deleteCat(neighborhood.getCatList().get(i));
                break;
            }
        }

        if (!catFound) {
            System.out.printf("%s is not found on the list\n", catDelete);
        }
    }

    // MODIFIES: this
    // EFFECTS: for the inputted cat, increments daysPassed and daysFed, as appropriate
    private void editCatOption() {
        System.out.printf("Please enter the name of the cat you would like to log the feeding of:");
        String catEdit = input.next().trim();
        boolean eaten = selectFeeding();

        if (catEdit.isEmpty()) {
            System.out.printf("Cannot change or log a cat with an empty name...\n");
        } else {
            boolean catFound = false;
            for (int i = 0; i < neighborhood.getCatList().size(); i++) {
                if (neighborhood.getCatList().get(i).getName().equals(catEdit)) {
                    catFound = true;
                    if (eaten) {
                        neighborhood.fedToday(neighborhood.getCatList().get(i));
                        System.out.printf("%s has been fed, amazing work!", catEdit);
                    } else {
                        neighborhood.notFedToday((neighborhood.getCatList().get(i)));
                        System.out.printf("%s had not been fed today. Hopefully they come back!", catEdit);
                    }
                }
            }
            if (!catFound) {
                System.out.printf("%s is not found on the list", catEdit);
            }
        }
    }

    // EFFECTS: produces a list of stray cats in the neighborhood
    private void viewList() {
        System.out.println("List of cats in neighborhood:");
        for (Cat cat : neighborhood.getCatList()) {
            System.out.println(cat.getName());
        }
    }

    // EFFECTS: outputs the number of stray cats in the neighborhood
    private void viewPresent() {
        System.out.printf("%d cats present in the list", neighborhood.getPresent());
    }

    // EFFECTS: outputs the number of cats that the user has rescued
    private void viewRescued() {
        System.out.printf("%d cats have been rescued. Keep it up!", neighborhood.getRescued());
    }

    // EFFECTS: prompts user to select whether they fed the cat or not, return true if they did
    // NOTE: based on the course project code of the TellerApp
    private boolean selectFeeding() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("y") || selection.equals("n"))) {
            System.out.println("y if you fed the cat today");
            System.out.println("n if you did not feed the cat today");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: displays a warning for each cat in the list that has been a stray for 7 days or more
    private void warnUser() {
        for (int i = 0; i < neighborhood.getCatList().size(); i++) {
            if (neighborhood.getCatList().get(i).getDaysPassed() == 7) {
                System.out.printf("\nWARNING: %s has been a stray for over 7 days, please send to vet or shelter!",
                        neighborhood.getCatList().get(i).getName());
            }
        }
    }
}
