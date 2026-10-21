public class DuplicateIdException extends Exception {
    private String duplicateId;
    public DuplicateIdException(String id) {
        super("Thiết bị " + id + "bị trùng");
        this.duplicateId = id;
    }

    public String getDuplicateId() {
        return this.duplicateId;
    }
}
