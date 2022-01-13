import java.util.*;

public class Assignment3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Player player = new Player(input);
		Game game = new Game(player, input);
		game.start();
	}
}

class Game{
	private Player player;
	private final Dice dice;
	private int floorNo;
	private final Object floors[];
	private int outcome;
	private Scanner input;
	private int status;
	
	public Game(Player player, Scanner input) {
		dice = new Dice();
		status = 0;
		floorNo = -1;
		this.player = player;
		this.input = input;
		floors = new Object[14];
		floors[0] = new EmptyFloor();
		floors[1] = new EmptyFloor();
		floors[2] = new ElevatorFloor();
		floors[3] = new EmptyFloor();
		floors[4] = new EmptyFloor();
		floors[5] = new NormalSnakeFloor();
		floors[6] = new EmptyFloor();
		floors[7] = new EmptyFloor();
		floors[8] = new LadderFloor();
		floors[9] = new EmptyFloor();
		floors[10] = new EmptyFloor();
		floors[11] = new KingCobraFloor();
		floors[12] = new EmptyFloor();
		floors[13] = new EmptyFloor();
		System.out.println("Game setup is Ready!");		
	}
	
	public void start() {
		boolean flag = true;
		while(floorNo != 13) {
			if(flag) {
				System.out.print("\nHit enter to roll the dice");
				input.nextLine();
				outcome = dice.roll();
				System.out.println("\n" + dice);
			}
			if(status==0 && outcome!=1) {
				System.out.println("Game cannot start until you get 1");
				continue;
			}else if(floorNo + outcome < 14){
				if(status==0) {
					status = 1;
				}
				floorNo += outcome;
				System.out.println("Player Position Floor "+floorNo);
				Object objectFloor = floors[floorNo];
				if (objectFloor.getClass().getSimpleName().equals("EmptyFloor")) {
					EmptyFloor objEmptyFloor = (EmptyFloor) objectFloor;
					System.out.println(player.getName() + objEmptyFloor);
					objEmptyFloor.deal(player);
					flag = true;
				}else if(objectFloor.getClass().getSimpleName().equals("NormalSnakeFloor")) {
					NormalSnakeFloor objNormalSnakeFloor = (NormalSnakeFloor)objectFloor;
					System.out.println(player.getName() + objNormalSnakeFloor);
					objNormalSnakeFloor.deal(player);
					floorNo = objNormalSnakeFloor.getNewFloorNo();
					flag = false;
				}else if(objectFloor.getClass().getSimpleName().equals("KingCobraFloor")) {
					KingCobraFloor objKingCobraFloor = (KingCobraFloor)objectFloor;
					System.out.println(player.getName() + objKingCobraFloor);
					objKingCobraFloor.deal(player);
					floorNo = objKingCobraFloor.getNewFloorNo();
					flag = false;
				}else if(objectFloor.getClass().getSimpleName().equals("LadderFloor")) {
					LadderFloor objLadderFloor = (LadderFloor)objectFloor;
					System.out.println(player.getName() + objLadderFloor);
					objLadderFloor.deal(player);
					floorNo = objLadderFloor.getNewFloorNo();
					flag = false;
				}else if(objectFloor.getClass().getSimpleName().equals("ElevatorFloor")){
					ElevatorFloor objElevatorFloor = (ElevatorFloor)objectFloor;
					System.out.println(player.getName() + objElevatorFloor);
					objElevatorFloor.deal(player);
					floorNo = objElevatorFloor.getNewFloorNo();
					flag = false;
				}
				if (!flag) {
					outcome = 0;
				}
				System.out.println(player);
			}else {
				System.out.println("Player can't move!");
				continue;
			}
		}
		System.out.println("Game Over");
		System.out.println(player.getName() + " accumulated " + player.getTotalPoints() + " points.");
	}
	
}

class EmptyFloor{
	private final int _pointsAssociated;
	private final String _name;
	
