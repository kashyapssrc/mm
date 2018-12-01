/**
 *
 */
package com.ofs.training;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author mohammed.mohammed
 *
 */
public class InputStreamFileReader {

    /**
     * Reads a file's content
     *
     * @return the file content in string format
     *
     * @throws Exception if the specified file is not found or empty
     */
    private void readFile() throws Exception {
        String filePath = "D:\\temp\\java.practice\\readFileSample.txt";
        InputStream inStream = new FileInputStream(filePath);
//        InputStream inStream = getClass().getResourceAsStream("readFileSample.txt");

        BufferedInputStream bufferStream = new BufferedInputStream(inStream, 50);

        byte[] contentBytes = new byte[bufferStream.available()];
        bufferStream.read(contentBytes, 0, contentBytes.length);

        for (byte content : contentBytes) {
            log("%c", content);
        }
//        while((data = inStream.read()) != -1) {
//            sb.append((char) data);
//        }
//        return sb.toString();

        inStream.close();
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

    public static void main(String[] args) throws Exception {
        InputStreamFileReader fileReader = new InputStreamFileReader();

        /*fileReader.log("%s\n", */fileReader.readFile();
    }
}
