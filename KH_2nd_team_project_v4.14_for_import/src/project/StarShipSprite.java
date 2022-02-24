package project;

import java.awt.Image;

//플레이어 개체 클래스 
public class StarShipSprite extends Sprite{
//	GalagaGame 클래스 사용
	private GalagaGame game;
//	생성자로 개체 기본값 설정
	public StarShipSprite(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		dx = 0;
		dy = 0;
	}
//	맵 밖으로 나가지 않는 범위에서만 키보드의 방향키 입력을 반환해주며 Sprite의 무브 실행
	@Override
	public void move() {
		if ((dx < 0) && (x < 10)) {
			return;
		}
		if ((dx > 0) && (x > 730)) {
			return;
		}
		if ((dy < 0) && (y < 10)) {
			return;
		}
		if ((dy > 0) && (y > 500) ) {
			return;
		}
		super.move();
	}
//	충돌 메소드이지만 사용하지 않음
	@Override
	public void handleCollision(Sprite other) {
		if(other instanceof AlienSprite) {
//			game.endGame();
		}
	}
}
