package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GalagaGame extends JPanel implements KeyListener {
//	게임 실행 판별을 위한 boolean 변수
	public boolean running = true;	
//	모든 개체를 담을 ArrayList sprites 
	ArrayList sprites = new ArrayList();	
//	플레이어 개체 생성,저장용
	 Sprite starship;	
//	적 개체 생성,저장용
	AlienSprite alien1;
    AlienSprite2 alien2;
	AlienSprite2 alien3;
	AlienSprite3 alien4;
	AlienSprite4 alien5;
	AlienSprite5 alien6;
	AlienSprite5 alien7;	
	AlienSprite5 alien8;
	AlienSprite6 alien9;
//	적 개체 이미지 저장용 BufferedImage
	private BufferedImage alienImage;
	private BufferedImage alienImage2;
	private BufferedImage alienImage3;
	private BufferedImage alienImage4;
	private BufferedImage alienImage5;
	private BufferedImage alienImage6;	
//	미사일 개체 이미지 저장용 BufferedImage
	private BufferedImage shotImage;
	private BufferedImage alienshotImage;
	private BufferedImage alienshotImage2;	
	private BufferedImage alienshotImage3;	
//	플레이어 개체 이미지 저장용 BufferedImage
	private BufferedImage shipImage;	
//	적 개체 죽일 시 드랍할 아이템 이미지 저장용 BufferedImage
	private BufferedImage pointitem;	
	private BufferedImage hpitem;	
//	배경 저장용 BufferedImage
	private BufferedImage backgroundImage;
//	필살기 발사 이미지
    private BufferedImage specialshotImage;
//	랜덤변수 생성으로 미사일 출력 간격 설정을 위한 변수
	int cnt;
    int cntFire; // 필살기 쓰기 위해서 카운트할 변수
    int sFire; 
    
//	생성자로 표시될 JFrame의 기초 설정
	public GalagaGame() {
//		JFrame "Galaga Game"을 생성
		JFrame frame = new JFrame("Galaga Game");		
//		생성한 JFrame의 크기 조절
		frame.setSize(800, 600);
//		프레임에 생성자 추가
		frame.add(this);		
//		창 크기 조절 불가
		frame.setResizable(false); 		
//		창이 보이게 설정
		frame.setVisible(true);		
//		종료시 제대로 종료되게 설정
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		이미지 파일 읽어서 BufferedImage 각 변수에 저장
		try {
			shotImage = ImageIO.read(new File("images/fire.png"));
			alienshotImage = ImageIO.read(new File("images/fire2.png"));
			alienshotImage2 = ImageIO.read(new File("images/fire3.png"));		
			alienshotImage3 = ImageIO.read(new File("images/fire4.png"));	
			shipImage = ImageIO.read(new File("images/starship.png"));
			alienImage = ImageIO.read(new File("images/alien.png"));
			alienImage2 = ImageIO.read(new File("images/alien2.png"));
			alienImage3 = ImageIO.read(new File("images/alien3.png"));
			alienImage4 = ImageIO.read(new File("images/alien4.png"));
			alienImage5 = ImageIO.read(new File("images/alien5.png"));
			alienImage6 = ImageIO.read(new File("images/alien6.png"));	
			backgroundImage = ImageIO.read(new File("images/backgroundImage.png"));
			pointitem = ImageIO.read(new File("images/pointitem.png"));		
			hpitem = ImageIO.read(new File("images/hpitem.png"));		
            specialshotImage = ImageIO.read(new File("images/specialfire.png"));
//		오류 예외 처리
		} catch (IOException e) {
			e.printStackTrace();
		}
//		포커싱
		this.requestFocus();
//		초기 개체들 생성
		this.initSprites();
//		키리스너 추가
		addKeyListener(this);
	}
//	초기 설정 메소드
	private void initSprites() {
//		플레이어 개체 추가
		starship = new StarShipSprite(this, shipImage, 370, 450, 500);		
//		플레이어 개체를 ArrayList sprites에 추가
		sprites.add(starship);
//		1번 적 개체 생성 할 for문이며 y값으로 행 설정, x값으로 열 설정 => 5행 12열
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 12; x++) {
				alien1 = new AlienSprite( this, alienImage, 100 + (x * 120), y * 30, 50);
//		5행 12열로 초탄생성
				AlienShotSprite enemyshot1 = new AlienShotSprite(this, alienshotImage, alien1.getX() - 150,
						alien1.getY() + 15 - 40 * x, 0);
//		ArrayList sprites에 생성한 적과 적의 초탄 추가
				sprites.add(alien1);
				sprites.add(enemyshot1);
			}
		}
