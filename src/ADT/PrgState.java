package ADT;

import Exception.*;
import Statement.*;
import Value.*;

import java.io.BufferedReader;

public class PrgState {
    private MyIStack<Statement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private MyIList<Value> out;
    private Statement originalProgram;
    private MyIHeap heap;
    int id;
    public static int numberThreads = 0;

    public PrgState(MyIStack<Statement> exeS, MyIDictionary<String, Value> symT, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> file, MyIHeap hea, Statement pro, int i){
        exeStack = exeS;
        symTable = symT;
        out = ot;
        heap = hea;
        fileTable = file;
        originalProgram = pro;
        exeStack.push(pro);
        id = i;
    }

    static public int getNumberThread (){return ++numberThreads;}

    public PrgState(Statement originalProgram){
        this.exeStack = new MyStack<Statement>();
        this.symTable = new MyDictionary<String,Value>();
        this.out = new MyList<Value>();
        this.fileTable = new MyDictionary<StringValue,BufferedReader>();
        this.heap = new MyHeap();
        this.id = this.getId();
        this.exeStack.push(originalProgram);
    }


    public int getId(){return id;}



    public String toString(){
        StringBuilder str = new StringBuilder();
        for(StringValue s: fileTable.getKeys())
            str.append("   "+ s + "\n");
        return "State id : " + id + "\nExeStack: " + exeStack.toString()+ "\n" +
                "SymTable: " + symTable.toString() + "\n" +
                "Output: " + out.toString() + "\n" +
                "Heap: " + heap.toString() + "\n" +
                "File table:" + str.toString() + "\n\n";
    }

    public MyIStack<Statement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<Statement> stack) {
        this.exeStack = stack;
    }

    public MyIDictionary<String, Value> getSymTable(){
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> table){
        this.symTable = table;
    }

    public MyIList<Value> getOut(){
        return out;
    }

    public void setOut(MyIList<Value> ot){
        this.out = ot;
    }
    public MyIDictionary<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> file){
        this.fileTable = file;
    }

    public MyIHeap getHeap(){
        return heap;
    }

    public void setHeap(MyIHeap h){
        this.heap = h;
    }

    public Boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) {
            throw new MyException("prgstate stack is empty");
        }
        Statement crtStmt = exeStack.pop();
        return crtStmt.execute(this);

    }
}
