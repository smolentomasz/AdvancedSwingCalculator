
public class FunctionElementList {
    private String nameOfFuction;
    private String expressionOfFunction;

    public FunctionElementList(String nameOfFuction, String expressionOfFunction) {
        this.nameOfFuction = nameOfFuction;
        this.expressionOfFunction = expressionOfFunction;
    }
    public String toString(){
        return nameOfFuction;
    }
    public String getExpression(){
        return expressionOfFunction;
    }
}
