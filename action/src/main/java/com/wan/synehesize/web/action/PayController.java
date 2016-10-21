package com.wan.synehesize.web.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;


@Controller
public class PayController {

	/**
	 * 日志.
	 */
	private final static Logger logger = Logger.getLogger(PayController.class);
	

	//@Autowired
	//private PayService payService;


/*	*//**
	 * 微信公众号支付
	 * @param id
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 *//*
	@RequestMapping(value = "/wechat_pay")
	@ResponseBody
	public ReturnResult wechat_pay(String id,HttpServletRequest request) throws UnsupportedEncodingException {

		ReturnResult result = new ReturnResult();
		result.setSuccess(false);
		try {
			//统一下单接口地址
			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
			// 用户在公众中的openid; 当支付类型为JSAPI时 必传
			String openid = userdetail.getUserAccount();
			// APPID微信公众号appid
			String appid = WeChatPayUtil.getAppid();
			// title
			String attach = "dayaweigou";
			// 描述
			String body = "dayaweigou";
			// 微信公众平台中的商户号
			String mch_id = WeChatPayUtil.getMchId();
			// 随机数
			String nonce_str = UUID.randomUUID().toString().replaceAll("-", "");
			// 商户订单号
			String out_trade_no = order.getSn();
			// 支付请求ip
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			String spbill_create_ip = ip;
			// 支付金额
			Integer total_fee = 1;// Math.round(order.getPrice() * 100)
			// 支付类型 H5支付类型为JSAPI
			String trade_type = "JSAPI";
			// 微信异步通知地址
			String notify_url = WeChatPayUtil.getNotify_url();
			// 统一支付签名
			String sign = "";
			// 生成预付款订单参数
			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			packageParams.add(new BasicNameValuePair("appid", appid));
			packageParams.add(new BasicNameValuePair("attach", attach));
			packageParams.add(new BasicNameValuePair("body", body));
			packageParams.add(new BasicNameValuePair("mch_id", mch_id));
			packageParams.add(new BasicNameValuePair("nonce_str", nonce_str));
			packageParams.add(new BasicNameValuePair("notify_url", notify_url));
			packageParams.add(new BasicNameValuePair("openid", openid));
			packageParams.add(new BasicNameValuePair("out_trade_no",
					out_trade_no));
			packageParams.add(new BasicNameValuePair("spbill_create_ip",
					spbill_create_ip));
			packageParams.add(new BasicNameValuePair("total_fee", total_fee
					.toString()));
			packageParams.add(new BasicNameValuePair("trade_type", trade_type));
			sign = WeChatPayUtil.sign(packageParams, trade_type);// 生成签名
			packageParams.add(new BasicNameValuePair("sign", sign));
			String xmlstring = WeChatPayUtil.toXml(packageParams);
			String text = SendUrl.sendPost(url, xmlstring);
			System.out.println(text);
			// 获取统一下单号
			String prepay_id = WeChatPayUtil.parseXmlText(text, "prepay_id");
			if (prepay_id != null) {
				result.setSuccess(true);
				String timeStamp = WeChatPayUtil.create_timestamp();
				String signType = "MD5";
				String pack = "prepay_id=" + prepay_id;
				List<NameValuePair> payParams = new LinkedList<NameValuePair>();
				payParams.add(new BasicNameValuePair("appId", appid));
				payParams.add(new BasicNameValuePair("nonceStr", nonce_str));
				payParams.add(new BasicNameValuePair("package", pack));
				payParams.add(new BasicNameValuePair("signType", signType));
				payParams.add(new BasicNameValuePair("timeStamp", timeStamp));
				String paySign = WeChatPayUtil.sign(payParams, trade_type);
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("timeStamp", timeStamp);
				paramMap.put("nonceStr", nonce_str);
				paramMap.put("signType", signType);
				paramMap.put("package", pack);
				paramMap.put("paySign", paySign);
				paramMap.put("appId", appid);
				System.out.println(paramMap);
				result.setRetval(paramMap);
				payService.updateWeixinSn(out_trade_no, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetcode(false);
		}
		return result;
	}*/
	/**
	 * 微信支付异步通知
	 * @param req
	 *//*
	@RequestMapping(value = "/notify_pay")
	@ResponseBody
	public String notify(HttpServletRequest req,@RequestBody String xml){
		System.out.println(xml);
		String out_trade_no = WeChatPayUtil.parseXmlText(xml, "out_trade_no");
		String pay_status = WeChatPayUtil.parseXmlText(xml, "result_code");
*//*		String out_trade_no = req.getParameter("out_trade_no");//商户订单号
		String pay_status = req.getParameter("result_code");//业务结果
*//*
		if("SUCCESS".equals(pay_status)){//支付成功
			//
			payService.updateWinxinTrade(out_trade_no);
			return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		}
		return null;
	}*/
	
	/**
	 * 获取js config
	 * @param req
	 */
	/*@RequestMapping(value = "/getJSConfig")
	@ResponseBody
	public ResponseEntity getConfig(HttpServletRequest req,String url){
		ResponseEntity result = new ResponseEntity();
		result.setRetcode(false);
		try {
			result.setRetcode(true);
			Map<String, String> map = CommonUtil.getJsConfig(url);
			map.put("appId", WeChatPayUtil.getAppid());
			result.setRetval(map);
		}catch(Exception e){
			result.setRetcode(false); 
			e.printStackTrace();
		}
		return result;
	}*/
	
}
