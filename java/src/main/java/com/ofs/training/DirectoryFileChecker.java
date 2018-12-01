/**
 *
 */
package com.ofs.training;

import java.io.File;

/**
 * @author mohammed.mohammed
 *
 */
public class DirectoryFileChecker {

    private static final String PATH = "D:\\temp\\java.practice\\readFileSample.txt";
    private static final String ANOTHER_PATH = "D:\\temp\\java.practice";

    /**
     * Checks if the path given is a file or directory
     *
     * @param path
     *        Given path to be checked
     *
     * @return a string stating if the given path is a file or directory
     */
    private String checkPathType(String path) {
        File givenPath = new File(path);

        if(givenPath.isFile()) {
            return path + " is a file";
        }

        if(givenPath.isDirectory()) {
            return path + " is a Directory";
        }

        throw new RuntimeException("INVALID PATH");
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
    public static void main(String[] args) {
        DirectoryFileChecker directoryFileChecker = new DirectoryFileChecker();

        String pathType = directoryFileChecker.checkPathType(PATH);
        directoryFileChecker.log("%s%n", pathType);

        String anotherPathType = directoryFileChecker.checkPathType(ANOTHER_PATH);
        directoryFileChecker.log("%s%n", anotherPathType);
    }
}
