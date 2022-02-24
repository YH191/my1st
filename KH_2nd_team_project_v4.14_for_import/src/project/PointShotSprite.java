package project;

import java.awt.Image;

//점수 상승 아이템을 위한 클래스
public class PointShotSprite extends Sprite {
//	GalagaGame 클래스 사용을 위해 선언
	private GalagaGame game;
//	생성자로 개체 기본 값 설정
	public PointShotSprite(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		dx = 0;
		dy = -1;
	}
//	x좌표는 플레이어의 위치를 얻어 설정되고, x좌표측의 이동속도는 없으며 y방향으로 2씩 이동
//	맵 밑으로 나가면 없어짐
	@Override
	public void move() {
		dx = 0;
		x = x;
		y -= dy;
		if (y > 600) {
			game.removeSprite(this);
		}
	}

	@Override
	public void handleCollision(Sprite other) {
//		개체가 플레이어와 닿았는지 확인 후 아이템 획득 효과음 출력 후 아이템 제거, 점수 상승
		if (other instanceof StarShipSprite) {
			if (other.hp >= 50) {
				game.getitemMusic();
				game.removeSprite(this);
				ShotSprite.score += 100;
			}
		}
	}
}
