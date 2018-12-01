package com.ofs.training;

// open the source file of the running class in Notepad++ - do NOT hardcode/specify the source file in the code; find the source file using APIs

// class CurrentClassFileOpener {
public class FileOpener {

     // static void execute() {
    public static void main(String[] args) throws Exception {

        // CurrentClassFileOpener currentProgram = getProgram();
        FileOpener currentProgram = new FileOpener();

        // Class currentClass = getClass();
        Class currentClass = currentProgram.getClass();

        // File currentClassFile = currentClass.getFile();
        String absPath = currentClass.getProtectionDomain()
                                     .getCodeSource()
                                     .getLocation()
                                     .getFile();

        // notepad = getNotepad();
        Runtime runtime = Runtime.getRuntime();
        String notepad = "D:\\tools\\Notepad++\\notepad++.exe " + (absPath.substring(3) + currentClass.getName() + ".java");

        // notepad.open(file);
        Process process = runtime.exec(notepad);

    }


}