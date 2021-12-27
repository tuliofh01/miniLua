package interpreter.command;

import java.util.Map.Entry;

import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.util.Utils;
import interpreter.value.TableValue;
import interpreter.value.Value;

public class GenericForCommand extends Command {

    private Variable var1, var2;
    private Expr expr;
    private Command cmds;

    public GenericForCommand(int line, Variable var1, Variable var2, Expr expr, Command cmds) {
        super(line);
        this.var1 = var1; this.var2 = var2;
        this.expr = expr; this.cmds = cmds;
    }

    @Override
    public void execute() {
        if(!(expr.expr() instanceof TableValue)){
            Utils.abort(super.getLine());
        }
        else{
            TableValue table = (TableValue) expr.expr();
            if(var2 != null){
                for(Entry<Value<?>,Value<?>> pair: table.value().entrySet()){
                    var1.setValue(pair.getKey());
                    var2.setValue(pair.getValue());
                    cmds.execute();
                }
            }
            else{
                for(Value<?> key : table.value().keySet()){
                    var1.setValue(key);
                    cmds.execute();
                }
            }
        }
    }
}