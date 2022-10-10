package application;


import java.io.IOException;

import banking.BankAccount;
import banking.InSufficientBalanceException;
import banking.InvalidAccountException;
import banking.MaxWithdawException;
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

public class EmployeeController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	@FXML
	private Button exitButton;
	@FXML
	private AnchorPane scenePane;
	
	@FXML
	TextField dAccNum;
	@FXML
	TextField ammount;
	@FXML
	TextField fromAccNum;
	@FXML
	TextField toAccNum;
	@FXML
	TextField trsAmmount;
	@FXML
	TextField accNum;
	@FXML
	TextField accType;
	@FXML
	ListView<String> listView;
	@FXML
	Label error1;
	@FXML
	Label error2;
	@FXML
	Label error3;
	@FXML
	Label error4;
	@FXML
	Label error5;
	@FXML
	Label error6;
	@FXML
	Label error7;
	
	public void removeMsg() {
		error1.setText(null);
		error2.setText(null);
		error3.setText(null);
		error4.setText(null);
		error5.setText(null);
		error6.setText(null);
		error7.setText(null);
	}
	
	public void backToLogin(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	     stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	    scene =new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void switchToCreatAccPanel(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("CreatAccPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
	
	public void deposit(ActionEvent event) {
		removeMsg();
		String accNumber = dAccNum.getText();
		String dAmmount = ammount.getText();
		double depAmmount = Double.parseDouble(dAmmount);
	    
		try {
			Main.bank.deposit(accNumber, depAmmount);
		} catch (InvalidAccountException e) {
			error1.setText("Invalid Account");
		}
		Main.bank.saveData();
		dAccNum.clear();
		ammount.clear();
	}
	
	public void withdraw(ActionEvent event)  {
		removeMsg();
		String accNumber = dAccNum.getText();
		String wAmmount = ammount.getText();
		double withAmmount = Double.parseDouble(wAmmount);
		try {
			Main.bank.withdraw(accNumber, withAmmount);
		} catch (InvalidAccountException e) {
			error1.setText("Invalid Account");
		} catch (InSufficientBalanceException e) {
			error2.setText("Insufficient Balance");
		} catch (MaxWithdawException e) {
			error3.setText("Max Withdraw Limit Exeed");
		}
		Main.bank.saveData();
		dAccNum.clear();
		ammount.clear();
	}
	
	public void transfer(ActionEvent event) {
		removeMsg();
		String fAccNumber = fromAccNum.getText();
		String tAccNumber = toAccNum.getText();
		String tAmmount = trsAmmount.getText();
		double transAmmount = Double.parseDouble(tAmmount);
		try {
			Main.bank.transfer(fAccNumber, tAccNumber, transAmmount);
		} catch (InvalidAccountException e) {
			error4.setText("Invalid Account");
		} catch (InSufficientBalanceException e) {
			error5.setText("Insufficient Balance");
		} catch (MaxWithdawException e) {
			error6.setText("Max Withdraw Limit Exeed");
		}
		Main.bank.saveData();
		fromAccNum.clear();
		toAccNum.clear();
		trsAmmount.clear();
	}
	
	public void summary(ActionEvent event) {
		listView.getItems().clear();
		removeMsg();
		String accNumber = accNum.getText();
		try {

			listView.getItems().addAll(Main.bank.findAccount(accNumber).toString());
		
		} catch (InvalidAccountException e) {
			error7.setText("Invalid Account");
			
		}
		accNum.clear();
	}
	
	public void transactions(ActionEvent event) {
		listView.getItems().clear();
		removeMsg();
		String accNumber = accNum.getText();
		
		try {
			
			ObservableList<Transaction> list = FXCollections.observableArrayList(Main.bank.getAccTransactions(accNumber));
            for(int i=0; i < list.size();i++) {
                listView.getItems().addAll(list.get(i).toString());
}
		
		} catch (InvalidAccountException e) {
			error7.setText("Invalid Account");
		}
		accNum.clear();
	}
	
	public void listOfAccounts(ActionEvent event) {
		listView.getItems().clear();
		removeMsg();
		
		ObservableList<BankAccount> list = FXCollections.observableArrayList(Main.bank.getAccounts());
        for(int i=0; i < list.size();i++) {
            listView.getItems().addAll(list.get(i).toString());
}
	}
	
	public void getAccounts(ActionEvent event) {
		listView.getItems().clear();
		removeMsg();
		String accountType = accType.getText();
		
		ObservableList<BankAccount> list = FXCollections.observableArrayList(Main.bank.getAccounts(accountType));
        for(int i=0; i < list.size();i++) {
            listView.getItems().addAll(list.get(i).toString());
}
		accType.clear();
	}
	
	public void exit(ActionEvent event) {
		stage=(Stage) scenePane.getScene().getWindow();
		stage.close();
	}

}
