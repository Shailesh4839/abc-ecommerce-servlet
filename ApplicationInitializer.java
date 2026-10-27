import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class ApplicationInitializer extends HttpServlet {
	
public void init(ServletConfig sc) throws ServletException {
	
	try {
	
		ServletContext ctx = sc.getServletContext();
		
		String val = ctx.getInitParameter("oracletab");
		String prop = ctx.getRealPath("/WEB-INF/db/db.properties");
		String oracle = ctx.getRealPath("/WEB-INF/dbtable/oracletable.txt");            
		
		LoadProperties.propLoad(prop);
		

		if ("yes".equalsIgnoreCase(val)) 
		{
			if (oracle != null) 
			{
				TableCreate.createTab(oracle);
				System.out.println("Table Created Successfully");
			}
		}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}