import java.lang.reflect.*;
import java.io.*;
import java.util.*;
public class ClassTest{
	public static void main(String[] args) throws Exception{


		Scanner scan=new Scanner(System.in);

		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:/ClassName.txt")));


		String className="";
		while ((className=br.readLine())!=null) {
			Object o=getInstance(className);
			Field[] f=getField(className);

			System.out.println("类名:"+className);

			System.out.println("private修饰类型的属性有：");
			for (Field f1:f ) {
				f1.setAccessible(true);

				if (f1.getModifiers()==Modifier.PRIVATE) {//筛选出private修饰的
					System.out.println(f1+"..."+f1.get(o)+"(值)");//打印属性和属性的值
				}
			}	

			System.out.println("public修饰类型的属性有：");
			for (Field f2:f ) {

				if (f2.getModifiers()==Modifier.PUBLIC) {
					System.out.println(f2+"..."+f2.get(o)+"(值)");
				}
			}


			System.out.println("下面分别调用了类里的方法：");
			Method[] m=getMethod(className);

			for (Method m1:m) {
				/*//getParameterTypes()如果无参将会返回长度为0的数组
				if (m1.getParameterTypes().length!=0&&!(m1.getName().equals("toString"))) {
					System.out.println(m1.getName()+"方法（有参）：");
					m1.invoke(o,"楼佳程");
				}else {
					if (!m1.getName().equals("toString")) {
						System.out.println(m1.getName()+"方法（无参）：");
						m1.invoke(o,new Object[]{});
					}

				}*/

			}	

			System.out.println("----------------------------------------------------");

		}
	}
	public static Object getInstance(String className)throws Exception{	
		Class clazz=Class.forName(className);//1、通过className创建出Class对象	
		return clazz.newInstance();//2、通过newInstance字节创建出无参的实例对象
	}
	public static Object getInstance(String className,Class[] types,Object[] params)throws Exception{	
		Class clazz=Class.forName(className);//1、通过className创建出Class对象		
		Constructor con=clazz.getConstructor(types);//2、通过getConstructor()获取构造函数		
		return con.newInstance(params);//3、通过构造函数创建出有参的实例对象
	}
	public static Field[] getField(String className)throws Exception{
		Class clazz=Class.forName(className);
		Object o=clazz.newInstance();
		return clazz.getDeclaredFields();
	}
	public static Method[] getMethod(String className)throws Exception{
		Class clazz=Class.forName(className);
		Object o=clazz.newInstance();
		return clazz.getDeclaredMethods();
	}
}

