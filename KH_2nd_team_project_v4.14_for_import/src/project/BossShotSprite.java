package project;

import java.awt.Image;
import java.util.Random;

//1번 보스 개체의 미사일을 위한 클래스
public class BossShotSprite extends Sprite {
//	GalagaGame 클래스 사용을 위해 선언
	private GalagaGame game;
//	생성자로 개체 기본 값 설정
	public BossShotSprite(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		dx = 1;
		dy = -2;
	}

//	움직이는 메소드이며 y값이 아래로 600이면 제거
//	0~99범위의 랜덤 정수를 생성해 좌우로 마구 움직이는 미사일 생성
//	y방향은 아래로 2씩 이동
	@Override
	public void move() {
		y -= dy;
		if (y>600) {
			game.removeSprite(this);
		}
		Random ran = new Random();
		int ranmove = ran.nextInt(100);
		if (ranmove >= 50 && ranmove < 100) {
			x += dx * 10;
		} else if (ranmove < 50 && ranmove >= 0) {
			x -= dx * 10;
		}
	}

	@Override
	public void handleCollision(Sprite other) {
//		총알이 적과 닿았는지 확인
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
