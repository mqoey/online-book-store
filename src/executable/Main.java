package executable;

import gui.BookstoreGUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookstoreGUI bookstoreGUI = new BookstoreGUI();
        });
    }
}
