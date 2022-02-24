package project;

import java.awt.Image;

//플레이어 위치를 받아들여 x좌표값을 이동할 적 유도 미사일
public class AlienShotSprite6 extends Sprite {
//	GalagaGame 클래스 사용을 위해 선언
	private GalagaGame game;
//	생성자로 개체 기본 값 설정
	public AlienShotSprite6(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		dx = 1;
		dy = 1;
	}
//	움직이는 메소드이며 y값이 아래로 600이면 제거
//	플레이어 위치를 받아들여 미사일을 기준으로 플레이어의 x좌표값이 오른쪽이면 오른쪽으로, 
//	플레이어의 x좌표값이 왼쪽이면 왼쪽으로 이동
//	y방향은 아래로 1씩 이동
	@Override
	public void move() {
		if (y > 600) {
			game.removeSprite(this);
		}
		if(game.starship.getX() > x) {
			dx = 2;
		}else if(game.starship.getX() < x) {
			dx = -2;
		}
			
		x += dx;
		y += dy;
	}

	@Override
	public void handleCollision(Sprite other) {
//		총알이 플레이어와 닿았는지 확인
		if (other instanceof StarShipSprite) {
//			플레이어 개체의 hp가 50 이상이면 피격음 출력하고 해당 AlienSprite1 개체 제거 후 플레이어의 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
				if (other.hp == 0) {
//					플레이어의 hp가 0이 되면 loseMusic 출력 후 플레이어 개체 제거 후 게임 중지
					game.loseMusic();
					game.removeSprite(other);
					game.playerdiealert();
				}
			}
		}
	}
}
