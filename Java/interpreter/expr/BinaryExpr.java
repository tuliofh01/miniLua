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
            if(bv.eval() == false)
                return right.expr();
        }
        return v;
    }

    private Value<?> andOp(){
        Value<?> v = null;

        Boolean bv1 = right.expr().eval();
        Boolean bv2 = left.expr().eval();

        if(right.expr() == null || left.expr() == null)
            v = null;
        else if (right.expr() instanceof BooleanValue || left.expr() instanceof BooleanValue){
            if(bv1== true && bv2== true && right.expr() instanceof BooleanValue && left.expr() instanceof BooleanValue){
                BooleanValue fv = new BooleanValue(true);
                v = fv;
            }
            else if(bv1 == true && bv2 == true && (right.expr() instanceof BooleanValue) == false){
                v = right.expr();
            }
            else{
                BooleanValue fv = new BooleanValue(false);
                v = fv;
            }
        }
        else{
            v = right.expr();
        }     
        return v;
    }

    private Value<?> equalOp(){
        if(right.expr().value() == left.expr().value()){
            BooleanValue fv = new BooleanValue(true);
            return fv;
        }
        else{
            BooleanValue fv = new BooleanValue(false);
            return fv;
        }
    }

    private Value<?> notEqualOp(){
        if(right.expr().value() != left.expr().value()){
            BooleanValue fv = new BooleanValue(true);
            return fv;
        }
        else{
            BooleanValue fv = new BooleanValue(false);
            return fv;
        }
    }

}
