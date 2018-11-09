import java.util.HashSet;
import java.util.Set;

public class ProblemCannibals extends Problem {
	
    static final int cannL = 0;
    static final int missL = 1;
    static final int boatL = 2;
    static final int cannR = 3;
    static final int missR = 4;
    static final int boatR = 5;
    
	boolean goal_test(Object state) {
        StateCannibals can_state = (StateCannibals) state;
        
        if (can_state.canArray[cannR]==3 && can_state.canArray[missR]==3 && can_state.canArray[boatR]==1)
            return true;
        else return false;
	}
  
    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateCannibals can_state = (StateCannibals) state;
        
        //Let's create without any constraint, then remove the illegal ones
        StateCannibals successor_state;
        
        //one cannibal only from left to right
        successor_state = new StateCannibals(can_state);
        successor_state.canArray[cannL] -= 1;
        successor_state.canArray[cannR] += 1;
        successor_state.canArray[boatL] -= 1;
        successor_state.canArray[boatR] += 1;
        if (isValid(successor_state)) set.add(successor_state);

        //one cannibal only from right to left
        //TODO    
		StateCannibals cannRL1 = new StateCannibals(can_state);
		cannRL1.canArray[cannR] -= 1;
		cannRL1.canArray[cannL] += 1;
		cannRL1.canArray[boatR] -= 1;
		cannRL1.canArray[boatL] += 1;
		if(isValid(cannRL1)) set.add(cannRL1);
        
        //two cannibals from left to right
        //TODO
		StateCannibals cannLR2 = new StateCannibals(can_state);
		cannLR2.canArray[cannL] -= 2;
		cannLR2.canArray[cannR] += 2;
		cannLR2.canArray[boatL] -= 1;
		cannLR2.canArray[boatR] += 1;
		if(isValid(cannLR2)) set.add(cannLR2);
        
        //two cannibals from right to left 
        //TODO        
        StateCannibals cannRL2 = new StateCannibals(can_state);
		cannRL2.canArray[cannR] -= 2;
		cannRL2.canArray[cannL] += 2;
		cannRL2.canArray[boatR] -= 1;
		cannRL2.canArray[boatL] += 1;
		if(isValid(cannRL2)) set.add(cannRL2);
		
        //one missionary only from left to right 
        //TODO
		StateCannibals missLR1 = new StateCannibals(can_state);
		missLR1.canArray[missL] -= 1;
		missLR1.canArray[missR] += 1;
		missLR1.canArray[boatL] -= 1;
		missLR1.canArray[boatR] += 1;
		if(isValid(missLR1)) set.add(missLR1);
        
        //one missionary only from right to left 
        //TODO
        StateCannibals missRL1 = new StateCannibals(can_state);
		missRL1.canArray[missR] -= 1;
		missRL1.canArray[missL] += 1;
		missRL1.canArray[boatR] -= 1;
		missRL1.canArray[boatL] += 1;
		if(isValid(missRL1)) set.add(missRL1);
		
        //two missionaries from left to right 
        //TODO
		StateCannibals missLR2 = new StateCannibals(can_state);
		missLR2.canArray[missL] -= 2;
		missLR2.canArray[missR] += 2;
		missLR2.canArray[boatL] -= 1;
		missLR2.canArray[boatR] += 1;
		if(isValid(missLR2)) set.add(missLR2);
        
        //two missionaries from right to left 
        //TODO
		StateCannibals missRL2 = new StateCannibals(can_state);
		missRL2.canArray[missR] -= 2;
		missRL2.canArray[missL] += 2;
		missRL2.canArray[boatR] -= 1;
		missRL2.canArray[boatL] += 1;
		if(isValid(missRL2)) set.add(missRL2);
        
        //one cannibal and one missionary from left to right 
        //TODO
		StateCannibals canmissLR1 = new StateCannibals(can_state);
		canmissLR1.canArray[cannL] -= 1;
		canmissLR1.canArray[missL] -= 1;
		canmissLR1.canArray[cannR] += 1;
		canmissLR1.canArray[missR] += 1;
		canmissLR1.canArray[boatL] -= 1;
		canmissLR1.canArray[boatR] += 1;
		if(isValid(canmissLR1)) set.add(canmissLR1);
        
        //one cannibal and one missionary from right to left 
        //TODO 
		StateCannibals canmissRL1 = new StateCannibals(can_state);
		canmissRL1.canArray[cannR] -= 1;
		canmissRL1.canArray[missR] -= 1;
		canmissRL1.canArray[cannL] += 1;
		canmissRL1.canArray[missL] += 1;
		canmissRL1.canArray[boatR] -= 1;
		canmissRL1.canArray[boatL] += 1;
		if(isValid(canmissRL1)) set.add(canmissRL1);
        
        return set;
    }
    
    private boolean isValid(StateCannibals state)
    {   
        //Checking to see if any element of the array is negative 
        for (int i=0; i<6; i++)
            if (state.canArray[i] < 0) return false;
        
        //Checking to see if the numbers of cannibals, missionaries, and boat 
        //are more then 3,3,1 respectively
        //TODO
		if(state.canArray[cannL]>3 || state.canArray[missL]>3 || state.canArray[boatL]>1 || 
			state.canArray[cannR]>3 || state.canArray[missR]>3 || state.canArray[boatR]>1){
				return false;
			}
        
        //Now, checking if cannibals out number missionaries
        //TODO
		if (((state.canArray[cannR] > state.canArray[missR]&& state.canArray[missR] != 0)) ||
                ((state.canArray[cannL] > state.canArray[missL]) && state.canArray[missL] != 0)) {
            return false;
        }
        
        return true;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {
		ProblemCannibals problem = new ProblemCannibals();
		int[] canArray = {3,3,1,0,0,0};
		problem.initialState = new StateCannibals(canArray); 
		
		Search search  = new Search(problem);
		
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		System.out.println("AstarTreeSearch:\t" + search.AstarTreeSearch());
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        System.out.println("DepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());
        System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());
        System.out.println("AstarGraphSearch:\t" + search.AstarGraphSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
}
