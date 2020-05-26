package web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Test extends HttpServlet{
	
	private static final long serialVersionUID = -8820057675394655493L;

	public void service(HttpServletRequest request , HttpServletResponse response) throws IOException{
		System.out.println("========");
		String a = request.getParameter("taskCode");
		System.out.println("taskCode===="+a);
		
//	String jsonstr = "[{iamSceneStatus:'1',sceneId:'4028810135ae08730135ae1d9c550002',sceneName:'?????????',policyId:'5',treasurySessionId:''}]";
//	 JSONObject json = JSONObject.fromObject(jsonstr);
//     String iamSceneStatus = json.getString("iamSceneStatus");
		PrintWriter pw = response.getWriter();
		pw.print("11111111");
		pw.close();
	}
}
