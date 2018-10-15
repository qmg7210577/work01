package com.qunar.qmg.demo01;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by menggao.qi on 2018/10/15.
 */
public class App {

    public static Splitter splitter = Splitter.on("    ").trimResults().omitEmptyStrings();

    public static void main(String[] args) {

        String fileName = "unorder.txt";
        String fileNamePath = App.class.getClassLoader().getResource(fileName).getPath();
        System.out.println(fileNamePath);

        sortChatting_record(fileNamePath);


    }

    public static void sortChatting_record(String fileNamePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileNamePath)));
            String readLine;

            List<Chatting_record> recordList = Lists.newArrayList();
            Multiset<String> userCount = HashMultiset.create();
            while ((readLine = reader.readLine()) != null && !Strings.isNullOrEmpty(readLine)) {
                Chatting_record recordAfterExplain = explain(readLine);
                recordList.add(recordAfterExplain);
                userCount.add(recordAfterExplain.getUserInfo().getUsername());
            }
            Collections.sort(recordList, getOrdering());

            for (Chatting_record record :
                    recordList) {
                System.out.println(record);
            }
            System.out.println(userCount);
            Iterator<Multiset.Entry<String>> iterator = userCount.entrySet().iterator();
            while (iterator.hasNext()){
                Multiset.Entry<String> entry = iterator.next();
                System.out.println(entry.getElement()+"="+entry.getCount());
            }

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Chatting_record explain(String record) {
        ArrayList<String> arrayList = Lists.newArrayList(splitter.split(record));

        String userInfoString = arrayList.get(0);
        String createTime = arrayList.get(1);
        String content = arrayList.get(2);


        UserInfo userInfo = getUserInfo(userInfoString);
        Date createDate = getDate(createTime);

        return new Chatting_record(userInfo, createDate, content);
    }

    private static Date getDate(String createTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = simpleDateFormat.parse(createTime);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static UserInfo getUserInfo(String userInfoString) {
        System.out.println(userInfoString);
        String level = userInfoString.substring(1, 3);
        String username;
        String userId;
        if (userInfoString.indexOf('(') > 0) {
            username = userInfoString.substring(4, userInfoString.lastIndexOf('('));
            userId = userInfoString.substring(userInfoString.lastIndexOf('(') + 1, userInfoString.length() - 1);
        } else {
            username = userInfoString.substring(4, userInfoString.lastIndexOf('<'));
            userId = userInfoString.substring(userInfoString.lastIndexOf('<') + 1, userInfoString.length() - 1);

        }

        return new UserInfo(level, username, userId);

    }



    public static Ordering<Chatting_record> getOrdering() {
        Ordering<Chatting_record> ordering = Ordering.from(new Comparator<Chatting_record>() {
            @Override
            public int compare(Chatting_record c1, Chatting_record c2) {
                int result = c1.getCreateTime().compareTo(c2.getCreateTime());
                if (result == 0) {
                    return c1.getUserInfo().compareTo(c2.getUserInfo());
                }
                return c1.getCreateTime().compareTo(c2.getCreateTime());
            }
        });
        return ordering;
    }
}
