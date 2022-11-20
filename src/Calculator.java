public class Calculator {
    Math mt = new Math();
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
        for (String sing : mathSings) {
            if (a.contains(sing)) {
                mathSing = sing;
            }
        }
        if (mathSing == null) {
            throw new Exception("Отсутствует допустимый математический знак: +,-,*,/");
        }
        leftOperand = a.substring(0, a.indexOf(mathSing)).trim();
        rightOperand = a.substring(a.indexOf(mathSing) + 1).trim();

        if (leftOperand.equals("") || (rightOperand.equals(""))) {
            throw new Exception("Строка не является математической операцией");
        }
        for (String number : numbers) {
            if (leftOperand.equals(number)) {
                this.countForArabic++;
                break;
            }
        }
        for (String number : numbers) {
            if (rightOperand.equals(number)) {
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
                arabicCalc(leftOperand, rightOperand);
            } else if (countForRoman == 2) {
                romanCalc(leftOperand, rightOperand);
            }
        } else {
            throw new Exception("Строка не является математической операцией");
        }
        return result;
    }

    public void arabicCalc(String a, String b) {
        this.firstNum = Integer.parseInt(a);
        this.secondNum = Integer.parseInt(b);

        result = String.valueOf(mt.calculations(firstNum, secondNum, mathSing));
    }

    public void romanCalc(String a, String b) throws Exception {

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

        int resultInArabic;
        resultInArabic = mt.calculations(firstNum, secondNum, mathSing);
        resultInRoman(resultInArabic);
    }

    public void resultInRoman(int a) throws Exception {
        String resultArabic1 = "";
        String resultArabic2 = "";
        int resPart1 = 0;
        int resPart2 = 0;
        if (a < 1) {
            throw new Exception("Ошибка, в римской системе нет отрицательных чисел и нуля");
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
