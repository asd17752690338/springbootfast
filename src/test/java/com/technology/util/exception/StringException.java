package com.technology.util.exception;

/**
 * 运行时异常
 */
public class StringException extends Exception {

    public StringException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public static void test() {
        try {
            System.out.println(111);
            int i = 0;
            if (i == 0) {
                throw new StringException("程序错误");
            }
            System.out.println(11);
        } catch (StringException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
    }

}
