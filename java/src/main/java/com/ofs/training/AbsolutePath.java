package com.ofs.training;

//Print the absolute path of the .class file of the current class

import java.io.File;

class AbsolutePath {

    public static void main(String[] args) {

    File file = new File("");

    String absolutePath = file.getAbsolutePath();
    String getPath = file.getPath();

    System.out.println("The absolute path of the file is: ");
    System.out.println(absolutePath);
    System.out.println(getPath);

    }

}
