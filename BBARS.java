/*
This program will show how to borrow and return book system like in a library.
This Java program shows a library's book borrowing and returning system. 
The program starts by asking the user to enter their name, age, address, and contact number. 
The program then displays a menu of options for the user to choose from: list all books, borrow a book, return a book, or exit the program. 
The program uses arrays to store the books' names, and a boolean array to keep track of whether each book has been borrowed or not. 
The program also uses a switch statement to perform the desired action based on the user's choice.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BBARS extends JFrame implements ActionListener {
    private static final int MAX_BOOKS = 15;
    private String[] books = new String[MAX_BOOKS];
    private boolean[] borrowed = new boolean[MAX_BOOKS];

    private JTextArea textArea;        // Text area to display the book list and messages
    private JTextField inputField;     // Text field for user input
    private JLabel messageLabel;       // Label to display status messages

    public BBARS() {
        initializeBooks();              // Initialize the book titles and availability

        setTitle("Book Borrowing And Returning System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));  
        JButton listBooksButton = createButton("List of all books");                  
        JButton borrowBookButton = createButton("Borrow a book");                       
        JButton returnBookButton = createButton("Return a book");         
        JButton submitButton = createButton("Submit");                    // Button to submit user input

        buttonPanel.add(listBooksButton);
        buttonPanel.add(borrowBookButton);
        buttonPanel.add(returnBookButton);
        buttonPanel.add(submitButton);
        
        // Text area for book list and messages
        textArea = new JTextArea(10, 40);               
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        // Text field for user input
        inputField = new JTextField(20);                
        
        // Panel for user input
        JPanel inputPanel = new JPanel();               
        inputPanel.add(new JLabel("Enter book number:"));
        inputPanel.add(inputField);
        
        // Label to display status messages
        messageLabel = new JLabel();                     
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(buttonPanel, BorderLayout.WEST);   // Add buttons to the left side
        mainPanel.add(scrollPane, BorderLayout.CENTER);  // Add text area for book list and messages
        mainPanel.add(inputPanel, BorderLayout.SOUTH);   // Add input panel at the bottom
        mainPanel.add(messageLabel, BorderLayout.NORTH); // Add message label at the top

        add(mainPanel);
        
        // Register action listeners for buttons
        listBooksButton.addActionListener(this);        
        borrowBookButton.addActionListener(this);
        returnBookButton.addActionListener(this);
        submitButton.addActionListener(this);
       
    }

    // ActionListener implementation to handle button clicks
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        // Call method to list all books
        if (buttonText.equals("List of all books")) {
            listBooks();                                
        } else if (buttonText.equals("Borrow a book")) {
            textArea.setText("Enter the book number you want to borrow:");
            inputField.setEditable(true);
            inputField.requestFocus();
        } else if (buttonText.equals("Return a book")) {
            textArea.setText("Enter the book number you want to return:");
            inputField.setEditable(true);
            inputField.requestFocus();
        } else if (buttonText.equals("Submit")) {
            handleUserInput(inputField.getText());       // Handle user input when Submit button is clicked
            inputField.setText("");
            inputField.setEditable(false);
            inputField.requestFocus();
        }
    }

    // Method to create and customize buttons
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        return button;
    }

    // Method to initialize the book titles and availability
    private void initializeBooks() {
        books[0] = "A Brief History of Time";
        books[1] = "Architecture: Form, Space, & Order";
        books[2] = "De Architectura";
        books[3] = "The 33 Strategies of War";
        books[4] = "The 48 Laws of Power";
        books[5] = "The Catcher in the Rye";
        books[6] = "The Mastery";
        books[7] = "The Odyssey";
        books[8] = "Moby Dick";
        books[9] = "Mythology: Timeless Tales of Gods and Heroes";
        books[10] = "Noli Me Tangere";
        books[11] = "The Arabian Nights: One Thousand and One Nights";
        books[12] = "Pride and Prejudice";
        books[13] = "Romeo and Juliet";
        books[14] = "To Kill a Mockingbird";
    }

    // Method to list all available books
    private void listBooks() {
        StringBuilder bookList = new StringBuilder("List of Books: \n");
        for (int i = 0; i < MAX_BOOKS; i++) {
            if (!borrowed[i]) {
                bookList.append((i + 1)).append(". ").append(books[i]).append("\n");
            }
        }
        textArea.setText(bookList.toString());
        messageLabel.setText("");
    }

    // Method to handle user input for borrowing or returning a book
    private void handleUserInput(String input) {
        try {
            int bookNum = Integer.parseInt(input);
            if (bookNum < 1 || bookNum > MAX_BOOKS) {
                showMessage("Invalid book number. Please enter a valid book number.");
            } else {
                bookNum--; // Adjust index to match array index
                if (textArea.getText().startsWith("Enter the book number you want to borrow:")) {
                    borrowBook(bookNum); // Borrow book
                } else if (textArea.getText().startsWith("Enter the book number you want to return:")) {
                    returnBook(bookNum); // Return book
                }
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid input. Please enter a valid book number.");
        }
    }

    // Method to borrow a book
    private void borrowBook(int bookNum) {
        if (borrowed[bookNum]) {
            showMessage("Sorry, the book is not available.");
        } else {
            borrowed[bookNum] = true;
            showMessage("You have successfully borrowed the book '" + books[bookNum] + "'.", Color.GREEN);
        }
    }

    // Method to return a book
    private void returnBook(int bookNum) {
        if (!borrowed[bookNum]) {
            showMessage("You cannot return a book that you haven't borrowed.", Color.RED);
        } else {
            borrowed[bookNum] = false;
            showMessage("You have successfully returned the book '" + books[bookNum] + "'.", Color.GREEN);
        }
    }

    // Method to display a message in the message label
    private void showMessage(String message) {
        showMessage(message, Color.RED);
    }

    // Method to display a message with a specified color in the message label
    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }

    // Main method to start the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BBARS bbars = new BBARS();
                bbars.pack();
                bbars.setVisible(true);
            }
        });
    }
}
