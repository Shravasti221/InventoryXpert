package com.labProject;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.regex.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable{
    private final ObjectProperty<User> user = new SimpleObjectProperty<>();
    public final ObjectProperty<User> userProperty() {
        return this.user;
    }
    public final com.labProject.User getUser() {
        return this.userProperty().get();
    }
    public final void setUser(final com.labProject.User user) {
        this.userProperty().set(user);
    }
    @FXML
    private TabPane allTabs;
    @FXML
    private TextField userNameFieldConsumer ;
    @FXML
    private PasswordField passwordFieldConsumer ;
    @FXML
    private ImageView imgConsumer;
    @FXML
    private Label errorLabelConsumer ;
    @FXML
    private TextField userNameFieldProducer ;
    @FXML
    private PasswordField passwordFieldProducer ;
    @FXML
    private Label errorLabelProducer ;
    @FXML
    private TextField NameCA ;
    @FXML
    private PasswordField passwordFieldCA ;
    @FXML
    private TextField mobileCA;
    @FXML
    private ChoiceBox typeCA;
    @FXML
    private TitledPane errorPane;
    @FXML
    private Label errorPaneLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCA.getItems().add("Consumer");
        typeCA.getItems().add("Producer");
        errorPane.setVisible(false);
        allTabs.setDisable(false);

    }
    @FXML
    private void okConsumer() {
        String userName = userNameFieldConsumer.getText();
        String password = passwordFieldConsumer.getText();
        if (authenticate(userName, password)) {
            System.out.println("Username and ID found");
            errorLabelConsumer.setText("");
            Consumer c = Main.godown.getConsumer(userName);
            ConsumerThread c_ = new ConsumerThread(c);
            Platform.runLater(c_);
        } else {
            errorLabelConsumer.setText("Incorrect login details");
        }
        clearFields();
    }
    @FXML
    private void okProducer() {
        String userName = userNameFieldProducer.getText();
        String password = passwordFieldProducer.getText();
        if (authenticate(userName, password)) {
            errorLabelProducer.setText("");
            System.out.println("Username and ID found");
            errorLabelProducer.setText("");
            Producer p = Main.godown.getProducer(userName);
            ProducerThread p_ = new ProducerThread(p);
            Platform.runLater(p_);
        } else {
            errorLabelProducer.setText("Incorrect login details");
        }
        clearFields();
    }
    @FXML
    private void cancel() {
        setUser(null);
        clearFields();
        errorLabelProducer.setText("");
        errorLabelConsumer.setText("");
    }
    @FXML
    private void closeRollError(){
        errorPane.setVisible(false);
        allTabs.setDisable(false);
    }
    @FXML
    private void writeToErrorPane(String title, String s){
        errorPane.setText(title);
        errorPane.setVisible(true);
        allTabs.setDisable(true);
        errorPaneLabel.setText(s);
    }
    @FXML
    void createUserID(){
        PatternChecker verifier = new PatternChecker();
        String ID = "";
        String mNo = mobileCA.getText();
        String name = NameCA.getText();
        String pwd = passwordFieldCA.getText();
        String roleName = (String) typeCA.getValue();
        if(mNo.isEmpty() || verifier.mno().check(mNo) == false) writeToErrorPane("Error","Enter valid mobile number");
        else if(name.isEmpty()) writeToErrorPane("Error","Enter valid name");
        else if(pwd.isEmpty() || verifier.password().check(pwd) == false) writeToErrorPane("Error","Enter valid password");

        else if (roleName == null){
            writeToErrorPane("Error", "Choose a Role: Producer or Consumer.");
        }
        else if(roleName.equalsIgnoreCase("Consumer")) {
            synchronized (this) {
                ID = Main.godown.getConsumerID();
                writeToErrorPane("Message", "Account Created with UserID as " + ID + ". Please remember this ID and use it to login to Consumer Page");
            }
        }
        else{
            synchronized (this) {
                ID = Main.godown.getProducerID();
                writeToErrorPane("Message", "Account Created with UserID as " + ID + ". Please remember this ID and use it to login to Producer Page");
            }
        }

        if (roleName=="Producer") {
            Producer U = new Producer(ID, pwd);
            U.setMobile(mNo);
            U.setUserName(name);
            Main.godown.addProducer(U);
        }

        if (roleName=="Consumer") {
            Consumer U = new Consumer(ID, pwd);
            U.setMobile(mNo);
            U.setUserName(name);
            Main.godown.addConsumer(U);
        }
    }
    private boolean authenticate(String userName, String password) {
        if (userName.isEmpty() || password.isEmpty()) {
            return false ;
        }
        String role = userName.replaceAll("[0-9]", "");
        try {
            if (role.equalsIgnoreCase("PROD"))
                return Main.godown.checkProducerPassword(userName, password);
            else if (role.equalsIgnoreCase("CONS"))
                return Main.godown.checkConsumerPassword(userName, password);
        }catch (IndexOutOfBoundsException e1){
            System.out.println("Tried to access out of bounds index for either consumer or producer list in godown. Official error: " + e1);
            return false;
        }catch (NumberFormatException e2){
            System.out.println("Incorrect username that cannot be parsed by parseInt. Official error: " + e2);
            return false ;
        }
        return false ;
    }
    private void clearFields() {
        userNameFieldProducer.setText("");
        passwordFieldProducer.setText("");
        userNameFieldConsumer.setText("");
        passwordFieldConsumer.setText("");
        NameCA.setText("");
        passwordFieldCA.setText("");
        mobileCA.setText("");
    }
}
