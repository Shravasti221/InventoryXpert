package com.labProject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.regex.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable{
    //for new UserIDs for producers and consumers
    static int refC = 0;
    static int refP = 0;

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
            errorLabelConsumer.setText("");
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
    void writeToErrorPane(String title, String s){
        errorPane.setText(title);
        errorPane.setVisible(true);
        allTabs.setDisable(true);
        errorPaneLabel.setText(s);
    }

    @FXML
    void createUserID(){
        String ID = "";
        String mNo = mobileCA.getText();
        String name = NameCA.getText();
        String pwd = passwordFieldCA.getText();
        String value = (String) typeCA.getValue();

        if(mNo.isEmpty() || isValidMobileNo(mNo)) writeToErrorPane("Error","Enter valid mobile number");
        else if(name.isEmpty()) writeToErrorPane("Error","Enter valid name");
        else if(pwd.isEmpty()) writeToErrorPane("Error","Enter valid password");

        else if (value == null){
            writeToErrorPane("Error", "Choose a Role: Producer or Consumer.");
        }
        else if(value.equalsIgnoreCase("Consumer")) {
            synchronized (this) {
                ID = "CONS" + this.refC++;
                writeToErrorPane("Message", "Account Created with UserID as " + ID + "Please remember this ID and use it to login to Consumer Page");
            }
        }
        else{
            synchronized (this) {
                ID = "PROD" + refP++;
                writeToErrorPane("Message", "Account Created with UserID as " + ID + "Please remember this ID and use it to login to Producer Page");
            }
        }
        User U = new User(ID, pwd);
        U.setMobile(mNo);
        U.setUserName(name);
    }

    private static boolean isValidMobileNo(String str)
    {
        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher match = ptrn.matcher(str);
        return (match.find() && match.group().equals(str));
    }

    private static boolean authenticate(String userName, String password) {
        if (userName.isEmpty() || password.isEmpty()) {
            return false ;
        }
        String role = userName.replaceAll("[0-9]", "");
        int index = Integer.parseInt(userName.replaceAll("[^0-9]", ""));
        try {
            if (role.equalsIgnoreCase("PROD"))
                return Main.godown.p.get(index).checkPassword(password);
            else if (role.equalsIgnoreCase("CONS"))
                return Main.godown.c.get(index).checkPassword(password);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Tried to access out of bounds index for either consumer or producer list in godown. Official error: " + e);
            return false;
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
