package project;

import java.awt.Image;
import java.util.Random;

//1번 적 미사일 클래스
public class AlienShotSprite extends Sprite {
//	GalagaGame 클래스 사용을 위해 선언
	private GalagaGame game;
//	생성자로 개체 기본 값 설정
	public AlienShotSprite(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		dx = 1;
		dy = -2;
	}
//	움직이는 메소드이며 y값이 위로 100 || 아래로 600이면 제거
//	랜덤값에 의해 미사일이 좌우로 날 뛰며 움직이게 설정
	@Override
	public void move() {
		y -= dy;
		if (y < -100 || y>600) {
			game.removeSprite(this);
		}
		Random ran = new Random();
		int ranmove = ran.nextInt(100);
		if(ranmove >= 50) {
		if(x<=500) {
			x -= dx;
		}
		else if(x>=0) {
		x += dx;
		}
		}
		else if(ranmove < 50) {
			if(x<=500) {
				x += dx;
			}
			else if(x>=-30) {
			x -= dx;
			}
		}
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
//				플레이어의 hp가 0이 되면 loseMusic 출력 후 플레이어 개체 제거 후 게임 중지
				if (other.hp == 0) {
					game.loseMusic();
					game.removeSprite(other);
					game.playerdiealert();
				}
			}
		}
	}
}
