package gui;

import Controller.Controller;
import Statement.Statement;
import gui.controller.Execution_Controller;
import gui.controller.Select_Window_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Gui extends Application {
    static List<Statement> st = new ArrayList<Statement>();

    public static void main_gui(String[] args, List<Statement> sti) {
        st = sti;
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("resources/execution.fxml"));
        Parent mainWindow = mainLoader.load();

        Execution_Controller mainWindowController = mainLoader.getController();
        Image icon = new Image("img.png");
        stage.getIcons().add(icon);

        stage.setTitle("Main Window");
        stage.setScene(new Scene(mainWindow, 1000, 800));
        stage.show();


        FXMLLoader secondaryLoader = new FXMLLoader();
        secondaryLoader.setLocation(getClass().getResource("resources/select_window.fxml"));
        Parent secondaryWindow = secondaryLoader.load();
        Select_Window_Controller selectWindowController = secondaryLoader.getController();
        selectWindowController.setExecController(mainWindowController);
        selectWindowController.add_programs(st);

        Stage secondaryStage = new Stage();
        secondaryStage.getIcons().add(icon);
        secondaryStage.setTitle("Select Window");
        secondaryStage.setScene(new Scene(secondaryWindow, 600, 650));
        secondaryStage.show();

/*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/select_window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Image icon = new Image("img.png");
        stage.getIcons().add(icon);
        stage.setTitle("Main Window");
        stage.setScene(scene);
        stage.show();

        Select_Window_Controller contr = fxmlLoader.getController();
        contr.add_programs(st);

        FXMLLoader secondaryLoader = new FXMLLoader();
        secondaryLoader.setLocation(getClass().getResource("resources/execution.fxml"));
        Parent root2 = secondaryLoader.load();
        Execution_Controller execution_controller = secondaryLoader.getController();
        execution_controller.setController(contr);

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Select Window");
        secondaryStage.setScene(new Scene(secondaryWindow, 600, 650));
        secondaryStage.show();*/
    }
}
