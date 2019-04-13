package none.cvg.handles;

public class DemoClass {

    private String name;

    public DemoClass() {
        this.name = "No param DemoClass constructor";
    }

    public DemoClass(String name) {
        this.name = name;
    }

    /**
     * Public static method with a parameter
     *
     * @param input - String that will be embedded in the returned content
     * @return - A String as a successful execution of the method.
     */
    public static String publicStaticMethod(String input) {
        return "DemoClass.class - Public static method " + input;
    }

    public String printStuff(String input) {
        return "[" + this.name + "] - " + input;
    }

    /**
     * Public method with a parameter
     *
     * @param input - String that will be embedded in the returned content
     * @return - A String as a successful execution of the method.
     */
    public String publicMethod(String input) {
        return "[" + this.getClass().getSimpleName() + "] - Public method - " + input;
    }

    /**
     * Private method with a parameter
     *
     * @param input - String that will be embedded in the returned content
     * @return - A String as a successful execution of the method.
     */
    private String privateMethod(String input) {
        return "[" + this.getClass().getSimpleName() + "] - Private method " + input;
    }

    /**
     * Protected method with a parameter
     *
     * @param input - String that will be embedded in the returned content
     * @return - A String as a successful execution of the method.
     */
    protected String protectedMethod(String input) {
        return "[" + this.getClass().getSimpleName() + "] - Protected method " + input;
    }

    /**
     * Default (package-protected) method with a parameter
     *
     * @param input - String that will be embedded in the returned content
     * @return - A String as a successful execution of the method.
     */
    String packageProtectedMethod(String input) {
        return "[" + this.getClass().getSimpleName() + "] - Package protected method " + input;
    }
}
