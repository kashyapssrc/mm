/**
 *
 */
package com.ofs.training;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author mohammed.mohammed
 *
 */
public class InputStreamStringConverter {

    /**
     * Converts an input stream into a string for a given file path
     *
     * @param filePath
     *        The path of the file for which input stream will be converted
     *        into String
     *
     * @return A string having the content of the file's input stream
     *
     * @throws Exception if the specified file is not found or empty
     */
    private String convertStreamToString(String filePath) throws Exception{
        InputStream inStream = new FileInputStream(filePath);
        InputStreamReader inStreamReader = new InputStreamReader(inStream);
        BufferedReader fileBuffer = new BufferedReader(inStreamReader);

        char[] content = new char[inStream.available()];
        fileBuffer.read(content, 0, content.length);
        String fileString = new String(content);
        inStream.close();

        return fileString;
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
        InputStreamStringConverter streamStringConverter = new InputStreamStringConverter();

        String convertedString = streamStringConverter.convertStreamToString(args[0]);
        streamStringConverter.log("%s%n", convertedString);
    }
}
