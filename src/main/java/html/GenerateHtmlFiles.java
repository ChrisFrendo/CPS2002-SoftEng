package html;

import utils.FileHelperUtils;

public class GenerateHtmlFiles {

    private static GenerateHtmlFiles generateHtmlFiles = null;

    private GenerateHtmlFiles() {

    }

    public static GenerateHtmlFiles getInstance() {
        if (generateHtmlFiles == null)
            generateHtmlFiles = new GenerateHtmlFiles();

        return generateHtmlFiles;
    }

    public void generateHtmlFile(String content, String fileName, String baseFolderName) {
        makeDirectories(baseFolderName);
        copyFiles(baseFolderName);
        createHtmlFile(fileName, content, baseFolderName);
    }

    private void makeDirectories(String baseFolderName) {
        FileHelperUtils.mkDir(baseFolderName);
        FileHelperUtils.mkDir(baseFolderName + "/images");
        FileHelperUtils.mkDir(baseFolderName + "/images/tiles");
        FileHelperUtils.mkDir(baseFolderName + "/title-font");
    }

    private void copyFiles(String baseFolderName) {
        FileHelperUtils.copyFile("images/sky-bg.jpg", baseFolderName + "/images/sky-bg.jpg");
        FileHelperUtils.copyFile("images/tiles/GrassTile.png", baseFolderName + "/images/tiles/GrassTile.png");
        FileHelperUtils.copyFile("images/tiles/WaterTile.png", baseFolderName + "/images/tiles/WaterTile.png");
        FileHelperUtils.copyFile("images/tiles/TreasureTile.png", baseFolderName + "/images/tiles/TreasureTile.png");
        FileHelperUtils.copyFile("images/tiles/HiddenTile.png", baseFolderName + "/images/tiles/HiddenTile.png");
        FileHelperUtils.copyFile("styles.css", baseFolderName + "/styles.css");
        FileHelperUtils.copyFile("title-font/04b_30__-webfont.woff", baseFolderName + "/title-font/04b_30__-webfont.woff");
        FileHelperUtils.copyFile("title-font/04b_30__-webfont.woff2", baseFolderName + "/title-font/04b_30__-webfont.woff2");
    }

    private static void createHtmlFile(String filename, String html, String baseFolderName) {
        String filepath = baseFolderName + "/" + filename;
        FileHelperUtils.writeToFile(filepath, html);
    }
}
