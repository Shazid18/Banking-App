package application;


import java.io.IOException;

import banking.BankAccount;
import banking.InvalidAccountException;
import banking.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccountHolderController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button exitButton;
	@FXML
	private AnchorPane scenePane;
	
	@FXML
	TextField accNum;
	@FXML
	ListView<String> listView;
	@FXML
	Label error1;
	
	static String nid;
	
	public void NID(String n) {
		nid = n;
	}
	
	public void backToLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene =new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	
	public void accountSummary(ActionEvent event) {
		listView.getItems().clear();
		error1.setText(null);
		String accNumber = accNum.getText();
		try {

			listView.getItems().addAll(Main.bank.findAccount(nid, accNumber).toString());
		
		} catch (InvalidAccountException e) {
			error1.setText("InvalidAccount");
		}
		accNum.clear();
	}
	
	
	public void transactions(ActionEvent event) {
		listView.getItems().clear();
		error1.setText(null);
		String accNumber = accNum.getText();
		try {
			
			ObservableList<Transaction> list = FXCollections.observableArrayList(Main.bank.getAccTransactions(nid, accNumber));
            for(int i=0; i < list.size();i++) {
                listView.getItems().addAll(list.get(i).toString());
                }
			
		} catch (InvalidAccountException e) {
			error1.setText("InvalidAccount");
		}
		accNum.clear();
	}

	
	public void viewAccounts(ActionEvent event) {
		listView.getItems().clear();
		error1.setText(null);
		
		ObservableList<BankAccount> list = FXCollections.observableArrayList(Main.bank.findAccounts(nid));
        for(int i=0; i < list.size();i++) {
            listView.getItems().addAll(list.get(i).toString());
            }
	}
	
	public void exit(ActionEvent event) {
		stage=(Stage) scenePane.getScene().getWindow();
		stage.close();
	}

}
