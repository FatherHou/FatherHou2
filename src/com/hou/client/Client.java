/**
 * 
 */
package com.hou.client;

import com.hou.service.MyService;
import com.hou.service.MyServiceService;

/**
 * @author hou
 * 客户端
 */
public class Client {
	 public static void main(String[] args) {
		 MyServiceService hService = new MyServiceService();
		 MyService service = hService.getMyServicePort();
		 new Handler(service).process();
		 System.out.println("123");
	    }
}
