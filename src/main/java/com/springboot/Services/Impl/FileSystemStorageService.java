package com.springboot.Services.Impl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Services.StorageService;

@Service
public class FileSystemStorageService implements StorageService{
	
	private final Path rootLocation;
	
	public FileSystemStorageService() {
		this.rootLocation = Paths.get("src/main/resources/static/uploads");
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			Files.createDirectories(rootLocation);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void store(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
			Path destinationFile = this.rootLocation.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			try(InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
