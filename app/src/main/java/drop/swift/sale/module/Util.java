package drop.swift.sale.module;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Util {

    public static String currencyFormatting(int price) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        format.setCurrency(Currency.getInstance("IDR"));
        return format.format(price);
    }

}
