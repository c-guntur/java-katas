package none.cvg.handles;

public enum ErrorMessages {

    REFLECTION_FAILURE("Reflection Test Failed. Exception message: "),
    UNSAFE_FAILURE("Unsafe Test Failed. Exception message: "),
    TEST_FAILURE("Test Failure - Fix all TODOs. Exception message: ");

    private String value;

    ErrorMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
