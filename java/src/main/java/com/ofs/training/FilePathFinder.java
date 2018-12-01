package com.ofs.training;

//Print the absolute path of the .class file of the current class

// class ClassFileLocationFinder {
public class FilePathFinder {

    // static void execute() {
    public static void main(String[] args) {

        // ClassFileLocationFinder currentProgram = getProgram();
        FilePathFinder currentProgram = new FilePathFinder();

        // class CurrentClass = getClass();
        Class currentClass = currentProgram.getClass();

        // File curentClassFile = currentClass.getFile();
        // String absolutePath = currentClassFile.getAbsolutePath();
        String absPath = currentClass.getProtectionDomain()
                                     .getCodeSource()
                                     .getLocation()
                                     .getFile();

        // Console console = getConsole();
        // console.print(absolutePath);
        System.out.println("The absolute path of the .class file of the current class is: ");
        System.out.println(absPath + currentClass.getName() + ".class");

    }

}