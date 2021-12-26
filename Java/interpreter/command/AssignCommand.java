package interpreter.command;

import java.util.ArrayList;
import java.util.Vector;

import interpreter.expr.Expr;
import interpreter.expr.SetExpr;
import interpreter.value.Value;

public class AssignCommand extends Command {

    private Vector<SetExpr> lhs;
    private Vector<Expr> rhs;

    public AssignCommand(int line, Vector<SetExpr> lhs, Vector<Expr> rhs) {
        super(line);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void execute() {
        ArrayList<Value<?>> list = new ArrayList<Value<?>>();
        for (int index = 0; index < lhs.size(); index++) {
            Expr right = rhs.get(index);
            list.add(right.expr());
            SetExpr left = lhs.get(index);
            left.setValue(list.get(index));
        }
    }

}
