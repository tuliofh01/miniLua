package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.BooleanValue;
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
                break;
            case OrOp:
                v = orOp();
                break;
            case EqualOp:
                break;
            case NotEqualOp:
                break;
            case LowerThanOp:
                break;
            case LowerEqualOp:
                break;
            case GreaterThanOp:
                break;
            case GreaterEqualOp:
                break;
            case ConcatOp:
                break;
            case AddOp:
                break;
            case SubOp:
                break;
            case MulOp:
                break;
            case DivOp:
                break;
            case ModOp:
                break;
            default:
                Utils.abort(super.getLine());
        }

        return v;
    }
    
    // Não recebe parametros
    private Value<?> orOp() {
        Value<?> v = left.expr();
        if (v == null)
            return right.expr();
        else if (v instanceof BooleanValue){
            BooleanValue bv = (BooleanValue) v;
            if(bv.value() == false)
                return right.expr();
        }
        return v;
    }

}
