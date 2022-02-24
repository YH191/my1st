package project;

import java.awt.Image;

//적 개체 클래스 1
public class AlienSprite extends Sprite{
//	GalagaGame 클래스 사용
	private GalagaGame game;
//	생성자로 개체 기본값 설정
	public AlienSprite(GalagaGame game, Image image, int x, int y, int hp) {
		super(image, x, y, hp);
		this.game = game;
		this.x = x;
		this.y = y;
		dx = +3;
	}
//	화면 밖으로 나가지 않게 x좌표의 이동 속도가 -이면 왼쪽 한계선을 설정해주고
//	x좌표의 이동 속도가 +이면 한계선을 설정해줘 x좌표의 이동속도를 뒤집어준다
//	y방향은 아래로 10씩 이동하며 y좌표가 600을 넘으면 개체를 삭제
	@Override
	public void move() {
		if(((dx < 0) && (x < 10)) || ((dx > 0) && (x > 800))) {
			dx = -dx;
			y += 10;
			if (y > 600) {
				game.removeSprite(this);
			}
		}
		super.move();
	}
//	이동방향과 좌표를 설정하기 위한 set & get
	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
