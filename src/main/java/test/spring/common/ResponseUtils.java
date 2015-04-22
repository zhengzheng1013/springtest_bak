package test.spring.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import test.spring.web.ResultCode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResponseUtils {

	private static final Logger logger = Logger.getLogger(ResponseUtils.class);

	private static final String JSONP_CALLBACK_REGEX = "^[\\w$]+$";

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static String DEFAULT_JSON_CALLBACK = "jsoncallback";

	public static final String RESPONSE_CODE = "code";

	public static final String RESPONSE_MSG = "msg";

	public static final String RESPONSE_DATA = "data";

	public static void setCommonHeader(HttpServletResponse response) {
		String encoding = response.getCharacterEncoding();
		if (StringUtils.isBlank(encoding)) {
			encoding = DEFAULT_ENCODING;
			response.setCharacterEncoding(encoding);
		}
		response.setContentType("text/html");
		response.setHeader("Content-Type", "text/html;charset=" + encoding);
	}

	public static void setCacheHeader(HttpServletResponse response, int seconds) {
		long now = System.currentTimeMillis();
		response.setHeader("Cache-Control", "max-age=" + seconds);
		response.setDateHeader("Last-Modified", now);
		response.setDateHeader("Expires", now + seconds * 1000);
	}

	public static void setHtmlContentCacheHeader(HttpServletResponse response, int seconds) {
		setCommonHeader(response);
		setCacheHeader(response, seconds);
	}

	public static void setNoCacheHeader(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	public static void setHtmlContentTypeNoCacheHeader(HttpServletResponse response) {
		setCommonHeader(response);
		setNoCacheHeader(response);
	}

	public static void writeResponse(HttpServletRequest request, HttpServletResponse response, int code, String msg,
			Object data) {
		setCommonHeader(response);
		String callback = request.getParameter(DEFAULT_JSON_CALLBACK);
		if (StringUtils.isNotBlank(callback)) {
			if (!Pattern.matches(JSONP_CALLBACK_REGEX, callback)) {
				try {
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				} catch (IOException e) {
					logger.error("", e);
				}
				return;
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(RESPONSE_CODE, code);
		result.put(RESPONSE_MSG, msg);
		result.put(RESPONSE_DATA, data);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String content = null;
			if (StringUtils.isBlank(callback)) {
				content = JSON.toJSONString(result);
			} else {
				content = callback
						+ "("
						+ JSON.toJSONString(result, SerializerFeature.BrowserCompatible,
								SerializerFeature.QuoteFieldNames) + ")";
			}
			writer.print(content);
		} catch (Exception e) {
			logger.info(e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}
	
	public static void writeSuccessResponse(HttpServletRequest request, HttpServletResponse response, Object data) {
		ResultCode resultCode = ResultCode.SUCCESS;
		writeResponse(request, response, resultCode.getCode(), resultCode.getMsg(), data);
	}
	
	public static void writeErrorResponse(HttpServletRequest request, HttpServletResponse response, Object data) {
		ResultCode resultCode = ResultCode.SERVER_ERROR;
		writeResponse(request, response, resultCode.getCode(), resultCode.getMsg(), data);
	}
	
	public static void writeEmptyResponse(HttpServletRequest request, HttpServletResponse response, ResultCode resultCode) {
		writeResponse(request, response, resultCode.getCode(), resultCode.getMsg(), null);
	}

	public static String getDefaultJsonCallback() {
		return DEFAULT_JSON_CALLBACK;
	}

	public static void setDefaultJsonCallback(String callback) {
		if (StringUtils.isNotBlank(callback)) {
			DEFAULT_JSON_CALLBACK = callback;
		}
	}
}