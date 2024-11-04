package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IVideoSerice;
import vn.iotstar.service.VideoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/delete", "/admin/video/edit", "/admin/video/update" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private IVideoSerice videoService = new VideoService();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/video/add")) {
			 ICategoryService categoryService = new CategoryService();//
			 List<Category> categories = categoryService.findAll();//
		        req.setAttribute("categories", categories);//
		        
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/delete")) {
			String id = req.getParameter("id");
			try {
				videoService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("/admin/video/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoService.findById(id);
			req.setAttribute("video", video);
			
			// Lấy danh sách category
			ICategoryService categoryService = new CategoryService();
		    List<Category> categories = categoryService.findAll();
		    req.setAttribute("categories", categories);
		    
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/video/update")) {
			int videoId = Integer.parseInt(req.getParameter("videoid"));
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int active = Integer.parseInt(req.getParameter("active"));
			int views = Integer.parseInt(req.getParameter("views"));
			int categoryId = Integer.parseInt(req.getParameter("categoryId")); // Lấy categoryId từ form
			
			Video video = new Video();
			video.setVideoId(videoId);
			video.setTitle(title);
			video.setDescription(description);
			video.setActive(active);
			video.setViews(views);
			
			 // Tìm Category theo ID
	        ICategoryService categoryService = new CategoryService();
	        Category category = categoryService.findById(categoryId); // Tìm category theo categoryId
	        video.setCategory(category); // Gán Category cho video

			Video videoOld = videoService.findById(videoId);
			String fileOld = videoOld.getPoster();

			String fname = "";
			String uploadPath = "E:\\upload2";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// Lấy giá trị từ ô nhập liệu văn bản (link hình ảnh)
			String posterLink = req.getParameter("poster");

			if (posterLink != null && !posterLink.isEmpty()) {
				// Nếu người dùng đã nhập link, sử dụng link đó
				video.setPoster(posterLink);
			} else {
				try {
					Part part = req.getPart("poster1");
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);
						video.setPoster(fname);
					} else {
						video.setPoster("default.png");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");

		} else if (url.contains("/admin/video/insert")) {
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int active = Integer.parseInt(req.getParameter("active"));
			int views = Integer.parseInt(req.getParameter("views"));
			int categoryId = Integer.parseInt(req.getParameter("categoryId"));//

			Video video = new Video();
			video.setTitle(title);
			video.setDescription(description);
			video.setActive(active);
			video.setViews(views);
			
			// Tìm Category theo ID
			ICategoryService categoryService = new CategoryService();

	        Category category = categoryService.findById(categoryId);
	        video.setCategory(category);
			
			

			String fname = "";
			String uploadPath = "E:\\upload2";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// Lấy giá trị từ ô nhập liệu văn bản (link hình ảnh)
			String posterLink = req.getParameter("poster");

			if (posterLink != null && !posterLink.isEmpty()) {
				video.setPoster(posterLink);
			} else {
				try {
					Part part = req.getPart("poster1");
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);
						video.setPoster(fname);
					} else {
						video.setPoster("default.png");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}
}
