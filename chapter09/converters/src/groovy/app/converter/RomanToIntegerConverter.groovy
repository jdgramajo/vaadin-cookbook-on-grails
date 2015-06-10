package app.converter

import com.vaadin.data.util.converter.Converter

@SuppressWarnings("serial")
public class RomanToIntegerConverter implements Converter<String, Integer> {

	private def romanChars = [:]	
	private def subtractedValues = [:]
	
	private def romanNumerals = [:]

	public RomanToIntegerConverter() {
		romanChars.put('M', 1000)
		romanChars.put('D', 500)
		romanChars.put('C', 100)
		romanChars.put('L', 50)
		romanChars.put('X', 10)
		romanChars.put('V', 5)
		romanChars.put('I', 1)

		subtractedValues.put('IV', 2)
		subtractedValues.put('IX', 2)
		subtractedValues.put('XL', 20)
		subtractedValues.put('XC', 20)
		subtractedValues.put('CD', 200)
		subtractedValues.put('CM', 200)
		
		romanNumerals.put('M', 1000)
		romanNumerals.put('CM', 900)
		romanNumerals.put('D', 500)
		romanNumerals.put('CD', 400)
		romanNumerals.put('C', 100)
		romanNumerals.put('XC', 90)
		romanNumerals.put('L', 50)
		romanNumerals.put('XL', 40)
		romanNumerals.put('X', 10)
		romanNumerals.put('IX', 9)
		romanNumerals.put('V', 5)
		romanNumerals.put('IV', 4)
		romanNumerals.put('I', 1)
	}

	@Override
	public Integer convertToModel(String romanInput, Class<? extends Integer> target, Locale locale) throws ConversionException {
		int intOutput = 0		
		
		romanInput.length().times { x ->
			Integer integer = romanChars.(romanInput.charAt(x)) as Integer
			if (integer == null) {
				throw new ConversionException()
			}
			intOutput += integer
		}

		romanInput = romanInput.toUpperCase()
		subtractedValues.keySet().each { substract ->
			if (romanInput.contains(substract)) {
				intOutput -= subtractedValues.get(substract)
			}
		}

		return intOutput
	}

	@Override
	public String convertToPresentation(Integer intInput, Class<? extends String> target, Locale locale) throws ConversionException {
		String romanOutput = ''		
		romanNumerals.keySet().each { romanKey ->
			Integer romanValue = romanNumerals.get(romanKey as String) as Integer
			while (intInput >= romanValue) {
				intInput -= romanValue?:0
				romanOutput += romanKey
			}
		}
		return romanOutput
	}

	@Override
	public Class<Integer> getModelType() {
		return Integer.class
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class
	}
	
}
