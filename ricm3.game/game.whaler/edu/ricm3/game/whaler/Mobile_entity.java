package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

public abstract class Mobile_entity extends Entity {

	BufferedImage[] sprites; // tab containing the sprites
	int width_sprite; // size of 1 sprite
	int height_sprite;
	int num_sprite; // current sprite
	int max_sprite; // 0<=num_sprite<max_sprite

	protected Mobile_entity(Model model, Location position, boolean collision_up, boolean collision_down,
			BufferedImage im_sprites, int nrows, int ncols) {
		super(model, position, collision_up, collision_down);
		Split_sprite(im_sprites, nrows, ncols);
	}

	private void Split_sprite(BufferedImage im_sprites, int nrows, int ncols) {
		int width = im_sprites.getWidth(null);
		int height = im_sprites.getHeight(null);
		sprites = new BufferedImage[nrows * ncols];
		width_sprite = width / ncols;
		height_sprite = height / nrows;
		this.num_sprite = 0;
		this.max_sprite = nrows * ncols;
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				int x = j * width_sprite;
				int y = i * height_sprite;
				sprites[(i * ncols) + j] = im_sprites.getSubimage(x, y, width_sprite, height_sprite);
			}
		}
	}

	public abstract void step(long now);
}
