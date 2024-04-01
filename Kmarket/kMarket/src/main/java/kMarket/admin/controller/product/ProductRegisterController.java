package kMarket.admin.controller.product;

import com.oreilly.servlet.MultipartRequest;
import kMarket.admin.request.ProductCreateRequest;
import kMarket.admin.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@WebServlet(name = "adminProductRegister",value = "/admin/product/register.do")
public class ProductRegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final ProductService productService = ProductService.getInstance();
    private String ctxPath = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ctxPath = config.getServletContext().getContextPath();
        logger.info("ctxPath setted");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product_register.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uploadPath = productService.getFilePath(req);

        // 폴더 생성
        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdirs(); // 디렉터리가 존재하지 않으면 생성
        }

        //LEARN: multipart request도 라이브러리 임포트해야한다. servlets:cos
        MultipartRequest mr = productService.uploadFile(req, uploadPath);

        String prodCate1 	= mr.getParameter("prodCate1"); //cate12
        String prodCate2 	= mr.getParameter("prodCate2");
        String prodName 	= mr.getParameter("prodName");
        String descript 	= mr.getParameter("descript");
        String company 		= mr.getParameter("company");
        String price 		= mr.getParameter("price");
        String discount 	= mr.getParameter("discount");
        String point 		= mr.getParameter("point");
        String stock 		= mr.getParameter("stock");
        String seller 		= mr.getParameter("seller");
        String delivery 	= mr.getParameter("delivery");
        String thumb1 		= mr.getOriginalFileName("thumb1");
        String thumb2 		= mr.getOriginalFileName("thumb2");
        String thumb3 		= mr.getOriginalFileName("thumb3");
        String detail 		= mr.getOriginalFileName("detail");

        String status 		= mr.getParameter("status");
        String duty 		= mr.getParameter("duty");
        String receipt 		= mr.getParameter("receipt");
        String bizType 		= mr.getParameter("bizType");
        String origin 		= mr.getParameter("origin");
        String ip = req.getRemoteAddr();


        thumb1 =  productService.renameToFile(req, uploadPath, thumb1, prodCate1, prodCate2);
        thumb2 =  productService.renameToFile(req, uploadPath, thumb2, prodCate1, prodCate2);
        thumb3 =  productService.renameToFile(req, uploadPath, thumb3, prodCate1, prodCate2);
        detail =  productService.renameToFile(req, uploadPath, detail, prodCate1, prodCate2);

        ProductCreateRequest dto = new ProductCreateRequest(uploadPath);
        dto.setProdCate1(prodCate1);
        dto.setProdCate2(prodCate2);
        dto.setProdName(prodName);
        dto.setDescript(descript);
        dto.setCompany(company);
        dto.setPrice(Integer.valueOf(price));
        dto.setDiscount(Integer.valueOf(discount));
        dto.setPoint(Integer.valueOf(point));
        dto.setStock(Integer.valueOf(stock));
        dto.setSeller(seller);
        dto.setDelivery(Integer.valueOf(delivery));
        dto.setThumb1(thumb1);
        dto.setThumb2(thumb2);
        dto.setThumb3(thumb3);
        dto.setDetail(detail);
        dto.setStatus(status);
        dto.setDuty(duty);
        dto.setReceipt(receipt);
        dto.setBizType(bizType);
        dto.setOrigin(origin);
        dto.setIp(ip);

        logger.debug("productRegisterInsertDTO() : " + dto);
        productService.registerProduct(dto);

        logger.debug("ip : " + ip);


        resp.sendRedirect(ctxPath+"/admin/product/list.do");
    }
}
