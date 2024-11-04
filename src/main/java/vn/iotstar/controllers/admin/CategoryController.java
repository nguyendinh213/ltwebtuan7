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
import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;
import vn.iotstar.service.ICategoryService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/delete", "/admin/category/edit", "/admin/category/update" })
public class CategoryController extends HttpServlet {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("/admin/categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("/admin/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("/admin/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/category/update")) {
			// lấy dữ liệu từ view
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));

			Category category = new Category();
			category.setCategoryId(categoryid);
			category.setCategoryname(categoryname);
			category.setStatus(status);

			Category cateold = cateService.findById(categoryid);
			String fileold = cateold.getImages();

			String fname = "";
			String uploadPath = "E:\\upload2"; // Đường dẫn tải lên tệp
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// Lấy giá trị từ ô nhập liệu văn bản (link hình ảnh)
			String imageLink = req.getParameter("images");

			if (imageLink != null && !imageLink.isEmpty()) {
				// Nếu người dùng đã nhập link, sử dụng link đó
				category.setImages(imageLink);
			} else {
				// Nếu không có link hình ảnh được nhập, xử lý tệp tải lên
				try {
					Part part = req.getPart("images1"); // Lấy tệp tải lên
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);
						category.setImages(fname);
					} else {
						// Nếu không có tệp tải lên, có thể đặt hình ảnh mặc định
						category.setImages("avata.png");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("/admin/category/insert")) {
			Category category = new Category();
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));

			category.setCategoryname(categoryname);
			category.setStatus(status);

			String fname = "";
			String uploadPath = "E:\\upload2"; // Đường dẫn tải lên tệp
			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// Lấy giá trị từ ô nhập liệu văn bản (link hình ảnh)
			String imageLink = req.getParameter("images");

			if (imageLink != null && !imageLink.isEmpty()) {
				// Nếu người dùng đã nhập link, sử dụng link đó
				category.setImages(imageLink);
			} else {
				// Nếu không có link hình ảnh được nhập, xử lý tệp tải lên
				try {
					Part part = req.getPart("images1"); // Lấy tệp tải lên
					if (part.getSize() > 0) {
						String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
						int index = filename.lastIndexOf(".");
						String ext = filename.substring(index + 1);
						fname = System.currentTimeMillis() + "." + ext;

						part.write(uploadPath + "/" + fname);
						category.setImages(fname);
					} else {
						// Nếu không có tệp tải lên, có thể đặt hình ảnh mặc định
						category.setImages("avata.png");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}

	}
}