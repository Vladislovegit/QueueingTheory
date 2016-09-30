package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.models.Buffer;
import sample.models.Generator;

public class Main extends Application {
    public TextField txtFldFirstChannelProbability;
    public TextField txtFldSecondChannelProbability;
    public Button btnCalculate;
    public TextField txtFldTactNumber;
    public Text txtQ;
    public Text txtL;
    QueuingSystem system = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_layout.fxml"));
        fxmlLoader.setController(this);
        primaryStage.setTitle("Queuing Theory");
        primaryStage.setScene(new Scene(fxmlLoader.load(), 330, 175));
        primaryStage.show();
        btnCalculate.setOnMouseClicked(event -> onBtnClick());
    }

    private void onBtnClick() {
        system = new QueuingSystem(
                Double.parseDouble(txtFldFirstChannelProbability.getText()),
                Double.parseDouble(txtFldSecondChannelProbability.getText())
        );
        Integer tactNumber = Integer.parseInt(txtFldTactNumber.getText());
        Generator.reset();
        Buffer.reset();
        for (int i = 0; i < tactNumber - 1; i++) {
            system.generateNextState(i);
        }
        txtL.setText(((Double) (system.getQueueLength() / (double) tactNumber )).toString());
        txtQ.setText(((Double) (system.getPacketsProcessed() / (double) Generator.getPacketsGenerated())).toString());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
