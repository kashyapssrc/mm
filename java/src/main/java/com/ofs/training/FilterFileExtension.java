/**
 *
 */
package com.ofs.training;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author mohammed.mohammed
 *
 */
public class FilterFileExtension {

    private static final String DIRECTORY = "D:\\temp\\java.practice";
    private static final String SEARCH_EXT = ".java";

    /**
     *  Gets the file names with specific extension within the specified directory
     *
     * @param directory
     *        The directory path in which the files with
     *        the required extensions are to be searched
     *
     * @return a string list of file names with the specific extension
     */
    private String[] getExtensionFile(String directory) {
        File dir = new File(directory);
        FilenameFilter filteredExtensions = (fileLocation, name) -> name.endsWith(SEARCH_EXT);
        String[] filteredFiles = dir.list(filteredExtensions);

        return filteredFiles;
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
        FilterFileExtension filterFileExtension = new FilterFileExtension();
        String[] extFile = filterFileExtension.getExtensionFile(DIRECTORY);

        for (String file : extFile) {
            filterFileExtension.log("%s%n", file);
        }

    }
}
