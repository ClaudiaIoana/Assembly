import ADT.*;
import Controller.Controller;
import Exception.MyException;
import Expression.*;
import Repository.IRepository;
import Repository.Repository;
import Statement.*;
import Type.BoolType;
import Type.IntType;
import Type.RefType;
import Type.StringType;
import Value.BoolValue;
import Value.IntValue;
import Value.StringValue;
import Value.Value;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;
import gui.Gui;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] v){
        System.out.println("What do you want to use? \n   1. console \n   2. giu \n\nOption: ");
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();
        if (num == 1){
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));

            MyIStack<Statement> stk1= new MyStack<>();
            MyIDictionary<String, Value> symtbl1 = new MyDictionary<>();
            MyIList<Value> out1 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft = new MyDictionary<>();
            MyIHeap h1 = new MyHeap();


            Statement ex1= new CompStmt(new VarDecl("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))));

            try{
                ex1.typecheck(new MyDictionary<>());
                PrgState prgState1 = new PrgState(stk1, symtbl1, out1, ft, h1, ex1, PrgState.getNumberThread());
                IRepository repo1 = new Repository(prgState1, "log1.txt");
                Controller ctrl1 = new Controller(repo1);
                menu.addCommand(new RunExample("1",ex1.toString(),ctrl1));
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            MyIStack<Statement> stk2= new MyStack<>();
            MyIDictionary<String, Value> symtbl2 = new MyDictionary<>();
            MyIList<Value> out2 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft2 = new MyDictionary<>();
            MyIHeap h2 = new MyHeap();


            Statement ex2 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'), '+')), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));

            try{
                ex2.typecheck(new MyDictionary<>());
                PrgState prgState2 = new PrgState(stk2, symtbl2, out2, ft2, h2, ex2, PrgState.getNumberThread());
                IRepository repo2 = new Repository(prgState2, "log2.txt");
                Controller ctrl2 = new Controller(repo2);
                menu.addCommand(new RunExample("2",ex2.toString(),ctrl2));
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            MyIStack<Statement> stk3= new MyStack<>();
            MyIDictionary<String, Value> symtbl3 = new MyDictionary<>();
            MyIList<Value> out3 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft3 = new MyDictionary<>();
            MyIHeap h3 = new MyHeap();

            Statement ex3 = new CompStmt(new VarDecl("a",new BoolType()), new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                            VarExp("v"))))));

            try{
                ex3.typecheck(new MyDictionary<>());
                PrgState prgState3 = new PrgState(stk3, symtbl3, out3, ft3,h3, ex3, PrgState.getNumberThread());
                IRepository repo3 = new Repository(prgState3, "log3.txt");
                Controller ctrl3 = new Controller(repo3);
                menu.addCommand(new RunExample("3",ex3.toString(),ctrl3));
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            MyIStack<Statement> stk4= new MyStack<>();
            MyIDictionary<String, Value> symtbl4 = new MyDictionary<>();
            MyIList<Value> out4 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft4 = new MyDictionary<>();
            MyIHeap h4 = new MyHeap();

            Statement ex4 = new CompStmt(new VarDecl("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                    new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDecl("varc", new IntType()), new CompStmt(
                            new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(
                            new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(
                            new VarExp("varf"))))))))));

            try{
                ex4.typecheck(new MyDictionary<>());
                PrgState prgState4 = new PrgState(stk4, symtbl4, out4, ft4,h4, ex4, PrgState.getNumberThread());
                IRepository repo4 = new Repository(prgState4, "log4.txt");
                Controller ctrl4 = new Controller(repo4);
                menu.addCommand(new RunExample("4",ex4.toString(),ctrl4));
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            MyIStack<Statement> stk5= new MyStack<>();
            MyIDictionary<String, Value> symtbl5 = new MyDictionary<>();
            MyIList<Value> out5 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft5 = new MyDictionary<>();
            MyIHeap h5 = new MyHeap();


            Statement ex5 = new CompStmt(new VarDecl("a",new BoolType()), new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new RelationalExp( new ValueExp(new IntValue(2)), new ValueExp(new IntValue(3)), 1),
                            new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                            new PrintStmt(new VarExp("v"))))));

            try{
                ex5.typecheck(new MyDictionary<>());
                PrgState prgState5 = new PrgState(stk5, symtbl5, out5, ft5, h5, ex5, PrgState.getNumberThread());
                IRepository repo5 = new Repository(prgState5, "log5.txt");
                Controller ctrl5 = new Controller(repo5);
                menu.addCommand(new RunExample("5",ex5.toString(),ctrl5));
            }catch (MyException e){
                System.out.println(e.getMessage());
            }



            MyIStack<Statement> stk6= new MyStack<>();
            MyIDictionary<String, Value> symtbl6 = new MyDictionary<>();
            MyIList<Value> out6 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft6 = new MyDictionary<>();
            MyIHeap h6 = new MyHeap();


            Statement ex6 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

            try{
                ex6.typecheck(new MyDictionary<>());
                PrgState prgState6 = new PrgState(stk6, symtbl6, out6, ft6, h6, ex6, PrgState.getNumberThread());
                IRepository repo6 = new Repository(prgState6, "log6.txt");
                Controller ctrl6 = new Controller(repo6);
                menu.addCommand(new RunExample("6",ex6.toString(),ctrl6));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }




            MyIStack<Statement> stk7= new MyStack<>();
            MyIDictionary<String, Value> symtbl7 = new MyDictionary<>();
            MyIList<Value> out7 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft7 = new MyDictionary<>();
            MyIHeap h7 = new MyHeap();


            Statement ex7 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new PrintStmt(new ReadHeap(new VarExp("v"))),
                                                    new PrintStmt(new ArithExp(new ReadHeap(new ReadHeap(new VarExp("a"))), new ValueExp(new IntValue(5)), '+')))))));


            try{
                ex7.typecheck(new MyDictionary<>());
                PrgState prgState7 = new PrgState(stk7, symtbl7, out7, ft7, h7, ex7, PrgState.getNumberThread());
                IRepository repo7 = new Repository(prgState7, "log7.txt");
                Controller ctrl7 = new Controller(repo7);

                menu.addCommand(new RunExample("7",ex7.toString(),ctrl7));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            MyIStack<Statement> stk8= new MyStack<>();
            MyIDictionary<String, Value> symtbl8 = new MyDictionary<>();
            MyIList<Value> out8 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft8 = new MyDictionary<>();
            MyIHeap h8 = new MyHeap();


            Statement ex8 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new PrintStmt(new ReadHeap(new VarExp("v"))),
                                    new CompStmt(new HeapWriting("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ArithExp(new ReadHeap(new VarExp("v")), new ValueExp(new IntValue(5)), '+'))))));

            try{
                ex8.typecheck(new MyDictionary<>());
                PrgState prgState8 = new PrgState(stk8, symtbl8, out8, ft8, h8, ex8, PrgState.getNumberThread());
                IRepository repo8 = new Repository(prgState8, "log8.txt");
                Controller ctrl8 = new Controller(repo8);
                menu.addCommand(new RunExample("8",ex8.toString(),ctrl8));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            MyIStack<Statement> stk9= new MyStack<>();
            MyIDictionary<String, Value> symtbl9 = new MyDictionary<>();
            MyIList<Value> out9 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft9 = new MyDictionary<>();
            MyIHeap h9 = new MyHeap();


            Statement ex9 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                    new CompStmt(new NewStmt("a", new VarExp("v")),
                                            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                    new PrintStmt(new ReadHeap(new ReadHeap(new VarExp("a")))))))));


            try{
                ex9.typecheck(new MyDictionary<>());
                PrgState prgState9 = new PrgState(stk9, symtbl9, out9, ft9, h9, ex9, PrgState.getNumberThread());
                IRepository repo9 = new Repository(prgState9, "log9.txt");
                Controller ctrl9 = new Controller(repo9);
                menu.addCommand(new RunExample("9",ex9.toString(),ctrl9));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }



            MyIStack<Statement> stk10= new MyStack<>();
            MyIDictionary<String, Value> symtbl10 = new MyDictionary<>();
            MyIList<Value> out10 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft10 = new MyDictionary<>();
            MyIHeap h10 = new MyHeap();


            Statement ex10 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new VarDecl("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v",
                    new ValueExp(new IntValue(10))), new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))), new CompStmt(
                    new ForkStmt(new CompStmt(new HeapWriting("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(
                            new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeap(new VarExp("a"))))))),
                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeap(new VarExp("a")))))))));


            try{
                ex10.typecheck(new MyDictionary<>());
                PrgState prgState10 = new PrgState(stk10, symtbl10, out10, ft10, h10, ex10, PrgState.getNumberThread());
                IRepository repo10 = new Repository(prgState10, "log10.txt");
                Controller ctrl10  = new Controller(repo10);
                menu.addCommand(new RunExample("10",ex10.toString(),ctrl10));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }

            //THE FOR STATEMENT
            MyIStack<Statement> stk11= new MyStack<>();
            MyIDictionary<String, Value> symtbl11 = new MyDictionary<>();
            MyIList<Value> out11 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft11 = new MyDictionary<>();
            MyIHeap h11 = new MyHeap();


            Statement ex11 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new ForStmt("v", new ValueExp(new IntValue(0)), new ValueExp(new IntValue(3)),
                            new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '+'),
                            new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"),
                                    new ValueExp(new IntValue(1)), '+'))))), new PrintStmt(new ArithExp(new VarExp("v"),
                            new ValueExp(new IntValue(10)), '*')))));



            try{
                ex11.typecheck(new MyDictionary<>());
                PrgState prgState11 = new PrgState(stk11, symtbl11, out11, ft11, h11, ex11, PrgState.getNumberThread());
                IRepository repo11 = new Repository(prgState11, "log11.txt");
                Controller ctrl11  = new Controller(repo11);
                menu.addCommand(new RunExample("11",ex11.toString(),ctrl11));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            //THE SLEEP
            MyIStack<Statement> stk12= new MyStack<>();
            MyIDictionary<String, Value> symtbl12 = new MyDictionary<>();
            MyIList<Value> out12 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft12 = new MyDictionary<>();
            MyIHeap h12 = new MyHeap();


            Statement ex12 =  new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                    new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), 1),
                            new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"),
                                    new ValueExp(new IntValue(1)), '+')))), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), '+')))),
                            new CompStmt(new Sleep(5), new PrintStmt(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), '*'))))));



            try{
                ex12.typecheck(new MyDictionary<>());
                PrgState prgState12 = new PrgState(stk12, symtbl12, out12, ft12, h12, ex12, PrgState.getNumberThread());
                IRepository repo12 = new Repository(prgState12, "log12.txt");
                Controller ctrl12  = new Controller(repo12);
                menu.addCommand(new RunExample("12",ex12.toString(),ctrl12));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            //SWITCH
            MyIStack<Statement> stk13= new MyStack<>();
            MyIDictionary<String, Value> symtbl13 = new MyDictionary<>();
            MyIList<Value> out13 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft13 = new MyDictionary<>();
            MyIHeap h13 = new MyHeap();


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

            try{
                ex13.typecheck(new MyDictionary<>());
                PrgState prgState13 = new PrgState(stk13, symtbl13, out13, ft13, h13, ex13, PrgState.getNumberThread());
                IRepository repo13 = new Repository(prgState13, "log13.txt");
                Controller ctrl13  = new Controller(repo13);
                menu.addCommand(new RunExample("13",ex13.toString(),ctrl13));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }

            //THE WAIT
            MyIStack<Statement> stk14= new MyStack<>();
            MyIDictionary<String, Value> symtbl14 = new MyDictionary<>();
            MyIList<Value> out14 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft14 = new MyDictionary<>();
            MyIHeap h14 = new MyHeap();


            Statement ex14 = new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(20))),
                            new CompStmt(new Wait(10),
                                    new PrintStmt(new ArithExp( new VarExp("v"), new ValueExp(new IntValue(10)),'*')))));


            try{
                ex14.typecheck(new MyDictionary<>());
                PrgState prgState14 = new PrgState(stk14, symtbl14, out14, ft14, h14, ex14, PrgState.getNumberThread());
                IRepository repo14 = new Repository(prgState14, "log14.txt");
                Controller ctrl14  = new Controller(repo14);
                menu.addCommand(new RunExample("14",ex14.toString(),ctrl14));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }

            //DO WHILE
            MyIStack<Statement> stk15= new MyStack<>();
            MyIDictionary<String, Value> symtbl15 = new MyDictionary<>();
            MyIList<Value> out15 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft15 = new MyDictionary<>();
            MyIHeap h15 = new MyHeap();


            Statement ex15 =new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(0))),
                            new CompStmt(new DoWhile(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(3)), 4),
                                    new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                            new AssignStmt("v", new ArithExp( new VarExp("v"), new ValueExp(new IntValue(1)),'-')))),
                                            new AssignStmt("v", new ArithExp( new VarExp("v"), new ValueExp(new IntValue(1)), '+')))

                            ),
                                    new CompStmt(new VarDecl("x", new IntType()),
                                            new CompStmt(new VarDecl("y", new IntType()),
                                                    new CompStmt(new VarDecl("z", new IntType()),
                                                            new CompStmt(new VarDecl("w", new IntType()),
                                                                    new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                                                            new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(2))),
                                                                                    new CompStmt(new AssignStmt("z", new ValueExp(new IntValue(3))),
                                                                                            new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(4))),
                                                                                                    new PrintStmt(new ArithExp( new VarExp("v"), new ValueExp(new IntValue(10)),'*')))))))))))));
            
            try{
                ex15.typecheck(new MyDictionary<>());
                PrgState prgState15 = new PrgState(stk15, symtbl15, out15, ft15, h15, ex15, PrgState.getNumberThread());
                IRepository repo15 = new Repository(prgState15, "log15.txt");
                Controller ctrl15  = new Controller(repo15);
                menu.addCommand(new RunExample("15",ex15.toString(),ctrl15));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            //  CONDITIONAL ASSIGN
            MyIStack<Statement> stk16= new MyStack<>();
            MyIDictionary<String, Value> symtbl16 = new MyDictionary<>();
            MyIList<Value> out16 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft16 = new MyDictionary<>();
            MyIHeap h16 = new MyHeap();


            Statement ex16 = new CompStmt(new VarDecl("b", new BoolType()), new CompStmt(new VarDecl("c", new IntType()),
                    new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))), new CompStmt(new CondAssign("c", new VarExp("b"),
                            new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new CompStmt(new PrintStmt(new VarExp("c")),
                            new CompStmt(new CondAssign("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),
                                    new PrintStmt(new VarExp("c"))))))));

            try{
                ex16.typecheck(new MyDictionary<>());
                PrgState prgState16 = new PrgState(stk16, symtbl16, out16, ft16, h16, ex16, PrgState.getNumberThread());
                IRepository repo16 = new Repository(prgState16, "log16.txt");
                Controller ctrl16  = new Controller(repo16);
                menu.addCommand(new RunExample("16",ex16.toString(),ctrl16));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            //MUL EXPRESSION
            MyIStack<Statement> stk17= new MyStack<>();
            MyIDictionary<String, Value> symtbl17 = new MyDictionary<>();
            MyIList<Value> out17 = new MyList<>();
            MyIDictionary<StringValue, BufferedReader> ft17 = new MyDictionary<>();
            MyIHeap h17 = new MyHeap();


            Statement ex17 =  new CompStmt(new VarDecl("v1", new IntType()), new CompStmt(new VarDecl("v2", new IntType()),
                    new CompStmt(new AssignStmt("v1", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("v2", new ValueExp(new IntValue(3))),
                            new IfStmt(new RelationalExp(new VarExp("v1"), new ValueExp(new IntValue(0)), 4), new PrintStmt(new MulExp(new VarExp("v1"),
                                    new VarExp("v2"))), new PrintStmt(new VarExp("v1")))))));

            try{
                ex17.typecheck(new MyDictionary<>());
                PrgState prgState17 = new PrgState(stk17, symtbl17, out17, ft17, h17, ex17, PrgState.getNumberThread());
                IRepository repo17 = new Repository(prgState17, "log17.txt");
                Controller ctrl17  = new Controller(repo17);
                menu.addCommand(new RunExample("17",ex17.toString(),ctrl17));//other prg
            }catch (MyException e){
                System.out.println(e.getMessage());
            }


            menu.show();
        }
        else if (num == 2){
            List<Statement> st = new ArrayList<Statement>();
            Statement ex1= new CompStmt(new VarDecl("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))));

            Statement ex2 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)), new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), '*'), '+')), new CompStmt(new AssignStmt("b", new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), '+')), new PrintStmt(new VarExp("b"))))));

            Statement ex3 = new CompStmt(new VarDecl("a",new BoolType()), new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                            VarExp("v"))))));

            Statement ex4 = new CompStmt(new VarDecl("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                    new CompStmt(new OpenRFile(new VarExp("varf")), new CompStmt(new VarDecl("varc", new IntType()), new CompStmt(
                            new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(
                            new ReadFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(
                            new VarExp("varf"))))))))));

            Statement ex5 = new CompStmt(new VarDecl("a",new BoolType()), new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new RelationalExp( new ValueExp(new IntValue(2)), new ValueExp(new IntValue(3)), 1),
                            new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
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
            st.add(ex12);
            st.add(ex11);
            st.add(ex13);
            st.add(ex14);
            st.add(ex15);
            st.add(ex16);
            st.add(ex17);

            Gui.main_gui(v, st);
        }

    }
}

