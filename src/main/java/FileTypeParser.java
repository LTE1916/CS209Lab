import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTypeParser {
  static   Map<List<String>,String> map = new HashMap<>();
    public static void main(String[]args){
        
        map.put(new ArrayList<String>(){{add("89");add("50");add("4e");add("47");}},"png");
        map.put(new ArrayList<String>(){{add("50");add("4b");add("03");add("04");}},"zip or jar");
        map.put(new ArrayList<String>(){{add("ca");add("fe");add("ba");add("be");}},"class");
        getFileType("1");
        getFileType("2");
        getFileType("3");
    }
    public static void getFileType(String path){
        File file=new File(path);
        List<String>list=getFileHeader(path);
        System.out.println("Filename: "+file.getName());
        System.out.println("File Header(Hex): "+list);
        System.out.println("File Type: "+map.get(list));
        
    }
    public static List<String> getFileHeader(String filePath){
        StringBuilder builder = new StringBuilder();
        String res;
        List<String>list = new ArrayList<>();
        try {
            FileInputStream fileInputStream=new FileInputStream(filePath);
            byte[]bytes = new byte[4];
            fileInputStream.read(bytes,0,bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
                res = Integer.toHexString(bytes[i] & 0xFF);
                if (res.length() < 2) {
                    builder.append(0);
                    builder.append(res);
                    list.add(builder.toString());
                    builder =new StringBuilder();
                }else list.add(res);
                
            }
    
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    
        return list;
    }
    
}
