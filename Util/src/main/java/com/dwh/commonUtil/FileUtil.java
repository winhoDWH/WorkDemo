package com.dwh.commonUtil;

import javax.naming.Name;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * 文件操作工具类
 *
 * @author dwh
 * @date
 */
public class FileUtil {
    public static void main(String[] args) throws IOException {
        /*File file = new File(".");
        //筛选出该目录下，文件夹和已.java结尾的文件
        String[] javaFileList = file.list((dir, name) -> name.endsWith(".java") || new File(name).isDirectory());
        ArrayList<String> list = new ArrayList<>();*/
        Set<String> set = new HashSet<>();
        set.add(null);
        set.add("1");
        System.out.println(set);
        HashMap
    }

    private void TestNewFile() throws IOException {
        //File(String)
        File parentFile = new File("./TestFile");
        //File(File, childPathString)
        File childFile1 = new File(parentFile, "test.txt");
        //File(parentPath, childPathString)
        File childFile2 = new File("./TestFile", "test.txt");
        System.out.println("parenFile是否存在" +parentFile.exists());
        parentFile.mkdir();
        System.out.println("parenFile是否存在" +parentFile.exists());
        System.out.println("parenFile路径" +parentFile.getAbsolutePath());

        System.out.println("childFile1是否存在" + childFile1.exists());
        childFile1.createNewFile();
        System.out.println("childFile1是否存在" + childFile1.exists());
        System.out.println("childFile1路径" + childFile1.getAbsolutePath());

        System.out.println("childFile2是否存在" + childFile2.exists());
        childFile2.createNewFile();
        System.out.println("childFile2是否存在" + childFile2.exists());
        System.out.println("childFile2路径" + childFile2.getAbsolutePath());

        //去到当前代码所在的根目录盘下，如F:\TestEmpty.txt
        File testEmpty = new File("", "TestEmpty1.txt");
        System.out.println("testEmpty是否存在" + testEmpty.exists());
        System.out.println("testEmpty父路径" + testEmpty.getParent());
        System.out.println("创建testEmpty：" + testEmpty.createNewFile());
        System.out.println("testEmpty是否存在" + testEmpty.exists());
        System.out.println("testEmpty路径" + testEmpty.getAbsolutePath());

        //将当前工作空间作为父目录，即该项目根目录
        File parentFile2 = null;
        File testEmpty2 = new File(parentFile2, "TestEmpty1.txt");
        System.out.println("testEmpty是否存在" + testEmpty.exists());
        System.out.println("testEmpty父路径" + testEmpty.getParent());
        System.out.println("创建testEmpty：" + testEmpty.createNewFile());
        System.out.println("testEmpty是否存在" + testEmpty.exists());
        System.out.println("testEmpty路径" + testEmpty.getAbsolutePath());
    }

    /**
     * 使用ObjectOutputStream与ObjectInputStream进行序列化
     */
    private void serializableObject() {
        //序列化
        try {
            //因为是处理流，所以要包装一个节点流
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("./testSerializable.txt")));
            //使用该方法序列化,写入文件中
            outputStream.writeObject(new TestEntity("hahah"));
        }catch (Exception e){
            e.printStackTrace();
        }
        //反序列化
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("./testSerializable.txt")));
            //可以直接转
            TestEntity e = (TestEntity) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
