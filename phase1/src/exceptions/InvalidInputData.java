package exceptions;

abstract public class InvalidInputData extends Exception{
    public InvalidInputData(String subClassMassage) {
        super("InvalidInputData" + " - " + subClassMassage);
    }
}
