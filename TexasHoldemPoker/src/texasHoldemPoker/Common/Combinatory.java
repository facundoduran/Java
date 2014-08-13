package texasHoldemPoker.Common;

import java.util.ArrayList;

public class Combinatory {
		
	public static ArrayList<ArrayList<Integer>> getCombinations(int k, int n)
	{
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> comb = new ArrayList<Integer>();
		
		for(int i = 0; i < k; ++i)
		{
			comb.add(i);
		}
		
        ArrayList<Integer> combination = new ArrayList<Integer>(comb);
        result.add(combination);
		
		boolean flag = true;
		
		while (flag)
		{					
			int i = k - 1; 
			
			int element = comb.get(i);
			
			++element;
			
			comb.set(i, element);		
			
	        while ((i > 0) && (comb.get(i) >= n - k + 1 + i)) 
			{
				--i; 
				
				element = comb.get(i);
				
				++element;
				
				comb.set(i, element);
			}
	        	        
			if (comb.get(0) > n - k) 
			{
				flag = false;
				break;
			}

			// No more combinations can be generated
	        for (i = i + 1; i < k; ++i) 
			{	
	        	int currentElement = comb.get(i-1) + 1;
	        	comb.set(i, currentElement);
			}	        
	        
	        combination = new ArrayList<Integer>(comb);
			
			result.add(combination);		
		}
		
		return result;
	} 
}
