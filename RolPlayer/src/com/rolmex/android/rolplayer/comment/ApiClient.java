
package com.rolmex.android.rolplayer.comment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiClient {
    public static final String UTF_8 = "UTF-8";

    public static final String DESC = "descend";

    public static final String ASC = "ascend";

    private final static int TIMEOUT_CONNECTION = 20000;

    private final static int TIMEOUT_SOCKET = 20000;

    private final static int RETRY_TIME = 3;

    /**
     * 获取网络图片
     * 
     * @param url
     * @return
     */
    public static Bitmap getBitmapByUrl(String urlString) {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            url = new URL(urlString);
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            opt.inSampleSize = 1;
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(25000);
            connection.setReadTimeout(90000);
            // connection.setRequestMethod("POST");
            // connection.setRequestMethod("GET");
            // ��������ͷ
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep_Alive");
            connection.setRequestProperty("Accept_Charset", "utf-8");
            int code = connection.getResponseCode();
            if (code == 200) {
                in = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in, null, opt);
            } else {
                bitmap = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (url != null) {
                url = null;
            }
            if (connection != null) {
                connection = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bitmap;

    }

}
