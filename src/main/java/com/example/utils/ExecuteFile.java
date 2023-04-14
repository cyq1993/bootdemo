package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: 开启进程执行脚本文件（.bat .exe等）
 * @author: cyq
 * @create: 2023/4/14 13:41
 */
public class ExecuteFile {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String fileName = "test.bat";
        exc(path, fileName);
    }

    public static void exc(String path, String fileName) {
        /**
         *  /c 告诉命令提示符执行完命令后关闭命令提示符窗口
         *  start 命令启动一个新的命令提示符窗口，并在其中执行命令。
         * */
        try {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "start", path + "\\" + fileName);
        builder.directory(new File(path));//设置进程的工作目录（命令中寻找上下文会依赖这里）
            Process p = builder.start();
            int code = p.waitFor();// (进程终止该方法会立刻返回)将参数 /c 换成 /k 这里会一直阻塞
            System.out.println("file executed successfully " + code);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
