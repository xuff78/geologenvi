package com.sichuan.geologenvi.DataBase;

/**
 * Created by 可爱的蘑菇 on 2016/7/17.
 */
public class QueryStr {

    public static final String yinhuandian="ZHAA01A020,ZHAA01A150, ZHAA01A190, ZHAA01A200, \n" +
            "case(ZHAA01A210) when '00' then '斜坡' when '01' then '滑坡' when '02' then '崩塌' when '03' then '泥石流' when '04' then '地面塌陷' when '05' then '地裂缝' when '06' then '地面沉降' when '07' then '其它' end as ZHAA01A210 ," +
            " ZHAA01A211, ZHAA01A300,\n" +
            "     ZHAA01A310,ZHAA01A320, ZHAA01A330, ZHAA01A340, ZHAA01A350, ZHAA01A360, \n" +
            "case (ZHAA01A370) when  'A' then '特大型' when 'B' then '大型' when  'C' then '中型' when 'D' then '小型' end as ZHAA01A370," +
            "case (ZHAA01A380) when  'A' then '分散农户' when 'B' then '聚集区' when  'C' then '学校' when 'D' then '场镇' \n" +
            "when  'E' then '县城' when 'F' then '公路' when  'G' then '河道' when 'H' then '景区'   when 'I' then '其它' \n" +
            "end as ZHAA01A380," +

            "     case(ZHAA01A381) when 0 then '否' when 1 then '是' end as ZHAA01A381 ," +
            "case(ZHAA01A382) when 0 then '否' when 1 then '是' end as ZHAA01A382 ," +
            "case(ZHAA01A383) when 0 then '否' when 1 then '是' end as ZHAA01A383 ," +
            "     ZHAA01A390,     ZHAA01A400,    ZHAA01A410,\n" +
            "case (ZHAA01A420) when  'A' then '特大型' when 'B' then '大型' when  'C' then '中型' when 'D' then '小型' end as ZHAA01A420, " +
            "      ZHAA01A490,     ZHAA01A500,     ZHAA01A510,    ZHAA01A710,\n" +
            "     ZHAA01A715,     ZHAA01A720,     ZHAA01A725,     ZHAA01A730,     ZHAA01A735,     ZHAA01A740,\n" +
            "     ZHAA01A741,     ZHAA01A742,     ZHAA01A743,     ZHAA01A744,     ZHAA01A745,     ZHAA01A748,\n" +
            "     ZHAA01A750,     ZHAA01A751,     ZHAA01A752,     ZHAA01A753,     ZHAA01A754,     ZHAA01A220,\n" +
            "     ZHAA01A230,     ZHAA01A240,     ZHAA01A250,     ZHAA01A260,     ZHAA01A270,     ZHAA01A280,\n" +
            "     ZHAA01A290,     ZHAA01A430,     ZHAA01A440,     ZHAA01A450,     ZHAA01A460,     ZHAA01A470,\n" +
            "     ZHAA01A480,     ZHAA01A520,     ZHAA01A530,     ZHAA01A540,     ZHAA01A550,     ZHAA01A560,\n" +
            "     ZHAA01A570,     ZHAA01A580,     ZHAA01A590,     ZHAA01A600,     ZHAA01A610,     ZHAA01A620,\n" +
            "     ZHAA01A630,     ZHAA01A670,     ZHAA01A680,     ZHAA01A681,    " +
            "case (ZHAA01A690) when  1 then '是' when 0 then '否' end as ZHAA01A690," +
            "case (ZHAA01A695) when  1 then '是' when 0 then '否' end as ZHAA01A695," +
            "case (ZHAA01A700) when  1 then '是' when 0 then '否' end as ZHAA01A700," +
            "case (ZHAA01A755) when  1 then '是' when 0 then '否' end as ZHAA01A755," +
            "case (ZHAA01A756) when  'A' then '群测群防' when 'B' then '专业监测' when  'C' then '搬迁避让' when 'D' then '工程治理' when 'E' then '排危除险' end as ZHAA01A756 ," +
            "case (ZHAA01A757) when  1 then '是' when 0 then '否' end as ZHAA01A757," +
            "     ZHAA01A758, " +
            "case (ZHAA01A760) when  '1' then '巡视' when '2' then '简易监测' when  '3' then '专业监测' when '9' then '其它' end as ZHAA01A760, " +
            "case (ZHAA01A761) when  1 then '是' when 0 then '否' end as ZHAA01A761," +
            "case (ZHAA01A762) when  1 then '是' when 0 then '否' end as ZHAA01A762," +
            "case (ZHAA01A763) when  1 then '是' when 0 then '否' end as ZHAA01A763," +
            "     ZHAA01A764,     " +
            "case (ZHAA01A765) when  '1' then '呼喊' when '2' then '警报' when  '3' then '鸣笛' when  '4' then '广播' when  '5' then '手摇报警器' when  '6' then '敲锣' when '9' then '其它' end as ZHAA01A765, " +
            "     ZHAA01A770,\n" +
            "     ZHAA01A775,     ZHAA01A785,     ZHAA01A805,     " +
            "case (ZHAA01A810) when  '1' then '省级预案点' when '2' then '市（州）级预案点' when  '3' then '县（市、区）级预案点' when '4' then '乡（镇）级预案点' end as ZHAA01A810, " +
            "    ZHAA01A815,     ZHAA01A820,\n" +
            "     ZHAA01A825,    " +
            "case (ZHAA01A830) when  '1' then '地震' when '2' then '降雨' when  '3' then '人为因素' when '4' then '其它' end as ZHAA01A830, " +
            " ZHAA01A835,     ZHAA01A837,     ZHAA01A840,     ZHAA01A845,\n" +
            "     ZHAA01A850,     ZHAA01A855,     ZHAA01A860,     ZHAA01A865,     ZHAA01A870,    " +
            "case (ZHAA01A875) when  1 then '是' when 0 then '否' end as ZHAA01A875," +
            "     ZHAA01A880,    " +
            "case (ZHAA01A890) when  '1' then '小型' when '2' then '中型' when  '3' then '大型' when '4' then '特大型' end as ZHAA01A890, " +

            " ZHAA01A900,     ZHAA01A905,     ZHAA01A910,     ZHAA01A915,\n" +
            "     ZHAA01A920,     ZHAA01A925,     ZHAA01A930,     ZHAA01A935,     ZHAA01A936,     ZHAA01A937,\n" +
            "     ZHAA01A938,     ZHAA01A010";
}


