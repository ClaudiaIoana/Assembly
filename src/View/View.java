package View;

import ADT.PrgState;
import Controller.Controller;
import Exception.MyException;

public class View {
    Controller controller;

    public View(Controller c){
        this.controller = c;
    }

    public void inputPrg(PrgState prg){
    }

    public void executeProgram() throws MyException{
        this.controller.allStep();
    }
}
