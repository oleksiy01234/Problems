package Recursion;

import java.io.File;

public class FilePrinter {

  public static void main(String[] args) {
    File file = new File("C:\\Users\\Oleksiy\\Google Drive\\Development");
    printFiles(file, "");
  }

  static void printFiles(File file, String indent) {

    if (file.isDirectory()) {
      System.out.println(indent + file.getName());
      for (File child : file.listFiles()) {
        printFiles(child, indent + "  ");
      }
    }

  }

}