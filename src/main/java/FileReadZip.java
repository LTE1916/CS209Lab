import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileReadZip {
  public static void main(String[]args) throws IOException {
    FileInputStream inputStream = new FileInputStream("src.zip");
    FileInputStream inputStream1 = new FileInputStream("rt.jar");
  //  print(inputStream);
    print(inputStream1);
  }


  public static void print (FileInputStream inputStream) throws IOException {
    ZipInputStream zipInputStream = new ZipInputStream(inputStream);
    ZipEntry zipEntry = zipInputStream.getNextEntry();
    int cnt = 0;
    ArrayList<ZipEntry> arrayList = new ArrayList<>();
    while (!(zipEntry == null)) {
      if (zipEntry.getName().contains("java/io")||zipEntry.getName().contains("java/nio")) {
        cnt++;
        arrayList.add(zipEntry);
      }zipEntry = zipInputStream.getNextEntry();
    }
    System.out.println("# of .java files in java.io/java.nio: "+cnt);
    arrayList.forEach(System.out::println);
  }
}
