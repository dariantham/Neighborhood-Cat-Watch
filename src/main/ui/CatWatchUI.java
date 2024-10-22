package ui;

import model.Cat;
import model.EventLog;
import model.EventLogShutdownHook;
import model.Neighborhood;
import org.json.JSONArray;

import java.util.List;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Cat Watch application with UI
public class CatWatchUI extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;
    private static final String JSON_STORE = "./data/neighborhood.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Neighborhood neighborhood;

    private static final String addCatString = "Add Cat";
    private static final String deleteCatString = "Delete Cat";
    private JButton addCatButton;
    private JButton deleteCatButton;
    private JTextField catName;
    private JButton feedButton;
    private JButton noFeedButton;
    private JButton rescuedCatsButton;
    private JButton saveButton;
    private JButton loadButton;

    // EFFECTS: runs the application by initializing all the buttons on the panel
    public CatWatchUI() {
        super(new BorderLayout());

        listModel = new DefaultListModel();
        listModel.addElement("");

        neighborhood = new Neighborhood();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        createList();
        createAddButton();
        createDeleteButton();
        createFeedButton();
        createNoFeedButton();
        createSaveButton();
        createLoadButton();
        createViewRescuedButton();
        createPanel();

        listModel.removeElement("");
    }

    // EFFECTS: initialize and display the splash screen when prompted
    private static void showSplashScreen() {
        SplashScreen splash = new SplashScreen(3000);
        splash.showSplash();
    }

    // EFFECTS: create the list and put it in a scroll pane
    // MODIFIES: list
    private void createList() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
    }

    // EFFECTS: creates the add button
    // MODIFIES: catName, addCatButton
    private void createAddButton() {
        addCatButton = new JButton(addCatString);
        AddCatListener addCatListener = new AddCatListener(addCatButton);
        addCatButton.setActionCommand(addCatString);
        addCatButton.addActionListener(addCatListener);
        addCatButton.setEnabled(false);
        catName = new JTextField(10);
        catName.addActionListener(addCatListener);
        catName.getDocument().addDocumentListener(addCatListener);
    }

    // EFFECTS: creates the delete button
    // MODIFIES: deleteCatButton
    private void createDeleteButton() {
        deleteCatButton = new JButton(deleteCatString);
        deleteCatButton.setActionCommand(deleteCatString);
        deleteCatButton.addActionListener(new DeleteCatListener());
    }

    // EFFECTS: creates the feed button
    // MODIFIES: feedButton
    private void createFeedButton() {
        feedButton = new JButton("Feed");
        feedButton.setEnabled(false);
        feedButton.addActionListener(new FeedCatListener());
    }

    // EFFECTS: creates the not fed button
    // MODIFIES: noFeedButton
    private void createNoFeedButton() {
        noFeedButton = new JButton("Not Fed");
        noFeedButton.setEnabled(false);
        noFeedButton.addActionListener(new NoFeedCatListener());
    }

    // EFFECTS: creates the save button
    // MODIFIES: saveButton
    private void createSaveButton() {
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveNeighborhood();
            }
        });
    }

    // EFFECTS: creates the load button
    // MODIFIES: loadButton
    private void createLoadButton() {
        loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadNeighborhood();
            }
        });
    }

    // EFFECTS: creates the rescued button
    // MODIFIES: rescuedCatsButton
    private void createViewRescuedButton() {
        rescuedCatsButton = new JButton("Rescued Cats");
        rescuedCatsButton.addActionListener(new RescuedCatsListener());
    }

    // EFFECTS: Create a panel that uses BoxLayout, with all the buttons above
    private void createPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(deleteCatButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(catName);
        buttonPane.add(addCatButton);
        buttonPane.add(feedButton);
        buttonPane.add(noFeedButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.add(rescuedCatsButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: implements the listener for the Delete Cat button
    //          displays appropriate message based on cat condition
    // MODIFIES: list, listModel, deleteCatButton, neighborhood
    class DeleteCatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (index != -1) {
                handleDeletedCat(neighborhood.getCatList().get(index));
                neighborhood.deleteCat(neighborhood.getCatList().get(index));
                listModel.remove(index);
            }

            int size = listModel.getSize();

            if (size == 0) {
                deleteCatButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    index--;
                }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // EFFECTS: implements the listener for the Add Cat button, shared by the text field
    // MODIFIES: this, neighborhood, catName, list
    class AddCatListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddCatListener(JButton button) {
            this.button = button;
        }

        public void actionPerformed(ActionEvent e) {
            String name = catName.getText();

            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                catName.requestFocusInWindow();
                catName.selectAll();
                return;
            }

            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            Cat newCat = new Cat(name);
            neighborhood.addCat(newCat);

            listModel.insertElementAt(catName.getText(), index);

            catName.requestFocusInWindow();
            catName.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: tests for string equality
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        // EFFECTS: updates whether the button is enabled or not
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // EFFECTS: updates whether the text field is empty or not
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // EFFECTS: alters the status of the updates for insertUpdate and removeUpdate
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: if the button is not already enabled, make it enabled
        // MODIFIES: button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: if the text field is empty from the user, disable the button
        //          return true if empty text field, false otherwise
        // MODIFIES: button, alreadyEnabled
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: implements the listener for the Feed button, while displaying the 7 day warning if needed
    // MODIFIES: neighborhood, listModel, list, feedButton
    class FeedCatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (index != -1) {
                Cat selectedCat = neighborhood.getCatList().get(index);
                neighborhood.fedToday(selectedCat);
            }
            int size = listModel.getSize();

            if (size == 0) {
                feedButton.setEnabled(false);

            } else {
                if (index == listModel.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            JOptionPane.showMessageDialog(CatWatchUI.this,
                        "You fed " + neighborhood.getCatList().get(index).getName());
            showNeighborhoodWarningIfNeeded();
        }
    }

    // EFFECTS: implements the listener for the Not Fed button, while displaying the 7 day warning if needed
    // MODIFIES: list, neighborhood, listModel, noFeedButton
    class NoFeedCatListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (index != -1) {
                Cat selectedCat = neighborhood.getCatList().get(index);
                neighborhood.notFedToday(selectedCat);
            }
            int size = listModel.getSize();

            if (size == 0) {
                noFeedButton.setEnabled(false);

            } else {
                if (index == listModel.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
            JOptionPane.showMessageDialog(CatWatchUI.this,
                    "Cat has NOT been fed today: " + neighborhood.getCatList().get(index).getName());
            showNeighborhoodWarningIfNeeded();
        }
    }

    // EFFECTS: implements the listener for the Rescued button, displaying a message with the appropriate value
    private class RescuedCatsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int rescuedCatsCount = neighborhood.getRescued();
            String message = "Number of rescued cats: " + rescuedCatsCount;
            JOptionPane.showMessageDialog(CatWatchUI.this, message);
        }
    }

    // EFFECTS: Manages whether the Delete Cat, Feed, and Not Fed button appear or not
    // MODIFIES: deleteCatButton, feedButton, noFeedButton
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() == -1) {
                deleteCatButton.setEnabled(false);
                feedButton.setEnabled(false);
                noFeedButton.setEnabled(false);
            } else {
                deleteCatButton.setEnabled(true);
                feedButton.setEnabled(true);
                noFeedButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: Create the GUI and show it
    // MODIFIES: frame, CatWatchUI
    private static void createAndShowGUI(JFrame frame) {
        JComponent newContentPane = new CatWatchUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        // Set minimum size for the frame
        frame.setMinimumSize(new Dimension(300, 200));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: gathers a list of cats that have been in the list for 7 days or more,
    //          then outputs a list of cats that have been there for too long
    private void showNeighborhoodWarningIfNeeded() {
        List<Cat> cats = neighborhood.getCatList();
        boolean showWarning = false;
        StringBuilder warningMessage = new StringBuilder("Warning: The following cats have been in the neighborhood"
                + "for 7 days or more:\n");
        for (Cat cat : cats) {
            if (cat.getDaysPassed() == 7) {
                showWarning = true;
                warningMessage.append(cat.getName()).append("\n");
            }
        }
        if (showWarning) {
            JOptionPane.showMessageDialog(this, warningMessage.toString());
        }
    }

    // EFFECTS: takes in a cat that the user removed from the list, and displays message accordingly
    //          depends on what the condition of the cat is
    private void handleDeletedCat(Cat cat) {
        if (cat.getDaysPassed() == 7) {
            if (cat.sendToVet()) {
                JOptionPane.showMessageDialog(CatWatchUI.this, "Please send it to the vet!");
            } else {
                JOptionPane.showMessageDialog(CatWatchUI.this, "Please send it to the shelter!");
            }
        } else {
            JOptionPane.showMessageDialog(CatWatchUI.this, "The cat has been reunited");
        }
    }

    // EFFECTS: runs the Cat Watch UI application, first showing the splash screen
    public static void main(String[] args) {
        showSplashScreen();
        JFrame frame = new JFrame("Cat Watch v1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and register the EventLogShutdownHook
        EventLogShutdownHook shutdownHook = new EventLogShutdownHook(frame);
        frame.addWindowListener(shutdownHook);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(frame);
            }
        });
    }

    // EFFECTS: saves the neighborhood to file
    // NOTE: Based on the sample JsonSerializationDemo code provided from the course
    private void saveNeighborhood() {
        JSONArray jsonCats = new JSONArray();
        for (Cat cat : neighborhood.getCatList()) {
            jsonCats.put(cat.toJson());
        }
        JOptionPane.showMessageDialog(this, "Neighborhood saved");

        try {
            jsonWriter.open();
            jsonWriter.write(neighborhood);
            jsonWriter.close();
            System.out.println("Saved current neighborhood status to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads neighborhood from file
    private void loadNeighborhood() {
        try {
            neighborhood = jsonReader.read();
            listModel.clear();
            for (Cat cat : neighborhood.getCatList()) {
                listModel.addElement(cat.getName());
            }
            JOptionPane.showMessageDialog(this, "Neighborhood loaded");
            System.out.println("Loaded neighborhood from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
