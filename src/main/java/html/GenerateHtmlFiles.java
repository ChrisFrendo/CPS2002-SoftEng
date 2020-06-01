package html;

import utils.FileHelperUtils;

/**
 * Singleton class used to generate the required directors and copy the necessary images, styles, fonts into that directory
 */
public class GenerateHtmlFiles {

    /**
     * Singleton instance
     */
    private static GenerateHtmlFiles generateHtmlFiles = null;

    private GenerateHtmlFiles() {

    }

    /**
     * Method used to get the singleton instance
     *
     * @return singleton instance of GenerateHtmlFiles
     */
    public static GenerateHtmlFiles getInstance() {
        if (generateHtmlFiles == null)
            generateHtmlFiles = new GenerateHtmlFiles();

        return generateHtmlFiles;
    }

    /**
     * Exposed function used to generate a directory with the given file name and content
     *
     * @param content        The content of the html file to generate
     * @param fileName       The name of the html file to generate
     * @param baseFolderName The name of the directory to place the files in
     */
    public void generateHtmlFile(String content, String fileName, String baseFolderName) {
        makeDirectories(baseFolderName);
        copyFiles(baseFolderName);
        createHtmlFile(fileName, content, baseFolderName);
    }

    /**
     * Helper function used to create the required directories
     *
     * @param baseFolderName The name of the root directory to place the other directories in
     */
    private void makeDirectories(String baseFolderName) {
        FileHelperUtils.mkDir(baseFolderName);
        FileHelperUtils.mkDir(baseFolderName + "/images");
        FileHelperUtils.mkDir(baseFolderName + "/images/tiles");
        FileHelperUtils.mkDir(baseFolderName + "/title-font");
    }

    /**
     * Helper function used to copy files from resources folder to required folder
     *
     * @param baseFolderName The name of the root directory to place the files in
     */
    private void copyFiles(String baseFolderName) {
        FileHelperUtils.copyFile("resources/images/sky-bg.jpg", baseFolderName + "/images/sky-bg.jpg");
        FileHelperUtils.copyFile("resources/images/tiles/GrassTile.png", baseFolderName + "/images/tiles/GrassTile.png");
        FileHelperUtils.copyFile("resources/images/tiles/WaterTile.png", baseFolderName + "/images/tiles/WaterTile.png");
        FileHelperUtils.copyFile("resources/images/tiles/TreasureTile.png", baseFolderName + "/images/tiles/TreasureTile.png");
        FileHelperUtils.copyFile("resources/images/tiles/HiddenTile.png", baseFolderName + "/images/tiles/HiddenTile.png");
        FileHelperUtils.copyFile("resources/images/tiles/GrassTilePlayer.png", baseFolderName + "/images/tiles/GrassTilePlayer.png");
        FileHelperUtils.copyFile("resources/styles.css", baseFolderName + "/styles.css");
        FileHelperUtils.copyFile("resources/title-font/04b_30__-webfont.woff", baseFolderName + "/title-font/04b_30__-webfont.woff");
        FileHelperUtils.copyFile("resources/title-font/04b_30__-webfont.woff2", baseFolderName + "/title-font/04b_30__-webfont.woff2");
    }

    /**
     * Helper function used to write content to a file
     *
     * @param filename       The name of the file to write content to
     * @param html           The content to write to the file
     * @param baseFolderName The root directory where the file is found
     */
    private static void createHtmlFile(String filename, String html, String baseFolderName) {
        String filepath = baseFolderName + "/" + filename;
        FileHelperUtils.writeToFile(filepath, html);
    }
}
