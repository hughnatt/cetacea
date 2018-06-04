package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

import com.sun.corba.se.impl.orbutil.graph.Graph;

public abstract class Background {

	BufferedImage[] sprites; // tab containing the sprites
	int width_sprite; // size of 1 sprite
	int height_sprite;
	int num_sprite; // current sprite
	int max_sprite; // 0<=num_sprite<max_sprite

	Model model;

	/**
	 * @param sprites
	 * @param width_sprite
	 * @param height_sprite
	 * @param num_sprite
	 * @param max_sprite
	 * @param model
	 */
	protected Background(BufferedImage[] sprites, int width_sprite, int height_sprite, int num_sprite, int max_sprite,
			Model model) {
		super();
		this.sprites = sprites;
		this.width_sprite = width_sprite;
		this.height_sprite = height_sprite;
		this.num_sprite = num_sprite;
		this.max_sprite = max_sprite;
		this.model = model;
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
	
	public abstract void paint(Graph g,Location p_ref_map);

}
