// This program sets up the starting pane, the window you see when the code is first launched.
// It explains how the type of encryption used works.

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class StartPane extends VBox 
{
    private Label lWelcome, lWorks, lExplanation, lOriginalAlphabet, lNewAlphabet;
    private VBox explanationPane;
    
    public StartPane() 
    {
        // welcome message
        lWelcome = new Label("Welcome to the Keyword Cipher! \nChoose one of the tabs above to get started!");
        lWelcome.setStyle("-fx-font: normal 18px \"Spot Mono\"");
        lWelcome.setTextAlignment(TextAlignment.CENTER);
        lWelcome.setPadding(new Insets(10,10,50,10));
        lWelcome.setAlignment(Pos.TOP_CENTER);

        // pane for the explanation of how the cipher works
        explanationPane = new VBox();
        explanationPane.setPadding(new Insets(10));
        explanationPane.setSpacing(10);
        explanationPane.setAlignment(Pos.CENTER);

        // title of explanation
        lWorks = new Label("How This Works");
        lWorks.setStyle("-fx-font: normal 16px \"Spot Mono\"");
        lWorks.setTextAlignment(TextAlignment.CENTER);

        // the explanation itself
        lExplanation = new Label("This program encrypts and decrypts messages according to a keyword cipher.The keyword is what shifts the alphabet to make the cipher. Each letter of of the alphabet is assigned to a new letter in the cipher alphabet. \n\nFor example, in this scenario, the keyword is CODE.");
        lExplanation.setWrapText(true);
        lExplanation.setMaxWidth(550);

        // the example
            // displays the first few letters of the original English alphabet
        lOriginalAlphabet = new Label ("Original:  A B C D E F G H I J K ...");
        lOriginalAlphabet.setStyle("-fx-font: normal 18px \"Spot Mono\"");
        
            // displays the resulting cipher from the key word input 
        lNewAlphabet = new Label("Encrypted: C O D E A B F G H I J ...");
        lNewAlphabet.setStyle("-fx-font: normal 18px \"Spot Mono\"");

        explanationPane.getChildren().addAll(lWorks, lExplanation, lOriginalAlphabet, lNewAlphabet);

        setPadding(new Insets(10));
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(lWelcome, explanationPane);
    }
}
