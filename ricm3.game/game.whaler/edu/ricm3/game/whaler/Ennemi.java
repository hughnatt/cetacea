package edu.ricm3.game.whaler;

public abstract class Ennemi extends Mobile_entity {

	protected Ennemi(Location position) {
		super(position, true, false);
	}

	@Override
	public abstract void paint();

}
