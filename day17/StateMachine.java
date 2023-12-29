import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class StateMachine {

	public StateMachine() {
		logger.debug = true;
	}

	public Chamber chamber;

	Action nextAction;

	Rock rock;

	RockGenerator rockGenerator;

	JetGenerator jetGenerator;

	EndTester endTester;

	String jet;

	Logger logger = new Logger(this);

	// Events/Actions
	// start
	// newRock
	// blowLeft
	// blowRight
	// drop
	// end

	enum Action { START, NEW_ROCK, NEW_JET, BLOW_LEFT, BLOW_RIGHT, DROP, END };

	void run() throws Exception {
		this.logger.log("run AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		this.nextAction = Action.START;
		while (this.nextAction != Action.END) {
			this.logger.log("Action: " + this.nextAction);
			switch (this.nextAction) {
				case START:
					this.start();
					break;
				case NEW_ROCK:
					this.newRock();
					break;
				case NEW_JET:
					this.newJet();
					break;
				case BLOW_LEFT:
					this.blowLeft();
					break;
				case BLOW_RIGHT:
					this.blowRight();
					break;
				case DROP:
					this.drop();
					break;
				case END:
					// this.end();
					break;
			}
			// this.logger.log(this.endTester.ended().toString());
		}
	}

	void start() {
		this.nextAction = Action.NEW_ROCK;
	}

	void newRock() throws Exception {
		if (this.endTester.ended()) {
			this.nextAction = Action.END;
			return;
		}
		this.rock = this.rockGenerator.next();
		this.chamber.addRock(this.rock);
		this.logger.log("newRock() added " + this.rock);
		this.nextAction = Action.NEW_JET;
	}

	void newJet() {
		this.jet = this.jetGenerator.next();
		this.nextAction = this.jet.equals(">") ? Action.BLOW_RIGHT : Action.BLOW_LEFT;
	}

	void blowLeft() throws Exception {

		this.nextAction = Action.DROP;
		try {
			this.rock.moveLeft();                                                                                                
			this.chamber.updateFallingRock(this.rock);          

		}
		catch (RockOutOfBoundsException e) {                                                                                                           
		}                                                                                                                                              
		catch (RocksOverlapException e) {                                                                                                              
			this.rock.moveRight();
			this.chamber.updateFallingRock(this.rock);                                                                                                    
			this.nextAction = Action.NEW_ROCK;
			return;
		}
	}

	void blowRight() throws Exception {
		this.nextAction = Action.DROP;
		try {
			this.rock.moveRight();                                                                                                
			this.chamber.updateFallingRock(this.rock);          

		}
		catch (RockOutOfBoundsException e) {                                                                                                           
		}                                                                                                                                              
		catch (RocksOverlapException e) {                                                                                                              
			this.rock.moveLeft();
			this.chamber.updateFallingRock(this.rock);                                                                                                    
			this.nextAction = Action.NEW_ROCK;
		}
	}

	void drop() throws Exception {

		this.nextAction = Action.NEW_JET;

		try {
			this.rock.moveDown();                                                                                                
			this.chamber.updateFallingRock(this.rock);          

		}
		catch (RockHitFloorException e) {                                                                                                           
			this.chamber.updateFallingRock(this.rock);                                                                                                    
			this.nextAction = Action.NEW_ROCK;
		}                                                                                                                                              
		catch (RocksOverlapException e) {                                                                                                              
			this.rock.moveUp();
			this.chamber.updateFallingRock(this.rock);                                                                                                    
			this.nextAction = Action.NEW_ROCK;
		}
	}

}
