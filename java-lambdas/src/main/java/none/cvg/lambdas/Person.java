package none.cvg.lambdas;

public class Person {

    public static final Person ALICE = new Person("Alice", "Smith");
    public static final Person BOB = new Person("Bob", "Jones");
    public static final Person CATHY = new Person("Cathy", "Williams");
    public static final Person DHRUV = new Person("Dhruv", "Patel");
    public static final Person EMILY = new Person("Emily", "Cruz");

    String firstName = "";
    String lastName = "";

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) return false;
        return lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
