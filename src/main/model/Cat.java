package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a cat having a name, number of days fed and number of days passed since first sighting
public class Cat implements Writable {
    private String name;          // the name of the cat, determined by the user
    private int daysFed;          // tracks how many days the cat is fed
    private int daysPassed;       // tracks how many days passed since first encounter with cat

    /*
    * REQUIRES: name has a non-zero length
    * EFFECTS: constructs a cat with a name, daysFed, and daysPassed
    *          name is set to the name given by the user
    *          daysFed is set to 0
    *          daysPassed is set to 0
    */
    public Cat(String name) {
        this.name = name;
        this.daysFed = 0;
        this.daysPassed = 0;
    }

    public String getName() {
        return name;
    }

    public int getDaysFed() {
        return daysFed;
    }

    public int getDaysPassed() {
        return daysPassed;
    }


    /*
     * MODIFIES: this
     * EFFECTS: daysFed is incremented by 1, with a maximum of 7 days fed
     */
    public void increaseDaysFed() {
        if (daysFed < 7) {
            this.daysFed++;
        }
        EventLog.getInstance().logEvent(new Event("Increased days fed for: " + this.getName()));
    }

    /*
     * MODIFIES: this
     * EFFECTS: daysPassed is incremented by 1, with a maximum of 7 days
     */
    public void increaseDaysPassed() {
        if (daysPassed < 7) {
            this.daysPassed++;
        }
        EventLog.getInstance().logEvent(new Event("Increased days passed for: " + this.getName()));
    }

    public void setDaysFed(int daysFed) {
        this.daysFed = daysFed;
    }

    public void setDaysPassed(int daysPassed) {
        this.daysPassed = daysPassed;
    }

    /*
     * REQUIRES: !(getDaysPassed() > 7)
     * MODIFIES: this
     * EFFECTS: marks if a cat should either be sent to the vet
     *          for medical aid or sent to the local shelter for
     *          adoption; if daysPassed has not reached 7 days,
     *          return false
     */
    public boolean sendToVet() {
        boolean b = false;
        if (this.daysPassed == 7) {
            if (this.daysFed <= 4) {
                b = true;
            }
            if (this.daysFed > 4) {
                b = false;
            }
        }
        EventLog.getInstance().logEvent(new Event(this.getName() + " has been released and set for shelter or vet"));
        return b;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("daysFed", daysFed);
        json.put("daysPassed", daysPassed);
        return json;
    }
}
