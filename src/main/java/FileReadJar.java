import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;

//public class FileReadJar {
//  public static void main(String[]args) throws IOException {
//    FileInputStream inputStream = new FileInputStream("rt.jar");
//    JarInputStream jarInputStream = new JarInputStream(inputStream);
//    ZipEntry zipEntry = jarInputStream.getNextEntry();
//    int cnt = 0;
//    ArrayList<ZipEntry> arrayList = new ArrayList<>();
//    while (!(zipEntry == null)) {
//      if (zipEntry.getName().contains("java/io")||zipEntry.getName().contains("java/nio")) {
//        cnt++;
//        arrayList.add(zipEntry);
//      }zipEntry = jarInputStream.getNextEntry();
//    }
//    System.out.println("# of .java files in java.io/java.nio: "+cnt);
//    arrayList.forEach(System.out::println);
//  }
//}
