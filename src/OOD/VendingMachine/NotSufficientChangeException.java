package OOD.VendingMachine;

public class NotSufficientChangeException {
    private String message;

    public NotSufficientChangeException(String string) {
        this.message = string;
    }

    @Override public String getMessage(){
        return message;
    }
}
