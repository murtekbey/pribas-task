package pribas.task.core.utilities.helpers.concretes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.gridfs.model.GridFSFile;

import org.apache.commons.io.IOUtils;
import pribas.task.core.entities.File;
import pribas.task.core.utilities.helpers.abstracts.MongoFileService;

@Service
public class MongoFileManager implements MongoFileService {
	
	private GridFsTemplate template;
	private GridFsOperations operations;

	@Autowired
	public MongoFileManager(GridFsTemplate template, GridFsOperations operations) {
		super();
		this.template = template;
		this.operations = operations;
	}

	@Override
	public List<File> uploadFiles(MultipartFile[] files) {
        if (files != null) {
        	List<File> uploadedFiles = new ArrayList<File>();
        	
            Arrays.asList(files).stream().forEach(file -> {
            	if (file.getOriginalFilename() == "") {
            		return;
            	}
            	
            	File instance = new File();
            	instance.setFilename(file.getOriginalFilename());
            	instance.setFileSize(String.valueOf(file.getSize()));
            	instance.setFileType(file.getContentType());
            	
            	Object fileID;
    			try {
    				fileID = template.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), instance);
    				instance.setId(fileID.toString());
    			} catch (IOException e) {
    				e.printStackTrace();
    			}

            	uploadedFiles.add(instance);
            });

            return uploadedFiles;
        }
        
        return null;
        

	}

	@Override
	public File downloadFile(String id) {
        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        File file = new File();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
        	file.setFilename( gridFSFile.getFilename() );

        	file.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

        	file.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

        	try {
				file.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

        return file;
	}

	@Override
	public String deleteFile(List<File> files) {
		if (files == null) {
			System.out.println("Files not found");
			return "File not found";
		}
		
		try {
			files.forEach(file -> {
				Query query = new Query(Criteria.where("_id").is(file.getId()));
				this.template.delete(query);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println("Files are deleted");
		return "File deleted";
	}

}
