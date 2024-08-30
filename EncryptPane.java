// This program initializes the graphics for the Encryption screen
// It also has the code that decides what happens when certain buttons are clicked.

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class EncryptPane extends VBox
{
    private GridPane keyPane;
    private HBox buttonPane;
    private Label lWelcome, lMessage, lKey, lError, lOutput;
    private TextField keyField;
    private TextArea messageField, output;
    private Button encryptButton, reset;

    public EncryptPane()
    {
        // welcome message/title
        lWelcome = new Label("Welcome to the Encryption Page!!");
        lWelcome.setStyle("-fx-font: normal 18px \"Spot Mono\"");
        lWelcome.setMinWidth(Region.USE_PREF_SIZE);
        lWelcome.setTextAlignment(TextAlignment.CENTER);

        // will display any errors that occur 
        lError = new Label("");
        lError.setStyle("-fx-text-fill: rgb(110, 40, 116)");
        lError.setPadding(new Insets(0));

        // setting up message input
        lMessage = new Label("Enter decrypted message:");
        lMessage.setMinWidth(Region.USE_PREF_SIZE);
        messageField = new TextArea();
        messageField.setPrefRowCount(5);
        messageField.setWrapText(true);
        
        // setting up key word input
        lKey = new Label("Key:");
        lKey.setMinWidth(Region.USE_PREF_SIZE);
        keyField = new TextField();
        
        // pane for the keyword input
        keyPane = new GridPane();
        keyPane.setPadding(new Insets(5,10,10,10));
        keyPane.setHgap(10);
        keyPane.setVgap(10);
        keyPane.add(lKey, 0, 0);
        keyPane.add(keyField, 1, 0);

        // sets up button to encrypt and reset
        encryptButton = new Button("Encrypt");
        reset = new Button("Reset");
        
        // pane for buttons set up
        buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(0));
        buttonPane.setSpacing(50);
        buttonPane.getChildren().addAll(encryptButton, reset);

        // output set up where the encrypted message will appear
        lOutput = new Label("Your encrypted message:");
        lOutput.setMinWidth(Region.USE_PREF_SIZE);
        output = new TextArea("Your encrypted message will appear here!");
        output.setPrefRowCount(5);
        output.setDisable(true);
        output.setWrapText(true);

        // sets up EncryptPane VBox
        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(lWelcome, lError, lMessage, messageField, keyPane, buttonPane, lOutput, output);

        // event handlers for buttons
        encryptButton.setOnAction(new ButtonHandler());
        reset.setOnAction(new ButtonHandler());
    }

    private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
            // encrypt button
            if (e.getSource() == encryptButton)
            {
                String key = keyField.getText();
                String message = messageField.getText();

                // error message for empty message field and empty key field
                if (message.equals(""))
                    lError.setText("Error. Please enter a message.");
                else if (key.equals(""))
                    lError.setText("Error. Please enter a key.");
                else
                {
                    // displays encrypted message
                    lError.setText("Success! Message encrypted using key \"" + key + "\".");
                    lError.setStyle("-fx-text-fill: blue");
                    output.setDisable(false);
                    output.clear();
                    output.appendText(Code.encrypt(key, message));
                }
            }
            // reset button
            else if (e.getSource() == reset)
            {
                // restores original setup
                lError.setText("");
                lError.setStyle("-fx-text-fill: rgb(110, 40, 116)");
                output.setDisable(true);
                output.clear();
                output.appendText("Your encrypted message will appear here!");
                messageField.clear();
                keyField.clear();
            }
        }
    }
}
