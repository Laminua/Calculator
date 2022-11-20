public class Convertor {
    String result;

    public String resultInRoman(int a) throws Exception {
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
        return result;
    }
}
