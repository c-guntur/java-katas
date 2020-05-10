package none.cvg.lambdas;

public class IntegerPair {

    public static String getDesctiption() {
        return "Integer Pair Holder";
    }

    int first;
    int second;

    public IntegerPair() {
        this(1, 6);
    }

    public IntegerPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegerPair that = (IntegerPair) o;

        if (first != that.first) return false;
        return second == that.second;
    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        return result;
    }

    @Override
    public String toString() {
        return "IntegerPair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
