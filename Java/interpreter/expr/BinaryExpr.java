package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.Value;

public class BinaryExpr extends Expr{

    private Expr left;
    private Expr right;

    private BinaryOp op;


    public BinaryExpr(int line, Expr left, BinaryOp op, Expr right){
        super(line);
        this.left = left; this.right = right; this.op = op;
    }

    @Override
    public Value<?> expr(){
        Value<?> v = null;

        switch (op){
            
            case AndOp:
            
            case OrOp:
            
            case EqualOp:
            
            case NotEqualOp:
            
            case LowerThanOp:
            
            case LowerEqualOp:
            
            case GreaterThanOp:
            
            case GreaterEqualOp:
            
            case ConcatOp:
            
            case AddOp:
            
            case SubOp:
            
            case MulOp:
            
            case DivOp:
            
            case ModOp:

            default:
                Utils.abort(super.getLine());
        }

        return v;
    }
    
}
