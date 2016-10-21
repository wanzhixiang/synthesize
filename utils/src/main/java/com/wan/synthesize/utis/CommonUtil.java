package com.wan.synthesize.utis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;


/**
 * 通用工具类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	//private static final String APPID = "wx75ac52d9439aefa1";
	//private static final String APPSERCERT = "53b48fee9f890a2dbb52abddb4421532";
	//wx6c71d1a3bba37663
	
	/**
	 * 微信的appid
	 */
	private static  String APPID;
	/**
	 * 微信的sercert
	 */
	private static  String APPSERCERT;
	/**
	 * 本项目的地址（微信）
	 */
	private static String LOCALURL;
	private static String YUCURL;
	private static String TEMPLATE;
	
	private static String OVERTIME;
	/**
	 * app接口地址
	 */
	private static String APIADDR;
	/**
	 * 后台管理地址
	 */
	private static String HTGLADDR;
	public static Map<String,String> PRO_MAP ;
	private static Map<String,String> JSMAP;
	
	
	//微信接口token
	public static Token token;
	//上次获取token的时间戳
	public static Long lastTokenTime =0L;
	//有效期(秒)
	public static int token_expires_in=7200;
	
	//微信js-sdk 的token
	public static String ticket;
	//上次获取微信 js-sdk token 的时间戳
	public static Long lastTicketTime =0L;
	/**
	 * 订单过期时间
	 * @return
	 */
	public static String getOVERTIME() {
		return OVERTIME;
	}

	//有效期
	public static int ticket_expires_in=7200;
	
	

	// 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	// 获取JS ticket
	public final static String js_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public static String getAPPID() {
		return APPID;
	}

	public static String getAPPSERCERT() {
		return APPSERCERT;
	}
	
	
	public static String getLOCALURL() {
		return LOCALURL;
	}
	
	public static String getYucURL() {
		return YUCURL;
	}
	//图片显示地址
	public static String getAPIADDR() {
		return APIADDR;
	}

	public static String getTEMPLATE() {
		return TEMPLATE;
	}

	public static String getHTGLADDR() {
		return HTGLADDR;
	}

	public static String getWeixinOuthUrl(String sourceURL,String type){
		try{
			if(ObjectUtil.isEmpty(sourceURL)){
				sourceURL = LOCALURL+"/weixin";
			}
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?a" +
					"ppid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
			url = url.replace("APPID", APPID).
					replace("REDIRECT_URI", java.net.URLEncoder.encode(sourceURL, "UTF-8"))
					.replace("SCOPE", "snsapi_userinfo")
					.replace("STATE", type);
			return url;
		}catch(Exception e){
			return "/error";
		}
		
	}
	
	public static String getWeixinOpenUrl(String sourceURL,String type){
		try{
			if(ObjectUtil.isEmpty(sourceURL)){
				sourceURL = LOCALURL+"/weixin";
			}
			String url = "https://open.weixin.qq.com/connect/qrconnect?" +
					"appid=APPID&redirect_uri=REDIRECT_URI&" +
					"response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
			url = url.replace("APPID", CommonUtil.PRO_MAP.get("OPEN_APPID")).
					replace("REDIRECT_URI", java.net.URLEncoder.encode(sourceURL, "UTF-8"))
					.replace("SCOPE", "snsapi_login")
					.replace("STATE", type);
			return url;
		}catch(Exception e){
			return "/error";
		}
		
	}
	
	static{
		InputStream in=null;
		try {
			System.out.println(Thread.currentThread().getContextClassLoader().getResource("")+"url.properties");
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("url.properties"); 
			Properties p = new Properties();  
			p.load(in);
			APPID = p.getProperty("APPID");
			APPSERCERT = p.getProperty("APPSERCERT");
			LOCALURL = p.getProperty("localurl");
		
			PRO_MAP = new HashMap<String, String>();
			Set set = p.keySet();
			for(Object o : set){
				String key = (String)o;
				PRO_MAP.put(key, p.getProperty(key));
			}
			 getToken(APPID, APPSERCERT);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(APPID==null||APPSERCERT==null){
			APPID = "";
			APPSERCERT = "";
		}
		     
	}
	
	/**
	 * 发送https请求
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static Token getToken(String appid, String appsecret) {
		if(token!=null&&(new Date().getTime()-lastTokenTime)/1000<token.getExpiresIn()-2000){
			log.info("have token 1 ");
			return token;
		}
		log.info("get new token 1");
		Token token = null;
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				CommonUtil.token = token;
				CommonUtil.lastTokenTime = new Date().getTime();
				System.out.println(CommonUtil.token.getAccessToken());
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	public static Map<String,String> getJsConfig(String url) {
		if(ObjectUtil.isEmpty(ticket)||(CommonUtil.lastTicketTime-CommonUtil.ticket_expires_in)/1000<CommonUtil.lastTicketTime-2000){
			Token token = getToken();
			String requestUrl = js_url.replace("ACCESS_TOKEN", token.getAccessToken());
			JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
			if (null != jsonObject) {
				try {
				/*	token = new Token();
					token.setAccessToken(jsonObject.getString("ticket"));
					token.setExpiresIn(jsonObject.getInt("expires_in"))*/;
					CommonUtil.ticket = jsonObject.getString("ticket");
					CommonUtil.lastTicketTime = new Date().getTime();
					CommonUtil.ticket_expires_in = jsonObject.getInt("expires_in");
				} catch (JSONException e) {
					token = null;
					// 获取token失败
					log.error("获取 js token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}
		}
		return CommonUtil.sign(CommonUtil.ticket, url);
		
		
		

	}
	
	
	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static Token getToken() {
		if(CommonUtil.token!=null&&(new Date().getTime()-lastTokenTime)/1000<CommonUtil.token.getExpiresIn()-2000){
			log.info("have token 2 ");
			return token;
		}
		log.info("get new token 2");
		Token token = null;
		String requestUrl = token_url.replace("APPID", APPID).replace("APPSECRET", APPSERCERT);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				CommonUtil.token = token;
				CommonUtil.lastTokenTime = new Date().getTime();
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = ".jpg";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	
	
	
    /** 
    * @Title: sign 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param jsapi_ticket
    * @param @param url
    * @param @return    设定文件 
    * @return Map<String,String>    返回类型 
    * @throws 
    */
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        JSONObject obj = JSONObject.fromObject(ret);
        log.info(obj.toString());
        System.out.println(obj.toString());
        return ret;
    }
    
/*    public static void main(String args[]){
    	CommonUtil.getJsConfig("http://www.baidu.com");
    }*/
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        //return UUID.randomUUID().toString();
    	return "Wm3WZYTPz0wzccnW";
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	
	
}