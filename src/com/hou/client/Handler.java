/**
 * 
 */
package com.hou.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.hou.service.Event;
import com.hou.service.MyService;

/**
 * @author hou
 * 客户端子线程
 */
public class Handler {
	private MyService service;
	private String name;
	private String password;
	public Handler(MyService service) {
		this.service = service;
	}

	public void process() {
		name = null;
		password = null;
        System.out.println("清先注册或登陆,格式为:");
        System.out.println("     register [username] [password]");
        System.out.println("     login [username] [password]\n");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {   
	        String msg = null;
			String[] handler = null;
			while(true){
				msg = buffer.readLine();
				handler = msg.split(" ");
				if(handler[0].equals("register")){
					if(service.register(handler[1], handler[2]) != true){
						System.out.println("此User已存在.请选择另一个用户名.");
					}else{
						name = handler[1];
						password = handler[2];
						System.out.println("User " + handler[1] + " 注册成功.");
						break;
					}
				}else if(handler[0].equals("login")){
					if(service.login(handler[1], handler[2]) == 0){
						name = handler[1];
						password = handler[2];
						System.out.println("User " + handler[1] + " 登录成功.");
						break;
					}else if(service.login(handler[1], handler[2]) == 1){
						System.out.println("帐号密码不匹配,登录失败.");
					}else if(service.login(handler[1], handler[2]) == 2){
						System.out.println("请先注册,登录失败.");
					}
				}else{
					System.out.println("格式错误,格式为:login/register [username] [password]");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        menu();
        System.out.println("请输入指令: ");
		String msg = null;
		try {
			while((msg = buffer.readLine()) != null){
				String split[] = msg.split(" ");
				if(split[0].equals("add")){
					if(split.length == 4){
						//建立新的Event存储参数传递给服务器
						Event newEv = new Event();
						newEv.setCreator(name);
						newEv.setStart(dateToXmlDate((new SimpleDateFormat("yyyy-MM-dd-HH:mm")).parse(split[1])));
						newEv.setEnd(dateToXmlDate((new SimpleDateFormat("yyyy-MM-dd-HH:mm")).parse(split[2])));
						newEv.setDescription(split[3]);
						if(service.add(newEv, name, password) == 0){
							System.out.println("添加事件成功.");
						}else if(service.add(newEv, name, password) == 1){
							System.out.println("添加失败,开始时间应在结束时间之后.");
						}else if(service.add(newEv, name, password) == 2){
							System.out.println("事件与已有事件冲突,添加失败.");
						}
					}else{
						System.out.println("格式应为:add [start] [end] [description].");
					}
				}else if(split[0].equals("delete")){ 
					if(split.length == 2){
						if(service.delete(Integer.parseInt(split[1]), name, password) == 0){
							System.out.println("删除事件成功.");
						}else if(service.delete(Integer.parseInt(split[1]), name, password) == 1){
							System.out.println("未找到此事件,删除失败.");
						}else if(service.delete(Integer.parseInt(split[1]), name, password) == 2){
							System.out.println("这不是你的事件,删除失败.");
						}
					}else{
						System.out.println("格式应为:delete [meetingid].");
					}
				}else if(split[0].equals("clear")){
					service.clear(name, password);
					System.out.println("清除" + name + "的事件成功.");
				}else if(split[0].equals("query")){
					if(split.length == 3){
						List<Event> qEvent = null;
						qEvent = service.query(dateToXmlDate((new SimpleDateFormat("yyyy-MM-dd-HH:mm")).parse(split[1])), dateToXmlDate((new SimpleDateFormat("yyyy-MM-dd-HH:mm")).parse(split[2])), name, password);
						System.out.println("查询成功.");
						if(qEvent.size()!=0){
							for(int i=0;i<qEvent.size();i++){
								System.out.println("Event"+(i+1)+":");
								System.out.print("{id:"+qEvent.get(i).getId()+"  ");
								System.out.print("creator:"+qEvent.get(i).getCreator()+"  ");
								System.out.print("start:"+(new SimpleDateFormat("yyyy-MM-dd-HH:mm")).format(xmlDateToDate(qEvent.get(i).getStart()))+"  ");
								System.out.print("end:"+(new SimpleDateFormat("yyyy-MM-dd-HH:mm")).format(xmlDateToDate(qEvent.get(i).getEnd()))+"  ");
								System.out.print("description:"+qEvent.get(i).getDescription()+"}  \n");
							}
						}else{
							System.out.println("所查询区间无事件");
						}
					}else{
						System.out.println("格式应为:query [start] [end].");
					}
				}else if(split[0].equals("help")){
					menu();
				}else if(split[0].equals("quit")){
					System.out.println("使用结束.");
					break;
				}else{
					System.out.println("未知指令.");
					break;
				}
				System.out.println("\n请输入指令: ");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

    /**
     * 菜单功能
     * @author hou
     */
	private void menu(){
		System.out.println("WebService Menu:");
		System.out.println("         1.add [start] [end] [description]");
		System.out.println("         2.delete [meetingid]");
		System.out.println("         3.clear");
		System.out.println("         4.query [start] [end]");
		System.out.println("         5.help");
		System.out.println("         6.quit");
		System.out.println("         (PS:日期格式 20xx-12-12-12:12)\n");
	}
	
	/**
	 * 将Date类转换为XMLGregorianCalendar
	 * @author hou
	 * @param date
	 * @return XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar dateToXmlDate(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			DatatypeFactory dtf = null;
		     try {
				dtf = DatatypeFactory.newInstance();
			} catch (DatatypeConfigurationException e) {
			}
			XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();
		    dateType.setYear(cal.get(Calendar.YEAR));
		    //由于Calendar.MONTH取值范围为0~11,需要加1
		    dateType.setMonth(cal.get(Calendar.MONTH)+1);
		    dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));
		    dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));
		    dateType.setMinute(cal.get(Calendar.MINUTE));
		    dateType.setSecond(cal.get(Calendar.SECOND));
		    return dateType;
		}
	
	/**
	 * 将XMLGregorianCalendar类转换为Date
	 * @author hou
	 * @param xgc
	 * @return Date
	 */
	public static Date xmlDateToDate(XMLGregorianCalendar xgc){
		GregorianCalendar gc = xgc.toGregorianCalendar();
		return gc.getTime();
	}
}
