package it.dy.util;

import java.io.File;
import java.util.*;

/**
 * @ClassName: FilesMonitor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2021/4/23 17:11
 */
public class FilesMonitor implements Runnable {

    private String filePath;

    public FilesMonitor(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    private static Map<String, Long> map = new HashMap<>();


   public  Integer getFileSize(){
       File[] files = getFiles(filePath, null);
       if(files==null){
           return 0;
       }else {
           return files.length;
       }

    }


    @Override
    public void run() {
        while (true) {
            try {
        // 设置每隔3秒检测一次
                Thread.sleep(3000);
                FileMonitor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 文件监听
    public void FileMonitor() {
        File[] files = getFiles(filePath, null);
        if (files != null && files.length > 0) {
         // 如果缓存中文件与读取的个数不一样的时候
            String fName = "";
            if (files.length != map.size()) {
                if (map.size() == 0) {
                    for (File file : files) {
                        fName = file.getName();
                        Long length = file.length();
                        map.put(fName, length);
                        System.out.println("新增了文件：" + fName);
                    }
                } else {
                // 如果减少了文件
                    if (map.size() > files.length) {
                        List<String> removeName = new ArrayList<String>();
                        Iterator<String> iter = map.keySet().iterator();
                        int j = 0;
                        while (iter.hasNext()) {
                            String key = iter.next();
                            if (key != null && key.length() > 0) {
                                for (File file : files) {
                                    fName = file.getName();
                                    if (fName.equals(key)) {
                                        j = 1;
                                        break;
                                    }
                                }
                                if (j != 1) {
                                    removeName.add(key);
                                }
                                j = 0;
                            }
                        }
                        // 判断是否有删除的文件
                        if (removeName.size() > 0) {
                            for (String item : removeName) {
                                map.remove(item);
                                System.out.println("减少了文件：" + item);
                            }
                        }
                    } else {
                        for (File file : files) {
                            fName = file.getName();
                            Long length = file.length();
                            if (!map.containsKey(fName.trim())) {
                                map.put(fName, length);
                                System.out.println("新增了文件：" + fName);
                            }
                        }
                    }
                }
            } else {
                map.clear();
                for (File file : files) {
                    fName = file.getName();
                    Long length = file.length();
                    map.put(fName, length);
                }
            }
            System.out.println("此时缓存中文件个数：" + map.size());
        }
    }


    /**
     * 文件读取
     *
     * @param filePath
     *      路径
     * @param fileName
     *      名称
     * @return 返回文件数组
     */
    public File[] getFiles(String filePath, String fileName) {
        File[] files = null;
        if (fileName == null) {
            File doc = new File(filePath);
            if (doc.isDirectory()) {
                String[] fileNameArr = doc.list();
                if(Objects.isNull(fileNameArr)) return null;
                if (fileNameArr.length > 0) {
                    files = new File[fileNameArr.length];
                    for (int i = 0; i < fileNameArr.length; i++) {
                        fileName = fileNameArr[i];
                        String fileAbsPath = filePath + fileName;
                        File regInfoFile = new File(fileAbsPath);
                        files[i] = regInfoFile;
                    }
                }
            }
        } else {
            String path = filePath + fileName;
            File doc = new File(path);
            if (doc.isFile()) {
                files = new File[1];
                files[0] = doc;
            }
        }
        return files;
    }


    // 启动线程
    public void show() {
        FilesMonitor t = new FilesMonitor(this.filePath);
        Thread tread = new Thread(t);
        tread.setName("eshore");
        tread.start();
    }

    public static Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) {
                return t;
            }
        }
        return null;
    }

    public void stopThread(){
        Thread thread = getThreadByName("eshore");
        thread.stop();
    }


}
