import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class NBulbPuzzle {

	public static int getTotalShiningMoments(int[] A)
	{
		int totalShiningMoments = 0;
		int totalBulbs = A.length;
		if (totalBulbs == 1) {
			totalShiningMoments = (A[0] == 1)? 1: 0;
			return totalShiningMoments;
		}
		Map<Integer, Integer> stateDictionary = getInitializedStateDictionary(A);
		int totalShiningMoments = 0;
		for (int moment = 0; moment < A.length; moment ++)
		{
			if (stateDictionary.containsKey(A[moment])) {
				stateDictionary.put(A[moment], 1);
				totalShiningMoments += validateShiningMoment(moment, stateDictionary);
			}
			
		}
		return totalShiningMoments;
 	}
	
	public static int validateShiningMoment(int moment, Map<Integer, Integer> dictionary)
	{
		if (dictionary.containsKey(1) && dictionary.get(1) != 1)
		{
			return 0;
		}
		int i = 1;
		for (; i <= moment; i++)
		{
		  if (dictionary.containsKey(i))
		  {
			  if (dictionary.get(i) == 1)
			  {
				  continue;
			  }
			  else return 0;
		  }
		}
		return (i != 1 && dictionary.containsKey(i-1) && dictionary.get(i-1) == 1)? 1: 0;
	}
	
	public static Map<Integer, Integer> getInitializedStateDictionary(int[] A)
	{
		Map<Integer, Integer> dictionary = new HashMap<>();
		IntStream intStream = Arrays.stream(A);
		intStream.forEach(a -> dictionary.put(a, 0));
		return dictionary;
	}
	
	public static void main(String[] args)
	{
		// test input
		int[] A = new int[] {2, 3, 1, 5, 4};
		System.out.println("Total shining moments : " + getTotalShiningMoments(A));
		
		A = new int[] {1, 3, 4, 2, 5};
		System.out.println("Total shining moments : " + getTotalShiningMoments(A));
		
		A = new int[] {2, 3, 4, 1, 5};
		System.out.println("Total shining moments : " + getTotalShiningMoments(A));
		
		A = new int[] {1, 2, 3, 5, 4};
		System.out.println("Total shining moments : " + getTotalShiningMoments(A));
	}
}
