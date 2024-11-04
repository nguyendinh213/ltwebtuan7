package vn.iotstar.service;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;

public class VideoService implements IVideoSerice{
	IVideoDao videodao = new VideoDao();

	@Override
	public int count() {
		return videodao.count();
	}

	@Override
	public List<Video> findAll(int page, int pageSize) {
		return videodao.findAll(page, pageSize);
	}

	@Override
	public List<Video> findByTitle(String title) {
		return videodao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videodao.findAll();
	}

	@Override
	public Video findById(int videoId) {
		return videodao.findById(videoId);
	}

	@Override
	public void delete(String videoId) throws Exception {
		videodao.delete(videoId);
		
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
		
	}

	@Override
	public void insert(Video video) {
		videodao.insert(video);
		
	}
	
	
	
	

}
