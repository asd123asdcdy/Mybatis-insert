package it.dy.controller;

import cn.hutool.core.io.FileUtil;
import it.dy.entity.User;
import it.dy.mapper.UserMapper;
import it.dy.util.CommentUtils;
import it.dy.util.ConverUtil;
import it.dy.util.FilesMonitor;
import it.dy.util.OpenCVUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.opencv.core.Mat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author w5489
 * @Description
 * @date 2022/7/28 3:50
 */
@RestController
public class TestController {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private UserMapper userMapper;
    //文件目录
    private String path="E:\\cas";
    //分片文件目录
    private String tempPath="E:\\csf";
    //生成文件目录
    private String targetPath="E:\\1231";



    @GetMapping(value = "getList")
    public List<User>getList(){
      return   userMapper.getList();
    }

    //自定义线程池
    private ThreadPoolExecutor getThreadPoolExecutor(){
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<>(800);
        // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, bq);
        tpe.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return tpe;
    }

    @GetMapping(value = "testOpenCV")
    public void testOpenCV(){
        OpenCVUtil util = new OpenCVUtil();
        BufferedImage buff = ConverUtil.bufferRead("/opt/1589890291643662337.jpg");  //全景图切割
        Mat[] cube = util.shear(buff, 1024, 1024,"/opt/spr");
        //预览图合成
        Mat preview = util.mergeImage(cube, 512,"/opt/spr");

    }


    @GetMapping(value = "testMoreThread")
    public void testMoreThread(){
        FilesMonitor monitor = new FilesMonitor(tempPath);
        monitor.show();

        ThreadPoolExecutor poolExecutor = getThreadPoolExecutor();
        List<File> loopFiles = FileUtil.loopFiles(path);

        long l = System.currentTimeMillis();

        for (File loopFile : loopFiles) {
            List<File> files = CommentUtils.splitDataToSaveFile( 5000, loopFile, tempPath);
            for (File file : files) {
                poolExecutor.execute(()->{
                    List<String> list = FileUtil.readUtf8Lines(file);
                    testInsertByForeachTag(list);
                    FileUtil.del(file);
                    list.clear();
                    System.gc();
                });
            }
//            FileUtil.del(loopFile);
            System.gc();
        }


        Integer size;
        while (true) {
            try {
                Thread.sleep(5000);
                size = monitor.getFileSize();
                if (size == 0) {
                    monitor.stopThread();
                    poolExecutor.shutdown();
                    System.out.println("任务结束");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long s = System.currentTimeMillis();
        System.out.println(s-l);
    }

    void testInsertBatch(List<String> list){

        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        list.forEach(v -> {
            String[] split = v.split(",");
            User user = new User();
            user.setUserId(split[0]);
            user.setCityCode(split[1]);
            userMapper.insert(user);
        });

        sqlSession.commit();
        sqlSession.clearCache();
        sqlSession.close();

    }


    void testInsertByForeachTag(List<String> list){
        List<User> userList = list.stream().map(v -> {
            String[] split = v.split(",");
            User user = new User();
            user.setUserId(split[0]);
            user.setCityCode(split[1]);
            return user;
        }).collect(Collectors.toList());
        userMapper.insertBatch(userList);
    }

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);
        System.out.println(s.length());
        LinkedList list = new LinkedList<>();
    }
}
