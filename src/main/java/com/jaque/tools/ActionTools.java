package com.jaque.tools;

import com.jaque.testUtils.TestUtils;

/**
 * 失败后重试或者失败后继续后续动作的动作处理类。
 * 可自定义动作，只需要重写dosomething方法即可，可自定义隔多久后重试。
 * 新增自定义重试次数
 * @author Jaqueliao
 */
public abstract class ActionTools {
    int sec;
    int retryCount = 2 ;

    public ActionTools() {
        this.sec = 3;
    }

    public ActionTools(int sec) {
        this.sec = sec;
    }
    public ActionTools(int sec, int retryCount) {
        this(sec);
        this.retryCount = retryCount;
    }
    public void dosomething() throws Exception {
        System.out.println("dosomething");
    }

    /**
     * 失败后重做，重做次数由retryCount定义，再次失败则报错
     *
     * @throws Exception 异常
     */
    public void retry() throws Exception {
        if (retryCount > 1 ){
            try {
                dosomething();
            } catch (Exception e) {
                if (!TestUtils.isTestServer()) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(sec * 1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                retryCount--;
                retry();
            }
        }else if(retryCount == 1){
            dosomething();
        }
    }

    /**
     * 只做一次的动作
     * @throws Exception 异常
     */
    public void doOnce() throws Exception {
        try {
            dosomething();
        } catch (Exception e) {
            if (!TestUtils.isTestServer()) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final int i = 100;
        new ActionTools(3,5) {
            @Override
            public void dosomething() throws Exception {
                System.out.println("dosomething" + i);
                throw new Exception();
            }
        }.retry();

    }

}