	public EmptyFloor() {
		_pointsAssociated = 1;
		_name = "empty floor";
	}
	
	public void deal(Player player) {
		player.changePoints(_pointsAssociated);
	}
	
	@Override
	public String toString() {
		return " has reached " + _name;
	}
}


class ChangeFloor extends EmptyFloor{
	private final int _pointsAssociated;
	private final String _name;
	private final int newFloorNo;
	
	public ChangeFloor() {
		_pointsAssociated = -1;
		_name = "";
		newFloorNo = -1;
	}
	
	public int getNewFloorNo() {
		return newFloorNo;
	}
	
	@Override
	public void deal(Player player) {
		player.changePoints(_pointsAssociated);
	}
	
	@Override
	public String toString() {
		return " has reached " + _name;
	}	
	
}

class NormalSnakeFloor extends ChangeFloor{
	private final int _pointsAssociated;
	private final String _name;
	private final int newFloorNo;
	
	public NormalSnakeFloor() {
		_pointsAssociated = -2;
		_name = "normal snake floor";
		newFloorNo = 1;
	}
	
	@Override
	public int getNewFloorNo() {
		return newFloorNo;
	}
	
	@Override
	public void deal(Player player) {
		player.changePoints(_pointsAssociated);
	}
	
	@Override
	public String toString() {
		return " has reached " + _name;
	}	
}

class KingCobraFloor extends ChangeFloor{
	private final int _pointsAssociated;
	private final String _name;
	private final int newFloorNo;
	
	public KingCobraFloor() {
		_pointsAssociated = -4;
		_name = "King Cobra";
		newFloorNo = 3;
	}
	
	@Override
	public int getNewFloorNo() {
		return newFloorNo;
	}
	
	@Override
	public void deal(Player player) {
		player.changePoints(_pointsAssociated);
	}
	
	@Override
	public String toString() {
		return " has reached " + _name;
	}	
}

class LadderFloor extends ChangeFloor{
	private final int _pointsAssociated;
	private final String _name;
	private final int newFloorNo;
	
	public LadderFloor() {
		_pointsAssociated = 2;
		_name = "Ladder floor";
		newFloorNo = 12;
	}
	
	@Override
	public int getNewFloorNo() {
		return newFloorNo;
	}
	
	@Override
	public void deal(Player player) {
		player.changePoints(_pointsAssociated);
	}
	
	@Override
	public String toString() {
		return " has reached " + _name;
	}	
}

class ElevatorFloor extends ChangeFloor{
	private final int _pointsAssociated;
	private final String _name;
	private final int newFloorNo;
	
	public ElevatorFloor() {
		_pointsAssociated = 4;
		_name = "elevator floor";
		newFloorNo = 10;
	}
	
	@Override
	public int getNewFloorNo() {
		return newFloorNo;
	}
	
	@Override
	public void deal(Player player) {
		player.changePoints(_pointsAssociated);
	}
	
	@Override
	public String toString() {
		return " has reached " + _name;
	}	
}


class Dice{
	private final int numFaces;
	private int currFaceValue;
	private Random random;
	
	public Dice() {
		numFaces = 2;
		currFaceValue = -1;
		random = new Random();
	}
	
	public int roll() {
		currFaceValue = 1 + random.nextInt(numFaces);
		return currFaceValue;
	}
	
	@Override
	public String toString() {
		return "Dice gave " + currFaceValue;
	}
}

class Player{
	private final String _name;
	private int totalPts;
	
	public Player(Scanner input) {
		System.out.println("Enter the player Name and hit Enter");
		_name = input.nextLine();
		totalPts = 0;
	}
	
	public void changePoints(int change) {
		totalPts += change;
	}
	
	public String getName() {
		return _name;
	}
	
	public int getTotalPoints() {
		return totalPts;
	}
	
	@Override
	public String toString() {
		return "Total Points " + totalPts;
	}
}