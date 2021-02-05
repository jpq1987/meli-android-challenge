package ar.com.meliandroidchallenge.utils;

import java.text.NumberFormat;
import java.util.Locale;

import ar.com.meliandroidchallenge.service.model.Address;
import ar.com.meliandroidchallenge.service.model.Installments;
import ar.com.meliandroidchallenge.service.model.Seller;
import ar.com.meliandroidchallenge.service.model.SellerReputation;
import ar.com.meliandroidchallenge.service.model.Shipping;

import static ar.com.meliandroidchallenge.utils.Constants.LOCALE_AR;

public class StringUtils {

    public static String getCurrencySymbol(String currency) {
        String symbol = "$";
        switch (currency) {
            case "ARS":
                symbol = "$";
                break;
            case "USD":
                symbol = "u$D";
                break;
        }
        return symbol;
    }

    public static String getLabelForCondition(String condition){
        String label = "Sin especificar";
        switch (condition) {
            case "new":
                label = "Nuevo";
                break;
            case "used":
                label = "Usado";
                break;
            case "reconditioned":
                label = "Reacondicionado";
                break;
        }
        return label;
    }

    public static String formatNumberToLocale(double number, Locale locale) {
        NumberFormat format = NumberFormat.getNumberInstance(locale);
        return format.format(number);
    }

    public static String formatPriceWithLocalizedCurrency(double price, String currency, Locale locale){
        return getCurrencySymbol(currency) + " " + formatNumberToLocale(price, locale);
    }

    public static String getDiscountLabelBetweenPrices(double originalPrice, double currentPrice){
        double discountPercentage = originalPrice > 0 ? 100 * (originalPrice - currentPrice) / originalPrice : 0;
        return discountPercentage > 0 ? String.format("%.0f%% OFF" , discountPercentage) : "";
    }


    public static String getInstallmentsLabel(Installments installments) {
        String price = formatPriceWithLocalizedCurrency(installments.getAmount(), installments.getCurrencyID(), LOCALE_AR);
        String interest = installments.getRate() > 0 ? "" : "sin interés";
        return String.format("en %d cuotas de %s %s", installments.getQuantity(), price, interest);
    }

    public static String getShippingModeLabel(Shipping shipping) {
        String freeShipping = String.format("Llega gratis %s", (shipping.getMode().equals("fulfillment") ? "mañana" : "dentro de 15 días"));
        String nonFreeShipping = "Entrega a acordar con el vendedor";
        return shipping.isFreeShipping() ? freeShipping: nonFreeShipping;
    }

    public static String getDeliveryModeLabel(String mode) {
        String deliveryMode;
        switch(mode){
            case "me2":
                deliveryMode = "Retiro gratis en correos y otros puntos";
                break;
            case "custom":
            case "not_specified":
                deliveryMode = "Retiro gratis a acordar con el vendedor";
                break;
            default:
                deliveryMode = "Retiro a acordar con el vendedor";
                break;
        }
        return deliveryMode;
    }

    public static String getSellerNameLabel(long officialStore, Seller seller) {
        String nameExtracted = (seller.getPermalink().split("/",0)[3]).replace("+"," ");
        String typeStore = officialStore == 0 ? "Vendido por" : "Tienda oficial";
        return String.format("%s %s", typeStore, nameExtracted);
    }

    public static String getSellerAddress(Address address) {
        return String.format("%s, %s", address.getCityName(), address.getStateName());
    }

    public static String getTotalSalesLabel(SellerReputation sellerReputation) {
        return String.format("%d ventas", sellerReputation.getTransactions().getCompleted());
    }
}
