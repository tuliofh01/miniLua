package interpreter.command;

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
        // FIXME: Implementar o resto => (fazer loop para iterar pelos vetores). Atualmente so pega o primeiro rhs e lhs.
        Expr right = rhs.get(0);
        
        // nome var
        Value<?> v = right.expr();

        SetExpr left = lhs.get(0);
        // conteudo var
        left.setValue(v);
    }

}
