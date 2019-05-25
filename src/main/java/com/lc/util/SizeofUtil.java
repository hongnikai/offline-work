package com.lc.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *  获取对象大小工具类
 *  create by: lc
 * @author  teasp
 * url: https://teasp.iteye.com/blog/1870871
 */
public class SizeofUtil {

    public static final int OBJ_BASIC_LEN = 8 * 8;
    public static final int ARRAY_BASIC_LEN = 12 * 8;
    public static final int OBJ_REF_LEN = 4 * 8;

    public static final int ALIGN = 8 * 8;
    private static Unsafe UNSAFE;

    static {
        try
        {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 原始类型的种类，以及每个类型所占空间，单位为bit
     * @author Administrator
     *
     */
    private enum PType
    {
        布尔(8)/*Java语言规定是1个bit*/,字节(8),字符(16),短整(16),
        整形(32),浮点(32),长整(64),双精(64);

        private int bits;

        private PType(int bits)
        {
            this.bits = bits;
        }

        public int getBits() {
            return bits;
        }
    }

    /**
     * 计算arr数组在虚拟机中所占的内存，单位为bit
     * @param arr
     * @return
     */
    private static int bitsofArray(Object arr)
    {
        int bits = 0;
        if (arr == null)
        {
            return bits;
        }

        bits += ARRAY_BASIC_LEN;

        Class<?> c = arr.getClass();
        if (c.isArray() == false)
        {
            throw new RuntimeException("Must be array!");
        }

        if (c == boolean[].class)
        {
            bits += PType.布尔.getBits() * ((boolean[])arr).length;
        }
        else if (c == byte[].class)
        {
            bits += PType.字节.getBits() * ((byte[])arr).length;
        }
        else if (c == char[].class)
        {
            bits += PType.字符.getBits() * ((char[])arr).length;
        }
        else if (c == short[].class)
        {
            bits += PType.短整.getBits() * ((short[])arr).length;
        }
        else if (c == int[].class)
        {
            bits += PType.整形.getBits() * ((int[])arr).length;
        }
        else if (c == float[].class)
        {
            bits += PType.浮点.getBits() * ((float[])arr).length;
        }
        else if (c == long[].class)
        {
            bits += PType.长整.getBits() * ((long[])arr).length;
        }
        else if (c == double[].class)
        {
            bits += PType.双精.getBits() * ((double[])arr).length;
        }
        else
        {
            Object[] os = (Object[])arr;
            for (Object o : os)
            {
                bits += OBJ_REF_LEN + bitsof(o);
            }
        }

        //补齐
        if (bits%ALIGN > 0)
        {
            bits += (ALIGN - bits%ALIGN);
        }

        return bits;
    }

    /**
     * 计算obj对象在虚拟机中所占的内存，单位为bit
     * @param obj
     * @return
     */
    private static int bitsof(Object obj)
    {
        if (obj == null)
        {
            return 0;
        }

        if (obj.getClass().isArray())
        {
            return bitsofArray(obj);
        }

        return getObjBits(obj, obj.getClass(), false);
    }

    /**
     * 计算obj对象在虚拟机中所占的内存，单位为bit。
     * 如果isPapa为true，则表明计算的是obj对象父类定义的属性。
     *
     * @param obj
     * @param clazz
     * @param isPapa
     * @return
     */
    private static int getObjBits(Object obj, Class<?> clazz, boolean isPapa)
    {
        int bits = 0;
        if (obj == null)
        {
            return bits;
        }

        bits += OBJ_BASIC_LEN;
        if (isPapa)
        {
            bits = 0;
        }

        Field[] fields = clazz.getDeclaredFields();
        if (fields != null)
        {
            for (Field field : fields)
            {
                //静态属性不参与计算
                if (Modifier.isStatic(field.getModifiers()))
                {
//                    System.out.println("static " + field.getName());
                    continue;
                }
                Class<?> c = field.getType();
                if (c == boolean.class)
                {
                    bits += PType.布尔.getBits();
                }
                else if (c == byte.class)
                {
                    bits += PType.字节.getBits();
                }
                else if (c == char.class)
                {
                    bits += PType.字符.getBits();
                }
                else if (c == short.class)
                {
                    bits += PType.短整.getBits();
                }
                else if (c == int.class)
                {
                    bits += PType.整形.getBits();
                    System.out.println(field.getName() + "=" + UNSAFE.getInt(obj, UNSAFE.objectFieldOffset(field)));
                }
                else if (c == float.class)
                {
                    bits += PType.浮点.getBits();
                }
                else if (c == long.class)
                {
                    bits += PType.长整.getBits();
                }
                else if (c == double.class)
                {
                    bits += PType.双精.getBits();
                }
                else if (c == void.class)
                {
                    //nothing
                } else if (c.isArray())
                {//是数组
                    Object o = UNSAFE.getObject(obj, UNSAFE.objectFieldOffset(field));
                    bits += OBJ_REF_LEN;
                    if (o != null)
                    {
                        try
                        {
                            bits += bitsofArray(o);
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                } else
                {//普通对象
                    Object o = UNSAFE.getObject(obj, UNSAFE.objectFieldOffset(field));
                    bits += OBJ_REF_LEN;
                    if (o != null)
                    {
                        try
                        {
                            bits += bitsof(o);
                        } catch (Exception e)
                        {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }

        Class<?> papa = clazz.getSuperclass();
        if (papa != null)
        {
            bits += getObjBits(obj, papa, true);
        }

        //补齐，当计算父类属性时，因为是对同一个对象在进行统计，所以不要补齐。
        //一个对象只做一次补齐动作。
        if (false == isPapa)
        {
            if (bits%ALIGN > 0)
            {
                bits += (ALIGN - bits%ALIGN);
            }
        }

        return bits;
    }

    /**
     * 计算obj对象在虚拟机中所占的内存，单位为byte
     * @param obj
     * @return
     */
    public static int sizeof(Object obj)
    {
        return bitsof(obj)/8;
    }



}
