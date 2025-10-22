package com.store.mcs.document.generator.utils;

public class StringUtils {

    private static final String[] UNITS = {
            "", "un", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho",
            "nueve"
    };
    private static final String[] TEENS = {
            "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis",
            "diecisiete", "dieciocho", "diecinueve"
    };
    private static final String[] TENS = {
            "", "", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta",
            "setenta", "ochenta", "noventa"
    };
    private static final String[] HUNDREDS = {
            "", "ciento", "doscientos", "trescientos", "cuatrocientos",
            "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
    };
    private static final String[] THOUSANDS = {
            "", "mil", "millón", "millones", "mil millones", "billón"
    };

    public static String convertAmountToWords(String value) {
        int number = Integer.valueOf(value);
        if (number == 0) {
            return "cero";
        }
        StringBuilder words = new StringBuilder();
        int thousandsIndex = 0;
        while (number > 0) {
            if (number % 1000 != 0) {
                if (words.length() > 0) {
                    words.insert(0, " ");
                }
                words.insert(0, convertChunkToWords(number % 1000,
                        thousandsIndex) + " " + THOUSANDS[thousandsIndex]);
            }
            number /= 1000;thousandsIndex++;
        }
        return replaceMillonPlural((words.toString().trim()));
    }
    private static String convertChunkToWords(int chunk, int thousandsIndex) {
        StringBuilder chunkWords = new StringBuilder();
        if (chunk == 100) {
            return "cien";
        }
        if (chunk > 100) {
            chunkWords.append(convertHundreds(chunk, thousandsIndex));
            chunk %= 100;
            if (chunk > 0) {
                chunkWords.append(" ");
            }
        }
        if (chunk >= 20) {
            chunkWords.append(convertTens(chunk));
            chunk %= 10;
            if (chunk > 0) {
                chunkWords.append(" y ");
            }
        }
        if (chunk > 0) {
            chunkWords.append(convertRemaining(chunk, thousandsIndex));
        }
        return chunkWords.toString().replace("veinte y ", "veinti");
    }
    private static String convertHundreds(int chunk, int thousandsIndex) {
        return HUNDREDS[chunk / 100];
    }
    private static String convertTens(int chunk) {
        return TENS[chunk / 10];
    }
    private static String convertRemaining(int chunk, int thousandsIndex) {
        if (chunk < 10) {
            return convertUnits(chunk);
        } else {
            if (thousandsIndex == 1 && chunk == 1) {
                return "uno";
            } else {
                return convertTeens(chunk);
            }}
    }
    private static String convertUnits(int chunk) {
        return UNITS[chunk];
    }
    private static String convertTeens(int chunk) {
        return TEENS[chunk - 10];
    }
    private static String replaceMillonPlural(String words) {
        String singular = THOUSANDS[2];
        String plural = THOUSANDS[3];
        if (!words.contains("un millón")) {
            words = words.replace(singular, plural);
        }
        return words;
    }

}
