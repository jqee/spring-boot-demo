package com.repo.test;

import cn.hutool.core.collection.BoundedPriorityQueue;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.system.SystemUtil;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author weibing@primeton.com
 * @date 2019/11/14 14:34
 */
public class TestB {

    @Test
    public void name() {
        String a = "我是一个小小的可爱的字符串";
        String hex = Convert.toHex(a, Charset.forName("utf-8"));
        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("../../qrcode.jpg"));
    }

    @Test
    public void t1() {
        Console.log(SystemUtil.getJvmSpecInfo());
        Integer a;
    }

    @Test
    public void c() {
        //初始化队列，设置队列的容量为5（只能容纳5个元素），元素类型为integer使用默认比较器，在队列内部将按照从小到大排序
        BoundedPriorityQueue<Integer> queue = new BoundedPriorityQueue<Integer>(5);

        //初始化队列，使用自定义的比较器
        queue = new BoundedPriorityQueue<>(5, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        //定义了6个元素，当元素加入到队列中，会按照从小到大排序，当加入第6个元素的时候，队列末尾（最大的元素）将会被抛弃
        int[] array = new int[]{5, 7, 9, 2, 3, 8};
        for (int i : array) {
            queue.offer(i);
        }

        //队列可以转换为List哦~~
        ArrayList<Integer> list = queue.toList();
        //ConverterRegistry
        URLUtil.normalize();
        Singleton;
        ClassUtil.isBasicType()

        System.out.println(queue);
    }
}
