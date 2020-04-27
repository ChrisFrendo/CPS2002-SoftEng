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

/**
 * Static class which contains methods used to manipulate files and folders
 */
public class FileHelperUtils {

    /**
     * Helper function which reads the entire contents of the file and returns them as a single String
     *
     * @param filePath The path of the file whose contents to read
     * @return A string containing the whole contents of the file
     * @throws Exception Throws an exception when an IO error happens during reading of file
     */
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

    /**
     * Helper function to create a directory
     *
     * @param dirName The name of the directory to create
     */
    public static void mkDir(String dirName) {
        File file = new File(dirName);
        file.mkdir();
    }

    /**
     * Helper function used to write contents to a file
     *
     * @param filename    The name of the file to write contents to
     * @param fileContent The contents to write into the file
     */
    public static void writeToFile(String filename, String fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(fileContent);
        } catch (IOException e) {
            System.err.println("Exception happened when writing to file");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Helper function used to copy a file
     *
     * @param src  Path to source file
     * @param dest Path where to place copied file
     */
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

    /**
     * Helper function used to get the file path of a resource in the resources folder
     *
     * @param resourceName The name of the resource whose file path to get
     * @return The file path of the required resource
     */
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

    /**
     * Helper function used to delete a directory and its contents
     *
     * @param directoryName The path of the directory to delete
     */
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
