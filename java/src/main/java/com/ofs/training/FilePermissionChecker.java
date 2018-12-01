/**
 *
 */
package com.ofs.training;

import java.io.File;

/**
 * @author mohammed.mohammed
 *
 */
public class FilePermissionChecker {

    /**
     * Checks the permissions allowed for a file
     *
     * @param samplefile
     *        Path of the file to be checked
     */
    private void checkFilePermission(String sampleFile) {
        File file = new File(sampleFile);

        if(file.canExecute()) {
            System.out.println("This file has execute permission");
        }

        if(file.canRead()) {
            System.out.println("This file has read permission");
        }

        if(file.canWrite()) {
            System.out.println("This file has write permission");
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        FilePermissionChecker permissionChecker = new FilePermissionChecker();

        System.out.format("Permissions for the file: %s%n", args[0]);
        permissionChecker.checkFilePermission(args[0]);
    }
}
