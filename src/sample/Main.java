package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public TextField txtFldFirstChannelProbability;
    public TextField txtFldSecondChannelProbability;
    public Button btnCalculate;
    public TextField txtFldTactNumber;
    public Text txtQ;
    public Text txtL;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_layout.fxml"));
        fxmlLoader.setController(this);
        primaryStage.setTitle("Simulation Model");
        primaryStage.setScene(new Scene(fxmlLoader.load(), 330, 175));
        primaryStage.show();

        btnCalculate.setOnMouseClicked(event ->  {
                QueuingSystem system = new QueuingSystem(
                        Double.parseDouble(txtFldFirstChannelProbability.getText()),
                        Double.parseDouble(txtFldSecondChannelProbability.getText())
                );
                Integer tactNumber = Integer.parseInt(txtFldTactNumber.getText());
                for (int i = 0; i < tactNumber; i++) {
                    system.generateNextState(i);
                }
                txtL.setText(((Double) (system.getQueueLength() / (double) tactNumber)).toString());
                txtQ.setText(((Double) (1 - system.getDenials() / (double) tactNumber)).toString());
            }
        );
    }


    public static void main(String[] args) {
        launch(args);
    }
}
