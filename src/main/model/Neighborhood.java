package model;

import java.util.List;
import java.util.ArrayList;

// Represents a neighborhood having a list of cats, amount of cats in the list,
// and amount of cats removed from the list after 7 days
public class Neighborhood {
    private List<Cat> listOfCats;
    private int rescued;
    private int present;

    /*
     * EFFECTS: constructs a neighborhood with a list of cats,
     *          number of cats who have been removed from the
     *          list, and number of cats present in the list
     */
    public Neighborhood() {
        this.listOfCats = new ArrayList<Cat>();
        this.rescued = 0;
        this.present = 0;
    }

    /*
     * REQUIRES: cat is not null
     * MODIFIES: this
     * EFFECTS: adds the cat to the list of cats
     *          increments the amount of cats present by 1
     */
    public void addCat(Cat cat) {
        this.listOfCats.add(cat);
        this.present++;
    }

    /*
     * REQUIRES: cat != null
     * MODIFIES: this
     * EFFECTS: removes the cat from the list of cats
     *          increases the amount of cats rescued
     *          (or removed from the list) by 1
     *          decrements the amount of cats present by 1
     */
    public void deleteCat(Cat cat) {
        this.listOfCats.remove(cat);
        this.rescued++;
        this.present--;
    }

    /*
     * REQUIRES: cat != null, !listOfCats.isEmpty()
     * MODIFIES: this
     * EFFECTS: finds the entered cat in the list of cats, then
     *          increments the cat's number of daysFed by 1;
     *          increments the cat's number of daysPassed by 1
     */
    public void fedToday(Cat cat) {
        for (int i = 0; i < listOfCats.size(); i++) {
            if (listOfCats.get(i) == cat) {
                this.listOfCats.get(i).increaseDaysFed();
                this.listOfCats.get(i).increaseDaysPassed();
            }
        }
    }

    /*
     * REQUIRES: cat != null, !listOfCats.isEmpty()
     * MODIFIES: this
     * EFFECTS: finds the entered cat in the list of cats, then
     *          increments the cat's number of daysPassed by 1
     */
    public void notFedToday(Cat cat) {
        for (int i = 0; i < listOfCats.size(); i++) {
            if (listOfCats.get(i) == cat) {
                this.listOfCats.get(i).increaseDaysPassed();
            }
        }
    }

    public List<Cat> getCatList() {
        return listOfCats;
    }

    public int getRescued() {
        return rescued;
    }

    public int getPresent() {
        return present;
    }
}
