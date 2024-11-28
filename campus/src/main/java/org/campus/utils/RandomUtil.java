package org.campus.utils;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 随机数生成 工具类
 */
@Component
public class RandomUtil {

    private static final DecimalFormat sixdf = new DecimalFormat("000000");
    private static final Random random = new Random();
    /**
     * 生成6个随机数
     * @return
     */
    public String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }


}