package interpreter.command;

import interpreter.expr.Expr;

public class IfCommand extends Command {

    private Expr expr;
    
    // Proc code no sintático
    private Command thenCmds;
    
    // Proc code no sintático
    private Command elseCmds;

    public IfCommand(int line, Expr expr, Command thenCmds){
        super(line);
        this.expr = expr;
        this.thenCmds = thenCmds;
    }

    public void setElseCommands(Command elseCmds){
        this.elseCmds = elseCmds;
    }

    @Override
    public void execute(){

        Value<?> v = expr.expr();

        if(v.eval() == true){
            thenCmds.execute();
        }

        if(elseCmds != null){
            elseCmds.execute();
        }

    }

}