//		2번 적 개체 생성 후 ArrayList sprites에 생성한 적 추가
			alien2 = new AlienSprite2(this, alienImage2, 350, 50, 500);
			sprites.add(alien2);
			
//		위아래로 움직이며 수직 방향으로 미사일 쏠 개체4 생성 후 ArrayList sprites에 생성한 적 추가
		alien3 = new AlienSprite2(this, alienImage4, 100, 0, 350);
		sprites.add(alien3);
//		Hp 가장 높은 4번 적 개체(1번 보스) 생성 후 ArrayList sprites에 생성한 적 추가
		alien4 = new AlienSprite3(this, alienImage3, 450, -10, 5000);
		sprites.add(alien4);
	}
//	스테이지 1 종료시 실행할 메소드로 5번 적 개체, 9번 적 개체 추가 후 등록
	public void nextround1() {
		if (!checkround1alive()) {
			alien5 = new AlienSprite4(this, alienImage5, 150, 0, 3500);
			sprites.add(alien5);
			alien9 = new AlienSprite6(this, alienImage6, 250, 0, 3500);
			sprites.add(alien9);
		}
	}

//	스테이지 2 종료시 실행할 메소드로 6번, 7번, 8번 적 개체 추가 후 등록
	public void nextround2() {
		if (!checkround2alive()) {
			alien6 = new AlienSprite5(this, alienImage6, 150, 0, 4000);
			sprites.add(alien6);
			alien7 = new AlienSprite5(this, alienImage5, 50, 0, 4500);
			sprites.add(alien7);
			alien8 = new AlienSprite5(this, alienImage5, 350, 0, 4000);
			sprites.add(alien8);
		}
	}

//	적 개체 사망시 랜덤숫자 생성 후 점수 상승 or Hp회복 개체 추가용 메소드
	public void randomdropitem() {
//		0~99까지의 정수 생성
		Random ran = new Random();
		int wow = ran.nextInt(100);
		if(wow>=0 && wow <= 20)
			getitem1();
		else if(wow>=21 && wow <= 26)
			getitem2();
	}
//	충돌 시 점수 상승 개체 추가용
	public void getitem1() {
		PointShotSprite coinitem = new PointShotSprite(this, pointitem, starship.getX(), 0, 350);
		sprites.add(coinitem);
	}
//	충돌 시 Hp 상승 개체 추가용
	public void getitem2() {
		HpShotSprite hpitemm = new HpShotSprite(this, hpitem, starship.getX(), 0, 350);
		sprites.add(hpitemm);
	}
	
//	시작시 sprites배열에 들어있는것을 초기화하고 초기 개체를 생성함
	private void startGame() {
		sprites.clear();
		initSprites();
	}
//	게임 종료에 사용할 메소드인데 사용하지 않을 것
	public void endGame() {
//		System.exit(0);
	}
//	sprites 배열에서 해당 개체를 지울 때 사용할 메소드
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
//	플레이어가 살아있는지 체크하고 미사일을 쏘며 미사일 발사 소리를 낼 메소드
	public void fire() {
		if (checkplayeralive()) {
			ShotSprite playershot = new ShotSprite(this, shotImage, starship.getX() + 10,
					starship.getY() - 30, 0);
			sprites.add(playershot);
			shotMusic();
            cntFire++;
		}
	}
// 필살기 사용 카운트를 올릴 때 사용할 발사횟수 측정을 위한 메소드
    public void countsFire(){
        if(cntFire == 10){
            sFire++;
            cntFire = 0;
        }
    }
// 필살기 발사 메소드
    public void specialFire(){  
        if(sFire > 0){
            if(checkplayeralive()){
                SpecialFireSprite specialfire = new SpecialFireSprite(this, specialshotImage, starship.getX(), starship.getY(), 100);
                sprites.add(specialfire);
                sFire--;
            }
        }
    }
//	플레이어 개체 생존 확인을 위한 메소드
	public boolean checkplayeralive() {
		if (sprites.contains(starship))
			return true;
		return false;
	}
//	1번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien1alive() {
		if (sprites.contains(alien1))
			return true;
		return false;
	}
//	2번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien2alive() {
		if (sprites.contains(alien2))
			return true;
		return false;
	}
//	3번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien3alive() {
		if (sprites.contains(alien3))
			return true;
		return false;
	}
//	4번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien4alive() {
		if (sprites.contains(alien4))
			return true;
		return false;
	}
//	5번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien5alive() {
		if (sprites.contains(alien5))
			return true;
		return false;
	}
//	6번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien6alive() {
		if (sprites.contains(alien6))
			return true;
		return false;
	}
