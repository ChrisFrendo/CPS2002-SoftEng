package utils;

// This was found on this stack overflow question:
// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

public enum Color {
    //utils.Color end string, color reset
    RESET("\033[0m"),

    RED("\033[0;31m"),      // RED
    MAGENTA("\033[0;35m"),  // MAGENTA
    YELLOW_BOLD("\033[1;33m"),  // YELLOW
    CYAN_BOLD_BRIGHT("\033[1;96m");     // CYAN

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}