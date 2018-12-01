/**
 *
 */
package com.ofs.training;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mohammed.mohammed
 *
 */
public class BufferedReaderFileRead {

    private static final String SAMPLE_FILE = "D:\\temp\\java.practice\\readFileSample.txt";

    /**
     * Reads the content of the specified file
     *
     * @param Fully qualified name of the file to be read
     *
     * @return a list containing content lines of the file as each element
     *
     * @throws Exception if the specified file is not found or empty
     */
    private List<String> readFile(String fileName) throws Exception {
        Reader reader = new FileReader(fileName);
        BufferedReader fileBuffer = new BufferedReader(reader);

         List<String> contentLine = fileBuffer.lines().collect(Collectors.toList());
        fileBuffer.close();

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
        BufferedReaderFileRead fileReader = new BufferedReaderFileRead();

        fileReader.readFile(SAMPLE_FILE)
                  .forEach(contentLine -> fileReader.log("%s%n",contentLine));
    }
}
