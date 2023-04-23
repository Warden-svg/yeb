package com.xxxx.server.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换
 */
@Component
public class DateConvert implements Converter<String,LocalDate> {


    @Override
    public LocalDate convert(String source){
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();//当try语句中出现异常是时，会执行catch 中的语句，java运行时系统会自动将catch括号中的Exception e 初始化，
                                   // 也就是实例化Exception类型的对象。e是此对象异常名称。然后e（引用）会自动调用Exception类中指定的方法，
                                 //也就出现了e.printStackTrace();。printStackTrace()方法的意思是：在命令行打印异常信息在程序中出错的位置及原因
        }
        return null;
    }

}
