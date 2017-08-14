package vn.fpt.util.number;

import java.text.DecimalFormat;

public class NumberToWords {
	private static final String[] ENGLISH_TENS_NAMES = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty", " ninety" };
	private static final String[] VIETNAMES_TENS_NAMES = { "", " mười", " hai mươi", " ba mươi", " bốn mươi", " năm mươi", " sáu mươi", " bảy mươi", " tám mươi", " chín mươi" };

	private static final String[] ENGLISH_NUM_NAMES = { "", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen" };
	private static final String[] VIETNAMESE_NUM_NAMES = { "", " một", " hai", " ba", " bốn", " năm", " sáu", " bảy", " tám", " chín", " mười", " mười một", " mười hai", " mười ba", " mười bốn", " mười lăm", " mười sáu", " mười bảy", " mười tám", " mười chín" };

	private static final String ENGLISH_HUNDRED = " hundred", VIETNAMESE_HUNDRED = " trăm";
	private static final String ENGLISH_THOUSAND = " thousand", VIETNAMESE_THOUSAND = " nghìn";
	private static final String ENGLISH_MILLION = " million", VIETNAMESE_MILLION = " triệu";
	private static final String ENGLISH_BILLION = " billion", VIETNAMESE_BILLION = " tỉ";

	private String[] tensNames;
	private String[] numNames;
	private String hundred, thousand, million, billion;
	
	public NumberToWords(String lang) {
		if ("vi".equalsIgnoreCase(lang)) {
			tensNames = VIETNAMES_TENS_NAMES;
			numNames = VIETNAMESE_NUM_NAMES;
			hundred = VIETNAMESE_HUNDRED;
			thousand = VIETNAMESE_THOUSAND;
			million = VIETNAMESE_MILLION;
			billion = VIETNAMESE_BILLION;
		} else {
			tensNames = ENGLISH_TENS_NAMES;
			numNames = ENGLISH_NUM_NAMES;
			hundred = ENGLISH_HUNDRED;
			thousand = ENGLISH_THOUSAND;
			million = ENGLISH_MILLION;
			billion = ENGLISH_BILLION;
		}
	}

	private String convertLessThanOneThousand(int number) {
		String soFar;

		if (number % 100 < 20) {
			soFar = numNames[number % 100];
			number /= 100;
		} else {
			soFar = numNames[number % 10];
			number /= 10;

			soFar = tensNames[number % 10] + soFar;
			number /= 10;
		}
		if (number == 0)
			return soFar;
		return numNames[number] + hundred + soFar;
	}

	public String convert(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "zero";
		}

		String snumber = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);

		// XXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(0, 3));
		// nnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(3, 6));
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(9, 12));

		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + billion + " ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + billion + " ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + million + " ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + million + " ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = numNames[1] + thousand + " ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + thousand + " ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}
	
	public static void main(String args[]) throws Exception {
		long num = 4922078;
		System.out.println(new NumberToWords("vi_VN").convert(num));
		System.out.println(new NumberToWords("en_US").convert(num));
	}
}
