package View;

import Controller.Controller;
import Exception.MyException;

public class RunExample extends Command{
    private Controller ctr;

    public RunExample(String key, String description, Controller con) {
        super(key, description);
        this.ctr = con;
    }

    @Override
    public void execute() {
        try{
            ctr.allStep();}
        catch (MyException e){
            System.out.println(e.getMessage());
        }
    }
}
