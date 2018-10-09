package com.share.lifetime.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

/**
 * 
 * @author hasee
 * @see https://github.com/coobird/thumbnailator
 */
public class ThumbnailUtils {

	/**
	 * 从图像文件创建缩略图
	 * 
	 * @param originalImageFilePath 原始图像文件存储位置(含文件名)
	 * @param thumbnailFilePath     缩略图文件存储位置(含文件名)
	 * @param width                 缩略图宽度
	 * @param height                缩略图高度
	 * @throws IOException
	 */
	public static void createThumbnailFromImageFile(final String originalImageFilePath, final String thumbnailFilePath,
			int width, int height) throws IOException {
		Thumbnails.of(new File(originalImageFilePath)).size(width, height).toFile(new File(thumbnailFilePath));
	}

	/**
	 * 使用旋转和水印创建缩略图
	 * 
	 * @param originalImageFilePath  原始图像文件存储位置(含文件名)
	 * @param watermarkImageFilePath 水印图像文件存储位置(含文件名)
	 * @param thumbnailFilePath      缩略图文件存储位置(含文件名)
	 * @param width                  缩略图宽度
	 * @param height                 缩略图高度
	 * @param angle                  缩略图将按指定的角度顺时针旋转
	 * @param opacity                缩略图水印不透明性(0.0d~1.0d)，0.0F完全透明，1.0F完全不透明。
	 * @param quality                缩略图压缩质量(0.0d~1.0d)
	 * @throws IOException
	 */
	public static void createThumbnailWithRotationAndWatermark(final String originalImageFilePath,
			final String watermarkImageFilePath, final String thumbnailFilePath, final int width, final int height,
			final double angle, final float opacity, final float quality) throws IOException {
		Thumbnails.of(new File(originalImageFilePath)).size(width, height).rotate(angle)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(watermarkImageFilePath)), opacity)
				.outputQuality(quality).toFile(new File(thumbnailFilePath));
	}

	/**
	 * 
	 * 创建缩略图并写入 OutputStream
	 * 
	 * @param originalImageFilePath 原始图像文件存储位置(含文件名)
	 * @param width                 缩略图宽度
	 * @param height                缩略图高度
	 * @param format                缩略图格式
	 * @param os                    缩略图输出流
	 * @return
	 * @throws IOException
	 */
	public static void createThumbnailAndWriteToOutputStream(final String originalImageFilePath, final int width,
			final int height, final String format, final OutputStream os) throws IOException {
		Thumbnails.of(originalImageFilePath).size(width, height).outputFormat(format).toOutputStream(os);
	}

	/**
	 * 
	 * 创建固定大小的缩略图
	 * 
	 * @param originalImageFilePath 原始图像文件存储位置(含文件名)
	 * @param width                 缩略图宽度
	 * @param height                缩略图高度
	 * @return 缩略图
	 * @throws IOException
	 */
	public static BufferedImage createFixedSizeThumbnails(final String originalImageFilePath, final int width,
			final int height) throws IOException {
		BufferedImage originalImage = ImageIO.read(new File(originalImageFilePath));
		BufferedImage thumbnail = Thumbnails.of(originalImage).size(width, height).asBufferedImage();
		return thumbnail;
	}

	/**
	 * 
	 * 按给定因子缩放图像
	 * 
	 * @param originalImageFilePath 原始图像文件存储位置(含文件名)
	 * @param scale                 缩略图缩放因子
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage scalingImageByGivenFactor(final String originalImageFilePath, final double scale)
			throws IOException {
		BufferedImage originalImage = ImageIO.read(new File(originalImageFilePath));
		BufferedImage thumbnail = Thumbnails.of(originalImage).scale(scale).asBufferedImage();
		return thumbnail;
	}

	/**
	 * 
	 * @param originalImageFilePath 原始图像文件存储位置(含文件名)
	 * @param thumbnailFilePath     缩略图文件存储位置(含文件名)
	 * @param width                 缩略图宽度
	 * @param height                缩略图高度
	 * @param angle                 缩略图将按指定的角度顺时针旋转
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage creatingThumbnailRotatingImage(final String originalImageFilePath,
			final String thumbnailFilePath, final int width, final int height, final double angle) throws IOException {
		BufferedImage originalImage = ImageIO.read(new File(originalImageFilePath));

		BufferedImage thumbnail = Thumbnails.of(originalImage).size(width, height).rotate(angle).asBufferedImage();
		return thumbnail;
	}

	/**
	 * 
	 * @param originalImageFilePath  原始图像文件存储位置(含文件名)
	 * @param watermarkImageFilePath 水印图像文件存储位置(含文件名)
	 * @param width                  缩略图宽度
	 * @param height                 缩略图高度
	 * @param opacity                缩略图水印不透明性(0.0d~1.0d)，0.0F完全透明，1.0F完全不透明。
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage creatingThumbnailWithWatermark(final String originalImageFilePath,
			final String watermarkImageFilePath, final int width, final int height, final float opacity)
			throws IOException {
		BufferedImage originalImage = ImageIO.read(new File(originalImageFilePath));
		BufferedImage watermarkImage = ImageIO.read(new File(watermarkImageFilePath));
		BufferedImage thumbnail = Thumbnails.of(originalImage).size(width, height)
				.watermark(Positions.BOTTOM_RIGHT, watermarkImage, opacity).asBufferedImage();
		return thumbnail;
	}

	/**
	 * 
	 * @param thumbnailFilePath 缩略图文件目录
	 * @param width             缩略图宽度
	 * @param height            缩略图高度
	 * @param files             缩略图图像文件存储位置(含文件名)
	 * @throws IOException
	 */
	public static void writeThumbnailsToSpecificDirectory(final String thumbnailDir, final int width, final int height,
			String... files) throws IOException {
		File destinationDir = new File(thumbnailDir);
		Thumbnails.of(files).size(width, height).toFiles(destinationDir, Rename.PREFIX_DOT_THUMBNAIL);
	}

}
