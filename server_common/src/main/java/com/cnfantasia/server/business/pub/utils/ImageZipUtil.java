package com.cnfantasia.server.business.pub.utils;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import org.apache.commons.io.IOUtils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

public class ImageZipUtil {

	/**
	 * 压缩图片文件<br>
	 * 先保存原文件，再压缩、上传
	 *
	 * @param oldFile   要进行压缩的文件全路径
	 * @param width     宽度
	 * @param height    高度
	 * @param quality   质量, 兼容以前，参数保留，不使用，默认为1.0f
	 * @param smallIcon 压缩后图片路径
	 * @return 返回压缩后的文件的全路径
	 */
	public static String zipImageFile(final String oldFile, Integer width, Integer height,
									  float quality, final String smallIconFile) throws IOException {
		if (oldFile == null) {
			return null;
		}
		File in = new File(oldFile);
		File out = new File(smallIconFile);
		Image srcImage = ImageIO.read(in);
		ScaleParameter scaleParam = getScaleParam(srcImage, width, height);

		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		WriteRender wr = null;
		try {
			inStream = new FileInputStream(in);
			outStream = new FileOutputStream(out);
			ImageRender rr = new ReadRender(inStream);
			ScaleRender sr = new ScaleRender(rr, scaleParam);
			wr = new WriteRender(sr, outStream);
			//图像处理
			wr.render();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inStream);
			IOUtils.closeQuietly(outStream);
			if (wr != null) {
				try {
					wr.dispose();
				} catch (SimpleImageException ignore) {
					;
				}
			}
		}
		return smallIconFile;
	}

	/**
	 * 处理长宽，一个为空，另一个等比例计算
	 * @param width 宽
	 * @param height 高
	 * @return 参数对象
	 */
	private static ScaleParameter getScaleParam(Image srcImage, Integer width, Integer height) {
		int h = srcImage.getHeight(null);
		int w = srcImage.getWidth(null);
		if (width == null && height == null) {
			throw new RuntimeException("The parameter width and height both null");
		} else if (width != null && height == null) {
			height = (int) (1.0 * h / w * width);
		} else if (width == null) {
			width = (int) (1.0 * w / h * height);
		}
		return new ScaleParameter(width, height);
	}


	public static byte[] zipImageFileAutoShort(byte[] srcImgBytes, Integer width, Integer height, float quality) throws IOException {
		if (srcImgBytes == null) {
			return null;
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(srcImgBytes);
		Image srcImage = ImageIO.read(bais);
		return zipImageFileAutoShort(srcImage, width, height, quality);
	}


	/**
	 * 按原始图片较短的边等比例压缩
	 *
	 * @param srcImage
	 * @param width
	 * @param height
	 * @param quality
	 * @return
	 * @throws IOException
	 */
	public static byte[] zipImageFileAutoShort(Image srcImage, Integer width, Integer height, float quality) throws IOException {
		{
			/** 对服务器上的临时文件进行处理 */
			int w = srcImage.getWidth(null);
			int h = srcImage.getHeight(null);
			if (height == null || width == null) {
				throw new RuntimeException("The parameter width or height is null");
			}
			if (w < h) {
				height = (int) (1.0 * h / w * width);
			} else {
				width = (int) (1.0 * w / h * height);
			}
		}
		return zipImageFileBase(srcImage, width, height, quality);
	}


	private static byte[] zipImageFileBase(Image srcImage, Integer width, Integer height, float quality) throws IOException {
		ByteArrayOutputStream baos = null;
		try {/** 宽,高设定 */
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcImage, 0, 0, width, height, null);
			/** 压缩之后临时存放位置 */
//			FileOutputStream out = new FileOutputStream(newImageFile);
			baos = new ByteArrayOutputStream();

			// 得到指定Format图片的writer
			ImageWriter writer = ImageIO.getImageWritersByFormatName("jpeg").next();// 得到迭代器
			// 得到指定writer的输出参数设置(ImageWriteParam )
			ImageWriteParam iwp = writer.getDefaultWriteParam();
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
			iwp.setCompressionQuality(quality); // 设置压缩质量参数  quality参数必须是0.0F到 1.0F的小数
//		    iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED );

			IIOImage iIamge = new IIOImage(tag, null, null);
			//此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
			//通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
			writer.setOutput(ImageIO.createImageOutputStream(baos));
			writer.write(null, iIamge, iwp);

//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
//			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//			/** 压缩质量 */
//			jep.setQuality(quality, true);
//			encoder.encode(tag, jep);
//			baos.close();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}


	public static void main(String args[]) throws IOException {

		String res = ImageZipUtil.zipImageFile("C:/Users/shiyl/Desktop/151202hbmmmf001.jpg", null, 400, 1.00f, "d:/x3.jpg");
		System.out.println(res);
	}
}