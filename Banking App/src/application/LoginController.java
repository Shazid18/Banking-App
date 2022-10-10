package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class LoginController {
	@FXML
	TextField empFld;
	@FXML
	TextField userFld;
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void eLogin(ActionEvent event) throws IOException {
		String eId = empFld.getText();
		root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene =new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void userLogin(ActionEvent event) throws IOException {
		String n = userFld.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountHolder.fxml"));
        root = loader.load();
        AccountHolderController cusMenu = loader.getController();
        cusMenu.NID(n);
	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene =new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void exitTest(ActionEvent event) {
		System.exit(0);
	}

}
