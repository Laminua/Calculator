public class Math {
    public int calculations(int a, int b, String c) {
        switch (c) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return(calculations(a, b, c));
    }

}
