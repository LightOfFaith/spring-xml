package com.share.lifetime.util;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThumbnailUtilsTest {

	@Test
	public void testCreateThumbnailFromImageFile() throws IOException {
		String originalImageFilePath = ResourceUtils
				.getPath("D:\\Users\\hasee\\git\\spring-xml\\src\\test\\resources\\original-1.jpg");
		String thumbnailFilePath = ResourceUtils
				.getPath("D:\\Users\\hasee\\git\\spring-xml\\src\\test\\resources\\original-1-thumbnail.jpg");
		log.info("originalImageFilePath:{}", originalImageFilePath);
		log.info("thumbnailFilePath:{}", thumbnailFilePath);
		ThumbnailUtils.createThumbnailFromImageFile(originalImageFilePath, thumbnailFilePath, 800, 800);
	}

	@Test
	public void testCreateThumbnailWithRotationAndWatermark() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateThumbnailAndWriteToOutputStream() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFixedSizeThumbnails() {
		fail("Not yet implemented");
	}

	@Test
	public void testScalingImageByGivenFactor() throws IOException {
		String originalImageFilePath = ResourceUtils
				.getPath("D:\\Users\\Administrator\\Documents\\workspace-sts-3.9.5.RELEASE\\spring-xml\\src\\test\\resources\\original-3.jpg");
		BufferedImage bufferedImage = ThumbnailUtils.scalingImageByGivenFactor(originalImageFilePath, 0.25);
		String thumbnailFilePath = ResourceUtils
				.getPath("D:\\Users\\Administrator\\Documents\\workspace-sts-3.9.5.RELEASE\\spring-xml\\src\\test\\resources\\original-1-thumbnail.jpg");
		log.info("originalImageFilePath:{}", originalImageFilePath);
		ImageIO.write(bufferedImage, "jpg", new File(thumbnailFilePath));
	}

	@Test
	public void testCreatingThumbnailRotatingImage() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatingThumbnailWithWatermark() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteThumbnailsToSpecificDirectory() {
		fail("Not yet implemented");
	}

}
