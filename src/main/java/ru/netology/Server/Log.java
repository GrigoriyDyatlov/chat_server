package ru.netology.Server;

import java.util.Set;
import java.util.TreeSet;

public class Log {
    private static Log loger = null;
    private final Set<String> msgLog = new TreeSet<>();

    public Log() {
    }

    public static Log getLogger() {
        if (loger == null) {
            synchronized (Log.class) {
                if (loger == null) {
                    loger = new Log();
                }

            }
        }
        return loger;
    }

    public void log(String msg) {
        msgLog.add(msg);
    }

    public void getLog() {
        System.out.println(msgLog);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : msgLog) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}