//	7번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien7alive() {  
		if (sprites.contains(alien7))
			return true;
		return false;
	}
//	8번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien8alive() {
		if (sprites.contains(alien8))
			return true;
		return false;
	}

//	9번 적 개체의 생존 확인을 위한 메소드
	public boolean checkalien9alive() {
		if (sprites.contains(alien9))
			return true;
		return false;
	}
//	2,3,4번 개체가 모두 죽으면(배열에 포함되지 않다면) 라운드1의 종료를 확인할 메소드
	public boolean checkround1alive() {
		if (sprites.contains(alien2) || sprites.contains(alien3)
				|| sprites.contains(alien4))
			return true;
		return false;
	}
//	2, 3, 4, 5, 9번 개체가 죽으면 라운드2의 종료를 확인할 메소드
	public boolean checkround2alive() {
		if (sprites.contains(alien5) || sprites.contains(alien9)
				||(sprites.contains(alien2)) || (sprites.contains(alien3))
				|| (sprites.contains(alien4)))
			return true;
		return false;
	}
//	모든 개체가 죽으면 라운드3의 종료를 확인할 메소드
	public boolean checkround3alive() {
		if (   sprites.contains(alien7) || sprites.contains(alien8) || sprites.contains(alien9)
				|| sprites.contains(alien5) || sprites.contains(alien6) || sprites.contains(alien4)
				|| sprites.contains(alien3) || sprites.contains(alien2) || sprites.contains(alien1) )
			return true;
		return false;
	}
//	게임 종료 다이얼로그 출력 문구
	public void endgamealert() {
		JOptionPane.showMessageDialog(null, "게임 클리어", "알림", JOptionPane.WARNING_MESSAGE);
	}
//	플레이어가 죽었을 때 다이얼로그 출력 문구
	public void playerdiealert() {
		JOptionPane.showMessageDialog(null, "플레이어 사망! 플레이어 부활을 원하시면 F1버튼을 눌러주세요",
				"플레이어 사망!", JOptionPane.WARNING_MESSAGE);
	}
//	주기적으로 필드에 발사될 미사일 개체 생성
	public void fieldFire() {
		if (cnt % 70 == 0) {
			for(int i=0;i<5;i++) {
			AlienShotSprite fieldshot1 = new AlienShotSprite(this, alienshotImage, 70 + i*150, 15, 0);
			sprites.add(fieldshot1);
			}
		}
	}
//	적 개체의 생존여부에 따른 미사일 발사 메소드
	public void enemyFire2() {
//		gameloop가 100번실행될때마다 한번 실행하는 메소드이며 alien3이 생존중일때 미사일 담당
		if (cnt % 100 == 0) {
			if (checkalien3alive()) {
				AlienShotSprite2 enemyshot5 = new AlienShotSprite2(this, alienshotImage2, alien3.getX(),
						alien3.getY() + 50, 0);
				sprites.add(enemyshot5);
			}
		}
//		gameloop가 50번실행될때마다 한번 실행하는 메소드이며 alien4이 생존중일때 미사일 담당
		if (cnt % 50 == 0) {
			if (checkalien4alive()) {
				BossShotSprite enemyshot6 = new BossShotSprite(this, alienshotImage, alien4.getX(),
						alien4.getY() + 50,
						0);
				sprites.add(enemyshot6);
			}
		}

//		gameloop가 50번실행될때마다 한번 실행하는 메소드이며 alien5가 생존중일때 미사일 담당
		if(cnt % 50 == 0) {
				if(checkalien5alive()) {
				AlienShotSprite2 enemyshot7 = new AlienShotSprite2(this, alienshotImage2, alien5.getX(),
						alien5.getY() + 50, 0);
				sprites.add(enemyshot7);
				AlienShotSprite3 enemyshot8 = new AlienShotSprite3(this, alienshotImage2, alien5.getX(),
						alien5.getY() + 50, 0);
				sprites.add(enemyshot8);
				AlienShotSprite4 enemyshot9 = new AlienShotSprite4(this, alienshotImage2, alien5.getX(),
						alien5.getY() + 50, 0);
				sprites.add(enemyshot9);
				}
		}
//		gameloop가 250번실행될때마다 한번 실행하는 메소드이며 alien9가 생존중일때 미사일 담당
		if (cnt % 250 == 0)
				if (checkalien9alive()) {
					AlienShotSprite6 enemyshot12 = new AlienShotSprite6(this, alienshotImage3,
							alien9.getX(),	alien9.getY() + 50, 0);
					sprites.add(enemyshot12);
				}
//		각각 gameloop가 30번실행될때마다 한번 실행하는 메소드 / 250번 실행될때마다 실행하는 메소드이며
//		alien6 / 8이 생존중일때 미사일 담당
			if(cnt % 50 == 0) {
				if(checkalien6alive()) {
				AlienShotSprite5 enemyshot10 = new AlienShotSprite5(this, alienshotImage2,
						alien6.getX()-500, alien6.getY() + 50, 0);
				sprites.add(enemyshot10);				
				}
			if(cnt % 250 == 0)
				if(checkalien8alive()) {
				AlienShotSprite6 enemyshot11 = new AlienShotSprite6(this, alienshotImage3,
						alien8.getX()+150, alien8.getY() + 50, 0);
				sprites.add(enemyshot11);				
				}
		}
//		gameloop가 100번실행될때마다 한번 실행하는 메소드이며 alien7이 생존중일때 미사일 담당
		if (cnt % 100 == 0) {
				if (checkalien7alive()) {
					AlienShotSprite2 enemyshot11 = new AlienShotSprite2(this, alienshotImage2,
							alien7.getX(),
							alien7.getY() + 50, 0);
					sprites.add(enemyshot11);
					AlienShotSprite3 enemyshot12 = new AlienShotSprite3(this, alienshotImage2,
							alien7.getX(),
							alien7.getY() + 50, 0);
					sprites.add(enemyshot12);
					AlienShotSprite4 enemyshot13 = new AlienShotSprite4(this, alienshotImage2,
							alien7.getX(),
							alien7.getY() + 50, 0);
					sprites.add(enemyshot13);
			}
		}
	}
