package OOD.VendingMachine;

public class SoldOutException {
    private String message;

    public SoldOutException(String string) {
        this.message = string;
    }

    @Override public String getMessage(){
        return message;
    }
}
