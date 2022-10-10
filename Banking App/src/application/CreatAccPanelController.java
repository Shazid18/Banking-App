package application;

import java.io.IOException;

import banking.BankAccount;
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

public class CreatAccPanelController {
	
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Button exitButton;
	@FXML
	private AnchorPane scenePane;
	
	@FXML
	TextField name;
	@FXML
	TextField nidNum;
	@FXML
	TextField deposit;
	@FXML
	TextField maxWithLim;
	@FXML
	TextField trdLicNum;
	@FXML
	TextField stdId;
	@FXML
	TextField insName;
	@FXML
	ListView<String> listView;
	@FXML
	Label error;
	
	public void switchToEmployee(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Employee.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
	
	public void savingAccount(ActionEvent event) {
		error.setText(null);
		listView.getItems().clear();
		String Name = name.getText();
		String NIDNumber = nidNum.getText();
		
		try {
		String dAmmount = deposit.getText();
		double Ammount = Double.parseDouble(dAmmount);
		String MWL = maxWithLim.getText();
		double MaxWithLimit = Double.parseDouble(MWL);

		
		listView.getItems().addAll("Account Number : " + Main.bank.addAccount(Name, NIDNumber, Ammount, MaxWithLimit));
		Main.bank.saveData();}
		catch(NumberFormatException e){
			error.setText("Ammount must be number.");
		}
		
		name.clear();
		nidNum.clear();
		deposit.clear();
		maxWithLim.clear();
		
	}
	
	public void currentAccount(ActionEvent event) {
		error.setText(null);
		listView.getItems().clear();
		String Name = name.getText();
		String NIDNumber = nidNum.getText();
		String TradeLicNumber = trdLicNum.getText();
		try {
		String dAmmount = deposit.getText();
		double Ammount = Double.parseDouble(dAmmount);
		
		listView.getItems().addAll("Account Number : " + Main.bank.addAccount(Name, NIDNumber, Ammount, TradeLicNumber));
		Main.bank.saveData();}
		catch(NumberFormatException e) {
			error.setText("Ammount must be number.");
		}
	
		name.clear();
		nidNum.clear();
		deposit.clear();
		trdLicNum.clear();
	
	}
	
	public void studentAccount(ActionEvent event) {
		error.setText(null);
		listView.getItems().clear();
		String Name = name.getText();
		String NIDNumber = nidNum.getText();
		String StudentID = stdId.getText();
		String InstitutionName = insName.getText();
		
		try {
		String dAmmount = deposit.getText();
		double Ammount = Double.parseDouble(dAmmount);
	
		listView.getItems().addAll("Account Number : " + Main.bank.addAccount(Name, NIDNumber, Ammount, InstitutionName, StudentID));
		Main.bank.saveData();}
		catch(NumberFormatException e) {
			error.setText("Ammount must be number.");
		}
		
		name.clear();
		nidNum.clear();
		deposit.clear();
		stdId.clear();
		insName.clear();
		
	}
	
	public void exit(ActionEvent event) {
		stage=(Stage) scenePane.getScene().getWindow();
		stage.close();
	}

}
