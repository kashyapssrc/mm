/**
 *
 */
package com.ofs.training;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author mohammed.mohammed
 *
 */
public class CsvFileReader {

    /**
     * Reads a CSV file from the specified file path
     *
     * @return a list with content lines of the CSV file as its elements
     *
     * @throws Exception if the file is not found or empty
     */
    private List<String> readFile() throws Exception {
        String fileLocation = "D:\\temp\\java.practice\\dbElectronicProductCsv.csv";

        Path filePath = Paths.get(fileLocation);
        List<String> contentLine = Files.readAllLines(filePath);

        return contentLine;
    }

    /**
     * Prints the passed objects as string
     *
     * @param format
     *        Output format defined for the string to be printed
     *
     * @param values
     *        Object values passed to be printed in the format defined
     */
    private void log(String format,Object... values) {
        System.out.format(format, values);
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        CsvFileReader fileReader = new CsvFileReader();

        fileReader.readFile()
                  .forEach(contentLine -> fileReader.log("%s%n", contentLine));
    }
}
