package html;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.FileHelperUtils;

public class GenerateHtmlFilesTest {

    private static final String DIR_NAME = "testGenerateHtml";

    private GenerateHtmlFiles generateHtmlFiles;

    @Before
    public void setUp() {
        generateHtmlFiles = GenerateHtmlFiles.getInstance();
        FileHelperUtils.deleteDirectory(DIR_NAME);
    }

    @After
    public void tearDown() {
        generateHtmlFiles = null;
        FileHelperUtils.deleteDirectory(DIR_NAME);
    }

    @Test
    public void getInstanceTest() {
        generateHtmlFiles = GenerateHtmlFiles.getInstance();

        assertNotNull(generateHtmlFiles);
    }

    @Test
    public void generateHtmlFile() {

        generateHtmlFiles.generateHtmlFile("someContent", "file.html", DIR_NAME);

        assertTrue(new File(DIR_NAME).exists());
        assertTrue(new File(DIR_NAME + "/styles.css").exists());
        assertTrue(new File(DIR_NAME + "/title-font").exists());
        assertTrue(new File(DIR_NAME + "/images").exists());
    }
}