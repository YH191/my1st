package project;

import java.awt.Image;

public class ShotSprite extends Sprite {
//	GalagaGame 클래스 사용을 위해 선언
	private GalagaGame game;
//	점수 표시를 위한 정수형 지역변수
	static int score;
//	생성자로 개체 기본 값 설정
	public ShotSprite(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		dy = -3;
	}
	@Override
	public void move() {
//		Sprite의 move메소드 실행
		super.move();
//		화면 위로 넘어가면 제거
		if (y < -100) {
			game.removeSprite(this);
		}
	}
	

	@Override
	public void handleCollision(Sprite other) {
//		other객체가 AlienSprite타입으로 형변환 가능한지 (총알이 적과 닿았는지) 확인
//		플레이어의 미사일이 AlienSprite와 닿았을때
		if (other instanceof AlienSprite) {
//			AlienSprite의 hp를 50 감소시키고 피격음 출력, 미사일 제거 후 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
//				AlienSprite의 hp가 0이면 점수를 10점 올리고 해당 AlienSprite 개체를 제거 후 랜덤 아이템 메소드 실행
				if (other.hp == 0) {
					score += 10;
					game.removeSprite(other);
					game.randomdropitem();

				}
			}
		}
//		플레이어의 미사일이 AlienSprite2와 닿았을때
		else if (other instanceof AlienSprite2) {
//			AlienSprite의 hp를 50 감소시키고 피격음 출력, 미사일 제거 후 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
//				AlienSprite의 hp가 0이면 점수를 100점 올리고 해당 AlienSprite 개체를 제거 후 랜덤 아이템 메소드 실행
//				nextround1() 메소드 실행으로 라운드1의 적 개체중 2,3,4가 죽으면 라운드 2의 몬스터 추가
				if (other.hp == 0) {
					score += 100;
					game.removeSprite(other);
					game.nextround1();
					game.randomdropitem();

				}
			}
		}
//		플레이어의 미사일이 AlienSprite3와 닿았을때
		else if (other instanceof AlienSprite3) {
//			AlienSprite의 hp를 50 감소시키고 피격음 출력, 미사일 제거 후 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
//				AlienSprite의 hp가 0이면 점수를 150점 올리고 해당 AlienSprite 개체를 제거 후 랜덤 아이템 메소드 실행
//				nextround1() 메소드 실행으로 라운드1의 적 개체중 2,3,4가 죽으면 라운드 2의 몬스터 추가
				if (other.hp == 0) {
					score += 150;
					game.removeSprite(other);
					game.nextround1();
					game.randomdropitem();

				}
			}
		}
//		플레이어의 미사일이 AlienSprite4와 닿았을때
		else if (other instanceof AlienSprite4) {
//			AlienSprite의 hp를 50 감소시키고 피격음 출력, 미사일 제거 후 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
//				AlienSprite의 hp가 0이면 점수를 200점 올리고 해당 AlienSprite 개체를 제거 후 랜덤 아이템 메소드 실행
//				nextround1() 메소드 실행으로 라운드2의 적 개체가 죽으면 라운드 3의 몬스터 추가
				if (other.hp == 0) {
					score += 200;
					game.removeSprite(other);
					game.nextround2();
					game.randomdropitem();

				}
			}
		}
//		플레이어의 미사일이 AlienSprite5와 닿았을때
		else if (other instanceof AlienSprite5) {
//			AlienSprite의 hp를 50 감소시키고 피격음 출력, 미사일 제거 후 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
//				AlienSprite의 hp가 0이면 점수를 250점 올리고 해당 AlienSprite 개체를 제거 후 랜덤 아이템 메소드 실행
				if (other.hp == 0) {
					score += 250;
					game.removeSprite(other);
					game.randomdropitem();

				}
			}
		}
//		플레이어의 미사일이 AlienSprite6와 닿았을때
		else if (other instanceof AlienSprite6) {
//			AlienSprite의 hp를 50 감소시키고 피격음 출력, 미사일 제거 후 hp -50
			if (other.hp >= 50) {
				game.hitMusic();
				game.removeSprite(this);
				other.hp -= 50;
//				AlienSprite의 hp가 0이면 점수를 200점 올리고 해당 AlienSprite 개체를 제거 후 랜덤 아이템 메소드 실행
//				nextround1() 메소드 실행으로 라운드2의 적 개체가 죽으면 라운드 3의 몬스터 추가
				if (other.hp == 0) {
					score += 200;
					game.removeSprite(other);
					game.nextround2();
					game.randomdropitem();

				}
			}
		}
	}
}