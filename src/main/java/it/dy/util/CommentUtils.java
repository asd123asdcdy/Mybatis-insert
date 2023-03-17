package it.dy.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @ClassName CommentUtils
 * @Author yangyu
 * @Date 2020/11/12 15:31
 * @Description CommentUtils
 * @Version 1.0
 */

@Slf4j
public class CommentUtils {


    /**
     * 功能描述: <把对应的数字转换成二进制编码 再由二进制变为十进制>
     * 〈t：位置信息, 从左开始第t位为1, 其他位为0; max: 二进制编码的总长度〉
     * example: max = 10; t = 2  生成二进制编码为 0000000010, 转换成十进制为 2
     * example: max = 10; t = 2,1  生成二进制编码为 0000000011, 转换成十进制为 3
     *
     * @Param: [t]
     * @Return: int
     * @Author: yangyu
     * @Date: 2020/11/12 15:31
     */
    public static String getBinaryCode(int max, Long... t) {

        List<Long> list = Arrays.asList(t);

        char[] chars = new char[max];
        for (int i = chars.length; i > 0; i--) {

            if (list.contains((long) (i))) {
                chars[chars.length - i] = '1';
            } else {
                chars[chars.length - i] = '0';
            }
        }
        return new BigInteger(new String(chars), 2).toString(10);
    }

    public static List<Long> getTagIds(String tagId) {
        String str = new BigInteger(tagId, 10).toString(2);
        Long[] longs = new Long[str.length()];
        for (int i = longs.length; i > 0; i--) {
            if (str.charAt(i - 1) == '1') {
                longs[i - 1] = (long) longs.length + 1 - i;
                ;
            }
        }
        return Arrays.asList(longs);
    }


    public static void main(String[] args) {
        splitDataToSaveFile(1000000,new File("C:\\Users\\Administrator\\Desktop\\审核现网数据\\audit_log.sql"),"C:\\Users\\Administrator\\Desktop\\审核现网数据\\spr");
    }

    public static List<File> splitDataToSaveFile(int rows, File sourceFile, String targetDirectoryPath) {
        long startTime = System.currentTimeMillis();
        List<File> fileList = new ArrayList<>();
        log.info("开始分割文件");
        File targetFile = new File(targetDirectoryPath);
        if (!sourceFile.exists() || rows <= 0 || sourceFile.isDirectory()) {
            return null;
        }
        if (targetFile.exists()) {
            if (!targetFile.isDirectory()) {
                return null;
            }
        } else {
            targetFile.mkdirs();
        }

        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String lineStr;
            int lineNo = 1, fileNum = 1;
            while ((lineStr = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineStr).append("\r\n");
                if (lineNo % rows == 0) {
                    File file = new File(targetDirectoryPath + File.separator + fileNum + "_" + sourceFile.getName());
                    writeFile(stringBuilder.toString(), file);
                    //清空文本
                    stringBuilder.delete(0, stringBuilder.length());
                    fileNum++;
                    fileList.add(file);
                }
                lineNo++;
            }
            if ((lineNo - 1) % rows != 0) {
                File file = new File(targetDirectoryPath + File.separator + fileNum + "_" + sourceFile.getName());
                writeFile(stringBuilder.toString(), file);
                fileList.add(file);
            }
            long endTime = System.currentTimeMillis();
            log.info("分割文件结束，耗时：{}秒", (endTime - startTime) / 1000);
            System.gc();
        } catch (Exception e) {
            log.error("分割文件异常", e);
        }
        return fileList;
    }

    private static void writeFile(String text, File file) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter, 1024)
        ) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Object init(String[] fieldName, Object[] fieldValue, Object obj) {

        HashMap<String, Object> hashMap = new HashMap<>(fieldName.length);
        for (int i = 0; i < fieldName.length; i++) {
            try {
                hashMap.put(fieldName[i], fieldValue[i]);
            } catch (Exception exception) {

            }
        }

        hashMap.forEach((name, value) -> {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.getName().equals(name)) {
                    try {
                        field.set(obj, value);
                    } catch (IllegalAccessException e) {
                        log.error("对象赋值错误", e);
                    }
                }
            }
        });
        return obj;
    }

    public static Map<String, Object> init(String[] fieldName, Object[] fieldValue) {

        HashMap<String, Object> hashMap = new HashMap<>(fieldName.length);
        for (int i = 0; i < fieldName.length; i++) {
            try {
                hashMap.put(fieldName[i], fieldValue[i]);
            } catch (Exception exception) {

            }
        }
        return hashMap;

    }

}
