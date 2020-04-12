package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public class FileHelperUtils {

    public static String readWholeFile(String filePath) throws Exception {

        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
        return contentBuilder.toString();
    }

    public static void mkDir(String dirName) {
        File file = new File(dirName);
        file.mkdir();
    }

    public static void writeToFile(String filename, String fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(fileContent);
        } catch (IOException e) {
            System.err.println("Exception happened when writing to file");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void copyFile(String src, String dest) {
        File destFile = new File(dest);
        if (!destFile.exists()) {
            File srcFile = new File(getResourceFilePath(src));
            try {
                FileUtils.copyFile(srcFile, destFile);
            } catch (IOException e) {
                System.out.println("IOException encountered when copying file. Exiting System");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public static String getResourceFilePath(String resourceName) {
        URL res = Objects.requireNonNull(FileHelperUtils.class.getClassLoader().getResource(resourceName));
        try {
            return Paths.get(res.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            System.err.println("Erroneous URI encountered when reading resource. Exiting System");
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    public static void deleteDirectory(String directoryName) {
        File dir = new File(directoryName);

        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                deleteDirectory(file.toString());
            }

        }
        dir.delete();
    }
}
