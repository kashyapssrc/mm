/**
 *
 */
package com.ofs.training;

import java.io.File;

/**
 * @author mohammed.mohammed
 *
 */
public class DirectoryFileCounter {

    /**
     * Counts the number of files present in a directory
     *
     * @param parentDirectory
     *        The directory in which files are to be counted
     *
     * @return Number of the files found in the parent directory
     */
    private int getFileCount(String parentDirectory) {
        File dir = new File(parentDirectory);
        File[] files = dir.listFiles();

        int fileCount = 0;
        for (File file : files) {
            if(file.isFile()) {fileCount++;}
        }

        return fileCount;
    }

    /**
     * Counts the number of sub-directories present in a directory
     *
     * @param parentDirectory
     *        The directory in which sub-directories are to be counted
     *
     * @return Number of the sub-directories found in the parent directory
     */
    private int getDirectoryCount(String parentDirectory) {
        File dir = new File(parentDirectory);
        File[] files = dir.listFiles();

        int dirCount = 0;
        for (File subDir : files) {
            if(subDir.isDirectory()) {dirCount++;}
        }

        return dirCount;
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
        DirectoryFileCounter directoryFileCounter = new DirectoryFileCounter();

        System.out.print("Number of files in the given directory = ");
        int fileCount = directoryFileCounter.getFileCount(args[0]);
        directoryFileCounter.log("%s%n", fileCount);

        System.out.print("Number of sub-directories in the given directory = ");
        int dirCount = directoryFileCounter.getDirectoryCount(args[0]);
        directoryFileCounter.log("%s%n", dirCount);
    }
}
