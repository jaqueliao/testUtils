package com.jaque.tools;

import com.jaque.testUtils.HttpUtil;
import com.jaque.testUtils.TestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 失败后重试或者失败后继续后续动作的动作处理类。
 * 可自定义动作，只需要重写dosomething方法即可，可自定义隔多久后重试。
 * 新增自定义重试次数
 *
 * @author Jaqueliao
 */
public abstract class ActionTools {
    private int sec;
    private int retryCount = 2;

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
        if (retryCount > 1) {
            try {
                dosomething();
            } catch (Exception e) {
                if (!TestUtils.isTestServer()) {
                    e.printStackTrace();
                }
                TestUtils.sleep(sec*1000);
                retryCount--;
                retry();
            }
        } else if (retryCount == 1) {
            dosomething();
        }
    }

    /**
     * 只做一次的动作
     *
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
        String rtmpUrl = "rtmp://pili-publish.test.zycourse.com/testzycourse/5b58467020a05d6b0b29a223?key=716109568643432448";
        HttpUtil.doGet("http://139.219.142.69/fileUpload/servlet/AddObsUrl?rtmpUrl=" + rtmpUrl);
        new ActionTools(2, 10) {
            @Override
            public void dosomething() throws Exception {
                String s = new String(HttpUtil.doGet("http://139.219.142.69/fileUpload/servlet/getObsState"));
                String[] ss = s.split("\n");
                HashMap<String, String> map = new HashMap<>();
                for (String sss : ss) {
                    if (sss.contains("=")) {
                        String[] ssss = sss.split("=");
                        map.put(ssss[0], ssss[1]);
                    }
                }
                if (!"true".equals(map.get("isLiving"))) {
                    throw new Exception();
                }
                System.out.print(s);
            }
        }.retry();

        Thread.sleep(15000);

        HttpUtil.doGet("http://139.219.142.69/fileUpload/servlet/updateObsState?rtmpUrl=" + rtmpUrl + "&doClose=true");
        new ActionTools(2, 5) {
            @Override
            public void dosomething() throws Exception {
                String s = new String(HttpUtil.doGet("http://139.219.142.69/fileUpload/servlet/getObsState"));
                String[] ss = s.split("\n");
                Map<String, String> map = new HashMap<>();
                for (String sss : ss) {
                    if (sss.contains("=")) {
                        String[] ssss = sss.split("=");
                        map.put(ssss[0], ssss[1]);
                    }
                }
                if (!"false".equals(map.get("doClose"))) {
                    throw new Exception();
                }
                System.out.print(s);
            }
        }.retry();
    }

}
