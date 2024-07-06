package ik.ijse.HardwareSystem.controller;

import ik.ijse.HardwareSystem.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import ik.ijse.HardwareSystem.dao.impl.UserDAOimpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignInFormController implements Initializable {

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
UserDAOimpl userDAOimpl = new UserDAOimpl();
    @FXML
    void btnSignInOnAction(ActionEvent event) {
        String username = txtName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();



        try {
            boolean isSaved = userDAOimpl.save(String.valueOf(new User(username,email,password)));
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"User Saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    public void clearFields(){
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
    }
}


