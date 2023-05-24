package exceptions;

abstract public class InvalidDiscountCode extends Exception{
    public InvalidDiscountCode(String subClassMassage) {
        super("InvalidDiscountCode" + " - " + subClassMassage);
    }
}