//	각 개체들을 그려줄 메소드이며 점수판도 이를 이용해 그림
	@Override
	public void paint(Graphics g) {
//		Graphics 개체를 가져온 다음 해당 개체에 대한 작업을 호출하여 구성 요소를 그림
//		super.paint(getGraphics());
//		배경 설정
		g.drawImage(backgroundImage, 0, 0, this);
//		점수판의 폰트, 크기, 표시내용, 위치 설정
		g.setColor(new Color(255, 255, 179));
		g.setFont(new Font(null, Font.BOLD, 28));
		g.drawString("Score:" + ShotSprite.score , 10, 30);
		g.drawString("필살:"+ sFire, 10, 110);
//		플레이어 개체가 생성된 다음 Hp를 표시해줄 메소드
		if(sprites.contains(starship)) {
		g.drawString("HP:"+ starship.hp , 10, 70);
		}else {
			g.drawString("HP: x", 10, 70);
		}
//		ArrayList sprite내부에 있는 모든 개체들을 Sprite형으로 변환해 그려줌
		for (int i = 0; i < sprites.size(); i++) {
			Sprite sprite = (Sprite) sprites.get(i);
			sprite.draw(g);
		}
	}

//게임의 진행을 계속 업데이트하며 표시 해줄 메소드
	public void gameLoop() {
		while (running) {

//			ArrayList의 모든 요소를 가져와 Sprite타입(슈퍼클래스)로 변환 후 이동 실행
			for (int i = 0; i < sprites.size(); i++) {
				Sprite sprite = (Sprite) sprites.get(i);
				sprite.move();
			}
//			s번째 인덱스의 스프라이트를 가져오고 s+1번째 인덱스부터의 스프라이트를 가져옴
//			각 개체들에 설정해둔 충돌 이벤트 체크용 for문
			for (int p = 0; p < sprites.size(); p++) {
				for (int s = p + 1; s < sprites.size(); s++) {

					Sprite me = (Sprite) sprites.get(p);
					Sprite other = (Sprite) sprites.get(s);

					if (me.checkCollision(other)) {
						me.handleCollision(other);
						other.handleCollision(me);
					}
				}
			}
//			컴포넌트를 다시 그려줄 메서드
			repaint();
			try {
//				1회 그릴때마다 cnt 숫자를 증가시켜 미사일의 발사 간격을 조절
				cnt++;
//				주기적으로 필드에 미사일 개체 생성
				fieldFire();
//				조건에 따라 적 개체에서 생성될 미사일의 추가를 위한 메소드
				enemyFire2();
//				필살기 발사 카운트를 위한 메소드
				countsFire();
//				게임 종료를 위한 메소드, 모든 적의 생존을 확인하고 게임 종료 알리는 다이얼로그 출력, 게임 종료
				if(!checkround3alive()) {
					this.endgamealert();
					System.exit(0);
				}
//				쓰레드의 실행 간격 조절
				Thread.sleep(10);
//				예외처리
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//	키가 눌렸을 때 작동할 이벤트 메소드
	@Override
	public void keyPressed(KeyEvent e) {
//		좌방향키 누르면 플레이어의 x축 이동속도를 -5로 변경
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			starship.setDx(-5);
		}
//		우방향키 누르면 플레이어의 x축 이동속도를 +5로 변경
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			starship.setDx(+5);
		}
//		상방향키 누르면 플레이어의 y축 이동속도를 -5로 변경
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			starship.setDy(-5);
		}
//		상방향키 누르면 플레이어의 y축 이동속도를 +5로 변경
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			starship.setDy(+5);
		}
