package com.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.entities.ProductDetails;
import com.app.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ImageHandlingServiceImpl implements ImageHandlingService {
	@Autowired
	private ProductRepository productRepo;
	
	
	//SpEL
	@Value("${file.upload.folder}")
	private String folder;
	@PostConstruct
	public void anyInit() {
		
		log.info("in init of imgae handler with folder {}",folder);
		//creating images folder if it doesnt exist
		File dir = new File(folder);
		
		if(!dir.exists())
			log.info("dir created: {}", dir.mkdirs());
		else
			log.info("dir already exists!");
	}
	@Override
	public String uploadImage(MultipartFile imageFile) throws IOException {
		
		//get Image Path
		String imagePath = folder.concat(File.separator).concat(imageFile.getOriginalFilename());
		//override image if already exists
		log.info("bytes copied {} ",
				Files.copy(imageFile.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING));
		return imagePath;
	}
	@Override
	public byte[] restoreImage(long productId) throws IOException {
		//get product details from productId
		ProductDetails product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Invalid product ID"));
		
		//check if image path is available or not
		if(product.getImagePath() == null) {
			throw new RuntimeException("Image doesn't Exist!");
		}
		
		//return byte[] containing image info
		return Files.readAllBytes(Paths.get(product.getImagePath()));
		
	}
}
