import java.util.*;

public class AIAgent{
  Random rand;

  ChessProject ChessProject;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){ //so the random move takes all valid movements from the stack and then chooses one at random without any considertation of what is happening on the board

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop(); //so we can see here all of the potential moves are looped and one of them is removed from the top of the stack
      System.out.println("Random turn print out test");
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove; //here the move is sent back the ChessProject.java and the move is executed in the ChessProject.java
  }

  /*

    This strategy doesnt take into account what happens after the move is made, so it could take a piece but the player could gain a big advantage one move later


    so an AI agent could use the Bishop to take a pawn but then the player could use a pawn to take the bishop


    when AI makes the move with a pawn its worth 1 point,
    but the queen has a value of 9 points


    Pawn:1
    Knight and Bishop is 3
    Rook:5
    Queen:9
    King is the game


we need to get all possible moves just like the random and apply the utility function to see which move to make.
  */

  /*public Move nextBestMove(Stack possibilities){
    System.out.println("next Best Move");
    Move selectedMove = new Move();
    return selectedMove;
  } */

  public Move nextBestMove(Stack whiteStack,Stack blackStack) {

        Stack whitePieces = (Stack) whiteStack.clone(); //I am making a clone here out of the stack which I defined above
        Stack black = (Stack) blackStack.clone();
        Move whiteMove;
        Move currentMove;
        Move bestMove; //here and slightly initiating the new moves, white movement current movement and best movement
        Square blackPosition;
        int points = 0; //setting the MAIN points to zero
        int potentialPoints = 0; //next potential points move is set to zero
        bestMove = null;

        while (!whiteStack.empty()) {
            whiteMove = (Move) whiteStack.pop();
            currentMove = whiteMove;


            // checking the middle of the board to assign the the axis either 3 or 4 points for the move

            //checking the cenre postion on the board, assigning different values to determine a next best attacking move based on the points which I give to all of the different pieces
            //so following video guide I gave the pawn 1 point, knight and bishop 3 rook is 5 and queen is the most as it is 9
            if ((currentMove.getStart().getYC() < currentMove.getLanding().getYC())
              && (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 3)
              || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 3)
              || (currentMove.getLanding().getXC() == 3) && (currentMove.getLanding().getYC() == 4)
              || (currentMove.getLanding().getXC() == 4) && (currentMove.getLanding().getYC() == 4))

            {
              points = 0;

            //here we are intiating the attack so if the points is bigger than potentialPoints then we simply do the best move
            if (points > potentialPoints) {
                    potentialPoints = points;
                    bestMove = currentMove;
                }
            }


            //here we are returning an attacking move if the piece has a higher value then the worth of the CENTRE postion
            //so if this is possible our agent will choose the bestmove as opposed to the random move
            while (!black.isEmpty()) {
                points = 0; //this sets our points to zero
                blackPosition = (Square) black.pop(); //note for me pop is to delete from stack so from the top of the list
                if ((currentMove.getLanding().getXC() == blackPosition.getXC()) && (currentMove.getLanding().getYC() == blackPosition.getYC())) { //here I am looking into my current move to check if the attacking moves are possible
                    //adds points for the black pieces
                    //if the attacking move is possible and the check is to see what the piece name is and if it is black pawn it is worth 1 point but if it is rook it will be 5 points so this will prioretise higher points
                    if(blackPosition.getName().contains("BlackPawn")){
                      points = 1;
                    }
                    else if(blackPosition.getName().contains("BlackBishop")){
                      points = 3;
                    }
                    else if(blackPosition.getName().contains("BlackKnight")){
                      points = 3;
                    }
                    else if(blackPosition.getName().contains("BlackRook")){
                      points = 5;
                    }
                    else if(blackPosition.getName().contains("BlackQueen")){
                      points = 9;
                    }

                }
                // updates the best next move
                if (points > potentialPoints) {
                    potentialPoints = points;
                    bestMove = currentMove;
                }
            }

            //so cloning and storing the new black stack into black with the new points scored
            black = (Stack) blackStack.clone();
        }
        // we will either go with the best move or Random
        if (potentialPoints > 0) {
            System.out.println("Best move selected by AI agent:" + potentialPoints);
            return bestMove;
        }
        return randomMove(whitePieces);

    }


/*
  This agent effectively extends the agent above as this agent look ahead to determine what the player is going to do nextInt

  This is like a min max

  so now  we know how to get all possible moves from the random move
  so now we want to catch the potential moves of the players pieces like we did for white pieces.
  Once this stack of movements is present we need a utility function to calculate the value of movements and try to estimate what movement the player will make
  and then the agent responds to the movement

  Random --> get all possible movements for White
        --> select a random moved

  NextBestMove --> get all possible movements for White
              --> create a utility function based on the current move... so when we take a piece we score points.
              --> loop through the stack and see if we are taking a piece and we are then make this movements

  twoLevelsDeep --> get all possible movements for White (stack)

                --> for each movement find the best possible response for the player
                -->get all possible movements for Black (stack) after the board changes for each movements for white
                --> rank the according to the utility function
                --> agent makes the best possible move it can with the worst best response from the player.


*/
  public Move twoLevelsDeep(Stack possibilities){
    System.out.println("Two levels deep");
    Move selectedMove = new Move();
    return selectedMove;
  }
}
