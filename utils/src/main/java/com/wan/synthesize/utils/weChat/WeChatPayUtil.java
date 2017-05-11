package com.wan.synthesize.utils.weChat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class WeChatPayUtil {
	
	private static String APPID = "";
	private static String LOCALURL = "";
	private static String APPSERCERT = "";
	private static String MCH_ID = "";
	private static String key = "";
	private static String OPEN_APPSERCERT = "";
	private static String open_APPID = "";
	private static String open_MCH = "";
	private static String open_KEY = "";
	private static String cert = "";
	private static String open_cert = "";
	private static String notify_url="";
	
	public static String getKey() {
		return key;
	}
	public static String getAppid() {
		return APPID;
	}
	public static String getLocalurl() {
		return LOCALURL;
	}
	public static String getAppsercert() {
		return APPSERCERT;
	}
	public static String getMchId() {
		return MCH_ID;
	}
	
	public static String getOPEN_APPSERCERT() {
		return OPEN_APPSERCERT;
	}
	public static String getOpen_APPID() {
		return open_APPID;
	}

	public static String getOpen_MCH() {
		return open_MCH;
	}

	public static String getOpen_KEY() {
		return open_KEY;
	}

	public static String getCert() {
		return cert;
	}
	public static String getOpen_cert() {
		return open_cert;
	}
	public static String getNotify_url() {
		return notify_url;
	}


	static{
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("url.properties");
			Properties p = new Properties();
			p.load(in);
			LOCALURL = p.getProperty("localurl");
			APPID = p.getProperty("APPID");
			APPSERCERT = p.getProperty("APPSERCERT");
			OPEN_APPSERCERT = p.getProperty("open_APPSERCERT");
			open_APPID = p.getProperty("open_APPID");
			MCH_ID = p.getProperty("mch_id");
			key = p.getProperty("key");
			open_MCH = p.getProperty("open_mch");
			open_KEY = p.getProperty("open_key");
			cert = p.getProperty("cert");
			open_cert = p.getProperty("open_cert");
			notify_url = p.getProperty("notify_url");
		} catch (IOException e) {
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
	}
	
	/**
	 * 解析xml字符串 获取指定节点值
	 * @param text xml字符串
	 * @param nodeName 指定节点
	 * @return 指定节点的值
	 */
	public static String parseXmlText(String text,String nodeName){
		try {
			  Document doc=(Document)DocumentHelper.parseText(text);   
	             Element books = doc.getRootElement();   
	            // Iterator users_subElements = books.elementIterator("UID");//指定获取那个元素   
	             Iterator Elements = books.elementIterator();   
	            while(Elements.hasNext()){   
	                Element user = (Element)Elements.next();   
	                if(user.getName().equals(nodeName)){//如果是指定节点
	                	return user.getText();
	                }
	            }
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * 签名
	 * @param params 签名的list集合
	 * @return
	 */
	public static String sign(List<NameValuePair> params,String type) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		if("APP".equals(type)){
			sb.append(open_KEY);//APP支付签名
		}else{
			sb.append(key);//公众号支付签名
		}
		

		String sign = WXMD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		return sign;
	}
	/**
	 * 转换成xml格式
	 * @param params
	 * @return
	 */
	public static  String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<"+params.get(i).getName()+">");


			sb.append(params.get(i).getValue());
			sb.append("</"+params.get(i).getName()+">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	/**
	 * 微信退款工具类
	 * @param certPath 证书路径（java只加载.p12证书）"C:\\Users\\Administrator\\Desktop\\wzx\\cert(app)\\
	 * @param pwd 证书密码 (商户ID)
	 * @param params 退款参数 xml格式
	 * @return
	 */
	public static String refund(String certPath,String pwd,String params) throws Exception{
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// 读取本机存放的PKCS12证书文件
		FileInputStream instream = new FileInputStream(
				new File(certPath+"apiclient_cert.p12"));
		try {
			// 指定PKCS12的密码(商户ID)
			keyStore.load(instream, pwd.toCharArray());
		} finally {
			instream.close();
		}

		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, pwd.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		try {

			HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");//退款接口
			System.out.println("executing request" + httppost.getRequestLine());
			StringEntity reqParam = new StringEntity(params);
			httppost.setEntity(reqParam);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: "
							+ entity.getContentLength());
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(entity.getContent()));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						System.out.println(text);
						return text;
					}
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return null;
	}

	public static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
