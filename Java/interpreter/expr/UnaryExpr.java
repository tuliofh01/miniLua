package interpreter.expr;

import java.util.Scanner;

import interpreter.util.Utils;
import interpreter.value.BooleanValue;
import interpreter.value.NumberValue;
import interpreter.value.StringValue;
import interpreter.value.TableValue;
import interpreter.value.Value;

public class UnaryExpr extends Expr {

    private Expr expr;
    private UnaryOp op;

    public UnaryExpr(int line, Expr expr, UnaryOp op) {
        super(line);
        this.expr = expr;
        this.op = op;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = expr != null ? expr.expr() : null;

        Value<?> ret = null;
        switch (op) {
            case Neg:
                ret = negOp(v);
                break;
            case Size:
                ret = sizeOp(v);
                break;
            case Not:
                ret = notOp(v);
                break;
            case ToNumber:
                ret = toNumberOp(v);
                break;
            case ToString:
                ret = toStringOp(v);
                break;
            case Read:
                ret = readOp(v);
                break;
            default:
                Utils.abort(super.getLine());
        }

        return ret;
    }

    private Value<?> negOp(Value<?> v) {
        Value<?> ret = null;
        if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            Double d = -nv.value();
            ret = new NumberValue(d);
        } else if (v instanceof StringValue) {
            StringValue sv = (StringValue) v;
            String tmp = sv.value();

            try {
                Double d = -Double.valueOf(tmp);
                ret = new NumberValue(d);
            } catch (Exception e) {
                Utils.abort(super.getLine());
            }
        } else {
            Utils.abort(super.getLine());
        }

        return ret;
    }

    private Value<?> notOp(Value<?> v) {
        boolean b = (v == null || !v.eval());
        BooleanValue bv = new BooleanValue(b);
        return bv;
    }

    private Value<?> sizeOp(Value<?> v){
        Value<?> ret = null;
        if (v instanceof StringValue){
            String tmp = v.toString();
            Double size = Double.valueOf(tmp.length());
            NumberValue nv = new NumberValue(size);
            ret = nv;
        }
        else if (v instanceof TableValue){
            TableValue tv = (TableValue) v;
            Double size = Double.valueOf(tv.value().size());
            NumberValue nv = new NumberValue(size);
            ret = nv;
        }
        else{
            Utils.abort(super.getLine());
        }
        return ret;
    }

    private Value<?> readOp(Value<?> v){
        if (v != null)
            System.out.print(v.toString());
        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine().trim();
        StringValue sv = new StringValue(tmp);
        //scanner.close();
        return sv;
    }

    private Value<?> toNumberOp(Value<?> v){
        NumberValue nv = null;
        if(v.value() instanceof String){
            String string = (String) v.value();
            nv = new NumberValue(Double.valueOf(string));
            
       }
       else {
            Utils.abort(super.getLine());
       } 
        return nv;
    }

    private Value<?> toStringOp(Value<?> v){
        StringValue nv = null;
        if(v.value() instanceof Double){
            Double number = (Double) v.value();
            String string = Double.toString(number);
            nv = new StringValue(string);
       }
       else {
            Utils.abort(super.getLine());
       } 
        return nv;
    }

}
