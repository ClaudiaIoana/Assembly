package gui.controller;

import ADT.*;
import Controller.Controller;
import Statement.Statement;
import Value.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Pair;
import Exception.MyException;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Execution_Controller implements Initializable {
    @FXML
    private TableView<Map.Entry<String, Value>> symbolTableView;
    @FXML
    private Label progStatesCount;
    @FXML
    private TableColumn<Map.Entry<String, Value>, String> symTableNames;
    @FXML
    private TableColumn<Map.Entry<String, Value>, String> symTableValues;
    @FXML
    private ListView<String> fileTableView;
    @FXML
    private ListView<String> outputView;
    @FXML
    private TableView<Map.Entry<Integer,Value>> heapTableView;
    @FXML
    private TableColumn <Map.Entry<Integer, Value>, String> heapValues;
    @FXML
    private TableColumn<Map.Entry<Integer,Value>,Integer> heapAddr;
    @FXML
    private Button execButton;
    @FXML
    private ListView<String> exeStackView;
    @FXML
    private ListView<Integer> progIdentifiersView;
    private Controller controller;

    public Controller getController(){
        return controller;
    }

    public void setController(Controller controller){
        this.controller = controller;
        populateProgStatesCount();
        populateIdentifiersView();
        execButton.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.controller = null;
        this.heapAddr.setCellValueFactory((p) -> {
            return (new SimpleIntegerProperty((Integer)((Map.Entry)p.getValue()).getKey())).asObject();
        });
        this.heapValues.setCellValueFactory((p) -> {
            return new SimpleStringProperty(String.valueOf(((Map.Entry)p.getValue()).getValue()) + " ");
        });
        this.symTableNames.setCellValueFactory((p) -> {
            return new SimpleStringProperty((String)((Map.Entry)p.getValue()).getKey() + " ");
        });
        this.symTableValues.setCellValueFactory((p) -> {
            return new SimpleStringProperty(String.valueOf(((Map.Entry)p.getValue()).getValue()) + " ");
        });
        this.progIdentifiersView.setOnMouseClicked((mouseEvent) -> {
            this.chageProgramStateHandler(this.getSelectedProgramState());
        });
        this.execButton.setDisable(true);
    }

    private void chageProgramStateHandler(PrgState currectProg){
        if(currectProg == null)
            return;
        try{
            populateProgStatesCount();
            populateIdentifiersView();
            populateHeapTableView(currectProg);
            populateOutputView(currectProg);
            populateFileTableView(currectProg);
            populateExeStackView(currectProg);
            populateSymTableView(currectProg);
        }catch (MyException e){
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
            error.show();
        }
    }

    public void oneStepHandler(ActionEvent actionEvent){
        if(controller == null){
            Alert error = new Alert(Alert.AlertType.ERROR, "No program was selected.");
            error.show();
            execButton.setDisable(true);
            return;
        }
        PrgState prgState = getSelectedProgramState();
        if(prgState != null && !prgState.isNotCompleted()) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Nothing left tot execute! ");
            error.show();
            return;
        }
        try{
            controller.oneStepAll();
            chageProgramStateHandler(prgState);
            if(controller.getRepo().getPrgList().size() == 0)
                execButton.setDisable(true);
        }catch (MyException e){
            Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
            error.show();
            execButton.setDisable(true);
        }
    }

    private void populateProgStatesCount(){
        progStatesCount.setText("No of Program States:" + controller.getRepo().getPrgList().size());
    }

    private void populateIdentifiersView(){
        progIdentifiersView.setItems(FXCollections.observableArrayList(controller.getRepo().getPrgList().stream().map(PrgState::getId).collect(Collectors.toList())));
        progIdentifiersView.refresh();
    }

    private void populateHeapTableView(PrgState givenPrg){
        heapTableView.setItems(FXCollections.observableList(new ArrayList<>(givenPrg.getHeap().getContent().entrySet())));
        heapTableView.refresh();
    }

    private void populateOutputView(PrgState givenPrg) throws MyException{
        outputView.setItems(FXCollections.observableArrayList(givenPrg.getOut().getContent()));
    }

    private void populateFileTableView(PrgState givenPrg){
        fileTableView.setItems(FXCollections.observableArrayList());
    }

    private void populateExeStackView(PrgState givenPrg){
        MyIStack<Statement> stack = givenPrg.getExeStack();
        List<String> stackOutput = new ArrayList<>();
        for(Object stm: stack.getValues()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        exeStackView.setItems(FXCollections.observableArrayList(stackOutput));
    }

    private void populateSymTableView(PrgState givenPrg){
        symbolTableView.setItems(FXCollections.observableList(new ArrayList<>(givenPrg.getSymTable().getContent().entrySet())));
        symbolTableView.refresh();
    }

    private PrgState getSelectedProgramState(){
        if(progIdentifiersView.getSelectionModel().getSelectedIndex()==-1)
            return null;
        int id = progIdentifiersView.getSelectionModel().getSelectedItem();
        return controller.getRepo().getProgramWithId(id);
    }
}
