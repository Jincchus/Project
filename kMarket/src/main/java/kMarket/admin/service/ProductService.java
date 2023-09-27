package kMarket.admin.service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import kMarket.admin.dao.ProductDao;
import kMarket.admin.request.ProductCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ProductService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ProductService INSTANCE = new ProductService();

    private ProductService() {}
    public static ProductService getInstance() {
        return INSTANCE;
    }
    private static ProductDao dao = ProductDao.getInstance();

    public void registerProduct(ProductCreateRequest dto) {
        dao.insertProduct(dto);
    }

    // MultipartForm으로 이미지, 파일 업로드
    public String getFilePath(HttpServletRequest req) {

        ServletContext ctx = req.getServletContext();
        String path = ctx.getRealPath("/thumb");
        return path;
    }
    // 파일명수정
    public String renameToFile(HttpServletRequest req, String path, String oName) {

        int i = oName.lastIndexOf(".");
        String ext = oName.substring(i);

        String uuid = UUID.randomUUID().toString();
        String sName = uuid + ext;

        File f1 = new File(path+"/"+oName);
        File f2 = new File(path +"/"+sName);

        f1.renameTo(f2);

        return sName;
    }

    //파일 업로드 - 경로 설정
    public String renameToFile(HttpServletRequest req, String path, String oName, String cate1, String cate2) {

        int i = oName.lastIndexOf(".");
        String ext = oName.substring(i);

        String uuid = UUID.randomUUID().toString();
        String sName = uuid + ext;

        String oriPath = "/" + oName;
        String savePath =  "/" + cate1 + "/" + cate2 +"/"+sName;

        File f1 = new File(path + oriPath);
        File f2 = new File(path + savePath);
        f1.renameTo(f2);
        if(f1.exists()) {
            f1.delete();
        }

        logger.debug(savePath + " : newPath / oName : " + oName);
        return "/thumb" + savePath;
    }
    // 파일업로드
    public MultipartRequest uploadFile(HttpServletRequest req, String path) {

        // upload file max size limitation
        int maxSize = 1024 * 1024 * 10;

        MultipartRequest mr = null;

        try {
            mr = new MultipartRequest(req,
                    path,
                    maxSize,
                    "UTF-8",
                    new DefaultFileRenamePolicy());
        } catch (IOException e) {
            logger.error("uploadFile : " + e.getMessage());
        }
        return mr;
    }
}
