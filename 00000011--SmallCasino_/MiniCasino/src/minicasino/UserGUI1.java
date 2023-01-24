/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minicasino;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author juwuanturner_howard
 */
public class UserGUI1 implements Initializable {

  @FXML
  private Button mainMenuButton;

  @FXML
  public Label username = new Label();

  @FXML
  public Label currency = new Label();

  @FXML
  private void mainMenuSwitch(ActionEvent event) throws IOException //this to switch
  {
    username.setText("Made by the guys...");
    currency.setText("... and Debug Master Rachel");

    Stage stage;
    Parent root;
    stage = (Stage) mainMenuButton.getScene().getWindow();
    root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.setMaximized(true);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    username.setText(User.getUser().getName());
    currency.setText(Integer.toString(User.getUser().getCurrency()));
  }

}
