package interpreter.command;

import interpreter.expr.Expr;
import interpreter.expr.Variable;

public class NumericForCommand extends Command {

    private Variable var;

    private Expr expr1, expr2;
    private Expr expr3 = null; 

    private Command cmds;

    public NumericForCommand(int line, Expr expr1, Expr expr2, Expr expr3, Command cmds){
        super(line);
        this.expr1 = expr1; this.expr2 = expr2; this.expr3 = expr3;
        this.cmds = cmds;
    }

    @Override
    public void execute(){
    
    }

}
