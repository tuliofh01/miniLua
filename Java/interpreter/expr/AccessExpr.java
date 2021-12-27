package interpreter.expr;

import interpreter.value.TableValue;
import interpreter.value.Value;

public class AccessExpr extends SetExpr{
    
    private Expr base;
    private Expr index;

    public AccessExpr(int line, Expr base, Expr index){
        super(line);
        this.base = base; this.index = index;
    }

    @Override
    public Value<?> expr() {
        TableValue baseVar = (TableValue) this.base.expr();
        Value<?> indexExpr = this.index.expr();
        Value<?> query = baseVar.value().get(indexExpr);
        return query; 
    }

    @Override
    public void setValue(Value<?> value){
        TableValue baseVar = (TableValue) this.base.expr();
        Value<?> indexExpr = this.index.expr();
        baseVar.value().put(indexExpr, value);
    }

}
