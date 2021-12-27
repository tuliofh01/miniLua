package interpreter.command;

import interpreter.expr.ConstExpr;
import interpreter.expr.Expr;
import interpreter.expr.Variable;
import interpreter.value.NumberValue;

public class NumericForCommand extends Command {

    private Variable var1;
    private Expr expr1, expr2, expr3;
    private Command cmds;

    public NumericForCommand(int line, Variable var1, Expr expr1, Expr expr2, Expr expr3, Command cmds) {
        super(line);
        this.var1 = var1;
        this.expr1 = expr1; this.expr2 = expr2;
        if(expr3 == null){
            this.expr3 = new ConstExpr(line, new NumberValue(1.0));
        }
        else {
            this.expr3 = expr3;
        }
        this.cmds = cmds;
    }

    @Override
    public void execute() {
        Double startValue = (Double) expr1.expr().value();
        Double finalValue = (Double) expr2.expr().value();

        for(Double i = startValue; Double.compare(i, finalValue) <= 0 ; i += (Double) expr3.expr().value()){
            NumberValue value = new NumberValue(i); this.var1.setValue(value);
            cmds.execute();
        }
    }
}