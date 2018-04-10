package gameMap;

import drawing.Position;

public class MapPortal {
	private Position portalPosition;
	private int targetMapIndex;
	private Position targetPosition;
	
	public MapPortal(Position portalPosition, int targetMapIndex, Position targetPosition) {
		this.portalPosition = portalPosition;
		this.targetMapIndex = targetMapIndex;
		this.targetPosition = targetPosition;
	}

	public Position getPortalPosition() {
		return portalPosition;
	}

	public int getTargetMapIndex() {
		return targetMapIndex;
	}

	public Position getTargetPosition() {
		return targetPosition;
	}
	
}
