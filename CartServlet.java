import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

public class CartServlet extends HttpServlet
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        
        if(session != null)
        {
            // Session se saare selected items nikalna
            String[] watches = (String[])session.getAttribute("watches");
            String[] cars = (String[])session.getAttribute("cars");
            String[] mobiles = (String[])session.getAttribute("mobiles");
            String[] laptops = (String[])session.getAttribute("laptops");
            String[] men = (String[])session.getAttribute("men");
            String[] women = (String[])session.getAttribute("women");
            
            // HTML aur Modern Premium UI CSS Layout
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>Your Cart - Shop</title>");
            out.println("    <style>");
            out.println("        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', system-ui, -apple-system, sans-serif; }");
            out.println("        body { background-color: #f8fafc; color: #0f172a; min-height: 100vh; padding: 40px 20px; display: flex; justify-content: center; align-items: flex-start; }");
            out.println("        .cart-container { max-width: 650px; width: 100%; background: #ffffff; padding: 40px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.05); border: 1px solid #e2e8f0; margin-top: 20px; }");
            out.println("        .cart-header { text-align: center; margin-bottom: 30px; border-bottom: 2px solid #e2e8f0; padding-bottom: 15px; }");
            out.println("        .cart-header h2 { font-size: 26px; color: #1e3a8a; font-weight: 800; }");
            out.println("        .cart-header p { color: #64748b; font-size: 14px; margin-top: 5px; }");
            out.println("        .cart-section { margin-bottom: 25px; }");
            out.println("        .section-title { font-size: 13px; font-weight: 700; text-transform: uppercase; color: #1e3a8a; letter-spacing: 0.5px; margin-bottom: 12px; padding-left: 5px; border-left: 3px solid #1e3a8a; }");
            out.println("        .product-row { display: flex; justify-content: space-between; align-items: center; background: #f8fafc; padding: 14px 20px; border-radius: 12px; margin-bottom: 10px; border: 1px solid #e2e8f0; transition: transform 0.2s; }");
            out.println("        .product-row:hover { transform: scale(1.01); background: #f1f5f9; }");
            out.println("        .product-details { display: flex; flex-direction: column; gap: 4px; }");
            out.println("        .product-name { font-size: 16px; font-weight: 700; color: #0f172a; }");
            out.println("        .product-desc { font-size: 13px; color: #64748b; }");
            out.println("        .product-price { font-size: 16px; font-weight: 700; color: #10b981; background: #ecfdf5; padding: 4px 12px; border-radius: 8px; border: 1px solid #a7f3d0; }");
            out.println("        .empty-cart { text-align: center; padding: 40px 0; color: #94a3b8; font-size: 16px; font-weight: 500; }");
            out.println("        .actions-group { display: flex; flex-direction: column; gap: 12px; margin-top: 35px; border-top: 1px solid #e2e8f0; padding-top: 25px; }");
            out.println("        .btn { display: block; text-align: center; text-decoration: none; padding: 14px; font-size: 15px; font-weight: 700; border-radius: 10px; cursor: pointer; transition: all 0.2s ease; border: none; width: 100%; }");
            out.println("        .btn-buy { background-color: #10b981; color: white; box-shadow: 0 4px 12px rgba(16, 185, 129, 0.2); }");
            out.println("        .btn-buy:hover { background-color: #059669; transform: translateY(-1px); box-shadow: 0 6px 15px rgba(16, 185, 129, 0.3); }");
            out.println("        .btn-back { background-color: transparent; color: #1e3a8a; border: 1px solid #cbd5e1; }");
            out.println("        .btn-back:hover { background-color: #f1f5f9; color: #334155; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<div class='cart-container'>");
            out.println("    <div class='cart-header'>");
            out.println("        <h2>Review Your Order</h2>");
            out.println("        <p>Detailed breakdown of items in your shopping cart</p>");
            out.println("    </div>");
            
            boolean itemFound = false;
            
            // 1. MOBILES CATEGORY
            if(mobiles != null && mobiles.length > 0) {
                itemFound = true;
                out.println("<div class='cart-section'>");
                out.println("    <div class='section-title'>Mobiles</div>");
                for(int m=0; m<mobiles.length; m++) {
                    String name = mobiles[m];
                    String desc = "Smartphone";
                    String price = "&#8377;0";
                    
                    if(name.equalsIgnoreCase("iphone")) { desc = "Apple iPhone - 128GB Storage"; price = "&#8377;79,900"; }
                    else if(name.equalsIgnoreCase("note2")) { desc = "Samsung Galaxy Note series"; price = "&#8377;34,999"; }
                    else if(name.equalsIgnoreCase("Xperia")) { desc = "Sony Xperia Ultra HD Display"; price = "&#8377;45,000"; }
                    else if(name.equalsIgnoreCase("MotoG") || name.equalsIgnoreCase("Moto")) { desc = "Motorola Moto G Pure Turbo"; price = "&#8377;18,499"; }
                    
                    out.println("    <div class='product-row'>");
                    out.println("        <div class='product-details'>");
                    out.println("            <span class='product-name'>" + name + "</span>");
                    out.println("            <span class='product-desc'>" + desc + "</span>");
                    out.println("        </div>");
                    out.println("        <span class='product-price'>" + price + "</span>");
                    out.println("    </div>");
                }
                out.println("</div>");
            }
            
            // 2. LAPTOPS CATEGORY
            if(laptops != null && laptops.length > 0) {
                itemFound = true;
                out.println("<div class='cart-section'>");
                out.println("    <div class='section-title'>Laptops</div>");
                for(int m=0; m<laptops.length; m++) {
                    String name = laptops[m];
                    String desc = "High Performance Laptop";
                    String price = "&#8377;0";
                    
                    if(name.equalsIgnoreCase("Dell") || name.equalsIgnoreCase("DellAlienware")) { desc = "Dell Alienware Gaming Core i7"; price = "&#8377;1,45,000"; }
                    else if(name.equalsIgnoreCase("HP")) { desc = "HP Pavilion Thin & Light"; price = "&#8377;58,500"; }
                    else if(name.equalsIgnoreCase("IBM")) { desc = "IBM ThinkPad Business Edition"; price = "&#8377;72,000"; }
                    else if(name.equalsIgnoreCase("MacBook")) { desc = "Apple MacBook Air M2 Pro"; price = "&#8377;1,19,900"; }
                    else { desc = "Premium Business Laptop"; price = "&#8377;65,000"; }
                    
                    out.println("    <div class='product-row'>");
                    out.println("        <div class='product-details'>");
                    out.println("            <span class='product-name'>" + name + "</span>");
                    out.println("            <span class='product-desc'>" + desc + "</span>");
                    out.println("        </div>");
                    out.println("        <span class='product-price'>" + price + "</span>");
                    out.println("    </div>");
                }
                out.println("</div>");
            }
            
            // 3. CARS CATEGORY
            if(cars != null && cars.length > 0) {
                itemFound = true;
                out.println("<div class='cart-section'>");
                out.println("    <div class='section-title'>Luxury Cars</div>");
                for(int m=0; m<cars.length; m++) {
                    String name = cars[m];
                    String desc = "Supercar Edition";
                    String price = "&#8377;0";
                    
                    if(name.equalsIgnoreCase("Porsche")) { desc = "Porsche 911 Carrera"; price = "&#8377;1.80 Cr"; }
                    else if(name.equalsIgnoreCase("Lamborghini")) { desc = "Lamborghini Huracan EVO"; price = "&#8377;3.70 Cr"; }
                    else if(name.equalsIgnoreCase("Bugatti Veyron")) { desc = "Bugatti Veyron Hypercar"; price = "&#8377;12.00 Cr"; }
                    else if(name.equalsIgnoreCase("Spyder")) { desc = "Porsche 911 Spyder Convertible"; price = "&#8377;1.50 Cr"; }
                    
                    out.println("    <div class='product-row'>");
                    out.println("        <div class='product-details'>");
                    out.println("            <span class='product-name'>" + name + "</span>");
                    out.println("            <span class='product-desc'>" + desc + "</span>");
                    out.println("        </div>");
                    out.println("        <span class='product-price'>" + price + "</span>");
                    out.println("    </div>");
                }
                out.println("</div>");
            }
            
            // 4. WATCHES CATEGORY
            if(watches != null && watches.length > 0) {
                itemFound = true;
                out.println("<div class='cart-section'>");
                out.println("    <div class='section-title'>Luxury Watches</div>");
                for(int m=0; m<watches.length; m++) {
                    String name = watches[m];
                    String desc = "Premium Timepiece";
                    String price = "&#8377;0";
                    
                    if(name.equalsIgnoreCase("Gucci")) { desc = "Gucci G-Timeless Slim"; price = "&#8377;85,000"; }
                    else if(name.equalsIgnoreCase("Armani")) { desc = "Emporio Armani Chronograph"; price = "&#8377;22,500"; }
                    else if(name.equalsIgnoreCase("Rado")) { desc = "Rado Centrix Automatic"; price = "&#8377;1,45,000"; }
                    else if(name.equalsIgnoreCase("Rolex")) { desc = "Rolex Submariner Date Luxury"; price = "&#8377;8,90,000"; }
                    
                    out.println("    <div class='product-row'>");
                    out.println("        <div class='product-details'>");
                    out.println("            <span class='product-name'>" + name + "</span>");
                    out.println("            <span class='product-desc'>" + desc + "</span>");
                    out.println("        </div>");
                    out.println("        <span class='product-price'>" + price + "</span>");
                    out.println("    </div>");
                }
                out.println("</div>");
            }
            
            // 5. MEN WEAR CATEGORY
            if(men != null && men.length > 0) {
                itemFound = true;
                out.println("<div class='cart-section'>");
                out.println("    <div class='section-title'>Men Apparel</div>");
                for(int m=0; m<men.length; m++) {
                    String name = men[m];
                    String desc = "Designer Wear";
                    String price = "&#8377;0";
                    
                    if(name.equalsIgnoreCase("Gucci")) { desc = "Gucci Men Luxury Casual Shirt"; price = "&#8377;42,000"; }
                    else if(name.equalsIgnoreCase("Armani")) { desc = "Giorgio Armani Slim Fit Suit"; price = "&#8377;1,10,000"; }
                    else if(name.equalsIgnoreCase("levis")) { desc = "Levi's 511 Slim Fit Denim"; price = "&#8377;3,299"; }
                    else if(name.equalsIgnoreCase("louisphilippe")) { desc = "Louis Philippe Formal Shirt"; price = "&#8377;2,499"; }
                    
                    out.println("    <div class='product-row'>");
                    out.println("        <div class='product-details'>");
                    out.println("            <span class='product-name'>" + name + "</span>");
                    out.println("            <span class='product-desc'>" + desc + "</span>");
                    out.println("        </div>");
                    out.println("        <span class='product-price'>" + price + "</span>");
                    out.println("    </div>");
                }
                out.println("</div>");
            }
            
            // 6. WOMEN WEAR CATEGORY
            if(women != null && women.length > 0) {
                itemFound = true;
                out.println("<div class='cart-section'>");
                out.println("    <div class='section-title'>Women Apparel</div>");
                for(int m=0; m<women.length; m++) {
                    String name = women[m];
                    String desc = "Premium Outfit";
                    String price = "&#8377;0";
                    
                    if(name.equalsIgnoreCase("Gucci")) { desc = "Gucci Silk Evening Gown"; price = "&#8377;2,65,000"; }
                    else if(name.equalsIgnoreCase("forever21")) { desc = "Forever 21 Summer Dress"; price = "&#8377;1,999"; }
                    else if(name.equalsIgnoreCase("levis")) { desc = "Levi's High Rise Women Jeans"; price = "&#8377;2,999"; }
                    else if(name.equalsIgnoreCase("louisphilippe")) { desc = "Louis Philippe Women Blazer"; price = "&#8377;4,500"; }
                    
                    out.println("    <div class='product-row'>");
                    out.println("        <div class='product-details'>");
                    out.println("            <span class='product-name'>" + name + "</span>");
                    out.println("            <span class='product-desc'>" + desc + "</span>");
                    out.println("        </div>");
                    out.println("        <span class='product-price'>" + price + "</span>");
                    out.println("    </div>");
                }
                out.println("</div>");
            }
            
            // Empty state message
            if(!itemFound) {
                out.println("<div class='empty-cart'>");
                out.println("    <p>Your shopping cart is empty!</p>");
                out.println("</div>");
            }
            
            // Footer Action buttons
            out.println("    <div class='actions-group'>");
            if(itemFound) {
                out.println("        <a href='netbank.html' class='btn btn-buy'>Proceed to Payment (Buy Now)</a>");
            }
            out.println("        <a href='welcome.html' class='btn btn-back'>&larr; Go Back to Categories</a>");
            out.println("    </div>");
            
            out.println("</div>"); // .cart-container
            out.println("</body></html>");
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher("/index.html");
            rd.forward(req, res);
        }
    }
}