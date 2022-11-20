public class Validator {
    int countForArabic = 0;
    int countForRoman = 0;
    String mathSign;
    String leftOperand;
    String rightOperand;
    int firstNum = 0;
    int secondNum = 0;

    public boolean isValid(String a) throws Exception {
        String[] mathSings = {"+", "-", "*", "/"}; // Допустимые математические знаки
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; // Допустимые арабские цифры

        if (a.length() < 3) { // Если в строке меньше 3-х символов, то это точно не мат. операция
            return false;
        }
        for (String sign : mathSings) { // Проверяем строку на наличие допустимых мат. знаков
            if (a.contains(sign)) {
                mathSign = sign;
            }
        }
        if (mathSign == null) {
            throw new Exception("Отсутствует допустимый математический знак: +,-,*,/");
        }
        leftOperand = a.substring(0, a.indexOf(mathSign)).trim();
        rightOperand = a.substring(a.indexOf(mathSign) + 1).trim();

        if (leftOperand.equals("") || (rightOperand.equals(""))) {
            throw new Exception("Строка не является математической операцией");
        }
        for (String number : numbers) {
            if (leftOperand.equals(number)) {
                this.countForArabic++;
                this.firstNum = Integer.parseInt(leftOperand);
            }
            if (rightOperand.equals(number)) {
                this.countForArabic++;
                this.secondNum = Integer.parseInt(rightOperand);
            }
        }
        for (RomanNumbers rn : RomanNumbers.values()) {
            if (leftOperand.equals(rn.name())) {
                if (rn.getI() > 10) {
                    break;
                }
                this.countForRoman++;
                this.firstNum = rn.getI();
            }
            if (rightOperand.equals(rn.name())) {
                if (rn.getI() > 10) {
                    break;
                }
                this.countForRoman++;
                this.secondNum = rn.getI();
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

    public int getCountForArabic() {
        return countForArabic;
    }

    public int getCountForRoman() {
        return countForRoman;
    }

    public String getMathSign() {
        return mathSign;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }
}
