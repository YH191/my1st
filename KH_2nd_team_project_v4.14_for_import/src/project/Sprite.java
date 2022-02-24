package project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

//모든 개체의 기본 생성자가 될 클래스
public class Sprite {
//	현재 위치의 x, y좌표로 사용할 값
	protected int x;
	protected int y;
//	각 개체의 Hp로 사용할 값
	protected int hp;
//	단위시간에 움직이는 x,y방향거리
	protected double dx;
	protected double dy;
//	개체가 사용하는 이미지
	private Image image;
	
//	생성자이며 입력되는 이미지, x, y, hp를 받아들여 사용하기 위해 만듬
	public Sprite(Image image, int x, int y, int hp) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.hp = hp;
	}

//	이미지의 가로값,세로값 반환 -> 충돌 계산을 위해, 이미지 그리기 위해 draw 선언, 기본 움직임, dx & dy 설정,반환
	public int getWidth() {
		return image.getWidth(null);
	}

	public int getHeight() {
		return image.getHeight(null);
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
		
	}

	public void move() {
		x += dx;
		y += dy;
	}

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

//	충돌 여부 계산 후 충돌시 true 반환
	public boolean checkCollision(Sprite other) {
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
//		나의 위치, 적의 위치에 이미지의 크기에 해당하는 사각형 영역 위치시킴
		myRect.setBounds(x, y, getWidth(), getHeight());
		otherRect.setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());
//		myRect와 otherRect가 교차하는지 확인
		return myRect.intersects(otherRect);
	}

//	충돌 이벤트 처리 메소드 오버라이딩용
	public void handleCollision(Sprite other) {

	}


}
