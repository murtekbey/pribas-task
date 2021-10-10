package pribas.task.core.utilities.helpers.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pribas.task.core.entities.File;

public interface MongoFileService {
	public List<File> uploadFiles(MultipartFile[] files);
	public File downloadFile(String id);
	public String deleteFile(List<File> files);
}
