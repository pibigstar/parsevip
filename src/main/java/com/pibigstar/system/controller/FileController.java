package com.pibigstar.system.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pibigstar.common.Constant;
import com.pibigstar.domain.User;
import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.system.domain.SystemUser;
import com.pibigstar.system.service.SystemUserService;

/**
 * 文件上传Controller
 * @author pibigstar
 *
 */
@Controller()
public class FileController extends SystemBaseController{

	private final String upload_path = Constant.DEFAULT_FILE_UPLOAD_PATH;
	@Autowired
	private SystemUserService systemUserService;

	/**
	 * 上传
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("file/upload")
	@ResponseBody
	public MyResponse singleFileUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return error(ExceptionMsg.FILLE_IS_NULL);
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(upload_path+file.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
		}
		SystemUser user = getUser();
		String headImg = Constant.READ_UPLOAD_FILE+file.getOriginalFilename();
		user.setHeadImg(headImg);
		systemUserService.update(user);
		getSession().setAttribute(Constant.LOGIN_USER_SESSION_KEY, user);
		return success("上传成功！", headImg);
	}

	/**
	 * 下载
	 * @param res
	 * @throws IOException
	 */
	@GetMapping("file/download")  
	public void download(HttpServletResponse res) throws IOException {

		String fileName = "Main.jpg";
		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File("E://temp/"+ fileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}
}
