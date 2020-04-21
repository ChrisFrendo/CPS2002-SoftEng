package utils;

// This was found on this stack overflow question:
// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

public enum Color {
    //utils.Color end string, color reset
    RESET("\033[0m"),

    // Regular Colors. Normal color, no bold, background color etc.
    WHITE("\033[0;30m"),    // WHITE
    RED("\033[0;31m"),      // RED
    GREEN("\033[0;32m"),    // GREEN
    YELLOW("\033[0;33m"),   // YELLOW
    BLUE("\033[0;34m"),     // BLUE
    MAGENTA("\033[0;35m"),  // MAGENTA
    CYAN("\033[0;36m"),     // CYAN
    BLACK("\033[0;37m"),    // BLACK

    // Bold
    WHITE_BOLD("\033[1;30m"),   // WHITE
    RED_BOLD("\033[1;31m"),     // RED
    GREEN_BOLD("\033[1;32m"),   // GREEN
    YELLOW_BOLD("\033[1;33m"),  // YELLOW
    BLUE_BOLD("\033[1;34m"),    // BLUE
    MAGENTA_BOLD("\033[1;35m"), // MAGENTA
    CYAN_BOLD("\033[1;36m"),    // CYAN
    BLACK_BOLD("\033[1;37m"),   // BLACK

    // Underline
    WHITE_UNDERLINED("\033[4;30m"),     // WHITE
    RED_UNDERLINED("\033[4;31m"),       // RED
    GREEN_UNDERLINED("\033[4;32m"),     // GREEN
    YELLOW_UNDERLINED("\033[4;33m"),    // YELLOW
    BLUE_UNDERLINED("\033[4;34m"),      // BLUE
    MAGENTA_UNDERLINED("\033[4;35m"),   // MAGENTA
    CYAN_UNDERLINED("\033[4;36m"),      // CYAN
    BLACK_UNDERLINED("\033[4;37m"),     // BLACK

    // Background
    WHITE_BACKGROUND("\033[40m"),   // WHITE
    RED_BACKGROUND("\033[41m"),     // RED
    GREEN_BACKGROUND("\033[42m"),   // GREEN
    YELLOW_BACKGROUND("\033[43m"),  // YELLOW
    BLUE_BACKGROUND("\033[44m"),    // BLUE
    MAGENTA_BACKGROUND("\033[45m"), // MAGENTA
    CYAN_BACKGROUND("\033[46m"),    // CYAN
    BLACK_BACKGROUND("\033[47m"),   // BLACK

    // High Intensity
    WHITE_BRIGHT("\033[0;90m"),     // WHITE
    RED_BRIGHT("\033[0;91m"),       // RED
    GREEN_BRIGHT("\033[0;92m"),     // GREEN
    YELLOW_BRIGHT("\033[0;93m"),    // YELLOW
    BLUE_BRIGHT("\033[0;94m"),      // BLUE
    MAGENTA_BRIGHT("\033[0;95m"),   // MAGENTA
    CYAN_BRIGHT("\033[0;96m"),      // CYAN
    BLACK_BRIGHT("\033[0;97m"),     // BLACK

    // Bold High Intensity
    WHITE_BOLD_BRIGHT("\033[1;90m"),    // WHITE
    RED_BOLD_BRIGHT("\033[1;91m"),      // RED
    GREEN_BOLD_BRIGHT("\033[1;92m"),    // GREEN
    YELLOW_BOLD_BRIGHT("\033[1;93m"),   // YELLOW
    BLUE_BOLD_BRIGHT("\033[1;94m"),     // BLUE
    MAGENTA_BOLD_BRIGHT("\033[1;95m"),  // MAGENTA
    CYAN_BOLD_BRIGHT("\033[1;96m"),     // CYAN
    BLACK_BOLD_BRIGHT("\033[1;97m"),    // BLACK

    // High Intensity backgrounds
    WHITE_BACKGROUND_BRIGHT("\033[0;100m"),     // WHITE
    RED_BACKGROUND_BRIGHT("\033[0;101m"),       // RED
    GREEN_BACKGROUND_BRIGHT("\033[0;102m"),     // GREEN
    YELLOW_BACKGROUND_BRIGHT("\033[0;103m"),    // YELLOW
    BLUE_BACKGROUND_BRIGHT("\033[0;104m"),      // BLUE
    MAGENTA_BACKGROUND_BRIGHT("\033[0;105m"),   // MAGENTA
    CYAN_BACKGROUND_BRIGHT("\033[0;106m"),      // CYAN
    BLACK_BACKGROUND_BRIGHT("\033[0;107m");     // BLACK

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}