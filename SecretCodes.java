// This code sets up the GUI window.
// It is entirely for graphics and combines all of the panes into one window

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SecretCodes extends Application 
{ 
    private TabPane tabPane;
	private DecryptPane decryptPane;
	private EncryptPane encryptPane;
    private StartPane startPane;

	public void start(Stage stage)
	{
		StackPane root = new StackPane();

		// creates the panes
		tabPane = new TabPane();
		decryptPane = new DecryptPane();
        encryptPane = new EncryptPane();
        startPane = new StartPane();

		// tab for startPane
        Tab tab0 = new Tab();
        tab0.setText("Home");
        tab0.setContent(startPane);

		// tab for encryptPane
		Tab tab1 = new Tab();
		tab1.setText("Encrypt");
		tab1.setContent(encryptPane);

		// tab for decryptPane
		Tab tab2 = new Tab();
		tab2.setText("Decrypt");
		tab2.setContent(decryptPane);

		tabPane.getSelectionModel().select(0);
		tabPane.getTabs().addAll(tab0, tab1, tab2);

		root.getChildren().add(tabPane);

		Scene scene = new Scene(root, 600, 600); 
		stage.setTitle("Keyword Cipher");
        scene.getStylesheets().add("Stylesheets.css");
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}