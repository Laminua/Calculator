public class Calculator {
    String result = "";
    int countForArabic = 0;
    int countForRoman = 0;
    String mathSing;
    String leftOperand;
    String rightOperand;
    int firstNum = 0;
    int secondNum = 0;

    public boolean isValid(String a) throws Exception {
        String[] mathSings = {"+", "-", "*", "/"};
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        if (a.length() < 3) {
            return false;
        }
        for (int i = 0; i < mathSings.length; i++) {
            if (a.contains(mathSings[i])) {
                mathSing = mathSings[i];
            }
        }
        if (mathSing == null) {
            throw new Exception("Отсутствует допустимый математический знак: +,-,*,/");
        }
        leftOperand = a.substring(0, a.indexOf(mathSing)).trim();
        rightOperand = a.substring(a.indexOf(mathSing) + 1, a.length()).trim();

        if (leftOperand == null || (rightOperand == null)) {
            throw new Exception("Строка не является математической операцией");
        }
        for (int i = 0; i < numbers.length; i++) {
            if (leftOperand.equals(numbers[i])) {
                this.countForArabic++;
                break;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (rightOperand.equals(numbers[i])) {
                this.countForArabic++;
                break;
            }
        }
        for (RomanNumbers rn : RomanNumbers.values()) {
            if (leftOperand.equals(rn.name())) {
                if (rn.getI() > 10) {
                    break;
                }
                this.countForRoman++;
                break;
            }
        }
        for (RomanNumbers rn : RomanNumbers.values()) {
            if (rightOperand.equals(rn.name())) {
                if (rn.getI() > 10) {
                    break;
                }
                this.countForRoman++;
                break;
            }
        }
        if ((countForRoman == 1) && (countForArabic == 1)) {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if ((countForRoman != 2) && (countForArabic != 2)) {
            throw new Exception("Введены недопустимые значения.");
        }
        return true;
    }

    public String calc(String input) throws Exception {
        if (isValid(input)) {
            if (countForArabic == 2) {
                arabicCalc(leftOperand, rightOperand, mathSing);
            } else if (countForRoman == 2) {
                romanCalc(leftOperand, rightOperand, mathSing);
            }
        } else {
            throw new Exception("Строка не является математической операцией");
        }
        return result;
    }

    public void arabicCalc(String a, String b, String c) {
        this.firstNum = Integer.parseInt(a);
        this.secondNum = Integer.parseInt(b);

        switch (c) {
            case "+":
                result = String.valueOf(firstNum + secondNum);
                return;
            case "-":
                result = String.valueOf(firstNum - secondNum);
                return;
            case "*":
                result = String.valueOf(firstNum * secondNum);
                return;
            case "/":
                result = String.valueOf(((int) firstNum / secondNum));
        }
    }

    public void romanCalc(String a, String b, String c) throws Exception {

        for (RomanNumbers rn : RomanNumbers.values()) {
            if (a.equals(rn.name())) {
                this.firstNum = rn.getI();
            }
        }
        for (RomanNumbers rn : RomanNumbers.values()) {
            if (b.equals(rn.name())) {
                this.secondNum = rn.getI();
            }
        }

        int resultInArabic = 0;
        switch (c) {
            case "+":
                resultInArabic = firstNum + secondNum;
                resultInRoman(resultInArabic);
                return;
            case "-":
                resultInArabic = firstNum - secondNum;
                resultInRoman(resultInArabic);
                return;
            case "*":
                resultInArabic = firstNum * secondNum;
                resultInRoman(resultInArabic);
                return;
            case "/":
                resultInArabic = (int) firstNum / secondNum;
                resultInRoman(resultInArabic);
        }
    }

    public void resultInRoman(int a) throws Exception {
        String resultArabic1 = "";
        String resultArabic2 = "";
        int resPart1 = 0;
        int resPart2 = 0;
        if (a < 1) {
            throw new Exception("Ошикба, в римской системе нет отрицательных чисел и нуля");
        }
        for (RomanNumbers rn : RomanNumbers.values()) {
            if (a == rn.getI()) {
                result = (rn.name());
            } else {
                resPart1 = (a / 10) * 10;
                resPart2 = a % 10;
            }
        }
        for (RomanNumbers rn : RomanNumbers.values()) {
            if (resPart1 == rn.getI()) {
                resultArabic1 = rn.name();
            } else if (resPart2 == rn.getI()) {
                resultArabic2 = rn.name();
            }
            result = resultArabic1 + resultArabic2;
        }
    }
}
