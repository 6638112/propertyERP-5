<%@ page pageEncoding="UTF-8" %>
<%@	page contentType="image/jpeg" %>   
<%@ page import="java.awt.Color" %>
<%@ page import="java.awt.Font" %>
<%@ page import="java.awt.Graphics" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.util.Random" %>
<%@ page import="javax.imageio.ImageIO" %>

<%!  
Color getRandColor(int fc,int bc)
{//给定范围获得随机颜色
	Random random = new Random();
	if(fc>255) fc=255;
	if(bc>255) bc=255;
	int r=fc+random.nextInt(bc-fc);
	int g=fc+random.nextInt(bc-fc);
	int b=fc+random.nextInt(bc-fc);
	return new Color(r,g,b);
}
%> 
 
<% 
	//设置页面不缓存
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);

	// 在内存中创建图象
	int width=75, height=20;
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	// 获取图形上下文
	Graphics g = image.getGraphics();

	//生成随机类
	Random random = new Random();

	// 设定背景色
	g.setColor(getRandColor(200,250));
	g.fillRect(0, 0, width, height);

	//设定字体
	g.setFont(new Font("Times New Roman",Font.PLAIN,18));

	//画边框
	//g.setColor(new Color());
	//g.drawRect(0,0,width-1,height-1);

	// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	g.setColor(getRandColor(160,200));
	for (int i=0;i<155;i++)
	{
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x,y,x+xl,y+yl);
	}

	// 取随机产生的认证码(5位数字)
	String sRand="";
	//去除大写'O', 与数字0很像
	char[] codeSequence = { '2', '3', '4', '5', '6', '7', '8', '9',
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
      'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm',
      'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  // 定义颜色三素
  int red = 0;
  int green = 0;
  int blue = 0;
  // randomCode保存随机产生的验证码
  StringBuffer randomCode = new StringBuffer();

	// 随机生产codeNum个数字验证码
            for (int i = 0; i < 5; i++) {
                // 得到随机产生的验证码
                String strRand = String.valueOf(codeSequence[random.nextInt(56)]);
                // 使用随机函数产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
                red = random.nextInt(100);  //255
                green = random.nextInt(100); //255
                blue = random.nextInt(255);

                // 用随机产生的颜色将验证码绘制到图像中。
                g.setColor(new Color(red, green, blue));
                //g.drawString(strRand, (i + 1) * x, codeY);
                g.drawString(strRand,13*i+6,16);

                // 将产生的四个随机数组合在一起。
                randomCode.append(strRand);
            }
            sRand = randomCode.toString();

//for (int i=0;i<5;i++){
//    String rand=String.valueOf(random.nextInt(10));
//    sRand+=rand;
//    // 将认证码显示到图象中
//    g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
//    g.drawString(rand,13*i+6,16);
//}
	
	//System.out.println("rand="+sRand);
	
	// 将认证码存入SESSION
	session.setAttribute("_rand",sRand);

	// 图象生效
	g.dispose();

	// 输出图象到页面
	ImageIO.write(image, "JPEG", response.getOutputStream());
	
	out.clear();
	out = pageContext.pushBody();
%>   