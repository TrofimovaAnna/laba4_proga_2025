package exceptions;

public class FairException extends Exception{
    public FairException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return "Нарушены правила ярмарки: " + super.getMessage();
    }
}
