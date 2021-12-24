package interpreter.expr;

import interpreter.util.Utils;
import interpreter.value.BooleanValue;
import interpreter.value.NumberValue;
import interpreter.value.StringValue;
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
                v = equalOp();
                break;
            case NotEqualOp:
                v = notEqualOp();
                break;
            case LowerThanOp:
                v = lowerThanOp();
                break;
            case LowerEqualOp:
                v = lowerEqualOp();
                break;
            case GreaterThanOp:
                v = greaterThanOp();
                break;
            case GreaterEqualOp:
                v = greaterEqualOp();
                break;
            case ConcatOp:
                v = concatOp();
                break;
            case AddOp:
                v = addOp();
                break;
            case SubOp:
                v = subOp();
                break;
            case MulOp:
                v = mulOp();
                break;
            case DivOp:
                v = divOp();
                break;
            case ModOp:
                v = modOp();
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

    private Value<?> lowerThanOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            // Compara 2 números
            NumberValue nvl = new NumberValue((Double) left.expr().value());
            NumberValue nvr = new NumberValue((Double) right.expr().value());
            if(nvl.value() < nvr.value()){
                BooleanValue fv = new BooleanValue(true);
                return fv;
            }
            else {
                BooleanValue fv = new BooleanValue(false);
                return fv;
            }
        }
        else{
            return null;
        }
    }

    private Value<?> lowerEqualOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            // Compara 2 números
            NumberValue nvl = new NumberValue((Double) left.expr().value());
            NumberValue nvr = new NumberValue((Double) right.expr().value());
            if(nvl.value() <= nvr.value()){
                BooleanValue fv = new BooleanValue(true);
                return fv;
            }
            else {
                BooleanValue fv = new BooleanValue(false);
                return fv;
            }
        }
        else{
            return null;
        }
    }

    private Value<?> greaterThanOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            // Compara 2 números
            NumberValue nvl = new NumberValue((Double) left.expr().value());
            NumberValue nvr = new NumberValue((Double) right.expr().value());
            if(nvl.value() > nvr.value()){
                BooleanValue fv = new BooleanValue(true);
                return fv;
            }
            else {
                BooleanValue fv = new BooleanValue(false);
                return fv;
            }
        }
        else{
            return null;
        }
    }

    private Value<?> greaterEqualOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            // Compara 2 números
            NumberValue nvl = new NumberValue((Double) left.expr().value());
            NumberValue nvr = new NumberValue((Double) right.expr().value());
            if(nvl.value() >= nvr.value()){
                BooleanValue fv = new BooleanValue(true);
                return fv;
            }
            else {
                BooleanValue fv = new BooleanValue(false);
                return fv;
            }
        }
        else{
            return null;
        }
    }

    private Value<?> concatOp(){
        if (left.expr().value() instanceof StringValue && right.expr().value() instanceof StringValue){
            StringValue nv = new StringValue(((String) left.expr().value()) + ((String) right.expr().value()));
            return nv;
        }
        else{
            return null;
        }
    }

    private Value<?> addOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            NumberValue nv = new NumberValue(((Double) left.expr().value()) + ((Double) right.expr().value()));
            return nv;
        }
        else{
            return null;
        }
    }

    private Value<?> subOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            NumberValue nv = new NumberValue(((Double) left.expr().value()) - ((Double) right.expr().value()));
            return nv;
        }
        else{
            return null;
        }
    }
    
    private Value<?> mulOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            NumberValue nv = new NumberValue(((Double) left.expr().value()) * ((Double) right.expr().value()));
            return nv;
        }
        else{
            return null;
        }
    }

    private Value<?> divOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            NumberValue nv = new NumberValue(((Double) left.expr().value()) / ((Double) right.expr().value()));
            return nv;
        }
        else{
            return null;
        }
    }

    private Value<?> modOp(){
        if (left.expr().value() instanceof NumberValue && right.expr().value() instanceof NumberValue){
            NumberValue nv = new NumberValue(((Double) left.expr().value()) % ((Double) right.expr().value()));
            return nv;
        }
        else{
            return null;
        }
    }

}
