package com.ly;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    private static final Path  PATH_BASE = (Path) Paths.get("E:\\dream\\derby-class");
    private static final File[] SOURCE_FILES = new File("E:\\dream\\db-derby-10.14.2.0-src\\db-derby-10.14.2.0-src\\java").listFiles();


    public static void main(String[] args) throws IOException {
        File file = new File("E:\\dream\\derby-class");
        findCopyFile(file);
    }

    private static void findCopyFile(File file) throws IOException {
        if(file.isDirectory()){
            final File[] files = file.listFiles();
            for(File f :files){
                findCopyFile(f);
            }
        }else {
            String filePath = file.getPath();
            if(!filePath.contains("$") && filePath.endsWith(".class")){
                Path pathRelative = PATH_BASE.relativize(file.toPath());
                copyFile(pathRelative);
            }
        }
    }

    public static void copyFile(Path filePath){
        boolean exist = false;
        Path path = null ;
        for(File file:SOURCE_FILES){
            String p = filePath.toString();
              path = Paths.get(file.getPath(), p.substring(0,p.length()-5)+"java");
            if(path.toFile().exists()){
                System.out.println(path);
                exist = true;
                break;
            }
        }
        if(!exist && filter(path)){
            throw new RuntimeException(path.toString()+ " file not exist..");
        }
    }

    public static boolean filter(Path path){
        String[] filters = new String[]{"ConglomInfo.java","ClassSizeCatalogImpl.java","MemberTableHash.java"
        ,"ImportFileInputStream.java","LockList.java","ClassLoaderLock.java","DirectCall.java"
        ,"SQLParser.java","SQLParserConstants.java","SQLParserTokenManager.java","TokenMgrError.java"
        ,"CacheLock.java","RemoveFile.java","LockCount.java"};
        for(String str:filters){
            if(path.toString().endsWith(str)){
                return false;
            }
        }
        return true;
    }
}
