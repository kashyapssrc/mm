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
public class FileReaderUsingPaths {

    /**
     * Reads a file from the specified file path
     *
     * @return a list with content lines of the file as its elements
     *
     * @throws Exception if the file is not found or empty
     */
    private List<String> readFile() throws Exception {
        String fileLocation = "D:\\temp\\java.practice\\readFileSample.txt";

        Path filePath = Paths.get(fileLocation);
        List<String> contentLines = Files.readAllLines(filePath);
//        Files.lines(filePath).collect(Collectors.toList());

        return contentLines;
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
        FileReaderUsingPaths fileReader = new FileReaderUsingPaths();

        List<String> content = fileReader.readFile();
        content.forEach(contentLines -> fileReader.log("%s%n", contentLines));
    }
}
