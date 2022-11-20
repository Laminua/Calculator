public class Calculator {

    String result = "";

    public String calc(String input) throws Exception {
        Math mt = new Math();
        Validator vl = new Validator();
        Convertor convertor = new Convertor();
        int resultInArabic;
        if (vl.isValid(input)) {
            if (vl.getCountForArabic() == 2) {
                result = String.valueOf(mt.calculations(vl.getFirstNum(),
                        vl.getSecondNum(),
                        vl.getMathSign()));
            } else if (vl.getCountForRoman() == 2) {
                resultInArabic = mt.calculations(vl.getFirstNum(),
                        vl.getSecondNum(),
                        vl.getMathSign());
                result = convertor.resultInRoman(resultInArabic);
            }
        } else {
            throw new Exception("Строка не является математической операцией");
        }
        return result;
    }


}
