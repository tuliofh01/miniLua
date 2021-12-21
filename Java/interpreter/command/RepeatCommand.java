package interpreter.command;

import interpreter.expr.Expr;
import interpreter.value.Value;

public class RepeatCommand extends Command {

    private Expr expr;
    
    private Command cmds; 

    public RepeatCommand(int line, Expr expr, Command cmds){
        super(line);
        this.expr = expr;
        this.cmds = cmds;
    }

    @Override
    public void execute(){

        Value<?> v = expr.expr();

        do {
            cmds.execute();
        } while (v != null && v.eval());

    }

}
