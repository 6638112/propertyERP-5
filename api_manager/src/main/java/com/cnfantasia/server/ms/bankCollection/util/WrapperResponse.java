package com.cnfantasia.server.ms.bankCollection.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 利用HttpServletResponseWrapper封装HttpServletResponse，使HttpServletResponse采用我们自己定义的输入流(OutputStream)
 * 这样，我们就可以通过这个OutputStream得到目标jsp页面内容。
 * 
 * @author liyulin
 * @version 1.0 2017年5月25日 下午3:43:43
 */
public class WrapperResponse extends HttpServletResponseWrapper {
	private final Log logger = LogFactory.getLog(getClass());
	private MyPrintWriter tmpWriter;
	private ByteArrayOutputStream output;

	public WrapperResponse(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
		output = new ByteArrayOutputStream();
		;// 真正存储数据的返回流（保存数据返回的结果）
		tmpWriter = new MyPrintWriter(output);
	}

	public String getContent() {
		String str = "";
		try {
			//刷新该流的缓冲，详看java.io.Writer.flush()
			tmpWriter.flush();
			str = tmpWriter.getByteArrayOutputStream().toString("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("不支持的编码异常,Unsupported Encoding Exception！");
		} finally {
			try {
				output.close();
				tmpWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("释放资源异常，release resource error!");
			}

		}
		return str;
	}

	//覆盖getWriter()方法，使用我们自己定义的Writer
	public PrintWriter getWriter() throws IOException {
		return tmpWriter;
	}

	public void close() throws IOException {
		tmpWriter.close();
	}

	//自定义PrintWriter，为的是把response流写到自己指定的输入流当中
	//而非默认的ServletOutputStream
	private static class MyPrintWriter extends PrintWriter {
		ByteArrayOutputStream myOutput;

		//此即为存放response输入流的对象

		public MyPrintWriter(ByteArrayOutputStream output) {
			super(output);
			myOutput = output;
		}

		public ByteArrayOutputStream getByteArrayOutputStream() {
			return myOutput;
		}

	}
}
