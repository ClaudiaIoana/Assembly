package gui.controller;

import Controller.Controller;
import Exception.MyException;
import Expression.*;
import Repository.*;
import Statement.*;
import Type.*;
import Value.*;

import ADT.MyDictionary;
import ADT.PrgState;
import Controller.Controller;
import Expression.*;
import Type.*;
import Exception.MyException;
import Repository.Repository;
import Statement.Statement;
import Value.BoolValue;
import Value.IntValue;
import Value.StringValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Select_Window_Controller implements Initializable {
    @FXML
    private ListView<Statement> programs;
    @FXML
    private Button selectBttn;
    private Execution_Controller execController;

    @FXML
    private Statement selectExample(ActionEvent actionEvent) {
        return programs.getItems().get(programs.getSelectionModel().getSelectedIndex());
    }

    public void add_programs(List<Statement> states){
        programs.getItems().addAll(states);
    }


    public Execution_Controller getExecController() {
        return execController;
    }

    public void setExecController(Execution_Controller mainWindowController) {
        this.execController = mainWindowController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        displayExamples();
    }

    private List<Statement> initExamples() {
        List<Statement> st = new ArrayList<Statement>();
        Statement ex1 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                new PrintStmt(new VarExp("v"))));

        Statement ex2 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'), '+')), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));

        Statement ex3 = new CompStmt(new VarDecl("a", new BoolType()), new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                        VarExp("v"))))));

        Statement ex4 = new CompStmt(new VarDecl("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDecl("varc", new IntType()), new CompStmt(
                        new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(
                        new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(
                        new VarExp("varf"))))))))));

        Statement ex5 = new CompStmt(new VarDecl("a", new BoolType()), new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new RelationalExp(new ValueExp(new IntValue(2)), new ValueExp(new IntValue(3)), 1),
                        new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                        new PrintStmt(new VarExp("v"))))));

        Statement ex6 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        Statement ex7 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeap(new VarExp("v"))),
                                                new PrintStmt(new ArithExp(new ReadHeap(new ReadHeap(new VarExp("a"))), new ValueExp(new IntValue(5)), '+')))))));

        Statement ex8 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new ReadHeap(new VarExp("v"))),
                                new CompStmt(new HeapWriting("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp(new ReadHeap(new VarExp("v")), new ValueExp(new IntValue(5)), '+'))))));

        Statement ex9 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ReadHeap(new ReadHeap(new VarExp("a")))))))));

        Statement ex10 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new VarDecl("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v",
                new ValueExp(new IntValue(10))), new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))), new CompStmt(
                new ForkStmt(new CompStmt(new HeapWriting("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(
                        new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeap(new VarExp("a"))))))),
                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeap(new VarExp("a")))))))));

        Statement ex11 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new ForStmt("v", new ValueExp(new IntValue(0)), new ValueExp(new IntValue(3)),
                        new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '+'),
                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"),
                                new ValueExp(new IntValue(1)), '+'))))), new PrintStmt(new ArithExp(new VarExp("v"),
                        new ValueExp(new IntValue(10)), '*')))));

        Statement ex12 =  new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), 1),
                        new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"),
                                new ValueExp(new IntValue(1)), '+')))), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '+')))),
                        new CompStmt(new Sleep(5), new PrintStmt(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), '*'))))));

        Statement ex13 = new CompStmt(new VarDecl("a", new IntType()),
                new CompStmt(new VarDecl("b", new IntType()),
                        new CompStmt(new VarDecl("c", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))),
                                                        new CompStmt(new Switch(
                                                                new ArithExp(new VarExp("a"), new ValueExp(new IntValue(10)), '*'),
                                                                new ArithExp( new VarExp("b"), new VarExp("c"), '*'),
                                                                new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                                new ValueExp(new IntValue(10)),
                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))),
                                                                new PrintStmt(new ValueExp(new IntValue(300)))
                                                        ), new PrintStmt(new ValueExp(new IntValue(300))))))))));


        Statement ex14 = new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new Wait(10),
                                new PrintStmt(new ArithExp( new VarExp("v"), new ValueExp(new IntValue(10)),'*')))));

        Statement ex15 =new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                        new CompStmt(new DoWhile(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), 4),
                                new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp( new VarExp("v"), new ValueExp(new IntValue(1)),'-')))),
                                        new AssignStmt("v", new ArithExp( new VarExp("v"), new ValueExp(new IntValue(1)), '+')))

                        ), new CompStmt(new VarDecl("x", new IntType()), new CompStmt(new VarDecl("y", new IntType()),
                                new CompStmt(new VarDecl("z", new IntType()), new CompStmt(new VarDecl("w", new IntType()),new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                        new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("z", new ValueExp(new IntValue(3))),
                                                new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(4))), new PrintStmt(new ArithExp( new VarExp("v"), new ValueExp(new IntValue(10)),'*')))))))))))));

        Statement ex16 = new CompStmt(new VarDecl("b", new BoolType()), new CompStmt(new VarDecl("c", new IntType()),
                new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))), new CompStmt(new CondAssign("c", new VarExp("b"),
                        new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new CompStmt(new PrintStmt(new VarExp("c")),
                        new CompStmt(new CondAssign("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),
                                new PrintStmt(new VarExp("c"))))))));

        Statement ex17 =  new CompStmt(new VarDecl("v1", new IntType()), new CompStmt(new VarDecl("v2", new IntType()),
                new CompStmt(new AssignStmt("v1", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("v2", new ValueExp(new IntValue(3))),
                        new IfStmt(new RelationalExp(new VarExp("v1"), new ValueExp(new IntValue(0)), 4), new PrintStmt(new MulExp(new VarExp("v1"),
                                new VarExp("v2"))), new PrintStmt(new VarExp("v1")))))));


        st.add(ex1);
        st.add(ex2);
        st.add(ex3);
        st.add(ex4);
        st.add(ex5);
        st.add(ex6);
        st.add(ex7);
        st.add(ex8);
        st.add(ex9);
        st.add(ex10);
        st.add(ex11);
        st.add(ex12);
        st.add(ex13);
        st.add(ex14);
        st.add(ex15);
        st.add(ex16);
        st.add(ex17);

        return st;
    }

    private void displayExamples(){
        List<Statement> examples = initExamples();
        programs.setItems(FXCollections.observableArrayList(examples));
        programs.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                int index = programs.getSelectionModel().getSelectedIndex();
                Statement selectedProgram = programs.getItems().get(index);
                index++;
                PrgState programState = new PrgState(selectedProgram);
                IRepository repository = new Repository(programState,"log" + index + ".txt");
                Controller controller = new Controller(repository);
                //controller.addProgram(programState);
                try {
                    selectedProgram.typecheck(new MyDictionary<String, Type>());
                    execController.setController(controller);
                } catch (MyException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                    alert.show();
                }

            }
        });
    }
}
