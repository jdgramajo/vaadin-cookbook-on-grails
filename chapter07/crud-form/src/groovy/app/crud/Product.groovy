package app.crud

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class Product  {			
	@NotNull
	int code
	@Size(min=3, max=30)
	String name = ""
	@Min(0L)
	double price
	boolean salable

	@Override
	String toString() {		
		"[code: ${code}, name: ${name}, price: ${price}, salable: ${salable}]"
	}

}
