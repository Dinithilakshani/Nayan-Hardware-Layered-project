package ik.ijse.HardwareSystem.controller;

import com.jfoenix.controls.JFXButton;
import ik.ijse.HardwareSystem.dao.impl.UserDAOimpl;
import ik.ijse.HardwareSystem.util.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LoginFormController {

    public VBox mainPane;
    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private AnchorPane rootNode;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String password = txtPassword.getText();
        String userName = txtUserName.getText();


        if (userName.equals("") || password.equals("") ) {
            new Alert(Alert.AlertType.CONFIRMATION, "Enter your password & user name");

        }else {

            System.out.println("usename"+userName);
            UserDAOimpl userDAOimpl = new UserDAOimpl();
            boolean rst = userDAOimpl.userCheck(userName, password);

            if (rst == true) {

                Navigation navigation = new Navigation();
                navigation.NewWindowsNavigation("Dashboard.fxml");

                Stage window = (Stage) mainPane.getScene().getWindow();
                window.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong Details..!").show();
            }
        }
    }




    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }


    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {
        Parent rootNode = (Parent) FXMLLoader.load(this.getClass().getResource("/view/signin.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign In Form");
        stage.show();

    }
}

