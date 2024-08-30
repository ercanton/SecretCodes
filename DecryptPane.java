// This program initializes the graphics for the Decryption screen
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

public class DecryptPane extends VBox
{
    private GridPane keyPane;
    private HBox buttonPane;
    private Label lWelcome, lMessage, lKey, lError, lOutput;
    private TextField keyField;
    private TextArea messageField, output;
    private Button decryptButton, reset;

    public DecryptPane()
    {
        // welcome message/title
        lWelcome = new Label("Welcome to the Decryption Page!!");
        lWelcome.setMinWidth(Region.USE_PREF_SIZE);
        lWelcome.setStyle("-fx-font: normal 18px \"Spot Mono\"");
        lWelcome.setTextAlignment(TextAlignment.CENTER);

        // displays any errors that will occur 
        lError = new Label("");
        lError.setStyle("-fx-text-fill: rgb(110, 40, 116)");
        lError.setPadding(new Insets(0));

        // message input setup
        lMessage = new Label("Enter encrypted message:");
        lMessage.setMinWidth(Region.USE_PREF_SIZE);
        messageField = new TextArea();
        messageField.setPrefRowCount(5);
        messageField.setWrapText(true);
        
        // setup for key word input
        lKey = new Label("Key:");
        lKey.setMinWidth(Region.USE_PREF_SIZE);
        keyField = new TextField();
        
        // sets up pane for key word
        keyPane = new GridPane();
        keyPane.setPadding(new Insets(5,10,10,10));
        keyPane.setHgap(10);
        keyPane.setVgap(10);
        keyPane.add(lKey, 0, 0);
        keyPane.add(keyField, 1, 0);

        // initializes button to decrypt message and reset
        decryptButton = new Button("Decrypt");
        reset = new Button("Reset");

        // pane for buttons set up
        buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(0));
        buttonPane.setSpacing(50);
        buttonPane.getChildren().addAll(decryptButton, reset);

        // sets up output where decrypted message will appear
        lOutput = new Label("Your decrypted message:");
        lOutput.setMinWidth(Region.USE_PREF_SIZE);
        output = new TextArea("Your decrypted message will appear here!");
        output.setDisable(true);
        output.setPrefRowCount(5);
        output.setWrapText(true);

        // sets up DecryptPane VBox
        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(lWelcome, lError, lMessage, messageField, keyPane, buttonPane, lOutput, output);

        // handler events for decrypt button and reset
        decryptButton.setOnAction(new ButtonHandler());
        reset.setOnAction(new ButtonHandler());
    }

    private class ButtonHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
            // decrypt button is clicked
            if (e.getSource() == decryptButton)
            {
                String key = keyField.getText();
                String message = messageField.getText();

                // if message field or key field is empty, error message will appear
                if (message.equals(""))
                    lError.setText("Error. Please enter a message.");
                else if (key.equals(""))
                    lError.setText("Error. Please enter a key.");
                else
                {
                    // decrypted message displayed successfully
                    lError.setText("Success! Message decrypted using key \"" + key + "\".");
                    lError.setStyle("-fx-text-fill: blue");
                    output.setDisable(false);
                    output.clear();
                    output.appendText(Code.decrypt(key, message));
                }
            }
            else if (e.getSource() == reset)
            {
                // restores original setup 
                lError.setText("");
                lError.setStyle("-fx-text-fill: rgb(110, 40, 116)");
                output.setDisable(true);
                output.clear();
                output.appendText("Your decrypted message will appear here!");
                messageField.clear();
                keyField.clear();
            }
        }
    }
}