//		스페이스바 키 누르면 fire()메소드 실행 ( 플레이어의 위치 계산 후 미사일 개체 생성 후 이동 )
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			fire();
		}
//필살기 사용을 위한 메소드
        if(e.getKeyCode() == KeyEvent.VK_B){
            specialFire();
        }
//플레이어 부활을 위한 메소드
        if(e.getKeyCode() == KeyEvent.VK_F1){
        	playerRevive();
        }
	}
//플레이어 부활을 위한 메소드
	public void playerRevive() {
		if(!checkplayeralive()) {
//			플레이어 개체 추가
			starship = new StarShipSprite(this, shipImage, 370, 450, 500);		
//			플레이어 개체를 ArrayList sprites에 추가
			sprites.add(starship);
		}
	}
//	누른 키를 뗄 때 작동할 이벤트 메소드
	@Override
	public void keyReleased(KeyEvent e) {
//		좌방향키 떼면 플레이어의 x축 이동속도를 0으로 변경
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			starship.setDx(0);
//		우방향키 떼면 플레이어의 x축 이동속도를 0으로 변경
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			starship.setDx(0);
//		상방향키 떼면 플레이어의 y축 이동속도를 0으로 변경
		if (e.getKeyCode() == KeyEvent.VK_UP)
			starship.setDy(0);
//		하방향키 떼면 플레이어의 y축 이동속도를 0으로 변경
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			starship.setDy(0);
	}
//	오류 방지를 위해 사용은 안해도 넣어두는 메소드
	@Override
	public void keyTyped(KeyEvent arg0) {

	}
//	배경 음악 재생을 위한 메소드
	public void playMusic() {
//		파일을 읽어 file에 저장
		File file = new File("wav/bgm.wav");
		
		try {
//			AudioInputStream 클래스로 stream 객체를 만들고 AudioSystem.getAudioInputStream(file)
//			메소드를 사용해 file에 저장된 오디오 입력 스트림을 가져와 stream에 저장 후 clip으로 읽어들여 재생함
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			
			clip.start();
//			clip.loop메소드를 사용해 음악을 반복재생
			clip.loop(Clip.LOOP_CONTINUOUSLY);
//			예외처리
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	미사일 발사음을 위한 메소드
	public void shotMusic() {
		File file = new File("wav/shot.wav");

		try {
//			AudioInputStream 클래스로 stream 객체를 만들고 AudioSystem.getAudioInputStream(file)
//			메소드를 사용해 file에 저장된 오디오 입력 스트림을 가져와 stream에 저장 후 clip으로 읽어들여 재생함
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
//			예외처리
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	미사일 피격음을 위한 메소드
	public void hitMusic() {
		File file = new File("wav/hitbymissile.wav");
		
		try {
//			AudioInputStream 클래스로 stream 객체를 만들고 AudioSystem.getAudioInputStream(file)
//			메소드를 사용해 file에 저장된 오디오 입력 스트림을 가져와 stream에 저장 후 clip으로 읽어들여 재생함
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
//			예외처리
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	사망시 출력음을 위한 메소드
	public void loseMusic() {
		File file = new File("wav/lose.wav");

		try {
//			AudioInputStream 클래스로 stream 객체를 만들고 AudioSystem.getAudioInputStream(file)
//			메소드를 사용해 file에 저장된 오디오 입력 스트림을 가져와 stream에 저장 후 clip으로 읽어들여 재생함
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
//			예외처리
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	아이템 획득시를 위한 메소드
	public void getitemMusic() {
		File file = new File("wav/coin.wav");

		try {
//			AudioInputStream 클래스로 stream 객체를 만들고 AudioSystem.getAudioInputStream(file)
//			메소드를 사용해 file에 저장된 오디오 입력 스트림을 가져와 stream에 저장 후 clip으로 읽어들여 재생함
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();
//			예외처리
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
//	메인함수이며 기본 생성자 실행으로 게임, 음악을 시작함
	public static void main(String[] args) {
		GalagaGame g = new GalagaGame();
		g.playMusic();
		g.gameLoop();

	}

}
